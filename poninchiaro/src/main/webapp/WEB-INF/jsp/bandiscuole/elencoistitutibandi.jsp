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
	        <a href="<c:url value="/"/>">Home</a> > Bandi scuole > <a href="<c:url value="/bandiscuole/ricerca/"/>">Istituti</a> > Risultati di ricerca
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
								<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Numero bandi</th>
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
										<td>
											<a href="<c:url value="/istituti/${vo.codiceMeccanograficoLowerCase}/${vo.desNomScuNorm}/bandi?tipoBando=${tipoBando}"/>" class="link-bandi"> ${vo.numeroProgetti} </a>
										</td>
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
				     <c:if test="${not empty dataAggiornamento}">
				     	<span>Dati aggiornati al: ${dataAggiornamento}</span>
				     </c:if>
				    </div>
					
					<div class="pag">
						<c:url value="/bandiscuole/ricerca/istituti${queryStringWithoutPage}" var="pagedLink">
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



