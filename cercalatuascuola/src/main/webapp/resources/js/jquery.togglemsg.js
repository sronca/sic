// Filename:	jquery.togglemsg.js
//
// Created: 	17/09/2015 (14:20:22)
// Created by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
//
// Last Updated:	24/09/2015 (14:20:22)
// Updated by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
//
// Copyleft:	2015 - Tutti i diritti riservati
//
// Comments: migrazione a script js.cookie
(function($) {

	$.fn.msgtoggle = function(customOptions) {

		var defParams = {
			cookieName: "mt_cookie",
			cookieExpire: 1,
			classContent: "elemhide",
			classClose: "mt_closelink",
			idDiscard: "edil-discard",
			autoClose: 0,
			endCallback: "",
			execEveryTime: "y",
			isDebug: false
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
		* init sui controlli dei div
		*/
		self.init = function() {

			// enter se il cookie esiste
			if (self.isCookie() === true) {

				self.toggleContent("show", opt.execEveryTime);

			} else {

				// nascondo i div se non è stato fatto via css. in js è più lento ma è un check di sicurezza
				$.each($("." +opt.classContent), function() {

					if ($(this).is(":visible") == true) {

						$(this).css("display", "none");
					}
				});

				// autoclose
				if (opt.autoClose > 0) {
					setTimeout(function() {
						self.toggleContent("show", opt.execEveryTime);
					}, opt.autoClose);
				};
			}
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
		* setCookie()
		*
		* imposta il cookie con i nuovi valori
		*/
		self.setCookie = function(value) {

			$.cookie(opt.cookieName, value, { expires: opt.cookieExpire, path: '/' });
		};

		/**
		* showContent()
		*
		* mostra il contenuto e nascondo l'avviso
		*/
		self.toggleContent = function(type, cb_exec) {

			type = typeof(type) !== "undefined" ? type : "show";
			cb_exec = typeof(cb_exec) !== "undefined" ? cb_exec : "n";

			if (type == "show") {

				$thismsg.css("display", "none");
				$("." +opt.classContent).show();

			} else {

				$("." +opt.classContent).css("display", "none");
				$thismsg.show();
			}

			// chiamo la callback
			if (cb_exec == "y" && typeof(opt.endCallback) == "function") {

				opt.endCallback.call(this);
			}
		};

		var $thismsg = $(this);

		// se esiste il contenitore da mostrare
		if ($thismsg.length > 0) {

			self.init();

			// onclick sull'elemento
			$("." + opt.classClose).click(function() {

				if ($("#" +opt.idDiscard).prop('checked') == true) {

					self.setCookie("discard");
				}

				self.toggleContent("show","y");
			});
		}

		return self;
	};
})(jQuery);