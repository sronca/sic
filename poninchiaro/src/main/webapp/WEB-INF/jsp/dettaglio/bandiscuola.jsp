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
	        		> <a href="<c:url value="/bandiscuole/ricerca/"/>">Istituti</a> 
	        			> <a href="<c:url value="${linkRisultatiRicerca}"/>">Risultati di ricerca</a>
	        				> <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/"/>">${scuola.denominazione}</a>
	        					> Bandi
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
					 <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fse/"/>">PROGETTI FORMAZIONE</a>
				     <a href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/progetti/fesr/"/>">PROGETTI INFRASTRUTTURA</a>
				     <a class="active" href="javascript:void(0);">BANDI</a>
				    </div>


            <div class="gray-block page-detail">			 
				<span class="title-grey-block">
					Bandi scuola - Tipo bando: ${tipoBando}
				</span>
				<div class="block-search">
					<form:form action="/poninchiaro/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/bandi/"
										 method="get" 
										 commandName="filtroRicerca" 
					>
					<span class="text-input">Scegli il tipo bando</span>
					<span class="input-input">
			             <form:select path="tipoBando"
								title="Scegli il tipo bando"
								items="${tipiBando}"
						 />
	                </span>
					<span class="input-send"><input type="submit" class="search-y" value="CERCA"></span>
					</form:form>
                </div> 				
			
				<table class="tablesaw tablesaw-stack white-row" data-tablesaw-mode="stack">
				  <thead>
					<tr>
						<th class="title td-xl" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="persist">Procedura</th>
						<th class="t-left" scope="col" data-tablesaw-sortable-col data-tablesaw-sortable-default-col data-tablesaw-priority="3">Descrizione</th>
						<th class=" td-s" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Dal - Al</th>
						<th class="td-m" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="2">Codice progetto</th>
						<th class="td-xs" scope="col" data-tablesaw-sortable-col data-tablesaw-priority="4">Scheda</th>
					</tr>
				  </thead>
				  <tbody>
				  	<c:if test="${not empty bandi}">
					 	<c:forEach items="${bandi}" var="vo" varStatus="status">
							<c:if test="${status.index mod 2 eq 1}">
								<tr class="odd">
							</c:if>
							<c:if test="${status.index mod 2 eq 0}">
								<tr>
							</c:if>
								<td class="title">${vo.procedura}</td>
								<td class="t-left">${vo.descrizione}</td>
								<td>${vo.dataPubblicazione}<br/>${vo.dataScadenza}</td>
								<td>${vo.codiceProgetto}</td>
								<td><a class="link-prj" href="<c:url value="/istituti/${scuola.codiceMeccanograficoLowerCase}/${scuola.desNomScuNorm}/bandi/${vo.codiceBando}/"/>"></a></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty bandi}">
						<tr>
							<td colspan="4">Nessun bando trovato</td>
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



