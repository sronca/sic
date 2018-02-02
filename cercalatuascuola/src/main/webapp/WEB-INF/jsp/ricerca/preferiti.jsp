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
				
				
/* 				$(".btn-vis-sel").click(function() {
					if ($(".formricerca").compare().num_items >= 1) {
						$('#${idform}').attr('action','/cercalatuascuola/mappa_istituti_confronta');
						$('#${idform}').submit();
					}else{
						modal({
							type: "info",
							title: "Attenzione!",
							text: 'Selezionare almeno una scuola',
							buttonText: {
								ok: "Chiudi"
							}
						});
					}
				}); */
				
			});
		</script>
				
	</head>
	<body>
	
	<%@ include file="../includes/toolbar_header.jsp" %>
	
    <section class="sc-internal-main">

        <div class="sc-title">
            <div class="sc-wrapper">
                <h1>Preferiti</h1>
            </div>
        </div>

        <form:form action="/cercalatuascuola/confronta/risultati" method="get" cssClass="formricerca" id="${idform}" >
        	<input type="hidden" name="idform" value="${idform}">
            <div class="sc-action-bar">
                <div class="sc-wrapper">
					<div class="sc-back-action sc-back-left">
						<a href="javascript:history.back();" class="sc-button">Torna indietro</a>
					</div>                
                    <ul>
                        <li class="sc-icon-search-bar">
                            <a class="sc-modal" href="/cercalatuascuola/ricerca/nuova/">
                                <span>Nuova ricerca</span>
                            </a>
                        </li>                    
                        <li class="sc-icon-print-bar">
                            <a href="javascript:window.print();">
                                <span>Stampa</span>
                            </a>
                        </li>
                        <li class="sc-icon-info-bar">
                            <a href="/cercalatuascuola/info2/" class="sc-info-modal">
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
                    <table class="sc-table">
                        <thead>
                            <tr>                            
                                <th>
                                    <span>Istituto Principale</span>
                                </th>
                                <th>
                                    <span>Plesso / Scuola</span><span class="sc-remove-htitle" id="sbm-delall"><a href="javascript:void(0);" title="Rimuovi tutto da Preferiti">Rimuovi tutto da Preferiti</a></span>
                                </th>
                                <th>
                                    <span>Indirizzo</span>
                                </th>
                                <th>
                                    <span>E-mail</span>
                                </th>
                                <th class="sc-tab-mappa">
                                    <span>Mappa</span>
                                </th>
                                <th class="sc-tab-confronta">
                                    <span>Seleziona</span>
                                </th>
                            </tr>
                        </thead>
                        <tbody class="sbm-list">                    
                        </tbody>
                    </table>
                </div>

            </article>
            <div class="sc-action-bar bottom">
                <div class="sc-wrapper">
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

        </form:form>

    </section>

	<%@ include file="../includes/footer.jsp" %>
    
	</body>
</html>