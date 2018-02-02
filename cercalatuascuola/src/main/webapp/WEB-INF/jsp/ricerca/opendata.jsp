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
	      <h1>Dati aperti della scuola</h1>
	    </div>
	  </div>


          <article class="sc-internal-content">
          	<div class="sc-wrapper">
          		<div class="sc-full">
					<div class="sc-note-small">
<!-- 					<p class="sc-note-small">
						In questa sezione vengono presentati in formato txt dati provenienti dall'Anagrafe Scuole, Anagrafe Studenti,
						Esiti ed Esami di Stato così come pubblicati nelle tabelle e nei grafici presenti in Scuola in Chiaro.
						E' possibile salvare i dati cliccando sull'icona "Download" posta accanto al nome di ciascun file che compone l'archivio.
						Per ciascun file è disponibile il tracciato record con la descrizione dei campi.
						E' necessario precisare che i dati si riferiscono alle sole scuole attive nell'a.s.2015/2016.
						Ciò significa che nei file scaricabili riferiti ad anni scolastici precedenti non sono presenti i dati delle scuole non più attive nell'a.s. 2015/2016.
					</p> -->
					
                    <div class="sc-list-check sc-list-accordion">
                    	<ul id="accordion">
                     		<li>
	                       		<p class="sc-list-accordion-title"><strong><a href="javascript:void(0);">Perché rendere disponibili i dati del MIUR</a></strong></p>
	                       		<div class="sc-list-accordion-text">
	                       			<p class="sc-note-small">
									Il Ministero dell'Istruzione, Università e Ricerca é impegnato a valorizzare il proprio patrimonio informativo condividendo i dati 
									a propria disposizione con i cittadini per favorire la trasparenza amministrativa, la partecipazione al miglioramento del sistema 
									scolastico e la nascita di una nuova generazione di servizi per studenti, insegnanti e famiglie.
									La pubblicazione dei dati in questa sezione é conforme ai principi sul riuso anche a fini commerciali dell'Informazione del Settore 
									Pubblico (Decreto legislativo 24 gennaio 2006, n.36 come modificato dal decreto Legislativo 18 maggio 2015, n.102) ed alle 
									disposizioni del codice dell'Amministrazione digitale (Decreto legislativo 7 marzo 2005, n. 82 come modificato dal Decreto legislativo 
									30 dicembre 2010 n.235)
	                       			</p>
	                       		</div>
                     		</li>
                     		<li>
	                       		<p class="sc-list-accordion-title"><strong><a href="javascript:void(0);">Cosa rappresentano questi dati</a></strong></p>
	                       		<div class="sc-list-accordion-text">
		                       		<p class="sc-note-small">
									In questa sezione sono disponibili i dati provenienti dall'Anagrafe Scuole, Anagrafe Studenti, Esiti ed Esami di Stato così come pubblicati 
									nelle tabelle e nei grafici presenti in Scuola in Chiaro.
									Una selezione dei dati  raccolti e gestiti nell'ambito del Sistema Nazionale di Valutazione, attraverso cui si é giunti alla formulazione 
									dei Rapporti di Autovalutazione (RAV), unitamente ad una selezione dei dati del patrimonio informativo del MIUR sono ora a disposizione 
									della collettività, con l'obiettivo di garantirne la reperibilità e la fruibilità in un'ottica di completa trasparenza del processo. 
									I dati si riferiscono alle scuole attive nell'a.s.2015/2016: nella parte di tracciati riferiti ad anni scolastici precedenti non sono 
									presenti i dati delle scuole non più attive nell'a.s. 2015/2016.
		                       		</p>
	                       		</div>
                     		</li>
                     		<li>
	                       		<p class="sc-list-accordion-title"><strong><a href="javascript:void(0);">Come posso utilizzare i dati</a></strong></p>
	                       		<div class="sc-list-accordion-text">
		                       		<p class="sc-note-small">
									E' possibile salvare i dati cliccando sull'icona "Download" posta accanto al nome di ciascun file che compone l'archivio. 
									Per ciascun file é disponibile il tracciato record con la descrizione dei campi. 
									I dati scaricabili da questo sito possono essere utilizzati per ogni scopo, anche commerciale, al fine di comprendere meglio il mondo 
									della scuola e creare servizi innovativi. 
									L'aggiornamento é previsto a cadenza periodica e segue specifici eventi amministrativi del sistema d'istruzione.
		                       		</p>
	                       		</div>
                     		</li>                     		
                    	</ul>
                  	</div>
                  	</div>				
          	
          	
          	
                    <table class="sc-table sc-table-standard">
	                  <thead>
	                    <tr>
	                      <th class="sc-left">Archivio</th>
	                      <th>Download</th>
						  <th>Tracciato</th>
						  <th>Data ultimo aggiornamento</th>
	                    </tr>
	                   </thead>
	                   <tbody>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1"> Anagrafe Scuole Statali 2015/16</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/7-Anagrafe_Scuole_Statali_201516.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/7-Tracciato_Anagrafe_Scuole_Statali_201516.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center">05/11/2015</td>
			            </tr>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1"> Anagrafe Scuole Paritarie 2015/16</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/8-Anagrafe_Scuole_Paritarie_201516.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/8-Tracciato_Anagrafe_Scuole_Paritarie_201516.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center">05/11/2015</td>
			            </tr>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1"> Anagrafe Centri Formazione Professionale 2014/15</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/9-Anagrafe_Centri_Formazione_Professionale_201516.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/9-Tracciato_Anagrafe_Centri_Formazione_Professionale_201516.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center">05/11/2015</td>
			            </tr>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1">Anagrafe Nazionale - Alunni e classi</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/1-Anagrafe_Nazionale_ALUNNI_CLASSI.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/1-Tracciato_Anagrafe_Nazionale_ALUNNI_CLASSI.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center"> 05/11/2015</td>
			            </tr>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1"> Anagrafe Nazionale - Classi 1° anno per indirizzo/tempo scuola</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/2-Anagrafe_Nazionale_CLASSI_INDIRIZZO_1ANNO.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/2-Tracciato_Anagrafe_Nazionale_CLASSI_INDIRIZZO_1ANNO.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center">05/11/2015</td>
			            </tr>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1"> Abbandoni e Trasferimenti</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/10-Dati_Abbandoni_Entrati_Usciti.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/10-Tracciato_Abbandoni_Trasferimenti.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center">05/11/2015</td>
			            </tr>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1"> Esiti scrutini</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/3-Esiti.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/3-Tracciato_Esiti.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center">05/11/2015</td>
			            </tr>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1"> Esiti esami di Stato I e II grado</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/4-Risultati_esami_di_Stato_I_e_II_grado.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/4-Tracciato_Risultati_esami_di_Stato_I_e_II_grado.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center">05/11/2015</td>
			            </tr>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1"> Esiti esami di Stato I e II grado per voto</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/5-Risultati_esami_di_Stato_I_e_II_grado_voto.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/5-Tracciato_Risultati_esami_di_Stato_I_e_II_grado_voto.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center">05/11/2015</td>
			            </tr>
			            <tr>
			              <td data-col-1="Archivio" class="sc-col-1"> Personale scuola</td>
			              <td data-col-2="Download" class="sc-col-2 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/6-Personale_scuola.csv" target="#"><img src="<c:url value="/resources/css/images/download_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-3="Tracciato" class="sc-col-3 sc-tab-small-td sc-center"><a href="http://www.istruzione.it/scuolainchiaro_dati/6-Tracciato_Personale_scuola.xlsx" target="#"><img src="<c:url value="/resources/css/images/trace_ico.png" />" alt="" width="26" height="28"></a></td>
			              <td data-col-4="Data ultimo aggiornamento" class="sc-col-4 sc-tab-small-td sc-center">05/11/2015</td>
			            </tr>
	                  </tbody>
	                </table>
	                
	                <p class="sc-note">Nota: i file dell'archivio non contengono, rispetto alla versione precedente, i dati relativi ai corsi serali facenti parte dell'istruzione per adulti.</p>
	                
	                
	        	</div>
	        </div>

          </article>
  	  
	</section>

    <footer class="sc-footer">
        <div class="sc-wrapper">
            <p class="sc-info-footer">Ministero dell'Istruzione, dell'Università e della Ricerca - Viale Trastevere, 76/A - 00153 ROMA - <a style="text-decoration: underline;" href="<c:url value="/informativa_cookie/"/>">Cookie policy</a></p>
        </div>
    </footer>

	<script>
		(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		ga('create', 'UA-28411071-1', 'auto');
		ga('send', 'pageview');
	</script>    
    
	</body>
</html>


