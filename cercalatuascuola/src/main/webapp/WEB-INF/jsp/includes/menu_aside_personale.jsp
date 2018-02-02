		            <aside class="sc-table-cell">
		            	<span class="sc-main-menu-section"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/personale/"/>">Personale</a></span>
		                <nav class="sc-list-side-menu">
		                    <ul>
			                	<c:if test="${subFunctionalityOn eq 'personale-personale'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'personale-personale'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/personale/"/>">Personale docente e ATA</a>
			                	</li>
			                	
			                	<c:if test="${subFunctionalityOn eq 'personale-mobilita'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'personale-mobilita'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/personale/mobilita/"/>">Mobilit&agrave;</a>
			                	</li>
			                	
			                	<c:if test="${subFunctionalityOn eq 'personale-assenze'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'personale-assenze'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/personale/assenze/"/>">Assenze</a>
			                	</li>			                			                	
		                    </ul>
		                </nav>
		                <div class="sc-share">
		                    <h2>Condividi</h2>
							<div id="share"></div>
		                </div>
		            </aside>