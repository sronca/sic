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
                            	<h2>Risultati a distanza - universit&agrave;</h2>
                            	<p class="sc-note-small">Passando con il mouse sui grafici, il tooltip mostra dati e periodi di riferimento; cliccando sugli elementi della legenda i grafici si modificano. L'icona del download consente di scaricare ciascun grafico in diversi formati e di stamparlo</p>
					          
					          
					            <div class="sc-cols">
					              <div class="sc-col-full">
					                <p class="sc-center">
					                	<strong>
					                		<span id="chart-immatricolazioni-universita-title"></span>
					                	</strong>
					                </p>
					                <div class="sc-chart" id="sc-pie-chart-immatricolazioni-universita"></div>
					                <p class="sc-note" id="chart-immatricolazioni-universita-note"></p>
				                	<div class="chart-explain chart-explain-clear">
				                		<p class="sc-note ellips" data-origwidth="" id="chart-immatricolazioni-universita-approfondisci"></p>
				                	</div> 					                
					              </div>
					            </div>
					            
					            <div class="sc-cols">
					              <div class="sc-col-full">
					                <p class="sc-center">
					                	<strong>
					                		<span id="chart-immatricolazioni-universita-area-didattica-title"></span>
					                	</strong>
					                </p>
					                <div class="sc-chart" id="sc-bar-chart-immatricolazioni-universita-area-didattica"></div>
					                <p class="sc-note" id="chart-immatricolazioni-universita-area-didattica-note"></p>
				                	<div class="chart-explain chart-explain-clear">
				                		<p class="sc-note ellips" data-origwidth="" id="chart-immatricolazioni-universita-area-didattica-approfondisci"></p>
				                	</div>					                
					              </div>
					            </div>
					            
					            <div class="sc-cols">
					              <div class="sc-col-full">
						              <p class="sc-center"><strong>${titoloTabellaIndicatoriRav1}</strong></p>
						              <c:if test="${not empty indicatoriRav1}">
						              <table class="sc-table sc-table-standard sc-table-vertical-th">
						                <tr class="">
						                  <th class="sc-center">Area didattica</th>
						                  <th class="sc-center">Classi di Credito Formativo</th>
						                  <th>Scuola</th>
						                  <th>Regione</th>
						                  <th>Italia</th>
						                </tr>
						                <c:forEach items="${indicatoriRav1}" var="voRav24b1">
							                <tr class="">
							                  <th rowspan="3" class="sc-center">${voRav24b1.area}</th>
							                  <th>più della metà dei CFU</th>
							                  <td data-col-1="${voRav24b1.area} - più della metà dei CFU - Scuola:" class="sc-col-1 sc-center">${voRav24b1.scuPiu}</td>
							                  <td data-col-2="${voRav24b1.area} - più della metà dei CFU - Regione:" class="sc-col-2 sc-center">${voRav24b1.regPiu}</td>
							                  <td data-col-3="${voRav24b1.area} - più della metà dei CFU - Italia:" class="sc-col-3 sc-center">${voRav24b1.itaPiu}</td>
							                </tr>
							                <tr>
							                  <th>meno della metà dei CFU</th>
							                  <td data-col-1="${voRav24b1.area} - meno della metà dei CFU - Scuola:" class="sc-col-1 sc-center">${voRav24b1.scuMeno}</td>
							                  <td data-col-2="${voRav24b1.area} - meno della metà dei CFU - Regione:" class="sc-col-2 sc-center">${voRav24b1.regMeno}</td>
							                  <td data-col-3="${voRav24b1.area} - meno della metà dei CFU - Italia:" class="sc-col-3 sc-center">${voRav24b1.itaMeno}</td>
							                </tr>
							                <tr>
							                  <th>Nessun CFU</th>
							                  <td data-col-1="${voRav24b1.area} - Nessun CFU - Scuola:" class="sc-col-1 sc-center">${voRav24b1.scuZero}</td>
							                  <td data-col-2="${voRav24b1.area} - Nessun CFU - Regione:" class="sc-col-2 sc-center">${voRav24b1.regZero}</td>
							                  <td data-col-3="${voRav24b1.area} - Nessun CFU - Italia:" class="sc-col-3 sc-center">${voRav24b1.itaZero}</td>
							                </tr>
							                <tr class="">
							                  <td colspan="5" style="background:#fff;"></td>
							                </tr>
						                </c:forEach>
						              </table>
						              <p class="sc-note">${noteTabellaIndicatoriRav1}</p>
						              <div class="chart-explain chart-explain-clear">
					                  	<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaIndicatoriRav1}</p>
					                  </div> 						              
						              </c:if>
						              <c:if test="${empty indicatoriRav1}">
                                      	<p class="sc-center">Dati non disponibili per la scuola</p>
                                      </c:if>						              
					           	  </div>
					            </div>
					            
					            <div class="sc-cols">
					              <div class="sc-col-full">
						              <p class="sc-center"><strong>${titoloTabellaIndicatoriRav2}</strong></p>
						              <c:if test="${not empty indicatoriRav2}">
						              <table class="sc-table sc-table-standard sc-table-vertical-th">
						                <tr class="">
						                  <th class="sc-center">Area didattica</th>
						                  <th class="sc-center">Classi di Credito Formativo</th>
						                  <th>Scuola</th>
						                  <th>Regione</th>
						                  <th>Italia</th>
						                </tr>
						                <c:forEach items="${indicatoriRav2}" var="voRav24b1">
							                <tr class="">
							                  <th rowspan="3" class="sc-center">${voRav24b1.area}</th>
							                  <th>più della metà dei CFU</th>
							                  <td data-col-1="${voRav24b1.area} - più della metà dei CFU - Scuola:" class="sc-col-1 sc-center">${voRav24b1.scuPiu}</td>
							                  <td data-col-2="${voRav24b1.area} - più della metà dei CFU - Regione:" class="sc-col-2 sc-center">${voRav24b1.regPiu}</td>
							                  <td data-col-3="${voRav24b1.area} - più della metà dei CFU - Italia:" class="sc-col-3 sc-center">${voRav24b1.itaPiu}</td>
							                </tr>
							                <tr>
							                  <th>meno della metà dei CFU</th>
							                  <td data-col-1="${voRav24b1.area} - meno della metà dei CFU - Scuola:" class="sc-col-1 sc-center">${voRav24b1.scuMeno}</td>
							                  <td data-col-2="${voRav24b1.area} - meno della metà dei CFU - Regione:" class="sc-col-2 sc-center">${voRav24b1.regMeno}</td>
							                  <td data-col-3="${voRav24b1.area} - meno della metà dei CFU - Italia:" class="sc-col-3 sc-center">${voRav24b1.itaMeno}</td>
							                </tr>
							                <tr>
							                  <th>Nessun CFU</th>
							                  <td data-col-1="${voRav24b1.area} - Nessun CFU - Scuola:" class="sc-col-1 sc-center">${voRav24b1.scuZero}</td>
							                  <td data-col-2="${voRav24b1.area} - Nessun CFU - Regione:" class="sc-col-2 sc-center">${voRav24b1.regZero}</td>
							                  <td data-col-3="${voRav24b1.area} - Nessun CFU - Italia:" class="sc-col-3 sc-center">${voRav24b1.itaZero}</td>
							                </tr>
							                <tr class="">
							                  <td colspan="5" style="background:#fff;"></td>
							                </tr>
						                </c:forEach>
						              </table>
						              <p class="sc-note">${noteTabellaIndicatoriRav2}</p>
						              <div class="chart-explain chart-explain-clear">
					                  	<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaIndicatoriRav2}</p>
					                  </div> 						              
						              </c:if>
						              <c:if test="${empty indicatoriRav2}">
                                      	<p class="sc-center">Dati non disponibili per la scuola</p>
                                      </c:if>						              
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
        <script src="<c:url value="/resources/js/grafici/alunni/immatricolazioniUniversita.js"/>"></script>
        <script src="<c:url value="/resources/js/grafici/alunni/immatricolazioniUniversitaAreaDidattica.js"/>"></script>        
        <%@ include file="../includes/footerDettaglioScuola.jsp" %>
        <script>$(".chart-explain").dotdot();</script>
    </body>
</html>