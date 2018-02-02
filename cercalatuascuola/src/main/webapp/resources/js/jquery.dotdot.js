/*
 * saibal-dotdotdot.js
 *
 * Created:	03/07/2015 (15:43:14)
 * Created by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
 *
 * Copyleft:	2015 - Tutti i diritti riservati
 * Comments:
 */
(function($) {

	$.fn.dotdot = function(customOptions) {

		var defParams = {
			wrapWidth: 20,
			wrapWidthTablet: 85,
			wrapWidthSmart: 60,
			wrapClass: "chart-explain",
			ellipClass: "ellips",
			switchClass: "switch-ellips",
			moreText: "Leggi tutto",
			lessText: "Nascondi",
			isDebug	: false
		}

		var self = {};

		var opt = $.extend(defParams, customOptions);

		//**********************************
		// funzione log
		//**********************************
		self.toLog = function(msg, type) {
			if (opt.isDebug) {
				type ? alert(msg) : (window.console && window.console.log ? window.console.log(msg) : null);
			}
		};

		self.init = function() {

			// lunghezza del viewport. utile per i breakpoint
			winWidth = $(window).width();

			self.toLog("lunghezza viewport: " +$(window).width());
			self.toLog("limite percentuale default: " +opt.wrapWidth);

			// per ogni blocco ellips controllo la lunghezza originale
			$( "." + opt.wrapClass).each(function() {

				obj = $(this).children("p." +opt.ellipClass);

				// ottengo la lunghezza percentuale di ogni blocco a partire dalla sua lunghezza originale in pixel
				objPerCent = (($(this).children().outerWidth(true) / winWidth) * 100).toFixed(0);

				self.toLog("percentuale lunghezza originale div: " +objPerCent);

				// smartphone breakpoint
				if (winWidth < 768) {
					opt.wrapWidth = opt.wrapWidthSmart;
				}

				// tablet breakpoint
				if (winWidth >= 768 && winWidth <= 1024) {
					opt.wrapWidth = opt.wrapWidthTablet;
				}

				wrapResult = ((opt.wrapWidth * winWidth) / 100).toFixed(0);

				// se il div supera il limite impostato aggiungo i link "more"
				if (parseFloat(objPerCent) > parseFloat(opt.wrapWidth)) {

					$(this).append("<span class=\"dotdot-more\">[ <a href=\"javascript:void(0)\">" +opt.moreText+ "</a> ]</span>");
					$(this).append("<span class=\"dotdot-close\">[ <a href=\"javascript:void(0)\">" +opt.lessText+ "</a> ]</span>");

					// accorcio i paragrafi che superano i limiti
					obj.css("width", wrapResult+ "px");
					// aggiungo la classe per tagliare il testo
					obj.addClass(opt.switchClass);
				}

				// registro la lunghezza originale
				if (obj.data("origwidth") == "") {

					// setto il valore come data-*
					obj.data("origwidth", wrapResult);
				}

				// mostro tutti i div
				obj.show();
			});
		};

		$(this).click(function(event) {

			obj = $(this).children("p");

			if (parseFloat(obj.data("origwidth")) > parseFloat(opt.wrapWidth)) {

				if (obj.hasClass(opt.switchClass)) {

					obj.css("width", "auto");
					obj.css("display", "block");
					obj.removeClass(opt.switchClass);
					$(this).children("span").toggle();

				} else {

					obj.css("width", obj.data("origwidth") + "px");
					obj.addClass(opt.switchClass);
					$(this).children("span").toggle();
				}
			}

			event.stopImmediatePropagation();
		});

		self.init();

		return self;
	};
})(jQuery);