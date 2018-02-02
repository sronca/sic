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
            <h2>Bacheca</h2>
            <div class="sc-cols">
            <div class="sc-col-full">
              
              <c:if test="${not empty listaNews}">
              	<c:forEach items="${listaNews}" var="news">
					<div class="sc-bacheca-item">
					    <p class="cs-data"><span>${news.datBacShort}</span></p>
					    <h3><strong>${news.desTitBac}</strong></h3>
					    <p>${news.desOggBac}</p>
					</div>
              	</c:forEach>
              </c:if>
              
              <c:if test="${empty listaNews}">
              	
	              <div class="sc-bacheca-item">
	                  <p class="cs-data"><span></span></p>
	                  <h3><strong>Nessun avviso presente.</strong></h3>
	              </div>              	
              </c:if>              

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