<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp"%>
<%@ include file="../includes/html.jsp"%>

<head>
<%@ include file="../includes/head.jsp"%>
</head>


<body>

	<div id="container" class="shadow-fix">
		
		<%@ include file="../includes/menu.jsp"%>
		
	    <div id="breadcrumb">
	        <a href="<c:url value="/"/>">Home</a> > Glossario
	    </div>
	    
    	<div class="content">
            <h1>Glossario</h1>
	        <div class="row">
	            <div class="intro row-max">
	                <span>
	                	Il Glossario ha lo scopo di semplificare il linguaggio tecnico utilizzato dagli esperti dei Fondi Strutturali.
	                	<br/>
	                	Per la vastit&agrave; dell'argomento &egrave; da considerarsi un lavoro in continuo aggiornamento.</span>
	            </div>
				<div class="gray-block style-col-2">
	                
	                <c:forEach items="${glossario}" var="lettera">
		                <div class="white-block">
		                    <a id="${lettera.key}">${lettera.key}</a>
		                    <c:forEach items="${lettera.value}" var="parola">
			                    <p class="title">${parola.label}</p>
			                    <p class="description">
			                    	${parola.value}
								</p>
							</c:forEach>
		                </div>
	                </c:forEach>
	
	            </div>
	        </div>
        </div>	    		
	    <div class="block-glossary">
	        <ul>
	            <li><a href="#" data-anchor="A" <c:if test="${empty glossario['A']}">class="disable"</c:if> >a</a></li>
	            <li><a href="#" data-anchor="B" <c:if test="${empty glossario['B']}">class="disable"</c:if> >b</a></li>
	            <li><a href="#" data-anchor="C" <c:if test="${empty glossario['C']}">class="disable"</c:if> >c</a></li>
	            <li><a href="#" data-anchor="D" <c:if test="${empty glossario['D']}">class="disable"</c:if> >d</a></li>
	            <li><a href="#" data-anchor="E" <c:if test="${empty glossario['E']}">class="disable"</c:if> >e</a></li>
	            <li><a href="#" data-anchor="F" <c:if test="${empty glossario['F']}">class="disable"</c:if> >f</a></li>
	            <li><a href="#" data-anchor="G" <c:if test="${empty glossario['G']}">class="disable"</c:if> >g</a></li>
	            <li><a href="#" data-anchor="H" <c:if test="${empty glossario['H']}">class="disable"</c:if> >h</a></li>
	            <li><a href="#" data-anchor="I" <c:if test="${empty glossario['I']}">class="disable"</c:if> >i</a></li>
	            <li><a href="#" data-anchor="L" <c:if test="${empty glossario['L']}">class="disable"</c:if> >l</a></li>
	            <li><a href="#" data-anchor="M" <c:if test="${empty glossario['M']}">class="disable"</c:if> >m</a></li>
	            <li><a href="#" data-anchor="N" <c:if test="${empty glossario['N']}">class="disable"</c:if> >n</a></li>
	            <li><a href="#" data-anchor="O" <c:if test="${empty glossario['O']}">class="disable"</c:if> >o</a></li>
	            <li><a href="#" data-anchor="P" <c:if test="${empty glossario['P']}">class="disable"</c:if> >p</a></li>
	            <li><a href="#" data-anchor="Q" <c:if test="${empty glossario['Q']}">class="disable"</c:if> >q</a></li>
	            <li><a href="#" data-anchor="R" <c:if test="${empty glossario['R']}">class="disable"</c:if> >r</a></li>
	            <li><a href="#" data-anchor="S" <c:if test="${empty glossario['S']}">class="disable"</c:if> >s</a></li>
	            <li><a href="#" data-anchor="T" <c:if test="${empty glossario['T']}">class="disable"</c:if> >t</a></li>
	            <li><a href="#" data-anchor="U" <c:if test="${empty glossario['U']}">class="disable"</c:if> >u</a></li>
	            <li><a href="#" data-anchor="V" <c:if test="${empty glossario['V']}">class="disable"</c:if> >v</a></li>
	            <li><a href="#" data-anchor="Z" <c:if test="${empty glossario['Z']}">class="disable"</c:if> >z</a></li>
	        </ul>
	    </div>		
		
		
		<%@ include file="../includes/glossary-footer.jsp"%>
		
	</div>

<%@ include file="../includes/scriptanalytics.jsp"%>
</body>


</html>



