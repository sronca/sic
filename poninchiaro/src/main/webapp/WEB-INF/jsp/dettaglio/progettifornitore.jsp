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
	        	> Beneficiari 
	        			> <a href="<c:url value="${linkElencoFornitori}"/>">Fornitori</a>
	        				> ${fornitore.denominazione}
	        					> Dettaglio progetti
	    </div>
	    
	    <div class="content space-bottom">
	     <c:if test="${not empty fornitore}">
	     
		     <h1>${fornitore.denominazione}: Lista progetti<span class="number-found">Numero progetti trovati: <b>${numeroprogetti}</b></span></h1>
		     <div class="row">		    


             	<div class="page-detail gray-block white-row">			
			
					<table class="tablesaw tablesaw-stack" data-tablesaw-mode="stack">
					  <thead>
						<tr>
						    <th class="td-m"scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">Codice</th>
							<th class="td-xxl t-left" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Titolo</th>		
							<th class="t-left" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Azione / sottoazione</th>
							<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="3">Stato</th>
							<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="4">Numero<br/>protocollo</th>
							<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="5">Data<br/>protocollo</th>
							<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="6">Ammontare<br>assegnato</th>
							<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="7">Totale<br>pagato</th>
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
									<td>${vo.codiceProgetto}</td>
									<td class="t-left">${vo.titolo}</td>
									<td class="t-left">${vo.codiceAzione} - ${vo.codiceSottoazione} - ${vo.sottoazione}</td>
									<td>${vo.stato}</td>
									<td>${vo.numeroProtocollo}</td>
									<td>${vo.dataProtocollo}</td>
									<td>&euro; <fmt:formatNumber value="${vo.importoAutorizzato}" /></td>
									<td>&euro; <fmt:formatNumber value="${vo.importoErogato}" /></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty pagedListHolder.pageList}">
							<tr>
								<td colspan="8">Nessun progetto trovato</td>
							</tr>
						</c:if>						
					  </tbody>
				    </table>
			    
				    <div class="update">
				     <span>Dati aggiornati al: ${dataAggiornamento}</span>
				    </div>

					<div class="pag">
						<c:url value="/beneficiari/fornitori/${fornitore.desNomScuNorm}/${fornitore.codFornitore}/${fornitore.prgFornitore}/progetti/" var="pagedLink">
						    <c:param name="page" value="~"/>
						</c:url>
						<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
					</div>			    
        
                </div>





		     </div>
	        </c:if>
			<c:if test="${empty fornitore}">
				<br/>
				<h1 class="title-box-2row">Attenzione : fornitore non trovata</h1>
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



