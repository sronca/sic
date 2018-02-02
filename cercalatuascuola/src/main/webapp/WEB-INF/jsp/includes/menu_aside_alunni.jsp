<aside class="sc-table-cell">
	<span class="sc-main-menu-section"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/"/>">Alunni</a></span>
    <nav class="sc-list-side-menu">
        <c:if test="${scuola.isSerale()}">
            <ul>
               <li ${subFunctionalityOn eq 'studenti-frequentanti'?'class="sc-active"':''}>
                    <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/frequentanti/" />">Studenti frequentanti</a>
               </li>
            </ul>
        </c:if>
        <c:if test="${not scuola.isSerale()}">
            <ul id="simple-accordion">
            	<li ${subFunctionalityOn eq 'alunni-classi'?'class="sc-active"':''}>
            		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/" />">Alunni e classi</a>
            	</li>
            	<li ${subFunctionalityOn eq 'alunni-ripetenti'?'class="sc-active"':''}>
            		<c:if test="${scuola.codTipSit ne 'EE'}">					                	
            			<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/ripetenti/" />">Ripetenti, abbandoni e trasferiti</a>
            		</c:if>
            		<c:if test="${scuola.codTipSit eq 'EE'}">					                	
            			<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/ripetenti/" />">Ripetenti e trasferiti</a>
            		</c:if>			                		
            	</li>
            	<c:if test="${scuola.secondariaSecGrado}">
                    <%-- <li ${subFunctionalityOn eq 'alunni-alternanza'?'class="sc-active"':''}>
                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/" />">Alternanza scuola lavoro</a>
                	</li> --%>
                    <li class="sc-active"<%-- ${subFunctionalityOn eq 'alunni-alternanza'?'class="sc-active"':''} --%>><a href="#" class="sc-simple-accordion-item">Alternanza scuola lavoro</a>
                        <ul class="sc-simple-accordion-submenu open-item<%-- ${subFunctionalityOn eq 'alunni-alternanza'?'open-item':''} --%>">
                            <li ${subSubFunctionalityOn eq 'alunni-alternanza-strutture'?'class="sc-active"':''}>
                                <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/strutture/" />">Strutture ospitanti</a>
                            </li>
                            <li ${subSubFunctionalityOn eq 'alunni-alternanza-percorsi'?'class="sc-active"':''}>
                                <a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/percorsi/" />">Percorsi attivati</a>
                            </li>
                        </ul>
                    </li>
                </c:if>		                	
	            <c:if test="${scuola.codTipSit ne 'EE' && scuola.codTipSit ne 'AA'}">
                	<li ${subFunctionalityOn eq 'alunni-scrutini'?'class="sc-active"':''}>
                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/risultati/scrutini/" />">Esiti scrutini</a>
                	</li>
                	<li ${subFunctionalityOn eq 'alunni-esami'?'class="sc-active"':''}>
                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/risultati/esami/" />">Esami di stato</a>
                	</li>			                							            
	            </c:if>
	            <c:if test="${scuola.secondariaSecGrado}">
                	<li ${subFunctionalityOn eq 'alunni-passaggioII'?'class="sc-active"':''}>
                 		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/passaggioII/" />">Passaggio dal I al II ciclo</a>
                	</li>
                    <li ${subFunctionalityOn eq 'alunni-risultati'?'class="sc-active"':''}>
                 		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/universita/" />">Risultati a distanza - Universit&agrave;</a>
                	</li>
                	<li ${subFunctionalityOn eq 'alunni-lavoro'?'class="sc-active"':''}>
                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/lavoro/" />">Risultati a distanza - Lavoro</a>
                	</li>				                	
	            </c:if>
	            <c:if test="${scuola.codTipSit eq 'MM'}">
                    <li ${subFunctionalityOn eq 'alunni-passaggioI'?'class="sc-active"':''}>
                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/passaggioI/" />">Passaggio dal I al II ciclo</a>
                	</li>
	            </c:if>					            
            </ul>
         </c:if>
    </nav>
    <div class="sc-share">
        <h2>Condividi</h2>
		<div id="share"></div>
    </div>
</aside>