function callbackDrawList(){
	
	$('.sc-map-modal').magnificPopup({
		type: 'ajax',
		removalDelay: 300,
		alignTop: 'true',
		fixedContentPos: 'true',
		mainClass: 'my-mfp-zoom-in',
		callbacks: {
			parseAjax: function(mfpResponse) {
				mfpResponse.data = $(mfpResponse.data).find('.sc-general-info');
			},
			ajaxContentAdded: function() {
				//showMap('sc-map-canvas-2');
			}
		}
	});
	
}

//function toggle_visibility(id) {
//    var e = document.getElementById(id);
//    if(e.style.display == 'block'){
//       e.style.display = 'none';
//    }
//    else{
//       e.style.display = 'block';
//       inizializeMapRicercaPosizione('sc-map-canvas-2');
//    }
// }

function checkConfrontaScuOrd(){
	
	var checkOK = true;
	var ordini = [];
	$.each($(".formricerca").compare().cookieVals.items, function(key, val) {

		var codScu = val[Object.keys(val)[0]];
		var scuOrd = codScu.substring(2,4);
		if (scuOrd == 'AA' || scuOrd == '1A'){
			ordini.push('AA');
		}else if (scuOrd == 'EE' || scuOrd == '1E'){
			ordini.push('EE');
		}else if (scuOrd == 'MM' || scuOrd == '1M'){
			ordini.push('MM');
		}else if (scuOrd == 'CT' || scuOrd == 'IC' || scuOrd == 'IS' || scuOrd == 'CF'){
			checkOK = false;
		}else{
			ordini.push('SS');
		}
	});
	
	if (ordini.length > 1){
		var first = ordini[0];
		$.each(ordini,function(index){
			if (ordini[index] != first){
				checkOK = false;
			}
		});
	}
	
	return checkOK;
	
}

function checkConfrontaCFP(){
	
	var checkOK = true;
	$.each($(".formricerca").compare().cookieVals.items, function(key, val) {

		var codScu = val[Object.keys(val)[0]];
		var scuOrd = codScu.substring(2,4);
		if (scuOrd == 'CF'){
			checkOK = false;
		}
	});
	
	return checkOK;
}

function checkConfrontaCT(){
	
	var checkOK = true;
	$.each($(".formricerca").compare().cookieVals.items, function(key, val) {

		var codScu = val[Object.keys(val)[0]];
		var scuOrd = codScu.substring(2,4);
		if (scuOrd == 'CT' || scuOrd == 'IC' || scuOrd == 'IS'){
			checkOK = false;
		}
	});
	
	return checkOK;
}

function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML = 
      "<html><head><title></title></head><body>" + 
      divElements + "</body>";

    //Print Page
    window.print();

    //Restore orignal HTML
    document.body.innerHTML = oldPage;
}


$(document).ready(function() {

	/* Favourite star */
	if ($('.sc-save-fav').length) {
		$('.sc-save-fav a').on('click', function(e) {
			e.preventDefault();
			$(this).toggleClass('sc-saved');
		});
	}

	$('.sc-map-modal').magnificPopup({
		type: 'ajax',
		removalDelay: 300,
		alignTop: 'true',
		fixedContentPos: 'true',
		mainClass: 'my-mfp-zoom-in',
		callbacks: {
			parseAjax: function(mfpResponse) {
				mfpResponse.data = $(mfpResponse.data).find('.sc-general-info');
			},
			ajaxContentAdded: function() {
				//showMap('sc-map-canvas-2');
			}
		}
	});

	$('.sc-modal').magnificPopup({
		alignTop: 'true',
		type: 'ajax',
		fixedContentPos: 'true',
		removalDelay: 300,
		mainClass: 'my-mfp-zoom-in',
		callbacks: {
			parseAjax: function(mfpResponse) {
				mfpResponse.data = $(mfpResponse.data).find('.sc-new-search');
			},
			ajaxContentAdded: function() {
				$(".sc-search-def .sc-form").checkForms();
				$(".sc-search-pos .sc-form").checkForms();
				$(".sc-search-adv .sc-form").checkForms();
				$('.sc-search-pos, .sc-search-adv').checkTabs();
			}
		}
	});

	$('.sc-info-modal').magnificPopup({
		alignTop: 'true',
		type: 'ajax',
		fixedContentPos: 'true',
		removalDelay: 300,
		mainClass: 'my-mfp-zoom-in',
		callbacks: {
			parseAjax: function(mfpResponse) {
				mfpResponse.data = $(mfpResponse.data).find('.sc-general-info');
			}
		}
	});

	/* Menu */

	$('.sc-menu a').on('click', function(e) {
		e.preventDefault();
		e.stopPropagation();
		$(this).toggleClass('active');
		$('.sc-main-nav').toggleClass('active');
	});

	$(document).on('click',function(e){
		if(!$(e.target).is('.sc-nav') && !$(e.target).is('.sc-nav a')){
			$(".sc-main-nav, .sc-menu a").removeClass("active");
		}
	});

	$('.sc-logo-header').on('click', function(e) {
		window.location.href = '/cercalatuascuola';
	});

	/* Tab */

	if ($('.sc-search-content').length) {
		$('.sc-search-pos, .sc-search-adv').checkTabs();
	}

	$(document).on("click", ".sc-search-nav a",function(e){
		e.preventDefault();
		$(this).parent('li').addClass('active');
		$(this).parent('li').siblings().removeClass('active');
		var tab = $(this).attr('data-tab');
		$('.'+tab).siblings().addClass('inactive');
		$('.'+tab).removeClass('inactive');
		if(tab=="sc-search-pos"){
			$("a").parents('div.sc-main-content').addClass('active');
			$("a").parents('div.sc-new-search').addClass('active');
			$.fn.drawMap().callbackResize('#sc-map-canvas-2');
		}
		else{
			$("a").parents('div.sc-main-content').removeClass('active');
			$("a").parents('div.sc-new-search').removeClass('active');
		}		
	});

	$(document).on("click", ".sc-new-search a",function(e){
		e.preventDefault();
		$(this).parent('li').addClass('active');
		$(this).parent('li').siblings().removeClass('active');
		var tab = $(this).attr('data-tab');
		$('.'+tab).siblings().addClass('inactive');
		$('.'+tab).removeClass('inactive');
		if(tab=="sc-search-pos"){
			$("a").parents('div.sc-main-content').addClass('active');
			$("a").parents('div.sc-new-search').addClass('active');
			$.fn.drawMap().callbackResize('#sc-map-canvas-2');
		}
		else{
			$("a").parents('div.sc-main-content').removeClass('active');
			$("a").parents('div.sc-new-search').removeClass('active');
		}		
	});

	/* Table */
	if ($('.sc-table').length) {
		$(".sc-table tr:odd").addClass("odd");
		$(".sc-table tr:even").addClass("even");
	}


	if ($('.sc-form').length) {
		/* Validation form */
		$(".sc-search-def .sc-form").checkForms();
		$(".sc-search-pos .sc-form").checkForms();
		$(".sc-search-adv .sc-form").checkForms();
	}

	var $header;
	var $fixedHeader;
	var $fixedDiv;

	if ($('.sc-compare').length) {
		$header = $(".sc-table-header").clone();
		$fixedHeader = $(".sc-table-header-fixed").append($header);
		$fixedDiv = $(".sc-table-fixed-container");
		checkTopTable($header, $fixedHeader, $fixedDiv);
	};

	if ($('.sc-compare-table').length) {
		checkWidth();
	}

	$(window).resize(function(){
		if ($('.sc-compare-table').length) {
			$('.sc-compare-table tr, .sc-compare-table th, .sc-compare-table td').resetValue();
			$('.sc-table-header-fixed tr, .sc-table-header-fixed th').resetValue();
			$('.sc-table-next').off();
			$('.sc-table-prev').off();
			checkWidth();
		}
		if ($('.sc-compare').length) {
			checkTopTable($header, $fixedHeader, $fixedDiv);	
		};
	});

	if ($('#sc-map-canvas').length) {
		if ($('#sc-map-canvas').attr('data-tipo') == 'EDIL'){
			//showMapEdilizia('sc-map-canvas');
		}else{
			showMap('sc-map-canvas');
		}
		
	}

	if ($('#sc-map-canvas-2').length) {
		if ($('#sc-map-canvas-2').attr('data-tipo') == 'RIC_POS'){
			inizializeMapRicercaPosizione('sc-map-canvas-2');
		}else{
			showMap('sc-map-canvas-2');
		}
	}

	if ($('.sc-section-menu').length) {
		$('.sc-nav-action').on('click', function(e) {
			e.preventDefault();
			$('.sc-icon').toggleClass('sc-active');
			$('.sc-scroll-cont-nav').slideToggle(400);
			$('.sc-scroll-cont-nav-map').slideUp(400);
		});
	};
	
	
	/* Preferiti */
	
	$.fn.bookmark.defParams.defTemplate =  "<tr>" +
										   "<td data-col-2=\"Istituto Principale\" class=\"sc-tab-istituto sc-col-2\"><a href=\"{item.murl}\">{item.mname}</a><span class=\"sc-cod\">{item.mid}</span></td>" +
										   "<td data-col-1=\"Plesso / Scuola\" class=\"sc-tab-plesso sc-col-1\"> <a href=\"{item.url}\">{item.name}</a> <span class=\"sc-cod\">{item.id} <span class=\"sc-pref-cod\"><a href=\"javascript:$.fn.bookmark().removeOne('{item.key}');\" title=\"Elimina dai preferiti\" class=\"sc-saved\">Elimina dai preferiti</a></span> </span> </td>" +
										   "<td data-col-3=\"Indirizzo\" class=\"sc-tab-indirizzo sc-col-3\"><span>{item.address}</span></td>" +
										   "<td data-col-4=\"Email\" class=\"sc-tab-email sc-col-4\"><a href=\"mailto:{item.email}\">{item.email}</a></td>" +
										   "<td data-col-5=\"Mappa\" class=\"sc-tab-mappa sc-col-5\"><a href=\"/cercalatuascuola/mappa_istituto/{item.id}/{item.id}\" class=\"sc-map-modal mappa pin-neutro\"><span>Mappa Link</span></a></td>" +
										   "<td data-col-6=\"Confronta\" class=\"sc-tab-confronta sc-col-6\"><input type=\"checkbox\" name=\"conf-{item.id}\" id=\"conf-{item.id}\" value=\"{item.id}\"/><label data-label=\"Seleziona per il confronto\"><span>Confronta</span></label></td>" +
										   "</tr>";
	
	$.fn.bookmark.defParams.drawCallback = callbackDrawList;
	
    $(".mybookmark").bookmark({
        "maxItems": 100
    });


    $.fn.bookmark().drawList();
	

	$("#sbm-delall").click(function() {
		$.fn.bookmark().removeAll();
	});
	/* Preferiti */
	
	/* $(".chart-explain").dotdot(); */

	
	
	/* Edilizia cookie */
	
/*	if ($.cookie('edil-msg') == "1") {

		$("#edil-msg").css("display","none");
		$("#box_intero_edilizia").removeClass("hidden");
	}

	$("#edil-close").click(function() {

		if ($('#edil-discard').prop('checked') == true) {

			$.cookie('edil-msg', '1', { expires: 1 });
		}

		$("#edil-msg").addClass("hidden");
		$("#box_intero_edilizia").removeClass("hidden");
		$.fn.drawMap().callbackResize('#sc-map-edil');

	});*/
	
	$("#linkNote").click(function() {

		$("#edil-msgNote").show();
		$("#box_intero_edilizia").addClass("hidden");

	});

	$("#edil-closeNote").click(function() {

		$("#edil-msgNote").hide();
		$("#box_intero_edilizia").removeClass("hidden");

	});

	/* Edilizia cookie */

	if ($('.sc-section-menu-map').length) {
		$('.sc-nav-action-map').on('click', function(e) {
			e.preventDefault();
			$('.sc-icon').toggleClass('sc-active');
			$('.sc-scroll-cont-nav-map').slideToggle(400);
			$('.sc-scroll-cont-nav').slideUp(400);
		});
	};
	
	 $(document).mouseup(function (e)
			    {
			        var container1 = $('.sc-scroll-cont-nav');
			        var container2 = $('.sc-scroll-cont-nav-map');
			   
			        if (!container1.is(e.target) && container1.has(e.target).length === 0 || !container2.is(e.target) && container2.has(e.target).length === 0  )
			        {
			            container1.hide();
			            container2.hide();
			        }
			    });
	

});

jQuery.fn.extend({
    resetValue: function () {
        $(this).removeAttr('style');
    }
});

jQuery.fn.extend({
    checkTabs: function () {
        $(this).addClass('inactive');
    }
});

jQuery.fn.extend({
	checkForms: function () {
		$(this).validate({
			errorPlacement: function (error, element) {
	        	if ($(element).is('select')) {
	        		element.parent().addClass('error');
	            	element.parent().after(error); // special placement for select elements
	        	} else {
	            	error.insertAfter(element);  // default placement for everything else
	        	}
	    	}
		});
	}
});

function checkTopTable($header, $fixedHeader, $fixedDiv){
	var $windowOffset = $(window).scrollTop();
	checkOffset($header, $fixedHeader, $fixedDiv, $windowOffset);
	$(window).unbind('scroll');
	$(window).bind("scroll", function() {
		var $elOffset = $(this).scrollTop();
		checkOffset($header, $fixedHeader, $fixedDiv, $elOffset);
	});
}

function checkOffset($header, $fixedHeader, $fixedDiv, $newOffset){
	var offset = $newOffset;
    var tableOffset = ($(".sc-compare-table").offset().top)-80;
    if (offset >= tableOffset && $fixedHeader.is(":hidden")) {
        $fixedDiv.show();
    }
    else if (offset < tableOffset) {
        $fixedDiv.hide();
    }
}

function checkWidth(){
    var scrollBarWidth = 0;
	var FF = !(window.mozInnerScreenX == null);
	if (FF) scrollBarWidth = window.innerWidth - jQuery("body").width();
	var mediaValue = 767;
	if ($('.sc-section-menu').length) { mediaValue = 959; }
	if( $(window).width() > mediaValue - scrollBarWidth){
		$('.sc-compare-table tr, .sc-compare-table th, .sc-compare-table td').resetValue();
		$('.sc-table-header-fixed tr, .sc-table-header-fixed th').resetValue();
		$('.sc-compare-table th, .sc-table-header-fixed th, .sc-table-content td').css('height', 'auto');
	}
	else {
		moveEl();
		$('.sc-compare-table th, .sc-table-header-fixed th').equalHeight();
		$('.sc-table-content td').equalHeight();
	}
	if ($('.sc-section-menu').length) {
		if($(window).width() > 767 - scrollBarWidth){
			$('.sc-icon').removeClass('sc-active');
			$('.sc-scroll-cont-nav').hide();
		}
	}
}

$.fn.equalHeight = function() {
	var maxHeight = 0;
	return this.each(function(index, box) {
		var boxHeight = $(box).height();
		maxHeight = Math.max(maxHeight, boxHeight);
	}).height(maxHeight);
};

function moveEl(){
	var mainValue = ($(window).width() / 2);
	if( $(".sc-detail").length ){ mainValue = ($(".sc-internal-content").width() / 2) }
	var tdNum;
	var tableWidth;
	var tablePercent;
	var nextValue;

	$(".sc-compare-table" ).each(function(index){
  		tdNum = $('th', this).size();
		tableWidth = (50 * tdNum)+'%';
		tablePercent = (100 / tdNum);
		nextValue = (-(mainValue * (tdNum-2)))+'px';
		$(this).attr({ 'data-next': nextValue });
		if ($('.sc-table-header-fixed').length) {
			$('.sc-table-header-fixed').attr({ 'data-next': nextValue });
		};
		$('tr', this).css({ width: tableWidth });
		$('.sc-table-header-fixed tr').css({ width: tableWidth });
		$('th, td', this).css({ width: tablePercent+'%'});
		$('.sc-table-header-fixed th').css({ width: tablePercent+'%'});
		$('.sc-table-next, .sc-table-prev').addClass('sc-active');
	});

	
	$('.sc-table-next').on('click', function(e) {
		e.preventDefault();
		var $this = $(this);
		var nextValue = $this.closest('table').attr('data-next');
		var dir = "-";
		preAnimateEl($this, nextValue, dir, mainValue);
	});

	$('.sc-table-prev').on('click', function(e) {
		e.preventDefault();
		var $this = $(this);
		var nextValue = '0px';
		var dir = "+";
		preAnimateEl($this, nextValue, dir, mainValue);
	});
}

function preAnimateEl($this, nextValue, dir, mainValue){
	var $thisHeader;
	var $thisContent;
	if ($this.closest('table').hasClass('sc-table-header-fixed')) {
		$thisHeader = $('.sc-compare-table th:not(:first-child)'); 
		$thisContent = $('.sc-compare-table td:not(:first-child)');
	} else {
		$thisHeader = $this.parent().parent().parent().children('th:not(:first-child)'); 
		$thisContent = $this.parent().parent().parent().parent().siblings().children().children('td:not(:first-child)');
	};
	if ($this.parent().parent().parent().children('th:nth-child(2)').css('left') != nextValue) {
		if ($this.hasClass('sc-active')) {
			$this.removeClass('sc-active');
			function animateEl($el){
				$el.animate({
				left: dir+'='+mainValue+'px'
				}, 300, function(){
					$this.addClass('sc-active');
				});
			}
			animateEl($('.sc-table-header-fixed th:not(:first-child)'));
			animateEl($thisHeader);
			animateEl($thisContent);
		};
	};
}

(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: IT (Italian; Italiano)
 */
$.extend($.validator.messages, {
	required: "Campo obbligatorio.",
	remote: "Controlla questo campo.",
	email: "Inserisci un indirizzo email valido.",
	url: "Inserisci un indirizzo web valido.",
	date: "Inserisci una data valida.",
	dateISO: "Inserisci una data valida (ISO).",
	number: "Inserisci un numero valido.",
	digits: "Inserisci solo numeri.",
	creditcard: "Inserisci un numero di carta di credito valido.",
	equalTo: "Il valore non corrisponde.",
	extension: "Inserisci un valore con un&apos;estensione valida.",
	maxlength: $.validator.format("Non inserire pi&ugrave; di {0} caratteri."),
	minlength: $.validator.format("Inserisci almeno {0} caratteri."),
	rangelength: $.validator.format("Inserisci un valore compreso tra {0} e {1} caratteri."),
	range: $.validator.format("Inserisci un valore compreso tra {0} e {1}."),
	max: $.validator.format("Inserisci un valore minore o uguale a {0}."),
	min: $.validator.format("Inserisci un valore maggiore o uguale a {0}."),
	nifES: "Inserisci un NIF valido.",
	nieES: "Inserisci un NIE valido.",
	cifES: "Inserisci un CIF valido."
});

}));

function showMap(mapcanvas){

	/** add Simone **/
	var locations = [];
	var latcenter;
	var loncenter;
	
	$(".dati_scuola" ).each(function(index){
		
		posizione = $(this).attr('data-index');
		lat = $(this).attr('data-lat');
		lon = $(this).attr('data-lon');
		nome = $(this).attr('data-nome');
		tiposcuola = $(this).attr('data-tiposcuola');
		indirizzo = $(this).attr('data-indirizzo');
		telefono = $(this).attr('data-telefono');
		codicemeccanografico = $(this).attr('data-codicemeccanografico');
		normalizedname = $(this).attr('data-normalizedname');
		stataleNonStatale = $(this).attr('data-stataleNonStatale');
		
		if (posizione == 1){
			latcenter = lat;
			loncenter = lon;
		}
		
		var myLatLng = new google.maps.LatLng(lat,lon);
		var image;
		if (stataleNonStatale == '0'){
			image = 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+posizione+'|8596FF|000000';
		}else{
			image = 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+posizione+'|6BC64E|000000';
		}
		var contentString = '<div id="sc-map-marker">'+
							'  <div class="sc-map-marker-content">'+
							'     <p><strong>'+nome+'</strong></p>'+
							'     <p>'+tiposcuola+'</p>'+
							'     <p>'+indirizzo+'</p>'+
							'     <p>Tel. '+telefono+'</p>'+
							'     <br />'+
							'     <p><strong><a href="/cercalatuascuola/istituti/'+codicemeccanografico+'/'+normalizedname+'">Scheda</a></strong></p>'+
							'  </div>'+
							'</div>';
		var markeri = ['Marker'+posizione, myLatLng, image, contentString];
		locations.push(markeri);
		
	});
	
//	var mapOptions = {
//			zoom: 4,
//			center: new google.maps.LatLng(latcenter,loncenter),
//			scrollwheel: false
//		};
	
	var myzoom = 6;
	if (locations.length == 1){
		myzoom = 10;
	}
	var mapOptions = {
				zoom: myzoom,
				center: new google.maps.LatLng(latcenter,loncenter),
				//center: new google.maps.LatLng(41.895466, 12.482324),//rome
				panControl: true,
				zoomControl: true,
				scaleControl: true,
				mapTypeControl: true,
				mapTypeId: google.maps.MapTypeId.ROADMAP,
				mapTypeControlOptions: {
					style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
					position: google.maps.ControlPosition.TOP_RIGHT},
				overviewMapControl: true
	};	

	var map = new google.maps.Map(document.getElementById(mapcanvas), mapOptions);
	
//	var myLatlng1 = new google.maps.LatLng(-25.363882,131.044922);
//	var myLatlng2 = new google.maps.LatLng(-25.363882,121.044922);
//	var myLatlng3 = new google.maps.LatLng(-25.363882,141.044922);
//	var myLatlng4 = new google.maps.LatLng(-25.363882,151.044922);
//	var myLatlng5 = new google.maps.LatLng(-25.363882,115.044922);
//	var myLatlng6 = new google.maps.LatLng(-25.363882,115.044922);

//	var image1 = 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=A|8596FF|000000';
//	var image2 = 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=B|6BC64E|000000';
//	var image3 = 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=C|FF837B|000000';
//	var image4 = 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=D|8596FF|000000';
//	var image5 = 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=E|6BC64E|000000';
//	var image6 = 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=F|6BC64E|000000';


//	var contentString = '<div id="sc-map-marker">'+
//						'  <div class="sc-map-marker-content">'+
//						'     <p><strong>Marker</strong></p>'+
//						'     <p><a href="">Lorem ipsum dolor sit</a></p>'+
//						'     <br />'+
//						'     <p><strong>Test / Test</strong></p>'+
//						'  </div>'+
//						'</div>';

//	var locations = [
//		['Marker 1', myLatlng1, image1, contentString],
//		['Marker 2', myLatlng2, image2, contentString],
//		['Marker 3', myLatlng3, image3, contentString],
//		['Marker 4', myLatlng4, image4, contentString],
//		['Marker 5', myLatlng5, image5, contentString],
//		['Marker 6', myLatlng6, image6, contentString]
//	];

	var marker, i;
	
	var infowindow = new google.maps.InfoWindow({
		//content: contentString,
		maxWidth: 240
	});
	
	for (i = 0; i < locations.length; i++) {  
		marker = new google.maps.Marker({
			position: locations[i][1],
			map: map,
			icon: locations[i][2]
		});

		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
	 			infowindow.setContent(locations[i][3]);
	 			infowindow.open(map, marker);
			};
		})(marker,i));
	};


	
//	google.maps.event.addListener(marker, 'click', function() {
//		infowindow.open(map,marker);
//	});
	


}

function codeAddress(lat, lon, indirizzo){
	
	var latlon = null;
	if (lat == "") {
		var address=indirizzo.toLowerCase().replace("snc",'');
		address=address.replace("frazione","");
		address=address.replace("fraz.","");
		geocoder.geocode( { 'address': address, 'region': 'it'}, function(results, status) {
		      if (status == google.maps.GeocoderStatus.OK) {
		    	  latlon = results[0].geometry.location;
		      } else {
		    	  if ( status == "OVER_QUERY_LIMIT" ) {
		    		  setTimeout(function () {
		    				codeAddress(lat,lng, address);
		    				},2000);	
		    	  }else{  
		    		  alert('Geocode was not successful for the following reason: ' + status);
		    	  }
		      }
    	});
	}else {
		latlon = new google.maps.LatLng(lat,lon);
	}
	
	
	return latlon;
}

//function showMapEdilizia(mapcanvas){
//
//	var locations = [];
//	var latcenter;
//	var loncenter;
//	geocoder = new google.maps.Geocoder();
//	
//	
//	$(".dati_scuola" ).each(function(index){
//		
//		posizione = $(this).attr('data-index');
//		lat = $(this).attr('data-lat');
//		lon = $(this).attr('data-lon');
//		indirizzo = $(this).attr('data-indirizzo');
//		numeroPlessi = $(this).attr('data-numeroPlessi');
//		
//		var myLatLng = null;
//				
//		myLatLng = codeAddress(lat, lon, indirizzo);
//		
//		var image = 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld='+posizione+'|8596FF|000000';
//
//		//Edificio 3
//		//Piazza Palio snc 73100 LECCE (LECCE) Italia
//		//Plessi ospitati: 4
//		var contentString = '<div id="sc-map-marker">'+
//							'  <div class="sc-map-marker-content">'+
//							'     <p><strong>Edificio '+posizione+'</strong></p>'+
//							'     <p>'+indirizzo+'</p>'+
//							'     <p>Plessi ospitati: '+numeroPlessi+'</p>'+
//							'  </div>'+
//							'</div>';
//		if (myLatLng != null && myLatLng != '' && myLatLng != 'undefined' && myLatLng != 'null'){
//			var markeri = ['Marker'+posizione, myLatLng, image, contentString];
//			locations.push(markeri);
//		}
//		
//	});
//
//	var myzoom = 6;
//
//	if (locations.length == 1){
//		myzoom = 10;
//	}
//	var mapOptions = {
//			zoom: myzoom,
//			//center: new google.maps.LatLng(latcenter,loncenter),
//			center: new google.maps.LatLng(41.895466, 12.482324),//rome
//			panControl: true,
//			zoomControl: true,
//			scaleControl: true,
//			mapTypeControl: true,
//			mapTypeId: google.maps.MapTypeId.ROADMAP,
//			mapTypeControlOptions: {
//				style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
//				position: google.maps.ControlPosition.TOP_RIGHT},
//				overviewMapControl: true
//	};	
//
//
//
//	var map = new google.maps.Map(document.getElementById(mapcanvas), mapOptions);
//
//	if (locations.length > 0){
//			
//		var marker, i;
//
//		var infowindow = new google.maps.InfoWindow({
//			//content: contentString,
//			maxWidth: 240
//		});
//
//		for (i = 0; i < locations.length; i++) {  
//			marker = new google.maps.Marker({
//				position: locations[i][1],
//				map: map,
//				icon: locations[i][2]
//			});
//
//			google.maps.event.addListener(marker, 'click', (function(marker, i) {
//				return function() {
//					infowindow.setContent(locations[i][3]);
//					infowindow.open(map, marker);
//				};
//			})(marker,i));
//		};
//
//	}
//
//
//}

