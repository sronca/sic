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
					          	<form:form action="/cercalatuascuola/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/bandi" method="get" commandName="voAnni">
					            <h2>Amministrazione trasparente - bandi di gara e contratti</h2>
					            <div class="sc-cols">
					              <div class="sc-col-full">
					                <div class="sc-fields">
					                  <div class="sc-field sc-field-1-3">
					                    <label>Anno scolastico</label>
					                    <div class="sc-select">
					                    	<form:select 
					                    		id="anniScolasticiAVCP" 
					                    		path="annoScolasticoSel" 
					                    		items="${anniScolastici}" 
					                    		onchange="this.form.submit()" 
					                    	/>
					                    </div>
					                  </div>
					                  <div class="sc-field sc-field-1-3">
					                    <label>Anno finanziario</label>
					                    <div class="sc-select">
					                      <form:select 
					                      	id="anniBilancioAVCP" 
					                      	path="annoBilancioSel" 
					                      	items="${anniBilancio}" 
					                      	onchange="this.form.submit()" 
					                      />
					                    </div>
					                  </div>
					                  
					                  
					                  <c:if test="${not empty voAVCPFinanza.lotti && not empty voAVCPFinanza.documentoSel}">
						                  <div class="sc-field sc-field-1-3">
						                  	<p class="sc-note-small">
						                  		I dati relativi ai partecipanti e agli aggiudicatari sono presenti nel documento XML scaricabile mediante il seguente 
						                  		<a target="_blank" href="<c:url value='/istituti/${scuola.codScuUt}/finanza/AVCP?annoScolastico=${voAVCPFinanza.documentoSel.annoScolastico}&annoBilancio=${voAVCPFinanza.documentoSel.annoBilancio}'/>" >link</a>
						                  	</p>
						                  </div>
					                  </c:if>
					                  					                  
					                  
					                </div>
					              </div>
					              <div class="sc-col-full">					           
					              	
					              	<c:if test="${not empty voAVCPFinanza.lotti}">
					              	
						                <table class="sc-table sc-table-standard">
						                  <thead>
						                    <tr>
						                      <th><span>CIG</span></th>
						                      <th><span>Struttura proponente</span></th>
						                      <th><span>Oggetto</span></th>
						                      <th><span>Procedura scelta</span></th>
						                      <th><span>Importo di aggiudicazione</span></th>
						                      <th><span>Tempi di completamento (inizio-fine)</span></th>
						                      <th><span>Somme liquidate</span></th>
						                    </tr>
						                  </thead>
						                  <tbody>
						                  	<c:forEach items="${voAVCPFinanza.lotti}" var="lotto">
							                    <tr>
							                      <td data-col-1="CIG" class="sc-col-1">${lotto.cig}</td>
							                      <td data-col-2="Struttura proponente" class="sc-col-2">${lotto.strutturaProponenteCF}-${lotto.strutturaProponenteDesc}</td>
							                      <td data-col-3="Oggetto" class="sc-col-3">${lotto.oggetto}</td>
							                      <td data-col-4="Procedura scelta" class="sc-col-4">${lotto.proceduraDiScelta}</td>
							                      <td data-col-5="Importo di aggiudicazione" class="sc-col-5 sc-center">${lotto.importoAggiudicazioneFormat}</td>
							                      <td data-col-6="Tempi di completamento (inizio-fine)" class="sc-col-6">${lotto.dataInizioFormat}-${lotto.dataFineFormat}</td>
							                      <td data-col-7="Somme liquidate" class="sc-col-7 sc-center">${lotto.importoLiquidatoFormat}</td>
							                    </tr>
						                    </c:forEach>
						                  </tbody>
						                </table>
						            </c:if>
					              	<c:if test="${empty voAVCPFinanza.lotti}">
					              		<p class="sc-center">Dati non disponibili per la scuola</p>
					              	</c:if>							            
					                
					                
					                
					              </div>
					            </div>
					          </form:form>
					          </article>


                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@ include file="../includes/footerDettaglioScuola.jsp" %>
    </body>
</html>