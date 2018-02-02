<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
<head>
    <%@ include file="../includes/head_dettaglio.jsp" %>
</head>
<body class="sc-edilizia">

<script>
function show_section(codice) {
	
	$(".li_section" ).each(function(){
		$(this).removeClass('sc-active');
	});
	
	$('#li_section_'+codice).addClass('sc-active');

	$(".div_section" ).each(function(){
		$(this).addClass('hidden');
	});
	
	$('#div_section_'+codice).removeClass('hidden');
	
	
}
</script>


<%@ include file="../includes/toolbar_header.jsp" %>

<section class="sc-internal-main sc-detail">

  <%@ include file="../includes/breadcrumbandtitle.jsp" %>
  <%@ include file="../includes/menu.jsp" %>

  <div class="sc-main-content-detail">
    <div class="sc-wrapper sc-wrapper-alt">
      <div  class="sc-table-table sc-external-box">
        <div class="sc-table-row sc-external-box">
	        
	        <aside class="sc-table-cell"><span class="sc-main-menu-section sc-edilizia-menu-section">Edilizia</span>
	          <nav class="sc-list-side-menu">
	            <ul>
	            	<c:forEach items="${voEdilizia.sezioni}" var="voSezione" varStatus="status">
	              		<c:if test="${voSezione.value.abilitato == 1}">
	              			<c:if test="${status.index == 0}">
	              				<li id="li_section_${voSezione.value.codice}" class="li_section sc-active">
	              			</c:if>
	              			<c:if test="${status.index > 0}">
	              				<li id="li_section_${voSezione.value.codice}" class="li_section">
	              			</c:if>              			
	              				<a href="javascript:show_section('${voSezione.value.codice}');">${voSezione.value.descrizione}</a>
	              			</li>
	              		</c:if>
	              	</c:forEach>
	            </ul>
	          </nav>
	          <div class="sc-share">
	            <h2>Condividi</h2>
				<div id="share"></div>
	          </div>
	        </aside>
          
          
	        <article class="sc-internal-content sc-table-cell">
	          <div class="sc-cols">
	            <h2 class="sc-center">Edificio n ${numero} - ${voEdificio.indirizzo}</h2>
	            <p class="sc-edilizia-back sc-right"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/edilizia?t=S"/>">Torna a scelta edificio</a></p>
	            <div class="sc-col-full sc-col-border">
	              
	              <c:forEach items="${voEdilizia.sezioni}" var="voSezione" varStatus="status">
	              	<c:if test="${voSezione.value.abilitato == 1}">
	              	    <c:if test="${status.index == 0}">
		              		<div class="sc-col-3-4 div_section" id="div_section_${voSezione.value.codice}">
		                </c:if>
	              	    <c:if test="${status.index > 0}">
		              		<div class="sc-col-3-4 div_section hidden" id="div_section_${voSezione.value.codice}">
		                </c:if>	                
		                <h2>${voSezione.value.descrizione}</h2>
		                <table class="sc-table sc-table-standard sc-table-vertical-th">
		                  <tbody>
		                  	<c:forEach items="${voSezione.value.campi}" var="voCampo">
			                    <tr>
			                      <th>${voCampo.value.descrizione}</th>
			                      <td>${voCampo.value.valore}</td>
			                    </tr>
		                    </c:forEach>
		                  </tbody>
		                </table>
		               <p class="sc-note sc-center"> NOTA: I dati sono riferiti all'anno scolastico ${annoScolastico}</p>
		              </div>
	              	</c:if>
	              </c:forEach>
	              
	              <div class="sc-col-1-4 sc-edilizialogo"> <img src="<c:url value="/resources/img/logo_edilizia_big.jpg" />" width="179" height="177"> </div>
	            </div>
	            <p class="sc-edilizia-back sc-right"><a href="<c:url value="/istituti/${scuola.codScuUt}/${scuola.desNomScuNorm}/edilizia?t=S"/>">Torna a scelta edificio</a></p>
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