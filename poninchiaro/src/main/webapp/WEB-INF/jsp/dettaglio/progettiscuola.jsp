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
	        		> <a href="<c:url value="/beneficiari/ricerca/"/>">Istituti</a> 
	        			> <a href="<c:url value="${linkRisultatiRicerca}"/>">Risultati di ricerca</a>
	        				> <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/"/>">${scuola.denominazione}</a>
	        					<c:if test="${codTipFon eq 'FSE' or codTipFon eq 'fse'}">
	        					> Progetti Formazione
	        					</c:if>
	        					<c:if test="${codTipFon eq 'FESR' or codTipFon eq 'fesr'}">
	        					> Progetti Infrastruttura
	        					</c:if>	        					
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
				     <c:if test="${codTipFon eq 'FSE' or codTipFon eq 'fse'}">
					     <a class="active" href="javascript:void(0);">PROGETTI FORMAZIONE</a>
					     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fesr/"/>">PROGETTI INFRASTRUTTURA</a>
				     </c:if>
				     <c:if test="${codTipFon eq 'FESR' or codTipFon eq 'fesr'}">
					     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fse/"/>">PROGETTI FORMAZIONE</a>
					     <a class="active" href="javascript:void(0);">PROGETTI INFRASTRUTTURA</a>
				     </c:if>				     
				     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/bandi/"/>">BANDI</a>
				    </div>


            <div class="gray-block page-detail">			 
				<span class="title-grey-block">
					<c:if test="${codTipFon eq 'FSE' or codTipFon eq 'fse'}">Progetti formazione </c:if>
					<c:if test="${codTipFon eq 'FESR' or codTipFon eq 'fesr'}">Progetti infrastruttura </c:if>
					<c:if test="${not empty anno}">anno: ${anno}</c:if>
				</span>
				<div class="block-search">
					<form:form action="/poninchiaro/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/${codTipFon}/"
										 method="get" 
										 commandName="filtroRicerca" 
					>
					<span class="text-input">Scegli l'anno</span>
					<span class="input-input">
			             <form:select path="anno"
								title="Scegli l'anno"
								items="${anni}"
						 />
	                </span>
					<span class="input-send"><input type="submit" class="search-y" value="CERCA"></span>
					</form:form>
                </div> 				
			
				<table class="tablesaw tablesaw-stack white-row label-cell-content-large" data-tablesaw-mode="stack">
				  <thead>
					<tr>
						<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-sortable-default-col data-tablesaw-priority="3">Codice</th>
						<th class="td-l t-left" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Titolo</th>
						<th class="t-left" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Azione / sottoazione</th>
						<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Stato</th>
						<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Importo<br>autorizzato</th>
						<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="1">Importo<br>pagato</th>
						<th class="td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="4">Scheda</th> 
					</tr>
				  </thead>
				  <tbody>
				  	<c:if test="${not empty progetti}">
					 	<c:forEach items="${progetti}" var="vo" varStatus="status">
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
								<td>&euro; <fmt:formatNumber value="${vo.importoAutorizzato}" /></td>
								<td>&euro; <fmt:formatNumber value="${vo.importoErogato}" /></td>
								<!-- <td><a class="link-prj" href="javascript:void(0);"></a></td> -->
								<td><a class="link-prj" href="<c:url value="/progetti/${codTipFon}/${vo.prgProgetto}/${scuola.codiceMeccanograficoLowerCase}/"/>"></a></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty progetti}">
						<tr>
							<td colspan="7">Nessun progetto trovato</td>
						</tr>
					</c:if>					
				  </tbody>
			    </table>
			    
			    <div class="update">
			     <span>Dati aggiornati al: ${dataAggiornamento}</span>
			    </div>			    
        
            </div>





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



