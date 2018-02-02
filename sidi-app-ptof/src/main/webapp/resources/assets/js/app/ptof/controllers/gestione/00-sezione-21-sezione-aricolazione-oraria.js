'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_21_articolazione_oraria', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-21-ArticolazioneOrariaCtrl', function ($scope, $rootScope, $window , $location , $base64, $timeout ,gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-21-ArticolazioneOrariaCtrl');
 
	$scope.vm = {
	    attribForm : {
	    },
	    form : { 
	     }, 
	    tabella1 : {// S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO
	    	    btnotcanc : 'T',btnotadd :'T',btnotmod :'T', btnotvis :'T',	
	    	    colPresenzaClassi : false,
				form : {},
				componente : {},
				key : {},
				titolo : '',
				tabella : { 
					cols : [
					        { title:'Indirizzo Studio',data:'indirizzoStudio',cl:''},
					        { title:'Descrizione',data:'descrizione',cl:''} /*,
					        { title:'Anno Scolastico inizio erogazione indirizzo',data:'annoScolasticoInizioErogazioneIndirizzo',cl:''} ,
					        { title:'Anno Scolastico fine erogazione indirizzo',data:'annoScolasticoFineErogazioneIndirizzo',cl:''}*/ 
					        ],
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
				    return true;
				},
				newItem :{}
	    }, // tabella 1
	    tabella2 : { // S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO
	        btnotcanc : 'T',btnotadd :'T',btnotmod :'T',	
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				        { title:'Anno Scolastico',data:'annoScolastico',cl:''},
				        { title:'Indirizzo Studio',data:'descrizioneIndirizzoScolastico',cl:''} ,
				        { title:'Piano Studio',data:'descrizionePianoStudio',cl:''} ,
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
			    return true;
			},
			newItem :{}
       } // tabella 2
	   , tabella3 : { //S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA
		    btnotcanc : 'T',btnotadd :'T',btnotmod :'T', btnotvis :'T',	
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				        { title:'Descrizione Tempo Scuola',data:'descrizioneTempoScuola',cl:''},
				        //{ title:'',data:'key',cl:''} 
				        ],
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
			    return true;
			},
			newItem :{}
       } // tabella 1
	    
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
				
				if (   componente.tipoComponente == 'S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO'    ){
					$scope.vm.tabella1.key = componente.key;
					$scope.vm.tabella1.titolo = componente.nome;
					$scope.vm.tabella1.tabella.items = componente.items;
					$scope.vm.tabella1.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella1.form =  tempForm;
					 
					temp = true
				}
				
				if (   componente.tipoComponente == 'S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO'    ){
					$scope.vm.tabella2.key = componente.key;
					$scope.vm.tabella2.titolo = componente.nome;
					$scope.vm.tabella2.tabella.items = componente.items;
					$scope.vm.tabella2.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella2.form =  tempForm;

					temp = true
				}
				
				if (   componente.tipoComponente == 'S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA'    ){
					$scope.vm.tabella3.key = componente.key;
					$scope.vm.tabella3.titolo = componente.nome;
					$scope.vm.tabella3.tabella.items = componente.items;
					$scope.vm.tabella3.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella3.form =  tempForm;

					temp = true
				}
			});
			if ( !temp ) {
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente S_PROGETTI_CURRICULARI_EXSTRA ' + data);
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