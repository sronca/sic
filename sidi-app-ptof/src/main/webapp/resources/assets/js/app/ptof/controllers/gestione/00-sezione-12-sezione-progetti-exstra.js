'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_12_progetti_exstra', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-12-GestioneProgettiExstraCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-11-GestioneProgettiCVCtrl');
 
	$scope.vm = {
	    attribForm : {
	    	ambitoL : [ ] 
	     
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
				        { title:'Ambito',data:'ambito.label',cl:''},
				        { title:'Tipologia Progetto',data:'tipologiaProgetto.label',cl:''} ,
				        { title:'Denominazione',data:'denominazione',cl:''} ,
//				        { title:'Destinatari',data:'destinatari',cl:''},
//				        
//				        { title:'Destinatari',data:'destinatari',cl:''},
//				        { title:'Principali Contenuti',data:'principaliContenuti',cl:''},
//				        { title:'Modalita Approcci Formativi Utilizzati',data:'destinatari',cl:''},
//				        { title:'Periodo Di Svolgimento',data:'periodoDiSvolgimento',cl:''},
//				        
//				        { title:'Area Tematica PNSD',data:'areaTematicaPNSD',cl:''},
//				        { title:'Rilevanza Per Scuola',data:'rilevanzaPerScuola',cl:''},
//				        { title:'Cooperazione Con Altre Scuole',data:'cooperazioneConAltreScuole',cl:''},
//				        
//				        { title:'Cooperazione Con Altre Scuole',data:'cooperazioneConAltreScuole',cl:''},
//				        { title:'Risorse Umane Area',data:'risorseUmaneArea',cl:''},
//				        
//				        { title:'Risorse Umane Area',data:'risorseUmaneArea',cl:''},
//				        { title:'Altre Risorse Necessarie',data:'altreRisorseNecessarie',cl:''},
//				        
//				        { title:'Stato Avanzamento',data:'statoAvanzamento',cl:''},
//				        
//				        { title:'Durata Dal',data:'durataDal',cl:''},
//				        { title:'Durata Al',data:'durataAl',cl:''},
//				        { title:'Risorse Finanziare Necessarie',data:'risorseFinanziareNecessarie',cl:''},
				        
				        
				        { title:'',data:'key',cl:''} ],
				items : []
			},
			loadDialog : function (key){
			   $scope.vm.form.ambitiL = 	[];
			   $scope.vm.form.tipologiaProgettoL =[];
			   
			   // RIMUOVO GLI AMBITI UTILIZZATI
			   $.each ( $scope.vm.attribForm.ambitiL , function (jj,ob){
	    		var flag = true;   
	    	    $.each ( $scope.vm.tabella1.tabella.items , function (j,e){
					if ( e.ambiti && e.ambiti.value == ob.value ){
						flag= false;
					}	
				})
				if ( flag) {
					$scope.vm.form.ambitiL.push ( ob );
				}
	    	   });	
	    	   
	    	   if ( $scope.vm.form.ambitiL.length==0 ){
	    		   var sms = {
			        		 title :"ATTENZIONE",
			        		 text : "Tutti gli Ambiti sono stati inseriti." , 
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

		 		commonsUtilityPtofServicesFactory.testCombo( { fieldErrors:fieldErrors,o:'o' , field :'item1_ambito', value : this.newItem.ambito });

			 	commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item1_tipologiaProgetto', value : this.newItem.tipologiaProgetto ? this.newItem.tipologiaProgetto.label : '' });
				
			 	commonsUtilityPtofServicesFactory.testDate( { fieldErrors:fieldErrors,    field :'date1_durataDal', value : this.newItem.durataDal});
				
		 	    commonsUtilityPtofServicesFactory.testDate( { fieldErrors:fieldErrors,  field :'date1_durataAl', value : this.newItem.durataAl});

		 	    commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o',  field :'item1_denominazione', value : this.newItem.denominazione});

		 	    commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o',  field :'item1_destinatari', value : this.newItem.destinatari});
		 	    
		 	    commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o',  field :'_areaTematicaPNSD', value : this.newItem.areaTematicaPNSD});
		 	   
		 	    commonsUtilityPtofServicesFactory.textMaxSizeTextArea (  { idTab:'addDalogTab1' ,  fieldErrors:fieldErrors } );
		 	     
		 	    
				if (fieldErrors.length	>0){
					var sms = {
			        		 title :"ATTENZIONE",
			        		 fieldErrors : fieldErrors , 
			        		 type :"KO"
			        };
					$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
					return false;
			     } 
				/*
				 * WR
				 */
				 
				this.newItem.tipologiaProgetto.value = this.newItem.tipologiaProgetto.label;
				
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
				
				if (   componente.tipoComponente == 'S_ALTRI_PROGETTI_CURRICULARI_EXSTRA'    ){
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
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente S_ALTRI_PROGETTI_CURRICULARI_EXSTRA ' + data);
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
		/*
		if ( $scope.vm.tabella1.tabella.items.length==0  ) {
			// DEVE INSERIRE ALEMENO PROGETTO/AMBITO
			var sms = {
	        		 title :"ATTENZIONE",
	        		 text : "Inserire almeno un ambito/progetto."  , 
	        		 type :"KO"
	        };
			$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
			exit = true;
			return;
		}*/
		 
		
		
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