'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_17_programmzione_formazione', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-17-GestioneProgrammazioneFormazioneCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-11-GestioneProgettiCVCtrl');
 
	$scope.vm = {
	    attribForm : {
	    	ambitiFormativiL : [],
	    	checkSiNoL : [ {label :"SI", value:"S" },{label :"NO", value:"N" } ]
	    },
	    form : {
	    	ambitiFormativiL : []
	    },
		tabella1 : {
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				        { title:'Ambito Formativo',data:'ambitoFormativo.label',cl:''},
				        { title:'Denominazione percorso formativo',data:'denominazionePercorsoFormativo',cl:''} ,
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

		 		commonsUtilityPtofServicesFactory.testCombo( { fieldErrors:fieldErrors,o:'o' , field :'_ambitoFormativo', value : this.newItem.ambitoFormativo });
 
		 		commonsUtilityPtofServicesFactory.testTextAreas( { fieldErrors:fieldErrors, o:'o', 
					 that : this.newItem ,
					 field : ['denominazionePercorsoFormativo',
					          'obiettivi',
					          'contenuti']});
				 
		 		commonsUtilityPtofServicesFactory.testDate( { fieldErrors:fieldErrors,  o:'o',  field :'_dataInizio', value : this.newItem.dataInizio});
				
		 	    commonsUtilityPtofServicesFactory.testDate( { fieldErrors:fieldErrors, o:'o', field :'_dataFine', value : this.newItem.dataFine});

		 	    commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o',  field :'_tempiOreComplessive', value : this.newItem.tempiOreComplessive});
		 	    
		 	    commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o',  field :'_areaTematicaPNSD', value : this.newItem.areaTematicaPNSD});
 	   
		 	    commonsUtilityPtofServicesFactory.textMaxSizeTextArea (  { fieldErrors:fieldErrors } );
		 	     
		 	    
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
	
	$scope.$watch("vm.tabella1.newItem.percorsoFormativo", function(newValue, oldValue){
		if ( newValue && newValue!=null && ($scope.vm.tabella1.dialogAction=='I' ) ) { 
			// IN MOFICA I CAMPI SONO READ ONLY , 
			// RESET I CAMPI
		    $scope.vm.tabella1.newItem.collaborazioneReteScolastic=null;
		    $scope.vm.tabella1.newItem.condiviso=null;
	    }
	});
	
	
	var init  = function () {
	 	var tempForm =  { keyDocumento : $scope.modelGestioneSezione.form.keyDocumento ,  keySezione : $scope.modelGestioneSezione.form.keySezione  } ;
		gestioneDocServicesFactory.initFormSezione( tempForm )
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			$scope.modelGestioneSezione.sezione = data.attribForm.sezione;   
			$scope.modelGestioneSezione.form.componenti = jQuery.extend(true,{}, data.attribForm.sezione.componenti );
			var temp = false;
			$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
				// S_PROGRAMMAZIONE_FORMAZIONE_DOC 		S_PROGRAMMAZIONE_FORMAZIONE_AMM 	S_PROGRAMMAZIONE_FORMAZIONE_TEC  S_PROGRAMMAZIONE_FORMAZIONE_AUS 
				if (  'S_PROGRAMMAZIONE_FORMAZIONE_'==componente.tipoComponente.substr(0,componente.tipoComponente.length-3)   ){
					$scope.vm.tabella1.key = componente.key;
					$scope.vm.tabella1.titolo = componente.nome;
					$scope.vm.tabella1.tabella.items = componente.items;
					
					if ( !angular.isDefined ( $scope.vm.tabella1.btnotadd )
							&& !componente.itemsAggiungibili ){
						$scope.vm.tabella1.btnotadd =  'T';	
					}

					$scope.vm.tabella1.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella1.form =  tempForm;
					temp = true
				}
			
			});
			if ( !temp ) {
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente S_PROGRAMMAZIONE_FORMAZIONE_DOC ' + data);
			}
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
		
		ptofCommonsServicesFactory.getTipologia({tipologica :{tipo:'AMBITI_FORMATIVI'}})
		.success(function (data, status, config, headers) {
			$scope.vm.attribForm.ambitiFormativiL = data.attribForm.AMBITI_FORMATIVI;	
		})
		
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
			} else if (  componente.tipoComponente == 'ALLEGATO' ) {
				tipoCompoPresenti.allegati ++;
				if ( componente.file ) {
					almenoUno = true;
				}
				
				// NESSUN CONTROLLO PER ORA
				if (  componente.obbligatorio  && 
						   ( componente.file ||  componente.fileName ) ) {

					var sms = {
			        		 title :"ATTENZIONE",
			        		 text : "ALLEGATO OBBLIGATORIO"  , 
			        		 type :"KO"
			        };
					$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
					exit = true;
					return;
					
				}
			} 
			
		})

		// COMPONETI SPECIFICI
		if ( $scope.vm.tabella1.tabella.items.length==0   ) {
			// DEVE INSERIRE ALEMENO UN OBBIETTIVO 
			var sms = {
	        		 title :"ATTENZIONE",
	        		 text : "Inserire almeno un ambito."  , 
	        		 type :"KO"
	        };
			$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
			exit = true;
			return;
		}
		 
		
		
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