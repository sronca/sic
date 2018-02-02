'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_27_fabbisogno_sostegno', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-27-FabbisognoSostegnoCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-27-FabbisognoSostegnoCtrl');
 
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
				            { title:'Posti di sostegno nel triennio' ,cl:'' , span : 9}  
					    ],
				colsHead : [
				            //{ title:'' ,cl:'' , span : 1},
						    { title:'Udito' ,cl:'' , span : 3} ,
						    { title:'Vista' ,cl:'' , span : 3} ,
						    { title:'Psicofisico' ,cl:'' , span : 3} 
					    ],
				cols : [
				        //{ title:'Anno Scolastico',data:'annoScolastico.label',cl:''},
				        { title:'-',data:'postiSostegnoUditoPrimoTriennio',cl:''} ,
				        { title:'-',data:'postiSostegnoUditoSecondoTriennio',cl:''} ,
				        { title:'-',data:'postiSostegnoUditoTerzoTriennio',cl:''} ,
				        { title:'-',data:'postiSostegnoVistaPrimoTriennio',cl:''} ,
				        { title:'-',data:'postiSostegnoVistaSecondoTriennio',cl:''} ,
				        { title:'-',data:'postiSostegnoVistaTerzoTriennio',cl:''} ,
				        { title:'-',data:'postiSostegnoPsicofisicoPrimoTriennio',cl:''} ,
				        { title:'-',data:'postiSostegnoPsicofisicoSecondoTriennio',cl:''} ,
				        { title:'-',data:'postiSostegnoPsicofisicoTerzoTriennio',cl:''} ,
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
					        { title:'Posti Sostegno',cl:'', span :3}
					    ],
				cols : [
				        //{ title:'Anno Scolastico',data:'annoScolastico.label',cl:''},
				        { title:'-',data:'numeroPostiPrimoAnnoTriennio',cl:''} ,
				        { title:'-',data:'numeroPostiSecondoAnnoTriennio',cl:''} ,
				        { title:'-',data:'numeroPostiTerzoAnnoTriennio',cl:''} ,
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
				if (   componente.tipoComponente == 'S_FABBISOGNO_POSTI_SOSTEGNO_AA_EE_MMI' 
					   || componente.tipoComponente == 'S_FABBISOGNO_POSTI_SOSTEGNO_MMII' ){
					
					var tabella =  ( componente.tipoComponente == 'S_FABBISOGNO_POSTI_SOSTEGNO_AA_EE_MMI'  ) ?
							       jQuery.extend(true,{}, tabTmpl ) : 
							    	   jQuery.extend(true,{}, tabMMTmpl ) ; 
	
					id_fai++ ;
					tabella.index = id_fai;
					tabella.key = componente.key;
					tabella.titolo = componente.nome;
					tabella.tabella.items = componente.items;
					
					var startCol = 0;

					tabella.tabella.cols[startCol].title = componente.labelAnno0;
					tabella.tabella.cols[startCol + 1].title = componente.labelAnno1;
					tabella.tabella.cols[startCol + 2].title = componente.labelAnno2;
					
					if (componente.tipoComponente == 'S_FABBISOGNO_POSTI_SOSTEGNO_AA_EE_MMI'){
						tabella.tabella.cols[startCol+ 3].title = componente.labelAnno0;
						tabella.tabella.cols[startCol + 4].title = componente.labelAnno1;
						tabella.tabella.cols[startCol + 5].title = componente.labelAnno2;
						tabella.tabella.cols[startCol + 6].title = componente.labelAnno0;
						tabella.tabella.cols[startCol + 7].title = componente.labelAnno1;
						tabella.tabella.cols[startCol + 8].title = componente.labelAnno2;
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