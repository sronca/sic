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
              <h2>Progettazione PON-FSE 2007/2015</h2>
              <c:if test="${not empty lista}">
	              <table class="sc-table sc-table-standard">
	                <thead>
	                  <tr>
	                    <th><span>Codice di progetto</span></th>
	                    <th><span>Progetti per i quali &egrave; stata fatta una selezione di esperti</span></th>
	                    <th><span>Breve descrizione del progetto</span></th>
	                    <th><span>Durata progetto (in ore)</span></th>
	                    <th><span>Link a contenuti prodotti</span></th>
	                  </tr>
	                </thead>
	                <tbody>
	                	<c:forEach items="${lista}" var="riga">
		                  	<tr>
			                    <td data-col-1="Codice di progetto" class="sc-col-1">${riga.codProgetto}</td>
			                    <td data-col-2="Progetti per i quali e' stata fatta una selezione di esperti" class="sc-col-2 sc-center">${riga.flgSelEsp}</td>
			                    <td data-col-3="Breve descrizione del progetto" class="sc-col-3">${riga.descProgFormat}</td>
			                    <td data-col-4="Durata progetto (in ore)" class="sc-col-4 sc-center">${riga.durataProg}</td>
			                    <td data-col-5="Link a contenuti prodotti" class="sc-col-5"><a href='http://${riga.link}' target='_blank'>${riga.link}</a></td>
		                  	</tr>
	                  	</c:forEach>
	                </tbody>
	              </table>
              </c:if>
              <c:if test="${empty lista}">
	              <div class="sc-col-full">
	              	<p>Dati non disponibili per la scuola</p>
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