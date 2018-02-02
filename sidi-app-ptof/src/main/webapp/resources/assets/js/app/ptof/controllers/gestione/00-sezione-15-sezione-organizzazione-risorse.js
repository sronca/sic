'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_15_organizzazione_risorse', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-15-GestioneOrganizzazioneRisorseCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-15-GestioneOrganizzazioneRisorseCtrl');
 
	$scope.vm = {
	    attribForm : {
	    	ruoliL : []
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
				        { title:'Ruolo', data:'ruolo.label',cl:''},
				        { title:'Nome', data:'nome',cl:''},
//				        { title:'Note Ruolo', data:'noteRuolo',cl:''},
				        { title:'Responsabilita', data:'responsabilita',cl:''},
//				        { title:'Note Responsabilita\'', data:'noteResponsabilita',cl:''},
				        { title:'',data:'key',cl:''} ],
				items : []
			},
			loadDialog : function (key){
				   $scope.vm.form.ruoliL = 	[];
				   
				   // RIMUOVO GLI AMBITI UTILIZZATI
				   $.each ( $scope.vm.attribForm.ruoliL , function (jj,ob){
		    		var flag = true;   
		    	    $.each ( $scope.vm.tabella1.tabella.items , function (j,e){
						if ( e.ruolo && e.ruolo.value == ob.value ){
							flag= false;
						}	
					})
					if ( flag) {
						$scope.vm.form.ruoliL.push ( ob );
					}
		    	   });	
		    	   
		    	   if ( $scope.vm.form.ruoliL.length==0 ){
		    		   var sms = {
				        		 title :"ATTENZIONE",
				        		 text : "Tutti i ruoli sono stati inseriti." , 
				        		 type :"KO"
				        };
						$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
		    		   return false;
		    	   }
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
		 		 commonsUtilityPtofServicesFactory.testCombo( { fieldErrors:fieldErrors,o:'o' , field :'_ruolo', value : this.newItem.ruolo });
				 commonsUtilityPtofServicesFactory.testTextAreas( { fieldErrors:fieldErrors, o:'o', 
					 that : this.newItem ,
					 field : ['nome',
					          //'noteRuolo','noteResponsabilita',
					          'responsabilita']});
		 	     commonsUtilityPtofServicesFactory.textMaxSizeTextArea (   { idTab:'addDalogTab1' ,  fieldErrors:fieldErrors } );
		 	     
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
	    }, 
		tabella2 : {
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				        { title:'Ruolo', data:'ruolo.label',cl:''},
				        { title:'Nome', data:'nome',cl:''},
				        { title:'Responsabilita', data:'responsabilita',cl:''},
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
					 that : this.newItem , perFix :'_t2',
					 field : ['nome','ruolo',
					          'responsabilita']});
		 	     commonsUtilityPtofServicesFactory.textMaxSizeTextArea (  { idTab:'addDalogTab2' ,  fieldErrors:fieldErrors } );

		 		commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'_ruolo', value : this.newItem.ruolo ? this.newItem.ruolo.label :this.newItem.ruolo   });

				if (fieldErrors.length	>0){
					var sms = {
			        		 title :"ATTENZIONE",
			        		 fieldErrors : fieldErrors , 
			        		 type :"KO"
			        };
					$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
					return false;
			     } 

				// WORKAROUND : uso lo stestto oggetto OBBIETTIVO!!!
				this.newItem.ruolo.value = this.newItem.ruolo.label;
			
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
				
				if ( componente.tipoComponente == 'S_ORGANIZZAZIONE_RISORSE' ){
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
				
				if ( componente.tipoComponente == 'S_ORGANIZZAZIONE_RISORSE_ALTRI_RUOLI' ){
				 
					$scope.vm.tabella2.key = componente.key;
					$scope.vm.tabella2.titolo = componente.nome;
					$scope.vm.tabella2.tabella.items = componente.items;
					
					if ( !angular.isDefined ( $scope.vm.tabella2.btnotadd )
							&& !componente.itemsAggiungibili ){
						$scope.vm.tabella2.btnotadd =  'T';	
					}

					
					$scope.vm.tabella2.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella2.form =  tempForm;
				}			
			});
			if ( !temp ) {
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente S_ORGANIZZAZIONE_RISORSE  ' + data);
			}
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
		
		ptofCommonsServicesFactory.getTipologia({tipologica :{tipo:'RUOLI'}})
		.success(function (data, status, config, headers) {
			$scope.vm.attribForm.ruoliL = data.attribForm.RUOLI;	
		})
		
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