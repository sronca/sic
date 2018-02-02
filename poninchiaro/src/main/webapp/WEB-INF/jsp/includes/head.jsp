    <title>${title}</title>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="index,follow">
    <meta name="description" content="${description}">
    <meta name="keywords" content="${keywords}">

    <!-- facebook -->
    <meta property="og:site_name" content="Pon in chiaro">
    <meta property="og:type" content="website">
    <meta property="og:title" content="${socialTitle}">
    <meta property="og:description" content="${description}">
    <meta property="og:image" content="http://poninchiaro.istruzione.it/poninchiaro/resources/img/logo-big.jpg">
    <meta property="og:url" content="${urlPage}">
    <!-- facebook -->

    <!-- twitter -->
    <meta name="twitter:card" content="summary">
	<meta name="twitter:site" content="@MiurSocial">
	<meta name="twitter:creator" content="@MiurSocial">    
    <meta name="twitter:title" content="${socialTitle}">
    <meta name="twitter:description" content="${description}">
    <meta name="twitter:image" content="http://poninchiaro.istruzione.it/poninchiaro/resources/img/logo-big.jpg">
    <meta name="twitter:url" content="${urlPage}">
    <!-- twitter -->

    <link rel="shortcut icon" href="<c:url value="/resources/favicon.ico"/>" />
    <link rel="canonical" href="${canonical}">

    <link href="<c:url value="/resources/css/font-face.css"/>" rel="stylesheet"> 
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/tablesaw.css"/>" rel="stylesheet">
    
    <link href="<c:url value="/resources/css/jssocials.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/jssocials-theme-a.css"/>" rel="stylesheet">
    
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

    <script>
        WebFontConfig = {
            custom: {
                families: ['bitterbold', 'bitteritalic', 'bitterregular','ubuntulight','ubuntubold']
            }
        };
        (function() {
            var wf = document.createElement('script');
            wf.src = '<c:url value="/resources/js/webfont.js"/>';
            wf.type = 'text/javascript';
            wf.async = 'true';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(wf, s);
        })();
    </script>

    
	<script src="<c:url value="/resources/js/modernizr.js"/>"></script>    
	<script src="<c:url value="/resources/js/jquery/jquery-1.12.3.min.js"/>"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery/jquery.scrolly.js"/>"></script>
    
	<!--[if lt IE 9]>
	<script src="<c:url value="/resources/js/respond.min.js"/>"></script>
	<![endif]-->    
       
    <script src="<c:url value="/resources/js/jquery/jquery.magnific-popup.js"/>"></script>
    <script src="<c:url value="/resources/js/tablesaw.js"/>"></script>
    <script src="<c:url value="/resources/js/functions.lib.js"/>"></script>
    
    <script src="<c:url value="/resources/js/funzioniRicerca.js"/>"></script>
    
    <!-- includere solo nelle pagine dettaglio scuole -->
	<script src="<c:url value="/resources/js/jssocials.js"/>"></script>
	
		<script>
			$(document).ready(function() {

				$("#share").jsSocials({

					shares: [
						{
							share: "facebook",
							logo: "facebook.png"
						},
						{
							share: "twitter",
							logo: "twitter.png"
						},
						{
							share: "linkedin",
							logo: "linkedin.png"
						},
						{
							share: "whatsapp",
							logo: "whatsapp.png"
						},
					],
					logoPath: '<c:url value="/resources/img"/>',
					showLabel: true,
					layoutCount: "right",
				});

			});
		</script>
		<!-- includere solo nelle pagine dettaglio scuole -->



