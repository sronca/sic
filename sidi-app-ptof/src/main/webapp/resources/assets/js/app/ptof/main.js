'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptofApp', [
  'ui.tinymce',                           
  'ngLoadingSpinner',
  'ngSanitize',
  'blueimp.fileupload',  
  'ptof.filters','monospaced.elastic',
  'ptof.commons.services',
  'ptof.directives',
  'ptof.controlles.home',
  'ptof.controlles.gestione',
  'ptof.controlles.gestione.sezione_08_obbiettivi',
  'ptof.controlles.gestione.sezione_09_obbiettivi',
  'ptof.controlles.gestione.sezione_11_progettiCV',
  'ptof.controlles.gestione.sezione_12_progetti_exstra',
  'ptof.controlles.gestione.sezione_13_inziative_didatiche',
  'ptof.controlles.gestione.sezione_14_attivita_sostegno',
  'ptof.controlles.gestione.sezione_15_organizzazione_risorse',
  'ptof.controlles.gestione.sezione_16_organizzazione_classi',
  'ptof.controlles.gestione.sezione_17_programmzione_formazione',
  'ptof.controlles.gestione.sezione_18_strumenti_attrezzature_tecn',
  'ptof.controlles.gestione.sezione_19_collaborazioni_entilocali',
  'ptof.controlles.gestione.sezione_20_pianifizione_interventi_monitoraggio',
  'ptof.controlles.gestione.sezione_22_alternanza_scuola_lavoro',
  'ptof.controlles.gestione.sezione_23_dati_finali_scuola',
  'ptof.controlles.gestione.sezione_24_piano_nazionale_scuola_digitale',
  'ptof.controlles.gestione.sezione_21_articolazione_oraria',
  'ptof.controlles.gestione.sezione_25_fabbisognoattrezzature',
  'ptof.controlles.gestione.sezione_26_fabbisognocattadre',
  'ptof.controlles.gestione.sezione_27_fabbisogno_sostegno',
  'ptof.controlles.gestione.sezione_28_fabbisogno_potenziamento',
  'ptof.controlles.gestione.sezione_29_fabbisogno_posti_personale_amm_tec_ausi',
  'ptof.controlles.gestione.sezione_26_fabbisognocattadre',
  'ptof.controlles.gestione.sezione_30_fabbisogno_connesso_progetto',
  'ptof.controlles.gestione.sezione_31_fabbisogno_connesso_formazione',
  'ptof.controller.monitoraggio.documenti_attivabili',
  'ptof.controllers.monitoraggio.reportCompleto',
  'ptof.controllers.monitoraggio.fabbissognoPostiComuni',
  'ptof.controllers.monitoraggio.fabbissognoPostiSostegno',
  'ptof.controllers.monitoraggio.fabbissognoPostiPotenziamento',
  'ptof.controllers.monitoraggio.fabbissognoTotaleOrganica',
  'ptof.controllers.convalida.cruscottoConvalidaFabbisogno',
  'ptof.controllers.convalida.cruscottoConvalidaSingolaFabbisogno',
  'ptof.controllers.consultazionePtof.consultazionePtof',
  'ptof.controllers.consultazionePtof.consultazioneDettaglioPtof',
  'ptof.controllers.convalida.cruscottoConvalidaSingolaFabbisogno',
  'ptof.controlles.pubblica',
  'ptof.controller.catalogo.documentiAttivabili',
  'ptof.controller.catalogo.documentoDecreti',
  'ptof.controller.catalogo.documentoDecretiDettaglio'
  ])
.config(['usSpinnerConfigProvider', function (usSpinnerConfigProvider) {
    usSpinnerConfigProvider.setDefaults({color: 'blue',  shadow: true });
}])
.config(['$httpProvider', function ($httpProvider) {
   
    var interceptor = ['$rootScope', '$q','$window',  function (rootScope, $q , $window ) {

        function success(response) {
            
            if( response.config.url && response.config.url.indexOf('set-m-')>-1 ) {
        	 
        	 var sms = {
        		 title :"MESSAGGIO",
        		 text :"Operazione effettuata con successo",
        		 type :"OK"
        	 };
        	 rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
            }
            return response;
        }

        function error(response) {
            var status = response.status;
            if (status == 422 ) { // errori validazione
               console.log(response);
               var sms = {
			      		  title : "ATTENZIONE",
			      		  errorsFunction : response.data.validationError,
			      		  type :"KO"
      	       };
               rootScope.$broadcast("EVENT_SENDMESSAGE", sms );
               
            } 

            if (status == 408 ) { // sessione scaduta (time out)
                console.log('code 408',response);
                 
         	 	var sms = {
                  		 title :"ATTENZIONE",
                  		 text :"SESSIONE SCADUTA",
                  		 type :"KO",
                  		 onCloseRunFn : function () {
                  			 $window.location.href = APPGLOBALCONSTANT.contexPath+'/home.do';
                  		 }
               };
        	   rootScope.$broadcast("EVENT_SENDMESSAGE", sms );
                
                return;
             }
            
            if (status == 401 ) { // non autorizzato
                console.log(response);
                $window.location.href = APPGLOBALCONSTANT.contexPath+'/home.do';
                return;
             } 
            
            if (status != 422) {
	        	var sms = {
	        		 title :"ERRORE",
	        		 text :"Sistema momentanemente non disponibile, riprovare piu' tardi",
	        		 type :"KO",
	        		 onCloseRunFn : function () {
              			 $window.location.href = APPGLOBALCONSTANT.contexPath+'/home.do';
              		 }
	        	};
        	    rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
        	    return;
            }
            // otherwise
            return $q.reject(response);

        }

        return function (promise) {
            return promise.then(success, error);
        }

    }];
    $httpProvider.responseInterceptors.push(interceptor);
}])
 