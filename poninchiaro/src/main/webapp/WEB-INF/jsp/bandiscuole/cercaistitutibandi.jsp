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
	        <a href="<c:url value="/"/>">Home</a> > Bandi scuole > Istituti
	    </div>
	    
    	<div class="content">
            <h1>Cerca Bando Scuola</h1>

	        <div class="row">
	            <div class="intro row-max">
	                <span>
	                	Vuoi conoscere quali sono le procedure di acquisizione di beni e servizio presenti nelle scuole nella tua regione?
	                	<br/>
	                	<br/>
	                	Basta selezionare la regione ed impostare il filtro dello Stato del Bando ad "In corso".I campi con l'asterisco sono obbligatori.
	                	
	                </span>
	            </div>
				  <form:form action="/poninchiaro/bandiscuole/ricerca/istituti"
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
	                    <label for="tipoBando">Tipo bando*:</label>
	                    <div class="input-1-2">
							<form:select path="tipoBando"
								title="Filtro per tipo bando"
								items="${tipiBando}"
								aria-label="tipo"
								tabindex="20"
							/>
	                    </div>
	                </div>	                
	                <div class="col-1-2">
	                    <label for="codiceRegione" class="field-right">Regione:</label>
	                    <div class="pull-right input-1-2">
							<form:select path="codiceRegione"
								title="Filtro per regione"
								onchange="javascript:caricaProvincia(this.value);"
								items="${regioni}"
								aria-label="regione"
								tabindex="30"
							/>
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="codiceProvincia">Provincia:</label>
	                    <div class="input-1-2">
							<form:select path="codiceProvincia"
								title="Filtro per provincia"
								aria-label="provincia"
								tabindex="40"
							 />
	                    </div>
	                </div>
	                <div class="col-1-2">
	                    <label for="stato" class="field-right">Stato*:</label>
	                    <div class="pull-right input-1-2">
							<form:select path="stato"
								title="Filtro per stato"
								items="${stati}"
								aria-label="stato"
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



