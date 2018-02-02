<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp"%>
<%@ include file="../includes/html.jsp"%>

<head>
<%@ include file="../includes/head.jsp"%>
</head>


<body>

	<div id="container">
		
		<%@ include file="../includes/menu.jsp"%>
		
	    <div class="parallax img-banner" data-velocity="-0.6"></div>
		<div class="content space-top">
	        <div class="title-box">
	            <h2>Una finestra aperta sul PON</h2>
	        </div>
	        <div class="row">
	            <div class="box-2-col text-box padding-right">Dai dati statistici, agli open data, dalle informazioni di dettaglio sui singoli progetti, ai lavori realizzati dalle scuole con i finanziamenti europei: PON in chiaro è la vetrina del Programma operativo del MIUR. Grazie al nuovo layout grafico e alla nuova interfaccia web tutti gli utenti, anche quelli che si affacciano per la prima volta al mondo dei Fondi strutturali, e persino i non addetti ai lavori, possono accedere facilmente alle informazioni e avere così un quadro chiaro ed esaustivo del Programma operativo.</div>
	            <div class="box-2-col text-box padding-left">Dispersione scolastica, orientamento, stage, edilizia, laboratori, sono solo alcuni dei temi su cui approfondire la ricerca per arrivare ai finanziamenti, agli interventi dei singoli istituti e ai risultati ottenuti. Uno spazio web utile per mettere a fattor comune le esperienze delle scuole e capire cosa c’è dietro le quinte dei Fondi strutturali.<br><br>Buona navigazione</div>
	        </div>
	        <div class="row space-block max-width">		
				<div class="box-2-col faq text-center">
					<div class="title-box-container">
						<a href="<c:url value="/faq/"/>">
							<span class="title-box-position"></span>
							<span class="title-box-text">FAQ</span>
						</a>
					</div>
				</div>
	            <div class="box-2-col pon text-center">
					<div class="title-box-container">
						<a href="<c:url value="/contatti/"/>">
							<span class="title-box-position"></span>
							<span class="title-box-text">Contatti</span>
						</a>
					</div>
				</div>
	        </div>
	    </div>
		<%@ include file="../includes/footer.jsp"%>
		
	</div>

<%@ include file="../includes/scriptanalytics.jsp"%>
</body>


</html>



