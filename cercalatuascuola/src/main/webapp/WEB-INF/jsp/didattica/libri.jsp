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
            <h2>Libri di testo - anno scolastico ${annoScolastico}</h2>
            <div class="sc-cols">
            <div class="sc-col-full">
              <div class="">
              	<c:if test="${mostraLinkMenuLibriDiTesto eq 'true'}">
	                <table class="sc-table sc-table-standard">
	                  <thead>
	                    <tr>
	                      <th>Classe</th>
	                      <th>Sezione</th>
	                      <th>Indirizzo di studio</th>
	                      <th></th>
	                      <th></th>
	                    </tr>
	                    </thead>
	                    <tbody>
	                    <c:if test="${not empty listaLibri}">
		                    <c:forEach items="${listaLibri}" var="libro">
			                    <tr>
			                      <td class="sc-center">${libro.classe}</td>
			                      <td class="sc-center">${libro.sezione}</td>
			                      <td>${libro.indirizzoDiStudio}</td>
			                      <td><a href="<c:url value="/render/document/${scuola.codScuUt}/libro?prgDoc=${libro.id}"/>">Visualizza</a></td>
			                      <td><a href="<c:url value="/render/document/${scuola.codScuUt}/libro?prgDoc=${libro.id}&disp=attach"/>">Scarica</a></td>
			                    </tr>
		                    </c:forEach>
	                    </c:if>
						<c:if test="${empty listaLibri}">
		                    <tr>
		                      <td colspan="5" class="sc-center">Dati non disponibili per la scuola</td>
		                    </tr>
	                    </c:if>	
	                  </tbody>
	                </table>
                </c:if>
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