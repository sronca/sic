'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_23_dati_finali_scuola', [
  'ptof.constant',                                                                          
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-23-GestioneDatiFinaliScuolaCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory , statoDocumento) {
	console.log('GestioneSezione-23-GestioneDatiFinaliScuolaCtrl');
 
	$scope.vm = {
	    attribForm : {
	    },
	    form : {
	    }
    };// VM
	
	var init  = function () {
	 	var tempForm =  { keyDocumento : $scope.modelGestioneSezione.form.keyDocumento ,  keySezione : $scope.modelGestioneSezione.form.keySezione  } ;
		gestioneDocServicesFactory.initFormSezione( tempForm )
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			$scope.modelGestioneSezione.sezione = data.attribForm.sezione;   
			$scope.modelGestioneSezione.form.componenti = jQuery.extend(true,{}, data.attribForm.sezione.componenti );	
			
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});		
	}
 	
	this.controlloSuperati = function(){
		console.log('GestioneSezione-23-GestioneDatiFinaliScuolaCtrl.controlloSuperati()'); 
		var fieldErrors =[];

		var $componteDatiFinaliScuola = $scope.modelGestioneSezione.sezione.componenti[0]; // ? DEVO FARE COSI
		
		commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'_dataRatificaAttoIndirizzoDirigente', value : $componteDatiFinaliScuola.dataRatificaAttoIndirizzoDirigente });
		commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'_dataPredisposizionePTOFCollegioDocenti', value : $componteDatiFinaliScuola.dataPredisposizionePTOFCollegioDocenti });
		commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'_dataApprovazioneConsiglioDocenti', value : $componteDatiFinaliScuola.dataApprovazioneConsiglioDocenti });
		
	 	commonsUtilityPtofServicesFactory.testDate( { fieldErrors:fieldErrors,    field :'_dataRatificaAttoIndirizzoDirigente', value : $componteDatiFinaliScuola.dataRatificaAttoIndirizzoDirigente});
		
 	    commonsUtilityPtofServicesFactory.testDate( { fieldErrors:fieldErrors,  field :'_dataPredisposizionePTOFCollegioDocenti', value : $componteDatiFinaliScuola.dataPredisposizionePTOFCollegioDocenti});

 	    commonsUtilityPtofServicesFactory.testDate( { fieldErrors:fieldErrors,  field :'_dataApprovazioneConsiglioDocenti', value : $componteDatiFinaliScuola.dataApprovazioneConsiglioDocenti});

 	    commonsUtilityPtofServicesFactory.testDateLower( { fieldErrors:fieldErrors,  field :'_dataPredisposizionePTOFCollegioDocenti', dataStart : $componteDatiFinaliScuola.dataRatificaAttoIndirizzoDirigente, dataEnd : $componteDatiFinaliScuola.dataPredisposizionePTOFCollegioDocenti, paramsMessage : ['Data predisposizione PTOF Collegio dei Docenti','Data ratifica atto di indirizzo Dirigente Scolastico']} );
 	    commonsUtilityPtofServicesFactory.testDateLower( { fieldErrors:fieldErrors,  field :'_dataApprovazioneConsiglioDocenti', dataStart : $componteDatiFinaliScuola.dataRatificaAttoIndirizzoDirigente, dataEnd : $componteDatiFinaliScuola.dataApprovazioneConsiglioDocenti,paramsMessage : ['Data approvazione Consiglio d’Istituto','Data ratifica atto di indirizzo Dirigente Scolastico']} );
 	    commonsUtilityPtofServicesFactory.testDateLower( { fieldErrors:fieldErrors,  field :'_dataApprovazioneConsiglioDocenti', dataStart : $componteDatiFinaliScuola.dataPredisposizionePTOFCollegioDocenti, dataEnd : $componteDatiFinaliScuola.dataApprovazioneConsiglioDocenti, paramsMessage : ['Data approvazione Consiglio d’Istituto','Data predisposizione PTOF Collegio dei Docenti']} );
 	    
		if (fieldErrors.length	>0){
			var sms = {
	        		 title :"ATTENZIONE",
	        		 fieldErrors : fieldErrors , 
	        		 type :"KO"
	        };
			$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
			return false;
	     } 
	
	    return true;
	}
	
	// SALVA DELLE SEZIONE
	this.salva = function (){
		console.log('GestioneSezione-23-GestioneDatiFinaliScuolaCtrl->salva');
		
		 if( this.controlloSuperati() ){
			 
			// TRA TESTO ALLEGATO e TABELLA almeno uno sia valoriazzarto
			var almenoUno = false, tipoCompoPresenti = { editor:0, allegati:0 , combo:0 , multibox:0 }, exit , fieldErrors= [] ;
	
			// PASSIAMO IL CONTROLLI FORMALI 
			$scope.salvaConConferma( function(){ 
				$scope.modelGestioneSezione.form.componenti =  $scope.modelGestioneSezione.sezione.componenti;
				gestioneDocServicesFactory.salvaSezione( $scope.modelGestioneSezione.form )
				.success(function (data, status, config, headers) {
					$scope.gotoIndiceSezione();
				})
				.error(function (data, status, config, headers) {
					console.log('Some error occurred!'); 
				});
				
			});
		 }
	}
	
	init();
})