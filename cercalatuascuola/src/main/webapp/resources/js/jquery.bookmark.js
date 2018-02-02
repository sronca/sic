// Filename:	jquery.bookmark.js
//
// Created: 	27/07/2015 (16:42:54)
// Created by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
//
// Last Updated:	27/07/2015 (16:42:54)
// Updated by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
//
// Copyleft:	2015 - Tutti i diritti riservati
(function($) {

	$.fn.bookmark = function(customOptions) {

		// dichiariazione dei parametri: esterni al plugin per override

		var self = {};

		var opt = $.extend({}, $.fn.bookmark.defParams, customOptions);

		// imposto un prefisso per identificare le chiavi in maniera univoca nel conteggio
		opt.keyPrefix = typeof(opt.keyPrefix) !== "undefined" && opt.keyPrefix != "" ? opt.keyPrefix : "sbm-";

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
		*
		*/
		self.init = function(that) {

			// se non c'è supporto a localstorage blocco
			if (self.supportStorage() === false) return false;

			// controllo che ci sia il selettore per eseguire il controllo metadati
			if ($(that.selector).length > 0 ) {

				// controllo e registro i metadati necessari
				if (self.checkMetaData(that) === false) return false;

				self.toggleClass(opt.idkey);
			}

			self.showTot();
		};

		/**
		* supportStorage()
		*
		* controllo supporto
		*/
		self.supportStorage = function(msgalert) {

			msgalert = typeof(msgalert) !== "undefined" ? msgalert : false;

			if (typeof(Storage) === "undefined") {

				if (msgalert != false) {

					self.showAlert(msgalert);
				}

				self.toLog("Funzione Local Storage non supportato");
				return false;
			}

			return true;
		};

		/**
		* checkMetaData()
		*
		* controllo l'esistenza dei metadati necessari. se mancano blocco il codice
		*/
		self.checkMetaData = function(that) {

			opt.idval	= that.data("idval");
			opt.idkey 	= opt.keyPrefix + opt.idval;
			opt.addr	= that.data("address");
			opt.email	= that.data("email");
			opt.ogtit 	= $("meta[property=\"og:title\"]").attr("content");
			opt.ogurl 	= $("meta[property=\"og:url\"]").attr("content");
			opt.midval	= that.data("midval");
			opt.mtit	= that.data("mtit");
			opt.murl	= that.data("murl");

			if (typeof(opt.idval) === "undefined" || opt.idval == "") {

				self.toLog("mancanza parametro id");
				return false;
			}

			if (typeof(opt.ogtit) === "undefined" || opt.ogtit == "") {

				self.toLog("mancanza parametro og:title");
				return false;
			}

			if (typeof(opt.ogurl) === "undefined" || opt.ogurl == "") {

				self.toLog("mancanza parametro og:url");
				return false;
			}

			return true;
		};

		/**
		* self.checkVal()
		*
		* controllo l'esistenza del valore passato come parametro
		*/
		self.checkVal = function(idval) {

			if (localStorage.getItem(idval) === null) {

				self.toLog("id " +idval+ " non memorizzato");
				return false;

			} else {

				self.toLog("id " +idval+ " memorizzato");
				return true;
			}
		};

		/**
		* self.addVal()
		*
		* memorizzo il valore
		*/
		self.addVal = function(idval) {

			$this.total = parseInt(self.countTot());

			if (isNaN(parseInt(opt.maxItems)) === true || (parseInt(opt.maxItems) > 0 && $this.total < opt.maxItems)) {

				localStorage.setItem($.trim(idval), JSON.stringify({
					"key": $.trim(idval),
					"id" : opt.idval,
					"name": opt.ogtit,
					"url": opt.ogurl,
					"address": opt.addr,
					"email": opt.email,
					"mid": opt.midval,
					"mname": opt.mtit,
					"murl": opt.murl
				}));

				self.recTot();
				return true;

			} else {

				self.showAlert(opt.maxItemsText);
				return false;
			}
		};

		/**
		* self.delVal()
		*
		* cancello il valore specificato
		*/
		self.delVal = function(idval) {

			$this.deferred = $.Deferred();

			// rimuovo item da localstorage
			localStorage.removeItem(idval);

			// registro il nuovo totale su LS
			rtot = self.recTot();

			if (rtot){
				$this.deferred.resolve();
			} else {
				$this.deferred.reject();
			}

			return $this.deferred.promise();
		};

		/**
		* self.removeOne()
		*
		* cancello tutti i valori. la showconfirm prende come secondo parametro una callback
		*/
		self.removeOne = function(idval) {

			var promise =  self.delVal(idval);

			promise.done(function() {
				self.drawList();
				self.showTot();
			});
		};

		/**
		* self.removeAll()
		*
		* cancello tutti i valori. la showconfirm prende come secondo parametro una callback
		*/
		self.removeAll = function(res) {

			if (res == "true") {

				$.each(localStorage, function(key,value) {

					if (key.indexOf(opt.keyPrefix) != -1) {

						localStorage.removeItem(key);
					}
				});

				// rimuovo la chiave contatore
				localStorage.removeItem(opt.counterLsKey);

				// setto a zero il contatore
				self.showTot(0);

				// ridisegno la lista
				self.drawList();

			} else {

				$this.totalVal = self.countTot();

				if ($this.totalVal > 0) {

					self.showConfirm(self.removeAll);

				} else {

					self.showAlert("Nessun record salvato");
				}
			}
		};

		/**
		* countTot()
		*
		* conto i valori registrati
		*/
		self.countTot = function() {

			$this.totalVal = 0;

			$.each(localStorage, function(key,value) {

				if ($.trim(key).indexOf(opt.keyPrefix) != -1) {

					$this.totalVal++;
				}
			});

			return $this.totalVal;
		};

		/**
		* recTot()
		*
		* registro il totale dei valori in una chiave unica
		*/
		self.recTot = function() {

			$this.total = parseInt(self.countTot());

			localStorage.setItem($.trim(opt.counterLsKey), $this.total);

			return true;
		};

		/**
		* showTot()
		*
		* mostro il totale dei valori registrati se esiste l'elemento con la classe di aggancio
		*/
		self.showTot = function(totval) {

			if (opt.counterClass != "" && $("." +opt.counterClass).length > 0) {

				if (typeof(totval) !== "undefined" && isNaN(parseInt(totval)) !== true) {

					$this.total = totval;

				} else {

					$this.total = localStorage.getItem(opt.counterLsKey);
					$this.total = $this.total !== null ? $this.total : "0";
				}

				$("." +opt.counterClass).text($this.total);

				return true;
			}
		};

		/**
		* self.toggleClass()
		*
		* aggiungo una classe css ad un elemento se il valore è presente
		*/
		self.toggleClass = function(idval) {

			if (opt.addedClass != "" && $("." +opt.elemAddClass).length > 0) {

				if (self.checkVal(idval) === true) {

					$("#" +$this.attr("id")+ " > ." +opt.elemAddClass).addClass(opt.addedClass);
					//alternativa meno flessibile
					//$('[data-idval="' +idval+ '"]' + " > ." +opt.elemAddClass).addClass(opt.addedClass);

				} else {

					$("#" +$this.attr("id")+ " > ." +opt.elemAddClass).removeClass(opt.addedClass);
					//alternativa meno flessibile
					//$('[data-idval="' +idval+ '"]' + " > ." +opt.elemAddClass).removeClass(opt.addedClass);
				}

				return true;
			}
		};

		/**
		* showAlert()
		*
		* mostra un alert custom se presente il plugin di default oppure un alert javascript
		*/
		self.showAlert = function(msgalert) {

			if(typeof(window[opt.modalPlugin]) !== "undefined") {

				modal({
					type: "info",
					title: "Attenzione!",
					text: msgalert,
					onShow: function(r) {
						setTimeout(function() {
							$("#" +r.html.attr("id")).fadeOut();
						}, opt.modalClose);
					},
					buttonText: {
						ok: "Chiudi"
					}
				});

			} else {

				alert(msgalert);
			}
		};

		/**
		* showConfirm()
		*
		* mostra un alert custom se presente il plugin di default oppure un alert javascript
		*/
		self.showConfirm = function(callback, msgalert) {

			msgalert = typeof(msgalert) !== "undefined" ? msgalert : opt.removeAllText;

			$this.deferred = $.Deferred();

			if(typeof(window[opt.modalPlugin]) !== "undefined") {

				modal({
					type: "confirm",
					title: "Attenzione!",
					text: msgalert,
					onShow: function(r) {
						setTimeout(function() {
							$("#" +r.html.attr("id")).fadeOut();
						}, opt.modalClose);
					},
					callback: function(r) {

						if (r == true) {

							if (typeof(callback) !== "undefined") {

								callback("true");
							}

							$this.deferred.resolve();

						} else {

							$this.deferred.reject();
						}
					},
					buttonText: {
						yes: 'Conferma',
						cancel: 'Annulla'
					},
				});

			} else {

				var msgconfirm = confirm(msgalert);

				if (msgconfirm) {

					callback("true");
					$this.deferred.resolve();

				} else {

					$this.deferred.reject();
				}
			}

			return $this.deferred.promise();
		};

		/**
		* drawList()
		*
		* prende un div di aggancio e inserisce il codice risultante come template
		*/
		self.drawList = function() {

			if ($("." +opt.elemListClass).length > 0) {

				//tpl = typeof($defTemplate) !== "undefined" ? $defTemplate : opt.defTemplate;
				tpl = opt.defTemplate;

				$this.output = [];
				$this.html = "";

				// unisco i dati in un unico array per ordinarli
				$.each(localStorage, function(key, value) {

					if (key.indexOf(opt.keyPrefix) != -1) {

						$this.output.push(JSON.parse(localStorage[key]));
					}
				});

				// riordino l'array dei dati su base nome e id
				$this.output.sort(self.dynamicSortMultiple("name", "id"));

				// scorro e mostro i dati
				$.each($this.output, function(key, value) {

					data = { item: {} };

					// funzione replace template
					tpl_result = tpl.replace(/\{([\w\.]*)\}/g, function(tplstr, tplkey) {

						var keys = tplkey.split(".");

						data["item"][keys[1]] = value[keys[1]];

						res = data[keys.shift()];
						res = res[keys];

						return (typeof res !== "undefined" && res !== null) ? res : "";
					});

					$this.html += tpl_result;

				});

				$("." +opt.elemListClass).html($this.html);

				// chiamo la callback
				if ($this.output.length > 0 && typeof opt.drawCallback == "function") {

					opt.drawCallback.call(this);
				}

				return true;
			}
		};

		/**
		* dynamicSort()
		*
		* riordina un array per la proprietà passata
		*/
		self.dynamicSort = function(prop) {

			var sortOrder = 1;

			if(prop[0] === "-") {
				sortOrder = -1;
				prop = prop.substr(1);
			}

			return function (a,b) {
				var res = (a[prop] < b[prop]) ? -1 : (a[prop] > b[prop]) ? 1 : 0;
				return res * sortOrder;
			}
		};

		/**
		* dynamicSortMultiple()
		*
		* riordina un array per le proprietà passate. aggiungendo il carattere "-" (meno) davanti al nome si inverte l'ordine
		*/
		self.dynamicSortMultiple = function() {

			var props = arguments;

			return function (obj1, obj2) {

				var i = 0, res = 0;
				var total_props = props.length;

				while(res === 0 && i < total_props) {

					res = self.dynamicSort(props[i])(obj1, obj2);
					i++;
				}

				return res;
			}
		};
		/** FINE FUNZIONI **/

		var $this = $(this);

		// init per mostrare totale record se è presente il contatore di aggancio
		self.init($this);

		// onclick sull'elemento
		$this.click(function() {

			// se non c'è supporto a localstorage blocco
			if (self.supportStorage("Spiacenti! Questa funzionalità non è supportata dal tuo browser") === false) return false;

			// controllo esistenza dei metatag necessari
			if (self.checkMetaData($this) === false) {

				self.showAlert("Si è verificato un errore! Impossibile completare il salvataggio");
				return false;
			};

			// se il valore non esiste lo inserisco
			if (self.checkVal(opt.idkey) !== true) {

				self.addVal(opt.idkey);
				self.toLog("inserimento id " +opt.idval);

			} else {

				self.delVal(opt.idkey);
				self.toLog("cancellazione id " +opt.idval);
			}

			// aggiungo la classe css se configurata
			self.toggleClass(opt.idkey);

			// mostro il totale degli item
			self.showTot();
		});

		return self;
	};

	// parametri esterni al plugin per permettere override dei parametri nei vari metodi
	$.fn.bookmark.defParams = {
		keyPrefix: location.host+ "-",
		maxItems: 100,
		maxItemsText: "È stato raggiunto il numero massimo di record salvati",
		elemAddClass: "sbm-addclass",
		addedClass: "sc-saved",
		elemListClass: "sbm-list",
		counterLsKey: "sbm-total",
		counterClass: "sbm-counter",
		modalPlugin: "modal",
		modalClose: 15000,
		removeAllText: "Confermi la cancellazione di tutti i preferiti?",
		defTemplate: "\n<tr>\n<td><a href=\"{item.url}\">{item.name} {item.mtitle}</a>\n</td>\n<td><a href=\"mailto:{item.email}\">{item.email}</a>\n</td>\n<td><a href=\"javascript:$.fn.bookmark().removeOne('{item.key}');\">cancella</a></td>\n</tr>",
		noSupportText: "Spiacenti, la funzione non è supporta",
		drawCallback: "",
		isDebug	: false
	}

})(jQuery);