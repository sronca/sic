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
                            	<h2>Personale docente e ATA</h2>
                            	<p class="sc-note-small">Passando con il mouse sui grafici, il tooltip mostra dati e periodi di riferimento; cliccando sugli elementi della legenda i grafici si modificano. L'icona del download consente di scaricare ciascun grafico in diversi formati e di stamparlo</p>
                            
                                <div class="sc-cols">
                                    <div class="sc-col-1-2">
                                        <p class="sc-center"><strong>${titoloTabellaDoc}</strong></p>

                                        <div class="">
                                        	<c:if test="${not empty doc}">
                                            <table class="sc-table sc-table-standard">
                                                <thead>
                                                <tr>
                                                    <th>Personale</th>
                                                    <th>Maschi</th>
                                                    <th>Femmine</th>
                                                    <th>Totale</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td data-col-1="Personale" class="sc-col-1">Docenti</td>
                                                    <td data-col-2="Maschi" class="sc-col-2 sc-center">
                                                    	<c:if test="${not empty doc.numDocMaschi}">${doc.numDocMaschi}</c:if>
                                                    	<c:if test="${empty doc.numDocMaschi}">n.d.</c:if>                                                    
                                                    </td>
                                                    <td data-col-3="Femmine" class="sc-col-3 sc-center">
                                                    	<c:if test="${not empty doc.numDocFemmine}">${doc.numDocFemmine}</c:if>
                                                    	<c:if test="${empty doc.numDocFemmine}">n.d.</c:if>                                                    
                                                    </td>
                                                    <td data-col-4="Totale" class="sc-col-4 sc-center">
                                                    	<c:if test="${not empty doc.totDoc}">${doc.totDoc}</c:if>
                                                    	<c:if test="${empty doc.totDoc}">n.d.</c:if>                                                    
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td data-col-1="Personale" class="sc-col-1">di cui di sostegno</td>
                                                    <td data-col-2="Maschi" class="sc-col-2 sc-center">
                                                    	<c:if test="${not empty doc.numSosMaschi}">${doc.numSosMaschi}</c:if>
                                                    	<c:if test="${empty doc.numSosMaschi}">n.d.</c:if>                                                    
                                                    </td>
                                                    <td data-col-3="Femmine" class="sc-col-3 sc-center">
                                                    	<c:if test="${not empty doc.numSosFemmine}">${doc.numSosFemmine}</c:if>
                                                    	<c:if test="${empty doc.numSosFemmine}">n.d.</c:if>                                                    
                                                    </td>
                                                    <td data-col-4="Totale" class="sc-col-4 sc-center">
                                                    	<c:if test="${not empty doc.totSos}">${doc.totSos}</c:if>
                                                    	<c:if test="${empty doc.totSos}">n.d.</c:if>                                                    
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td data-col-1="Personale" class="sc-col-1">ATA</td>
                                                    <td data-col-2="Maschi" class="sc-col-2 sc-center">
                                                    	<c:if test="${not empty ata.numAtaMaschi}">${ata.numAtaMaschi}</c:if>
                                                    	<c:if test="${empty ata.numAtaMaschi}">n.d.</c:if>                                                    
                                                    </td>
                                                    <td data-col-3="Femmine" class="sc-col-3 sc-center">
                                                    	<c:if test="${not empty ata.numAtaFemmine}">${ata.numAtaFemmine}</c:if>
                                                    	<c:if test="${empty ata.numAtaFemmine}">n.d.</c:if>                                                    
                                                    </td>
                                                    <td data-col-4="Totale" class="sc-col-4 sc-center">
                                                    	<c:if test="${not empty ata.totAta}">${ata.totAta}</c:if>
                                                    	<c:if test="${empty ata.totAta}">n.d.</c:if>                                                    
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
							                <p class="sc-note">${noteTabellaDoc}</p>
							                <div class="chart-explain chart-explain-clear">
						                  		<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaDoc}</p>
						                    </div>                                            
                                            </c:if>
                                            <c:if test="${empty doc}">
                                            	<p class="sc-center">Dati non disponibili per la scuola</p>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="sc-col-1-2">
                                        <p class="sc-center"><strong>${titoloTabellaAta}</strong></p>

                                        <div class="">
                                        	<c:if test="${not empty doc}">
                                            <table class="sc-table sc-table-standard">
                                                <thead>
                                                <tr>
                                                    <th>A tempo indeterminato</th>
                                                    <th>A tempo determinato</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td data-col-1="A tempo indeterminato" class="sc-col-1 sc-center">
                                                    	<c:if test="${not empty doc.totRuo}">${doc.totRuo}</c:if>
                                                    	<c:if test="${empty doc.totRuo}">n.d.</c:if>
                                                    </td>
                                                    <td data-col-2="A tempo determinato" class="sc-col-2 sc-center">
                                                    	<c:if test="${not empty doc.totNoRuo}">${doc.totNoRuo}</c:if>
                                                    	<c:if test="${empty doc.totNoRuo}">n.d.</c:if>                                                    
                                                   </td>
                                                </tr>
                                                </tbody>
                                            </table>
							                <p class="sc-note">${noteTabellaAta}</p>
							                <div class="chart-explain chart-explain-clear">
						                  		<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaAta}</p>
						                    </div>                                            
                                            </c:if>
                                            <c:if test="${empty doc}">
                                            	<p class="sc-center">Dati non disponibili per la scuola</p>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
					            <div class="sc-cols">
					              <div class="sc-col-full">
					                <p class="sc-center"><strong><span id="chart-docenti-fasce-eta-title"></span></strong></p>
					                <div class="sc-chart" id="sc-bar-chart-docenti-fasce-eta"></div>
					                <p class="sc-note" id="chart-docenti-fasce-eta-note"></p>
				                	<div class="chart-explain chart-explain-clear">
				                		<p class="sc-note ellips" data-origwidth="" id="chart-docenti-fasce-eta-approfondisci"></p>
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
        <script src="<c:url value="/resources/js/grafici/personale/docentiFasceEta.js"/>"></script>        
        <%@ include file="../includes/footerDettaglioScuola.jsp" %>
        <script>$(".chart-explain").dotdot();</script>
    </body>
</html>