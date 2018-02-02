<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>

<html>
    <head>
        <%@ include file="../includes/head_dettaglio.jsp" %>
    </head>
    <body data-codscuut="${scuola.codScuUt}" data-desnomscu="${scuola.desNomScuNorm}">

        <%@ include file="../includes/toolbar_header.jsp" %>

        <section class="sc-internal-main sc-detail">

            <%@ include file="../includes/breadcrumbandtitle.jsp" %>
            <%@ include file="../includes/menu.jsp" %>

            <div class="sc-main-content-detail">
                <div class="sc-wrapper sc-wrapper-alt">
                    <div class="sc-table-table sc-external-box">
                        <div class="sc-table-row sc-external-box">

                            <%@ include file="../includes/menu_aside_alunni.jsp" %>

                            <article class="sc-internal-content sc-table-cell">
                            	<h2>Alunni e classi</h2>
                            	<p class="sc-note-small">Passando con il mouse sui grafici, il tooltip mostra dati e periodi di riferimento; cliccando sugli elementi della legenda i grafici si modificano. L'icona del download consente di scaricare ciascun grafico in diversi formati e di stamparlo</p>
                                <div class="sc-cols">
                                    <div class="sc-col-1-2">
                                        <p class="sc-center"><strong>${titoloTabellaAlunniPerAnnoCorso}</strong></p>

                                        <div class="">
                                        	<c:if test="${not empty alunniPerAnnoCorso}">
	                                            <table class="sc-table sc-table-standard">
	                                                <thead>
	                                                <tr>
	                                                    <th>Anno di corso</th>
	                                                    <th>Alunni</th>
	                                                    <th>Classi</th>
	                                                    <th>N° medio alunni per classe</th>
	                                                </tr>
	                                                </thead>
	                                                <tbody>
	                                                <c:forEach items="${alunniPerAnnoCorso}" var="alunni">
	                                                    <tr>
	                                                        <td data-col-1="Anno di Corso" class="sc-col-1 sc-center">${alunni.perAnnCor}</td>
	                                                        <td data-col-2="Alunni" class="sc-col-2 sc-center">${alunni.numAlu}</td>
	                                                        <td data-col-3="Classi" class="sc-col-3 sc-center">${alunni.numCla}</td>
	                                                        <td data-col-4="N° Medio Alunni per Classe" class="sc-col-4 sc-center">${alunni.aluPerClaFormatted}</td>
	                                                    </tr>
	                                                </c:forEach>
	                                                </tbody>
	                                            </table>
	                                            <c:if test="${presenteAnno6}">
	                                            	<p class="sc-note">${noteTabellaAlunniPerAnnoCorso}</p>
                                            	</c:if> 
                                            </c:if>
                                            <c:if test="${empty alunniPerAnnoCorso}">
                                            	<p class="sc-center">Dati non disponibili per la scuola</p>
                                            </c:if>                                            
                                        </div>
                                        <c:if test="${not empty alunniPerAnnoCorso}">
											<div class="chart-explain chart-explain-clear">
						                		<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaAlunniPerAnnoCorso}</p>
						                	</div>
					                	</c:if>                                       
                                    </div>
                                    <div class="sc-col-1-2">
                                        <p class="sc-center"><strong>${titoloTabellaListaClassi}</strong></p>

                                        <div class="">
                                        	<c:if test="${not empty listaClassi}">
	                                            <table class="sc-table sc-table-standard">
	                                                <thead>
	                                                <tr>
	                                                    <th>Descrizione</th>
	                                                    <th>Classi</th>
	                                                </tr>
	                                                </thead>
	                                                <tbody>
		                                                <c:forEach items="${listaClassi}" var="classi">
		                                                    <tr>
		                                                        <td data-col-1="Descrizione" class="sc-col-1">${classi.descrizione}</td>
		                                                        <td data-col-2="Classi" class="sc-col-2 sc-center">${classi.numeroClassi}</td>
		                                                    </tr>
		                                                </c:forEach>
	                                                </tbody>
	                                            </table>
	                                            <p class="sc-note">${noteTabellaListaClassi}</p>
                                            </c:if>
                                            <c:if test="${empty listaClassi}">
                                            	<p class="sc-center">Dati non disponibili per la scuola</p>
                                            </c:if>                                            
                                        </div>
                                        <c:if test="${not empty listaClassi}">
                                        	<p class="sc-note">${noteTabellaListaClassi}</p>
											<div class="chart-explain chart-explain-clear">
						                		<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaListaClassi}</p>
						                	</div>
					                	</c:if>                            
                                    </div>
                                </div>
                                
                                <div class="sc-cols">
                                    <div class="sc-col-full">
                                        <p class="sc-center"><strong><span id="chart-andamento-alunni-title"></span></strong></p>
                                        <div class="sc-chart" id="sc-bar-chart-andamento-alunni"></div>
					                	<p class="sc-note" id="chart-andamento-alunni-note"></p>
					                	<div class="chart-explain chart-explain-clear">
					                		<p class="sc-note ellips" data-origwidth="" id="chart-andamento-alunni-approfondisci"></p>
					                	</div>                                   
									</div>                                    
                                </div>
                            </article>


                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script>$(".chart-explain").dotdot();</script>
        <!-- CHARTS-->
        <script src="<c:url value="/resources/js/grafici/highChartsCommon.js"/>"></script>
        <script src="<c:url value="/resources/js/grafici/alunni/andamentoAlunni.js"/>"></script>        
        <%@ include file="../includes/footerDettaglioScuola.jsp" %>
    </body>
</html>