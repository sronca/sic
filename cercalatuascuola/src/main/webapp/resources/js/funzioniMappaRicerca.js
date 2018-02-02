//var mappaRicerca;
var marker;
var cerchio;

/*function inizializeMapRicercaPosizione_OLD(mapcanvas){


	if (mappaRicerca == null){
		
		var latcenter = $('#latIndirizzoRiferimento').val();
		var loncenter = $('#lngIndirizzoRiferimento').val();
		var center;

		if (latcenter != '' && loncenter != ''){
			center = new google.maps.LatLng(latcenter,loncenter);
		}else{
			center = new google.maps.LatLng(41.9034651, 12.4794071); //rome
		}


		var myzoom = 12;

		var mapOptions = {
				zoom: myzoom,
				//center: new google.maps.LatLng(latcenter,loncenter),
				//center: new google.maps.LatLng(41.895466, 12.482324),//rome
				center: center,
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

		mappaRicerca = new google.maps.Map(document.getElementById(mapcanvas), mapOptions);

		google.maps.event.addListener(mappaRicerca, 'rightclick', function(event) {
			placeMarker(event.latLng);
			getAddressIndirizzoRicerca(event.latLng);
		});

		marker = new google.maps.Marker({
			position: center,
			map: mappaRicerca
		});
		
		getAddressIndirizzoRicerca(center);

	}

}
*/

function inizializeMapRicercaPosizione(mapcanvas){

	$("#"+mapcanvas).drawMap({
		"mapEmptyShow": true,
		"zoom": 6,
		"markers": [],
		"callbackName": function() { $.fn.drawContextMenu("#"+mapcanvas) }
	});
	
//	var latcenter = $('#latIndirizzoRiferimento').val();
//	var loncenter = $('#lngIndirizzoRiferimento').val();
//	var center;
//
//	if (latcenter != '' && loncenter != ''){
//		center = new google.maps.LatLng(latcenter,loncenter);
//	}else{
//		center = new google.maps.LatLng(41.9034651, 12.4794071); //rome
//	}


//	google.maps.event.addListener(mappaRicerca, 'rightclick', function(event) {
//		placeMarker(event.latLng);
//		getAddressIndirizzoRicerca(event.latLng);
//	});

//	marker = new google.maps.Marker({
//		position: center,
//		map: mappaRicerca
//	});
		
//	getAddressIndirizzoRicerca(center);


}
function placeMarker(location) {
	
	var mappaRicerca = $.fn.drawMap().getMapObj('#sc-map-canvas-2');
	
	if (mappaRicerca != null){
		if(marker){
			marker.setPosition(location);
		}else{
			marker = new google.maps.Marker({
				position: location, 
				map: mappaRicerca
			});
		}
		mappaRicerca.setCenter(location);
		mappaRicerca.setZoom(12);
	}
}

function getAddressIndirizzoRicerca(latLng) {
	var geocoderIndirizzo = new google.maps.Geocoder();
	geocoderIndirizzo.geocode( {'latLng': latLng},
      function(results, status) {
        if(status == google.maps.GeocoderStatus.OK) {
          if(results[0]) {
            //document.getElementById("indirizzoRiferimento").value = results[0].formatted_address;
            $('#indirizzoRiferimento').val(results[0].formatted_address);
            $('#latIndirizzoRiferimento').val(latLng.lat());
            $('#lngIndirizzoRiferimento').val(latLng.lng());
          }
          else {
            //document.getElementById("indirizzoRiferimento").value = "No results";
        	$('#indirizzoRiferimento').val('');
            $('#latIndirizzoRiferimento').val('');
            $('#lngIndirizzoRiferimento').val('');

          }
        }
        else {
          //document.getElementById("indirizzoRiferimento").value = status;
        	$('#indirizzoRiferimento').val('');
            $('#latIndirizzoRiferimento').val('');
            $('#lngIndirizzoRiferimento').val('');
        }
        
        if ( $('#raggio').val() != "" ) {
        	disegnaCerchioProssimita();
        }
        
        abilita_disabilita_btn();
      });
}

function disegnaCerchioProssimita(){
	
	var mappaRicerca = $.fn.drawMap().getMapObj('#sc-map-canvas-2');
	
	if (mappaRicerca != null && mappaRicerca != "undefined"){

		var indirizzo = $('#indirizzoRiferimento').val();
		var raggioCerchio = $('#raggio').val();
		var lat = $('#latIndirizzoRiferimento').val();
		var lon = $('#lngIndirizzoRiferimento').val();

        
		if(indirizzo != null && indirizzo != "" && indirizzo != "null" && indirizzo != "undefined"
			&& lat != null && lat != "" && lat != "null" && lat != "undefined"){

			if(cerchio != null && cerchio != "" && cerchio != "null" && cerchio != "undefined"){
				cerchio.setMap(null);
				cerchio = null;
			}		
			if (raggioCerchio != ''){
				var position = new google.maps.LatLng(lat,lon);
				cerchio = new google.maps.Circle({
					strokeColor: '#4E7A8B',
					strokeOpacity: 0.8,
					strokeWeight: 2,
					fillColor: '#779DA5',
					fillOpacity: 0.25,
					center: position,
					radius: Number(raggioCerchio)*1000,
					map: mappaRicerca });
				mappaRicerca.fitBounds(cerchio.getBounds());
			}
		}

	}
}
