		            <aside class="sc-table-cell">
		            	<span class="sc-main-menu-section"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/servizi/"/>">Servizi e attivit&agrave;</a></span>
		                <nav class="sc-list-side-menu">
		                    <ul>
			                	<c:if test="${subFunctionalityOn eq 'servizi-servizi'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'servizi-servizi'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/servizi/"/>">Servizi</a>
			                	</li>
			                	
			                	<c:if test="${subFunctionalityOn eq 'servizi-attivita'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'servizi-attivita'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/servizi/attivita/"/>">Attivit&agrave;</a>
			                	</li>		                			                	
		                    </ul>
		                </nav>
		                <div class="sc-share">
		                    <h2>Condividi</h2>
							<div id="share"></div>
		                </div>
		            </aside>