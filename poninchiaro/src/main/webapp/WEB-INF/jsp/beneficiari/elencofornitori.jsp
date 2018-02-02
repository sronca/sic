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
	        <a href="<c:url value="/"/>">Home</a> > Beneficiari > Fornitori
	    </div>
	    
    	<div class="content">
            <h1>Lista beneficiari fornitori<span class="number-found">Numero fornitori trovati <b>${numerofornitori}</b></span></h1>

	        <div class="row no-p">
				
					<table class="tablesaw tablesaw-stack" data-tablesaw-mode="stack">
						<thead>
							<tr>
							    <th scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">Fornitori</th>
								<th class="td-l" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Importi autorizzati</th>
								<th class="td-l" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Importi pagati</th>
								<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Progetti</th>
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
										<td class="title">${vo.denominazione}</td>
										<td>&euro; <fmt:formatNumber value="${vo.importoAutorizzato}" /></td>
										<td>&euro; <fmt:formatNumber value="${vo.importoErogato}" /></td>
										<td>
											<a href="<c:url value="/beneficiari/fornitori/${vo.desNomScuNorm}/${vo.codFornitore}/${vo.prgFornitore}/progetti/"/>" class="link-bandi"> ${vo.numeroProgetti} </a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty pagedListHolder.pageList}">
								<tr>
									<td colspan="4">Nessun fornitore trovato</td>
								</tr>
							</c:if>							
						</tbody>
					</table>

				    <div class="update">
				     <span>Dati aggiornati al: ${dataAggiornamento}</span>
				    </div>
					
					<div class="pag">
						<c:url value="/beneficiari/fornitori${queryStringWithoutPage}" var="pagedLink">
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



