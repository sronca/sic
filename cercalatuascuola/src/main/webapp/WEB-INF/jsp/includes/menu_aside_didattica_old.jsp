		            <aside class="sc-table-cell">
		            	<span class="sc-main-menu-section"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/"/>">Didattica</a></span>
		                <nav class="sc-list-side-menu">
		                    <ul>
			                	<c:if test="${subFunctionalityOn eq 'didattica-documenti'}">
			                		<li class="sc-active">
			                	</c:if>
			                	<c:if test="${subFunctionalityOn ne 'didattica-documenti'}">
			                		<li>
			                	</c:if>					                	
			                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/"/>">Documenti</a>
			                	</li>
			                	
			                	<c:if test="${mostraLinkMenuLibriDiTesto eq 'true'}">
				                	<c:if test="${subFunctionalityOn eq 'didattica-libri'}">
				                		<li class="sc-active">
				                	</c:if>
				                	<c:if test="${subFunctionalityOn ne 'didattica-libri'}">
				                		<li>
				                	</c:if>					                	
				                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/libri/"/>">Libri di testo</a>
				                	</li>			                	
			                	</c:if>
			                	
			                	<c:if test="${scuola.flgPon eq 'true'}">
				                	<c:if test="${subFunctionalityOn eq 'didattica-progetti-fse'}">
				                		<li class="sc-active">
				                	</c:if>
				                	<c:if test="${subFunctionalityOn ne 'didattica-progetti-fse'}">
				                		<li>
				                	</c:if>					                	
				                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/progetti-fse/"/>">La progettazione PON FSE</a>
				                	</li>
	
				                	<c:if test="${subFunctionalityOn eq 'didattica-progetti-fesr'}">
				                		<li class="sc-active">
				                	</c:if>
				                	<c:if test="${subFunctionalityOn ne 'didattica-progetti-fesr'}">
				                		<li>
				                	</c:if>					                	
				                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/progetti-fesr/"/>">La progettazione PON FESR</a>
				                	</li>
	
				                	<c:if test="${subFunctionalityOn eq 'didattica-progetti-incorso-fse'}">
				                		<li class="sc-active">
				                	</c:if>
				                	<c:if test="${subFunctionalityOn ne 'didattica-progetti-incorso-fse'}">
				                		<li>
				                	</c:if>					                	
				                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/progetti-incorso-fse/"/>">Progetti PON FSE in corso</a>
				                	</li>
	
				                	<c:if test="${subFunctionalityOn eq 'didattica-progetti-incorso-fesr'}">
				                		<li class="sc-active">
				                	</c:if>
				                	<c:if test="${subFunctionalityOn ne 'didattica-progetti-incorso-fesr'}">
				                		<li>
				                	</c:if>					                	
				                		<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/didattica/progetti-incorso-fesr/"/>">Progetti PON FESR in corso</a>
				                	</li>			                	
			                	</c:if>             			                	
		                    </ul>
		                </nav>
		                <div class="sc-share">
		                    <h2>Condividi</h2>
							<div id="share"></div>
		                </div>
		            </aside>