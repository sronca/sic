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
					          	<form:form action="/cercalatuascuola/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/pagamenti" method="get" commandName="voAnni">
					            <h2>Amministrazione trasparente - pagamenti dell'amministrazione</h2>
					            <p class="sc-note-small">
					            	Tale sezione contiene l'indicatore annuale e trimestrale di tempestivit&agrave; dei pagamenti previsti dagli articoli
					            	9 e 10 del <a href="http://www.gazzettaufficiale.it/eli/id/2014/11/14/14A08772/sg" onclick="window.open(this.href);return false;">D.P.C.M. del 22/09/2014</a>.
					            	<br/>
					            	Gli indicatori misurano i tempi medi di pagamento relativi agli acquisti di beni, servizi e forniture.
					            </p>
					            <div class="sc-cols">
					              <div class="sc-col-1-2">
					                <div class="sc-fields">
					                  <div class="sc-field sc-field-1-2">
					                    <label>Anno scolastico</label>
					                    <div class="sc-select">
					                    	<form:select 
					                    		id="anniScolasticiPagamentiAmm" 
					                    		path="annoScolasticoSel" 
					                    		items="${anniScolastici}" 
					                    		onchange="this.form.submit()" 
					                    	/>
					                    </div>
					                  </div>
					                  <div class="sc-field sc-field-1-2">
					                    <label>Anno finanziario</label>
					                    <div class="sc-select">
					                        <form:select 
					                      		id="anniFinanziariPagamentiAmm" 
					                      		path="annoFinanziarioSel" 
					                      		items="${anniFinanziari}" 
					                      		onchange="this.form.submit()" 
					                       />
					                    </div>
					                  </div>
					                </div>
					              </div>
					              <div class="sc-col-full">
					              						              
						                <table class="sc-table sc-table-standard">
						                  <thead>
						                    <tr>
						                      <th><span>Codice meccanografico</span></th>
						                      <th><span>Anno scolastico</span></th>
						                      <th><span>Anno finanziario</span></th>
						                      <th><span>Indice</span></th>
						                      <th><span>Valore</span></th>
						                      <th><span>Data ultima modifica</span></th>
						                    </tr>
						                  </thead>
						                  <tbody>
						                  	<c:if test="${not empty elencoIndici}">
							                  	<c:forEach items="${elencoIndici}" var="indice">
								                    <tr>
								                      <td data-col-1="Codice Meccanografico" class="sc-col-1 sc-center">${indice.codiceMeccanografico}</td>
								                      <td data-col-2="Anno Scolastico" class="sc-col-2 sc-center">${indice.datAnnScoRil}</td>
								                      <td data-col-3="Anno Finanziario" class="sc-col-3 sc-center">${indice.annoFinanz}</td>
								                      <td data-col-4="Indice" class="sc-col-4 sc-center">${indice.descPrRif}</td>
								                      <td data-col-5="Valore" class="sc-col-5 sc-center">${indice.numValIndTemStr}</td>
								                      <td data-col-6="Data ultima modifica" class="sc-col-6 sc-center">${indice.dataUltMod}</td>
								                    </tr>
							                    </c:forEach>
						                    </c:if>
							              	<c:if test="${empty elencoIndici}">
							              		<tr>
							              			<td colspan="6" class="sc-col-1 sc-center">
							              				Dati non disponibili per la scuola
							              			</td>
							              		</tr>
							              	</c:if>						                    
						                  </tbody>
						                </table>
					                
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