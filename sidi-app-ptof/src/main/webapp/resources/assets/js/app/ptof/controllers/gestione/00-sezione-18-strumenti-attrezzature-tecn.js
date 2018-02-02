'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_18_strumenti_attrezzature_tecn', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-18-GestioneStrumentiAttrezzatureTecnCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-18');
 
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
				        { title:'Strumenti e attrezzature',data:'strumentiAttrezzature',cl:''},
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
					 field : ['strumentiAttrezzature', 
					          'descrizione']});
		 		
		 		commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o',  field :'_obiettivi', value : this.newItem.areaTematicaPNSD});
				 
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
	     }, // TAB 1
	     tabella2 : {
				form : {},
				componente : {},
				key : {},
				titolo : '',
				tabella : { 
					cols : [   
					        { title:'Dotazioni Multimediali',data:'dotazioniMultimediali',cl:''},
					        { title:'Numero',data:'numero',cl:''},
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
						 field : ['dotazioniMultimediali', 
						          'numero']});
			 		
			 		commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o',  field :'_t2_obiettivi', value : this.newItem.areaTematicaPNSD});
					 
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
			var temp = false , temp2 = false;
			$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
				// S_PROGRAMMAZIONE_FORMAZIONE_DOC 		S_PROGRAMMAZIONE_FORMAZIONE_AMM 	S_PROGRAMMAZIONE_FORMAZIONE_TEC  S_PROGRAMMAZIONE_FORMAZIONE_AUS 
				if (  'S_STRUMENTI_ATTREZZATURE_TECNOLOGIA'==componente.tipoComponente  ){
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
				if (  'S_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE'==componente.tipoComponente  ){
					$scope.vm.tabella2.key = componente.key;
					$scope.vm.tabella2.titolo = componente.nome;
					$scope.vm.tabella2.tabella.items = componente.items;

					if ( !angular.isDefined ( $scope.vm.tabella2.btnotadd )
							&& !componente.itemsAggiungibili ){
						$scope.vm.tabella2.btnotadd =  'T';	
					}

					
					$scope.vm.tabella2.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella2.form =  tempForm;
					temp2 = true
				}			
			});
			if ( !temp || !temp2 ) {
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente S_STRUMENTI_ATTREZZATURE_TECNOLOGIA and S_DOTAZIONI_DELL_ISTITUTO_PRINCIPALE ' + data);
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