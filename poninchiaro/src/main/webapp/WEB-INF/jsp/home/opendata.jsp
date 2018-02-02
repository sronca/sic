
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
	        <a href="<c:url value="/"/>">Home</a> > Open Data
	    </div>
	    
    	<div class="content">
            <h1>Open Data</h1>
	        <div class="row">
				<table class="tablesaw tablesaw-stack open-data-table"  data-tablesaw-mode="stack">
					<thead>
						<tr>
						    <th scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">Tipologia</th>
							<th class="td-l" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Download Dati</th>
							<th class="td-l" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Download Tracciato</th>
							<th class="td-xl" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Ultimo Aggiornamento</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="title">Istituti Beneficiari</td>
							<td><a href="<c:url value="/opendata/progetti/istituti/"/>"><img src="<c:url value="/resources/img/download.png"/>" alt="Scarica i dati"></a></td>
							<td><a href="<c:url value="/resources/doc/Tracciato_beneficiari_istituti.xlsx"/>"><img src="<c:url value="/resources/img/excel.png"/>" alt="Scarica il tracciato"></a></td>
							<td>${dataAggiornamentoProgettiIstituti}</td>
						</tr>
						<tr class="odd">
							<td class="title">Fornitori Beneficiari</td>
							<td><a href="<c:url value="/opendata/progetti/fornitori/"/>"><img src="<c:url value="/resources/img/download.png"/>" alt="Scarica i dati"></a></td>
							<td><a href="<c:url value="/resources/doc/Tracciato_beneficiari_fornitori.xlsx"/>"><img src="<c:url value="/resources/img/excel.png"/>" alt="Scarica il tracciato"></a></td>
							<td>${dataAggiornamentoProgettiFornitori}</td>
						</tr>
						<tr>
							<td class="title">Bandi delle Scuole</td>
							<td><a href="<c:url value="/opendata/bandi/scuole/"/>"><img src="<c:url value="/resources/img/download.png"/>" alt="Scarica i dati"></a></td>
							<td><a href="<c:url value="/resources/doc/Tracciato_bandi_delle_scuole.xlsx"/>"><img src="<c:url value="/resources/img/excel.png"/>" alt="Scarica il tracciato"></a></td>
							<td>${dataAggiornamentoBandiScuole}</td>
						</tr>
 						<tr class="odd">
							<td class="title">Opportunit&agrave; di Finanziamento</td>
							<td><a href="<c:url value="/opendata/bandi/amministrazione/"/>"><img src="<c:url value="/resources/img/download.png"/>"  alt="Scarica i dati"></a></td>
							<td><a href="<c:url value="/resources/doc/Tracciato_opportunita.xlsx"/>"><img src="<c:url value="/resources/img/excel.png"/>" alt="Scarica il tracciato"></a></td>
							<td>${dataAggiornamentoBandiAmministrazione}</td>
						</tr>					
					</tbody>
				</table>
	        </div>
	        <div class="gray-block page-open-data">
	        	<div class="box-left box-left-sb">
	        		<h4 class="title-box">Come scaricare gli Open Data</h4>
	
	        		<p class="sb">Questa sezione ospita in formato aperto un insieme di dataset sulla programmazione <strong>2014-2020</strong>. Le informazioni hanno un livello di dettaglio maggiore rispetto a quanto riportato in altre sezioni del portale.<br>Tutti i dati sono rilasciati in formato <strong>CSV</strong> (leggibile da Excel).</p>
	
					<p class="sb">Al momento è possibile scaricare i dati relativi alle seguenti aree-tematiche:</p>
	
					<ul>
						<li>Progetti autorizzati agli istituti scolastici che hanno beneficiato dei fondi europei</li>
						<li>Progetti autorizzati ai fornitori che hanno beneficiato dei fondi europei</li>
						<li>Dati di dettaglio sui bandi delle scuole</li>
						<li>Avvisi pubblici emanati dall'autorità di gestione</li>
					</ul>
	
					<p class="sb">Ogni dataset presente in questa sezione è caratterizzato da quattro colonne:</p>
	
	
					<ul>
						<li><b>Tipologia</b>: indica la tematica del dataset</li>
						<li><b>Download Dati</b>: clicca per scaricare i dati in formato ".csv"</li>
						<li><b>Download Tracciato</b>: download del tracciato del file in formato ".xls"</li>
						<li><b>Ultimo Aggiornamento</b>: riporta la data di pubblicazione dell'ultimo aggiornamento</li>
					</ul>
	
	
	
	        	</div>
	        </div>	        
        </div>	    		
	
		
		
		<%@ include file="../includes/footer.jsp"%>
		
	</div>

<%@ include file="../includes/scriptanalytics.jsp"%>
</body>


</html>



