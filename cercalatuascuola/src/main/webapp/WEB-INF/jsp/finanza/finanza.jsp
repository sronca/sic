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

                    <%@ include file="../includes/menu_aside_finanza.jsp" %>

                    <article class="sc-internal-content sc-table-cell">
                        <h2>Entrate per fonti di finanziamento</h2>
                        <p class="sc-note-small">Passando con il mouse sui grafici, il tooltip mostra dati e periodi di riferimento; cliccando sugli elementi della legenda i grafici si modificano. L'icona del download consente di scaricare ciascun grafico in diversi formati e di stamparlo</p>


                        <div class="sc-cols">
                            <div class="sc-col-full">
                                <p class="sc-center"><strong><span id="chart-entrate-fonti-finanziamento-title"></span></strong>
                                </p>

                                <div class="sc-chart" id="sc-pie-chart-entrate-fonti-finanziamento"></div>
                                <p class="sc-note" id="chart-entrate-fonti-finanziamento-note"></p>
			                	<div class="chart-explain chart-explain-clear">
			                		<p class="sc-note ellips" data-origwidth="" id="chart-entrate-fonti-finanziamento-approfondisci"></p>
			                	</div>                                
                            </div>
                        </div>
                        <div class="sc-cols">
                            <div class="sc-col-full">
                                <p class="sc-center"><strong>${titoloTabellaPercentualiEntrate}</strong></p>

                                <div class="">
                                	<c:if test="${not empty listaEntrate}">
                                    <table class="sc-table sc-table-standard">
                                        <thead>
                                        <tr>
                                            <th>Risorse assegnate da</th>
                                            <th>Dettaglio finanziamenti</th>
                                            <th>Funzionamento generale</th>
                                            <th>Spese pulizia</th>
                                            <th>Risorse per retribuzione accessoria</th>
                                            <th>Risorse per supplenze brevi</th>
                                            <th>Stipendi personale di ruolo</th>
                                            <th>Stipendi personale supplente</th>
                                            <th>Ampliamento offerta formativa</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listaEntrate}" var="entrata">
                                            <tr>
                                                <td>${entrata.desAggEnt}</td>
                                                <td>${entrata.desDetEnt}</td>
                                                <td>${entrata.prcFnzGen}</td>
                                                <td>${entrata.prcSpePul}</td>
                                                <td>${entrata.prcRisRetApe}</td>
                                                <td>${entrata.prcRisSupBre}</td>
                                                <td>${entrata.prcStiPerRuo}</td>
                                                <td>${entrata.prcStiPerSup}</td>
                                                <td>${entrata.prcAmpOffFor}</td>
                                            </tr>
                                        </c:forEach>                                    
                                        </tbody>
                                    </table>
					                <p class="sc-note">${noteTabellaPercentualiEntrate}</p>
					                <div class="chart-explain chart-explain-clear">
				                  		<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaPercentualiEntrate}</p>
				                    </div>                                            
                                    </c:if>
                                    <c:if test="${empty listaEntrate}">
                                    	<p class="sc-center">Dati non disponibili per la scuola</p>
                                    </c:if>                                    
                                </div>                               
                            </div>
                            <div class="sc-col-full">
                                <p class="sc-center"><strong>${titoloTabellaImportiEntrate}</strong></p>

                                <div class="">
                                	<c:if test="${not empty listaEntrate}">
                                    <table class="sc-table sc-table-standard">
                                        <thead>
                                        <tr>
                                            <th>Risorse assegnate da</th>
                                            <th>Dettaglio finanziamenti</th>
                                            <th>Funzionamento generale</th>
                                            <th>Spese pulizia</th>
                                            <th>Risorse per retribuzione accessoria</th>
                                            <th>Risorse per supplenze brevi</th>
                                            <th>Stipendi personale di ruolo</th>
                                            <th>Stipendi personale supplente</th>
                                            <th>Ampliamento offerta formativa</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listaEntrate}" var="entrata">
                                            <tr>
                                                <td>${entrata.desAggEnt}</td>
                                                <td>${entrata.desDetEnt}</td>
                                                <td>${entrata.impFnzGen}</td>
                                                <td>${entrata.impSpePul}</td>
                                                <td>${entrata.impRisRetApe}</td>
                                                <td>${entrata.impRisSupBre}</td>
                                                <td>${entrata.impStiPerRuo}</td>
                                                <td>${entrata.impStiPerSup}</td>
                                                <td>${entrata.impAmpOffFor}</td>
                                            </tr>
                                        </c:forEach>                                      
                                        </tbody>
                                    </table>
					                <p class="sc-note">${noteTabellaImportiEntrate}</p>
					                <div class="chart-explain chart-explain-clear">
				                  		<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaImportiEntrate}</p>
				                    </div>                                            
                                    </c:if>
                                    <c:if test="${empty listaEntrate}">
                                    	<p class="sc-center">Dati non disponibili per la scuola</p>
                                    </c:if>                                     
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
<script src="<c:url value="/resources/js/grafici/finanza/entrateFontiFinanziamento.js"/>"></script>
<%@ include file="../includes/footerDettaglioScuola.jsp" %>
</body>
</html>