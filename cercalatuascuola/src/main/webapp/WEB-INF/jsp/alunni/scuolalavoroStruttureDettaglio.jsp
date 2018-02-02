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
                                <h2>${struttura.desStrutturaOspitante}</h2>
                                <c:if test="${not empty infoGrafico.desInfGra}"><p class="sc-note-small">${infoGrafico.desInfGra}</p></c:if>
                                <div class="sc-cols">
                                    <div class="sc-col-full">
                                        <div class="">
                                            <table class="sc-table sc-table-standard">
                                                <thead>
                                                  <tr>
                                                    <th class="sc-table-col-big">Percorsi attivati</th>
                                                    <th>Ore previste da svolgere in aula</th>
                                                    <th>Ore da svolgere presso questa e altre strutture</th>
                                                  </tr>
                                                </thead>
                                                <tbody>
                                                <c:if test="${empty percorsiStruttura}">
                                                    <tr>
                                                        <td colspan="3" class="sc-col-1 sc-center">Dati non disponibili per la scuola</td>
                                                    </tr>
                                                </c:if>                                                
                                                <c:if test="${not empty percorsiStruttura}">
	                                                <c:forEach items="${percorsiStruttura}" var="vo">
                                                        <tr>
                                                            <td data-col-1="Percorsi attivati" class="sc-col-1">${vo.desPercorso}</td>
                                                            <td data-col-2="ore previste da svolgere in aula" class="sc-col-2 sc-center">${vo.numOreAula}</td>
                                                            <td data-col-3="ore da svolgere presso la struttura" class="sc-col-3 sc-center">${vo.numOreStruttura}</td>
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