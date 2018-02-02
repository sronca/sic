<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp"%>
<%@ include file="../includes/html.jsp"%>

<head>
<%@ include file="../includes/head.jsp"%>

	<script src="<c:url value="/resources/js/highcharts/highcharts.js"/>"></script>
	<script src="<c:url value="/resources/js/highcharts/exporting.js"/>"></script>
	<script src="<c:url value="/resources/js/highcharts/common.js"/>"></script>
	<script src="<c:url value="/resources/js/grafici/progettiPerTipoIntervento.js"/>"></script>

</head>


<body>

	<div id="container" class="shadow-fix">
		
		<%@ include file="../includes/menu.jsp"%>
		
	    <div id="breadcrumb">
	        <a href="<c:url value="/"/>">Home</a> 
	        		> Pon in cifre
	    </div>
	    
		<div class="content">
			<div class="social">
				<div id="share"></div>
			</div>		
			<h2>Progetti</h2>
			<div class="gray-block content-box-value">
				<div class="box-value radius-all">
					<p>Progetti autorizzati: <strong><fmt:formatNumber value="${progettiAutorizzati}" /></strong></p>
					<p>Importo impegnato: <strong>&euro; <fmt:formatNumber value="${importoAutorizzato}" /></strong></p>
				</div>
			</div>
			
			<c:if test="${not empty distribuzioneProgettiPerTipoIntervento}">
				<div class="gray-block content-box-value space-bottom">
					<div class="box-value">
					   <div id="chart-proj-1" class="chart"></div>
					</div>
				</div>
				<h3 class="box-intro">Tabella riassuntiva per tipo di intervento</h3>
				<table class="tablesaw tablesaw-stack img-value space-bottom" data-tablesaw-mode="stack">
					<thead>
						<tr>
							<th class="title td-xxs" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">Tipo fondo</th>
							<th class="t-left td-l" scope="col" data-tablesaw-sortable-col data-tablesaw-sortable-default-col data-tablesaw-priority="3">Categoria Intervento</th>
							<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Numero Progetti</th>
							<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Importo Impegnato Iniziale</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${distribuzioneProgettiPerTipoIntervento}" var="vo" varStatus="status">
							<c:if test="${status.index mod 2 eq 1}">
								<tr class="odd">
							</c:if>
							<c:if test="${status.index mod 2 eq 0}">
								<tr>
							</c:if>					
								<td class="title">${vo.fondo}</td>
								<td class="t-left">${vo.categoriaIntervento}</td>
								<td><fmt:formatNumber value="${vo.numeroProgetti}" /></td>
								<td>&euro; <fmt:formatNumber value="${vo.importoAutorizzato}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="2">TOTALE</td>
							<td><fmt:formatNumber value="${progettiAutorizzati}" /></td>
							<td>&euro; <fmt:formatNumber value="${importoAutorizzato}" /></td>
						</tr>
					</tbody>
				</table>
		
			<h3 id="bymap" class="box-intro">Distribuzione dei progetti autorizzati degli istituti per area territoriale</h3>
			<div class="gray-block content-box-value-small">
				<div class="box-value box-value-small-space">
					<div class="white-block box-search">
						<form:form action="/poninchiaro/ponincifre/progetti/#bymap"
							 method="get" 
							 commandName="filtroRicerca"
							 id="simple-search"
				  		>
							<label for="area" class="pull-left">Scegli </label>
								<form:select path="area"
									title="Filtro per area territoriale"
									items="${areeTerritoriali}"
									aria-label="Sceglie l'area"
									tabindex="10"
									id="map-area"
									data-qstring="area"
									data-hash="bymap"
								/>							
							<input aria-label="invia" type="submit" class="send" value="VAI" tabindex="20">
						</form:form>
						<div class="box-l-s">
							<div class="box-left-table">
								<table class="tablesaw tablesaw-stack img-value space-bottom" data-tablesaw-mode="stack">
									<thead>
										<tr>
											<th class="title td-xxs" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">Regione</th>
											<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Numero Progetti</th>
											<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Importo Impegnato Iniziale</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${distribuzioneProgettiIstitutiPerAreaTerritoriale}" var="vo" varStatus="status">
											<c:if test="${status.index mod 2 eq 1}">
												<tr class="odd">
											</c:if>
											<c:if test="${status.index mod 2 eq 0}">
												<tr>
											</c:if>					
												<td class="title">${vo.regione}</td>
												<td><fmt:formatNumber value="${vo.numeroProgetti}" /></td>
												<td>&euro; <fmt:formatNumber value="${vo.importoAutorizzato}" /></td>
											</tr>
										</c:forEach>
										<tr>
											<td>TOTALE</td>
											<td><fmt:formatNumber value="${progettiAutorizzatiPerArea}" /></td>
											<td>&euro; <fmt:formatNumber value="${importoAutorizzatoPerArea}" /></td>
										</tr>
									</tbody>								
								</table>
							</div>
							<div class="box-right-table">
								<ul>
									<li class="more"><a <c:if test="${filtroRicerca.area eq 'PSVIL'}">class="active"</c:if> href="<c:url value="/ponincifre/progetti/?area=PSVIL#bymap"/>">Aree più sviluppate<span></span></a></li>
									<li class="trans"><a <c:if test="${filtroRicerca.area eq 'TRANS'}">class="active"</c:if> href="<c:url value="/ponincifre/progetti/?area=TRANS#bymap"/>">Aree in transizione<span></span></a></li>
									<li class="less"><a <c:if test="${filtroRicerca.area eq 'MSVIL'}">class="active"</c:if> href="<c:url value="/ponincifre/progetti/?area=MSVIL#bymap"/>">Aree meno sviluppate<span></span></a></li>
									<li class="all">
										<a <c:if test="${empty filtroRicerca.area or filtroRicerca.area eq 'TUTTE'}">class="active"</c:if> href="<c:url value="/ponincifre/progetti/?area=TUTTE#bymap"/>">Tutte le Aree
										<span id="all-area"><span></span><span></span><span></span></span>
										</a>
									</li>
								</ul>
								<img 
									class="map-italia" 
									name="map-italia"
									<c:if test="${filtroRicerca.area eq 'PSVIL'}">
										src="<c:url value="/resources/img/italia-nord.png"/>"
									</c:if>
									<c:if test="${filtroRicerca.area eq 'TRANS'}">
										src="<c:url value="/resources/img/italia-centro.png"/>"
									</c:if>
									<c:if test="${filtroRicerca.area eq 'MSVIL'}">
										src="<c:url value="/resources/img/italia-sud.png"/>"
									</c:if>
									<c:if test="${empty filtroRicerca.area or filtroRicerca.area eq 'TUTTE'}">
										src="<c:url value="/resources/img/italia.png"/>"
									</c:if>																							
									usemap="#m_italia" 
									alt="Immagine aree territoriali distribuzione progetti" />
								<map name="m_italia" id="m_italia">
									<area 
										shape="poly" 
										coords="219,175,239,173,238,178,235,182,233,186,281,214,298,230,297,236,296,242,291,241,272,226,263,220,259,224,255,230,253,234,251,238,250,241,249,247,250,249,264,259,261,272,254,276,250,282,248,289,248,293,244,295,239,302,239,304,213,318,214,348,203,346,195,340,187,333,174,327,158,318,151,315,149,307,156,300,169,299,175,302,187,303,205,301,220,296,226,295,232,294,241,277,228,237,225,236,220,237,214,232,211,228,211,222,208,216,201,214,197,212,192,209,190,207,185,203,183,198,189,192,199,196,203,195,211,192"
										href="<c:url value="/ponincifre/progetti/?area=MSVIL#bymap"/>" 
										alt="aree meno sviluppate" />
									<area 
										shape="poly" 
										coords="41,209,61,200,68,195,75,200,78,209,81,219,80,225,77,230,78,245,78,256,76,263,75,268,67,264,62,265,61,269,57,274,52,274,48,271,44,265,40,217,38,212" 
										href="<c:url value="/ponincifre/progetti/?area=TRANS#bymap"/>" 
										alt="aree in transizione" />
									<area 
										shape="poly" 
										coords="185,144,205,167,213,172,210,177,208,181,206,184,204,186,200,187,198,187,194,187,193,186,191,186,188,185,186,182,184,177,181,176,180,176,178,175,172,173,169,170,167,169,170,160,168,157,166,154,167,153,180,144" 
										href="<c:url value="/ponincifre/progetti/?area=TRANS#bymap"/>" 
										alt="aree in transizione" />
									<area 
										shape="poly"
										coords="180,128,171,114,156,105,145,90,143,77,148,72,143,66,143,58,146,54,151,50,160,48,162,47,167,45,171,45,174,44,176,39,174,37,172,34,176,31,173,29,171,26,175,20,177,20,170,17,162,17,151,16,148,15,143,15,141,16,137,17,135,19,133,21,132,25,135,30,135,35,132,35,130,36,129,37,125,39,121,40,120,40,117,44,113,48,108,47,106,45,101,44,99,43,97,41,96,37,96,35,98,28,100,25,100,21,95,19,92,17,90,17,89,19,89,24,89,26,88,27,84,28,83,27,80,26,75,26,74,26,71,22,67,20,68,24,68,24,67,28,64,31,63,34,62,37,63,41,61,44,57,41,54,37,48,32,48,30,44,24,44,24,41,26,39,28,38,31,37,34,35,36,33,39,33,41,35,46,35,49,35,50,32,51,30,52,27,53,23,54,18,55,17,55,16,56,15,58,14,59,13,63,9,65,3,67,2,67,3,73,4,74,10,77,11,81,8,84,8,85,7,89,7,90,11,95,11,95,14,96,20,97,25,99,30,99,26,104,25,106,23,107,23,110,28,110,41,102,44,99,45,96,47,95,50,92,52,91,57,91,62,91,65,94,71,97,79,98,84,102,92,105,92,109,93,114,94,118,95,122,95,123,97,130,99,135,97,139,101,141,103,144,110,147,110,148,112,152,113,155,117,158,123,157,127,164,128,166,131,169,135,172,140,175,142,178,146,181,147,188,153,189,154,190,158,192,163,195,168,194,173,194,173,190,180,192,180,189,174,180,175,180,166,180,171,180,170,180,164,180,159,171,158,169,157,170,157,166,156,161,158,157,160,157,160,156,160,153,161,153,162,149,163,144,170,144,172,142,180,138,180,138,183,139"
										href="<c:url value="/ponincifre/progetti/?area=PSVIL#bymap"/>"
										alt="aree sviluppate" />
								</map>
								<p>
									Puoi utilizzare la mappa per selezionare le tue aree di interesse.
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			</c:if>
			
			
		</div>	    		
		
		
		<%@ include file="../includes/footer.jsp"%>
		
	</div>
	
<%@ include file="../includes/scriptanalytics.jsp"%>


</body>
</html>



