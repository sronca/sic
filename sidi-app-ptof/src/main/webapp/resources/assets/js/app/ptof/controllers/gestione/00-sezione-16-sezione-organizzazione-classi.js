'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_16_organizzazione_classi', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-16-GestioneOrganizzazioneClassiCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-16-GestioneOrganizzazioneClassiCtrl');
 
	$scope.vm = {
	    attribForm : {
	    	ruoliL : []
	    },
	    form : {
	    },
		tabella1 : {
			btnotcanc : 'T',btnotadd :'T',	
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				      //  { title:'Tipologia Posti', data:'tipologiaPosti',cl:''},
				      //  { title:'Descrizione', data:'descrizione',cl:''},
				        { title:'Sezione Orario Normale', data:'sezioneOrarioNormale',cl:''},
				        { title:'Sezione Orario Ridotto', data:'sezioneOrarioRidotto',cl:''},
				        	  
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
				console.log('controlliSuperati!'); 
				return true;
			},
			newItem :{}
	     }, // TABELLA 1
	     tabella2 : {
			btnotcanc : 'T',btnotadd :'T',	
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				colsHead : [ 
						    { title:'Classi Tempo  Normale' ,cl:'' , span : 6},
					        { title:'Classi Tempo  Pieno',cl:'', span :6},
					    ],
						cols : [
						        { title:'I', data:'classiAlunniTempoNormaleI',cl:''},
						        { title:'II', data:'classiAlunniTempoNormaleII',cl:''},
						        { title:'III', data:'classiAlunniTempoNormaleIII',cl:''},
						        { title:'IV', data:'classiAlunniTempoNormaleIV',cl:''},
						        { title:'V', data:'classiAlunniTempoNormaleV',cl:''},
						        { title:'PLURICL', data:'classiAlunniTempoNormalePLURICL',cl:''},
						        
						        { title:'I', data:'classiAlunniTempoPienoI',cl:''},
						        { title:'II', data:'classiAlunniTempoPienoII',cl:''},
						        { title:'III', data:'classiAlunniTempoPienoIII',cl:''},
						        { title:'IV', data:'classiAlunniTempoPienoIV',cl:''},
						        { title:'V', data:'classiAlunniTempoPienoV',cl:''},
						        { title:'PLURICL', data:'classiAlunniTempoPienoPLURICL',cl:''},

						        { title:'',data:'key',cl:''} ],

				items : []
			},
			loadDialog : function (key){ 
				console.log('loadDialog!'); 
			},
			modifica : function (key){
				console.log('modifica!'); 
			},
			cancella : function (items){
				console.log('cancella!');
				
			},
			controlliSuperati : function (key){
				console.log('controlliSuperati!'); 
				return true;
			    
			},
			newItem :{}
	    }, // TABELLA 2
	     tabella3 : {
			btnotcanc : 'T',btnotadd :'T',	
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : {
				colsHead : [ 
				    { title:'Totali classi tempo normale' ,cl:'' , span : 3},
			        { title:'Totali classi tempo prolungato',cl:'', span : 3},
			    ],
				cols : [
				        { title:'I', data:'tempoNormaleTotaliClassiI',cl:''},
				        { title:'II', data:'tempoNormaleTotaliClassiII',cl:''},
				        { title:'III', data:'tempoNormaleTotaliClassiIII',cl:''},
				        { title:'I', data:'tempoProlungatoTotaliClassiI',cl:''},
				        { title:'II', data:'tempoProlungatoTotaliClassiII',cl:''},
				        { title:'III', data:'tempoProlungatoTotaliClassiIII',cl:''},
				        { title:'',data:'key',cl:''} ],
				items : []
			},
			loadDialog : function (key){ 
				console.log('loadDialog!'); 
			},
			modifica : function (key){
				console.log('modifica!'); 
			},
			cancella : function (items){
				console.log('cancella!');
				
			},
			controlliSuperati : function (key){
				console.log('controlliSuperati!'); 
				return true;
			    
			},
			newItem :{}
	    }, // TABELLA 4
	     tabella4 : {
				btnotcanc : 'T',btnotadd :'T',	
				form : {},
				componente : {},
				key : {},
				titolo : '',
				tabella : {
					colsHead : [ 
							    { title:'' ,cl:'fillBlankThTableSmallLeft' , span : 1},
						        { title:'Classi',cl:'', span : 6},
						    ],
 					cols : [
					        { title:'Indirizzo', data:'indirizzo',cl:''},
					        { title:'I', data:'classiI',cl:''},
					        { title:'II', data:'classiII',cl:''},
					        { title:'III', data:'classiIII',cl:''},
					        { title:'IV', data:'classiVI',cl:''},
					        { title:'V', data:'classiV',cl:''},
					        { title:'VI', data:'classiVI',cl:''},
					        { title:'',data:'key',cl:''} ],
					items : []
				},
				loadDialog : function (key){ 
					console.log('loadDialog!'); 
				},
				modifica : function (key){
					console.log('modifica!'); 
				},
				cancella : function (items){
					console.log('cancella!');
					
				},
				controlliSuperati : function (key){
					console.log('controlliSuperati!'); 
					return true;
				    
				},
				newItem :{}
		    } // TABELLA 3
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
				
				if ( componente.tipoComponente == 'S_ORGANIZZAZIONE_CLASSI_AA' ){
					temp = true
					$scope.vm.tabella1.key = componente.key;
					$scope.vm.tabella1.titolo = componente.nome;
					$scope.vm.tabella1.tabella.items = componente.items;
					$scope.vm.tabella1.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella1.form =  tempForm;
				} else if ( componente.tipoComponente == 'S_ORGANIZZAZIONE_CLASSI_EE' ){
					temp = true
					$scope.vm.tabella2.key = componente.key;
					$scope.vm.tabella2.titolo = componente.nome;
					$scope.vm.tabella2.tabella.items = componente.items;
					$scope.vm.tabella2.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella2.form =  tempForm;
				} else if ( componente.tipoComponente == 'S_ORGANIZZAZIONE_CLASSI_MMI' ){
					temp = true
					$scope.vm.tabella3.key = componente.key;
					$scope.vm.tabella3.titolo = componente.nome;
					$scope.vm.tabella3.tabella.items = componente.items;
					$scope.vm.tabella3.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella3.form =  tempForm;
				} else if ( componente.tipoComponente == 'S_ORGANIZZAZIONE_CLASSI_MMII' ){
					temp = true
					$scope.vm.tabella4.key = componente.key;
					$scope.vm.tabella4.titolo = componente.nome;
					$scope.vm.tabella4.tabella.items = componente.items;
					$scope.vm.tabella4.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella4.form =  tempForm;
				}
				
			
			});
			if ( !temp ) {
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente    ' + data);
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