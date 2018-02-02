<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
	<head>
		<%@ include file="../includes/head.jsp" %>
	</head>
	<body>
	
	<%@ include file="../includes/toolbar_header.jsp" %>
	<section class="sc-internal-main">
	  <div class="sc-title">
	    <div class="sc-wrapper">
<!-- 	      <div class="sc-action-bar">
	    	<ul>
	        	<li class="sc-icon-back-bar"> <a href="javascript:history.back();" title="Indietro">Indietro</a></li>
	      	</ul>
	      </div> -->
	      <h1>Guida alla navigazione</h1>
	    </div>
	  </div>


  <article class="sc-internal-content">
    <div class="sc-wrapper">
      <div class="sc-cont-full">
        <p>Nella parte alta della pagina, hai a disposizione un menu generale a scomparsa <span class="sc-hamburger2"></span> da cui puoi:</p>
        <ul>
          <li> tornare alla home page;</li>
          <li> aprire la mappa del sito;</li>
          <li> consultare la guida di navigazione;</li>
          <li> visualizzare l'elenco delle scuole preferite;</li>
          <li> accedere alla pagina dei contatti dell'Ufficio Relazioni con il Pubblico (URP).</li>
        </ul>

        <p>Il breadcrumb, nella parte alta della pagina, indica dove ti trovi in ogni momento della navigazione.</p>


          <p>Per trovare facilmente le informazioni desiderate puoi utilizzare tre differenti modalit&agrave; di ricerca:</p>
          <ul>
            <li> "Ricerca per posizione geografica",</li>
            <li> "Ricerca rapida",</li>
            <li> "Ricerca avanzata".</li>
          </ul>
          <div class="sc-col-1-3">
            <p class="sc-center"><img src="<c:url value="/resources/img/ricerca-rapida.jpg" />"></p>
            <p>Con la "<strong>Ricerca rapida",</strong> inserendo le parole chiave che intendi cercare (come, ad esempio, il nome della scuola o il suo codice, il comune di interesse o il tipo di percorso di studio che vuoi conoscere), ottieni l'elenco delle scuole corrispondente alla somma delle parole "descrittive" che hai inserito.</p>
          </div>
          <div class="sc-col-1-3">
            <p class="sc-center"><img src="<c:url value="/resources/img/ricerca-posizione.jpg" />"></p>
            <p>Con la "<strong>Ricerca per posizione</strong> <strong>geografica</strong>", individui tutte le scuole presenti nell'area geografica che hai indicato o compilando i campi presenti nella scheda o cliccando sulla mappa geografica; puoi anche specificare il raggio entro cui effettuare la ricerca. Seleziona il tipo d'istruzione. Poi procedi con la ricerca.</p>
            <p>L'indicatore sulla mappa evidenzia il punto esatto in cui &egrave; collocata la scuola o le scuole che rispondono ai criteri che hai indicato.</p>
          </div>
          <div class="sc-col-1-3">
            <p class="sc-center"><img src="<c:url value="/resources/img/ricerca-avanzata.jpg" />"></p>
            <p>Per personalizzare i risultati di ricerca e trovare esattamente ci&ograve; che desideri utilizza la <strong>"Ricerca avanzata"</strong>. Questa modalit&agrave; ti permette di specificare la regione, la provincia, il comune, il tipo d'istruzione e altre caratteristiche che vuoi che siano presenti nella scuola che cerchi. Puoi anche inserire il nome della scuola o in alternativa il suo codice identificativo.</p>
          </div>

        <p style="clear:both;">La ricerca restituisce l'elenco delle scuole che rispondono ai criteri impostati (con la denominazione, l'indirizzo, il codice scuola e altri dati principali) e, sulla mappa, la posizione geografica delle scuole selezionate.</p>
 
        
        <p>I marker rappresentati in mappa con tre differenti colori indicano se le scuole che stai visualizzando sono <strong style="color:#129dc7"> Istituti Statali (blu)</strong>, <strong style="color:#86bc29">Paritari (verde)</strong> o se si tratta di <strong style="color:#a92f4a">Centri di Formazione Professionale (rosso)</strong>.</p>

        <p>Per conoscere la posizione esatta di una scuola sulla cartina geografica, fai clic su "MAPPA".</p>

        <p>Se vuoi confrontare pi&ugrave; scuole tra loro, seleziona le scuole dall'elenco (massimo 6) e clicca sul pulsante "CONFRONTA". Puoi anche effettuare una stampa dei dati visualizzati.</p>

        <p><strong>Scuola in Chiaro pu&ograve; essere consultata anche attraverso tablet e smartphone</strong>.</p>

        <p>&nbsp;</p>
        <div class="sc-center">
          <h2>Video tutorial</h2>
            <div class="sc-video"> 
		      <video id="my-video" class="video-js" controls preload="auto" poster="<c:url value="/resources/img/screen-video.jpg" />" data-setup="{}"  style="width:100%; height:auto;">
		        <source src="http://www.istruzione.it/scuolainchiaro_dati/tutorial.mp4" type='video/mp4'>
		        <source src="http://www.istruzione.it/scuolainchiaro_dati/tutorial.webm" type='video/webm'>
		        <p class="vjs-no-js"> To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a> </p>
		      </video>            		
            </div>
          
        </div>
        
      </div>
    </div>
  </article>
  	  
	</section>

	<%@ include file="../includes/footer.jsp" %>
    
	</body>
</html>


