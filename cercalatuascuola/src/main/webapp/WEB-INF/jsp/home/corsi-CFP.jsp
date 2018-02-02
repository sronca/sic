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
              
              <h2>Corsi</h2>
              
              <c:if test="${not empty scuola.listaCorsi}">
	              <table class="sc-table sc-table-standard">
	                <thead>
	                  <tr>
	                    <th class="sc-table-th-sup"> Titolo corso </th>
	                    <th class="sc-table-th-sup"> Ciclo formativo </th>
	                    <th class="sc-table-th-sup"> Area </th>
	                    <th class="sc-table-th-sup"> Totale ore </th>
	                    <th class="sc-table-th-sup"> Ore stage </th>
	                  </tr>
	                </thead>
	                <tbody>
	                	<c:forEach items="${scuola.listaCorsi}" var="corso">
		                  <tr>
		                    <td data-col-1="Titolo Corso" class="sc-col-21 sc-center"> ${corso.cpTitoloCorso} </td>
		                    <td data-col-2="Ciclo Formativo" class="sc-col-2 sc-center"> ${corso.cpCicloFormativo} </td>
		                    <td data-col-3="Area" class="sc-col-3 sc-center"> ${corso.cpSettore} </td>
		                    <td data-col-4="Totale Ore" class="sc-col-4 sc-center"> ${corso.cpTotaleOre} </td>
		                    <td data-col-5="Ore Stage" class="sc-col-5 sc-center"> ${corso.cpTotaleOreStage} </td>
		                  </tr>
	                  	</c:forEach>
	                </tbody>
	              </table>
              </c:if>
              <c:if test="${empty scuola.listaCorsi}">
                <h3 class="sc-center">Non ci sono corsi da visualizzare per questo centro di formazione professionale</h3>
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