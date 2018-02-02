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
                            	<h2>Risultati a distanza - lavoro</h2>
                            	<p class="sc-note-small">
                            	In questa sezione vengono presentati i dati dei risultati in ambito lavorativo, a un anno di distanza dal diploma, degli studenti diplomati negli anni scolastici 2010/2011, 2011/2012, 2012/2013 e 2013/2014.<br/>
								Le informazioni provengono dal Sistema Informativo Statistico delle Comunicazioni Obbligatorie (SISCO) del Ministero del Lavoro e delle Politiche Sociali. I dati considerati sono relativi agli eventi di attivazione e cessazione per i quali la data di inizio è successiva al conseguimento del Diploma (per convenzione la data di conseguimento del diploma è fissata al 30 luglio dell'anno di diploma).<br/>
								Sono presi in considerazione tutti i rapporti di lavoro dipendente, più i rapporti di lavoro parasubordinato e le esperienze di lavoro soggette a comunicazione obbligatoria. Non sono osservati, invece, i rapporti di lavoro indipendente (imprenditori, commercianti, artigiani, liberi professionisti), né i rapporti di lavoro regolati con voucher, né, infine, il lavoro somministrato.<br/>
								Gli indicatori sono calcolati considerando il numero di diplomati che hanno lavorato almeno un giorno in un intervallo di 30 giorni centrato al 30 settembre (15 settembre – 15 ottobre) del primo anno successivo a quello del diploma.<br/>
								Si precisa che per l'analisi sulla "Tipologia di contratto", sul "Settore di attività economica" e sulla "Qualifica professionale" viene considerato, per ogni lavoratore, l'ultimo contratto osservato nel periodo di riferimento. 
                            	</p>


					            <div class="sc-cols">
					              <div class="sc-col-full">
						              <p class="sc-center"><strong>${titoloTabellaLavoro1}</strong></p>
						              <c:if test="${not empty datiLavoro1}">
						              <table class="sc-table sc-table-standard sc-table-vertical-th sc-table-vertical-th-special">
						                <tr class="">
						                  <th class="sc-center">Anno di diploma</th>
						                  <th class="sc-center sc-tab-small-td">Scuola</th>
						                  <th class="sc-center sc-tab-small-td">Regione</th>
						                  <th class="sc-center sc-tab-small-td">Italia</th>
						                </tr>
						                <c:forEach items="${datiLavoro1}" var="vo">
							                <tr class="">
							                  <td data-col-1="Anno di diploma" class="sc-col-1 sc-center">${vo.annoDiploma}</td>
							                  <td data-col-2="Scuola" class="sc-col-2 sc-center sc-tab-small-td">${vo.scu}</td>
							                  <td data-col-3="Regione" class="sc-col-3 sc-center sc-tab-small-td">${vo.reg}</td>
							                  <td data-col-4="Italia" class="sc-col-4 sc-center sc-tab-small-td">${vo.ita}</td>
							                </tr>
						                 </c:forEach>
						              </table>
						              <p class="sc-note">${noteTabellaLavoro1}</p>
						              <div class="chart-explain chart-explain-clear">
					                  	<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaLavoro1}</p>
					                  </div> 						              
						              </c:if>
						              <c:if test="${empty datiLavoro1}">
                                      	<p class="sc-center">Dati non disponibili per la scuola</p>
                                      </c:if>
					           	  </div>
					            </div>

					            <div class="sc-cols">
					              <div class="sc-col-full">
						              <p class="sc-center"><strong>${titoloTabellaLavoro2}</strong></p>
						              <c:if test="${not empty datiLavoro2}">
						              <table class="sc-table sc-table-standard sc-table-vertical-th sc-table-vertical-th-special">
						                <tr class="">
						                  <th class="sc-center sc-tab-small-td">Anno di diploma</th>
						                  <th class="sc-center">Tipologia di contratto</th>
						                  <th class="sc-center sc-tab-small-td">Scuola</th>
						                  <th class="sc-center sc-tab-small-td">Regione</th>
						                  <th class="sc-center sc-tab-small-td">Italia</th>
						                </tr>
						                <c:forEach items="${datiLavoro2}" var="vo">
							                <tr class="">
							                  <th rowspan="6" class="sc-center sc-tab-small-td">${vo.annoDiploma}</th>
							                  <th>Tempo indeterminato</th>
							                  <td data-col-1="${vo.annoDiploma} - Tempo indeterminato - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuTin}</td>
							                  <td data-col-2="${vo.annoDiploma} - Tempo indeterminato - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regTin}</td>
							                  <td data-col-3="${vo.annoDiploma} - Tempo indeterminato - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaTin}</td>
							                </tr>
							                <tr class="">
							                  <th>Tempo determinato</th>
							                  <td data-col-1="${vo.annoDiploma} - Tempo determinato - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuTde}</td>
							                  <td data-col-2="${vo.annoDiploma} - Tempo determinato - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regTde}</td>
							                  <td data-col-3="${vo.annoDiploma} - Tempo determinato - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaTde}</td>
							                </tr>
							                <tr class="">
							                  <th>Apprendistato</th>
							                  <td data-col-1="${vo.annoDiploma} - Apprendistato - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuApp}</td>
							                  <td data-col-2="${vo.annoDiploma} - Apprendistato - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regApp}</td>
							                  <td data-col-3="${vo.annoDiploma} - Apprendistato - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaApp}</td>
							                </tr>
							                <tr class="">
							                  <th>Collaborazione</th>
							                  <td data-col-1="${vo.annoDiploma} - Collaborazione - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuCol}</td>
							                  <td data-col-2="${vo.annoDiploma} - Collaborazione - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regCol}</td>
							                  <td data-col-3="${vo.annoDiploma} - Collaborazione - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaCol}</td>
							                </tr>
							                <tr class="">
							                  <th>Tirocinio</th>
							                  <td data-col-1="${vo.annoDiploma} - Tirocinio - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuTir}</td>
							                  <td data-col-2="${vo.annoDiploma} - Tirocinio - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regTir}</td>
							                  <td data-col-3="${vo.annoDiploma} - Tirocinio - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaTir}</td>
							                </tr>
							                <tr class="">
							                  <th>Altro</th>
							                  <td data-col-1="${vo.annoDiploma} - Altro - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuAlt}</td>
							                  <td data-col-2="${vo.annoDiploma} - Altro - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regAlt}</td>
							                  <td data-col-3="${vo.annoDiploma} - Altro - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaAlt}</td>
							                </tr>							                							                						                							                							                
							                <tr class="">
							                  <td colspan="5" style="background:#fff;"></td>
							                </tr>
						                 </c:forEach>
						              </table>
						              <p class="sc-note">${noteTabellaLavoro2}</p>
						              <div class="chart-explain chart-explain-clear">
					                  	<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaLavoro2}</p>
					                  </div> 
						              </c:if>
						              <c:if test="${empty datiLavoro2}">
                                      	<p class="sc-center">Dati non disponibili per la scuola</p>
                                      </c:if>
					           	  </div>
					            </div>					          

					            <div class="sc-cols">
					              <div class="sc-col-full">
						              <p class="sc-center"><strong>${titoloTabellaLavoro3}</strong></p>
						              <c:if test="${not empty datiLavoro3}">
						              <table class="sc-table sc-table-standard sc-table-vertical-th sc-table-vertical-th-special">
						                <tr class="">
						                  <th class="sc-center sc-tab-small-td">Anno di diploma</th>
						                  <th class="sc-center">Settore di attivit&agrave; economica</th>
						                  <th class="sc-center sc-tab-small-td">Scuola</th>
						                  <th class="sc-center sc-tab-small-td">Regione</th>
						                  <th class="sc-center sc-tab-small-td">Italia</th>
						                </tr>
						                <c:forEach items="${datiLavoro3}" var="vo">
							                <tr class="">
							                  <th rowspan="3" class="sc-center sc-tab-small-td">${vo.annoDiploma}</th>
							                  <th>Agricoltura</th>
							                  <td data-col-1="${vo.annoDiploma} - Agricoltura - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuAgr}</td>
							                  <td data-col-2="${vo.annoDiploma} - Agricoltura - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regAgr}</td>
							                  <td data-col-3="${vo.annoDiploma} - Agricoltura - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaAgr}</td>
							                </tr>
							                <tr class="">
							                  <th>Industria</th>
							                  <td data-col-1="${vo.annoDiploma} - Industria - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuInd}</td>
							                  <td data-col-2="${vo.annoDiploma} - Industria - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regInd}</td>
							                  <td data-col-3="${vo.annoDiploma} - Industria - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaInd}</td>
							                </tr>
							                <tr class="">
							                  <th>Servizi</th>
							                  <td data-col-1="${vo.annoDiploma} - Servizi - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuSer}</td>
							                  <td data-col-2="${vo.annoDiploma} - Servizi - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regSer}</td>
							                  <td data-col-3="${vo.annoDiploma} - Servizi - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaSer}</td>
							                </tr>
							                <tr class="">
							                  <td colspan="5" style="background:#fff;"></td>
							                </tr>
						                 </c:forEach>
						              </table>
						              <p class="sc-note">${noteTabellaLavoro3}</p>
						              <div class="chart-explain chart-explain-clear">
					                  	<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaLavoro3}</p>
					                  </div> 						              
						              </c:if>
						              <c:if test="${empty datiLavoro3}">
                                      	<p class="sc-center">Dati non disponibili per la scuola</p>
                                      </c:if>
					           	  </div>
					            </div>					          	

					            <div class="sc-cols">
					              <div class="sc-col-full">
						              <p class="sc-center"><strong>${titoloTabellaLavoro4}</strong></p>
						              <c:if test="${not empty datiLavoro4}">
						              <table class="sc-table sc-table-standard sc-table-vertical-th sc-table-vertical-th-special">
						                <tr class="">
						                  <th class="sc-center sc-tab-small-td">Anno di diploma</th>
						                  <th class="sc-center">Qualifica professionale</th>
						                  <th class="sc-center sc-tab-small-td">Scuola</th>
						                  <th class="sc-center sc-tab-small-td">Regione</th>
						                  <th class="sc-center sc-tab-small-td">Italia</th>
						                </tr>
						                <c:forEach items="${datiLavoro4}" var="vo">
							                <tr class="">
							                  <th rowspan="3" class="sc-center sc-tab-small-td">${vo.annoDiploma}</th>
							                  <th>Alta</th>
							                  <td data-col-1="${vo.annoDiploma} - Alta - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuAlt}</td>
							                  <td data-col-2="${vo.annoDiploma} - Alta - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regAlt}</td>
							                  <td data-col-3="${vo.annoDiploma} - Alta - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaAlt}</td>
							                </tr>
							                <tr class="">
							                  <th>Media</th>
							                  <td data-col-1="${vo.annoDiploma} - Media - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuMed}</td>
							                  <td data-col-2="${vo.annoDiploma} - Media - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regMed}</td>
							                  <td data-col-3="${vo.annoDiploma} - Media - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaMed}</td>
							                </tr>
							                <tr class="">
							                  <th>Bassa</th>
							                  <td data-col-1="${vo.annoDiploma} - Bassa - Scuola:" class="sc-col-1 sc-center sc-tab-small-td">${vo.scuBas}</td>
							                  <td data-col-2="${vo.annoDiploma} - Bassa - Regione:" class="sc-col-2 sc-center sc-tab-small-td">${vo.regBas}</td>
							                  <td data-col-3="${vo.annoDiploma} - Bassa - Italia:" class="sc-col-3 sc-center sc-tab-small-td">${vo.itaBas}</td>
							                </tr>
							                <tr class="">
							                  <td colspan="5" style="background:#fff;"></td>
							                </tr>
						                 </c:forEach>
						              </table>
						              <p class="sc-note">${noteTabellaLavoro4}</p>
						              <div class="chart-explain chart-explain-clear">
					                  	<p class="sc-note ellips" data-origwidth="">${approfondisciTabellaLavoro4}</p>
					                  </div>
						              </c:if>
						              <c:if test="${empty datiLavoro4}">
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
        <%@ include file="../includes/footerDettaglioScuola.jsp" %>
        <script>$(".chart-explain").dotdot();</script>
    </body>
</html>