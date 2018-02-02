//This example displays an address form, using the autocomplete feature
//of the Google Places API to help users fill in the information.

var placeSearch, autocomplete;
var componentForm = {
street_number: 'short_name',
route: 'long_name',
locality: 'long_name',
administrative_area_level_1: 'short_name',
country: 'long_name',
postal_code: 'short_name'
};
var geocoderIndirizzo;
var geocoder = null;

function showError(error)
{
	switch(error.code)
	{
		case error.PERMISSION_DENIED:
			alert("User denied the request for Geolocation.");
			break;
		case error.POSITION_UNAVAILABLE:
			alert("Location information is unavailable.");
			break;
		case error.TIMEOUT:
			alert("The request to get user location timed out.");
			break;
		case error.UNKNOWN_ERROR:
			alert("An unknown error occurred.");
			break;
	} 	
}

function showMyPosition(position)
{
	lat=position.coords.latitude;
	lon=position.coords.longitude;
	latlon=new google.maps.LatLng(lat, lon);
	$('#latIndirizzoRiferimento').val(lat);
	$('#lngIndirizzoRiferimento').val(lon);
	//alert('impostate coordinate ' + $('#latIndirizzoRiferimento').val() + '-' + $('#lngIndirizzoRiferimento').val());
	getAddressIndirizzo(latlon);
	
}

function getLocation() 
{ 
	if (navigator.geolocation) 
	{ 
		navigator.geolocation.getCurrentPosition(showMyPosition,showError);
	}
}

function getAddressIndirizzo(latLng) {
	geocoderIndirizzo.geocode( {'latLng': latLng},
      function(results, status) {
        if(status == google.maps.GeocoderStatus.OK) {
          if(results[0]) {
            document.getElementById("indirizzoRiferimento").value = results[0].formatted_address;
            abilita_disabilita_btn();
          }
          else {
            document.getElementById("indirizzoRiferimento").value = "";
          }
        }
        else {
          document.getElementById("indirizzoRiferimento").value = "";
        }

      });
    }

function initialize() {

	$('#rapida').keypress(function (e) {
		 var key = e.which;
		 if(key == 13)  // the enter key code
		  {
			//ricercaScuole('rapida');
			 //alert('ricercaAlert');
			$('#formRicercaRapida').submit();
			return false;
		  }
		});
	
	$(document).on('keyup keypress', '#indirizzoRiferimento', function(e) {
		  if(e.keyCode == 13) {
		    e.preventDefault();
		    return false;
		  }
		});
	
	
	caricaTipologia("","");
	caricaTipologia("","Avanzata");
	
	geocoderIndirizzo = new google.maps.Geocoder();
	
	// Create the autocomplete object, restricting the search
	// to geographical location types.
	autocomplete = new google.maps.places.Autocomplete(
	   /** @type {HTMLInputElement} */(document.getElementById('indirizzoRiferimento')),
	   { types: ['geocode'], componentRestrictions: { country: "it"} });
	// When the user selects an address from the dropdown,
	// populate the address fields in the form.
	google.maps.event.addListener(autocomplete, 'place_changed', function() {
	 fillInAddress();
	 abilita_disabilita_btn();
	});
	
	$('#btnForGetLocation').click(function(){
		getLocation();
	});
	
	
	
	/* Cerca per posizione */
	$("#btn_vis_mappa_posizione").click(function() {
		
		var message = showAlertMessage('Caricamento in corso');
		
		if ($('#latIndirizzoRiferimento').val() != '' && $('#lngIndirizzoRiferimento').val() != ''){


			$("#btn_vis_elenco_posizione").prop("disabled",true);
			$("#btn_vis_elenco_posizione").addClass("inactive");
			$("#btn_vis_mappa_posizione").prop("disabled",true);
			$("#btn_vis_mappa_posizione").addClass("inactive");


			var jqxhr = $.getJSON('/cercalatuascuola/ricerca/getRisultati.json?'+$("#voRicerca_posizione").serialize(),function(data){
				console.log( "success" );
				closeMessage(message);
				if (data != null && data.length > 0){
					console.log(data.length);
					$("#sc-map-canvas-2").drawMap({
						"markers": data,
						"templateOpen" : "<div id=\"sc-map-marker\"><div class=\"sc-map-marker-content\">",
						"templateClose" : "</div></div>"
					});
					showSuccessMessage('Caricamento terminato');
				}else{
					var center = $.fn.drawMap().getMapObj('#sc-map-canvas-2').getCenter();

					$("#sc-map-canvas-2").drawMap({
						"mapEmptyShow": true,
						"zoom": 6,
						"markers": [],
						"center" : center
					});
					showErrorMessage('Nessuna scuola trovata');
				}

				if ( $('#raggio').val() != "" ) {
					disegnaCerchioProssimita();
				}

				$("#btn_vis_elenco_posizione").prop("disabled",false);
				$("#btn_vis_elenco_posizione").removeClass("inactive");
				$("#btn_vis_mappa_posizione").prop("disabled",false);
				$("#btn_vis_mappa_posizione").removeClass("inactive");

			})
			.done(function() {
				//$("#btn_vis_elenco_posizione").prop("disabled",false);
				//$("#btn_vis_elenco_posizione").removeClass("inactive");
				console.log( "second success" );
			})
			.fail(function() {
				console.log( "error" );
				if ( $('#raggio').val() != "" ) {
					disegnaCerchioProssimita();
				}
				closeMessage(message);
				showErrorMessage('Attenzione: si é verificato un errore');

				$("#btn_vis_elenco_posizione").prop("disabled",false);
				$("#btn_vis_elenco_posizione").removeClass("inactive");
				$("#btn_vis_mappa_posizione").prop("disabled",false);
				$("#btn_vis_mappa_posizione").removeClass("inactive");

			})
			.always(function() {
				console.log( "complete" );
			});


		}else{
			closeMessage(message);
			showErrorMessage('Attenzione: indirizzo di riferimento non caricato correttamente');
		}
			 
	});
	
	$("#btn_vis_elenco_posizione").click(function() {

		var message = showAlertMessage('Caricamento in corso');
		
		if ($('#latIndirizzoRiferimento').val() != '' && $('#lngIndirizzoRiferimento').val() != ''){

			$("#btn_vis_elenco_posizione").prop("disabled",true);
			$("#btn_vis_elenco_posizione").addClass("inactive");
			$("#btn_vis_mappa_posizione").prop("disabled",true);
			$("#btn_vis_mappa_posizione").addClass("inactive");

			$("#voRicerca_posizione").submit();

		}else{
			closeMessage(message);
			showErrorMessage('Attenzione: indirizzo di riferimento non caricato correttamente');
		}

	});

	$("#btn_cerca_avanzata").click(function() {

		var message = showAlertMessage('Caricamento in corso');

		$("#btn_cerca_avanzata").prop("disabled",true);
		$("#btn_cerca_avanzata").addClass("inactive");

		$("#voRicerca").submit();

	});
	
	/* Cerca per posizione */
	
}

function fillInAddress() {
	// Get the place details from the autocomplete object.
	var place = autocomplete.getPlace();
    if (!place.geometry) {
        window.alert("Attenzione: indirizzo non riconosciuto");
    	$('#latIndirizzoRiferimento').val('');
    	$('#lngIndirizzoRiferimento').val('');

        return;
    }else{
    	$('#latIndirizzoRiferimento').val(place.geometry.location.lat());
    	$('#lngIndirizzoRiferimento').val(place.geometry.location.lng());
    	//alert('impostate coordinate ' + $('#latIndirizzoRiferimento').val() + '-' + $('#lngIndirizzoRiferimento').val());
    	placeMarker(place.geometry.location);
    	disegnaCerchioProssimita();
    }
}
function abilita_disabilita_btn() {
	
	//console.log ($('#indirizzoRiferimento').val());
	//console.log ($('#raggio').val());
	//console.log ($('.codiceOrdine_posizione').val());
	if (
			$('#indirizzoRiferimento').val() != ''
			&& $('#indirizzoRiferimento').val() != 'Inserisci un indirizzo o attiva la localizzazione automatica'
			&& $('#raggio').val() != ''
			&& $('.codiceOrdine_posizione').val() != ''
	){
		$("#btn_vis_elenco_posizione").prop("disabled",false);
		$("#btn_vis_elenco_posizione").removeClass("inactive");
		$("#btn_vis_mappa_posizione").prop("disabled",false);
		$("#btn_vis_mappa_posizione").removeClass("inactive");
		
	}else{
		$("#btn_vis_elenco_posizione").prop("disabled",true);
		$("#btn_vis_elenco_posizione").addClass("inactive");
		$("#btn_vis_mappa_posizione").prop("disabled",true);
		$("#btn_vis_mappa_posizione").addClass("inactive");
	}
}

//[START region_geolocation]
//Bias the autocomplete object to the user's geographical location,
//as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
if (navigator.geolocation) {
navigator.geolocation.getCurrentPosition(function(position) {
 var geolocation = new google.maps.LatLng(
     position.coords.latitude, position.coords.longitude);
 autocomplete.setBounds(new google.maps.LatLngBounds(geolocation,
     geolocation));
});
}
}

//initialize();
//[END region_geolocation]