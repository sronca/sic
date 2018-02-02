(function($) {

	/**
	* $.fn.autoCompleteAddon
	*
	* copia il valore della selezione del plugin autocomplete dentro un campo hidden
	*/
	$.fn.autoCompleteAddon = function(customOptions, fnMethods) {

		this.defParams = {
			elem: "",
			suffix: "_autocomplete",
			type: "hidden",
			name: "",
			value: "",
			keypressed: "",
			keyprevent: "9, 13",
			debug: false
		}

		this.opt = $.extend(true, {}, this.defParams, customOptions);

		if (typeof(this.opt.elem) == "object" && this.opt.elem != "") {

			// ottengo l'id del clone a partire dall'id originario
			originId = $(this.opt.elem).attr("id");
			cloneId	= originId + this.opt.suffix;

			var methods = {

				init : function() {

					$($.parseHTML('<input>')).attr({
						id: cloneId,
						name: cloneId,
						value: this.opt.value,
						type: this.opt.type

					}).insertAfter('#'+originId);
				},

				remove : function() {

					// trasformo l'array e li mappo come valori numerici
					var myarray = this.opt.keyprevent.split(",").map(function(item) {
						return parseInt(item, 10);
					});

					// rimuovo l'elemento hidden solo se non è stato premuto tab o altro tasto nell'arayy esclusione
					if ($("#" +cloneId).length && $.inArray(this.opt.keypressed, myarray) === -1 ) {

						$("#" +cloneId).remove();
					}
				},

				// reset del campo al caricamento della pagina
				reset : function() {

					if ($("#" +originId).length) {

						$("#" +originId).val("");
					}
				}
			};

			if (methods[fnMethods]) {

				return methods[fnMethods].apply(this, Array.prototype.slice.call(arguments, 1));

			} else if (typeof fnMethods === "object" || !fnMethods) {

				// default impostato per "init"
				return methods.init.apply(this, Array.prototype.slice.call(arguments, 1));

			} else {

				if (this.opt.debug === true) {

					console.log("Il metodo " +  fnMethods + "non esiste");
				}
			}

		}
    };
})( jQuery );

$(document).ready(function()
{

	var $root = $("html, body");
	var ie8 = 0;

    $('.parallax').scrolly({bgParallax: true});

    if (!jQuery.support.leadingWhitespace) {
		var imgbanner = $('.parallax');
        imgbanner.addClass("img-banner-ie8");
        var ie8 = 1;
    };

    $(window).scroll(function () {

        var navcont        =  $('.nav-container');
        var header         =  $('header');
        var logo           =  $('.logo-small-position');

        if ($(this).scrollTop() > 70) {

            header.addClass("header-margin-bottom");
            navcont.addClass("f-nav");

            if(!ie8) {
                logo.fadeIn(500);
            } else {
				logo.removeClass("hidden");
				logo.addClass("show");
			}
        } else {
            header.removeClass("header-margin-bottom");
            navcont.removeClass("f-nav");

            if(!ie8) {
                logo.hide();
            } else {
                logo.removeClass("show");
                logo.addClass("hidden");
            }
        }
    });

    $(".item-menu").on('click', function() {

        if (!$(this).hasClass("menu-active")) {

            $(".item-sub-menu").hide();
            $(".item-menu").removeClass("menu-active");
            $(".link-menu").removeClass("link-menu-active");
            $(this).addClass("menu-active");
            $(this).children(".link-menu").children("span").addClass("arrow-on");
            $(this).children(".link-menu").addClass("link-menu-active");

			if ($("#menu-responsive").is(":visible")) {

				var numItems = $('.open').length;

				$(".item-sub-menu").removeClass("open");


				if (numItems>1) {
					$(this).children(".item-sub-menu").fadeIn();
					$(this).children(".item-sub-menu").delay(400).addClass("open");
				} else {
					$(this).children(".item-sub-menu").addClass("open");
				}
			} else {
				$(this).children(".item-sub-menu").fadeIn();
			}

        } else {

            $(this).removeClass("menu-active");
            $(this).children(".link-menu").removeClass("link-menu-active");

            if ($("#menu-responsive").is(":visible")) {

                $(this).children(".item-sub-menu").removeClass("open");
				$(this).children(".item-sub-menu").hide();

            } else {

                $(".item-sub-menu").hide();
            }
        }
    });

    $('html').click(function (event) {

        if (!$(event.target).is("a.link-menu.link-menu-active") && !$(event.target).is("span.arrow-on")) {
            $(".item-sub-menu").fadeOut();
            $(".link-menu").removeClass("link-menu-active");
            $(".link-menu").children("span").removeClass("arrow-on");
            $(".item-menu").removeClass("menu-active");
        }
    });

    $(".accordion-faq-question").click(function() {

        var q = "#"+$(this).attr("data");

        if ($(q).children(".accordion-faq-answer").hasClass("faq-open")) {
            $(q).children(".accordion-faq-question").removeClass("accordion-faq-question-open");
            $(q).children(".accordion-faq-answer").removeClass("faq-open");
			$(q).children(".accordion-faq-answer").removeClass("open");

        } else {

            $(q).children(".accordion-faq-question").addClass("accordion-faq-question-open");
            $(q).children(".accordion-faq-answer").addClass("faq-open");
            $(q).children(".accordion-faq-answer").addClass("open");
        }
    });

    $("#menu-responsive").on('click', function() {

        if ($("#menu").hasClass("open")) {
            $("#menu").removeClass("open");
			$("#header").removeClass("no-fixed");
			$("#menu-responsive").removeClass("no-fixed");
			$("#breadcrumb").removeClass("margin-mobile");

        } else {

            $("#menu").addClass("open");
			$("#header").addClass("no-fixed");
			$("#breadcrumb").addClass("margin-mobile");
			$("#menu-responsive").addClass("no-fixed");
			window.scrollTo(0,0);
        }
    });

	$('.modal-popup').magnificPopup({
		type: 'iframe',
		removalDelay: 300,
		mainClass: 'mfp-fade',
		iframe: {
			markup: '<div class="mfp-iframe-scaler">'+
			'<div class="mfp-close"></div>'+
			'<iframe class="mfp-iframe" frameborder="0" allowfullscreen></iframe>'+
			'</div>',

			patterns: {
				youtube: {
					index: 'youtube.com/',

					id: 'v=',

					src: '//www.youtube.com/embed/%id%?autoplay=1'
				},
				vimeo: {
					index: 'vimeo.com/',
					id: '/',
					src: '//player.vimeo.com/video/%id%?autoplay=1'
				},
				gmaps: {
					index: '//maps.google.',
					src: '%id%&output=embed'
				}
			},

			srcAction: 'iframe_src'
		}
	});

	$(document).trigger("enhance.tablesaw");

	// gestisco la lista delle àncore
	$("div.block-glossary a").on("click", function(e) {

		// blocco il caricamento se la lettera premuta è quella attuale
		if (window.location.hash == "#" +$(this).data("anchor")) {

			return false;
		}

		if (!$(this).hasClass("disable")) {

			// oggetto target preso dall'attributo data
			var aTag = $("a[id='" +$(this).data("anchor")+ "']");

			if (typeof(aTag.offset()) !== "undefined") {

				$root.animate({scrollTop: aTag.offset().top-90}, 2000);

				if (history.pushState) {

					e.preventDefault();
					history.pushState({}, "", "#" +$(this).data("anchor"));

				} else {

					$(this).prop("href", "#" +$(this).data("anchor"));
				}

			} else {

				// evito che si azioni il comportamento di default delle àncore
				e.preventDefault();
			}
		}
	});

	// creo l'oggetto select e aggiungo la classe css necessaria
    $("<select />").appendTo("div.block-glossary").addClass("select-letter");

	// creo il valore di default
	$($.parseHTML("<option>")).attr({
		value: "null",
		selected: true
	}).html("Seleziona una lettera").appendTo("select.select-letter");

	// creo la lista delle option a partire dai valori della lista ul/li
	$("div.block-glossary > ul > li > a").each(function() {

		var el = $(this);
		var eldisable = false;

		if (el.hasClass("disable")) {

			eldisable = true;
		}

		$($.parseHTML("<option>")).attr({
			value: el.data("anchor"),
			disabled: eldisable
		}).html(el.text().toString().toUpperCase()).appendTo("select.select-letter");
	});

	// seleziono il valore della select e se esiste la corrispettiva àncora attivo il movimento
	$("select.select-letter").change(function(e) {

		var aTag = $("a[id='" +$(this).val()+ "']");

		if (typeof(aTag.offset()) !== "undefined") {

			if (history.pushState) {

				e.preventDefault();
				history.pushState({}, "", "#" +$(this).val());
			}

			$root.animate({scrollTop: aTag.offset().top-90}, 2000);
		}
	});

	/**
	* jump menu su select
	*/
	$("#map-area").change(function() {

		if ($(this).val() != "" && $(this).data("qstring")) {

			var qstring = ($(this).data("qstring") != "") ? $(this).data("qstring")+ "=" : "";
			var hash	= ($(this).data("hash") != "") ? "#" +$(this).data("hash") : "";

			window.location = window.location.pathname+ '?' +qstring+ $(this).val() +hash;
		}
	})

	// delegate calls to data-toggle="lightbox"
	$(document).delegate('*[data-toggle="lightbox"]:not([data-gallery="navigateTo"])', "click", function(event) {

		if (Function.prototype.bind === undefined) {

			window.open($(this).attr("href"), "_blank");
			return false;
		}

		event.preventDefault();

		return $(this).ekkoLightbox({
			onShown: function() {
				if (window.console) {
					return console.log("Checking our the events huh?");
				}
			},
			onNavigate: function(direction, itemIndex) {
				if (window.console) {
					return console.log("Navigating "+direction+". Current item: "+itemIndex);
				}
			}
		});
	});

	//Programatically call
	$('#open-image').click(function (e) {
		e.preventDefault();
		$(this).ekkoLightbox();
	});
	$('#open-youtube').click(function (e) {
		e.preventDefault();
		$(this).ekkoLightbox();
	});

	// navigateTo
	$(document).delegate('*[data-gallery="navigateTo"]', "click", function(event) {
		event.preventDefault();
		var lb;
		return $(this).ekkoLightbox({
			onShown: function() {

				lb = this;

				$(lb.modal_content).on("click", ".modal-footer a", function(e) {

					e.preventDefault();
					lb.navigateTo(2);
				});
			}
		});
	});
});