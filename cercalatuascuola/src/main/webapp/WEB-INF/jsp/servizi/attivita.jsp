<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>

<html>
    <head>
        <%@ include file="../includes/head_dettaglio.jsp" %>
    </head>
    <body data-codscuut="${scuola.codScuUt}" data-desnomscu="${scuola.desNomScu}">

        <%@ include file="../includes/toolbar_header.jsp" %>

        <section class="sc-internal-main sc-detail">

            <%@ include file="../includes/breadcrumbandtitle.jsp" %>
            <%@ include file="../includes/menu.jsp" %>

            <div class="sc-main-content-detail">
                <div class="sc-wrapper sc-wrapper-alt">
                    <div class="sc-table-table sc-external-box">
                        <div class="sc-table-row sc-external-box">

                            <%@ include file="../includes/menu_aside_servizi.jsp" %>

				          <article class="sc-internal-content sc-table-cell">
				            <div class="sc-cols">
				              
					              <div class="sc-col-full sc-col-border">
					                <h2>Certificazioni</h2>
					                <div class="sc-cols">
	
					                  <div class="sc-col-full">
					                  	<c:if test="${not empty listaCertificazioni}">
						                    <table class="sc-table sc-table-standard">
						                      <thead>
						                        <tr>
						                          <th>Codice meccanografico</th>
						                          <th>Tipo</th>
						                          <th>Alunni</th>
						                        </tr>
						                      </thead>
						                      <tbody>
						                      	<c:forEach items="${listaCertificazioni}" var="cert">
							                        <tr>
							                          <td data-col-1="Codice Meccanografico" class="sc-col-1"> ${scuola.codScuUt} </td>
							                          <td data-col-2="Tipo" class="sc-col-2"> ${cert.desNot} </td>
							                          <td data-col-3="Alunni" class="sc-col-3 sc-center"> ${cert.numAlu} </td>
							                        </tr>
						                        </c:forEach>
						
						                      </tbody>
						                    </table>
					                    </c:if>
					                    <c:if test="${empty listaCertificazioni}">
					                    	<p>Dati non disponibili per la scuola</p>
					                    </c:if>
					                  </div>
					                  
					                </div>
					              </div>
				              
				              
				              
				              <c:if test="${not empty listaAltriServizi}">
				              
			                    <c:forEach items="${listaAltriServizi}" var="tipologiaServizio">
			                        <c:forEach items="${tipologiaServizio.servizi}" var="servizio">
				              
						              <c:if test="${servizio.desSer ne 'Altri Servizi'}">
						              <div class="sc-col-full sc-col-border">
						                <h2>${servizio.desSer}</h2>
						                <div class="sc-cols">
						                  <div class="sc-col-full">
						                    <div class="sc-list-check">
						                      <ul>
						                      	<c:forEach items="${servizio.attivitaServizio}" var="attivitaServizio" varStatus="row">
						                        <li> ${attivitaServizio.desAttSer} </li>
						                        </c:forEach>
						                      </ul>
						                    </div>
						                  </div>
						                </div>
						              </div>
						              </c:if>
						              
						            </c:forEach>
						        </c:forEach>
					              
					              
				              </c:if>
				              <c:if test="${empty listaAltriServizi}">
					              <div class="sc-col-full sc-col-border">
					                <h2>Attivit&agrave; di laboratorio</h2>
					                <div class="sc-cols">
					                  <div class="sc-col-full">
					                  	<p>Dati non disponibili per la scuola</p>
					                  </div>
					                </div>
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