<script>
var gskey = 'AIzaSyCk522JbvK0P04Wwkzuhvaxo1vOyqoytkw';
$.getScript('http://maps.googleapis.com/maps/api/js?key=' + gskey + '&sensor=false&callback=$.fn.callbackMap');
</script>
<script src="<c:url value="/resources/js/jquery/jquery.s-map.js"/>"></script>
<script>
(function($) {

    /**
    * $.fn.callbackMap
    *
    * callback senza parametri che richiama la drawMap con parametri
    */
    $.fn.callbackMap = function() {

        $("#map_canvas").drawMap({
            "mapAutoFit": true,
            "maxZoom": 16,
            "markers": [
                {data: "${scuola.denominazione}", lat: "", lng: "", address:"${scuola.indirizzoCompleto}"}

            ]
        });

    };
})(jQuery);
</script>