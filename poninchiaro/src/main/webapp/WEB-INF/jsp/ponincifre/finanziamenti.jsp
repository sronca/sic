<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp"%>
<%@ include file="../includes/html.jsp"%>

<head>
<%@ include file="../includes/head.jsp"%>

	<script src="<c:url value="/resources/js/highcharts/highcharts.js"/>"></script>
	<script src="<c:url value="/resources/js/highcharts/exporting.js"/>"></script>
	<script src="<c:url value="/resources/js/highcharts/common.js"/>"></script>
	<script src="<c:url value="/resources/js/grafici/distribuzionePercentualeImportiProgrammazione.js"/>"></script>
	<script src="<c:url value="/resources/js/grafici/programmatoEdAutorizzato.js"/>"></script>

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
			<h2>Importo programmato</h2>
			<div class="gray-block content-box-value">
				<div class="box-value radius-all">
					<p>Totale Programmato: <strong>&euro; <fmt:formatNumber value="${importoTotale}" /></strong></p>
				</div>
			</div>
			
			<c:if test="${not empty distribuzioneProgrammazioneAreaTerritoriale}">
				<div class="gray-block content-box-value space-bottom">
					<div class="box-value">
					   <div id="chart-finanz-1" class="chart"></div>
					</div>
				</div>
				<h3 class="box-intro">Importo programmato per asse e area territoriale</h3>
				<table class="tablesaw tablesaw-stack img-value space-bottom" data-tablesaw-mode="stack">
				   <thead>
				   	<tr>
						<th class="title td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">Asse</th>
						<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-sortable-default-col data-tablesaw-priority="3">Area meno sviluppata</th>
						<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Area in transizione</th>
						<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Area più sviluppata</th>
						<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Totale</th>
					</tr>
				   </thead>
				   <tbody>
						<c:forEach items="${distribuzioneProgrammazioneAreaTerritoriale}" var="vo" varStatus="status">
							<c:if test="${status.index mod 2 eq 1}">
								<tr class="odd">
							</c:if>
							<c:if test="${status.index mod 2 eq 0}">
								<tr>
							</c:if>					
								<td class="title">${vo.codiceAsse} - (${vo.descrizioneAsse})</td>
								<td>&euro; <fmt:formatNumber value="${vo.importoAreaMS}" /></td>
								<td>&euro; <fmt:formatNumber value="${vo.importoAreaTR}" /></td>
								<td>&euro; <fmt:formatNumber value="${vo.importoAreaPS}" /></td>
								<td>&euro; <fmt:formatNumber value="${vo.importoTotaleAsse}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td>TOTALE</td>
							<td>&euro; <fmt:formatNumber value="${importoTotaleMS}" /></td>
							<td>&euro; <fmt:formatNumber value="${importoTotaleTR}" /></td>
							<td>&euro; <fmt:formatNumber value="${importoTotalePS}" /></td>
							<td>&euro; <fmt:formatNumber value="${importoTotale}" /></td>
						</tr>
					</tbody>
				</table>
		
			<h3 class="box-intro">Programmato ed impegnato</h3>
			<div class="gray-block content-box-value">
				<div class="box-value radius-all">
					<p>Totale programmato: <strong>&euro; <fmt:formatNumber value="${importoTotale}" /></strong></p>
					<p>Totale impegnato: <strong>&euro; <fmt:formatNumber value="${importoTotaleImpegnato}" /></strong></p>
				</div>
			</div>			
			<div class="gray-block content-box-value space-bottom">
				<div class="box-value">
					<div id="chart-finanz-2" class="chart"></div>
				</div>
			</div>
			
			</c:if>
			
			
		</div>	    		
		
		
		<%@ include file="../includes/footer.jsp"%>
		
	</div>
	
<%@ include file="../includes/scriptanalytics.jsp"%>


</body>
</html>



