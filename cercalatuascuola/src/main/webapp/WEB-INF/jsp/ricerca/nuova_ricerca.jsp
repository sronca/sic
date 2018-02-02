<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>

	<head>
		<%@ include file="../includes/head.jsp" %>
	</head>
	

<body class="sc-home" onload="initialize();">

	<%@ include file="../includes/toolbar_header.jsp" %>
	
    <section class="sc-main">

        <div class="sc-main-content">

            <div class="sc-logo">
                <h1>Scuola in Chiaro</h1>
            </div>


			<div class="sc-new-search sc-zoom-anim-dialog">
			
				<script src="<c:url value="/resources/js/jquery.s-map-contextmenu.js"/>"></script>

	            <nav class="sc-search-nav">
	                <ul>
	                    <li class="active">
	                        <a class="sc-icon-def" data-tab="sc-search-def" href="javascript:void(0);" title="Ricerca rapida">
	                            <span>Ricerca rapida</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a class="sc-icon-pos" data-tab="sc-search-pos" href="javascript:void(0);" title="Ricerca per posizione">
	                            <span>Ricerca per posizione</span>
	                        </a>
	                    </li>
	                    <li>
	                        <a class="sc-icon-adv" data-tab="sc-search-adv" href="javascript:void(0);" title="Ricerca avanzata">
	                            <span>Ricerca avanzata</span>
	                        </a>
	                    </li>
	                </ul>
	            </nav>

	            <section class="sc-search-content">
	
	                <article class="sc-search-def active">
	                    <h2>Ricerca rapida</h2>
						<form:form action="/cercalatuascuola/ricerca/risultati" method="get" commandName="voRicerca" cssClass="sc-form" id="formRicercaRapida">
	                        <fieldset>
	                            <div class="sc-form-content">
	                                <div class="sc-fields">
	                                    <div class="sc-field sc-field-2-3">
											<input type="text"
												   name="rapida"
												   id="rapida" 
												   spellcheck="false" 
												   placeholder="Ricerca rapida"
												   value="${voRicerca.rapida}"
												   class="required"
											/>
	                                    </div>
	                                    <div class="sc-field sc-field-1-3">
											<input type="hidden" name="tipoRicerca" value="RAPIDA" />
											<input type="hidden" name="gidf" value="1" />
											<button class="sc-button" type="submit">Cerca</button>
	                                    </div>
	                                </div>
	                            </div>
	                        </fieldset>
	                    </form:form>                
	                </article>
	
				<article class="sc-search-pos">
					<h2>Ricerca per posizione</h2>
					<form:form action="/cercalatuascuola/ricerca/risultati"
						method="get" commandName="voRicerca" cssClass="sc-form"
						id="voRicerca_posizione">
						<fieldset>
							<div class="sc-form-content sc-col-1-3">
								<div class="sc-fields">
									<div class="sc-field">
										<div class="sc-gps">
											<input type="text" value=""
												placeholder="Inserisci un indirizzo o attiva la localizzazione automatica"
												id="indirizzoRiferimento" name="indirizzoRiferimento"
												title="Inserisci un indirizzo o attiva la localizzazione automatica"
												class="required" autocomplete="off" />
											<div class="sc-gps-action" id="btnForGetLocation">gps</div>
											<input type="hidden" id="latIndirizzoRiferimento"
												name="latIndirizzoRiferimento" /> <input type="hidden"
												id="lngIndirizzoRiferimento" name="lngIndirizzoRiferimento" />
											<!-- <div class="sc-mappa-action"><a href="javascript:void(0);" class="mappa pin-small" onclick="toggle_visibility('mappa');" title="Apri la mappa"> <span>mappa</span> </a> </div> -->
										</div>
									</div>
								</div>
								<div class="sc-fields">
									<div class="sc-field sc-field-1-3">
										<label>Raggio * </label>
										<div class="sc-select">
											<form:select path="raggio" items="${listaRaggi}"
												title="Selezionare un raggio per la ricerca per posizione"
												cssClass="required" onchange="disegnaCerchioProssimita();abilita_disabilita_btn();" />
										</div>
									</div>
									<div class="sc-field sc-field-2-3">
										<label>Tipo di istruzione * </label>
										<div class="sc-select">
											<form:select path="codiceOrdine" items="${listaOrdini}"
												title="Selezionare il tipo di istruzione"
												cssClass="required codiceOrdine_posizione"
												onchange="caricaTipologia(this.value,'');abilita_disabilita_btn();" />
										</div>
									</div>
								</div>
								<div class="sc-fields" id="tipoScuola_container">
									<div class="sc-field sc-field-1-2">
										<label>Statale</label>
										<div class="sc-cont-check">
											<div class="sc-cont-check-cel">
												<input type="checkbox" id="checkStatale" name="checkStatale" value="S" />
												<!-- <input type="radio" class="radioTipoScuola" id="radioTipoScuolaStatale"
													name="radioTipoScuola"
													onclick="javascript:caricaTipologia('4','')"
													style="display: none" checked="checked" value="Statale" /> -->
											</div>
										</div>
									</div>
									<div class="sc-field sc-field-1-2">
										<label>Paritaria</label>
										<div class="sc-cont-check">
											<div class="sc-cont-check-cel">
												<input type="checkbox" id="checkNonStatale" name="checkNonStatale" value="S" />
												<!-- <input type="radio" class="radioTipoScuola"
													id="radioTipoScuolaNonStatale" name="radioTipoScuola"
													onclick="javascript:caricaTipologia('4','')"
													style="display: none" value="NonStatale" /> -->
											</div>
										</div>
									</div>
								</div>
								<div class="sc-fields"
									id="tipologiaStataleNuovoOrdinamento_container">
									<div class="sc-field">
										<label>Scuola secondaria di II grado
											${msgANNO_SCOLASTICO_INDIRIZZI_STUDIO}</label>
										<div class="sc-select">
											<form:select path="codiceTipologiaStataleNuovoOrdinamento"
												title="Selezionare tipo scuola"
												onchange="javascript:caricaSettoreScuola(this.value,'');" />
										</div>
									</div>
								</div>
								<%-- <div class="sc-fields" id="tipologiaNonStatale_container">
									<div class="sc-field">
										<label>Scuola secondaria di II grado</label>
										<div class="sc-select">
											<form:select path="codiceTipologiaNonStatale"
												title="Selezionare tipo scuola" />
										</div>
									</div>
								</div> --%>
								<div class="sc-fields" id="settoreScuola_container">
									<div class="sc-field">
										<label id="settore_percorso">Settore/Percorso</label>
										<div class="sc-select">
											<form:select path="codiceSettoreScuola"
												title="Selezionare settore scuola"
												onchange="javascript:caricaIndirizzoStudio('');" />
										</div>
									</div>
								</div>
								<div class="sc-fields" id="biennio_triennio_container">
									<div class="sc-field sc-field-1-2">
										<label>Biennio</label>
										<div class="sc-cont-check">
											<div class="sc-cont-check-cel">
												<input type="radio" class="radioBiennioTriennio"
													id="radioBiennioTriennio" name="radioBiennioTriennio"
													value="Biennio"
													onclick="javascript:caricaIndirizzoStudio('');"
													style="display: block" checked="checked" />
											</div>
										</div>
									</div>
									<div class="sc-field sc-field-1-2">
										<label>Triennio</label>
										<div class="sc-cont-check">
											<div class="sc-cont-check-cel">
												<input type="radio" class="radioBiennioTriennio"
													id="radioBiennioTriennio" name="radioBiennioTriennio"
													value="Triennio"
													onclick="javascript:caricaIndirizzoStudio('');"
													style="display: block" />
											</div>
										</div>
									</div>
								</div>
								<div class="sc-fields" id="indirizzoStudio_container">
									<div class="sc-field">
										<label id="indirizzo_studio_qualifica">Indirizzo di
											studio</label>
										<div class="sc-select">
											<form:select path="codiceIndirizzo"
												title="Selezionare indirizzo di studio" />
										</div>
									</div>
								</div>
								<div class="sc-fields" id="codiceCFP_container">
									<div class="sc-field">
										<label>Percorso</label>
										<div class="sc-select">
											<form:select path="codiceCFPPercorso"
												title="Selezionare tipo centro formazione professionale"
												onchange="javascript:caricaCFP(this.value,'');" />
										</div>
									</div>
								</div>
								<div class="sc-fields" id="settoreCFP_container">
									<div class="sc-field">
										<label>Settore</label>
										<div class="sc-select">
											<form:select path="codiceCFPSettore"
												title="Selezionare settore"
												onchange="javascript:caricaCFPIndirizzoStudio('');" />
										</div>
									</div>
								</div>
								<div class="sc-fields" id="indirizzoCFP_container">
									<div class="sc-field">
										<label>Indirizzo di studio</label>
										<div class="sc-select">
											<form:select path="codiceCFPIndirizzo"
												title="Selezionare indirizzo di studio" />
										</div>
									</div>
								</div>
								<div class="sc-fields" id="tempiScuolaScuolaPrimaria_container">
									<div class="sc-field">
										<label>Tempi Scuola primaria</label>
										<div class="sc-select">
											<form:select path="codiceTempoPrimaria"
												title="Selezionare Tempi Scuola" />
										</div>
									</div>
								</div>
								<div class="sc-fields"
									id="tempiScuolaScuolaSecondaria1Grado_container">
									<div class="sc-field">
										<label>Tempi Scuola secondaria di I grado</label>
										<div class="sc-select">
											<form:select path="codiceTempoSecondaria1Grado"
												title="Selezionare Tempi Scuola" />
										</div>
									</div>
								</div>
								<div class="sc-fields" id="indirizzoMusicale_container">
									<div class="sc-field">
										<label>Indirizzo musicale</label>
										<div class="sc-cont-check">
											<div class="sc-cont-check-cel">
												<input type="checkbox" id="checkIndirizzoMusicale"
													name="checkIndirizzoMusicale" value="S" />
											</div>
										</div>
									</div>
								</div>
								<div class="sc-submit">
									<input type="hidden" name="tipoRicerca" value="VICINO_A_TE" />
									<input type="hidden" name="gidf" value="1" />
									<button class="sc-button inactive" type="button"
										id="btn_vis_mappa_posizione" disabled="disabled">Visualizza in mappa</button>
									<button class="sc-button inactive" type="button"
										id="btn_vis_elenco_posizione" disabled="disabled">Visualizza
										elenco</button>
								</div>

							</div>

							<div class="sc-mappa-home sc-col-2-3" id="mappa">
								<div class="sc-legend-map">
									<p class="sc-legend-map-stat">
										<span style="color: #129dc7"> Statale</span>
									</p>
									<p class="sc-legend-map-par">
										<span style="color: #86bc29">Paritaria</span>
									</p>
									<p class="sc-legend-map-cfp">
										<span style="color: #a92f4a">Centri Formazione
											Professionale</span>
									</p>
								</div>							
								<div class="sc-map-wrapper">
									<div id="sc-map-canvas-2" data-tipo="RIC_POS"></div>
								</div>
							</div>

						</fieldset>
					</form:form>
				</article>
	
	                <article class="sc-search-adv">
	                    <h2>Ricerca avanzata</h2>
	                    <form:form action="/cercalatuascuola/ricerca/risultati" method="get" commandName="voRicerca">
	                        <fieldset>
	                            <div class="sc-form-content">
	                                <div class="sc-fields">
	                                    <div class="sc-field sc-field-1-2">
	                                        <label>Regione </label>
	                                        <div class="sc-select">
											<form:select 
													path="codiceRegione" 
													title="Selezionare una regione"
													onchange="javascript:caricaProvincia(this.value);"
													items="${listaRegioni}"
													cssClass="required"
											/>
	                                        </div>
	                                    </div>
	                                    <div class="sc-field sc-field-1-2" id="provincia_container">
	                                        <label>Provincia </label>
	                                        <div class="sc-select">
											<form:select 
														path="codiceProvincia" 
														title="Selezionare provincia"
														onchange="javascript:caricaComune(this.value);"
											/>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="sc-fields">
	                                    <div class="sc-field sc-field-1-2" id="comune_container">
	                                        <label>Comune </label>
	                                        <div class="sc-select">
											<form:select 
														path="codiceComune" 
														title="Selezionare comune"
											/>
	                                        </div>
	                                    </div>
	                                    <div class="sc-field sc-field-1-2">
	                                        <label>Tipo di istruzione </label>
	                                        <div class="sc-select">
											<form:select 
														path="codiceOrdine"
														id="codiceOrdineAvanzata" 
														title="Selezionare l'ordine scuola"
														onchange="javascript:caricaTipologia(this.value,'Avanzata');"
														items="${listaOrdini}"
														cssClass="required"
											/>
	                                        </div>
	                                    </div>
	                                </div>								
									<div class="sc-fields" id="tipoScuola_containerAvanzata">
	                                    <div class="sc-field sc-field-1-2">
	                                        <label>Statale</label>
	                                        <div class="sc-cont-check">
												<div class="sc-cont-check-cel">
													<input type="checkbox" id="checkStataleAvanzata"
														name="checkStatale" value="S" />
													<!-- <input
														type="radio" class="radioTipoScuolaAvanzata"
														id="radioTipoScuolaStataleAvanzata" name="radioTipoScuola"
														onclick="javascript:caricaTipologia('4','Avanzata')"
														style="display: none" checked="checked" value="Statale" /> -->
												</div>
											</div>
	                                    </div>
	                                    <div class="sc-field sc-field-1-2">
	                                        <label>Paritaria</label>
	                                        <div class="sc-cont-check">
												<div class="sc-cont-check-cel">
													<input type="checkbox" id="checkNonStataleAvanzata"
														name="checkNonStatale" value="S" />
													<!-- <input
														type="radio" class="radioTipoScuolaAvanzata"
														id="radioTipoScuolaNonStataleAvanzata"
														name="radioTipoScuola"
														onclick="javascript:caricaTipologia('4','Avanzata')"
														style="display: none" value="NonStatale" /> -->
												</div>
											</div>
	                                    </div>									
	                                </div>
	                                <div class="sc-fields" id="tipologiaStataleNuovoOrdinamento_containerAvanzata">
	                                    <div class="sc-field">
	                                        <label>Scuola secondaria di II grado ${msgANNO_SCOLASTICO_INDIRIZZI_STUDIO}</label>
	                                        <div class="sc-select">
											<form:select 
												id="codiceTipologiaStataleNuovoOrdinamentoAvanzata"
												path="codiceTipologiaStataleNuovoOrdinamento" 
												title="Selezionare tipo scuola"
												onchange="javascript:caricaSettoreScuola(this.value,'Avanzata');"
											/>
	                                        </div>
	                                    </div>
	                                </div>
								<%-- <div class="sc-fields"
									id="tipologiaNonStatale_containerAvanzata">
									<div class="sc-field">
										<label>Scuola secondaria di II grado</label>
										<div class="sc-select">
											<form:select id="codiceTipologiaNonStataleAvanzata"
												path="codiceTipologiaNonStatale"
												title="Selezionare tipo scuola" />
										</div>
									</div>
								</div> --%>
	                                <div class="sc-fields" id="settoreScuola_containerAvanzata">
	                                    <div class="sc-field">
	                                        <label id="settore_percorsoAvanzata">Settore/Percorso</label>
	                                        <div class="sc-select">
											<form:select 
												id="codiceSettoreScuolaAvanzata"
												path="codiceSettoreScuola" 
												title="Selezionare settore scuola"
												onchange="javascript:caricaIndirizzoStudio('Avanzata');"
											/>
	                                        </div>
	                                    </div>
	                                </div>
									<div class="sc-fields" id="biennio_triennio_containerAvanzata">
	                                    <div class="sc-field sc-field-1-2">
	                                        <label>Biennio</label>
	                                        <div class="sc-cont-check">
	                                            <div class="sc-cont-check-cel">                                        
													<input type="radio" class="radioBiennioTriennioAvanzata" id="radioBiennioTriennioAvanzata" name="radioBiennioTriennio" value="Biennio" onclick="javascript:caricaIndirizzoStudio('Avanzata');" style="display: block" checked="checked" />
												</div>
											</div>
	                                    </div>
	                                    <div class="sc-field sc-field-1-2">
	                                        <label>Triennio</label>
	                                        <div class="sc-cont-check">
	                                            <div class="sc-cont-check-cel">                                        
													<input type="radio" class="radioBiennioTriennioAvanzata" id="radioBiennioTriennioAvanzata" name="radioBiennioTriennio" value="Triennio" onclick="javascript:caricaIndirizzoStudio('Avanzata');" style="display: block" />
												</div>
											</div>
	                                    </div>									
	                                </div>								
	                                <div class="sc-fields" id="indirizzoStudio_containerAvanzata">
	                                    <div class="sc-field">
	                                        <label id="indirizzo_studio_qualificaAvanzata">Indirizzo di studio</label>
	                                        <div class="sc-select">
											<form:select 
												id="codiceIndirizzoAvanzata"
												path="codiceIndirizzo"
												title="Selezionare indirizzo di studio"
											/>
	                                        </div>
	                                    </div>
	                                </div>								
	                                <div class="sc-fields" id="codiceCFP_containerAvanzata">
	                                    <div class="sc-field">
	                                        <label>Percorso</label>
	                                        <div class="sc-select">
											<form:select 
												id="codiceCFPPercorsoAvanzata" 
												path="codiceCFPPercorso" 
												title="Selezionare tipo centro formazione professionale"
												onchange="javascript:caricaCFP(this.value,'Avanzata');"
											/>
	                                        </div>
	                                    </div>
	                                </div>								
	                                <div class="sc-fields" id="settoreCFP_containerAvanzata">
	                                    <div class="sc-field">
	                                        <label>Settore</label>
	                                        <div class="sc-select">
											<form:select 
												id="codiceCFPSettoreAvanzata" 
												path="codiceCFPSettore"
												title="Selezionare settore"
												onchange="javascript:caricaCFPIndirizzoStudio('Avanzata');"
											/>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="sc-fields" id="indirizzoCFP_containerAvanzata">
	                                    <div class="sc-field">
	                                        <label>Indirizzo di studio</label>
	                                        <div class="sc-select">
											<form:select 
												id="codiceCFPIndirizzoAvanzata"
												path="codiceCFPIndirizzo" 
												title="Selezionare indirizzo di studio"
											/>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="sc-fields" id="tempiScuolaScuolaPrimaria_containerAvanzata">
	                                    <div class="sc-field">
	                                        <label>Tempi Scuola primaria</label>
	                                        <div class="sc-select">
											<form:select 
												id="codiceTempoPrimariaAvanzata"
												path="codiceTempoPrimaria" 
												title="Selezionare Tempi Scuola"
											/>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="sc-fields" id="tempiScuolaScuolaSecondaria1Grado_containerAvanzata">
	                                    <div class="sc-field">
	                                        <label>Tempi Scuola secondaria di I grado</label>
	                                        <div class="sc-select">
											<form:select 
												id="codiceTempoSecondaria1GradoAvanzata"
												path="codiceTempoSecondaria1Grado"
												title="Selezionare Tempi Scuola"
											/>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="sc-fields" id="indirizzoMusicale_containerAvanzata">
	                                    <div class="sc-field">
	                                        <label>Indirizzo musicale</label>
	                                        <div class="sc-cont-check">
	                                            <div class="sc-cont-check-cel">                                        
	                                        		<input type="checkbox" id="checkIndirizzoMusicaleAvanzata" name="checkIndirizzoMusicale" value="S" />
	                                        	</div>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="sc-fields">
	                                    <div class="sc-field">
	                                        <label>Denominazione</label>
											<input class="campo_testo" type="text" name="denominazione" id="denominazioneAvanzata" />
	                                    </div>
	                                </div>
	                                <div class="sc-fields">
	                                    <div class="sc-field">
	                                        <label>Codice meccanografico</label>
	                                        <input class="campo_testo" type="text" name="codMecc" size="30" maxlength="10" id="codMeccAvanzata" />
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="sc-submit">
									<input type="hidden" name="tipoRicerca" value="AVANZATA" />
									<input type="hidden" name="gidf" value="1" />
	                                <button id="btn_cerca_avanzata" class="sc-button" type="button">Cerca</button>
	                            </div>
	                        </fieldset>
	                    </form:form>
	                </article>
	
	            </section>
	            <script type="text/javascript">initialize();inizializeMapRicercaPosizione('sc-map-canvas-2');</script>
	    	</div>

        </div>

        <div class="sc-bg"></div>

    </section>
    

</body>


</html>



