<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp"%>
<%@ include file="../includes/html.jsp"%>

<head>
<%@ include file="../includes/head.jsp"%>

<script src="<c:url value="/resources/js/jquery/jquery.autocomplete.min.js"/>"></script>

<script>

	$(document).ready(function() {
		
		// reset del campo al caricamento della pagina
		$.fn.autoCompleteAddon({
			elem: $("#codicemeccanografico")
		}, "reset");	

		// al keydown rimuovo eventuali campi hidden
		$("#codicemeccanografico").keydown(function(e) {
			$.fn.autoCompleteAddon({
				elem: $(this),
				keypressed: e.keyCode
			}, "remove");
		}).autocomplete({
			serviceUrl: "/poninchiaro/getIstitutiList.json",
			appendTo: "#autocomplete-container",
			minChars: 3,
			zIndex: 99,
			deferRequestBy: 500,
			noCache: true,
			onSelect: function (data) {
				// chiamo la funzione addon per registrare il valore dentro un campo hidden
				$.fn.autoCompleteAddon({
					elem: $(this),
					value: data.data
				});
			}
		});
	});

</script>

</head>


<body>

	<div id="container" class="shadow-fix">
		
		<%@ include file="../includes/menu.jsp"%>
		
	    <div id="breadcrumb">
	        <a href="<c:url value="/"/>">Home</a> > Le scuole del PON
	    </div>
	    
    	<div class="content">
            <h1>Cerca Istituto</h1>

	        <div class="row">
	            <div class="intro row-max">
	                <span>
	                	Vuoi conoscere nel dettaglio gli interventi che ha avviato la scuola vicino casa con i finanziamenti PON?
	                	<br/>
	                	<br/>
	                	Inserisci il nome dell'istituto, se lo conosci,  o seleziona gli altri campi (regione, provincia e comune) e scegli anche la categoria di intervento.
	                	Hai la possibilit&agrave; di ricercare sia le scuole che hanno avviato progetti finanziati dai fondi FSE e FESR sia quelle che finora
	                	non si sono affacciate al mondo PON.
						<br/>
						Per ciascuna delle scuole beneficiarie avrai una scheda progetto molto esplicativa con foto e documenti a corredo.
	                </span>
	            </div>
				  <form:form action="/poninchiaro/scuoledelpon/ricerca/istituti"
							 method="get" 
							 commandName="filtroRicerca" 
				  >
						
				  <div class="gray-block style-col-2">
	                <div class="col-1-1">
	                    <label for="scuola" class="pull-left">Cerca:</label>
	                    <div id="autocomplete-container" class="input-1-1 pull-right">
	                    	<input 
	                    		placeholder="Nome Istituto o codice meccanografico" 
	                    		class="before-text" 
	                    		type="text" 
	                    		id="codicemeccanografico" 
	                    		name="scuola" 
	                    		aria-label="Nome scuola o codice meccanografico"
	                    		tabindex="10"
	                    	/>
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="codiceRegione">Regione:</label>
	                    <div class="input-1-2">
							<form:select path="codiceRegione"
								title="Filtro per regione"
								onchange="javascript:caricaProvincia(this.value);"
								items="${regioni}"
								aria-label="Regione"
								tabindex="20"
							/>
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="codiceProvincia" class="field-right">Provincia:</label>
	                    <div class="pull-right input-1-2">
							<form:select path="codiceProvincia"
								title="Filtro per provincia"
								onchange="javascript:caricaComune(this.value);"
								aria-label="Provincia"
								tabindex="30"
							 />
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="codiceComune">Comune:</label>
	                    <div class="input-1-2">
							<form:select path="codiceComune"
								title="Filtro per comune"
								aria-label="Comune"
								tabindex="40"
							 />
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="codiceTipoIstruzione" class="field-right">Tipo Istruzione:</label>
	                    <div class="pull-right input-1-2">
							<form:select path="codiceTipoIstruzione"
								title="Filtro per Tipo Istruzione"
								aria-label="Tipo Istruzione"
								items="${tipiIstruzione}"
								tabindex="50"
							 />
	                    </div>
	                </div>	                	                
	                <div class="col-1-2">
	                    <label for="tipoFondo">Categoria di Intervento:</label>
	                    <div class="input-1-2">
							<form:select path="tipoFondo"
								title="Filtro per categoria di intervento"
								onchange="javascript:caricaSottoCategoria(this.value);"
								items="${tipiFondo}"
								aria-label="Categoria di Intervento"
								tabindex="60"
							/>
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="codiceSottoCategoria" class="field-right">Sotto Categoria:</label>
	                    <div class="pull-right input-1-2">
							<form:select path="codiceSottoCategoria"
								title="Filtro per Sotto Categoria"
								aria-label="Sotto Categoria"
								tabindex="70"
							/>
	                    </div>
	                </div>
	                <div class="col-1-1 last-col-1">
	                  <input type="submit" class="search" value="CERCA" aria-label="invia" tabindex="80">
	                </div>
	             </div>
			    </form:form>
	        </div>
        

        </div>
	
		
		
		<%@ include file="../includes/footer.jsp"%>
		
	</div>

<%@ include file="../includes/scriptanalytics.jsp"%>
</body>


</html>



