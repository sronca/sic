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
                            	<h2>Esiti scrutini</h2>
                            	<p class="sc-note-small">Passando con il mouse sui grafici, il tooltip mostra dati e periodi di riferimento; cliccando sugli elementi della legenda i grafici si modificano. L'icona del download consente di scaricare ciascun grafico in diversi formati e di stamparlo</p>
					          
					            <div class="sc-cols">
					              <div class="sc-col-1-2">
					                <p class="sc-center"><strong><span id="chart-esiti-giugno-title"></span></strong></p>
					                <div class="sc-chart" id="sc-pie-chart-esiti-giugno"></div>
					                <p class="sc-note" id="chart-esiti-giugno-note"></p>
					                <div class="chart-explain chart-explain-clear">
					                	<p class="sc-note ellips" data-origwidth="" id="chart-esiti-giugno-approfondisci"></p>
					                </div>
					              </div>
					              <div class="sc-col-1-2">
					                <p class="sc-center"><strong><span id="chart-esiti-giugno-settembre-title"></span></strong></p>
					                <div class="sc-chart" id="sc-pie-chart-esiti-giugno-settembre"></div>
					                <p class="sc-note" id="chart-esiti-giugno-settembre-note"></p>
					                <div class="chart-explain chart-explain-clear">
					                	<p class="sc-note ellips" data-origwidth="" id="chart-esiti-giugno-settembre-approfondisci"></p>
					                </div>					                
					              </div>
					            </div>
					            <div class="sc-cols">
					              <div class="sc-col-full">
					                <p class="sc-center"><strong><span id="chart-ammessi-giugno-title"></span></strong></p>
					                <div class="sc-chart" id="sc-bar-chart-ammessi-giugno"></div>
					                <p class="sc-note" id="chart-ammessi-giugno-note"></p>
					                <div class="chart-explain chart-explain-clear">
					                	<p class="sc-note ellips" data-origwidth="" id="chart-ammessi-giugno-approfondisci"></p>
					                </div>					                
					              </div>
					            </div>
					            <div class="sc-cols">
					              <div class="sc-col-full">
					                <p class="sc-center"><strong><span id="chart-ammessi-settembre-title"></span></strong></p>
					                <div class="sc-chart" id="sc-bar-chart-ammessi-settembre"></div>
					                <p class="sc-note" id="chart-ammessi-settembre-note"></p>
					                <div class="chart-explain chart-explain-clear">
					                	<p class="sc-note ellips" data-origwidth="" id="chart-ammessi-settembre-approfondisci"></p>
					                </div>					                
					              </div>
					            </div>					            
					          </article>


                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- CHARTS-->
        <script src="<c:url value="/resources/js/grafici/highChartsCommon.js"/>"></script>
        <script src="<c:url value="/resources/js/grafici/alunni/ammessiGiugno.js"/>"></script>
        <script src="<c:url value="/resources/js/grafici/alunni/ammessiSettembre.js"/>"></script>
        <script src="<c:url value="/resources/js/grafici/alunni/esitiGiugno.js"/>"></script>
        <script src="<c:url value="/resources/js/grafici/alunni/esitiGiugnoSettembre.js"/>"></script>        
        <%@ include file="../includes/footerDettaglioScuola.jsp" %>
    </body>
</html>