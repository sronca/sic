<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
<head>
    <%@ include file="../includes/head_dettaglio.jsp" %>
    <style>
    
    	.titoloSezione
    	{
    		font-size: 16px !important;
		    text-transform: uppercase;
		    line-height: 19px;
		    font-family: open-sans, sans-serif;
		    font-weight: 700;
    	}
    	
    	.sottoTitoloSezione
    	{
    	    font-size: 14px !important;
		    font-family: open-sans, sans-serif;
		    font-weight: 700;
    	}
    	
    </style>
    <script>
	
		function vaiA(anchor)
		{
			$('html, body').animate(
			{
				scrollTop: $("#"+anchor).offset().top-100
		    }, 1000);
		}

    </script>
</head>
<body>


<%@ include file="../includes/toolbar_header.jsp" %>

<section class="sc-internal-main sc-detail">

  <%@ include file="../includes/breadcrumbandtitle.jsp" %>
  <%@ include file="../includes/menu.jsp" %>

  <div class="sc-main-content-detail">
    <div class="sc-wrapper sc-wrapper-alt">
      <div  class="sc-table-table sc-external-box">
        <div class="sc-table-row sc-external-box">
        
          <%@ include file="../includes/menu_aside_chisiamo.jsp" %>
          
          <article class="sc-internal-content sc-table-cell">
			<c:choose>
				<c:when test="${not empty subSubFunctionalityOn}">
					<c:forEach items="${scuola.caratteristicaDiplomato}" var="voceMenu" varStatus="status">
						<c:if test="${voceMenu.area eq subFunctionalityOn}">
							<c:forEach items="${voceMenu.sottomenu}" var="voceSottoMenu" varStatus="statu">
								<c:if test="${voceSottoMenu.codice eq subSubFunctionalityOn}">
									<h2>${voceSottoMenu.descrizione } per indirizzo d'esame:</h2>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${scuola.caratteristicaDiplomato}" var="voceMenu" varStatus="status">
						<c:if test="${voceMenu.area eq subFunctionalityOn}">
							<h2>${voceMenu.tipologia.descrizione} per indirizzo d'esame:</h2>
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
           	<c:if test="${empty listaVOIndirizzoEsame}">
           		<h2>Nulla da visualizzare.</h2>
           	</c:if>
            <div class="sc-cols">
                <ol>
	                <c:forEach items="${listaVOIndirizzoEsame}" var="indirizzoEsame" varStatus="status">
	                	<li>
	                		<a href="javascript:void(0)" onclick="javascritp:vaiA('${indirizzoEsame.tipologia.codice}')" style=" font-weight: bold; font-style: normal;">${indirizzoEsame.tipologia.descrizione} </a>
	                	</li>
	                </c:forEach>
                </ol>
                
                <c:forEach items="${listaVOIndirizzoEsame}" var="indirizzoEsame" varStatus="status">
                
					<div class="sc-col-full sc-double-col">
					
						<div class="sc-table-table">
						
		                	<div class="sc-table-row sc-table-bg" id="${indirizzoEsame.tipologia.codice}">
								
				                	<p class="titoloSezione">
				                		${indirizzoEsame.tipologia.descrizione}
				                	</p>
				                	
				                	<c:if test="${empty indirizzoEsame.listaCaratteristica}">
				                		Nessuna caratteristica da visualizzare.
				                	</c:if>
				                	
				                	<c:forEach items="${indirizzoEsame.listaCaratteristica}" var="caratteristica" varStatus="statu">
				                	
					                	<p class="sottoTitoloSezione">
					                		${caratteristica.sottotitolo}
					                	</p>
				              
						                <ul class="caratteristiche">
							                <c:forEach items="${caratteristica.listaCaratteristica}" var="descrizioneCaratteristica" varStatus="stat">
							                	<li>
							                		${descrizioneCaratteristica}
							                	</li>
							                </c:forEach>
						                </ul>
						                
				                	</c:forEach>
				                	
							</div>
							
						</div>
						
	                </div>
	                
                </c:forEach>
                


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