<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
<head>
    <%@ include file="../includes/head_dettaglio.jsp" %>
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
        
          <%@ include file="../includes/menu_aside_didattica.jsp" %>
          
          <article class="sc-internal-content sc-table-cell">
            <div class="sc-cols">
              <h2>Avanzamento attivit&agrave; dei moduli in corso di realizzazione per tipologia</h2>
              <table class="sc-table sc-table-standard">
                <thead>
                  <tr>
                    <th>Tipologia intervento</th>
                    <th>Numero moduli totali</th>
                    <th>Perc. avanz. moduli</th>
                    <th>N° di moduli conclusi</th>
                    <th>N° destinatari coinvolti</th>
                  </tr>
                </thead>
                <tbody>
                	<c:forEach items="${lista}" var="riga">
	                  <tr>
	                    <td>${riga.tipInt}</td>
	                    <td class="sc-center">${riga.numMod}</td>
	                    <td class="sc-center">${riga.percAvvModFormat}</td>
	                    <td class="sc-center">${riga.numModConcl}</td>
	                    <td class="sc-center">${riga.numDestCoinv}</td>
	                  </tr>
                  	</c:forEach>
                </tbody>
              </table>
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