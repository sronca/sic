		            <aside class="sc-table-cell">
		            	<span class="sc-main-menu-section"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/"/>">Finanza</a></span>
		                <nav class="sc-list-side-menu">
		                    <ul>
			                	<c:if test="${subFunctionalityOn eq 'finanza-finanza'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'finanza-finanza'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/"/>">Entrate per fonti di finanziamento</a>
			                	</li>
			                			                    
			                	<c:if test="${subFunctionalityOn eq 'finanza-bandi'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'finanza-bandi'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/bandi/"/>">AMM. TRASPARENTE - BANDI DI GARA E CONTRATTI</a>
			                	</li>
			                	
 			                	<c:if test="${subFunctionalityOn eq 'finanza-pagamenti'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'finanza-pagamenti'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/finanza/pagamenti/"/>">AMM. TRASPARENTE - PAGAMENTI DELL'AMMINISTRAZIONE</a>
			                	</li>		                			                	
		                    </ul>
		                </nav>
		                <div class="sc-share">
		                    <h2>Condividi</h2>
							<div id="share"></div>
		                </div>
		            </aside>