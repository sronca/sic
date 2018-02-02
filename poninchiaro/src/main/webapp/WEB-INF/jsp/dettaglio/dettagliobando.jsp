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
	        		> <a href="<c:url value="/bandiscuole/ricerca/"/>">Istituti</a> 
	        			> <a href="<c:url value="${linkRisultatiRicerca}"/>">Risultati di ricerca</a>
	        				> <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/"/>">${scuola.denominazione}</a>
	        					> Bandi
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
					 <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fse/"/>">PROGETTI FORMAZIONE</a>
				     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fesr/"/>">PROGETTI INFRASTRUTTURA</a>
				     <a class="active" href="javascript:void(0);">BANDI</a>
				    </div>


					<c:if test="${not empty bando}">
			            <div class="gray-block page-detail page-separate-columns">
							<p class="title-grey-detail"><a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/bandi/"/>">Bandi</a> > Informazioni di dettaglio</p>
			                
			                <div class="box-left box-left-sb">
			                	<div class="radius-top-left box-single-white">
			                		<h4 class="title-box">Descrizione</h4>
			                		<p class="sb">${bando.descrizione}</p>
			                	</div>
			                	
			                	<div class="box-single-white last-box radius-bottom-left space-mobile-top">
			                		<div class="box-center-sb">
										<h4 class="title-box f-clear">Dati bando</h4>
										<ul class="item-list list-simple">
											<li><span class="key">Tipo bando:</span> <span class="value"><strong>${bando.tipoBando}</strong></span></li>
											<li class="r"><span class="key">Fondo:</span> <span class="value"><strong>${bando.fondo}</strong></span></li>
											<li><span class="key">Procedura di gara:</span> <span class="value"><strong>${bando.procedura}</strong></span></li>
											<li class="r"><span class="key">Dal - Al:</span> <span class="value"><strong>${bando.dataPubblicazione}<br>${bando.dataScadenza}</strong></span></li>
											<li><span class="key">Progetto:</span> <span class="value"><strong>${bando.codiceProgetto}</strong></span></li>
											<li class="r"><span class="key">CIG:</span> <span class="value"><strong>${bando.cig}</strong></span></li>
											<li><span class="key">Anno:</span> <span class="value"><strong>${bando.anno}</strong></span></li>
											<li class="r"><span class="key">CUP:</span> <span class="value"><strong>${bando.cup}</strong></span></li>
										</ul>
									</div>
								</div>
											                	
			                </div>

			 				<div class="box-right box-right-sb">	
<%-- 			                <div class="first-box radius-top-right box-single-white space-mobile-top">
									<h4 class="title-box">Dati bando</h4>
										<ul class="item-list list-simple">
											<li><span class="key">Tipo bando:</span> <span class="value"><strong>${bando.tipoBando}</strong></span></li>
											<li><span class="key">Fondo:</span> <span class="value"><strong>${bando.fondo}</strong></span></li>
											<li><span class="key">Procedura di gara:</span> <span class="value"><strong>${bando.procedura}</strong></span></li>
											<li><span class="key">Dal - Al:</span> <span class="value"><strong>${bando.dataPubblicazione}<br>${bando.dataScadenza}</strong></span></li>
											<li><span class="key">Progetto:</span> <span class="value"><strong>${bando.codiceProgetto}</strong></span></li>
											<li><span class="key">CIG:</span> <span class="value"><strong>${bando.cig}</strong></span></li>
											<li><span class="key">Anno:</span> <span class="value"><strong>${bando.anno}</strong></span></li>
											<li><span class="key">CUP:</span> <span class="value"><strong>${bando.cup}</strong></span></li>
										</ul>
								</div>                
			                	<div class="last-box box-single-white radius-bottom-right space-mobile-top">
									<h4 class="title-box">Contatti</h4>
									<ul class="item-list list-simple item-list-sb">
										<li><span class="key">Riferimento:</span> <span class="value"><strong>${bando.riferimento}</strong></span></li>
										<li><span class="key">Contatto:</span> <span class="value"><strong>${bando.contatto}</strong></span></li>
										<c:if test="${not empty bando.link}">
											<li><a href="${bando.link}">${bando.link}</a></li>
										</c:if>
									</ul>
								</div>
--%>
			                	<div class="first-box radius-top-right box-single-white space-mobile-top">
									<h4 class="title-box">Contatti</h4>
									<ul class="item-list list-simple item-list-sb">
										<c:if test="${not empty bando.riferimento or not empty bando.contatto}">
											<li><span class="key">Riferimento:</span> <span class="value"><strong>${bando.riferimento}</strong></span></li>
											<li><span class="key">Contatto:</span> <span class="value"><strong>${bando.contatto}</strong></span></li>
										</c:if>
										<c:if test="${empty bando.riferimento and empty bando.contatto}">
											<li><span class="key">Riferimento:</span> <span class="value"><strong></strong></span></li>
											<c:if test="${empty scuola.email and not empty scuola.telefono}">
												<li><span class="key">Contatto:</span> <span class="value"><strong>${scuola.telefono}</strong></span></li>
											</c:if>
											<c:if test="${empty scuola.telefono and not empty scuola.email}">
												<li><span class="key">Contatto:</span> <span class="value"><strong>${scuola.email}</strong></span></li>
											</c:if>
											<c:if test="${not empty scuola.email and not empty scuola.telefono}">
												<li><span class="key">Contatto:</span> <span class="value"><strong>${scuola.telefono} - ${scuola.email}</strong></span></li>
											</c:if>
										</c:if>
										<c:if test="${not empty bando.link}">
											<li><a href="${bando.link}">${bando.link}</a></li>
										</c:if>
										<c:if test="${empty bando.link}">
											<li>
												<span class="key">Sito web:</span>
												<span class="value">
													<a href="http://${scuola.sitoweb}"><strong>${scuola.sitoweb}</strong></a>
												</span>
											</li>
										</c:if>
									</ul>
								</div>
							</div>			                
			                
			            </div>
		            </c:if>

					<c:if test="${empty bando}">
			            <div class="gray-block page-detail page-separate-columns">
							<p class="title-grey-detail"><a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/bandi/"/>">Bandi</a> > Informazioni di dettaglio</p>
			                <div class="box-left box-left-sb radius-top-left">
			                	<p class="title-box">Attenzione : bando non trovato</p>
			                </div>
			            </div>
		            </c:if>






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

<%@ include file="../includes/scriptanalytics.jsp"%>
</body>


</html>



