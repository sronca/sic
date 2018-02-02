 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
    <div class="sc-extra">
        <ul class="sc-breadcrumbs">
            <li><a href="/cercalatuascuola/">Home</a></li>
            <li><a href="/cercalatuascuola${linkRisultatiRicerca}">Istituti</a></li>
            <li><a href="<c:url value="/istituti/${scuola.codScuUtPri}/${scuola.denScuPriNorm}/"/>">Istituto Principale: ${scuola.denScuPri}</a></li>
            <c:if test="${not scuola.isIstitutoPrincipale()}">
            	<li><span>Plesso: ${scuola.desNomScu}</span></li>
            </c:if>
        </ul>
        <div class="sc-favorite"><a href="/cercalatuascuola/preferiti/"> <span>Scuole preferite</span> <span class="sc-number">(<span class="sbm-counter">0</span>)</span> </a>
        </div>
    </div>
    <div class="sc-title">
        <div class="sc-wrapper">
            <h1>${scuola.desNomScu}</h1>
        </div>
    </div>