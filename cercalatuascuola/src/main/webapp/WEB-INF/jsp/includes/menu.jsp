<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sc-section-menu">
    <div class="sc-wrapper">
        <nav>
            <ul>
               	<li ${functionalityOn eq 'home'?'class="sc-active"':''}>
                	<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/"/>"> <span>Chi Siamo</span> </a>
                </li>
                <c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S' && not scuola.cpia}">
                    <c:if test="${scuola.codTipSit ne 'CT' && scuola.codTipSit ne 'VC' && scuola.codTipSit ne 'VE' && not scuola.isSerale()}">
	                    <li ${functionalityOn eq 'didattica'?'class="sc-active"':''}>
                    		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/"/>"> <span>Didattica</span> </a>
                    	</li>
                    </c:if>
                    <li ${functionalityOn eq 'servizi'?'class="sc-active"':''}>
                    	<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/servizi/"/>"> <span>Servizi E Attività</span> </a>
                    </li>
					<c:if test="${scuola.flgIstSta eq '0'}">
						<c:if test="${  scuola.codTipSit ne 'AA' 
                                        && scuola.codTipSit ne 'IC' 
                                        && scuola.codTipSit ne 'IS'
					                    && scuola.codTipSit ne 'CT' }">
                            <!-- /*&& not scuola.isSerale()*/ -->
					        <c:if test="${scuola.codTipSit ne 'VC' && scuola.codTipSit ne 'VE' && not scuola.isCircoloDidattico()}">                	                    
			                    <li ${functionalityOn eq 'alunni'?'class="sc-active"':''}>
                                <c:if test="${scuola.isSerale()}">
                                    <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/frequentanti/" />"><span>Alunni</span></a>
                                </c:if>
                                <c:if test="${not scuola.isSerale()}">
		                    		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/"/>"> <span>Alunni</span> </a>
		                    	</c:if>
                                </li>
		                    </c:if>
		                    <c:if test="${scuola.codTipSit ne 'VC'}">
	                            <li ${functionalityOn eq 'personale'?'class="sc-active"':''}>
	                                <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/personale/"/>"> <span>Personale</span> </a>
	                            </li>
                            </c:if>
		                </c:if>
		                    <c:if test="${  scuola.codTipSit ne 'CT' 
                                            && scuola.codTipSit ne 'VC' 
                                            && scuola.codTipSit ne 'VE' 
                                            && not scuola.isSerale() }">
			                	<c:if test="${functionalityOn eq 'finanza'}">
			                    	<li class="sc-active">
			                    </c:if>
			                	<c:if test="${functionalityOn ne 'finanza'}">
			                    	<li>
			                    </c:if>
		                    		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/"/>"> <span>Finanza</span> </a>
		                    	</li>
		                    </c:if>
		            </c:if>
		            
                    <c:if test="${scuola.flgIstSta eq '1' && scuola.codTipSit ne 'AA'}">
	                	<c:if test="${functionalityOn eq 'alunni'}">
	                    	<li class="sc-active">
	                    </c:if>
	                	<c:if test="${functionalityOn ne 'alunni'}">
	                    	<li>
	                    </c:if>
							<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/"/>"> <span>Alunni</span> </a>
						</li>	                            
                    </c:if>
                    
                    <c:if test="${not scuola.cpia}">
	                	<c:if test="${functionalityOn eq 'valutazione'}">
	                    	<li class="sc-active">
	                    </c:if>
	                	<c:if test="${functionalityOn ne 'valutazione'}">
	                    	<li>
	                    </c:if>
                       		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/"/>"> <span>Autovalutazione</span> </a>
                       	</li>
                   	</c:if>
                    
                    <c:if test="${scuola.mostraPulsanteEdilizia && scuola.flgIstSta eq '0' && not scuola.cpia}">
	                	<c:if test="${functionalityOn eq 'edifici'}">
	                    	<li class="sc-active">
	                    </c:if>
	                	<c:if test="${functionalityOn ne 'edifici'}">
	                    	<li>
	                    </c:if>
                    		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/edilizia/"/>"> <span>Edilizia</span> </a>
                    	</li>
                    </c:if>			            
                    
                    
                </c:if>
            </ul>
        </nav>
        <div class="sc-nav-responsive">
        	<a class="sc-nav-action" href="#"> 
        		<span class="sc-icon">Icona Menu</span>
            	<span class="sc-section">Chi Siamo</span> 
            	<span class="sc-sub-section">Informazioni</span>
        	</a>

            <div class="sc-scroll-cont-nav">
                <div class="sc-cont-nav">
                    <ul>
                        <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/"/>">CHI SIAMO</a>
                            <ul class="simple-accordion2">
					            <c:if test="${scuola.flagCentroFormazioneProfessionale eq 'S'}">
					                <c:if test="${not empty scuola.listaCorsi}">
					                	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/corsi/"/>">Corsi</a></li>
					                </c:if>
					            </c:if>                                
					            <c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S' && not scuola.cpia}">

					                <c:if test="${scuola.codTipSit eq 'AA'}">
					                	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/tempi-scuola-AA/"/>">Tempi scuola per l'a.s. <%=Utility.annoScolasticoCorrente()%></a></li>
					                </c:if>
					                						
					                <c:if test="${scuola.codTipSit eq 'EE'}">
					                	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/tempi-scuola/"/>">Tempi scuola per l'a.s. <%=Utility.annoScolasticoCorrente()%></a></li>
					                </c:if>
					
					                <c:if test="${scuola.codTipSit eq 'MM'}">
					                	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/tempi-scuola/"/>">Tempi scuola/indirizzi per l'a.s. <%=Utility.annoScolasticoCorrente()%></a></li>
					                </c:if>
					
					                <c:if test="${scuola.codTipSit > 'MM'}">
					                	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/offerta-formativa-anno-corrente/"/>">Indirizzi di studio per l'a.s. <%=Utility.annoScolasticoCorrente()%></a></li>
					                    <c:if test="${scuola.isSerale() eq 'false'}">
					                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/offerta-formativa-anno-successivo/"/>">Indirizzi di studio per l'a.s. <%=Utility.annoScolasticoSuccessivo()%></a></li>
					                    </c:if>
					                </c:if>
					            </c:if>			            
					            <c:if test="${scuola.flgPon eq 'true' && not scuola.cpia}">
					            	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/sintesi-progetti-pon/"/>">Sintesi progetti PON</a></li>
					            	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/progetti-fse/"/>">Progetti FSE</a></li>
					            	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/progetti-fesr/"/>">Progetti FESR</a></li>
					            </c:if>
					            
								<c:forEach items="${scuola.caratteristicaDiplomato}" var="voceMenu" varStatus="status">
								
									<li ${not empty voceMenu.sottomenu ?'class="sc-active"':''}>
									
										<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/${voceMenu.area}/" var="url_temp"/>
										<c:choose>
											<c:when test="${not empty voceMenu.sottomenu}">
												<p class="sc-simple-accordion-item">${voceMenu.tipologia.descrizione}</p>
											</c:when>
											<c:otherwise>
												<a href="${url_temp}">${voceMenu.tipologia.descrizione}</a>
											</c:otherwise>
										</c:choose>
										
										
										<c:if test="${not empty voceMenu.sottomenu}">
										
											<ul class="sc-simple-accordion-submenu open-item">
												<c:forEach items="${voceMenu.sottomenu}" var="voceSottoMenu" varStatus="statu">
													<li>
														<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/${voceMenu.area}/${voceSottoMenu.codice}"/>">${voceSottoMenu.descrizione}</a>
													</li>
												</c:forEach>
											</ul>
											
										</c:if>
										
									</li>
								</c:forEach>
								
                            </ul>
                        </li>
                        <c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S' && not scuola.cpia}">
                            
                            <c:if test="${scuola.codTipSit ne 'CT' && scuola.codTipSit ne 'VC' && scuola.codTipSit ne 'VE' && not scuola.isSerale()}">
                            	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/"/>">DIDATTICA</a>
	                                <ul>
	                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/"/>">Documenti</a></li>
	                                    <c:if test="${mostraLinkMenuLibriDiTesto eq 'true'}">
	                                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/libri/"/>">Libri di testo</a></li>
	                                    </c:if>
	                                    <c:if test="${scuola.flgPon eq 'true'}">
		                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/progetti-fse/"/>">La progettazione PON FSE</a></li>
		                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/progetti-fesr/"/>">La progettazione PON FESR</a></li>
		                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/progetti-incorso-fse/"/>">Progetti PON FSE in corso</a></li>
		                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/progetti-incorso-fesr/"/>">Progetti PON FESR in corso</a></li>
	                                    </c:if>
	                                </ul>
	                            </li>	                            	
                            </c:if>
                            
                            <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/servizi/"/>">SERVIZI E ATTIVITÀ</a>
                                <ul>
                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/servizi/"/>">Servizi</a></li>
                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/servizi/attivita/"/>">Attivit&agrave;</a></li>
                                </ul>	                            
                            </li>
                            
					        <c:if test="${scuola.flgIstSta eq '0' && not scuola.cpia}">
			                <c:if test="${ scuola.codTipSit ne 'AA' 
                                        && scuola.codTipSit ne 'IC' 
                                        && scuola.codTipSit ne 'IS'
			                            && scuola.codTipSit ne 'CT'}">
                                    <!-- && not scuola.isSerale() -->
                            		<c:if test="${scuola.codTipSit ne 'VC' && scuola.codTipSit ne 'VE' && not scuola.isCircoloDidattico()}">
			                            <c:if test="${scuola.isSerale()}">
                                            <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/frequentanti/"/>">ALUNNI</a>
                                                <ul>
                                                   <li ${subFunctionalityOn eq 'studenti-frequentanti'?'class="sc-active"':''}>
                                                        <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/frequentanti/" />">Studenti frequentanti</a>
                                                   </li>
                                                </ul>
                                            </li>
                                        </c:if>
                                        <c:if test="${not scuola.isSerale()}">
                                            <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/"/>">ALUNNI</a>
				                                <ul id="simple-accordion">
				                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/" />">Alunni e classi</a></li>
							                		<c:if test="${scuola.codTipSit ne 'EE'}">					                	
							                			<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/ripetenti/" />">Ripetenti, abbandoni e trasferiti</a></li>
							                		</c:if>
							                		<c:if test="${scuola.codTipSit eq 'EE'}">					                	
							                			<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/ripetenti/" />">Ripetenti e trasferiti</a></li>
							                		</c:if>					                                    
				                                    <c:if test="${scuola.secondariaSecGrado}">
				                                    	<%-- <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/" />">Alternanza scuola lavoro</a></li>--%>
                                                        <li class="sc-active"><p class="sc-simple-accordion-item">Alternanza scuola lavoro</p>
                                                            <ul class="sc-simple-accordion-submenu open-item">
                                                                <li>
                                                                    <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/strutture/" />">Strutture ospitanti</a>
                                                                </li>
                                                                <li>
                                                                    <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/percorsi/" />">Percorsi attivati</a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                    </c:if>				                                    
				                                    <c:if test="${scuola.codTipSit ne 'EE' && scuola.codTipSit ne 'AA'}">
					                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/risultati/scrutini/" />">Esiti scrutini</a></li>
					                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/risultati/esami/" />">Esami di stato</a></li>
				                                    </c:if>
				                                    <c:if test="${scuola.secondariaSecGrado}">
				                                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/passaggioII/" />">Passaggio dal I al II ciclo</a></li>
				                                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/universita/" />">Risultati a distanza - Universit&agrave;</a></li>
				                                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/lavoro/" />">Risultati a distanza - Lavoro</a></li>
				                                    </c:if>
				                                    <c:if test="${scuola.codTipSit eq 'MM'}">
				                                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/passaggioI/" />">Passaggio dal I al II ciclo</a></li>
				                                    </c:if>
				                                </ul>
				                            </li>
                                         </c:if>
		                            </c:if>
		                            
		                            <c:if test="${scuola.codTipSit ne 'VC'}">
			                            <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/personale/"/>">PERSONALE</a>
			                                <ul>
			                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/personale/"/>">Personale docente e ATA</a></li>
			                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/personale/mobilita/"/>">Mobilit&agrave;</a></li>
			                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/personale/assenze/"/>">Assenze</a></li>
			                                </ul>
			                            </li>
		                            </c:if>
		                            
		                        </c:if>
	                            <c:if test="${scuola.codTipSit ne 'CT' && scuola.codTipSit ne 'VC' && scuola.codTipSit ne 'VE' && not scuola.isSerale()}">
                            		<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/"/>">FINANZA</a>
		                                <ul>
		                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/"/>">Entrate per fonti di finanziamento</a></li>
		                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/bandi/"/>">AMM. TRASPARENTE - BANDI DI GARA E CONTRATTI</a></li>
		                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/pagamenti/"/>">AMM. TRASPARENTE - PAGAMENTI DELL'AMMINISTRAZIONE</a></li>
		                                </ul>		                            		
                            		</li>
	                            </c:if>
                            </c:if>
                            
                            <c:if test="${scuola.flgIstSta eq '1' && scuola.codTipSit ne 'AA'}">
	                            <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/"/>">ALUNNI</a>
	                                <ul id="simple-accordion">
	                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/" />">Alunni e classi</a></li>
				                		<c:if test="${scuola.codTipSit ne 'EE'}">					                	
				                			<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/ripetenti/" />">Ripetenti, abbandoni e trasferiti</a></li>
				                		</c:if>
				                		<c:if test="${scuola.codTipSit eq 'EE'}">					                	
				                			<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/ripetenti/" />">Ripetenti e trasferiti</a></li>
				                		</c:if>
	                                    <c:if test="${scuola.secondariaSecGrado}">
	                                    	<%-- <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/" />">Alternanza scuola lavoro</a></li> --%>
	                                        <li class="sc-active"><p class="sc-simple-accordion-item">Alternanza scuola lavoro</p>
                                                <ul class="sc-simple-accordion-submenu open-item">
                                                    <li>
                                                        <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/strutture/" />">Strutture ospitanti</a>
                                                    </li>
                                                    <li>
                                                        <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/percorsi/" />">Percorsi attivati</a>
                                                    </li>
                                                </ul>
                                            </li>
                                        </c:if>					                		                               
				                		<c:if test="${scuola.codTipSit ne 'EE' && scuola.codTipSit ne 'AA'}">
		                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/risultati/scrutini/" />">Esiti scrutini</a></li>
		                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/risultati/esami/" />">Esami di stato</a></li>
	                                    </c:if>
	                                    <c:if test="${scuola.secondariaSecGrado}">
	                                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/passaggioII/" />">Passaggio dal I al II ciclo</a></li>
	                                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/universita/" />">Risultati a distanza - Universit&agrave;</a></li>
	                                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/lavoro/" />">Risultati a distanza - Lavoro</a></li>
	                                    </c:if>
	                                    <c:if test="${scuola.codTipSit eq 'MM'}">
	                                    	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/passaggioI/" />">Passaggio dal I al II ciclo</a></li>
	                                    </c:if>		                                    
	                                </ul>
	                            </li>	                            
                            </c:if>
                            
                            <c:if test="${not scuola.cpia}">
                            	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/"/>">AUTOVALUTAZIONE</a>
	                                <ul>
	                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/"/>">Rapporto di autovalutazione</a></li>
	                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/sintesi/"/>">Naviga il RAV</a></li>
	                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/naviga/"/>">Indicatori</a></li>
	                                    <%-- <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/indicatori"/>">Indicatori</a></li> --%>
	                                    <li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/documenti/"/>">RAV in formato PDF</a></li>
	                                </ul>	                            	
                            	</li>
                        	</c:if>
                            
                            <c:if test="${scuola.mostraPulsanteEdilizia && scuola.flgIstSta eq '0' && not scuola.cpia}">
                            	<li><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/edilizia/"/>">EDILIZIA</a></li>
                            </c:if>
                            
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
		<div id="sbm" class="sc-save-fav mybookmark" 
			 data-idval="${scuola.codScuUt}" 
			 data-address="${scuola.indirizzoCompleto}" 
			 data-email="${scuola.desIndEml}"
			 data-midval="${scuola.codScuUtPri}"
			 data-mtit="${scuola.denScuPri}"
			 data-murl="<c:url value="/istituti/${scuola.codScuUtPri}/${scuola.denScuPriNorm}/"/>"
		>
			<a class="sbm-addclass" href="javascript:void(0)">
				<span>Salva nei preferiti</span>
			</a>
		</div>            
        
    </div>
</div>