<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>

<html>
    <head>
        <%@ include file="../includes/head_dettaglio.jsp" %>
    </head>
    <body data-codscuut="${scuola.codScuUt}" data-desnomscu="${scuola.desNomScu}">

        <%@ include file="../includes/toolbar_header.jsp" %>

        <section class="sc-internal-main sc-detail">

            <%@ include file="../includes/breadcrumbandtitle.jsp" %>
            <%@ include file="../includes/menu.jsp" %>

            <div class="sc-main-content-detail">
                <div class="sc-wrapper sc-wrapper-alt">
                    <div class="sc-table-table sc-external-box">
                        <div class="sc-table-row sc-external-box">

                            <%@ include file="../includes/menu_aside_servizi.jsp" %>

				          <article class="sc-internal-content sc-table-cell">
				            <div class="sc-cols">
				              
					              <div class="sc-col-full sc-col-border">
					                <h2>Servizi web</h2>
					                <div class="sc-cols">
					                  <div class="sc-col-full">
					                    <c:if test="${not empty listaServiziWeb}">
						                    <div class="sc-list-check">
								                <ul>
								                    <c:forEach items="${listaServiziWeb}" var="tipologiaServizio">
								                        <c:forEach items="${tipologiaServizio.servizi}" var="servizio">
								                            <c:forEach items="${servizio.attivitaServizio}" var="attivitaServizio">
								                                <li>${attivitaServizio.desAttSer}</li>
								                            </c:forEach>
								                        </c:forEach>
								                    </c:forEach>
								                </ul>
						                    </div>
					                    </c:if>
					                    <c:if test="${empty listaServiziWeb}">
					                    	<p>Dati non disponibili per la scuola</p>
					                    </c:if>
					                  </div>
					                </div>
					              </div>
				              
				              
				              <c:if test="${not empty listaAltriServizi}">
			                    <c:forEach items="${listaAltriServizi}" var="tipologiaServizio">
			                        <c:forEach items="${tipologiaServizio.servizi}" var="servizio">
				              
						              <c:if test="${servizio.desSer eq 'Altri Servizi'}">
						              <div class="sc-col-full sc-col-border">
						                <h2>${servizio.desSer}</h2>
						                <div class="sc-cols">
						                  <div class="sc-col-full">
						                    <div class="sc-list-check">
						                      <ul>
						                      	<c:forEach items="${servizio.attivitaServizio}" var="attivitaServizio" varStatus="row">
						                        <li> ${attivitaServizio.desAttSer} </li>
						                        </c:forEach>
						                      </ul>
						                    </div>
						                  </div>
						                </div>
						              </div>
						              </c:if>
						              
						            </c:forEach>
						        </c:forEach>
				              </c:if>
				              <c:if test="${empty listaAltriServizi}">
					              <div class="sc-col-full sc-col-border">
					                <h2>Altri servizi</h2>
					                <div class="sc-cols">
					                  <div class="sc-col-full">
					                  	<p>Dati non disponibili per la scuola</p>
					                  </div>
					                </div>
					              </div>
				              </c:if>				              		              
				              
				              <c:if test="${scuola.codTipSit ne 'IC' && scuola.codTipSit ne 'IS' && scuola.codTipSit ne 'AA' && not scuola.isSerale() && flgPlesso eq 'true'}">
				              <div class="sc-col-full sc-col-border">
				                <h2>Attrezzature multimediali</h2>
				                <div class="sc-cols">
				                  
				                  <div class="sc-col-full">
				                    <c:if test="${not empty descrizionePC}">
				                    	<p class="sc-center"><strong>${titoloPC}</strong></p>
				                    </c:if>
				                    <c:if test="${flgDati eq 'true'}">
					                    <table class="sc-table sc-table-standard">
					                      <thead>
					                        <tr>
					                          <th style="text-align: left;">Dotazioni multimediali</th>
					                          <th>Laboratori</th>
					                          <th>Altri ambienti</th>
					                        </tr>
					                      </thead>
					                      <tbody>
					                        <tr>
					                          <td data-col-1="Dotazioni multimediali" class="sc-col-1">Computer</td>
					                          <td data-col-2="Laboratori" class="sc-col-2 sc-center">${compLab}</td>
					                          <td data-col-3="Bibilioteche" class="sc-col-3 sc-center">${compBib}</td>
					                        </tr>
					                        <tr>
					                          <td data-col-1="Dotazioni multimediali" class="sc-col-1">Lim</td>
					                          <td data-col-2="Laboratori" class="sc-col-2 sc-center">${limLab}</td>
					                          <td data-col-3="Bibilioteche" class="sc-col-3 sc-center">${limBib}</td>
					                        </tr>
					                        <tr>
					                          <td data-col-1="Dotazioni multimediali" class="sc-col-1">Proiettori interattivi</td>
					                          <td data-col-2="Laboratori" class="sc-col-2 sc-center">${proiettLab}</td>
					                          <td data-col-3="Bibilioteche" class="sc-col-3 sc-center">${proiettBib}</td>
					                        </tr>
					                      </tbody>
					                    </table>
				                    </c:if>
				                    <c:if test="${flgDati eq 'false'}">
				                    	<p class="sc-center">Dati non disponibili per la scuola</p>
				                    </c:if>
				                  </div>
				                  
				                  <div class="sc-col-full">
				                  	<c:if test="${not empty descrizioneLIM}">
				                    	<p class="sc-center"><strong> ${titoloLIM} </strong></p>
				                    </c:if>
				                    <c:if test="${flgDati eq 'true'}">
					                    <table class="sc-table sc-table-standard">
					                      <thead>
					                        <tr>
					                          <th style="text-align: left;">Dotazioni multimediali</th>
					                          <th>Numeri</th>
					                        </tr>
					                      </thead>
					                      <tbody>
					                        <tr>
					                          <td data-col-1="Dotazioni multimediali" class="sc-col-1">Computer</td>
					                          <td data-col-2="Numeri" class="sc-col-2 sc-center">${compScu}</td>
					                        </tr>
					                        <tr>
					                          <td data-col-1="Dotazioni multimediali" class="sc-col-1">Dispositivi mobili</td>
					                          <td data-col-2="Numeri" class="sc-col-2 sc-center">${dispMobScu}</td>
					                        </tr>
					                        <tr>
					                          <td data-col-1="Dotazioni multimediali" class="sc-col-1">Lim</td>
					                          <td data-col-2="Numeri" class="sc-col-2 sc-center">${limScu}</td>
					                        </tr>
					                        <tr>
					                          <td data-col-1="Dotazioni multimediali" class="sc-col-1">Proiettori interattivi</td>
					                          <td data-col-2="Numeri" class="sc-col-2 sc-center">${proiettScu}</td>
					                        </tr>
					                      </tbody>
					                    </table>
				                    </c:if>
				                    <c:if test="${flgDati eq 'false'}">
				                    	<p class="sc-center">Dati non disponibili per la scuola</p>
				                    </c:if>				                    
				                  </div>
				                  
				                  <div class="sc-col-full">
				                  	<c:if test="${not empty descrizioneAule}">
				                    	<p class="sc-center"><strong>${titoloAule}</strong></p>
				                    </c:if>
				                    <c:if test="${flgDati eq 'true'}">
					                    <table class="sc-table sc-table-standard">
					                      <thead>
					                        <tr>
					                          <th>N. aule dedicate alla didattica</th>
					                          <th>% aule con connessione wifi</th>
					                        </tr>
					                      </thead>
					                      <tbody>
					                        <tr>
					                          <td data-col-1="N. aule dedicate alla didattica" class="sc-col-1 sc-center">${numAuleDid}</td>
					                          <td data-col-2="% aule con connessione wifi" class="sc-col-2 sc-center">${percAuleConn}%</td>
					                        </tr>
					                      </tbody>
					                    </table>
				                    </c:if>
				                    <c:if test="${flgDati eq 'false'}">
				                    	<p class="sc-center">Dati non disponibili per la scuola</p>
				                    </c:if>					                    
				                  </div>
				                  
				                </div>
				              </div>
				              </c:if>
				              
				              
	                          
				              <div class="sc-col-full sc-col-border">
				                <h2>Attrezzature a supporto</h2>
				                <div class="sc-cols">
				                  
								<c:if test="${not empty listaServiziSupporto}">
				                    <c:forEach items="${listaServiziSupporto}" var="tipologiaServizio">
				                        <c:forEach items="${tipologiaServizio.servizi}" var="servizio">
					                  
					                  		<c:if test="${scuola.flgPon eq 'true'}">
					                  		
					                  			<c:if test="${servizio.desSer eq 'Laboratori PON'}">
					                  		
								                  <div class="sc-col-1-2 sc-double-col">
								                    <p class="sc-center"><strong>Laboratori</strong></p>
								                    <div class="sc-list-check">
								                      <ul>
								                      	<c:forEach items="${servizio.attivitaServizio}" var="attivitaServizio">
								                        	<li>${attivitaServizio.desAttSer}</li>
								                        </c:forEach>
								                      </ul>
								                    </div>
								                  </div>
							                  
							                  	</c:if>
							                  	
							                  	<c:if test="${servizio.desSer ne 'Laboratori PON' && servizio.desSer ne 'Laboratori'}">
							                  	
								                  <div class="sc-col-1-2 sc-double-col">
								                    <p class="sc-center"><strong>${servizio.desSer}</strong></p>
								                    <div class="sc-list-check">
								                      <ul>
								                      	<c:forEach items="${servizio.attivitaServizio}" var="attivitaServizio">
								                        	<li>${attivitaServizio.desAttSer}</li>
								                        </c:forEach>
								                      </ul>
								                    </div>
								                  </div>							                  	
							                  	
							                  	</c:if>
							                  
							                </c:if>
							                
							                <c:if test="${scuola.flgPon ne 'true' && servizio.desSer ne 'Laboratori PON'}">

								                  <div class="sc-col-1-2 sc-double-col">
								                    <p class="sc-center"><strong>${servizio.desSer}</strong></p>
								                    <div class="sc-list-check">
								                      <ul>
								                      	<c:forEach items="${servizio.attivitaServizio}" var="attivitaServizio">
								                        	<li>${attivitaServizio.desAttSer}</li>
								                        </c:forEach>
								                      </ul>
								                    </div>
								                  </div>
							                
							                </c:if>
						                  
						                </c:forEach>
						            </c:forEach>
					            </c:if>
					            
			                    <c:if test="${empty listaServiziSupporto}">
			                    	<div class="sc-col-full">
			                    		<p>Dati non disponibili per la scuola</p>
			                    	</div>
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
        <%@ include file="../includes/footerDettaglioScuola.jsp" %>
    </body>
</html>