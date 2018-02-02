'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controllers.monitoraggio.fabbissognoPostiSostegno', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64'
]).controller('MonitoraggioFabbisognoPostiSostegnoCtrl', function ($scope, $window ,$timeout, $base64, ptofCommonsServicesFactory,monitoraggioServicesFactory) {
	console.log('MonitoraggioFabbisognoPostiSostegnoCtrl->');
	
	$scope.vm = {
	    attribForm : {
	    	regioniL :[]
	    },
	    form : {
	    },
		tabella : []  
	}
	
	//1)inizializzo il form
	var init  = function () {
		console.log('popolo combo regione');
		ptofCommonsServicesFactory.getRegioni({tipologica :{tipo:'REGIONI'}})
		.success(function (data, status, config, headers) {
			$scope.vm.attribForm.regioniL = data.attribForm.REGIONI;
			
			if($scope.vm.attribForm.regioniL.length==1){
				$scope.vm.form.regione = {  
						value : $scope.vm.attribForm.regioniL[0].value , 
						label : $scope.vm.attribForm.regioniL[0].label 
				};
			}
			
		})
	}
	
   $scope.scaricaReport = function() {	    
	   var form = {
	    		progressivoGestioneCatalogoDocumento : $scope.vm.form.progressivoGestioneCatalogoDocumento
	   };
	   
	   if ($scope.vm.form.regione != undefined){
		   form.regione = $scope.vm.form.regione.value;
	   }
	   
	   console.log('MonitoraggioFabbisognoPostiSostegnoCtrl->scaricaReport');
	   monitoraggioServicesFactory.downloadFabbisognoPostiSostegno(form);   
   }
   
   $scope.indietro = function () {
	   window.history.back();
	}
   
   //1)inizializzo il form
   init();
})