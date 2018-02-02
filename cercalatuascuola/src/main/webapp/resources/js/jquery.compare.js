// Filename:	jquery.compare.js
//
// Created: 	27/07/2015 (16:43:17)
// Created by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
//
// Last Updated:	27/07/2015 (16:43:17)
// Updated by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
//
// Copyleft:	2015 - Tutti i diritti riservati
(function($) {

	$.fn.compare = function(customOptions) {

		var defParams = {
			maxItems: 500,
			cookiePrefix: "cookie_",
			counterClass: "compare-counter",
			alertPlugin: "modal",
			alertClose: 8000,
			alertText: "Il numero massimo di scuole selezionabili &egrave; 500.",
			isDebug	: false
		}

		var self = {};

		var opt = $.extend(defParams, customOptions);

		/**
		* toLog()
		*
		* abilita o meno i messaggi di log
		*/
		self.toLog = function(msg) {
			if (opt.isDebug) {
				(window.console && window.console.log) ? window.console.log(msg) : alert(msg)
			}
		};

		/**
		* init()
		*
		* controllo i checkbox memorizzati nel cookie. attivo i controlli alla funzione onclick sui checkbox del form
		*/
		self.init = function() {

			// plyfill per ie8
			self.objPolifyll();

			// azzero tutte le selezioni del relativo form
			$("#" +opt.formId+ " [type=checkbox]").prop("checked", false);

			// leggo il cookie e vedo i risultati
			this.read_cookie = self.readCookie();

			// deduco il contatore dai valori nel cookie o dal numero massimo delle opzioni
			if (this.read_cookie.items && this.read_cookie.items.length > 0) {

				if (this.read_cookie.items.length > opt.maxItems) {
					this.num_items = opt.maxItems;
				} else {
					this.num_items = this.read_cookie.items.length;
				}

				// update del contatore
				self.counterUpdate(this.num_items);

				// scorro tutti i valori del cookie per trovare il relativo checkbox sulla base del valore
				$.each(this.read_cookie.items, function(key, val) {

					this.id = Object.keys(val);

					if ($("#" + this.id).length > 0) {

						$("#" + this.id).prop("checked", true);
						self.toLog("init: " +this.id+ " checkbox settati");
					}
				});
			}

			// ad ogni selezione/deselezione dei checkbox attivo il controllo
			$("#" +opt.formId+ " [type=checkbox]").on("click", function() {

				// controllo che il checkbox abbia l'id
				if ($(this).attr("id")) {

					// usa anche l'id nell cookie
					this.obj_val = $.trim( $(this).val() );
					this.obj_id = $(this).attr('id');

					// se è check
					if($(this).is(":checked")) {

						// se il valore non esiste nel cookie
						if (self.checkVal(this.obj_id) !== true) {

							// aggiungo il valore
							this.addval = self.addVal(this.obj_id, this.obj_val);
							self.toLog("init: il valore non esiste. aggiungo");

						} else {

							self.toLog("init: il valore esiste già nel cookie. non lo aggiungo");
						}

					// se non è check
					} else {

						// se il valore esiste nel cookie
						if (self.checkVal(this.obj_id) === true) {

							// cancello il valore
							self.removeVal(this.obj_id);
							self.toLog("init: il valore esiste. cancello");

						} else {

							self.toLog("init: il valore non esiste nel cookie. non lo cancello");
						}
					}
				}
			});
		};

		/**
		* isCookie()
		*
		* controllo l'esistenza del cookie
		*/
		self.isCookie = function() {

			if ($.cookie(opt.cookieName)) {

				self.toLog("isCookie: il cookie esiste");
				return true;

			} else {

				self.toLog("isCookie: il cookie non esiste");
				return false;
			}
		};

		/**
		* readCookie()
		*
		* leggo il cookie e ritorno i valori dentro un array
		*/
		self.readCookie = function() {

			this.cookieVals = { items:[] };

			// enter se il cookie esiste
			if (self.isCookie() === true) {

				this.cookieVals = JSON.parse($.cookie(opt.cookieName)); //$.cookie(opt.cookieName).split(",");
				self.toLog("readCookie: return dei valori presenti nel cookie");
			}

			return this.cookieVals;
		};

		/**
		* setCookie()
		*
		* imposta il cookie con i nuovi valori
		*/
		self.setCookie = function(values) {

			$.cookie(opt.cookieName, JSON.stringify(values), { path: '/' });

			// aggiorno il contatore
			self.counterUpdate(values.items.length);
		};

		/**
		* delCookie()
		*
		* cancella il cookie
		*/
		self.delCookie = function(cookiename) {

			cookiename = typeof(cookiename) !== "undefined" ? cookiename : opt.cookieName;

			$.removeCookie(cookiename, { path: '/' });

			// aggiorno il contatore
			self.counterUpdate(0);

			if ($("#" +opt.formId).length > 0) {

				// azzero tutte le selezioni del relativo form
				$("#" +opt.formId+ " [type=checkbox]").prop("checked", false);
			}
		};

		/**
		* checkVal()
		*
		* controllo l'esistenza nel cookie del valore passato come parametro
		*/
		self.checkVal = function(id) {

			this.cookie_vals 	= self.readCookie();
			var check_val 		= false;

			if (this.read_cookie.items && this.cookie_vals.items.length > 0) {

				self.toLog("checkVal: controllo esistenza valore passato");

				$.each(this.cookie_vals.items, function(keys, vals) {

					this.id = Object.keys(vals);

					if (this.id == id) {

						check_val = true;

						return false;
					}
				});

				if (check_val == true) {

					self.toLog(id+ " esiste nel cookie");
					return true;

				} else {

					self.toLog(id+ " non esiste nel cookie");
					return false;
				}
			}
		};

		/**
		* addVal()
		*
		* aggiunge il valore al cookie
		*/
		self.addVal = function(id, val) {

			var cookie_vals = self.readCookie();
			var add_val 	= true;

			// blocco se superato il limite
			if (cookie_vals.items.length >= opt.maxItems) {

				//alert
				self.showAlert();
				// uncheck dell'elemento
				$('#' +id).prop('checked', false);

				return false;
			}

			$.each(cookie_vals.items, function(keys, vals) {

				var key = Object.keys(vals);

				if (key == id) {

					add_val = false;

					return false;
				}
			});

			if (add_val == true) {

				this.newval = {};
				this.newval[id] = val;

				cookie_vals['items'].splice(cookie_vals.items.length, 1, this.newval);

				self.setCookie(cookie_vals);
			}
		};

		/**
		* removeVal()
		*
		* rimuove il valore dal cookie
		*/
		self.removeVal = function(id) {

			var cookie_vals = self.readCookie();

			$.each(cookie_vals.items, function(keys, vals) {

				var key = Object.keys(vals);

				if (key == id) {

					cookie_vals['items'].splice( keys, 1 );
					self.toLog("cancello " +key);

					return false;
				}
			});

			if (cookie_vals.items.length == 0) {

				self.delCookie();

				return false
			}

			self.setCookie(cookie_vals);
		};

		/**
		* counterUpdate()
		*
		* update del contatore degli item
		*/
		self.counterUpdate = function(total) {

			if (opt.counterClass != "" && $("." +opt.counterClass).length > 0) {

				$("." +opt.counterClass).text(total);
			}
		};

		/**
		* showAlert()
		*
		* mostra un alert custom se presente il plugin di default
		*/
		self.showAlert = function() {

			if( typeof(window[opt.alertPlugin]) !== "undefined") {

				modal({
					type: "info",
					title: "Attenzione!",
					text: opt.alertText,
					onShow: function(r) {
						setTimeout(function() {
							$("#" +r.html.attr("id")).fadeOut();
						}, opt.alertClose);
					},
					buttonText: {
						ok: "Chiudi"
					}
				});

			} else {

				alert(alertText);
			}
		};

		/**
		* objPolifyll()
		*
		* polifyll per object.key che non funziona su ie8
		*/
		self.objPolifyll = function() {

			if (!Object.keys) {

				Object.keys = (function() {
					"use strict";
					var hasOwnProperty = Object.prototype.hasOwnProperty,
					hasDontEnumBug = !({ toString: null }).propertyIsEnumerable("toString"),
					dontEnums = [
					  "toString",
					  "toLocaleString",
					  "valueOf",
					  "hasOwnProperty",
					  "isPrototypeOf",
					  "propertyIsEnumerable",
					  "constructor"
					],
					dontEnumsLength = dontEnums.length;

					return function(obj) {
						  if (typeof obj !== "object" && (typeof obj !== "function" || obj === null)) {
							throw new TypeError("Object.keys called on non-object");
						  }

						var result = [], prop, i;

						for (prop in obj) {
							if (hasOwnProperty.call(obj, prop)) {
								result.push(prop);
							}
						}

						if (hasDontEnumBug) {
							for (i = 0; i < dontEnumsLength; i++) {
								if (hasOwnProperty.call(obj, dontEnums[i])) {
									result.push(dontEnums[i]);
								}
							}
						}
						return result;
					};
				}());
			} else {
				self.toLog("ceee");
			}
		};

		var $this = $(this);

		// se esiste il form ed è impostato correttamente l'id
		if ($this.length > 0 && $this.attr("id")) {

			opt.formId		= $this.attr("id");
			opt.cookieName 	= opt.cookiePrefix + opt.formId;

			self.init();
		}

		// evito firefox cache
		window.onunload = function(){};

		return self;
	};
})(jQuery);