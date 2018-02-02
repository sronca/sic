'use strict';

// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione.sezione_24_piano_nazionale_scuola_digitale', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64',
  'ptof.controlles.gestione'
])
.controller('GestioneSezione-24-GestionePianoNazionaleScuolaDigitaleCtrl', function ($scope, $rootScope, $window , $location , $base64, $timeout ,gestioneDocServicesFactory , commonsUtilityPtofServicesFactory , ptofCommonsServicesFactory) {
	console.log('GestioneSezione-24-GestionePianoNazionaleScuolaDigitaleCtrl');
 
	$scope.vm = {
	    attribForm : {
	    },
	    form : {
	    },
		tabella1 : {
			btnotcanc : 'T',
			btnotadd :'T',
			btnotmod :'T',
			btnotvis :'T',
			form : {},
			componente : {},
			key : {},
			titolo : '',
			tabella : { 
				cols : [
				        { title:'Area Tematica PNSD',data:'areaTematicaPNSD',cl:''},
				        { title:'Numero Sezione',data:'numeroSezione',cl:''} ,
				        { title:'Descrizione Sezione',data:'descrizioneSezione',cl:''} ,
				        { title:'Contenuto Legato al PNSD',data:'contenutoLegatoPNSD',cl:''} ,
				        //{ title:'',data:'key',cl:''} 
				        ],
				items : []
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
				
				if (componente.tipoComponente == 'S_PIANO_NAZIONALE_SCUOLA_DIGITALE'    ){
					$scope.vm.tabella1.key = componente.key;
					$scope.vm.tabella1.titolo = componente.nome;
					$scope.vm.tabella1.tabella.items = componente.items;
					
					$scope.vm.tabella1.componente = jQuery.extend(true,{}, componente );
					$scope.vm.tabella1.form =  tempForm;
					temp = true
				}
			});
			if ( !temp ) {
			   console.log('ERRORE BK : LA SEZIONE DEVE CONTENERE ALEMNO UN componente S_PIANO_NAZIONALE_SCUOLA_DIGITALE ' + data);
			}
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
	}
 	
	// SALVA DELLE SEZIONE
	this.salva = function (){
		console.log('GestioneSezione-24-GestionePianoNazionaleScuolaDigitaleCtrl->salva');
		
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