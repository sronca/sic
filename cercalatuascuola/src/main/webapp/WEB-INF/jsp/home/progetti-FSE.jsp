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
            <h2>Sintesi dei progetti realizzati sulla formazione</h2>
            <div class="sc-cols">
            	<c:if test="${visualizzaDati}">
	              <table class="sc-table sc-table-standard sc-table-vertical-th">
	                <tr>
	                  <th colspan="2">Totale interventi realizzati</th>
	                  <td data-col-1="Totale interventi realizzati" class="sc-col-1 sc-center"> 
	                   	<c:if test="${not empty riga.totIntReal}">${riga.totIntReal}</c:if>
	                   	<c:if test="${empty riga.totIntReal}">n.d.</c:if>	                  
	                  </td>
	                </tr>
	                <tr>
	                  <th colspan="2">Importo totale autorizzato (€)</th>
	                  <td data-col-2="Importo totale autorizzato (€)" class="sc-col-2 sc-center">
	                   	<c:if test="${not empty riga.impTotAutFormat}">${riga.impTotAutFormat}</c:if>
	                   	<c:if test="${empty riga.impTotAutFormat}">n.d.</c:if>	                   
	                   </td>
	                </tr>
	                <tr>
	                  <th colspan="2">Numero totale dei destinatari formati</th>
	                  <td data-col-3="Numero totale dei destinatari formati" class="sc-col-3 sc-center"> 
	                   	<c:if test="${not empty riga.numTotDestForm}">${riga.numTotDestForm}</c:if>
	                   	<c:if test="${empty riga.numTotDestForm}">n.d.</c:if>	                  
	                  </td>
	                </tr>
	                <tr class="sc-special-tablerow">
	                  <th rowspan="3">Destinatari(in %)</th>
	                  <th>Adulti</th>
	                  <td class="sc-col-4 sc-center" data-col-4="Destinatari(in %): Adulti ">
	                   	<c:if test="${not empty riga.genFormat}">${riga.genFormat}</c:if>
	                   	<c:if test="${empty riga.genFormat}">n.d.</c:if>	                  
	                  </td>
	                </tr>
	                <tr class="sc-special-tablerow">
	                  <th>Personale</th>
	                  <td class="sc-col-4 sc-center" data-col-4="Destinatari(in %): Personale">
	                   	<c:if test="${not empty riga.persFormat}">${riga.persFormat}</c:if>
	                   	<c:if test="${empty riga.persFormat}">n.d.</c:if>	                  
	                  </td>
	                </tr>
	                <tr class="sc-special-tablerow">
	                  <th>Alunni</th>
	                  <td class="sc-col-4 sc-center" data-col-4="Destinatari(in %): Alunni">
	                   	<c:if test="${not empty riga.alnFormat}">${riga.alnFormat}</c:if>
	                   	<c:if test="${empty riga.alnFormat}">n.d.</c:if>	                  
	                  </td>
	                </tr>
	                <tr>
	                  <th colspan="2">Numero medio di ore di formazione effettuati per modulo</th>
	                  <td data-col-5="Numero medio di ore di formazione effettuati per modulo" class="sc-col-5 sc-center"> 
	                   	<c:if test="${not empty riga.numMedOreFormEffFormat}">${riga.numMedOreFormEffFormat}</c:if>
	                   	<c:if test="${empty riga.numMedOreFormEffFormat}">n.d.</c:if>	                  
	                  </td>
	                </tr>
	                <tr>
	                  <th colspan="2">Numero medio di certificazioni/attestati conseguiti per modulo</th>
	                  <td data-col-6="Numero medio di certificazioni/attestati conseguiti per modulo" class="sc-col-6 sc-center">
	                   	<c:if test="${not empty riga.numMedCertAttConsFormat}">${riga.numMedCertAttConsFormat}</c:if>
	                   	<c:if test="${empty riga.numMedCertAttConsFormat}">n.d.</c:if>	                   
	                  </td>
	                </tr>
	              </table>
	            </c:if>
	            <c:if test="${not visualizzaDati}">
	            	<h3 class="sc-center">Dati non disponibili per la scuola</h3>
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