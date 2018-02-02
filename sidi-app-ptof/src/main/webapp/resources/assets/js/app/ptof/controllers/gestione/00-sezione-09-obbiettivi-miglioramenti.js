'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_09_obbiettivi', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-09-ObbiettiviMiglioramentoCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-01IndiceCtrl->');
 
	$scope.vm = {};
	
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
 	
	// SALVA DELLE SEZIONE
	this.salva = function (){
		console.log('GestioneSezione-ObbiettiviCtrl->salva');
		
		// TRA TESTO ALLEGATO e TABELLA almeno uno sia valoriazzarto
		var almenoUno = false, tipoCompoPresenti = { editor:0, allegati:0 , combo:0 , multibox:0 }, exit , fieldErrors= [] ;

		// COMPONENTI VARIABILI SCOMMENTARE SE SI DECIDE DI GOVERBARE DA DB
		$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
		    
			if (  componente.tipoComponente == 'TEXTEDITOR'  ) {
				tipoCompoPresenti.editor ++;
				if ( componente.valore ) {
					almenoUno = true;
				}
				if (  componente.obbligatorio  && 
						   ( !componente.valore ||  componente.valore < APPGLOBALCONSTANT.TEXTAREA_MIN_SIZE.size ) ) {
							var sms = {
					        		 title :"ATTENZIONE",
					        		 text : APPGLOBALCONSTANT.TEXTAREA_MIN_SIZE.messaggioErrore  , 
					        		 type :"KO"
					        };
							$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
							exit = true;
							return;
				}
			} ;
		})

		if (exit) {
			
			if ( fieldErrors.length>0  ) {
				var sms = {
		        		 title :"ATTENZIONE",
		        		 fieldErrors : fieldErrors , 
		        		 type :"KO"
		        };
				$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
			}
			
			return ;
		}
 		
		
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
	
	init();
})