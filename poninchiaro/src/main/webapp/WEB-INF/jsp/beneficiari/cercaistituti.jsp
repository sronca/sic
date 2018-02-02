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
	        <a href="<c:url value="/"/>">Home</a> > Beneficiari > Istituti
	    </div>
	    
    	<div class="content">
            <h1>Cerca Istituto</h1>

	        <div class="row">
	            <div class="intro row-max">
	                <span>
	                	Vuoi conoscere quali sono i beneficiari degli avvisi PON per la programmazione 2014-2020? La ricerca &egrave; facile e intuitiva.
	                	<br/>
	                	<br/>
	                	Inserisci il nome dell'istituto se lo conosci altrimenti estendi la tua ricerca selezionando la regione. I campi con l'asterisco sono obbligatori.
	                	
	                </span>
	            </div>
				  <form:form action="/poninchiaro/beneficiari/ricerca/istituti"
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
								aria-label="regione"
								tabindex="20"
							/>
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="codiceProvincia" class="field-right">Provincia:</label>
	                    <div class="pull-right input-1-2">
							<form:select path="codiceProvincia"
								title="Filtro per provincia"
								aria-label="provincia"
								tabindex="30"
							 />
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="anno">Anno*:</label>
	                    <div class="input-1-2">
							<form:select path="anno"
								title="Filtro per anno"
								items="${anni}"
								aria-label="anno"
								tabindex="40"
							/>
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="tipoFondo" class="field-right">Tipo fondo*:</label>
	                    <div class="pull-right input-1-2">
							<form:select path="tipoFondo"
								title="Filtro per tipo fondo"
								items="${tipiFondo}"
								aria-label="tipo"
								tabindex="50"
							/>
	                    </div>
	                </div>
	                <div class="col-1-1 last-col-1">
	                  <input type="submit" class="search" value="CERCA" aria-label="invia" tabindex="60">
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



