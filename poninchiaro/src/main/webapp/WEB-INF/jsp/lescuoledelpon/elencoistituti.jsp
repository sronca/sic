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
	        <a href="<c:url value="/"/>">Home</a> > <a href="<c:url value="/scuoledelpon/ricerca/"/>">Le scuole del PON</a> > Risultati di ricerca
	    </div>
	    
    	<div class="content">
            <h1>Risultati di ricerca<span class="number-found">Numero scuole trovate <b>${numeroscuole}</b></span></h1>
            <p class="filter">Filtri: <b>${filtri}</b></p>

	        <div class="row">
				
					<table class="tablesaw tablesaw-stack" data-tablesaw-mode="stack">
						<thead>
							<tr>
							    <th scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">Istituto</th>
								<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Codice istituto</th>	
								<th class="td-l" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Comune</th>
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Progetti<br>formazione</th>
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Importo autorizzato<br>formazione</th>
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Progetti<br>infrastruttura</th>
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Importo autorizzato<br>infrastruttura</th>								
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
										<td class="title"><a href="<c:url value="/istituti/${vo.codiceMeccanograficoLowerCase}/${vo.desNomScuNorm}/"/>">${vo.denominazione}</a></td>
										<td>${vo.codiceMeccanografico}</td>
										<td>${vo.comune}</td>
										<td><a href="<c:url value="/istituti/${vo.codiceMeccanograficoLowerCase}/${vo.desNomScuNorm}/progetti/fse/"/>" class="link-bandi"> ${vo.numeroProgettiFSE} </a></td>
										<td>&euro; <fmt:formatNumber value="${vo.importoAutorizzatoFSE}" /></td>
										<td><a href="<c:url value="/istituti/${vo.codiceMeccanograficoLowerCase}/${vo.desNomScuNorm}/progetti/fesr/"/>" class="link-bandi"> ${vo.numeroProgettiFESR} </a></td>
										<td>&euro; <fmt:formatNumber value="${vo.importoAutorizzatoFESR}" /></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty pagedListHolder.pageList}">
								<tr>
									<td colspan="6">Nessun istituto trovato</td>
								</tr>
							</c:if>
							
						</tbody>
					</table>

				    <div class="update">
				     <span>Dati aggiornati al: ${dataAggiornamento}</span>
				    </div>
					
					<div class="pag">
						<c:url value="/scuoledelpon/ricerca/istituti${queryStringWithoutPage}" var="pagedLink">
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



