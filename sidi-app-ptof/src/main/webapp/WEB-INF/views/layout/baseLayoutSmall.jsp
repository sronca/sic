<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="taglib.jsp"%>

<!--[if IE 8 ]> <html class="no-js lt-ie9 is-ie8" lang="en" ng-app="ptofApp" > <![endif]-->
<!--[if IE 9 ]> <html class="no-js lt-ie10 is-ie9" lang="en" ng-app="ptofApp" > <![endif]-->
<!--[if (gte IE 10)|!(IE)]><!--> <html class="no-js" lang="en" ng-app="ptofApp" ng-clock > <!--<![endif]-->
 
<head>
<jsp:include page="topHeader.jsp"></jsp:include>
</head>

<body>

	<div id="main-container"   >

		<tiles:insertAttribute name="header" />
 
		<tiles:insertAttribute name="menu" />
 
         <div id="main-page-content" class="page-content page-sidebar clearfix"	>
          <tiles:insertAttribute name="message" />
		  <tiles:insertAttribute name="body" />
		</div>
		
		<div id="footer">
			<p>
				<a href="http://www.istruzione.it">Ministero dell&#39;Istruzione, dell&#39;Università e della Ricerca</a> <span>Tutti i diritti riservati &copy; 2016</span>
			</p>
		</div>
		
	</div>

	<div class="service-message ie8-compat-message">
		<p>Sembra che il tuo browser sia in modalita'  "Compatibile".</p>
		<p>Per una corretta visione del sito utilizza la modalita' 
			standard dentro "Strumenti > Strumenti di sviluppo"</p>
	</div>
	<div class="service-message no-js-message">
		<p>Per utilizzare il sito e' necessario attivare javascript</p>
	</div>

</body>



</html>
