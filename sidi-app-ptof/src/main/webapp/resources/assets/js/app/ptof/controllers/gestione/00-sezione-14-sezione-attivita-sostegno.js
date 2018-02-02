'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_14_attivita_sostegno', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-14-GestioneAttivitaSostegnoCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-11-GestioneProgettiCVCtrl');
 
	$scope.vm = {
	    attribForm : {
	    },
	    form : {
	    },
		tabella1 : {
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				        { title:'Contenuti dell\'attivita\' di sostegno',data:'contenutiAttivitaSostegno',cl:''},
				        { title:'',data:'key',cl:''} ],
				items : []
			},
			loadDialog : function (key){
			   
	    	   			    
	    	   return true;
			},
			modifica : function (key){
				console.log('modifica!'); 
			},
			cancella : function (items){
				console.log('cancella!');
				
			},
			controlliSuperati : function (key){
				console.log('aggiungi!'); 
				var fieldErrors =[];
				 
				 commonsUtilityPtofServicesFactory.testTextAreas( { fieldErrors:fieldErrors, o:'o', 
					 that : this.newItem ,
					 field : ['contenutiAttivitaSostegno',
					          'metodologieUtilizzate',
					          'coopServiziSSAssocSettore',
					          'aspettiSupportoLogistico']});
	 	   			 	 
		 	     commonsUtilityPtofServicesFactory.textMaxSizeTextArea (   { idTab:'addDalogTab1' ,  fieldErrors:fieldErrors }  );
		 	     
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
			    
			},
			newItem :{}
	     } 
    };// VM
	 
	
	var init  = function () {
	 	var tempForm =  { keyDocumento : $scope.modelGestioneSezione.form.keyDocumento ,  keySezione : $scope.modelGestioneSezione.form.keySezione  } ;
		gestioneDocServicesFactory.initFormSezione( tempForm )
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			$scope.modelGestioneSezione.sezione = data.attribForm.sezione;   
			$scope.modelGestioneSezione.form.componenti = jQuery.extend(true,{}, data.attribForm.sezione.componenti );
			var temp = false;
			$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
				
				if (   componente.tipoComponente == 'S_ATTIVITA_SOSTEGNO'    ){
					temp = true
					$scope.vm.tabella1.key = componente.key;
					$scope.vm.tabella1.titolo = componente.nome;
					$scope.vm.tabella1.tabella.items = componente.items;
					
					if ( !angular.isDefined ( $scope.vm.tabella1.btnotadd )
							&& !componente.itemsAggiungibili ){
						$scope.vm.tabella1.btnotadd =  'T';	
					}

					$scope.vm.tabella1.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella1.form =  tempForm;
				}
			
			});
			if ( !temp ) {
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente S_ATTIVITA_SOSTEGNO  ' + data);
			}
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
		
		ptofCommonsServicesFactory.getTipologia({tipologica :{tipo:'AMBITI'}})
		.success(function (data, status, config, headers) {
			$scope.vm.attribForm.ambitiL = data.attribForm.AMBITI;	
		})
		
	}
 	
	// SALVA DELLE SEZIONE
	this.salva = function (){
		console.log('GestioneSezione-ObbiettiviCtrl->salva');
		var fieldErrors =[];

		 commonsUtilityPtofServicesFactory.textMaxSizeTextArea (   { idTab:'addDalogTab2' ,  fieldErrors:fieldErrors }  );
	 	  
		 
		 
			if (fieldErrors.length	>0){
				var sms = {
		        		 title :"ATTENZIONE",
		        		 fieldErrors : fieldErrors , 
		        		 type :"KO"
		        };
				$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
				return false;
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