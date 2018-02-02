// Filename:	jquery.smap.js
//
// Created: 	13/10/2015 (14:48:51)
// Created by: 	Lorenzo Saibal Forti
//
// Last Updated:	13/10/2015 (14:48:51)
// Updated by: 	Lorenzo Saibal Forti
//
// Copyleft:	2015 - Tutti i diritti riservati
//
// Comments:
(function($) {

	// oggetti pubblici per accesso fuori dal plugin
	publicMapObj = {};
	publicInfoWindow = {};
	publicBounds = null;

	/**
	* $.fn.drawMap
	*
	* istanzia e disegna la mappa
	*/
	$.fn.drawMap = function(customOptions) {

		// se google map non è caricata in modalità asincrona faccio controllo per esistenza oggetti corretti
		if (typeof(google) !== "object" || typeof(google.maps) !== "object") return false;

		var $this = $(this);
		var $thisid = "#" + $this.attr('id');

		var marker = {};
		var mapInfoWindow = {};
		var bounds = null;
		var currentInfoWindow = null;

		// parametri di default
		var defParams = {
			centerFirstItem			: false,
			mapAutoFit				: true,
			mapAsyncFit				: true,
			mapEmptyShow			: false,
			exposeMap				: true,
			zoom					: 10,
			queryTimeout			: 250,
			queryAttemps			: 6,
			infoMaxWidth			: "",
			labelOnMarker			: false,
			labelList				: "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789",
			labelColor				: "#000",
			labelSize				: "16px",
			iconCommUrl				: "",
			iconSizeW				: 31,
			iconSizeH				: 40,
			templateOpen			: "",
			templateClose			: "",
			callbackName			: "",
			center					: {lat: 41.890251, lng: 12.492373},
			mapTypeControl			: true,
			mapTypeControlOptions	: {position: google.maps.ControlPosition.TOP_RIGHT},
			navigationControl		: true,
			mapTypeId				: google.maps.MapTypeId.ROADMAP,
			isDebug					: false
		};

		var self = {};

		var opt = $.extend(true, {}, defParams, customOptions);
		//var opt = $.extend(true, {}, $.merge($.fn.drawMap.defParams, defParams), customOptions);

		// parametri di google maps
		var gMapParams = {
			zoom					: opt.zoom,
			center					: opt.center,
			mapTypeControl			: opt.mapTypeControl,
			mapTypeControlOptions	: opt.mapTypeControlOptions,
			navigationControl		: opt.navigationControl,
			mapTypeId				: opt.mapTypeId
		};

		// per ogni chiamata espongo i parametri se l'opzione è true
		if(opt.exposeMap == true) {

			$.each(opt, function() {

				var $that = $this;

				$that.data("expose_settings", $.extend(true, {}, defParams, customOptions || {}));
			});
		}

		/**
		* init()
		*
		* istanzia la mappa
		*/
		self.init = function() {

			if ($this.length) {

				// istanzio la mappa. utilizzo $.data per passare il riferimento all'oggetto in maniera univoca
				$.data($this, "map_obj", new google.maps.Map($this[0], gMapParams));
				$.data($this, "loop_index", 0);

				// se c'è almeno un indirizzo chiamo la funzione che cicla. altrimenti chiamo solo la callback
				if (opt.markers.length) {

					self.setMarker(0);

				} else {

					self.callbackFunction();
				}

				// ricentro la mappa al resize
				$(window).on("resize", function(){

					// chiudo eventuali infowindow
					if (currentInfoWindow != null) mapInfoWindow[currentInfoWindow].win.close();

					var center = $.data($this, "map_obj").getCenter();
					google.maps.event.trigger($.data($this, "map_obj"), "resize");
					$.data($this, "map_obj").setCenter(center);

					self.toLog("resize della mappa");
				})

				// se esposta, registro la mappa in un oggetto pubblico
				if (opt.exposeMap == true) {

					publicMapObj[$thisid] = $.data($this, "map_obj");

					self.toLog("oggetto mappa pubblico");
				}
			}
		};

		/**
		* callbackResize()
		*
		* callback di resize se è stata esposta la mappa
		*/
		self.callbackResize = function(mapdiv) {

			this.option = $(mapdiv).data("expose_settings");

			if(typeof(this.option) != "undefined" && this.option.exposeMap == true && publicMapObj[mapdiv]) {

				if (publicInfoWindow[mapdiv]) publicInfoWindow[mapdiv].close();

				var center = publicMapObj[mapdiv].getCenter();
				google.maps.event.trigger(publicMapObj[mapdiv], "resize");
				publicMapObj[mapdiv].setCenter(center);

				if (this.option.markers.length) {

					if (this.option.centerFirstItem == false && this.option.mapAutoFit == true) {

						publicMapObj[mapdiv].fitBounds(publicBounds);

						self.toLog("callback fitbounds");

					} else {

						publicMapObj[mapdiv].setZoom(publicMapObj[mapdiv].getZoom());

						self.toLog("callback zoom");
					}
				}
			}
        };

        /**
		* getMapObj()
		*
		* restituisce l'oggetto mappa a chi lo richiede
		*/
		self.getMapObj = function(mapdiv) {

			this.option = $(mapdiv).data("expose_settings");

			if(typeof(this.option) != "undefined" && this.option.exposeMap == true && publicMapObj[mapdiv]) {

				return publicMapObj[mapdiv];
			}
        };

		/**
		* setFirstCenter()
		*
		* imposta il centro sul primo risultato utile
		*/
		self.setFirstCenter = function() {

			if (opt.centerFirstItem == true && opt.markers[0]["position"]) {

				if (typeof(firstcenter_set) === "undefined") {

					newmapCenter = opt.markers[0]["position"];
					$.data($this, "map_obj").setCenter(newmapCenter);

					firstcenter_set = true;

					self.toLog("centro impostato: " + newmapCenter);
				}
			}
		};

		/**
		* drawMarker()
		*
		* istanzia e disegna un marker
		*/
		self.drawMarker = function(key) {

			var params = {
				position: opt.markers[key]["position"],
				map: $.data($this, "map_obj")
			};

			if(opt.labelOnMarker == true) {

				params["label"] = {
					text: opt.labelList[key % opt.labelList.length],
					color: opt.labelColor,
					fontSize: opt.labelSize
				};
			}

			if(opt.markers[key]["icon"]) {

				// hack per impostare correttamente le dimensioni sull'icona
				params["icon"] = new google.maps.MarkerImage(
					opt.iconCommUrl + opt.markers[key]["icon"],
					null, /* size a runtime */
					null, /* origin è 0,0 */
					null, /* anchor */
					new google.maps.Size(opt.iconSizeW, opt.iconSizeH)
				);

				// sembra non funzionare il resize delle immagini
				//~ params["icon"] = {
					//~ url: opt.markers[key]["icon"],
					//~ origin: new google.maps.Point(0, 0),
					//~ anchor: new google.maps.Point(0, 32),
					//~ size: new google.maps.Size(50, 50),
				//~ };
			}

			marker[key] = new google.maps.Marker(params);
		};

		/**
		* infoWindow()
		*
		* funzione per la infowindow
		*/
		self.infoWindow = function(key) {

			var alldata = opt.markers[key]["data"].split("||").join("</p> <p>");;

			var params = {
				content: opt.templateOpen + "<p>" +alldata+ "</p>" + opt.templateClose
			};

			if(opt.infoMaxWidth != "") params["maxWidth"]= opt.infoMaxWidth;

			// istanzio info window sul marker
			this.win = new google.maps.InfoWindow(params);
		};

		/**
		* infoListener()
		*
		* listener per la infoWindow
		*/
		self.infoListener = function(key) {

			// se non è stata istanziata manualmente, creo la infowindow
			if (typeof(mapInfoWindow[key]) === "undefined") {

				mapInfoWindow[key] = new self.infoWindow(key);

				self.toLog("infowindow " +key+ ": autoset su " +$thisid);
			}

			// funzione con closure necessaria: https://developers.google.com/maps/documentation/javascript/examples/event-closure
			google.maps.event.addListener(marker[key], "click", (function(key) {

				return function() {

					if (currentInfoWindow != null) mapInfoWindow[currentInfoWindow].win.close();
					mapInfoWindow[key].win.open($.data($this, "map_obj"), marker[key]);
					currentInfoWindow = key;

					if (opt.exposeMap == true) {
						publicInfoWindow[$thisid] = mapInfoWindow[key].win;
					}
				}
			})(key));
		};

		/**
		* addBounds()
		*
		* aggiunge le coordinate all'array per riposizionare lo zoom della mappa
		*/
		self.addBounds = function(key) {

			if (key == 0) {

				bounds = new google.maps.LatLngBounds();
				publicBounds = bounds;

				self.toLog("latlngbounds: class istanziata");
			}

			if (opt.markers[key]["position"]) {

				bounds.extend(opt.markers[key]["position"]);

				if (opt.exposeMap == true) {
					publicBounds.extend(opt.markers[key]["position"]);
				}
			}
		};

		/**
		* boundsOnLast()
		*
		* resize dello zoom sulla base dei marker solo sull'ultimo risultato utile
		*/
		self.fitBoundsAsync = function(key) {

			self.addBounds(key);

			if ($.data($this, "loop_index") == opt.markers.length) {

				$.data($this, "map_obj").fitBounds(bounds);

				self.toLog("fitboudasync: eseguito");
			}
		};

		/**
		* callbackFunction()
		*
		* resize dello zoom sulla base dei marker solo sull'ultimo risultato utile
		*/
		self.callbackFunction = function() {

			if ($.data($this, "loop_index") == opt.markers.length) {

				if (opt.callbackName != "" && typeof(opt.callbackName) == "function") {

					google.maps.event.addListener($.data($this, "map_obj"), "idle", function() {

						opt.callbackName.call(this);

						self.toLog("callback eseguita");
					});
				}
			}
		};

		/**
		* geoCode()
		*
		* funzione geocode deferred
		*/
		self.geoCode = function(data) {

			// istanzio la classe geocoder
			if (!$.data($this, "geocoder")) {

				$.data($this, "geocoder", new google.maps.Geocoder());

				self.toLog("geocode istanziata su " +$thisid);
			}

			var def = $.Deferred();

			$.data($this, "geocoder").geocode(data, function(results, status) {

				if (status == google.maps.GeocoderStatus.OK) {
					def.resolve(results, status);
				} else {
					def.reject(status);
				}

			});

			return def.promise();
		};

		/**
		* setMarker()
		*
		* aggiunge i marker
		*/
		self.setMarker = function(key) {

			// istanzio subito i risultati con le coordinate
			if(opt.markers[key]["lat"] && opt.markers[key]["lng"]) {

				// aggiungo dato all'oggetto per la posizione su base coordinate
				opt.markers[key]["position"] = new google.maps.LatLng(opt.markers[key]["lat"], opt.markers[key]["lng"]);

				if(opt.markers[key]["position"]) {

					$.data($this, "loop_index", parseInt($.data($this, "loop_index")) + 1);

					// disegno il marker
					self.drawMarker(key);

					// listener per infowindow
					self.infoListener(key);

					// aggiungo le coordinate dell'indirizzo se autofit è true
					if (opt.centerFirstItem == false && opt.mapAutoFit == true) {

						self.fitBoundsAsync(key);
					}

					// eseguo la callback se c'è
					self.callbackFunction();

					// funzione ricorsiva senza timeout (con le coordinate non serve)
					if ($.data($this, "loop_index") < opt.markers.length) {

						self.setMarker($.data($this, "loop_index"));
					}
				}

			} else {

				if (opt.markers[key]["address"] != "") {

					// per gli array con indirizzi chiamo la geocodifica. promise function
					self.geoCode({

						address: opt.markers[key]["address"]

					// se il risultato è positivo vado avanti con lo scorrimento dell'array
					}).done(function(results) {

						$.data($this, "loop_index", parseInt($.data($this, "loop_index")) + 1);

						self.toLog("geocode ok: " +opt.markers[key]["address"]);

						// definisco la posizione su base coordinate
						opt.markers[key]["position"] = results[0].geometry.location;

						// disegno il marker
						self.drawMarker(key);

						// listener per infowindow
						self.infoListener(key);

						// funzione ricorsiva con timeout (con gli indirizzi serve) e max attemps
						if ($.data($this, "loop_index") < opt.markers.length) {

							setTimeout(function() {

								self.setMarker($.data($this, "loop_index"));

							}, (opt.queryTimeout));
						}

					}).fail(function() {

						// creo l'oggetto per il conteggio se è la prima volta che la query fallisce l'indirizzo
						if(typeof($.data($this, "query_try_" +key)) === "undefined") $.data($this, "query_try_" +key, 1);

						self.toLog("timeout " +$.data($this, "query_try_" +key)+ ": " +opt.markers[key]["address"]+ ". recall.");

						// se il numero di errori sulla stessa chiave è inferiore al limite
						if ($.data($this, "query_try_" +key) < opt.queryAttemps) {

							// aumento il contatore dei tentativi
							$.data($this, "query_try_" +key, parseInt($.data($this, "query_try_" +key)) + 1);

							// imposto il fail_index e richiamo la funzione reiterativa
							$.data($this, "fail_index", key);

						} else {

							// se l'indirizzo ha superato i tentativi ammessi aumento il contatore generale e vado avanti nel loop
							$.data($this, "loop_index", parseInt($.data($this, "loop_index")) + 1);

							// imposto il fail_index e richiamo la funzione reiterativa
							$.data($this, "fail_index", $.data($this, "loop_index"));

							self.toLog("max attemps raggiunto: " +opt.markers[key]["address"]);
						}

						if ($.data($this, "fail_index") < opt.markers.length) {

							// reitero la chiamata. passo "index" e non "start_index" o "key"
							setTimeout(function() {

								self.setMarker($.data($this, "fail_index"));

							}, (opt.queryTimeout * 3));
						}

					// chiamo sempre l'autofit (viene eseguito solo alla fine dell'array)
					}).always(function() {

						// aggiungo le coordinate dell'indirizzo se autofit è true ma solo sull'ultimo elemento
						if (opt.centerFirstItem == false && opt.mapAutoFit == true && opt.mapAsyncFit == true) {

							self.fitBoundsAsync(key);
						}

						// eseguo la callback se c'è
						self.callbackFunction();
					});
				}
			}

			// se abilitato imposto il centro sul primo risultato trovato
			self.setFirstCenter();
		};

		/**
		* toLog()
		*
		* abilita o meno i messaggi di log
		*/
		self.toLog = function(msg) {
			if (opt.isDebug) {
				(window.console && window.console.log) ? window.console.log(msg) : ""
			}
		};

		self.init();

		return self;
	};

})(jQuery);
