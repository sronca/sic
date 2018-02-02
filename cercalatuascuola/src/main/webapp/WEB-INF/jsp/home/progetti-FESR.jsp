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
            <h2>Sintesi dei progetti realizzati su infrastruttura</h2>
            <div class="sc-cols">
              <table class="sc-table sc-table-standard">
                <thead>
                  <tr>
                    <th>Tipologia di interventi</th>
                    <th>N°</th>
                    <th>Importo totale autorizzato (€)</th>
                  </tr>
                </thead>
                <tbody>
                	<c:if test="${not empty ponList}">
	                  <c:forEach items="${ponList}" var="riga">
		                  <tr>
		                  	<td data-col-1="Tipologia di interventi" class="sc-col-1">
		                      	<c:if test="${riga.tipInt eq 'ASSE1'}">
		                                Societ&agrave; dell'informazione e della conoscenza
		                        </c:if>
		                        <c:if test="${riga.tipInt eq 'ASSE2'}">
		                                Qualit&agrave; degli ambienti scolastici
		                        </c:if>
		                        <c:if test="${riga.tipInt eq 'ASSE3'}">
		                                Assistenza tecnica
		                        </c:if>
	                        </td>	                  
		                    <td data-col-2="N°" class="sc-col-2 sc-center"> ${riga.numProg} </td>
		                    <td data-col-3="Importo totale autorizzato (€)" class="sc-col-3 sc-center"> ${riga.impTotAutFormat} </td>
		                  </tr>
	                  </c:forEach>
                  	</c:if>
                    <c:if test="${empty ponList}">
	                     <tr>
	                         <td colspan="3" class="sc-col-1 sc-center">Dati non disponibili per la scuola</td>
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