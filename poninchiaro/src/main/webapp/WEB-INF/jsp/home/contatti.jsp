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
	        <a href="<c:url value="/"/>">Home</a> > Contatti
	    </div>
	    
	    <div class="content">
	        <div class="title-box">
	            <h2>Contatti</h2>
	        </div>
	        <h3>Ministero della Pubblica Istruzione</h3>
	        <div class="row">
	            <div class="gray-block">
	                <div class="col-1-3">
	                    <p class="maps-i icon"></p>
	                    <p>Viale Trastevere 76/A - Roma</p>
	                </div>
	                <div class="col-1-3">
	                    <p class="phone-i icon"></p>
	                    <p>(+39) 06 5849 3428</p>
	                </div>
	                <div class="col-1-3">
	                    <p class="email-i icon"></p>
	                    <p class="email">ponscuola.comunicazione@istruzione.it</p>
	                </div>
	            </div>
	        </div>
	        <h3>Mappa</h3>
	        <div class="row">
	            <div class="gray-block gray-block-maps">
	                <a class="modal-popup" href="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2970.3503390077617!2d12.470808499999999!3d41.8853221!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0000000000000000%3A0x248249b3cb271fc8!2sMinistero+dell&#39;Istruzione%2C+dell&#39;Universit%C3%A0+e+della+Ricerca!5e0!3m2!1sit!2sit!4v1441968626525">
	                	<img src="<c:url value="/resources/img/map.jpg"/>">
	                </a>
	                <p>Clicca per vedere la mappa dinamica</p>
				</div>
	        </div>
	    </div>	    		
		
		
		
		<%@ include file="../includes/footer.jsp"%>
		
	</div>

<%@ include file="../includes/scriptanalytics.jsp"%>
</body>


</html>



