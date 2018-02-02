$(document).ready(function(){

	$.fn.drawContextMenu = function(mapid) {

		// personalizzare a piacere lasciando inviariati i placeholder {..}
		var contextTemplate = "";
		contextTemplate += "<div class=\"{contextMenuClass}\">";
		contextTemplate += "<p><a href=\"javascript:void(0)\" onclick=\"{callback}\">Imposta questa posizione</a></p>";
		contextTemplate += "</div>";

		// non modificare
		defParams = {
			"contextMenuClass"		: "gmap_contextmenu",
			"contextContainerClass" : "gmap_contextcontainer",
			"callback"				: "$.fn.drawContextMenu('" +mapid+ "').doCallback('" +mapid+ "')"
		};

		var self = {};

		if (typeof(mapid) == "undefined") return false;

		var contextContainer = defParams["contextContainerClass"];
		var pContextContainer = "." + contextContainer;
		var thismap = $.fn.drawMap().getMapObj(mapid);

		/**
		* doCallback()
		*
		* inserire le callback qui dentro. la var che contiene le coordinate è "resultLatLng"
		*/
		self.doCallback = function(mapid) {

			// non rimuovere
			$(mapid+ " " +pContextContainer).remove();

			//inserire callback. a disposizione la var "resultLatLng"
			placeMarker(resultLatLng);
			getAddressIndirizzoRicerca(resultLatLng);
			//
			//
		};

		/**
		* init()
		*
		* istanzia il menu all'onclick tasto destro
		*/
		self.init = function() {

			google.maps.event.addListener(thismap, "rightclick", function(e) {

				resultLatLng = e.latLng;

				self.showContextMenu(e.latLng);
			});

			google.maps.event.addListener(thismap, "click", function() {

				$(mapid+ " " +pContextContainer).remove();
			});
		};

		/**
		* showContextMenu()
		*
		* rimuove i vecchi menu aperti e crea il nuovo
		*/
		self.showContextMenu = function(currentCoord) {

			$(mapid+ " " +pContextContainer).remove();

			// $.parseHTML ha ottime prestazioni rispetto ai vecchi metodi. poco meno veloce di document.createElement()
			$($.parseHTML('<div>')).addClass(contextContainer).html(self.renderTemplate()).appendTo(thismap.getDiv());

			self.setMenuCoord(currentCoord);
		};

		/**
		* setMenuCoord()
		*
		* prende le coordinate del click sulla mappa e imposta l'offset al menu
		*/
		self.setMenuCoord = function(currentCoord) {

			var scale = Math.pow(2, thismap.getZoom());

			var nw = new google.maps.LatLng(
				thismap.getBounds().getNorthEast().lat(),
				thismap.getBounds().getSouthWest().lng()
			);

			var worldCoordinateNW = thismap.getProjection().fromLatLngToPoint(nw);
			var worldCoordinate = thismap.getProjection().fromLatLngToPoint(currentCoord);

			var currentLatLngOffset = new google.maps.Point(
				Math.floor((worldCoordinate.x - worldCoordinateNW.x) * scale),
				Math.floor((worldCoordinate.y - worldCoordinateNW.y) * scale)
			);

			$(mapid+ " " +pContextContainer).css("left", currentLatLngOffset.x);
			$(mapid+ " " +pContextContainer).css("top", currentLatLngOffset.y);
		};

		/**
		* renderTemplate()
		*
		* sostistuisce nel template le variabili racchiuse tra {}
		*/
		self.renderTemplate = function() {

			var tpl = contextTemplate;

			tplResult = tpl.replace(/\{([\w\.]*)\}/g, function(tplStr, tplKey) {

				tpl = "";
				res = defParams[tplKey];
				return (typeof(res) !== "undefined" && res !== null) ? res : "";
			});

			tpl += tplResult;

			return tpl;
		};

		self.init();

		return self;
	};
});