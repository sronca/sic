'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_28_fabbisogno_potenziamento', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-28-FabbisognoPotenziamentoCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-28-FabbisognoPotenziamentoCtrl');
 
	// utilita per associare tabella componente
	this.getTabella= function( keyComponente ) {
		var out;
		$.each( $scope.vm.tabella , function (index, item ){
			if ( item.key == keyComponente ){
				out = item;
			}
		});	
		return out;
	}
  
	
	var tabTmpl = {
			btnotcanc : 'T', btnotadd :'T',
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : {
				colsGroupHead : [
						            { title:'Tipologia Posti nel triennio' ,cl:'' , span : 6}  
							    ],
				colsHead : [
						    { title:'Comuni' ,cl:'' , span : 3} ,
						    { title:'Sostegno' ,cl:'' , span : 3} 
					    ],
				cols : [
				        { title:'-',data:'postiComuniPrimoAnno',cl:''} ,
				        { title:'-',data:'postiComuniSecondoAnno',cl:''} ,
				        { title:'-',data:'postiComuniTerzoAnno',cl:''} ,
				        { title:'-',data:'postiSostegnoPrimoAnno',cl:''} ,
				        { title:'-',data:'postiSostegnoSecondoAnno',cl:''} ,
				        { title:'-',data:'postiSostegnoTerzoAnno',cl:''} ,
//				        { title:'Motivazione',data:'motivazione',cl:''} ,
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
	}, tabMMTmpl = {
			btnotcanc : 'T', btnotadd :'T',
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : {
				colsHead : [
				            { title:'' ,cl:'fillBlankThTableSmallLeft' , span : 1},
						    { title:'Posti di Potenziamento' ,cl:'' , span : 3} ,
					    ],
				cols : [
				        { title:'Classe Concorso',data:'classeConcorso',cl:''} ,
				        { title:'-',data:'postiPotenziamentoPrimoAnno',cl:''} ,
				        { title:'-',data:'postiPotenziamentoSecondoAnno',cl:''} ,
				        { title:'-',data:'postiPotenziamentoTerzoAnno',cl:''} ,
//				        { title:'Motivazione',data:'motivazione',cl:''} ,
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
	}, tabMMSostegnoTmpl = {
			btnotcanc : 'T', btnotadd :'T',
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : {
				colsHead : [
						    { title:'Posti di Potenziamento' ,cl:'' , span : 3} ,
					    ],
				cols : [
				        { title:'-',data:'postiSostegnoPrimoAnno',cl:''} ,
				        { title:'-',data:'postiSostegnoSecondoAnno',cl:''} ,
				        { title:'-',data:'postiSostegnoTerzoAnno',cl:''} ,
//				        { title:'Motivazione',data:'motivazione',cl:''} ,
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
	}
	
	$scope.vm = {
	    attribForm : {},
	    form : {},
		tabella : []  
	}
	
	
	var init  = function () {
	 	var tempForm =  { keyDocumento : $scope.modelGestioneSezione.form.keyDocumento ,  keySezione : $scope.modelGestioneSezione.form.keySezione  } ;
		gestioneDocServicesFactory.initFormSezione( tempForm )
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			$scope.modelGestioneSezione.sezione = data.attribForm.sezione;   
			$scope.modelGestioneSezione.form.componenti = jQuery.extend(true,{}, data.attribForm.sezione.componenti );
			
			var id_fai = 0;
			$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
				if (   componente.tipoComponente == 'S_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE' ||  
					   componente.tipoComponente == 'S_FABBISOGNO_POSTI_POTENZIAMENTO_MM' ||
					   componente.tipoComponente == 'S_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SOSTEGNO'){
//					
//					var tabella =  ( componente.tipoComponente == 'S_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE' ) ? 
//									 jQuery.extend(true,{}, tabTmpl) : 
//							    	 jQuery.extend(true,{}, tabMMTmpl); 
//									 
					var tabella = undefined;
					
					switch (componente.tipoComponente) {
					    case 'S_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE':
					    	tabella = jQuery.extend(true,{}, tabTmpl);
					        break;				 
					    case 'S_FABBISOGNO_POSTI_POTENZIAMENTO_MM':
					    	tabella = jQuery.extend(true,{}, tabMMTmpl);
					        break;
					    case 'S_FABBISOGNO_POSTI_POTENZIAMENTO_MM_SOSTEGNO':
					    	tabella = jQuery.extend(true,{}, tabMMSostegnoTmpl);
					        break;
					}        
	
					id_fai++ ;
					tabella.index = id_fai;
					tabella.key = componente.key;
					tabella.titolo = componente.nome;
					tabella.tabella.items = componente.items;
					
					var startCol = 0;
					
					if (componente.tipoComponente == 'S_FABBISOGNO_POSTI_POTENZIAMENTO_MM'){
						startCol = 1;
					}
					tabella.tabella.cols[startCol].title = componente.labelAnno0;
					tabella.tabella.cols[startCol + 1].title = componente.labelAnno1;
					tabella.tabella.cols[startCol + 2].title = componente.labelAnno2;
					
					if (componente.tipoComponente == 'S_FABBISOGNO_POSTI_POTENZIAMENTO_AA_EE'){
						tabella.tabella.cols[startCol + 3].title = componente.labelAnno0;
						tabella.tabella.cols[startCol + 4].title = componente.labelAnno1;
						tabella.tabella.cols[startCol + 5].title = componente.labelAnno2;
					}
					
					tabella.componente = jQuery.extend(true,{}, componente );
					tabella.form =  tempForm;
					$scope.vm.tabella.push( {
						tab:  tabella ,
						key : componente.key,
						id : id_fai
				    }) ;
					
				}  
				
			});
			
			
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