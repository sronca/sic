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
        
          <%@ include file="../includes/menu_aside_chisiamo.jsp" %>
          
          <article class="sc-internal-content sc-table-cell">
            <div class="sc-cols">
              <h2>Sintesi dei progetti realizzati per programma per anno di programmazione</h2>
              <h3 class="sc-center">Fondo Sociale Europeo</h3>
              <table class="sc-table sc-table-standard">
                <thead>
                  <tr>
                    <th class="sc-table-th-sup"> </th>
                    <th colspan="2" class="sc-table-th-sup">PON</th>
                    <th colspan="2" class="sc-table-th-sup">POR</th>
                  </tr>
                  <tr>
                    <th>Anno</th>
                    <th>Numero interventi</th>
                    <th>Importo finanziato (€)</th>
                    <th>Numero interventi</th>
                    <th>Importo finanziato (€)</th>
                  </tr>
                </thead>
                <tbody>
                	<c:if test="${not empty ponList}">
	                	<c:forEach items="${ponList}" var="riga">
		                  <tr>
		                    <td data-col-1="Anno" class="sc-col-21 sc-center"> ${riga.anno}</td>
		                    <td data-col-2="PON: Numero Interventi" class="sc-col-2 sc-center"> ${riga.numProgettiFsePon}</td>
		                    <td data-col-3="PON: Importo Finanziato (€)" class="sc-col-3 sc-center"> ${riga.impFinFsePonFormat}</td>
		                    <td data-col-4="POR: Numero Interventi" class="sc-col-4 sc-center"> ${riga.numProgettiFsePor}</td>
		                    <td data-col-5="POR: Importo Finanziato (€)" class="sc-col-5 sc-center"> ${riga.impFinFsePorFormat}</td>
		                  </tr>
	                  	</c:forEach>
                  	</c:if>
                    <c:if test="${empty ponList}">
	                     <tr>
	                         <td colspan="5" class="sc-col-1 sc-center">Dati non disponibili per la scuola</td>
	                     </tr>
                    </c:if>                    	
                </tbody>
              </table>
              <h3 class="sc-center">Fondo Europeo Sviluppo Regionale</h3>
              <table class="sc-table sc-table-standard">
                <thead>
                  <tr>
                    <th class="sc-table-th-sup"> </th>
                    <th colspan="2" class="sc-table-th-sup">PON</th>
                    <th colspan="2" class="sc-table-th-sup">POR</th>
                  </tr>
                  <tr>
                    <th>Anno</th>
                    <th>Numero interventi</th>
                    <th>Importo finanziato (€)</th>
                    <th>Numero interventi</th>
                    <th>Importo finanziato (€)</th>
                  </tr>
                </thead>
                <tbody>
                	<c:if test="${not empty ponList}">
	                	<c:forEach items="${ponList}" var="riga">
		                  <tr>
		                    <td data-col-1="Anno" class="sc-col-21 sc-center"> ${riga.anno}</td>
		                    <td data-col-2="PON: Numero Interventi" class="sc-col-2 sc-center"> ${riga.numProgettiFesrPon}</td>
		                    <td data-col-3="PON: Importo Finanziato (€)" class="sc-col-3 sc-center"> ${riga.impFinFesrPonFormat}</td>
		                    <td data-col-4="POR: Numero Interventi" class="sc-col-4 sc-center"> ${riga.numProgettiFesrPor}</td>
		                    <td data-col-5="POR: Importo Finanziato (€)" class="sc-col-5 sc-center"> ${riga.impFinFesrPorFormat}</td>
		                  </tr>
	                  	</c:forEach>
                  	</c:if>
                    <c:if test="${empty ponList}">
	                     <tr>
	                         <td colspan="5" class="sc-col-1 sc-center">Dati non disponibili per la scuola</td>
	                     </tr>
                    </c:if>                    	
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