'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_30_fabbisogno_connesso_progetto', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-30-GestioneFabbisognoConnessoProgettoCtrl', function ($scope, $rootScope, $window , $location , $base64, $timeout ,gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-30-GestioneFabbisognoConnessoProgettoCtrl');
 
	$scope.vm = {
	    attribForm : {
	    	
	    },
	    form : {
	    	tipoProgettiScuolaL : [ ],
	    	denominazioneProgettiScuolaL : [ ]
	    },
		tabella1 : {
			btnotadd : 'T',
			statoDocumento : '',
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				        { title:'Tipologia Progetto',data:'tipoProgetti.label',cl:''} ,
				        { title:'Denominazione',data:'denominazioneProgettiScuola.label',cl:''} ,
				        { title:'Codice Meccanografico',data:'codiceMeccanograficoPlesso',cl:''} ,
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

			 	commonsUtilityPtofServicesFactory.testCombo( { fieldErrors:fieldErrors,o:'o' , field :'item1_tipoProgetti', value : this.newItem.tipoProgetti});
			 	commonsUtilityPtofServicesFactory.testCombo( { fieldErrors:fieldErrors,o:'o' , field :'item1_denominazioneProgettiScuola', value : this.newItem.denominazioneProgettiScuola});
			 	commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item1_nomeBeneServizio', value : this.newItem.nomeBeneServizio });
			 	commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item1_descrizioneBeneServizio', value : this.newItem.descrizioneBeneServizio });
			 	commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item1_numeroDocentiATACoinvolti', value : this.newItem.numeroDocentiATACoinvolti });
			 	commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item1_importoStimato', value : this.newItem.importoStimato });
				
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
	
	$scope.$watch("vm.tabella1.newItem.tipoProgetti", function(newValue, oldValue){
		if ( newValue && newValue!=null && ($scope.vm.tabella1.dialogAction=='I' ) ) { 
			// IN MOFICA I CAMPI SONO READ ONLY
			   ptofCommonsServicesFactory.getTipologia(
					    {  tipologica :
					       { tipo : 'DENOMINAZIONE_PROGETTI_SCUOLA' , 
					    	 itemToFilter : { value : newValue.value , tipo : 'TIPOPROGETTI_SCUOLA' } 
					       }
					    }).success(function (data, status, config, headers) {
					      // workaround per aggiornanare i dati	
					      $timeout(function() {
					    	  $scope.vm.tabella1.newItem.denominazioneProgettiScuola=null;
					    	  $scope.vm.form.denominazioneProgettiScuolaL.length = 0;
					    	  $scope.$apply();
					    	  $scope.vm.form.denominazioneProgettiScuolaL = data.attribForm.DENOMINAZIONE_PROGETTI_SCUOLA;
					    	   
							  // RIMUOVO GLI AMBITI UTILIZZATI
					    	  var elemDisponibili = [];
							  $.each ( $scope.vm.form.denominazioneProgettiScuolaL , function (jj,ob){
							    	var flag = true;
						    	    $.each ( $scope.vm.tabella1.tabella.items , function (j,e){
										if ( e.denominazioneProgettiScuola && e.denominazioneProgettiScuola.value == ob.value ){
											flag = false;
										}	
									})
									
									if ( flag) {
										elemDisponibili.push ( ob );
									}
					    	  });	
					    	   
							  $scope.vm.form.denominazioneProgettiScuolaL = elemDisponibili;
							   
					      },0);
						 
					});	
	    } else {
	    	$scope.vm.form.denominazioneProgettiScuolaL =[];
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
				
				if (   componente.tipoComponente == 'S_FABBISOGNO_CONNESSO_PROGETTO'    ){
					
					ptofCommonsServicesFactory.getTipologia({tipologica :{tipo:'TIPOPROGETTI_SCUOLA'}})
					.success(function (data, status, config, headers) {
						$scope.vm.form.tipoProgettiScuolaL = data.attribForm.TIPOPROGETTI_SCUOLA;	
					})
					
					$scope.vm.tabella1.statoDocumento = componente.statoDocumento;
					$scope.vm.tabella1.key = componente.key;
					$scope.vm.tabella1.titolo = componente.nome;
					$scope.vm.tabella1.tabella.items = componente.items;

					$scope.vm.tabella1.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella1.form =  tempForm;
					temp = true
				}
			
			});
			if ( !temp ) {
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente S_FABBISOGNO_CONNESSO_PROGETTO ' + data);
			}
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
	}
 	
	// SALVA DELLE SEZIONE
	this.salva = function (){
		console.log('Gestione-GestioneFabbisognoConnessoProgettoCtrl-->salva');
		
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