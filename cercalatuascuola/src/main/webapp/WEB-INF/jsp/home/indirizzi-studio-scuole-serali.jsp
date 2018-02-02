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
            <h2>Indirizzi attivi nell'a.s. corrente (<%=Utility.annoScolasticoCorrente() %>)</h2>
            <div class="sc-cols">
                <div class="sc-col-full">
                    <div class="">
                        <table class="sc-table sc-table-standard">
                            <thead>
                              <tr>
                                <th style="width:20%">Periodo scolastico</th>
                                <th>Indirizzi di studio</th>
                              </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td data-col-1="Periodo scolastico" class="sc-col-1 sc-center">I Periodo</td>
                                    <td data-col-2="Indirizzi di Studio" class="sc-col-2">
                                        <c:if test="${empty lista1}">
                                            Dati non disponibili per la scuola
                                        </c:if>
                                        <c:if test="${not empty lista1}">
                                        <table class="sc-table sc-table-standard">
                                            <c:forEach items="${lista1}" var="riga">
                                                <tr>
                                                    <td>${riga.desSer}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td data-col-1="Periodo scolastico" class="sc-col-1 sc-center">II Periodo</td>
                                    <td data-col-2="Indirizzi di Studio" class="sc-col-2">
                                        <c:if test="${empty lista2}">
                                            Dati non disponibili per la scuola
                                        </c:if>
                                        <c:if test="${not empty lista2}">
                                        <table class="sc-table sc-table-standard">
                                            <c:forEach items="${lista2}" var="riga">
                                                <tr>
                                                    <td>${riga.desSer}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td data-col-1="Periodo scolastico" class="sc-col-1 sc-center">III Periodo</td>
                                    <td data-col-2="Indirizzi di Studio" class="sc-col-2">
                                        <c:if test="${empty lista3}">
                                            Dati non disponibili per la scuola
                                        </c:if>
                                        <c:if test="${not empty lista3}">
                                        <table class="sc-table sc-table-standard">
                                            <c:forEach items="${lista3}" var="riga">
                                                <tr>
                                                    <td>${riga.desSer}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        </c:if>
                                    </td>
                                </tr>
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