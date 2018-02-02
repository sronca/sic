<%@ include file="taglib.jsp"%>

<title>Sidi - Miur - Ministero della Pubblica Istruzione</title>

<!-- META -->

	<!-- <meta http-equiv="X-UA-Compatible" content="IE=8" /> 
		 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	-->
	<meta http-equiv="X-UA-Compatible" content="IE=9" />

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Content-language" content="it-IT" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="max-age=0" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="expires" content="-1" />
	<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

<!-- META -->

<!-- CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/webfont.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<link href="<%=request.getContextPath()%>/assets/plugins/an-datetimepicker/4.14.30/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
	
	<link href="<%=request.getContextPath()%>/assets/plugins/bs-select/1.11.2/css/bootstrap-select.css" rel="stylesheet" type="text/css"/>
	
	<!-- CSS CM-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/plugins/jq-datatable/1.10.12/css/dataTables.bootstrap.css">
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/plugins/jq-datatable/buttons-1.2.2/css/buttons.bootstrap.css">
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/plugins/bs-dialog/bootstrap-dialog.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/custom.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ptof-custom.css" />
 		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/custum-font.css" type="text/css"/>
	<!-- CSS CM -->

	<link rel="icon" href="<%=request.getContextPath()%>/assets/img/favicon.ico" type="image/x-icon" />



	<style type="text/css" >
	
		@font-face {
		  font-family: 'glyphicons-icons';
		
		  src: url('<%=request.getContextPath()%>/assets/fonts/glyphicons-halflings-regular.eot');
		  src: url('<%=request.getContextPath()%>/assets/fonts/glyphicons-halflings-regular.eot?#iefix') format('embedded-opentype'), url('<%=request.getContextPath()%>/assets/fonts/glyphicons-halflings-regular.woff2') format('woff2'), url('<%=request.getContextPath()%>/assets/fonts/glyphicons-halflings-regular.woff') format('woff'), url('<%=request.getContextPath()%>/assets/fonts/glyphicons-halflings-regular.ttf') format('truetype'), url('<%=request.getContextPath()%>/assets/fonts/glyphicons-halflings-regular.svg#glyphicons_halflingsregular') format('svg');
		}
    </style>

	 <!--[if lt IE 9]>
	     <link href="<%=request.getContextPath()%>/assets/css/ptof-custom-ie8.css" rel="stylesheet" type="text/css"/>
	 <![endif]-->
	
<!-- CSS -->

<!-- JAVASTRIPT -->


 <!--
    IE8 support, see AngularJS Internet Explorer Compatibility http://docs.angularjs.org/guide/ie
    For Firefox 3.6, you will also need to include jQuery and ECMAScript 5 shim
  -->
  <!--[if lt IE 9]>
    <script src=""<%=request.getContextPath()%>/assets/plugins/js-shim/4.5.9/es5-shim.js"></script>
  <![endif]-->

   <!--[if lt IE 9]>                           
      <script src="<%=request.getContextPath()%>/assets/js/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="<%=request.getContextPath()%>/assets/js/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	<script type="text/javascript">
		//per browser che non supportano il logging
		if (!window.console) {
			console = {
				log : function() {
				}
			};
		}
		APPGLOBALCONSTANT = {
			contexPath : '<%=request.getContextPath()%>' ,
			TEXTAREA_MAX_SIZE : { size : 11 , messaggioErrore :  'Il valore deve essere minore o uguale a 1024' } ,
			TEXTAREA_MIN_SIZE : { size : 1 ,  messaggioErrore :  'Inserire almeno un testo' },
			SMS01_CAMPO_OBBLIGATORIO : { messaggioErrore :  'Campo Obbligatorio' },
			SMS02_CAMPO_TXTAREA_OVER_MAX : { size: 2000 , messaggioErrore :  'Dimensione massima possibile (2000 caratteri ) superata' },
			SMS03_CAMPO_DATA_NON_VALID : { size: 10 , messaggioErrore :  'Data non valida. ' },
			SMS03_CAMPO_DATA_LOWER_NON_VALID : {size : 10, messaggioErrore : '{1}, non può essere inferiore alla {2}' }
			
		};         
	</script>
	 


	<!-- webfont loader -->
	<script>
				WebFontConfig = {
					custom: {
						families: ["titilliumweb-r", "titilliumweb-sb", "titilliumweb-i", "miur-icons","glyphicons-icons"]  
					}
				};

				(function() {
					var wf 		= document.createElement("script");
					wf.async 	= "true";
					wf.src 		= "<%=request.getContextPath()%>/assets/js/webfont.min.js";
					var s 		= document.getElementsByTagName("script")[0];
					s.parentNode.insertBefore(wf, s);
				})();
	</script>
	
	
	
	<!-- webfont loader -->
 
    <!-- LIB --> 
		<script src="<%=request.getContextPath()%>/assets/js/modernizr/3.3.1/modernizr-custom.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets/js/jquery/1.12.4/jquery.min.js"></script>

        <!-- MOMENT -->
        
            	<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/moment/2.15.1/moment-with-locales.min.js"> </script>
				<script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/an-datetimepicker/4.14.30/js/bootstrap-datetimepicker.js"></script>
        <!-- DATE --> 


		<!--  BOOTSTRAPJS -->	
		<script src="<%=request.getContextPath()%>/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script>


		<!-- ANGULAR --> 
		<script src="<%=request.getContextPath()%>/assets/js/angularjs/1.2.28/angular.min.js" ></script>
		<script src="<%=request.getContextPath()%>/assets/js/angularjs/1.2.28/angular-sanitize.min.js"   ></script>
 
		<!-- ANGULAR -->

		<!-- PLUG-IN JQUERY OS-->

            <!--  FILEUPLOAD  -->                         
                <script src="<%=request.getContextPath()%>/assets/plugins/jq-fileu-upload/9.12.5/vendor/jquery.ui.widget.js" ></script>
				<script src="<%=request.getContextPath()%>/assets/plugins/jq-fileu-upload/9.12.5/jquery.iframe-transport.js"  ></script>
				<script src="<%=request.getContextPath()%>/assets/plugins/jq-fileu-upload/9.12.5/jquery.fileupload.js"  ></script>
				<script src="<%=request.getContextPath()%>/assets/plugins/jq-fileu-upload/9.12.5/jquery.fileupload-process.js"  ></script>
				<script src="<%=request.getContextPath()%>/assets/plugins/jq-fileu-upload/9.12.5/jquery.fileupload-image.js"  ></script>
				<script src="<%=request.getContextPath()%>/assets/plugins/jq-fileu-upload/9.12.5/jquery.fileupload-audio.js"  ></script>
				<script src="<%=request.getContextPath()%>/assets/plugins/jq-fileu-upload/9.12.5/jquery.fileupload-video.js"  ></script>
				<script src="<%=request.getContextPath()%>/assets/plugins/jq-fileu-upload/9.12.5/jquery.fileupload-validate.js"  ></script>
				<script src="<%=request.getContextPath()%>/assets/plugins/jq-fileu-upload/9.12.5/jquery.fileupload-angular.js"  ></script>
	        <!--  FILEUPLOAD  --> 

			<script src="<%=request.getContextPath()%>/assets/plugins/jq-file-download/1.4.4/jquery.fileDownload.js"  ></script>

            <!--  DATATABLE -->

			<script src="<%=request.getContextPath()%>/assets/plugins/jq-datatable/1.10.12/js/jquery.dataTables.min.js"  ></script>
			<script src="<%=request.getContextPath()%>/assets/plugins/jq-datatable/1.10.12/js/dataTables.bootstrap.min.js"  ></script>
			                                           
			<script src="<%=request.getContextPath()%>/assets/plugins/jq-datatable/buttons-1.2.2/js/dataTables.buttons.min.js"  ></script>
			<script src="<%=request.getContextPath()%>/assets/plugins/jq-datatable/buttons-1.2.2/js/buttons.bootstrap.min.js"  ></script>
			<script src="<%=request.getContextPath()%>/assets/plugins/jq-datatable/buttons-1.2.2/js/buttons.colVis.min.js"  ></script>
			                                      
			<script src="<%=request.getContextPath()%>/assets/plugins/jq-datatable/1.10.12/responsive/2.1.0/js/dataTables.responsive.min.js"  ></script>
			<script src="<%=request.getContextPath()%>/assets/plugins/jq-datatable/1.10.12/responsive/2.1.0/js/responsive.bootstrap.min.js"  ></script>

            <!--  TINYMCE -->
			<script src="<%=request.getContextPath()%>/assets/plugins/jq-tinymce/4.4.3/tinymce.min.js"></script>
			
			<script src="<%=request.getContextPath()%>/assets/plugins/an-base64/2.0.5/angular-base64.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/an-datetimepicker/4.14.30/js/jquery.formatter.js"></script>
			
			<!-- autonumeric -->
			 <script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/jq-autonumeric/1.9.26/autoNumeric.js"></script>
			 
			
		<!-- PLUG-IN JQUERY OS-->

		<!-- PLUG-IN JQUERY CM-->

			<script src="<%=request.getContextPath()%>/assets/js/functions.lib.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/custom.lib.js"></script>
 		
		<!-- PLUG-IN JQUERY CM-->

		<!-- PLUG-IN Bootstrap CM-->
	
	
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/bs-select/1.11.2/js/bootstrap-select.js">	</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/bs-bootpag/1.0.7/jquery.bootpag.js"> 	 </script>
		
		<!-- PLUG-IN Bootstrap CM-->



		<!-- PLUG-IN ANGULAR OS-->
			<script src="<%=request.getContextPath()%>/assets/plugins/jq-spiner/2.3.2/spin.js"></script>
			<script src="<%=request.getContextPath()%>/assets/plugins/an-spinner/0.8.1/angular-spinner.js" ></script>
			<script src="<%=request.getContextPath()%>/assets/plugins/an-recursion/1.0.5/angular-recursion.js" ></script>
			<script src="<%=request.getContextPath()%>/assets/plugins/an-tinymce/0.0.14/tinymce.js" ></script>
			 
 
			<script type="text/javascript" src="<%=request.getContextPath()%>/assets/plugins/an-elastic/2.5.1/elastic.js">	</script>
		<!-- PLUG-IN ANGULAR OS-->

		<!-- PLUG-IN ANGULAR CM-->
			<script src="<%=request.getContextPath()%>/assets/plugins/an-cm-spinner-loading/0.0.1/angular-loading-spinner.js" ></script>

		<!-- PLUG-IN ANGULAR CM-->

	<!-- LIB -->

    <!-- APP -->
    
    <!-- 0 LOAD 0-->
	    <script src="<%=request.getContextPath()%>/assets/js/app/ptof/commons/constants.js"></script>
	    <script src="<%=request.getContextPath()%>/assets/js/app/ptof/commons/directives.js"></script>
	    <script src="<%=request.getContextPath()%>/assets/js/app/ptof/commons/filters.js"></script>
	    <script src="<%=request.getContextPath()%>/assets/js/app/ptof/commons/services.js"></script>
    <!-- 0 LOAD 0-->
    
    <!-- LOAD 1-->
    
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/mainGestioneDoc.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-08-obbiettivi.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-09-obbiettivi-miglioramenti.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-11-sezione-progetticv.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-12-sezione-progetti-exstra.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-13-sezione-iniziative-dida.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-14-sezione-attivita-sostegno.js">	</script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-15-sezione-organizzazione-risorse.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-16-sezione-organizzazione-classi.js">	</script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-17-sezione-programmazione-formazione.js"> </script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-18-strumenti-attrezzature-tecn.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-19-coll-entilocali-territori.js">	</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-20-pianificazione-interventi-monitoraggio.js"> </script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-21-sezione-aricolazione-oraria.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-22-alternanza-scuola-lavoro.js"> </script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-23-dati-finali-scuola.js"> </script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-24-piano-nazionale-scuola-digitale.js"> </script>		
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-25-fabbisogno-attrezzature-infrastrutture.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-26-fabbisogno-posti-cattedre.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-27-fabbisogno-posti-sostegno.js">	</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-28-fabbisogno-posti-potenziamento.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-29-fabbisogno-posti-personale-amm-tec-ausi.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-30-fabbisogno-connesso-progetto.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/gestione/00-sezione-31-fabbisogno-connesso-formazione.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/monitoraggio/fn-mon-documenti-attivabili.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/monitoraggio/fn-mon-report-completo.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/monitoraggio/fn-mon-cruscotto-fabbisogno-posti-comuni.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/monitoraggio/fn-mon-cruscotto-fabbisogno-posti-sostegno.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/monitoraggio/fn-mon-cruscotto-fabbisogno-posti-potenziamento.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/monitoraggio/fn-mon-cruscotto-fabbisogno-totale-organica.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/convalida/fn-cruscotto-convalida-fabbisogno.js"></script>																														
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/convalida/fn-cruscotto-convalida-singola-fabbisogno.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/pubblica/fn-pubblica-documento.js">	</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/consultazionePtof/fn-mon-consultazione-ptof.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/consultazionePtof/fn-mon-consultazione-dettaglio-ptof.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/catalogo/fn-gestione-catalogo-documenti.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/catalogo/fn-gestione-catalogo-documenti-decreti.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/catalogo/fn-gestione-catalogo-documenti-decreti-dettaglio.js"></script>
		
		<script src="<%=request.getContextPath()%>/assets/js/app/ptof/controllers/home/mainMenu.js"></script>
    <!-- LOAD 1-->
    
    <!-- LOAD 2-->
 	    <script src="<%=request.getContextPath()%>/assets/js/app/ptof/main.js"></script>
    <!-- LOAD 2-->
    
    <!-- APP -->	
<!-- JAVASTRIPT -->

<jsp:include page="../template/tmpl.jsp"></jsp:include>
 
 
 
 

 
