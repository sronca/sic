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
	        <a href="<c:url value="/"/>">Home</a> > FAQ
	    </div>
	    
    	<div class="content">
            <h2>FAQ</h2>
            <div class="gray-block accordion-box">
            
               	<c:if test="${not empty elencoFaq}">
					<c:forEach items="${elencoFaq}" var="vo" varStatus="status">
		            	
		            	<div class="accordion-group" id="q${status.index}">
			                <div data="q${status.index}" class="accordion-faq-question" >
			                    <h4>${status.index + 1}) ${vo.desDomFaq}</h4>
			                </div>
		                    <div class="accordion-faq-answer row-max-block">
		                    	<p>${vo.desRisFaq}</p>
		                    </div>
		                </div>
	                
	                </c:forEach>
                </c:if>
                
            </div>
        </div>	    		
		
		
		
		<%@ include file="../includes/footer.jsp"%>
		
	</div>

<%@ include file="../includes/scriptanalytics.jsp"%>
</body>


</html>



