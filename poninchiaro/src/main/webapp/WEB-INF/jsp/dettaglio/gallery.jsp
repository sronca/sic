<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp"%>
<%@ include file="../includes/html.jsp"%>

<head>
<%@ include file="../includes/head.jsp"%>
</head>

<body>    

	<div id="container" class="shadow-fix">

	<%@ include file="../includes/menu.jsp"%>
		
	<div id="breadcrumb">
        <a href="<c:url value="/"/>">Home</a> > Beneficiari > <a href="<c:url value="/beneficiari/ricerca/"/>">Scheda progetto</a> > Gallery
    </div>
    
    <div class="content">
    	
		 <div class="social">
			<div id="share"></div>
		 </div>
     	
     	<h1 class="title-box-2row">Istituto ${scuola.denominazione}</h1>
          <div class="row">
		    <div class="top-tabs">
		    	<a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/"/>">INFORMAZIONI</a>
			    <c:if test="${codTipFon eq 'FSE' or codTipFon eq 'fse'}">
				     <a class="active" href="javascript:void(0);">PROGETTI FORMAZIONE</a>
				     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fesr/"/>">PROGETTI INFRASTRUTTURA</a>
			     </c:if>
			     <c:if test="${codTipFon eq 'FESR' or codTipFon eq 'fesr'}">
				     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fse/"/>">PROGETTI FORMAZIONE</a>
				     <a class="active" href="javascript:void(0);">PROGETTI INFRASTRUTTURA</a>
			     </c:if>
		     	<a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/bandi/"/>">BANDI</a>
		    </div>
            <div class="gray-block page-detail page-separate-columns video">
				
				<p class="title-grey-detail resp">
					<c:if test="${codTipFon eq 'FSE' or codTipFon eq 'fse'}">
						<a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/${codTipFon}"/>">Progetti formazione</a> > <a href="<c:url value="/progetti/${codTipFon}/${prgProgetto}/${scuola.codiceMeccanograficoLowerCase}"/>">Scheda Progetto </a> > Gallery
					</c:if> 
					<c:if test="${codTipFon eq 'FESR' or codTipFon eq 'fesr'}">
						<a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/${codTipFon}"/>">Progetti infrastruttura</a> > <a href="<c:url value="/progetti/${codTipFon}/${prgProgetto}/${scuola.codiceMeccanograficoLowerCase}"/>">Scheda Progetto </a> > Gallery
					</c:if> 
				</p>
	 			
	 			<div class="box-value radius-all">
					<h3 class="box-intro">Galleria immagini del progetto</h3>
					<ul class="gallery">
						<c:forEach items="${galleryList}" var="vo" varStatus="status">
							<li>
								<a href="${serverAllegati}/outdoc.php?flname=${vo}" data-toggle="lightbox" data-gallery="multiimages">
	                                <img src="${serverAllegati}/thumbnail.php?flname=${vo}" class="img-responsive" alt="">
	                            </a>
	                        </li>
						</c:forEach>
					</ul>
				</div>
            </div>
            <p class="back"><a href="<c:url value="/progetti/${codTipFon}/${prgProgetto}/${scuola.codiceMeccanograficoLowerCase}"/>"> < Torna indietro</a></p>
        </div>
    </div>
    
<div class="row space-footer"></div>
<div class="footer row ">

    <p class="text-center text-muted-footer">Tutti i diritti riservati © 2015 Ministero dell'Istruzione, dell'Universitè e della Ricerca - Viale Trastevere 76/A - 00153 Roma</p>
</div>
</div>

	<div class="service-message ie8-compat-message">
		<p>Stai utilizzando Explorer 8, un browser datato. Per una migliore esperienza utilizzane uno più moderno come Firefox o Chrome</p>
	</div>

	<!-- includere solo nella pagina dettaglio scuola -->
	<script>
	var gskey = 'AIzaSyCk522JbvK0P04Wwkzuhvaxo1vOyqoytkw';
	$.getScript('http://maps.googleapis.com/maps/api/js?key=' + gskey + '&sensor=false&callback=$.fn.callbackMap');
	</script>
	<script src="assets/js/jquery/jquery.s-map.js"></script>
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
					{data: "Istituto Ettore Majorana", lat: "", lng: "", address:"Viale Romania 32, 00187 Roma"}

				]
			});

		};
	})(jQuery);
	</script>

	<!-- includere solo nella pagina galleria immagini -->
	<script src="<c:url value="/resources/js/ekko-lightbox.js"/>"></script>
	<!-- includere solo nella pagina galleria immagini -->

	<!-- includere solo nella pagina dettaglio scuola -->
	<script src="<c:url value="/resources/js/jquery/jquery.scrolly.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery/jquery.magnific-popup.js"/>"></script>
    <!-- includere solo nella pagina dettaglio scuola -->

	<script src="<c:url value="/resources/js/jquery/tablesaw.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery/functions.lib.js"/>"></script>

</body>
</html>
