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
	        <a href="<c:url value="/"/>">Home</a> 
	        		> <a href="<c:url value="/beneficiari/ricerca/"/>">Istituti</a> 
	        			> <a href="<c:url value="${linkRisultatiRicerca}"/>">Risultati di ricerca</a>
	        				> ${scuola.denominazione}
	    </div>
	    
	    <div class="content">
	     <c:if test="${not empty scuola}">
			 <div class="social">
				<div id="share"></div>
			 </div>
		     <h1 class="title-box-2row">Istituto ${scuola.denominazione}</h1>
		          <div class="row">		    
				    <div class="top-tabs">
				     <a class="active" href="javascript:void(0);">INFORMAZIONI</a>
				     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fse/"/>">PROGETTI FORMAZIONE</a>
				     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fesr/"/>">PROGETTI INFRASTRUTTURA</a>
				     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/bandi/"/>">BANDI</a>
				    </div>
		            <div class="gray-block page-detail">
		                
						<!-- flex -->
						<div class="flexit f-clear">
							<div class="box-left">
				                 <c:if test="${not empty fotoScuola}">
				                     <img src="<c:url value="/render/document/${scuola.codiceMeccanografico}?datAnnScoRil=${scuola.datAnnScoRil}&codTipFil=${fotoScuola.codTipFil}&prgDoc=${fotoScuola.prgDoc}&x=${String.valueOf(System.currentTimeMillis())}"/>" />
				                 </c:if>
				                 <c:if test="${empty fotoScuola}">
				                 	<img src="<c:url value="/resources/img/foto-non-disponibile.png" />" />
				                 </c:if>								
								<a class="linked" href="http://cercalatuascuola.istruzione.it/cercalatuascuola/istituti/${scuola.codiceMeccanografico}/${scuola.desNomScuNorm}/">Maggiori informazioni<br>sul sito "Scuola in Chiaro"</a>
							</div>
							<div class="box-right">
								<h4 class="title-box">La scuola</h4>
								<ul class="item-list list-simple no-margin-bottom">
									<li>Codice: <strong>${scuola.codiceMeccanografico}</strong></li>
									<li>Indirizzo: <strong>${scuola.indirizzoCompleto}</strong></li>
									<c:if test="${not empty scuola.email}">
										<li><span class="towrap"><a href="mailto:${scuola.email}">${scuola.email}</a></span></li>
									</c:if>
									<c:if test="${not empty scuola.pec}">
										<li><span class="towrap">PEC: <a class="nonblock" href="mailto:${scuola.pec}">${scuola.pec}</a></span></li>
									</c:if>
									<c:if test="${not empty scuola.sitoweb}">
										<li><a class="nonblock" href="javascript:void(0)">${scuola.sitoweb}</a></li>
									</c:if>
									<c:if test="${not empty scuola.telefono}">
										<li><span class="towrap">Telefono: <a class="nonblock" href="tel:${scuola.telefono}">${scuola.telefono}</a></span></li>
									</c:if>
									<c:if test="${not empty dirigente}">
										<li>Dirigente scolastico: <strong>${dirigente}</strong></li>
									</c:if>
								</ul>
							</div>
						</div>
						<!-- flex -->
		
						<div id="map_canvas" class="box-map"></div>		                
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
		
		
		<%@ include file="../includes/footer.jsp"%>
		
	</div>


<%@ include file="../includes/scriptmaps.jsp"%>
<%@ include file="../includes/scriptanalytics.jsp"%>


</body>
</html>



