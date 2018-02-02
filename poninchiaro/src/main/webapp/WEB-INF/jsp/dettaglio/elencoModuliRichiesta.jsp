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
	        <a href="<c:url value="/"/>">Home</a> > Beneficiari > <a href="<c:url value="/progetti/fse/${prgProgetto}/${codScuUt}"/>">Scheda progetto</a> > Elenco Richieste
	    </div>
	    
    	<div class="content">
            <h1>Elenco Richieste</h1>

	        <div class="row">
				
					<table class="tablesaw tablesaw-stack" data-tablesaw-mode="stack">
						<thead>
							<tr>
							    <th scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">Titolo Richiesta</th>
								<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Descrizione Richiesta</th>	
								<th class="td-l" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Numero ORE</th>
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Numero Partecipanti<br> a priori</th>
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Numero Partecipanti <br>effettivi</th>
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Numero Attestati</th>
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Data Inizio</th>
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Data fine</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty pagedListHolder.pageList}">
								<c:forEach items="${pagedListHolder.pageList}" var="vo" varStatus="status">										
									<c:if test="${status.index mod 2 eq 1}">
										<tr class="odd">
									</c:if>
									<c:if test="${status.index mod 2 eq 0}">
										<tr>
									</c:if>
 										<td>${vo.titoloRichiesta}</td> 
										<td>${vo.descRichiesta}</td>
										<td>${vo.numOre}</td>
										<td>${vo.numPartecipanti}</td>
										<td>${vo.numEffPartecipanti}</td>
										<td>${vo.numAttestati}</td>
										<td>${vo.dataInizio}</td>
										<td>${vo.dataFine}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty pagedListHolder.pageList}">
								<tr>
									<td colspan="6">Nessun modulo richiesta trovato</td>
								</tr>
							</c:if>
							
						</tbody>
					</table>

				    
					<div class="pag">
						<c:url value="/progetti/${prgProgetto}/${codScuUt}${queryStringWithoutPage}" var="pagedLink">
						    <c:param name="page" value="~"/>
						</c:url>
						<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
					</div>
						

	        </div>
        

        </div>	    		
		
		
		<%@ include file="../includes/footer.jsp"%>
		
	</div>

<%@ include file="../includes/scriptanalytics.jsp"%>
</body>


</html>



