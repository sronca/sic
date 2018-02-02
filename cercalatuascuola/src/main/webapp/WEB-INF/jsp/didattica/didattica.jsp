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
          
            <h2>Documenti</h2>
            <div class="sc-cols">
	            <div class="sc-col-full">
	              <div class="">
	                <table class="sc-table sc-table-standard">
	                  <thead>
	                    <tr>
	                      <th></th>
	                      <th></th>
	                      <th></th>
	                    </tr>
	                    </thead>
	                    <tbody>
	                    <c:forEach items="${listaDocumentiDidattica}" var="documento">
	                    <tr>
	                      <td>${documento.desTipFil}</td>
	                      <td><a href="<c:url value="/render/document/${scuola.codScuUt}?prgDoc=${documento.prgDoc}&codTipFil=${documento.codTipFil}"/>">Visualizza</a></td>
	                      <td><a target="_new" href="<c:url value="/render/document/${scuola.codScuUt}?prgDoc=${documento.prgDoc}&codTipFil=${documento.codTipFil}&disp=attach"/>">Scarica</a></td>
	                    </tr>
	                    </c:forEach>
	                    <c:if test="${empty listaDocumentiDidattica}">
	                    	<tr>
	                    		<td colspan="3">Dati non disponibili per la scuola
	                    		</td>
	                    	</tr>
	                    </c:if>
	                  </tbody>
	                </table>
	              </div>
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