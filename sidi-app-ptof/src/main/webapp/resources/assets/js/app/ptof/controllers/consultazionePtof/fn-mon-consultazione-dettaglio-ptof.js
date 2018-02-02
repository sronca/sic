'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controllers.consultazionePtof.consultazioneDettaglioPtof', [
  'ptof.commons.services',
  'ptof.directives',
  'base64'
]).factory('ptofConsultazioneDettaglioFactory', function ($http, $rootScope) {
	return {
		 initConsultazionePtof : function (form) {
				console.log('ptofConsultazionePuntualeFactory.consultazione-ptof.init-consultazione-puntuale-ptof.json ->');
				return $http.post(APPGLOBALCONSTANT.contexPath+'/consultazione-ptof/init-consultazione-puntuale-ptof.json',form);
		 },
     	 loadConsultazionePuntualePtof : function (form) {
		       console.log('ptofConsultazionePuntualeFactory.consultazione-ptof.load-consultazione-puntuale-ptof.json ->');
		       return $http.post(APPGLOBALCONSTANT.contexPath+'/consultazione-ptof/load-consultazione-puntuale-ptof.json',form);
     	 },
	    getDettaglioFabbisogno : function (key) {
		       console.log('ptofConsultazionePuntualeFactory.consultazione-ptof.get-dettaglio-fabbisogno.json ->');
		       return $http.post(APPGLOBALCONSTANT.contexPath+'/consultazione-ptof/get-dettaglio-fabbisogno.json',key);
		}
  }
}).controller('ConsultazioneDettaglioPtofCtrl', function ($scope, $window ,$rootScope, $element ,$timeout, $location , $base64, $filter , ptofConsultazioneDettaglioFactory, ptofCommonsServicesFactory) {
	console.log('ConsultazionePtofCtrl->');


	$scope.vm = {
			form : {
				consultazionePtof : {}

			},
			attribForm : {
				consultazionePtofEmpty : {},
				consultazionePtof : {
					regioniL :[] ,provinceL :[] ,comuniL :[] , tipologiaScuolaL : [], codMeccanografico : ''
				},
				scuolePtofPubblicatoL : [],// lista scuole che hanno pubblicato il PTOF
				keyDocumento : {},
				progCatDoc : {}
			}
	};

	var appo = $base64.decode( $location.search().key).split('||');
	 
	$scope.vm.attribForm.keyDocumento =   appo[0]   ;
	$scope.vm.attribForm.progCatDoc =     appo[1]   ;

	//1)inizializzo il form
	var init  = function () {
		console.log('Init della pagina');
		 
 
		ptofConsultazioneDettaglioFactory.getDettaglioFabbisogno($scope.vm.attribForm.keyDocumento)
		.success(function (data, status, config, headers) {
			$scope.vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz = data.attribForm.fabbisognoPostiComuniIntestaz ;
    		$scope.vm.attribForm.consultazionePtof.fabbisognoPostiComuniL =   data.attribForm.fabbisognoPostiComuniL ;
    		$scope.vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz =  data.attribForm.fabbisognoPostiSostegnoIntestaz ;
    		$scope.vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoL =  data.attribForm.fabbisognoPostiSostegnoL ;
 			$scope.vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz = data.attribForm.fabbisognoPostiPotenziamentoIntestaz ;
 			$scope.vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoL = data.attribForm.fabbisognoPostiPotenziamentoL ;
 			
 			$scope.vm.attribForm.consultazionePtof.istitutoScolastico = data.attribForm.istitutoScolastico ;
		}).error(function (data, status, config, headers) {
			   console.log('Some error occurred! init');
		});
		
		 
		
	}
 
	/**********/
	/*END INIT VM */
	/**********/

	/*********/
   /* AZIONI */
 
   $scope.indietro = function () {
	   window.history.back();
	}
 
   // 1)inizializzo il form
	
		init();
	

})
