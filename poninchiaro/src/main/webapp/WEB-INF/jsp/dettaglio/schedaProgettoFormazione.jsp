<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp"%>
<%@ include file="../includes/html.jsp"%>

<head>
<%@ include file="../includes/head.jsp"%>
<script src="<c:url value="/resources/js/hightcharts/highcharts.js"/>"></script>
<script src="<c:url value="/resources/js/hightcharts/exporting.js"/>"></script>
<script src="<c:url value="/resources/js/hightcharts/common.js"/>"></script>
</head>


<div id="container" class="shadow-fix">

	<%@ include file="../includes/menu.jsp"%>

	<div id="breadcrumb">
        <a href="index.php">Home</a> > Scuole > Istituti > Scheda Progetto
    </div>
    <div class="content">
	<c:if test="${not empty scuola}">
	 <div class="social">
		<div id="share"></div>
	 </div>
     <h1 class="title-box-2row">Istituto ${scuola.denominazione}</h1>
          <div class="row">
		    
		    <div class="top-tabs">
				<a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/"/>">INFORMAZIONI</a>
			    <a class="active" href="javascript:void(0);">PROGETTI FORMAZIONE</a>
			    <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fesr/"/>">PROGETTI INFRASTRUTTURA</a>
		     	<a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/bandi/"/>">BANDI</a>
		    </div>
		    
            <div class="gray-block page-detail page-separate-columns">
				<p class="title-grey-detail">
					<a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/${codTipFon}"/>">Progetti formazione</a> > Scheda Progetto
				</p>
					
				<div class="box-left box-left-sb">

					<!-- DATI DESCRITTIVI DEL PROGETTO -->
               		<div class="radius-top-left box-single-white">
               			<c:if test="${not empty datiDescrittiviProg}">
               				<h4 class="title-box">${datiDescrittiviProg.denomOperazione}</h4>
               				<p class="sb">${datiDescrittiviProg.sintesiOperazione}</p>
               				<h4 class="title-box">Obiettivi specifici e risultati attesi</h4>
               				<p class="sb">${datiDescrittiviProg.descObiettivi}</p>
               				<h4 class="title-box">Congruità con il POF della scuola</h4>
               				<p class="sb">${datiDescrittiviProg.descPOF}</p>
               			</c:if>
	              	</div>
                
					<!-- DATI PROGETTO -->	                
                	<div class="box-single-white last-box radius-bottom-left space-mobile-top">
                		<div class="box-center-sb">
							<p class="title-box f-clear">Dati progetto</p>
							<ul class="item-list list-simple">
								<li><span class="key">Codice progetto:</span> <span class="value"><strong>${datiProg.codiceProgetto}</strong></span></li>
								<li class="r"><span class="key">Anno:</span> <span class="value"><strong>${datiProg.annoAvviso}</strong></span></li>
								<li><span class="key">Tipo intervento:</span> <span class="value"><strong>${datiProg.tipoIntervento}</strong></span></li>
								<li class="r"><span class="key">Azione/Sottoazione:</span> <span class="value"><strong>${datiProg.codiceAzione}</strong></span></li>
								<li><span class="key">Numero interventi:</span> 
									<a href="<c:url value="/progetti/${prgProgetto}/${scuola.codiceMeccanograficoLowerCase}"/>">
										<span class="value"><strong>${datiProg.numInterventi}</strong></span>
									</a>
								</li>
								<li class="r"><span class="key">Numero partecipanti effettivi :</span> <span class="value"><strong>${datiProg.numEffPartecipanti}</strong></span></li>
								<li><span class="key">Attestati a fine intervento:</span> <span class="value"><strong>${datiProg.numAttestati}</strong></span></li>
								<li class="r"><span class="key">Data inizio progetto:</span> <span class="value"><strong>${datiProg.dataInizioProgetto}</strong></span></li>
								<li><span class="key">Data chiusura attività gestionale:</span> <span class="value"><strong>${datiProg.dataChiusuraAttivita}</strong></span></li>
								<li class="r"><span class="key">Destinatari:</span> <span class="value"><strong>${datiProg.destinatari}</strong></span></li>
							</ul>
						</div>
                	</div>
                	
 				</div>
 				
 				<div class="box-right box-right-sb">
 				
 					<!-- DATI FINANZIARI -->	         
                	<div class="first-box radius-top-right box-single-white space-mobile-top">
						<h4 class="title-box">Dati finanziari</h4>
						<ul class="item-list list-simple">
							<li><span class="key">Tipo finanziamento:</span> <span class="value"><strong>${datiFinanziariProg.tipoFinanziamento}</strong></span></li>
							<li><span class="key">Importo Autorizzato:</span> <span class="value"><strong>&euro; <fmt:formatNumber value="${datiFinanziariProg.importoAutorizzato}"/></strong></span></li>
							<li><span class="key">Importo Pagato:</span> <span class="value"><strong>&euro; <fmt:formatNumber value="${datiFinanziariProg.importoErogato}"/></strong></span></li>
							<li><span class="key">Numero Protocollo Autorizzazione:</span> <span class="value"><strong>${datiFinanziariProg.numeroProtocollo}</strong></span></li>
							<li><span class="key">Data Protocollo Autorizzazione:</span> <span class="value"><strong>${datiFinanziariProg.dataProtocollo}</strong></span></li>
						</ul>
					</div>
					
					<!-- DATI RISORSE UTILI -->
                	<div class="last-box box-single-white radius-bottom-right space-mobile-top">
						<h4 class="title-box">Risorse utili</h4>
							<ul class="item-list list-simple item-list-sb">
								<c:if test="${not empty documentList}">
									<c:forEach var="row" items='${documentList}' varStatus="count">
										<li><a href="${serverAllegati}/outdoc.php?flname=${row}">Allegato ${count.count}</a></li>
									</c:forEach>
								</c:if>
								<c:if test="${galleryPresent}">
									<li><a href="<c:url value="/progetti/gallery/${prgProgetto}/${scuola.codiceMeccanograficoLowerCase}/${codTipFon}"/>">Gallery</a></li>
								</c:if>
								<c:if test="${videoPresent}">
									<li><a href="<c:url value="/progetti/video/${prgProgetto}/${scuola.codiceMeccanograficoLowerCase}/${codTipFon}"/>">Video</a></li>
								</c:if>
								<c:if test="${not empty facebookUrl || not empty twitterUrl}">
									<li>
										Social Network: <a href="${facebookUrl}"><img src="<c:url value="/resources/img/fb.jpg"/>"></a> <a href="${twitterUrl}"><img src="<c:url value="/resources/img/tw.jpg"/>"></a>
									</li>
								</c:if>
							</ul>
					</div>
				</div>
            </div>
        </div>
        </c:if>
        <c:if test="${empty scuola}">
			<br/>
			<h1 class="title-box-2row">Attenzione : scuola non trovata</h1>
			<div class="row">
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
			</div>
		</c:if>
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
					{data: "Istituto Ettore Majorana", lat: "", lng: "", address:"Viale Romania 32, 00187 Roma"}

				]
			});

		};
	})(jQuery);
	</script>

	<!-- includere solo nella pagina galleria immagini -->
	<script src="<c:url value="/resources/js/ekko-lightbox.js"/>"></script>
	<!-- includere solo nella pagina galleria immagini -->

	<script src="<c:url value="/resources/js/jquery/tablesaw.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery/functions.lib.js"/>"></script>

</body>
</html>



