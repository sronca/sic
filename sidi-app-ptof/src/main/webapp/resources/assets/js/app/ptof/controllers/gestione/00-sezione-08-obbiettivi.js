'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_08_obbiettivi', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-08-ObbiettiviCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-01IndiceCtrl->');
 
	$scope.vm = {
	    attribForm : {
	    	prioritaL : [{label :'altissil',value :'alta'}],
	    	obbiettiviL : [{label :'obbiettivo',value :'ob1'},{label :'obbiettivo3',value :'ob3'}]
	    },
	    form : {
	    	obbiettiviL : [], // TIPOLOGIA OBBIETTIVI
	    	prioritaL :[]// TITOLOGICA PRIORITA
	    },
		tabella1 : {
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				        { title:'Obiettivi',data:'obbiettivo.label',cl:''},
				        { title:'Finalit\xE0',data:'finalita',cl:''} ,
				        { title:'Benefici',data:'benefici',cl:''} ,
				        { title:'Priorit\xE0',data:'priorita.label',cl:''},
				        { title:'',data:'key',cl:''} ],
				items : []
			},
			loadDialog : function (key){
			   $scope.vm.form.obbiettiviL = 	[];
	    	   
			   // RIMUOVO GLI OBBIETTIVI UTILIZZATI
			   $.each ( $scope.vm.attribForm.obbiettiviL , function (jj,ob){
	    		var flag = true;   
	    	    $.each ( $scope.vm.tabella1.tabella.items , function (j,e){
					if ( e.obbiettivo && e.obbiettivo.value == ob.value ){
						flag= false;
					}	
				})
				if ( flag) {
					$scope.vm.form.obbiettiviL.push ( ob );
				}
	    	   });	
	    	   
	    	   if ( $scope.vm.form.obbiettiviL.length==0 ){
	    		   var sms = {
			        		 title :"ATTENZIONE",
			        		 text : "Tutti gli obbiettivi sono stati inseriti." , 
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

				commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item1_benefici', value : this.newItem.benefici });

				commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item1_finalita', value : this.newItem.finalita });

				commonsUtilityPtofServicesFactory.testCombo( { fieldErrors:fieldErrors,o:'o' , field :'item1_priorita', value : this.newItem.priorita });

				commonsUtilityPtofServicesFactory.testCombo( { fieldErrors:fieldErrors,o:'o' , field :'item1_obbiettivi', value : this.newItem.obbiettivo });

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
	     }, // TABELLA 2 (altri obbiettivi ) init
		 tabella2 : {
				form : {},
				componente : {},
				key : {},
				titolo : '',
				tabella : { 
					cols : [{ title:'Obiettivi',data:'obbiettivo.label',cl:''},
					        { title:'Finalit\xE0',data:'finalita',cl:''} ,
					        { title:'Benefici',data:'benefici',cl:''} ,
					        { title:'Priorit\xE0',data:'priorita.label',cl:''},
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

					commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item2_benefici', value : this.newItem.benefici });

					commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item2_finalita', value : this.newItem.finalita });

					commonsUtilityPtofServicesFactory.testCombo( { fieldErrors:fieldErrors,o:'o' , field :'item2_priorita', value : this.newItem.priorita });

					commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item2_obbiettivi', value : this.newItem.obbiettivo ? this.newItem.obbiettivo.label :this.newItem.obbiettivo   });

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
					this.newItem.obbiettivo.value = this.newItem.obbiettivo.label;
					
				    return true;
				    
				},
				newItem :{}
		     }// TABELLA 2 end
     	    
	}
	
	
	var init  = function () {
	 	var tempForm =  { keyDocumento : $scope.modelGestioneSezione.form.keyDocumento ,  keySezione : $scope.modelGestioneSezione.form.keySezione  } ;
		gestioneDocServicesFactory.initFormSezione( tempForm )
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			$scope.modelGestioneSezione.sezione = data.attribForm.sezione;   
			$scope.modelGestioneSezione.form.componenti = jQuery.extend(true,{}, data.attribForm.sezione.componenti );
			
			$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
				if (   componente.tipoComponente == 'S_OBBIETTIVI_FORMATIVI'    ){
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
				if (   componente.tipoComponente == 'S_OBBIETTIVI_FORMATIVI_ALTRI'    ){
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
			
			
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
		
		ptofCommonsServicesFactory.getTipologia({tipologica :{tipo:'PRIORITA'}})
		.success(function (data, status, config, headers) {
			$scope.vm.attribForm.prioritaL = data.attribForm.PRIORITA;	
		})
		
		ptofCommonsServicesFactory.getTipologia({tipologica :{tipo:'OBBIETTIVI'}})
		.success(function (data, status, config, headers) {
			$scope.vm.attribForm.obbiettiviL = data.attribForm.OBBIETTIVI;	
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
			} ;
		})

		// COMPONETI SPECIFICI
		if ( $scope.vm.tabella1.tabella.items.length==0 && 
				$scope.vm.tabella2.tabella.items.length==0 ) {
			// DEVE INSERIRE ALEMENO UN OBBIETTIVO 
			var sms = {
	        		 title :"ATTENZIONE",
	        		 text : "Inserire almeno un obbiettivo."  , 
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
		
		if ( $scope.modelGestioneSezione.sezione.obbInsAlmenoUnCom && !almenoUno ) {
			    
				var sms = {
		        		 title :"ATTENZIONE",
		        		 text : "Inserito almeno uno tra Testo e Allegato."  , 
		        		 type :"KO"
		        };
				$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
				return 
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