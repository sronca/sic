/*
 * cookieconsent.js
 *
 * Created:	28/06/2015 (18:16:11)
 * Created by: 	Lorenzo Saibal Forti <saibal@lorenzone.it>
 *
 * Copyleft:	2015 - Tutti i diritti riservati
 * Comments: original idea from https://github.com/silktide/cookieconsent2
 */
$(document).ready(function() {

    if (!window.hasCookieConsent) {

        window.hasCookieConsent = !0;
        var e = "cookieconsent_options",
            t = "update_cookieconsent_options",
            n = "cookieconsent_dismissed",
            i = "";

        n = window.cookieconsent_options.cookiename ? window.cookieconsent_options.cookiename : n;

        if (!(document.cookie.indexOf(n) > -1)) {

            "function" != typeof(String.prototype.trim) && (String.prototype.trim = function() {
                return this.replace(/^\s+|\s+$/g, "")
            });

            var o, s = {

				isArray: function(e) {
					var t = Object.prototype.toString.call(e);
					return "[object Array]" == t
				},
				isObject: function(e) {
					return "[object Object]" == Object.prototype.toString.call(e)
				},
				each: function(e, t, n, i) {
					if (s.isObject(e) && !i)
						for (var o in e) e.hasOwnProperty(o) && t.call(n, e[o], o, e);
					else
						for (var r = 0, c = e.length; c > r; r++) t.call(n, e[r], r, e)
				},
				merge: function(e, t) {
					e && s.each(t, function(t, n) {
						s.isObject(t) && s.isObject(e[n]) ? s.merge(e[n], t) : e[n] = t
					})
				},
				bind: function(e, t) {
					return function() {
						return e.apply(t, arguments)
					}
				},
				queryObject: function(e, t) {
					var n, i = 0,
						o = e;
					for (t = t.split(".");
						(n = t[i++]) && o.hasOwnProperty(n) && (o = o[n]);)
						if (i === t.length) return o;
					return null
				},
				setCookie: function(e, t, n) {
					var i = new Date;
					n = n || 365, i.setDate(i.getDate() + n);
					if (c.options.allowdomain) {
						var domain = location.hostname.split(".").slice(-2).join(".");
					} else {
						var domain = "";
					}
					document.cookie = e + "=" + t + "; expires=" + i.toUTCString() + "; path=/; domain=" + domain + ";"
				},
				addEventListener: function(e, t, n) {
					e.addEventListener ? e.addEventListener(t, n) : e.attachEvent("on" + t, n)
				}
			},
			r = function() {
				var e = "data-cc-event",
					t = "data-cc-if",
					n = function(e, t, i) {
						return s.isArray(t) ? s.each(t, function(t) {
							n(e, t, i)
						}) : void(e.addEventListener ? e.addEventListener(t, i) : e.attachEvent("on" + t, i))
					},
					i = function(e, t) {
						return e.replace(/\{\{(.*?)\}\}/g, function(e, n) {
							for (var i, o = n.split("||"); token = o.shift();) {
								if (token = token.trim(), '"' === token[0]) return token.slice(1, token.length - 1);
								if (i = s.queryObject(t, token)) return i
							}
							return ""
						})
					},
					o = function(e) {
						var t = document.createElement("div");
						return t.innerHTML = e, t.children[0]
					},
					r = function(e, t, n) {
						var i = e.parentNode.querySelectorAll("[" + t + "]");
						s.each(i, function(e) {
							var i = e.getAttribute(t);
							n(e, i)
						}, window, !0)
					},
					c = function(t, i) {
						r(t, e, function(e, t) {
							var o = t.split(":"),
								r = s.queryObject(i, o[1]);
							n(e, o[0], s.bind(r, i))
						})
					},
					a = function(e, n) {
						r(e, t, function(e, t) {
							var i = s.queryObject(n, t);
							i || e.parentNode.removeChild(e)
						})
					};
				return {
					build: function(e, t) {
						s.isArray(e) && (e = e.join("")), e = i(e, t);
						var n = o(e);
						return c(n, t), a(n, t), n
					}
				}
			}(),
			c = {
				options: {
					message: "Questo sito si avvale di cookie necessari al funzionamento del sito. Se vuoi saperne di piÃ¹ o negare il consenso a tutti o ad alcuni cookie, consulta la cookie policy. Chiudendo questo banner o proseguendo la navigazione in altra maniera, acconsenti all'uso dei cookie.",
					dismiss: "Accetta",
					learnmore: "Maggiori informazioni",
					link: null,
					autoclose: null,
					allowdomain: true,
					scrollaccept: true,
					expiredays: 365,
					container: null,
					theme: "",
					markup: [
						'<div class="cc_banner-wrapper {{containerClasses}}">',
						'<div class="cc_banner cc_container cc_container--open">',
						'<a href="javascript:void(0)" data-cc-event="click:dismiss" class="cc_btn cc_btn_accept_all">{{options.dismiss}}</a>',
						'<p class="cc_message">{{options.message}} <a data-cc-if="options.link" class="cc_more_info" href="{{options.link || "javascript:void(0)"}}" onclick="this.target=\'_blank\';">{{options.learnmore}}</a></p>',
						'</div>',
						'</div>'
					]
				},
				init: function() {
					var t = window[e];
					t && this.setOptions(t), this.setContainer(), this.options.theme ? this.loadTheme(this.render) : this.render()
				},
				setOptionsOnTheFly: function(e) {
					this.setOptions(e), this.render()
				},
				setOptions: function(e) {
					s.merge(this.options, e)
				},
				setContainer: function() {
					this.container = this.options.container ? document.querySelector(this.options.container) : document.body, this.containerClasses = "", navigator.appVersion.indexOf("MSIE 8") > -1 && (this.containerClasses += " cc_ie8")
				},
				loadTheme: function(e) {
					var t = this.options.theme; - 1 === t.indexOf(".css") && (t = i + t + ".css");
					var n = document.createElement("link");
					n.rel = "stylesheet", n.type = "text/css", n.href = t;
					var o = !1;
					n.onload = s.bind(function() {
						!o && e && (e.call(this), o = !0)
					}, this), document.getElementsByTagName("head")[0].appendChild(n);
				},
				render: function() {

					thisdiv = this;

					this.element && this.element.parentNode && (this.element.parentNode.removeChild(this.element), delete this.element), this.element = r.build(this.options.markup, this), this.container.firstChild ? this.container.insertBefore(this.element, this.container.firstChild) : this.container.appendChild(this.element);

					// autoclose
					if (c.options.autoclose) {
						setTimeout(function() {
							$(thisdiv.element).fadeOut();
						}, c.options.autoclose);
					};

					// chiusura on scroll
					if (c.options.scrollaccept) {

						$(window).scroll(function(e) {

							if ($(thisdiv.element).length > 0 && $(thisdiv.element).hasClass("cc_banner-hide") == true) {

								thisdiv.dismiss(e);
							}

							$(thisdiv.element).addClass("cc_banner-hide");
						});
					};
				},
				// rimuovo l'elemento dopo il fadeout
				removeElem: function() {
					$(this.element).fadeOut(function() { $(this.element).remove() });
				},
				dismiss: function(e) {
					e.preventDefault && e.preventDefault();
					e.returnValue = !1;
					this.setDismissedCookie();
					this.removeElem();
				},
				setDismissedCookie: function() {
					s.setCookie(n, "yes", c.options.expiredays)
				}
			},
			a = !1;
            //~ (o = function() {
                //~ a || "complete" != document.readyState || (c.init(), a = !0, window[t] = s.bind(c.setOptionsOnTheFly, c))
            //~ })(), s.addEventListener(document, "readystatechange", o)

			(c.init(), window[t] = s.bind(c.setOptionsOnTheFly, c));
        }
    }
});
