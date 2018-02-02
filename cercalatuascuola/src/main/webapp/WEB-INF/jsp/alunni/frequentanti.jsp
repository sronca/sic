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
                            	<h2>Studenti frequentanti - ${titoloTabellaAlunniPerAnnoCorso}</h2>
                                <c:if test="${not empty infoGrafico.desInfGra}"><p class="sc-note-small">${infoGrafico.desInfGra}</p></c:if>
                            	<div class="sc-cols">
                                    <div class="sc-col-full"><!-- classe sc-col-full del div -->
                                        <table class="sc-table sc-table-centered"><!-- classe sc-table-centered della tabella -->
                                            <thead>
                                                <tr>
                                                    <th class="sc-table-col-big">Periodo didattico</th>
                                                    <th>N. studenti</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:if test="${empty alunniPerAnnoCorso}">
                                                <tr>
                                                    <td colspan="2" class="sc-col-1 sc-center">Dati non disponibili per la scuola</td>
                                                </tr>
                                            </c:if> 
                                            <c:if test="${not empty alunniPerAnnoCorso}">
                                                <c:forEach items="${alunniPerAnnoCorso}" var="alunni">
                                                    <tr>
                                                        <td data-col-1="Periodo didattico" class="sc-col-1">${alunni.perAnnCor}</td>
                                                        <td data-col-2="n. studenti" class="sc-col-2 sc-center">${alunni.numAlu}</td>
                                                     </tr>
                                                </c:forEach>
                                            </c:if>
                                            </tbody>
                                        </table>
                                        <c:if test="${not empty infoGrafico.desNotGra}"><p class="sc-note">${infoGrafico.desNotGra}</p></c:if>
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