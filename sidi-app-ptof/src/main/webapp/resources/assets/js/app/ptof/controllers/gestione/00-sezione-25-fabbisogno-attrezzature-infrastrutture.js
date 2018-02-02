'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_25_fabbisognoattrezzature', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-25-FabbisognoAttrezzatureCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-25-FabbisognoAttrezzatureCtrl');
 
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
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				        { title:'Tipologia',data:'tipologia',cl:''},
				        { title:'Descrizione',data:'descrizione',cl:''} ,
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

				commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item_tipologia_'+this.index, value : this.newItem.tipologia });
				commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item_descrizione_'+this.index, value : this.newItem.descrizione });
				commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item_numero_pezzi_'+this.index, value : this.newItem.numeroPezzi });
				commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors,o:'o' , field :'item_area_tematica_'+this.index, value : this.newItem.areaTematicaPNSD });
				//commonsUtilityPtofServicesFactory.textMaxSizeTextArea (  { fieldErrors:fieldErrors } );
				
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
				if (   componente.tipoComponente == 'S_FABBISOGNO_ATTREZZATURE_INFRA'    ){
					var tabella =  jQuery.extend(true,{}, tabTmpl );
					id_fai++ ;
					tabella.index = id_fai;
					tabella.key = componente.key;
					tabella.titolo = componente.nome;
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