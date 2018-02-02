<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
<head>
    <%@ include file="../includes/head_dettaglio.jsp" %>
    
    <script>
    function ptofNonDisponibile(){
		modal({
			type: "info",
			title: "Piano Triennale dell'Offerta Formativa",
			text: 'Il Piano triennale dell\'offerta formativa della scuola sarà a breve disponibile.',
			buttonText: {
				ok: "Chiudi"
			}
		});
    }
    </script>
</head>
<body>


<%@ include file="../includes/toolbar_header.jsp" %>


<section class="sc-internal-main sc-detail">

	<%@ include file="../includes/breadcrumbandtitle.jsp" %>
	<%@ include file="../includes/menu.jsp" %>

    
    
    <div class="sc-main-content-detail">
        <div class="sc-wrapper sc-wrapper-alt">
        
        	<div  class="sc-table-table sc-external-box">
        		<div class="sc-table-row sc-external-box">

				  <%@ include file="../includes/menu_aside_chisiamo.jsp" %>

		            
			      <article class="sc-internal-content sc-table-cell">
			      
			        <div class="sc-cols">
			          <div class="sc-table-table">
			            <div class="sc-table-row sc-table-bg">
			              <div class="sc-table-cell sc-left-home">
			                <figure class="sc-main-photo">
			                 <c:if test="${not empty fotoScuola}">
			                     <img src="<c:url value="/render/document/${scuola.codScuUt}?codTipFil=${fotoScuola.codTipFil}&prgDoc=${fotoScuola.prgDoc}&x=${String.valueOf(System.currentTimeMillis())}"/>" />
			                 </c:if>
			                 <c:if test="${empty fotoScuola}">
			                     <c:if test="${scuola.flagCentroFormazioneProfessionale eq 'S'}">
			                         <img src="<c:url value="/resources/img/CFP.gif" />" />
			                     </c:if>
			                     <c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S'}">
			                         <img src="<c:url value="/resources/img/foto-non-disponibile.png" />" />
			                     </c:if>
			                 </c:if>
			                </figure>
					                        
			                <table class="sc-table sc-school-home-data sc-address">
			                  <tbody>
			                    <tr>
			                      <td><strong>Indirizzo</strong></td>
			                      <td><span>${scuola.indirizzoCompleto}</span></td>
			                      <td class="sc-tab-mappa"><a href="/cercalatuascuola/mappa_istituto/${scuola.codScuUt}/${scuola.desNomScuNorm}/" class="sc-map-modal mappa pin-small-1"> <span>Mappa Link</span> </a></td>
			                    </tr>
			                  </tbody>
			                </table>
			              </div>
			              <div class="sc-table-cell sc-right-home-equal sc-position-ab">
			              	<c:if test="${scuola.flgPon eq 'true'}">
			                	<span class="sc-euflag"><img src="<c:url value="/resources/img/eu-flag-big.png" />" width="70" height="45" title="La scuola partecipa ai Programmi Operativi Nazionali e/o Regionali finanziati con i Fondi Strutturali"></span>
			                </c:if>
			                <div class="sc-school-name eu-school">
			                  <h2>${scuola.desNomScu}</h2>
			                  <p>
			                	<c:if test="${scuola.flagCentroFormazioneProfessionale eq 'S'}">
			                    	${scuola.desTipSit}
			                	</c:if>
			                	<c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S' && not scuola.cpia}">
			                    	<c:if test="${scuola.flgIstSta eq '0'}">
			                        	Scuola statale
			                    	</c:if>
			                    	<c:if test="${scuola.flgIstSta eq '1'}">
			                        	Scuola paritaria
			                    	</c:if>
			                    	- ${scuola.desTipSit}
			                	</c:if>
			                	<c:if test="${scuola.cpia}">
			                		Centro Provinciale per l'Istruzione degli Adulti (CPIA)
			                	</c:if>                            
			                  </p>
			                </div>
			                <table class="sc-simple-table sc-school-home-data">
			                  <tbody>
			                    <tr>
			                      <td>Codice</td>
			                      <td>${scuola.codScuUt} </td>
			                    </tr>
			                    <c:if test="${not empty scuola.desIndEml}">
				                    <tr>
				                      <td>Email</td>
				                      <td><a href="mailto:${scuola.desIndEml}">${scuola.desIndEml}</a></td>
				                    </tr>
			                    </c:if>
			                    <c:if test="${not empty scuola.desIndEmaPce}">
				                     <tr>
				                         <td>PEC</td>
				                         <td colspan="2"><a href="mailto:${scuola.desIndEmaPce}">${scuola.desIndEmaPce}</a>
				                         </td>
				                     </tr>
			                    </c:if>
			                    <c:if test="${not empty scuola.desIndWeb}">
				                    <tr>
				                      <td>Sito web</td>
				                      <td><a target="_new" href="${scuola.desIndWeb}">${scuola.desIndWeb}</a></td>
				                    </tr>
			                    </c:if>
			                  </tbody>
			                </table>
			                <div class="bottoni sc-position-ab"> 
				               	<c:if test="${not scuola.cpia && not scuola.isSerale()}">
					               	<c:if test="${not empty urlIscrizioniOnline}">
					               		<a href="javascript:void(0);" class="sc-button iscrizione-online" onclick="javascript: window.open('${urlIscrizioniOnline}','_parent');">
					                    <c:if test="${scuola.codPrv ne 'TN'}">
					                        <c:if test="${provenienzaPortale}">
					                            Iscrizioni on-line
					                        </c:if>
					                        <c:if test="${not provenienzaPortale}">
					                            Torna ad Iscr. on-line
					                        </c:if>
					                    </c:if>
					                    <c:if test="${scuola.codPrv eq 'TN'}">
					                        Iscrizioni on-line
					                    </c:if>
					                    </a>
					                </c:if>
 					               	<c:if test="${empty urlIscrizioniOnline}">
 					               		<c:if test="${visLinkIscr}">
					               			<a href="javascript:void(0);" class="sc-button iscrizione-online inactive">Iscrizioni on-line</a>
					               		</c:if>
					                </c:if>
					            </c:if>
					            <c:if test="${not scuola.cpia}">			                                	
				                	<c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S'}">
				                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/"/>" class="sc-button rav">Rapporto di<br/>autovalutazione</a>
						            </c:if>
						            <c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S'}">
							            <c:if test="${not empty urlIscrizioniOnline}">
								            <c:if test="${visLinkIscr}">
								            	<a href="/cercalatuascuola/criteriprecedenza/${scuola.codScuUt}/" class="sc-button criteri sc-info-modal">Criteri di precedenza</a>
								            </c:if>
							            </c:if>
							            <c:if test="${empty urlIscrizioniOnline}">
								            <c:if test="${visLinkIscr}">
								            	<a href="javascript:void(0);" class="sc-button criteri inactive">Criteri di precedenza</a>
								            </c:if>
							            </c:if>
						            </c:if>					            
						            <c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S'}">
										<c:if test="${not empty ptof}">
											<a href="<c:url value="/render/document/${scuola.codScuUt}?codTipFil=${ptof.codTipFil}&prgDoc=${ptof.prgDoc}&disp=attach"/>" target="_new" class="sc-button ptof">Piano triennale <br/> offerta formativa</a>
										</c:if>
										<c:if test="${empty ptof}">
											<a href="javascript:ptofNonDisponibile();" class="sc-button ptof">Piano triennale <br/> offerta formativa</a>
										</c:if>
									</c:if>
					            </c:if>			            
			                </div>
			              </div>
			            </div>
			          </div>
			        </div>
			        <div class="sc-cols">
			          <div class="sc-col-1-2 sc-col-right-home">
			            <c:if test="${not scuola.cpia}">
				            <h2>
					            <c:if test="${scuola.flgIstSta eq '0'}">
					                ISTITUTO PRINCIPALE
					            </c:if>
					            <c:if test="${scuola.flgIstSta eq '1'}">
					                SCUOLA PARITARIA
					            </c:if>             
				            </h2>
			            </c:if>
			            <div class="sc-info-scuola sc-table-bg sc-bg-green">
			              <h2>
			              	<c:if test="${not scuola.isIstitutoPrincipale()}">
			              		<a href="<c:url value="/istituti/${scuolaPri.codScuUt}/${scuolaPri.desNomScuNorm}/"/>">${scuolaPri.desNomScu}</a>
			              	</c:if>
			              	<c:if test="${scuola.isIstitutoPrincipale()}">
			              		${scuolaPri.desNomScu}
			              	</c:if>
			              </h2>         
			              <p>
			               <c:if test="${scuolaPri.flagCentroFormazioneProfessionale eq 'S'}">
			                   ${scuolaPri.desTipSit}
			               </c:if>
			               <c:if test="${scuolaPri.flagCentroFormazioneProfessionale ne 'S' && not scuolaPri.cpia}">
			                   <c:if test="${scuolaPri.flgIstSta eq '0'}">
			                       Scuola statale
			                   </c:if>
			                   <c:if test="${scuolaPri.flgIstSta eq '1'}">
			                       Scuola paritaria
			                   </c:if>
			                   - ${scuolaPri.desTipSit}
			               </c:if>
			               <c:if test="${scuolaPri.cpia}">
			                   Centro Provinciale per l'Istruzione degli Adulti (CPIA)
			               </c:if>                     
			              </p>
			              <c:if test="${not empty dirigente}">
			              <table class="sc-simple-table sc-school-home-data sc-margin-bottom">
			                <tbody>
			                  <tr>
			                    <td>Dirigente scolastico</td>
			                    <td><strong>${dirigente}</strong></td>
			                  </tr>
			                </tbody>
			              </table>
			              </c:if>
			              <table class="sc-simple-table sc-school-home-data">
			                <tbody>
				                <tr>
				                    <td>Indirizzo</td>
				                    <td>${scuolaPri.indirizzoCompleto}</td>
				                </tr>
				             <c:if test="${scuolaPri.flagCentroFormazioneProfessionale ne 'S'}">
				                 <c:if test="${not scuola.isIstitutoPrincipale()}">
				                     <tr>
				                         <td>Codice</td>
				                         <td>
				                             ${scuolaPri.codScuUt} (Istituto principale)
				                         </td>
				                     </tr>
				                 </c:if>
				                 <c:if test="${scuola.isIstitutoPrincipale()}">
				                     <tr>
				                         <td>Codice</td>
				                         <td>${scuolaPri.codScuUt}
				                             <c:if test="${scuola.flgIstSta eq '0'}">
				                                 (Istituto principale)
				                             </c:if>
				                         </td>
				                     </tr>
				                 </c:if>
				             </c:if>
				                <c:if test="${not empty scuolaPri.codTelScu}">
				                 <tr>
				                     <td>Telefono</td>
				                     <td><a href="tel:${scuolaPri.codTelScu}">${scuolaPri.codTelScu}</a></td>
				                 </tr>
				                </c:if>
				                <c:if test="${not empty scuolaPri.codFaxScu}">
				                 <tr>
				                     <td>Fax</td>
				                     <td>${scuolaPri.codFaxScu}</td>
				                 </tr>
				                </c:if>
				                <c:if test="${not empty scuolaPri.desIndEml}">
				                 <tr>
				                     <td>Email</td>
				                     <td><a href="mailto:${scuolaPri.desIndEml}">${scuolaPri.desIndEml}</a></td>
				                 </tr>
				                </c:if>
				                <c:if test="${not empty scuolaPri.desIndEmaPce}">
				                 <tr>
				                     <td>Pec</td>
				                     <td><a target="_new" href="mailto:${scuolaPri.desIndEmaPce}">${scuolaPri.desIndEmaPce}</a>
				                     </td>
				                 </tr>
				                </c:if>
				                <c:if test="${not empty scuolaPri.desIndWeb}">
				                 <tr>
				                     <td>Sito web</td>
				                     <td><a target="_new" href="${scuolaPri.desIndWeb}">${scuolaPri.desIndWeb}</a>
				                     </td>
				                 </tr>
				                </c:if>
			                </tbody>
			              </table>
			              <c:if test="${not scuola.cpia && not scuola.isSerale()}">
				              <c:if test="${not empty numeroPlessi}">
					              <table class="sc-simple-table sc-school-home-data sc-margin-top">
					                <tbody>
					                    <tr>
					                        <td>Numero plessi/scuole</td>
					                        <td><strong>${numeroPlessi}</strong></td>
					                    </tr>
					                    <tr>
					                    	<td>di cui</td>
					                        <td>
					                                <c:if test="${not empty numeroInfanzia}">
					                                	Infanzia: <strong>${numeroInfanzia}</strong><br/>
					                                </c:if>
					                                <c:if test="${not empty numeroPrimaria}">
					                                	Primaria: <strong>${numeroPrimaria}</strong><br/>
					                                </c:if>
					                                <c:if test="${not empty numeroIGrado}">
					                                	I Grado: <strong>${numeroIGrado}</strong><br/>
					                                </c:if>
					                                <c:if test="${not empty numeroIIGrado}">
					                                	II Grado: <strong>${numeroIIGrado}</strong>
					                                </c:if>
					                                <c:if test="${not empty numeroIC}">
					                                	Istituto Comprensivo: <strong>${numeroIC}</strong>
					                                </c:if>
					                                <c:if test="${not empty numeroCT}">
					                                	Centro Territoriale: <strong>${numeroCT}</strong>
					                                </c:if>                                           
					                        </td>
					                    </tr>
					                </tbody>
					              </table>
				              </c:if>
			              </c:if>
			            </div>
			          </div>
			          <div class="sc-col-1-2 sc-col-left-home">
			          	<c:if test="${scuolaPri.flagCentroFormazioneProfessionale ne 'S' && not scuola.cpia}">
						                        
							<c:if test="${not scuola.isSerale()}">
								<c:if test="${not empty numeroAlunni}">
				                   <div class="sc-box-col-left-home">
				                     <h2>La scuola in numeri</h2>
				
				                     <div class="sc-table-bg">
				                         <table class="sc-simple-table">
				                             <tbody>
				                             <tr>
				                                 <td>Numero alunni</td>
				                                 <td align="center">${numeroAlunni}</td>
				                             </tr>
				                             <tr>
				                                 <td>Numero classi</td>
				                                 <td align="center">${numeroClassi}</td>
				                             </tr>
				                             <tr>
				                                 <td>Media alunni/classi</td>
				                                 <td align="center">${mediaAlunniClassi}</td>
				                             </tr>
				                             </tbody>
				                         </table>
				                     </div>
				                   </div>
				                </c:if>
			                </c:if>
			                			                
			                <c:if test="${not empty tempiScuola}">
			                 <div class="sc-box-col-left-home">
			                     <h2>Tempi scuola</h2>
			
			                     <div class="sc-list-check">
			                         <ul>
			                         	<c:forEach items="${tempiScuola}" var="tempiScuolaItem">
			                            		<li>${tempiScuolaItem.desSer}</li>
			                             </c:forEach>
			                         </ul>
			                     </div>
			                 </div>
			                </c:if>
			
			                <c:if test="${not empty indirizziMM}">
			                 <div class="sc-box-col-left-home">
			                     <h2>Indirizzi</h2>
			
			                     <div class="sc-list-check">
			                         <ul>
			                         	<c:forEach items="${indirizziMM}" var="indirizziMMItem">
			                            		<li>${indirizziMMItem.desSer}</li>
			                             </c:forEach>
			                         </ul>
			                     </div>
			                 </div>
			                </c:if>
			
			                <c:if test="${not empty percorsi}">
			                 <div class="sc-box-col-left-home">
			                     <h2>Percorsi di studio</h2>
			
			                     <div class="sc-list-check">
			                         <ul>
			                         	<c:forEach items="${percorsi}" var="percorsiItem">
			                            		<li>${percorsiItem.desPer}</li>
			                             </c:forEach>
			                         </ul>
			                     </div>
			                 </div>
			                </c:if>
			                
<%-- 			                <c:if test="${not empty pof}">		                    
				              	<div class="sc-box-col-left-home">
				                  <h2>Offerta formativa</h2>
				                  <div class="sc-list-check">
				                    <ul>
				                      <li><a href="<c:url value="/render/document/${scuola.codScuUt}?codTipFil=${pof.codTipFil}&prgDoc=${pof.prgDoc}&disp=attach"/>" target="_new">Scarica l'offerta formativa (.pdf)</a></li>
				                    </ul>
				                  </div>
				                </div>			                    
			                </c:if> --%>	                        	
						                        
				            <c:if test="${scuola.flgSedDir eq '1' && flgPlessi eq 'true'}">
				                <div class="sc-box-col-left-home">
				                  <h2>Plessi/Scuole</h2>
				                  <div class="sc-list-check">
				                    <ul>
				                    	<c:forEach items="${plessi}" var="plesso">
				                      		<li>
				                        		<p><strong><a href="<c:url value="/istituti/${plesso.codScuUt}/${plesso.desNomScuNorm}/"/>">${plesso.desNomScu}</a></strong></p>
				                        		<p>${plesso.codScuUt} - ${plesso.desTipSit}</p>
				                      		</li>
				                      	</c:forEach>	
				                    </ul>
				                  </div>
				                </div>
				        	</c:if>
				            <c:if test="${flagCorsiSerali eq 'true'}">
				                <div class="sc-box-col-left-home">
				                  <h2 class="sc-normal-title">Corsi istruzione per adulti – II livello</h2>
				                  <div class="sc-list-check">
				                    <ul>
				                    	<c:forEach items="${corsiSerali}" var="plesso">
				                      		<li>
				                        		<p><strong><a href="<c:url value="/istituti/${plesso.codScuUt}/${plesso.desNomScuNorm}/"/>">${plesso.desNomScu}</a></strong></p>
				                        		<p>${plesso.codScuUt} - ${plesso.desTipSit}</p>
				                      		</li>
				                      	</c:forEach>	
				                    </ul>
				                  </div>
				                </div>
				        	</c:if>				        			
			            </c:if>
			            <c:if test="${scuola.cpia}">
			                <div class="sc-box-col-left-home">
			                  <h2 class="sc-normal-title">Centro Provinciale per l'Istruzione degli Adulti (CPIA)</h2>
			                  <div class="sc-list-check sc-list-accordion">
			                    <ul id="accordion">
		                      		<li>
		                        		<p class="sc-list-accordion-title"><strong><a href="javascript:void(0);">I percorsi di istruzione</a></strong></p>
		                        		<div class="sc-list-accordion-text">
		                        			<p>
		                        				Presso i CPIA possono essere attivati tre tipi di percorsi di istruzione:
		                        				<br/><br/>
		                        				1) percorsi di primo livello: sono finalizzati al conseguimento del titolo di studio conclusivo del primo ciclo di 
		                        				istruzione e della certificazione che attesta l'acquisizione delle competenze di base connesse all'obbligo di istruzione;
		                        				<br/><br/>
		                        				2) percorsi di secondo livello: sono finalizzati al conseguimento del diploma di istruzione tecnica, professionale e artistica;
		                        				<br/><br/>
		                        				3) percorsi di alfabetizzazione e di apprendimento della lingua italiana, destinati agli adulti stranieri e finalizzati alla conoscenza della lingua italiana.
		                        			</p>
		                        		</div>
		                      		</li>
		                      		<li>
		                        		<p class="sc-list-accordion-title"><strong><a href="javascript:void(0);">Chi può iscriversi</a></strong></p>
		                        		<div class="sc-list-accordion-text">
		                        		<p>
		                        			Possono iscriversi ai CPIA:
		                        			<br/><br/>
		                        			1) gli adulti che non hanno assolto l'obbligo di istruzione o che non sono in possesso del titolo di studio conclusivo del primo ciclo 
		                        			di istruzione;
		                        			<br/><br/>
		                        			2) coloro che hanno compiuto il sedicesimo anno di et&agrave; e che non sono in possesso del titolo di studio conclusivo 
		                        			del primo ciclo di istruzione (in alcune regioni, in base a specifici accordi tra regione e ufficio scolastico regionale,
		                        			 possono iscriversi anche minori che hanno compiuto il quindicesimo anno di et&agrave;).
		                        		</p>
		                        		</div>
		                      		</li>
			                    </ul>
			                    <p class="sc-list-accordion-title"><strong><a href="http://www.normattiva.it/uri-res/N2Ls?urn:nir:presidente.repubblica:decreto:2012-10-29;263" onclick="window.open(this.href);return false;">Decreto del Presidente della Repubblica 29 ottobre 2012 , n. 263</a></strong></p>
			                  </div>
			                </div>
			        	</c:if>				            
			          </div>
                      <c:if test="${not empty infoStruttureAltScuolaLavoro}">
                        <div class="sc-cols">
                            <div class="sc-col-full">
                                <div class="sc-info-scuola sc-table-bg"> 
                                    <p><strong><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/strutture/" />">${infoStruttureAltScuolaLavoro}</a></strong></p>             
                                </div>
                            </div>
                        </div>
                      </c:if>
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