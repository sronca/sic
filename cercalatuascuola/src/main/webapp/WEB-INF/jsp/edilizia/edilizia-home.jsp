<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
<head>
    <%@ include file="../includes/head_dettaglio.jsp" %>
    

		<script>
		
			callback = function(){
				
				if ($(window).width() < 768) {

		            $('#sc-map-edil').parent().css("min-width","100%");
		            $('#sc-map-edil').parent().css("max-width","100%");

		        } else {

		            $('#sc-map-edil').parent().css("position","absolute");
		            $('#sc-map-edil').parent().css("min-width","74%");
		            $('#sc-map-edil').parent().css("max-width","74%");
		        }

				
				$.fn.drawMap().callbackResize('#sc-map-edil');
			};

			$(document).ready(function() {
				
				$("#sc-map-edil").drawMap({
					//"iconCommonUrl" : "<c:url value='/resources/img/pin/' />",
					"markers": [
						<c:forEach items="${listEdifici}" var="edificio" varStatus="status">
						 	<c:if test="${not status.first}">,</c:if>
							{data: "<strong>Edificio ${status.index +1}</strong>||${edificio.indirizzoMarker}||Plessi ospitati: ${edificio.numeroPlessi}", lat: "${edificio.latitudine}", lng: "${edificio.longitudine}", address:"${edificio.indirizzoMarker}", icon:"<c:url value='/resources/img/pin/' />marker_${status.index +1}.png"}
						</c:forEach>
					]
				});
				
				$("#edil-msg").msgtoggle({
					//"autoClose": 5000
					"endCallback": callback
				});
			});


		</script>
		    
</head>
<body class="sc-edilizia sc-edilizia-home">


<%@ include file="../includes/toolbar_header.jsp" %>

<section class="sc-internal-main sc-detail">

  <%@ include file="../includes/breadcrumbandtitle.jsp" %>
  <%@ include file="../includes/menu.jsp" %>

  <div class="sc-main-content-detail">
    <div class="sc-wrapper sc-wrapper-alt">
      <div  class="sc-table-table sc-external-box">

        <c:if test="${t ne 'S'}">
	        <div class="sc-table-row sc-external-box" id="edil-msg">
	          <article class="sc-internal-content sc-table-cell">
	            <div class="sc-cols">
		            <div class="sc-col-full sc-col-border">
		              <h2>Avviso</h2>
		              <p class="sc-justify"><strong>ATTENZIONE: I dati contenuti nella presente sezione contengono tutte le informazioni di carattere tecnico relative agli edifici scolastici attivi censiti così come comunicati dagli enti locali proprietari degli stessi per il tramite dei nodi regionali dell'Anagrafe.</strong></p>
		              <p class="sc-justify"><strong> Si precisa che, a seguito di accordo in conferenza unificata di intesa con Comuni e Province, è stato stabilito di aggiornare al 31 gennaio 2016 la pubblicazione dei dati relativi alle certificazioni degli edifici al fine di consentire l'adeguamento delle informazioni contenute nella sezione agli interventi recentemente autorizzati dal Ministero dell'istruzione, dell'università e della ricerca.</strong></p>
		              <p class="sc-justify"><strong> Sarà comunque a breve disponibile nella pagina web denominata Anagrafe del Piano di edilizia scolastica l'informazione aggregata a livello provinciale delle certificazioni degli edifici scolastici </strong></p>
		              <p><strong>NOTA: I dati sono riferiti all'anno scolastico ${annoScolastico}</strong></p>
		            </div>
		            <form>
			            <div class="sc-fields sc-center">
			            	<p><input id="edil-close" name="edil-close" type="button" class="sc-button mt_closelink" value="Procedi"></p>
							<p>
								<label for="edil-discard">Non mostrare più questo messaggio</label>
								<input type="checkbox" value="discard" name="edil-discard" id="edil-discard">
							</p>
						</div>
		        	</form>
	            </div>
	          </article>
	        </div>
        </c:if>
        
        <div class="sc-table-row sc-external-box" id="edil-msgNote" style="display: none;">
          <article class="sc-internal-content sc-table-cell">
            <div class="sc-cols">
	            <div class="sc-col-full sc-col-border">
	              <h2>Avviso</h2>
	              <p class="sc-justify"><strong>ATTENZIONE: I dati contenuti nella presente sezione contengono tutte le informazioni di carattere tecnico relative agli edifici scolastici attivi censiti così come comunicati dagli enti locali proprietari degli stessi per il tramite dei nodi regionali dell'Anagrafe.</strong></p>
	              <p class="sc-justify"><strong> Si precisa che, a seguito di accordo in conferenza unificata di intesa con Comuni e Province, è stato stabilito di aggiornare al 31 gennaio 2016 la pubblicazione dei dati relativi alle certificazioni degli edifici al fine di consentire l'adeguamento delle informazioni contenute nella sezione agli interventi recentemente autorizzati dal Ministero dell'istruzione, dell'università e della ricerca.</strong></p>
	              <p class="sc-justify"><strong> Sarà comunque a breve disponibile nella pagina web denominata Anagrafe del Piano di edilizia scolastica l'informazione aggregata a livello provinciale delle certificazioni degli edifici scolastici </strong></p>
	              <p><strong>NOTA: I dati sono riferiti all'anno scolastico ${annoScolastico}</strong></p>
	            </div>
	            <form>
		            <div class="sc-fields sc-center">
		            	<p><input id="edil-closeNote" name="edil-closeNote" type="button" class="sc-button" value="Chiudi"></p>
					</div>
	        	</form>
            </div>
          </article>
        </div>        
        
        <c:if test="${t eq 'S'}">
        	<div class="sc-table-row sc-external-box" id="box_intero_edilizia">
        </c:if>
        <c:if test="${t ne 'S'}">
        	<div class="sc-table-row sc-external-box elemhide" id="box_intero_edilizia">
        </c:if>
          <aside class="sc-table-cell">
          	<span class="sc-main-menu-section sc-edilizia-menu-section">Edilizia</span>
            <nav class="sc-list-side-menu">
              <c:if test="${not empty listEdifici}">             
              <p style="font-size:1.2rem; padding:10px;">L'istituto si compone dei seguenti edifici:</p>
              <ul id="accordion">
              	<c:forEach items="${listEdifici}" var="edificio" varStatus="status">
	                <li>
	                  <table class="sc-table sc-school-home-data sc-address">
	                    <tbody>
	                      <tr>
	                        <td class="sc-tab-mappa">
	                        	<span><a href="javascript:void(0);" class="sc-map-stat sc-map-${status.index +1}">Posizione sulla Mappa</a></span>
	                        </td>
	                        <td>
	                        	<span><strong>${edificio.indirizzo}</strong></span>
	                        </td>
	                      </tr>
	                    </tbody>
	                  </table>
	                  <c:if test="${status.first}">
	                  	<div class="sc-ediliziasubmenu sc-ediliziasubmenu-first">
	                  </c:if>
	                  <c:if test="${not status.first}">
	                  	<div class="sc-ediliziasubmenu">
	                  </c:if>	                  
	                    <ul>
	                    	<c:forEach items="${edificio.scuole}" var="scuola">
	                      		<li> ${scuola.desNomScu} - ${scuola.codTipSit} (${scuola.codScuUt}) </li>
	                      	</c:forEach>
	                    </ul>
	                    <p class="sc-ediliziadettaglio">
	                    	<a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/edilizia/dettaglio?edificio=${edificio.codiceEdificio}&n=${status.index +1}&a=${annoScolastico}"/>">Dati di dettaglio</a>
	                    </p>
	                    
	                    </div>
	                </li>
                </c:forEach>
              </ul>
              </c:if>
              <c:if test="${empty listEdifici}">             
              	<p style="font-size:1.2rem; padding:10px;">Non sono disponibili dati sugli edifici per questa scuola</p>
			  </c:if>
              <ul>
              	<li><a href="javascript:void(0);" id="linkNote" >Note</a></li>
              </ul>			                         
            </nav>
            <div class="sc-share">
              <h2>Condividi</h2>
			  <div id="share"></div>
            </div>
          </aside>
          
          
          <article class="sc-internal-content sc-table-cell">
            <h2 class="sc-center"> ${scuola.desNomScu} (${scuola.codScuUt}) </h2>
            <div class="sc-cols">
            <div class="sc-col-full">
              <c:if test="${not empty listEdifici}">
              	<div id="sc-map-edil" style="position: relative; background-color: rgb(229, 227, 223); overflow: hidden;" data-tipo="EDIL"></div>
              </c:if>              
              <div class="sc-center"><img src="<c:url value="/resources/img/logo_edilizia_big.jpg" />" width="179" height="177"></div>
            </div>
            </div>
          </article>
        </div>
        
        
      </div>
    </div>
  </div>
</section>
<%@ include file="../includes/footerDettaglioScuola.jsp" %>
</body>
</html>