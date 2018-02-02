<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
	<head>
		<%@ include file="../includes/head.jsp" %>
		
		<script>
			$(document).ready(function() {

				$(".formricerca").compare({
					"counterClass": "compare-counter"
				});

				$(".delall").click(function() {

					$(".formricerca").compare().delCookie();
				});

				$(".btn-confronta").click(function() {
					if ($(".formricerca").compare().num_items >= 2 && $(".formricerca").compare().num_items <= 6) {
						
						if (!checkConfrontaCFP()){
							modal({
								type: "info",
								title: "Attenzione!",
								text: 'I Centri di Formazione Professionale non sono confrontabili.',
								buttonText: {
									ok: "Chiudi"
								}
							});
						}else if (!checkConfrontaCT()){
							modal({
								type: "info",
								title: "Attenzione!",
								text: 'I Centri Territoriali non sono confrontabili.',
								buttonText: {
									ok: "Chiudi"
								}
							});
						}else if (checkConfrontaScuOrd()){
							document.forms[0].submit();
						}else{
							modal({
								type: "info",
								title: "Attenzione!",
								text: 'Le scuole di ordine e grado diversi non sono confrontabili.',
								buttonText: {
									ok: "Chiudi"
								}
							});
						}
						
					}else{
						if (!$(".formricerca").compare().num_items || $(".formricerca").compare().num_items == 1) {
							modal({
								type: "info",
								title: "Attenzione!",
								text: 'Selezionare almeno 2 scuole',
								buttonText: {
									ok: "Chiudi"
								}
							});
						}else{
							modal({
								type: "info",
								title: "Attenzione!",
								text: 'Puoi confrontare al massimo 6 scuole',
								buttonText: {
									ok: "Chiudi"
								}
							});
						}
					}
				});
			});
		</script>
				
	</head>
	<body>
	
	<%@ include file="../includes/toolbar_header.jsp" %>
	
    <section class="sc-internal-main">

        <div class="sc-title">
            <div class="sc-wrapper">
                <h1>${numeroScuoleTrovate} risultati <c:if test="${keywordsricercarapida ne ''}"> per "${keywordsricercarapida}"</c:if></h1>
            </div>
        </div>

        <form:form action="/cercalatuascuola/confronta/risultati" method="get" cssClass="formricerca" id="${idform}" >
        	<input type="hidden" name="idform" value="${idform}">
            <div class="sc-action-bar">
                <div class="sc-wrapper">
                    <ul>
                        <li class="sc-icon-search-bar">
                            <a class="sc-modal" href="/cercalatuascuola/ricerca/nuova/">
                                <span>Nuova ricerca</span>
                            </a>
                        </li>
                        <li class="sc-icon-print-bar">
                            <a href="javascript:window.print();">
                                <span>Stampa risultati</span>
                            </a>
                        </li>
                        <li class="sc-icon-info-bar">
                            <a href="/cercalatuascuola/info1/" class="sc-info-modal">
                                <span>Aiuto</span>
                            </a>
                        </li>
                    </ul>

					<div class="sc-action-bar-group">
				        <div class="sc-compare-action">
				        	<a href="javascript:void(0);" class="sc-button btn-confronta" title="Confronta (massimo 6 scuole).">Confronta</a><span class="sc-del-compare sc-section-menu"><a href="javascript:void(0);" class="sc-nav-action"><span class="sc-icon">▼</span></a></span>
				            <div class="sc-scroll-cont-nav">
				            	<div class="sc-cont-nav delall">
				            		<p><a href="javascript:void(0);">Deseleziona tutte</a></p>
				            	</div>
				            </div>
				        </div>
				        <div class="sc-map-action" style="float:right"><a href="javascript:void(0);" class="sc-button" title="Visualizza in mappa fino a 500 scuole">Visualizza in mappa</a> <span class="sc-map-action-open sc-section-menu-map"><a href="javascript:void(0);" class="sc-nav-action-map"><span class="sc-icon">▼</span></a></span>
				          <div class="sc-scroll-cont-nav-map">
				            <div class="sc-cont-nav">
				              <p><a href="/cercalatuascuola/mappa_istituti_confronta?idform=${idform}" class="btn-vis-sel sc-map-modal">Visualizza scuole selezionate</a></p>
				              <p><a href="/cercalatuascuola/mappa/risultati${queryStringWithoutPage}" class="btn-vis-all sc-map-modal">Visualizza tutte</a></p>
				              <p class="delall"><a href="javascript:void(0);">Deseleziona tutte</a></p>
				            </div>
				          </div>
				        </div>
					</div>
        			<div class="sc-list-action">
          				<p class="sc-label-list">
          					Scuole selezionate <span class="compare-counter sc-num-compare">0</span>
          				</p>
          			</div>
					
      							             

                </div>
            </div>

            <article class="sc-internal-content">
                <div class="sc-wrapper sc-wrapper-alt">
                    <table class="sc-table" id="tabellaRisultati">
                        <thead>
                            <tr>
                                <th>
                                    <span>Istituto Principale</span>
                                </th>                            
                                <th>
                                    <span>Plesso / Scuola</span>
                                </th>
                                <th>
                                    <span>Indirizzo</span>
                                </th>
                                <th>
                                    <span>Telefono</span>
                                </th>                                
                                <th>
                                    <span>E-mail</span>
                                </th>
                                <th class="sc-tab-mappa">
                                    <span>Mappa</span>
                                </th>
                                <th class="sc-tab-confronta">
                                    <span>Seleziona</span> <span class="sc-sel-help" title="Puoi selezionare fino a 500 scuole per visualizzarle in mappa e fino a 6 scuole per confrontarle fra loro. Le scuole di ordine e grado diversi non sono confrontabili.">(?)</span>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:if test="${not empty pagedListHolder.pageList}">
								<c:forEach items="${pagedListHolder.pageList}" var="voScuola" varStatus="status">
									<tr>
		                                <td data-col-1="Istituto Principale" class="sc-tab-istituto sc-col-2">
		                                    <a href="/cercalatuascuola/istituti/${voScuola.codMeccIstRif}/${voScuola.normalizedNameIstRif}/">${voScuola.denScuPri}</a>
		                                    <span class="sc-cod">${voScuola.codMeccIstRif}</span>
		                                </td>									
		                                <td data-col-2="Plesso / Scuola" class="sc-tab-plesso sc-col-1">
		                                    <a href="/cercalatuascuola/istituti/${voScuola.codMecc}/${voScuola.normalizedName}/">${voScuola.descrizione}</a>
		                                    <span class="sc-cod">${voScuola.codMecc}</span>
		                                </td>
		                                <td data-col-3="Indirizzo" class="sc-tab-indirizzo sc-col-3">
		                                    <span>${voScuola.indirizzoCompleto}</span>
		                                </td>
		                                <td data-col-4="Telefono" class="sc-tab-telefono sc-col-4">
		                                    <a href="tel:${voScuola.telefono}">${voScuola.telefono}</a>
		                                </td>		                                
		                                <td data-col-5="Email" class="sc-tab-email sc-col-5">
		                                    <a href="mailto:${voScuola.indirizzoEmail}">${voScuola.indirizzoEmail}</a>
		                                </td>
		                                <td data-col-6="Mappa" class="sc-tab-mappa sc-col-6">
		                                	<c:if test="${voScuola.latitudine ne ''}">
			                                	<c:if test="${voScuola.cfp}">
			                                    	<a href="/cercalatuascuola/mappa_istituto/${voScuola.codMecc}/${voScuola.normalizedName}/" class="sc-map-modal mappa pin-1">
			                                    </c:if>
			                                	<c:if test="${voScuola.checkStatale eq 'S'}">
			                                    	<a href="/cercalatuascuola/mappa_istituto/${voScuola.codMecc}/${voScuola.normalizedName}/" class="sc-map-modal mappa pin-3">
			                                    </c:if>
			                                	<c:if test="${voScuola.checkNonStatale eq 'S'}">
			                                    	<a href="/cercalatuascuola/mappa_istituto/${voScuola.codMecc}/${voScuola.normalizedName}/" class="sc-map-modal mappa pin-2">
			                                    </c:if>		                                    		                                    
			                                        <span>Mappa Link</span>
			                                    </a>
		                                    </c:if>
		                                </td>
		                                <td data-col-7="Confronta" class="sc-tab-confronta sc-col-7">
		                                    <c:if test="${not voScuola.cfp and voScuola.codTipSit ne 'CT' and voScuola.codTipSit ne 'IC' and voScuola.codTipSit ne 'IS' }">
		                                    	<input type="checkbox" name="conf-${idform}-${pagedListHolder.page}-${status.index}" id="conf-${idform}-${pagedListHolder.page}-${status.index}" value="${voScuola.codMecc}"/>
			                                    <label for="conf-${idform}-${pagedListHolder.page}-${status.index}" data-label="Seleziona">
			                                        <span>Seleziona</span>
			                                    </label>		                                    
		                                    </c:if>
		                                    <c:if test="${voScuola.cfp or voScuola.codTipSit eq 'CT' or voScuola.codTipSit eq 'IC' or voScuola.codTipSit eq 'IS'}">
		                                    	<input type="checkbox" name="conf-${idform}-${pagedListHolder.page}-${status.index}" id="conf-${idform}-${pagedListHolder.page}-${status.index}" value="${voScuola.codMecc}"/>
			                                    <label for="conf-${idform}-${pagedListHolder.page}-${status.index}" data-label="Seleziona">
			                                        <span>Seleziona</span>
			                                    </label>		                                    		                                    	
		                                    </c:if>		                                    

		                                </td>
									</tr>
								</c:forEach>
							</c:if>                    

                        </tbody>
                    </table>
                </div>

            </article>
            <div class="sc-action-bar bottom">
                <div class="sc-wrapper">
					<div class="sc-legend-map">
			          <p class="sc-legend-map-stat"><span style="color:#129dc7"> Statale</span></p>
			          <p class="sc-legend-map-par"><span style="color:#86bc29">Paritaria</span></p>
			          <p class="sc-legend-map-cfp"><span style="color:#a92f4a">Centri  Formazione Professionale</span> </p>
			        </div>

					<div class="sc-action-bar-group">
				        <div class="sc-compare-action">
				        	<a href="javascript:void(0);" class="sc-button btn-confronta" title="Confronta (massimo 6 scuole).">Confronta</a><span class="sc-del-compare sc-section-menu"><a href="javascript:void(0);" class="sc-nav-action"><span class="sc-icon">▼</span></a></span>
				            <div class="sc-scroll-cont-nav">
				            	<div class="sc-cont-nav delall">
				            		<p><a href="javascript:void(0);">Deseleziona tutte</a></p>
				            	</div>
				            </div>
				        </div>
				        <div class="sc-map-action" style="float:right"><a href="javascript:void(0);" class="sc-button" title="Visualizza in mappa fino a 500 scuole">Visualizza in mappa</a> <span class="sc-map-action-open sc-section-menu-map"><a href="javascript:void(0);" class="sc-nav-action-map"><span class="sc-icon">▼</span></a></span>
				          <div class="sc-scroll-cont-nav-map">
				            <div class="sc-cont-nav">
				              <p><a href="/cercalatuascuola/mappa_istituti_confronta?idform=${idform}" class="btn-vis-sel sc-map-modal">Visualizza scuole selezionate</a></p>
				              <p><a href="javascript:void(0);" class="btn-vis-all">Visualizza tutte</a></p>
				              <p class="delall"><a href="javascript:void(0);">Deseleziona tutte</a></p>
				            </div>
				          </div>
				        </div>
					</div>
        			<div class="sc-list-action">
          				<p class="sc-label-list">
          					Scuole selezionate <span class="compare-counter sc-num-compare">0</span>
          				</p>
          			</div>
          			
					<c:if test="${not empty pagedListHolder.pageList}">
			
						<c:url value="/ricerca/risultati${queryStringWithoutPage}" var="pagedLink">
						    <c:param name="page" value="~"/>
						</c:url>
						<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
						
					</c:if>
					
                </div>
            </div>

        </form:form>

    </section>

	<%@ include file="../includes/footerRisultati.jsp" %>
    
	</body>
</html>