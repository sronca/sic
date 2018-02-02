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

                            <%@ include file="../includes/menu_aside_personale.jsp" %>

                            <article class="sc-internal-content sc-table-cell">
                            	<h2>Assenze</h2>
                            	<p class="sc-note-small">Passando con il mouse sui grafici, il tooltip mostra dati e periodi di riferimento; cliccando sugli elementi della legenda i grafici si modificano. L'icona del download consente di scaricare ciascun grafico in diversi formati e di stamparlo</p>
                            
					            <div class="sc-cols">
					              <div class="sc-col-full">
					                <p class="sc-center"><strong><span id="chart-assenze-docenti-title"></span></strong></p>
					                <div class="sc-chart" id="sc-bar-chart-assenze-docenti"></div>
					                <p class="sc-note" id="chart-assenze-docenti-note"></p>
				                	<div class="chart-explain chart-explain-clear">
				                		<p class="sc-note ellips" data-origwidth="" id="chart-assenze-docenti-approfondisci"></p>
				                	</div>					                
					              </div>
					            </div>
					            
					            <div class="sc-cols">
					              <div class="sc-col-full">
					                <p class="sc-center"><strong><span id="chart-assenze-ata-title"></span></strong></p>
					                <div class="sc-chart" id="sc-bar-chart-assenze-ata"></div>
					                <p class="sc-note" id="chart-assenze-ata-note"></p>
				                	<div class="chart-explain chart-explain-clear">
				                		<p class="sc-note ellips" data-origwidth="" id="chart-assenze-ata-approfondisci"></p>
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
        <script src="<c:url value="/resources/js/grafici/personale/assenzeDocenti.js"/>"></script>
        <script src="<c:url value="/resources/js/grafici/personale/assenzeATA.js"/>"></script>        
        <%@ include file="../includes/footerDettaglioScuola.jsp" %>
    </body>
</html>