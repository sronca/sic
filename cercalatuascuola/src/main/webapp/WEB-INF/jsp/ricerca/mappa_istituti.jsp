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
                <h1>Mappa</h1>
            </div>
        </div>

        <article class="sc-general-info sc-modal-map sc-zoom-anim-dialog">
            <div class="sc-wrapper">
                <h2>Mappa istituti selezionati</h2>
				
<%-- 				<c:forEach items="${scuoleDaConfrontare}" var="voScuola" varStatus="status">
		            <!-- dati per la mappa - inizio -->
		            <span class="dati_scuola" style="display: none;"
		            	   data-index="${status.index +1}"
		            	   data-lat="${voScuola.latitudine}"
		            	   data-lon="${voScuola.longitudine}"
		            	   data-nome="${voScuola.descrizione}"
		            	   data-tiposcuola="${voScuola.ordine} ${voScuola.tipologia}"
		            	   data-indirizzo="${voScuola.indirizzoCompleto}"
		            	   data-telefono="${voScuola.telefono}"
		            	   data-codicemeccanografico="${voScuola.codMecc}"
		            	   data-normalizedname="${voScuola.normalizedName}"
		            	   data-stataleNonStatale="${voScuola.stataleNonStatale}"
		            ></span>
				 </c:forEach>  --%>               
                <div class="sc-map-wrapper">
                    <div id="sc-map-edil"></div>
                </div>
            </div>
            
		<script>

			$(document).ready(function() {
				
				$("#sc-map-edil").drawMap({
					"markers": [
						<c:forEach items="${scuoleDaConfrontare}" var="voScuola" varStatus="status">
							<c:if test="${not empty voScuola.latitudine && status.index lt 500}">
								<c:if test="${not status.first}">,</c:if>
								{data: "<strong>${voScuola.descrizione}</strong>||${voScuola.ordine} ${voScuola.tipologia}||${voScuola.indirizzoCompleto}||Tel. ${voScuola.telefono}||<strong><a href='/cercalatuascuola/istituti/${voScuola.codMecc}/${voScuola.normalizedName}/'>Vai alla scheda</a></strong>", lat: "${voScuola.latitudine}", lng: "${voScuola.longitudine}", address:"${voScuola.indirizzoCompleto}", icon:"${voScuola.iconUrl}"}
							</c:if>
						</c:forEach>
					],
					"templateOpen" : "<div id=\"sc-map-marker\"><div class=\"sc-map-marker-content\">",
					"templateClose" : "</div></div>"
				});
			});

		</script>
		
		            
        </article>

    </section>
	<%@ include file="../includes/footer.jsp" %>
	
</body>

</html>