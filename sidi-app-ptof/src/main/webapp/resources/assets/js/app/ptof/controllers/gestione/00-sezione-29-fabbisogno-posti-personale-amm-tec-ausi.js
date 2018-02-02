'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_29_fabbisogno_posti_personale_amm_tec_ausi', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-29-GestioneFabbisognoPostiPersonaleAmmTecAusiCtrl', function ($scope, $rootScope, $window , $location , $base64, $timeout ,gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-29-GestioneFabbisognoPostiPersonaleAmmTecAusiCtrl');
 
	$scope.vm = {
	    attribForm : {
	    },
	    form : {
	    },
		tabella1 : {
			form : {},
			btnotadd : 'T',
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				colsHead : [ 
				            { title:'',cl:'fillBlankThTableSmallLeft', span :1},
					        { title:'Posti',cl:'', span :3}
					    ],
				cols : [
				        //{ title:'Anno Scolastico',data:'annoScolastico',cl:''} ,
				        { title:'Profilo Professionale',data:'figuraProfessionale',cl:''},	
				        { title:'-',data:'numeroPostiPrimoAnnoTriennio',cl:''} ,
				        { title:'-',data:'numeroPostiSecondoAnnoTriennio',cl:''} ,
				        { title:'-',data:'numeroPostiTerzoAnnoTriennio',cl:''} ,
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

		 		//commonsUtilityPtofServicesFactory.testCombo( { fieldErrors:fieldErrors,o:'o' , field :'item1_ambito', value : this.newItem.ambito });

			 	
		 	     
		 	    
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
	
	$scope.$watch("vm.tabella1.newItem.ambito", function(newValue, oldValue){
		if ( newValue && newValue!=null && ($scope.vm.tabella1.dialogAction=='I' ) ) { 
			// IN MOFICA I CAMPI SONO READ ONLY
			   ptofCommonsServicesFactory.getTipologia(
					    {  tipologica :
					       { tipo : 'TIPOPROGETTI' , 
					    	 itemToFilter : { value : newValue.value , tipo : 'AMBITI' } 
					       }
					    }).success(function (data, status, config, headers) {
					      // workaround per aggiornanare i dati	
					      $timeout(function() {
					    	  $scope.vm.tabella1.newItem.tipologiaProgetto=null;
					    	  $scope.vm.form.tipologiaProgettoL.length = 0;
					    	  $scope.$apply();
					    	  $scope.vm.form.tipologiaProgettoL = data.attribForm.TIPOPROGETTI;
					      },0);
						 
					});	
	    } else {
	    	$scope.vm.form.tipologiaProgettoL =[];
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
				
				if (   componente.tipoComponente == 'S_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI'    ){
					$scope.vm.tabella1.key = componente.key;
					$scope.vm.tabella1.titolo = componente.nome;
					$scope.vm.tabella1.tabella.items = componente.items;
					
					var startCol = 1;
					
					$scope.vm.tabella1.tabella.cols[startCol].title = componente.labelAnno0;
					$scope.vm.tabella1.tabella.cols[startCol + 1].title = componente.labelAnno1;
					$scope.vm.tabella1.tabella.cols[startCol + 2].title = componente.labelAnno2;
					
					$scope.vm.tabella1.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella1.form =  tempForm;
					temp = true
				}
			
			});
			if ( !temp ) {
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente S_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI ' + data);
			}
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
		
	}
 	
	// SALVA DELLE SEZIONE
	this.salva = function (){
		console.log('GestioneSezione-ObbiettiviCtrl->salva');
		
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