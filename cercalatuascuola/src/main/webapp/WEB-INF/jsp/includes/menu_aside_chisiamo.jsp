		            <aside class="sc-table-cell">
		            	<span class="sc-main-menu-section"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/"/>">Chi siamo</a></span>
		                <nav class="sc-list-side-menu">
		                    <ul id="simple-accordion">
					            <c:if test="${scuola.flagCentroFormazioneProfessionale eq 'S'}">
					                <c:if test="${not empty scuola.listaCorsi}">
					                	<c:if test="${subFunctionalityOn eq 'home-corsi'}">
					                		<li class="sc-active">
					                	</c:if>
					                	<c:if test="${subFunctionalityOn ne 'home-corsi'}">
					                		<li>
					                	</c:if>					                	
					                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/corsi/"/>">Corsi</a>
					                	</li>
					                </c:if>
					            </c:if>
					            <c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S' && not scuola.cpia}">
					
					                <c:if test="${scuola.codTipSit eq 'AA'}">
					                	<c:if test="${subFunctionalityOn eq 'home-tempi-scuola-AA'}">
					                		<li class="sc-active">
					                	</c:if>
					                	<c:if test="${subFunctionalityOn ne 'home-tempi-scuola-AA'}">
					                		<li>
					                	</c:if>	
					                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/tempi-scuola-AA/"/>">Tempi scuola per l'a.s. <%=Utility.annoScolasticoCorrente()%></a>
					                	</li>
					                </c:if>
					                
					                <c:if test="${scuola.codTipSit eq 'EE'}">
					                	<c:if test="${subFunctionalityOn eq 'home-tempi-scuola'}">
					                		<li class="sc-active">
					                	</c:if>
					                	<c:if test="${subFunctionalityOn ne 'home-tempi-scuola'}">
					                		<li>
					                	</c:if>	
					                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/tempi-scuola/"/>">Tempi scuola per l'a.s. <%=Utility.annoScolasticoCorrente()%></a>
					                	</li>
					                </c:if>
					
					                <c:if test="${scuola.codTipSit eq 'MM'}">
					                	<c:if test="${subFunctionalityOn eq 'home-tempi-scuola'}">
					                		<li class="sc-active">
					                	</c:if>
					                	<c:if test="${subFunctionalityOn ne 'home-tempi-scuola'}">
					                		<li>
					                	</c:if>	
					                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/tempi-scuola/"/>">Tempi scuola/indirizzi per l'a.s. <%=Utility.annoScolasticoCorrente()%></a>
					                	</li>
					                </c:if>
					
					                <c:if test="${scuola.codTipSit > 'MM'}">
					                	<c:if test="${subFunctionalityOn eq 'home-offerta-formativa-anno-corrente'}">
					                		<li class="sc-active">
					                	</c:if>
					                	<c:if test="${subFunctionalityOn ne 'home-offerta-formativa-anno-corrente'}">
					                		<li>
					                	</c:if>	
					                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/offerta-formativa-anno-corrente/"/>">Indirizzi di studio per l'a.s. <%=Utility.annoScolasticoCorrente()%></a>
					                	</li>
					                    <c:if test="${scuola.isSerale() eq 'false'}">
						                	<c:if test="${subFunctionalityOn eq 'home-offerta-formativa-anno-successivo'}">
						                		<li class="sc-active">
						                	</c:if>
						                	<c:if test="${subFunctionalityOn ne 'home-offerta-formativa-anno-successivo'}">
						                		<li>
						                	</c:if>	
					                    		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/offerta-formativa-anno-successivo/"/>">Indirizzi di studio per l'a.s. <%=Utility.annoScolasticoSuccessivo()%></a>
					                    	</li>
					                    </c:if>
					                </c:if>
					            </c:if>			            
		
					            <c:if test="${scuola.flgPon eq 'true' && not scuola.cpia}">
				                	<c:if test="${subFunctionalityOn eq 'home-sintesi-progetti-pon'}">
				                		<li class="sc-active">
				                	</c:if>
				                	<c:if test="${subFunctionalityOn ne 'home-sintesi-progetti-pon'}">
				                		<li>
				                	</c:if>	
					            		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/sintesi-progetti-pon/"/>">Sintesi progetti PON</a>
					            	</li>
				                	<c:if test="${subFunctionalityOn eq 'home-progetti-fse'}">
				                		<li class="sc-active">
				                	</c:if>
				                	<c:if test="${subFunctionalityOn ne 'home-progetti-fse'}">
				                		<li>
				                	</c:if>	
					            		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/progetti-fse/"/>">Progetti FSE</a>
					            	</li>
				                	<c:if test="${subFunctionalityOn eq 'home-progetti-fesr'}">
				                		<li class="sc-active">
				                	</c:if>
				                	<c:if test="${subFunctionalityOn ne 'home-progetti-fesr'}">
				                		<li>
				                	</c:if>	
					            		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/progetti-fesr/"/>">Progetti FESR</a>
					            	</li>
					            </c:if>			            
								
								<c:forEach items="${scuola.caratteristicaDiplomato}" var="voceMenu" varStatus="status">
								
									<li ${subFunctionalityOn eq voceMenu.area ?'class="sc-active"':''}>
									
										<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/${voceMenu.area}/" var="url_temp"/>
										<a href="${not empty voceMenu.sottomenu ? 'javascript:void(0)' : url_temp }"  ${not empty voceMenu.sottomenu ? 'class="sc-simple-accordion-item"' : '' }>${voceMenu.tipologia.descrizione}</a>
										
										<c:if test="${not empty voceMenu.sottomenu}">
										
											<ul class="sc-simple-accordion-submenu ${subFunctionalityOn eq voceMenu.area ?'open-item':''}">
												<c:forEach items="${voceMenu.sottomenu}" var="voceSottoMenu" varStatus="statu">
													<li ${subSubFunctionalityOn eq voceSottoMenu.codice ?'class="sc-active"':''}><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/${voceMenu.area}/${voceSottoMenu.codice}"/>">${voceSottoMenu.descrizione}</a></li>
												</c:forEach>
											</ul>
											
										</c:if>
									</li>
								</c:forEach>
								
		                    </ul>
		                </nav>
			            <c:if test="${scuola.flagCentroFormazioneProfessionale ne 'S' && not scuola.cpia}">
							<div class="bottoni sc-position-ab sc-sx-col">
								<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/informazioni/in-evidenza/"/>" class="sc-button bacheca">Bacheca</a>
							</div>
						</c:if>		                
		                <div class="sc-share">
		                    <h2>Condividi</h2>
		                    <div id="share"></div>
		                </div>
		            </aside>