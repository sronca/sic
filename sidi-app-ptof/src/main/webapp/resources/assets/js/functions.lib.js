/**
* INIZIO JQUERY
* author: lorenzo "saibal" forti (lorenzo.forti@gmail.com)
* copy 2016
*/

/**
* parametri di default
*/
/*
nel caso si volesse sovrascrivere qualche parametro senza modificare questo file ecco la sintassi da usare in pagina nell'head
<script>
	var opt = $.extend(defParams, {
		'layoutMenuVersion': "h"
	});
</script>
*/
var defParams = {
	layoutMenuVersion	: "v",
	eMainContainer		: "#main-container",
	eMainPageContent	: "#main-page-content",
	eFooter				: "#footer",
	eOverLay			: "#overlay",
	eNavMobile			: "#nav-mobile",
	eMenuH				: "#menu-h",
	eMenuContainerH		: "#main-menu-container-h",
	eFixedHead			: "#fixed-bar",
	eFixedBarWrapper	: "#fixed-bar-wrapper",
	eTopNavWrapper		: "#top-nav-wrapper",
	eHeaderWrapper		: "#header-wrapper",
	eSideBarContainer	: "#sidebar-container",
	eSideBarMenu		: "#sidebar-menu",
	ePageContent		: ".page-content",
	ePageHeader			: ".page-header",
	eOpenThis			: ".openthis",
	eSelectPicker		: ".selectpicker",
	eDropDown			: ".dropdown",
	eCollapse			: ".collapse",
	eFixElement			: ".fixit",
	eMultiLevelMenu		: ".multilevel-menu",
	eLevelMenu2			: ".menu-level-2",
	eTopLinksWrapper	: ".desktop-usermenu-wrapper",
	eSwitchIcon			: ".switchicon",
	eMultiLineEllipsis	: ".multi-line-ellipsis",
	multiLevelMenuIn	: "in",
	lastChild			: "last-child",
	cloneMenuDataSource	: "desktop-usermenu",
	cloneMenuDataTarget	: "fixed-bar-wrapper",
	cloneMenuDataTarget2: "mobile-usermenu-h",
	cloneMenuDataTarget3: "mobile-usermenu-v",
	darkOverlay			: "dark-overlay",
	SwitchIconEven		: "switchicon-even",
	SwitchIconOdd		: "switchicon-odd",
	limitCharsEllipsis	: 0,
	modalTemplate		: '<div id="msgdialog" aria-labelledby="win-modal" role="dialog" tabindex="-1" class="modal"><div class="modal-dialog modal-sm"><div class="modal-content"><div class="{modal.class} modal-header"><button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">X</span></button><h4 id="win-modal" class="modal-title bootstrap-dialog-header">{modal.title}</h4></div><div class="modal-body">{modal.content}</div></div></div></div>',
	funcTimeOut			: 20000,
	isDebug				: true
};

var opt = $.extend(true, {}, defParams);

(function($) {

	/**
	* $.fn.toLog
	*
	* funzione log
	*/
	$.fn.toLog = function(msg, type) {

		type = typeof(type) !== "undefined" && type != "" ? true : false;

		if (opt.isDebug === true) {
			type ? alert(msg) : (window.console && window.console.log ? window.console.log(msg) : null);
		}
	};

	/**
	*  $.fn.openPopup
	*
	* apre una popup centrale
	*/
	$.fn.openPopup = function(page) {

		left  = ($(window).width()/2)-(900/2),
		top   = ($(window).height()/2)-(600/2),
		popup = window.open (page, "popup", "width=600, top="+top+", left="+left);
	};

	/**
	* $.fn.urlParam
	*
	* estrae i parametri in querystring
	*/
	$.fn.urlParam = function(param) {

		var res = new RegExp('[\?&]' + param + '=([^&#]*)').exec(window.location.href);

		if (res == null) {
		   return null;
		} else {
		   return res[1] || 0;
		}
	};

	/**
	* $.fn.checkHamburger
	*
	* controlla la visibilità del menu hamburger
	*/
	$.fn.hamburgerStatus = function() {

		//workaround per colpa di explorer 8. muori
		//hamburgerStatus = ($(opt.eNavMobile).is(":visible")) ? true : false;
		hamburgerStatus = ($(opt.eNavMobile).css("display") != "none") ? true : false;
	};

	/**
	* $.fn.toggleSwitchIcon
	*
	* dato un selettore fa lo switch delle icone dichiarate come attributo data-*
	*/
	$.fn.toggleSwitchIcon = function(item) {

		if (typeof(item) != "undefined") {

			$(item).toggleClass($(item).data(opt.SwitchIconEven)).toggleClass($(item).data(opt.SwitchIconOdd));
		}
	};

	/**
	* $.fn.manageMenu
	*
	* gestice il menu orizzontale quando si ridimensiona o si carica la pagina
	*/
	$.fn.manageMenuH = function() {

		var self = {};

		$(opt.eMenuH).scrollTop(0);

		self.open = function() {

			$(opt.eMenuContainerH).css("min-height", $(opt.eMainPageContent).height());
			$(opt.eMenuContainerH).css("display", "block");
			$("body").css("overflow", "hidden");

			$.fn.toLog("aperto");
		};

		self.close = function() {

			$(opt.eMenuContainerH).css("min-height", "auto");
			$("body").css("overflow", "auto");

			// se mi trovo in modalità mobile
			if (hamburgerStatus == true) {

				$(opt.eMenuContainerH).css("display","none");

				$.fn.toLog("menu mobile chiuso onclick");

			} else  {

				$(opt.eMenuContainerH).css("display","block");

				$.fn.toLog("menu mobile aperto per desktop");
			}
		};

		return self;
	};

	/**
	* $.fn.manageMenu
	*
	* gestice il menu orizzontale quando si ridimensiona o si carica la pagina
	*/
	$.fn.manageMenuV = function() {

		var self = {};

		self.open = function() {

			$(window).scrollTop(0);
			$(opt.eSideBarContainer).css("display", "block");
			// nascondo il footer per evitare conflitti
			$(opt.eFooter).addClass("hidden");

			// inserisco il div di aggancio per l'overlay
			if (!$(opt.eOverLay).length) {

				$(opt.eMainContainer).before($.parseHTML('<div id="overlay"></div>'));
			}

			$(opt.eOverLay).addClass(opt.darkOverlay);

			// regolo l'altezza della sidebar e dell'overlay confrontando il viewport
//			if ($(opt.eMainPageContent).innerHeight() > sizeviewport) {
//
//				_thisheight = $(opt.eMainPageContent).innerHeight();
//
//			} else {

				_thisheight = sizeviewport;
//			}

			$(opt.eOverLay).css("min-height", _thisheight+ "px");
			$(opt.eSideBarContainer).css("min-height", _thisheight+ "px");

			$.fn.toLog("aperto");
		};

		self.close = function() {

			// se mi trovo in modalità mobile
			if (hamburgerStatus == true) {

				$(opt.eSideBarContainer).css("display", "none");

				$.fn.toLog("menu mobile chiuso onclick");

			} else  {

				$(opt.eSideBarContainer).css("display", "block");

				$.fn.toLog("menu mobile aperto per desktop");
			}

			$(opt.eOverLay).removeClass("dark-overlay");
			$(opt.eFooter).removeClass("hidden");
		};

		return self;
	};

	/**
	* $.fn.manageHamburger
	*
	* gestisce lo status del bottone hamburger
	*/
	$.fn.manageHamburger = function() {

		// wrappo la funzione di gestione menu a seconda del tipo layout scelto
		if (opt.layoutMenuVersion == "h") {
			var manageMenuWrap = new $.fn.manageMenuH();
			var cloneMenuTarget = opt.cloneMenuDataTarget2;
		} else {
			var manageMenuWrap = new $.fn.manageMenuV();
			var cloneMenuTarget = opt.cloneMenuDataTarget3;
		}

		// il controllo mi permette di capire se ho cliccato o meno sull'hamburger
		if (hamburgerStatus == true && $(this).length) {

			$(opt.eNavMobile).toggleClass("open");
		}

		switch($(opt.eNavMobile).hasClass("open")) {

			// se hamburger è aperto
			case true:

				// se mi trovo in modalità mobile chiamo la funzione "open" di managemenu
				if (hamburgerStatus == true) {

					manageMenuWrap["open"]();

					// clono il menu utente
					$.fn.domCloneIt({
						cloneSource: opt.cloneMenuDataSource,
						cloneDest: cloneMenuTarget,
						cloneContainer: "n"
					})

				// se ho ingrandito la finestra ripristino il tutto
				} else {

					$(opt.eNavMobile).removeClass("open");

					// funzione ricorsiva
					$.fn.manageHamburger();

					$.fn.toLog("menu mobile chiuso auto");
				}
			break;

			// se hamburger è chiuso
			case false:
				manageMenuWrap["close"]();
			break;

			// default
			default:
				manageMenuWrap["close"]();
			break;
		}
	};

	/**
	* $.fn.closeUserMenu
	*
	* controlla e chiude il menu utente se aperto
	*/
	$.fn.closeDropDown = function(selector) {

		if ($(selector).hasClass("open")) {

			$(selector).removeClass("open");

			$.fn.toLog("chiudo menu utente");
		}
	};

	/**
	* $.fn.checkLayout
	*
	* controlla il tipo di layout e nasconde/mostra alcuni elementi. posiziona il footer in base alla lunghezza
	* del contenuto principale. si poteva fare con css ma nel layout mobile c'era un problema con la proprietà fixed dell'header
	* gestisce la lunghezza della sidebar
	*/
	$.fn.checkLayout = function(selector) {

		$.fn.hamburgerStatus();

		sizehead = $(opt.ePageHeader).innerHeight();
		sizefoot = $(opt.eFooter).innerHeight();
		sizeviewport = $(window).height() - sizehead;
		sizetotal = $(window).height() - (sizehead + sizefoot);

		switch(opt.layoutMenuVersion) {

			case "v":
				$(opt.eMenuContainerH).css("display", "none");
			break;

			case "h":
				$(opt.eSideBarContainer).css("display", "none");
			break;
		}

		// se la pagina non ha contenuto scrollabile posiziono il footer in fondo
		if ($("body").innerHeight() <= $(window).height()) {

			$(opt.ePageContent).css("min-height", sizetotal+ "px");
		}

		// in versione desktop la lunghezza minima della sidebar è quella del contenuto (escludo per ie8 che ha problemi di velocità)
		if (hamburgerStatus != true && $("html").hasClass("is-ie8") == false) {

			//$(opt.eSideBarContainer).css("min-height", $(opt.ePageContent).innerHeight()+ sizefoot+ "px");
		}
	};

	/**
	* $.fn.domCloneIt
	*
	* clona un oggetto e lo copia in altra posizione. vengono utilizzati gli attributi data-* per una maggiore flessibilità
	*/
	$.fn.domCloneIt = function(customOptions) {

		this.defParams = {
			cloneSource: "",
			cloneDest: "",
			cloneType: "appendTo",
			cloneContainer: "y",
			callprevFunc: "",
			callbackFunc: ""
		}

		this.opt = $.extend(true, {}, this.defParams, customOptions);

		if (this.opt.cloneSource != "" && this.opt.cloneDest != "") {

			if (typeof(this.opt.callprevFunc) == "function") {

				this.opt.callprevFunc.call(this);
			}

			// dichiaro l'oggetto per il semaforo ed evito copie multiple
			if (typeof(_thisControl) == "undefined") {

				_thisControl = {};
			}

			if (typeof(_thisControl[this.opt.cloneDest]) == "undefined") {

				// controllo se copiare tutto l'elemento o solo il contenuto
				if (this.opt.cloneContainer == "n") {
					_thisSource = $("[data-clone-source=" +this.opt.cloneSource+ "]").contents();
				} else {
					_thisSource = $("[data-clone-source=" +this.opt.cloneSource+ "]");
				}

				_thisTarget = $("[data-clone-target=" +this.opt.cloneDest+ "]");

				// clono e rimuovo l'attributo "data" dall'oggetto altrimenti viene copiato N volte
				_thisSource.clone().removeAttr("data-clone-source")[this.opt.cloneType](_thisTarget).promise().done(function() {

					// dopo la prima copia attivo il semaforo
					_thisControl[this.opt.cloneDest] = true;

					$.fn.toLog(this.opt.cloneSource+ " :oggetto clonato");
				});
			};

			if (typeof(this.opt.callbackFunc) == "function") {

				this.opt.callbackFunc.call(this);
			}
		}
	}

	/**
	* $.fn.fixmenuShowHide
	*
	* mostra/nasconde la fixbar a seconda delle dimensioni della finestra e della posizione della scrollbar
	*/
	$.fn.fixbarShowHide = function() {

		var firstScoll = true;
		
		$(window).on("scroll resize", function(e) {

			//FIX altezza menu
			if (firstScoll && $("html").hasClass("is-ie8") == false ){
				$('#sidebar-container').height(   $('#main-page-content').height());	
				firstScoll= false;
			} 
			
			
			if (hamburgerStatus == false) {

				if ($(window).scrollTop() >= $(opt.eFixedHead).data("offset")) {

					// workaround di $(opt.eFixedHead).is(":visible") == false per colpa di explorer 8. muori
					if ($(opt.eFixedHead).css("display") == "none") {

						// chiudo il menu utente se aperto
						$.fn.closeDropDown(opt.eTopNavWrapper+ " " +opt.eTopLinksWrapper);

						$.fn.domCloneIt({
							cloneSource: opt.cloneMenuDataSource,
							cloneDest: opt.cloneMenuDataTarget
						})

						$(opt.eFixedHead).fadeIn(100, function() {

							$(opt.eFixedHead).show();
						});

						$.fn.toLog("menu mobile non visibile. attivo la fixbar");
					}

				} else {

					// workaround di $(opt.eFixedHead).is(":visible") == true per colpa di explorer 8. muori
					if ($(opt.eFixedHead).css("display") != "none") {

						$(opt.eFixedHead).hide().promise().done(function() {

							// chiudo il menu fixedbar se aperto
							$.fn.closeDropDown(opt.eFixedBarWrapper+ " " +opt.eTopLinksWrapper);
						});
					}
				}

			} else {

				$(opt.eFixedHead).hide();

				$(window).off("scroll", opt.eFixedHead);

				$.fn.toLog("menu mobile non visibile");
			}
		});
	};

	/**
	*  $.fn.showDialog
	*
	* mostra un div al centro del viewport con un messaggio
	*/
	$.fn.showDialog = function(customOptions) {

		this.defParams = {
			type: "warning",
			title: "",
			bodymsg: "",
			buttonLabel: "Chiudi",
			draggable: false,
			closable: true,
			callbackFunc: ""
		}

		this.opt = $.extend(true, {}, this.defParams, customOptions);

		var modal_types = {
			"default": BootstrapDialog.TYPE_DEFAULT,
			"info": BootstrapDialog.TYPE_INFO,
			"primary": BootstrapDialog.TYPE_PRIMARY,
			"success": BootstrapDialog.TYPE_SUCCESS,
			"warning": BootstrapDialog.TYPE_WARNING,
			"danger": BootstrapDialog.TYPE_DANGER
		};

		BootstrapDialog.alert({
            title: this.opt.title,
            message: this.opt.bodymsg,
            type: modal_types[this.opt.type],
            closable: this.opt.closable,
            draggable: this.opt.draggable,
            buttonLabel: this.opt.buttonLabel,
            callback: function(result) {

				if (typeof(this.opt.callbackFunc) == "function") {

					this.opt.callbackFunc.call(this);
				}
            }
        });
	};

})(jQuery);

/**
* funzioni al document ready
*/
$(document).ready(function() {

	// controlla il tipo di layout
	$.fn.checkLayout();

	// visibilità bar fix
	$.fn.fixbarShowHide();

	// aggiungo la classe per aprire in automatico il menu
	$(opt.eSideBarMenu+ " " +opt.eOpenThis).each(function() {

		// addback comprendo anche il genitore di parents
		_thisopen = $(this).parents(opt.eMultiLevelMenu).addBack().addClass(opt.multiLevelMenuIn);

		_thisitem = $(this).parents().children("a").find(opt.eSwitchIcon);

		$.fn.toggleSwitchIcon(_thisitem);
	});

	//~ $('[data-toggle="collapse"]').click(function(e) {

		//~ _thisitem = $(this).parents().children("a").find(opt.eSwitchIcon);

		//~ $.fn.toggleSwitchIcon(_thisitem);

	//~ });

	// status su hamburger
	$(opt.eNavMobile).click(function() {

		$.fn.manageHamburger.call($(this));
	});

	// cambio icona su dropdown e collapse
	$(opt.eDropDown+ "," +opt.eCollapse).on("show.bs.dropdown hide.bs.dropdown show.bs.collapse hide.bs.collapse", function(e) {

		// evito conflitti su collapse multipli in pagina
		if ($(this).is(e.target)) {

			// codice relativo ai dropdown
			if (e.namespace == "bs.dropdown") {

				_thisItem = $(this).find(opt.eSwitchIcon);

				//~ if (e.type == "show" && hamburgerStatus == true) {

					//~ //scroll top per evitare un bug
					//~ setTimeout(function(){ $(opt.eMenuH).scrollTop(0); }, 20);
				//~ }

			// codice relativo ai collapse
			} else if (e.namespace == "bs.collapse") {

				_thisItem = $(this).parent().find(opt.eSwitchIcon+":first");
			}

			$.fn.toggleSwitchIcon(_thisItem);
		}
	});

	// fallback ie8... muori!
	if(!Modernizr.svg) {

		$('img[src*="svg"]').attr('src', function() {
			return $(this).attr('src').replace('.svg', '.png');
		});
	}

	// fallback ie8... muori!
	if(!Modernizr.lastchild) {

		$(opt.eLevelMenu2+ " li:last-child a").addClass(opt.lastChild);

		// solo se impostato limitCharsEllipsis si attiva il codice. può essere un fallback per le classi css
		if (opt.limitCharsEllipsis > 0) {

			$(opt.eMultiLineEllipsis).each (function () {

				if ($(this).text().length > opt.limitCharsEllipsis) {
					$(this).text($(this).text().substring(0, opt.limitCharsEllipsis) + '...');
				}
			});
		}
	}
});

// eventi on resize
$(window).on("resize", function() {

 	$.fn.fixbarShowHide();

	$.fn.checkLayout();

	$.fn.manageHamburger();
});