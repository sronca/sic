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
            <h2>Tempi scuola per l'a.s. <%=Utility.annoScolasticoCorrente() %></h2>
            <div class="sc-cols">
              <div class="sc-col-1-2 sc-double-col">
                <table class="sc-table sc-table-standard">
                  <thead>
                    <tr>
                      <th class="sc-table-th-sup" style="text-align: left;">1° anno di corso</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${listaTmp1}" var="riga">
	                    <tr>
	                      <td data-col-1="1° anno di corso" class="sc-col-1">${riga.desSer}</td>
	                    </tr>                       
                    </c:forEach>                    
                  </tbody>
                </table>
              </div>
              <div class="sc-col-1-2 sc-double-col">
                <table class="sc-table sc-table-standard">
                  <thead>
                    <tr>
                      <th class="sc-table-th-sup" style="text-align: left;">2° anno di corso</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${listaTmp2}" var="riga">
	                    <tr>
	                      <td data-col-1="2° anno di corso" class="sc-col-1">${riga.desSer}</td>
	                    </tr>                       
                    </c:forEach>                    
                  </tbody>
                </table>
              </div>
              <div class="sc-col-1-2 sc-double-col">
                <table class="sc-table sc-table-standard">
                  <thead>
                    <tr>
                      <th class="sc-table-th-sup" style="text-align: left;">3° anno di corso</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${listaTmp3}" var="riga">
	                    <tr>
	                      <td data-col-1="3° anno di corso" class="sc-col-1">${riga.desSer}</td>
	                    </tr>                       
                    </c:forEach>                    
                  </tbody>
                </table>
              </div>
              <c:if test="${scuola.codTipSit ne 'MM'}">
	              <div class="sc-col-1-2 sc-double-col">
	                <table class="sc-table sc-table-standard">
	                  <thead>
	                    <tr>
	                      <th class="sc-table-th-sup" style="text-align: left;">4° anno di corso</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                    <c:forEach items="${listaTmp4}" var="riga">
		                    <tr>
		                      <td data-col-1="4° anno di corso" class="sc-col-1">${riga.desSer}</td>
		                    </tr>                       
	                    </c:forEach>                    
	                  </tbody>
	                </table>
	              </div>
	              <c:if test="${scuola.codTipSit eq 'EE'}">
		              <div class="sc-col-1-2 sc-double-col">
		                <table class="sc-table sc-table-standard">
		                  <thead>
		                    <tr>
		                      <th class="sc-table-th-sup" style="text-align: left;">5° anno di corso</th>
		                    </tr>
		                  </thead>
		                  <tbody>
		                    <c:forEach items="${listaTmp5}" var="riga">
			                    <tr>
			                      <td data-col-1="5° anno di corso" class="sc-col-1">${riga.desSer}</td>
			                    </tr>                       
		                    </c:forEach>                    
		                  </tbody>
		                </table>
		              </div>
	              </c:if>
              </c:if>
            </div>
            
            
            <c:if test="${scuola.codTipSit ne 'EE'}">
	            <h2 class="sc-center">Indirizzi di studio per l'a.s. <%=Utility.annoScolasticoCorrente() %></h2>
	            <div class="sc-cols">
	              <div class="sc-col-1-2 sc-double-col">
	                <table class="sc-table sc-table-standard">
	                  <thead>
	                    <tr>
	                      <th class="sc-table-th-sup" style="text-align: left;">1° anno di corso</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                    <c:forEach items="${lista1}" var="riga">
		                    <tr>
		                      <td data-col-1="1° anno di corso" class="sc-col-1">${riga.desSer}</td>
		                    </tr>                       
	                    </c:forEach>                    
	                  </tbody>
	                </table>
	              </div>
	              <div class="sc-col-1-2 sc-double-col">
	                <table class="sc-table sc-table-standard">
	                  <thead>
	                    <tr>
	                      <th class="sc-table-th-sup" style="text-align: left;">2° anno di corso</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                    <c:forEach items="${lista2}" var="riga">
		                    <tr>
		                      <td data-col-1="2° anno di corso" class="sc-col-1">${riga.desSer}</td>
		                    </tr>                       
	                    </c:forEach>                    
	                  </tbody>
	                </table>
	              </div>
	              <div class="sc-col-1-2 sc-double-col">
	                <table class="sc-table sc-table-standard">
	                  <thead>
	                    <tr>
	                      <th class="sc-table-th-sup" style="text-align: left;">3° anno di corso</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                    <c:forEach items="${lista3}" var="riga">
		                    <tr>
		                      <td data-col-1="3° anno di corso" class="sc-col-1">${riga.desSer}</td>
		                    </tr>                       
	                    </c:forEach>                    
	                  </tbody>
	                </table>
	              </div>
	              <c:if test="${scuola.codTipSit ne 'MM'}">
		              <div class="sc-col-1-2 sc-double-col">
		                <table class="sc-table sc-table-standard">
		                  <thead>
		                    <tr>
		                      <th class="sc-table-th-sup" style="text-align: left;">4° anno di corso</th>
		                    </tr>
		                  </thead>
		                  <tbody>
		                    <c:forEach items="${lista4}" var="riga">
			                    <tr>
			                      <td data-col-1="4° anno di corso" class="sc-col-1">${riga.desSer}</td>
			                    </tr>                       
		                    </c:forEach>                    
		                  </tbody>
		                </table>
		              </div>
	              </c:if>
	            </div>
            </c:if>

            
          </article>
        </div>
      </div>
    </div>
  </div>
</section>
<%@ include file="../includes/footerDettaglioScuola.jsp" %>
</body>
</html>