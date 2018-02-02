// Filename:	jquery.jssocials-1.1.js
//
// Created: 	20/02/2016 (11:24:34)
// Created by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
//
// Last Updated:	20/02/2016 (11:24:34)
// Updated by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
//
// Copyleft:	2016 - Tutti i diritti riservati
// Fork from: http://js-socials.com / Copyright (c) 2015 Artem Tabalin; Licensed MIT
(function(window, $, undefined) {

	jQuery.support.cors = true;

    var JSSOCIALS = "JSSocials",
        JSSOCIALS_DATA_KEY = JSSOCIALS;

    var getOrApply = function(value, context) {
        if($.isFunction(value)) {
            return value.apply(context, $.makeArray(arguments).slice(2));
        }
        return value;
    };

    var IMG_SRC_REGEX = /(\.(jpeg|png|gif|bmp)$|^data:image\/(jpeg|png|gif|bmp);base64)/i;
    var URL_PARAMS_REGEX = /(&?[a-zA-Z0-9]+=)\{([a-zA-Z0-9]+)\}/g;
    var FIELD_SUBSTITUTION_REGEX = /\{([a-zA-Z0-9]+)\}/g;

    //~ var MEASURES = {
        //~ "G": 1000000000,
        //~ "M": 1000000,
        //~ "K": 1000
    //~ };

    var shares = [];

    function Socials(element, config) {
        var $element = $(element);

        $element.data(JSSOCIALS_DATA_KEY, this);

        this._$element = $element;

        this.shares = [];

        this._init(config);
        this._render();
    }

    Socials.prototype = {
		text: "",
        url: "",
        // path globale dei loghi
        logoPath: "",
        // lunghezza minima del bottone
        buttonMinWidth: "",
        // decimali da visualizzare
        fixedNumber: 0,
        // abilito/disabilito il contatore
        showCounter: true,
        // escludo il contatore interno indipendentemente da qualsiasi layout scelto
        showCounterInside: true,
        // soglia minima per visualizzare il contatore. lasciare zero per visualizzare qualsiasi valore
        minShowCount: 0,
		// aggiungo opzioni per la visualizzazione del contatore
        layoutCount: "right",
        // imposto come parametro le unità di misura
        measures: {"mld": 1000000000, "mln": 1000000, "mila": 1000},

        showLabel: function(screenWidth) {
            return (this.showCount === false) ?
                (screenWidth > this.smallScreenWidth) :
                (screenWidth >= this.largeScreenWidth);
        },

        showCount: function(screenWidth) {
            return (screenWidth <= this.smallScreenWidth) ? "inside" : true;
        },

        smallScreenWidth: 600,
        largeScreenWidth: 1024,
        resizeTimeout: 200,
        elementClass: "jssocials",
        sharesClass: "jssocials-shares",
        shareClass: "jssocials-share",
        shareButtonClass: "jssocials-share-button",
        shareLinkClass: "jssocials-share-link",
        shareLogoClass: "jssocials-share-logo",
        shareLabelClass: "jssocials-share-label",
        shareLinkCountClass: "jssocials-share-link-count",
        shareLinkCountClassInside: "jssocials-share-link-count-inside",
        shareCountBoxClass: "jssocials-share-count-box",
        shareCountBoxClassTop: "jssocials-share-count-box-top",
        shareCountClass: "jssocials-share-count",
        shareZeroCountClass: "jssocials-share-no-count",

        _init: function(config) {
            this._initDefaults();
            $.extend(this, config);
            this._initShares();
            this._attachWindowResizeCallback();
        },

        _initDefaults: function() {
            //~ this.url = window.location.href;
            //~ this.text = $.trim($("meta[name=description]").attr("content") || $("title").text());
			// cambio la modalità di acquisizione dei dati
            this.text = $("meta[property=\"og:title\"]").attr("content") || $("title").text();
            this.url = $("meta[property=\"og:url\"]").attr("content") || window.location.href;
            //
            this.tandu = this.text+ " " +this.url;
        },

        _initShares: function() {
            this.shares = $.map(this.shares, $.proxy(function(shareConfig) {
                if(typeof shareConfig === "string") {
                    shareConfig = { share: shareConfig };
                }

                var share = (shareConfig.share && shares[shareConfig.share]);

                if(!share && !shareConfig.renderer) {
                    throw Error("Share '" + shareConfig.share + "' is not found");
                }

				//
                return $.extend({ url: this.url, text: this.text, tandu: this.tandu }, share, shareConfig);
            }, this));
        },

        _attachWindowResizeCallback: function() {
            $(window).on("resize", $.proxy(this._windowResizeHandler, this));
        },

        _detachWindowResizeCallback: function() {
            $(window).off("resize", this._windowResizeHandler);
        },

        _windowResizeHandler: function() {
            if($.isFunction(this.showLabel) || $.isFunction(this.showCount)) {
                window.clearTimeout(this._resizeTimer);
                this._resizeTimer = setTimeout($.proxy(this.refresh, this), this.resizeTimeout);
            }
        },

        _render: function() {
            this._clear();

            this._defineOptionsByScreen();

            this._$element.addClass(this.elementClass);

            this._$shares = $("<div>").addClass(this.sharesClass)
                .appendTo(this._$element);

            this._renderShares();
        },

        _defineOptionsByScreen: function() {
            this._screenWidth = $(window).width();
            this._showLabel = getOrApply(this.showLabel, this, this._screenWidth);
            this._showCount = getOrApply(this.showCount, this, this._screenWidth);
        },

        _renderShares: function() {
            $.each(this.shares, $.proxy(function(_, share) {
                this._renderShare(share);
            }, this));
        },

        _renderShare: function(share) {
            var $share;

            if($.isFunction(share.renderer)) {
                $share = $(share.renderer());
            } else {
                $share = this._createShare(share);
            }

            $share.addClass(this.shareClass)
                .addClass(share.share ? "jssocials-share-" + share.share : "")
                .addClass(share.css)
                .appendTo(this._$shares);

            // aggiungo misura lunghezza minima dei bottoni
            if (this.buttonMinWidth != "") {

				$("." +this.shareLinkClass).css("min-width", this.buttonMinWidth);
			}

			// controllo per mostrare whatsapp solo su mobile
			if (share.share == "whatsapp" && navigator.userAgent.match(/Android|iPhone|iPod|iPad|iemobile/i)) {

				$(".jssocials-share-" +share.share).css("display", "inline-block");
			}
        },

        _createShare: function(share) {
            var $result = $("<div>");
            var $shareLink = this._createShareLink(share).appendTo($result);

			// per problemi di compatibilità con il contatore su IE8, aggiunto controllo capabilty
            if(this._showCount && document.addEventListener) {

				// var di controllo per escludere il contatore interno
				var showInside = true;
                var isInsideCount = (this._showCount === "inside");
                //~ var $countContainer = isInsideCount ? $shareLink : $("<div>").addClass(this.shareCountBoxClass).appendTo($result);
                //~ $countContainer.addClass(isInsideCount ? this.shareLinkCountClass : this.shareCountBoxClass);
                //~ this._renderShareCount(share, $countContainer);

                // controllo la posizione del contatore
				if (this.showCounter == true) {

					if (isInsideCount == true || this.layoutCount == "inside") {

						var $countContainer = $shareLink;
						var shareLinkCountClass = this.shareLinkCountClassInside;

						// escludo il contatore intero in modalità mobile
						if (this.showCounterInside == false) {

							var showInside = false;
						}

					} else {

						if (this.layoutCount == "right" || this.layoutCount == "only-right") {

							var $countContainer = $("<div>").addClass(this.shareCountBoxClass).appendTo($result);
							var shareLinkCountClass = this.shareCountBoxClass;

						} else {

							var $countContainer = $("<div>").addClass(this.shareCountBoxClassTop).prependTo($result);
							var shareLinkCountClass = this.shareCountBoxClassTop;
						}
					}
					// controllo per la posizione del contatore

					if (showInside === true) {

						$countContainer.addClass(shareLinkCountClass);
						this._renderShareCount(share, $countContainer);
					}
				}
            }

            return $result;
        },

        _createShareLink: function(share) {
            var $result = $("<a>").addClass(this.shareLinkClass)
                .attr({ href: this._getShareUrl(share), target: "_blank" })
                .append(this._createShareLogo(share));

            $.each(this.on || {}, function(event, handler) {
                if($.isFunction(handler)) {
                    $result.on(event, $.proxy(handler, share));
                }
            });

            if(this._showLabel) {
                $result.append(this._createShareLabel(share));
            }

            return $result;
        },

        _getShareUrl: function(share) {
            var shareUrl = getOrApply(share.shareUrl, share);
            return this._formatShareUrl(shareUrl, share);
        },

        _createShareLogo: function(share) {
            var logo = share.logo;

            // se impostato logoPath aggiungo il percorso all'immagine
            pathLogo = (this.logoPath != "") ? this.logoPath+ "/" +share.logo : share.logo;

            var $result = IMG_SRC_REGEX.test(logo) ? $("<img>").attr("src", pathLogo) : $("<i>").addClass(logo);

            $result.addClass(this.shareLogoClass);

            return $result;
        },

        _createShareLabel: function(share) {
            return $("<span>").addClass(this.shareLabelClass)
                .text(share.label);
        },

        _renderShareCount: function(share, $container) {
            var $count = $("<span>").addClass(this.shareCountClass);

            $container.addClass(this.shareZeroCountClass)
                .append($count);

			// se disabilitato rimuovo lo span del contatore per evitare spazi inutili
            this._loadCount(share).done($.proxy(function(count) {
                if(count) {
                    $container.removeClass(this.shareZeroCountClass);
                    $count.text(count);
                } else {
					$count.remove();
				}
            }, this));
        },

        _loadCount: function(share) {
            var deferred = $.Deferred();
            var countUrl = this._getCountUrl(share);

            if(!countUrl) {
                return deferred.resolve(0).promise();
            }

            var handleSuccess = $.proxy(function(response) {
                deferred.resolve(this._getCountValue(response, share));
            }, this);

			// vecchio codice non supportato da ie9
            //~ $.getJSON(countUrl).done(handleSuccess)
                //~ .fail(function() {
                    //~ $.get(countUrl).done(handleSuccess)
                        //~ .fail(function() {
                            //~ deferred.resolve(0);
                        //~ });
                //~ });

			// nuovo codice per supporto a IE9
            $.ajax({
				crossDomain: true,
				url: countUrl,
				dataType: "jsonp"
			}).done(handleSuccess)
			.fail(function() {
				$.ajax({
					crossDomain: true,
					url: countUrl,
					dataType: "jsonp"
				}).done(handleSuccess)
				.fail(function() {
					deferred.resolve(0);
				});
			});
			// nuovo codice per supporto a IE9

			return deferred.promise();
        },

        _getCountUrl: function(share) {
            var countUrl = getOrApply(share.countUrl, share);
            return this._formatShareUrl(countUrl, share);
        },

        _getCountValue: function(response, share) {

            var count = ($.isFunction(share.getCount) ? share.getCount(response) : response) || 0;
            return (typeof count === "string") ? count : this._formatNumber(count);

        },

        _formatNumber: function(number) {

			// controllo sulla soglia minima
			if (number >= this.minShowCount) {

				$.each(this.measures, function(letter, value) {
					if(number >= value) {
						number = parseFloat((number / value).toFixed(this.fixedNumber)) + letter;
						return false;
					}
				});

			} else {

				number = 0;
			}

            return number;
        },

        _formatShareUrl: function(url, share) {
            url = url.replace(URL_PARAMS_REGEX, function(match, key, field) {
                var value = share[field] || "";
                return value ? (key + window.encodeURIComponent(value)) : "";
            });

            return url.replace(FIELD_SUBSTITUTION_REGEX, function(match, field) {
                var value = share[field] || "";
                return value ? window.encodeURIComponent(value) : "";
            });
        },

        _clear: function() {
            window.clearTimeout(this._resizeTimer);
            this._$element.empty();
        },

        _passOptionToShares: function(key, value) {
            var shares = this.shares;

            $.each(["url", "text"], function(_, optionName) {
                if(optionName !== key)
                    return;

                $.each(shares, function(_, share) {
                    share[key] = value;
                });
            });
        },

        _normalizeShare: function(share) {
            if($.isNumeric(share)) {
                return this.shares[share];
            }

            if(typeof share === "string") {
                return $.grep(this.shares, function(s) {
                    return s.share === share;
                })[0];
            }

            return share;
        },

        refresh: function() {
            this._render();
        },

        destroy: function() {
            this._clear();
            this._detachWindowResizeCallback();

            this._$element
                .removeClass(this.elementClass)
                .removeData(JSSOCIALS_DATA_KEY);
        },

        option: function(key, value) {
            if(arguments.length === 1) {
                return this[key];
            }

            this[key] = value;

            this._passOptionToShares(key, value);

            this.refresh();
        },

        shareOption: function(share, key, value) {
            share = this._normalizeShare(share);

            if(arguments.length === 2) {
                return share[key];
            }

            share[key] = value;
            this.refresh();
        }

    };

    $.fn.jsSocials = function(config) {
        var args = $.makeArray(arguments),
            methodArgs = args.slice(1),
            result = this;

        this.each(function() {
            var $element = $(this),
                instance = $element.data(JSSOCIALS_DATA_KEY),
                methodResult;

            if(instance) {
                if(typeof config === "string") {
                    methodResult = instance[config].apply(instance, methodArgs);
                    if(methodResult !== undefined && methodResult !== instance) {
                        result = methodResult;
                        return false;
                    }
                } else {
                    instance._detachWindowResizeCallback();
                    instance._init(config);
                    instance._render();
                }

            } else {
                new Socials($element, config);
            }
        });

        return result;
    };

    var setDefaults = function(config) {
        var component;

        if($.isPlainObject(config)) {
            component = Socials.prototype;
        } else {
            component = shares[config];
            config = arguments[1] || {};
        }

        $.extend(component, config);
    };

    window.jsSocials = {
        Socials: Socials,
        shares: shares,
        setDefaults: setDefaults
    };

}(window, jQuery));

(function(window, $, jsSocials, undefined) {

    $.extend(jsSocials.shares, {

        whatsapp: {
            label: "WhatsApp",
            logo: "fa fa-whatsapp",
            shareUrl: "whatsapp://send?text={url} {text}",
            countUrl: ""
        },

        email: {
            label: "Email",
            logo: "fa fa-at",
            shareUrl: "mailto:?subject={text}&body={url}",
            countUrl: ""
        },

        twitter: {
            label: "Tweet",
            logo: "fa fa-twitter",
            shareUrl: "https://twitter.com/share?url={url}&text={text}&via={via}&hashtags={hashtags}",
            countUrl: ""
        },

        facebook: {
            label: "Condividi",
            logo: "fa fa-facebook",
            shareUrl: "https://facebook.com/sharer/sharer.php?u={url}",
			countUrl: function() {
				return "http://graph.facebook.com/?id=" + window.encodeURIComponent(this.url);
            },
            getCount: function(data) {

				if (typeof(data.error) !== "undefined" && typeof(console.log) !== "undefined") {

					console.log(data.error.message);
				}

				return (typeof(data.share) !== "undefined") ? data.share.share_count : 0;
            },
        },

        googleplus: {
            label: "+1",
            logo: "fa fa-google-plus",
            shareUrl: "https://plus.google.com/share?url={url}",
            countUrl: function() {
                return "https://cors-anywhere.herokuapp.com/https://plusone.google.com/_/+1/fastbutton?url="+ window.encodeURIComponent(this.url);
            },
            getCount: function(data) {
                return parseFloat((data.match(/\{c: ([.0-9E]+)/) || [])[1]);
            }
        },

        linkedin: {
            label: "Linkedin",
            logo: "fa fa-linkedin",
            shareUrl: "https://www.linkedin.com/shareArticle?mini=true&url={url}",
            countUrl: "https://www.linkedin.com/countserv/count/share?format=jsonp&url={url}&callback=?",
            getCount: function(data) {
                return data.count;
            }
        },

        pinterest: {
            label: "Pin it",
            logo: "fa fa-pinterest",
            shareUrl: "https://pinterest.com/pin/create/bookmarklet/?media={media}&url={url}&description={text}",
            countUrl: "https://api.pinterest.com/v1/urls/count.json?&url={url}&callback=?",
            getCount: function(data) {
                return data.count;
            }
        }
    });
}(window, jQuery, window.jsSocials))