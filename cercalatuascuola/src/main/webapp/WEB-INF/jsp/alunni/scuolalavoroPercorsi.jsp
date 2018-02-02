<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
<html>
    <head>
        <%@ include file="../includes/head_dettaglio.jsp" %>
    </head>
    <body data-codscuut="${scuola.codScuUt}" data-desnomscu="${scuola.desNomScuNorm}">
        <%@ include file="../includes/toolbar_header.jsp" %>
        <section class="sc-internal-main sc-detail">
            <%@ include file="../includes/breadcrumbandtitle.jsp" %>
            <%@ include file="../includes/menu.jsp" %>
            <div class="sc-main-content-detail">
                <div class="sc-wrapper sc-wrapper-alt">
                    <div class="sc-table-table sc-external-box">
                        <div class="sc-table-row sc-external-box">
                            <%@ include file="../includes/menu_aside_alunni.jsp" %>
                            <article class="sc-internal-content sc-table-cell">
                                <h2>Alternanza scuola lavoro – ${infoGrafico.desTitGra}</h2>
                                <c:if test="${not empty infoGrafico.desInfGra}"><p class="sc-note-small">${infoGrafico.desInfGra}</p></c:if>
                                <div class="sc-cols">
                                  <div class="sc-col-full">
                                    <div class="">
                                      <table class="sc-table sc-table-standard">
                                        <thead>
                                          <tr>
                                            <th class="sc-table-col-big">Percorsi attivati</th>
                                            <th>N. ore in aula</th>
                                            <th>N. ore c/o  strutture</th>
                                            <th>N. alunni 3° anno</th>
                                            <th>N. alunni 4° anno</th>
                                            <th>N. alunni 5° anno</th>
                                          </tr>
                                        </thead>
                                        <tbody>
                                        <c:if test="${empty listaPercorsi}">
                                            <tr>
                                                <td colspan="6" class="sc-col-1 sc-center">Dati non disponibili per la scuola</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${not empty listaPercorsi}">
                                            <c:forEach items="${listaPercorsi}" var="vo">
                                                <tr>
                                                    <td data-col-1="Percorsi attivati" class="sc-col-1"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/alunni/scuolalavoro/percorsi/${vo.prgPerScu}" />" class="sc-view-link"><span></span>${vo.desPercorso}</a></td>
                                                    <td data-col-2="n. ore in aula" class="sc-col-2 sc-center">${vo.numOreAula}</td>
                                                    <td data-col-3="n. ore c/o  la struttura" class="sc-col-3 sc-center">${vo.numOreStruttura}</td>
                                                    <td data-col-4="n. alunni 3° anno" class="sc-col-4 sc-center">${vo.numAlunniAnno3}</td>
                                                    <td data-col-5="n. alunni 4° anno" class="sc-col-5 sc-center">${vo.numAlunniAnno4}</td>
                                                    <td data-col-6="n. alunni 5° anno" class="sc-col-6 sc-center">${vo.numAlunniAnno5}</td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        </tbody>
                                      </table>
                                      <c:if test="${not empty infoGrafico.desNotGra}"><p class="sc-note">${infoGrafico.desNotGra}</p></c:if>
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