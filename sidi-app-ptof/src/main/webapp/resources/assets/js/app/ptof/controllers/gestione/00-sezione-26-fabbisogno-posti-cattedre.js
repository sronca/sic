'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_26_fabbisognocattadre', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-26-FabbisognoCattedreCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-26-FabbisognoCattedreCtrl');
 
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
				colsHead : [ 
					        { title:'Posti Comuni',cl:'', span :3}
					    ],
				cols : [
				        //{ title:'Anno Scolastico',data:'annoScolastico.label',cl:''},
				        { title:'-',data:'postiComuniPrimoAnnoTriennio',cl:''} ,
				        { title:'-',data:'postiComuniSecondoAnnoTriennio',cl:''} ,
				        { title:'-',data:'postiComuniTerzoAnnoTriennio',cl:''} ,
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
				            { title:'',cl:'fillBlankThTableSmallLeft', span :1},
					        { title:'Cattedre',cl:'', span :3}
					    ],
				cols : [
				        //{ title:'Anno Scolastico',data:'annoScolastico.label',cl:''},
				        { title:'Classe Concorso',data:'classeConcorso',cl:''} ,
				        { title:'-',data:'cattedrePrimoAnnoTriennio',cl:''} ,
				        { title:'-',data:'cattedreSecondoAnnoTriennio',cl:''} ,
				        { title:'-',data:'cattedreTerzoAnnoTriennio',cl:''} ,
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
				if (   componente.tipoComponente == 'S_FABBISOGNO_POSTI_CATTEDRE_AA_EE' 
					   || componente.tipoComponente == 'S_FABBISOGNO_POSTI_CATTEDRE_MM' ){
					
					var tabella =  ( componente.tipoComponente == 'S_FABBISOGNO_POSTI_CATTEDRE_AA_EE'  ) ?
							       jQuery.extend(true,{}, tabTmpl ) : 
							    	   jQuery.extend(true,{}, tabMMTmpl ) ; 
	
					id_fai++ ;
					tabella.index = id_fai;
					tabella.key = componente.key;
					tabella.titolo = componente.nome;

					var startCol = 0;
					
					if (componente.tipoComponente == 'S_FABBISOGNO_POSTI_CATTEDRE_MM'){
						startCol = 1;
					}
					tabella.tabella.cols[startCol].title = componente.labelAnno0;
					tabella.tabella.cols[startCol + 1].title = componente.labelAnno1;
					tabella.tabella.cols[startCol + 2].title = componente.labelAnno2;
					tabella.tabella.items = componente.items;
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