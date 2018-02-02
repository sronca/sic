<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp"%>
<%@ include file="../includes/html.jsp"%>

<head>
<%@ include file="../includes/head.jsp"%>

	<script src="<c:url value="/resources/js/highcharts/highcharts.js"/>"></script>
	<script src="<c:url value="/resources/js/highcharts/exporting.js"/>"></script>
	<script src="<c:url value="/resources/js/highcharts/common.js"/>"></script>
	<script src="<c:url value="/resources/js/grafici/distribuzionePartecipantiPerArea.js"/>"></script>
	<script src="<c:url value="/resources/js/grafici/distribuzionePartecipantiPerRegione.js"/>"></script>
	<script src="<c:url value="/resources/js/grafici/distribuzionePartecipantiPerCicloScolastico.js"/>"></script>

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
			<h2>Beneficiari</h2>
			<div class="gray-block content-box-value">
				<div class="box-value radius-all">
					<p>Totale istituti: <strong><fmt:formatNumber value="${totaleIstituti}" /></strong></p>
					<p>Totale istituti partecipanti: <strong><fmt:formatNumber value="${totalePartecipanti}" /></strong></p>
				</div>
			</div>
			
			<h3 class="box-intro">Grafico riassuntivo suddiviso per area</h3>
			<div class="gray-block content-box-value space-bottom">
				<div class="box-value">
					<div id="chart-ben-1" class="chart"></div>
				</div>
			</div>
	
			<h3 class="box-intro">Grafico riassuntivo suddiviso per regione</h3>
			<div class="gray-block content-box-value space-bottom">
				<div class="box-value">
					<div id="chart-ben-2" class="chart chart-big"></div>
				</div>
			</div>
	

			<h3 class="box-intro">Distribuzione partecipanti per ciclo scolastico</h3>
			<div class="gray-block content-box-value">
				<div class="box-value radius-all">
					<p>Numero totale istituti partecipanti primo ciclo: <strong><fmt:formatNumber value="${totalePartecipantiPrimoCiclo}" /></strong></p>
					<p>Numero totale istituti partecipanti secondo ciclo: <strong><fmt:formatNumber value="${totalePartecipantiSecondoCiclo}" /></strong></p>
				</div>
			</div>
			<div class="gray-block content-box-value space-bottom">
				<div class="box-value">
					<div id="chart-ben-3" class="chart"></div>
				</div>
			</div>
			
			
		</div>	    		
		
		
		<%@ include file="../includes/footer.jsp"%>
		
	</div>
	
<%@ include file="../includes/scriptanalytics.jsp"%>


</body>
</html>



