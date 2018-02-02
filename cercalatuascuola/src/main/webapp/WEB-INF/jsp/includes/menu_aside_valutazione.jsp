		            <aside class="sc-table-cell">
		            	<span class="sc-main-menu-section"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/"/>">Autovalutazione</a></span>
		                <nav class="sc-list-side-menu">
		                    <ul>
			                	<c:if test="${subFunctionalityOn eq 'valutazione-rav'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'valutazione-rav'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/"/>">Rapporto di autovalutazione</a>
			                	</li>
			                	
			                	<c:if test="${subFunctionalityOn eq 'valutazione-sintesi'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'valutazione-sintesi'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/sintesi/"/>">Naviga il RAV</a>
			                	</li>
			                	
 			                	<c:if test="${subFunctionalityOn eq 'valutazione-naviga'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'valutazione-naviga'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/naviga/"/>">Indicatori</a>
			                	</li>	                	
			                	
<%-- 			                	<c:if test="${subFunctionalityOn eq 'valutazione-indicatori'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'valutazione-indicatori'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/indicatori"/>">Indicatori</a>
			                	</li> --%>
			                				                	
			                	<c:if test="${subFunctionalityOn eq 'valutazione-documenti'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'valutazione-documenti'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/valutazione/documenti/"/>">RAV in formato PDF</a>
			                	</li>
			                	
			                				                			                			                	
		                    </ul>
		                </nav>
		                <div class="sc-share">
		                    <h2>Condividi</h2>
							<div id="share"></div>
		                </div>
		            </aside>