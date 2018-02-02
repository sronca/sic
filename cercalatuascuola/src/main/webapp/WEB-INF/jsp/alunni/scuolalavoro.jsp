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
                            	<h2>Alternanza scuola lavoro</h2>
                            
                                <div class="sc-cols">
                                    <div class="sc-col-full">

                                        <div class="">
                                            <table class="sc-table sc-table-standard">
                                                <thead>
                                                <tr>
                                                    <th>Tipologia struttura ospitante</th>
                                                    <th>n. percorsi attivati</th>
                                                    <th>n. alunni 1° anno</th>
                                                    <th>n. alunni 2° anno</th>
                                                    <th>n. alunni 3° anno</th>
                                                    <th>n. alunni 4° anno</th>
                                                    <th>n. alunni 5° anno</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:if test="${not empty alternanzaScuolaLavoro}">
	                                                <c:forEach items="${alternanzaScuolaLavoro}" var="vo">
	                                                    <tr>
	                                                        <td data-col-1="Tipologia Struttura Ospitante" class="sc-col-1">
	                                                        	<span style="cursor: pointer;" title="${vo.desStrutturaOspitante}">${vo.tipoStrutturaOspitante}</span>
	                                                        </td>
	                                                        <td data-col-2="n. percorsi attivati" class="sc-col-2 sc-center">${vo.percorsi}</td>
	                                                        <td data-col-3="n. alunni 1° anno" class="sc-col-3 sc-center">${vo.alunni1}</td>
	                                                        <td data-col-4="n. alunni 2° anno" class="sc-col-4 sc-center">${vo.alunni2}</td>
	                                                        <td data-col-5="n. alunni 3° anno" class="sc-col-5 sc-center">${vo.alunni3}</td>
	                                                        <td data-col-6="n. alunni 4° anno" class="sc-col-6 sc-center">${vo.alunni4}</td>
	                                                        <td data-col-7="n. alunni 5° anno" class="sc-col-7 sc-center">${vo.alunni5}</td>
	                                                    </tr>
	                                                </c:forEach>
                                                </c:if>
                                                <c:if test="${empty alternanzaScuolaLavoro}">
	                                                <tr>
	                                                    <td colspan="7" class="sc-col-1 sc-center">Dati non disponibili per la scuola</td>
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