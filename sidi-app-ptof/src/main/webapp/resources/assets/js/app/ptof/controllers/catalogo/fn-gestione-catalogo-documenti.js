'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controller.catalogo.documentiAttivabili', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64'
]).controller('GestioneCatalogoDocumentiCtrl', function ($scope, $rootScope, $window , $base64 , gestioneDocServicesFactory, gestioneCatalogoDocumentiServicesFactory, commonsUtilityPtofServicesFactory ) {
	console.log('GestioneCatalogoDocumentiCtrl->');

	$scope.catalogoDocumento = {};
	$scope.modelGestioneCatalogoDocumenti = {			
			documentiAttivabili : {}
	};
	 
	this.loadCatalogoDocumenti = function(){
		gestioneDocServicesFactory.initFormDocAttivabili({})
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			$scope.modelGestioneCatalogoDocumenti.documentiAttivabili   = data.attribForm.documentiAttivabili;
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!');s = data.fieldErrors; 
		});
	}
	// TASTO AGGIUNGI SOPRA LA TABELLA
	this.validazione = function(){
		console.log('GestioneCatalogoDocumentiCtrl -> validazione'); 
		var fieldErrors =[];

		commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o', field :'descrizioneDocumento', value : $scope.catalogoDocumento.descrizioneDocumento});
		commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o', field :'dataInizioValidita', value : $scope.catalogoDocumento.dataInzioValidita});
 	    commonsUtilityPtofServicesFactory.testTextArea( { fieldErrors:fieldErrors, o:'o', field :'dataFineValidita', value : $scope.catalogoDocumento.dataFineValidita});
		commonsUtilityPtofServicesFactory.testDate( { fieldErrors:fieldErrors,    field :'dataInizioValidita', value : $scope.catalogoDocumento.dataInzioValidita});
 	    commonsUtilityPtofServicesFactory.testDate( { fieldErrors:fieldErrors,  field :'dataFineValidita', value : $scope.catalogoDocumento.dataFineValidita});
 	    
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
	}
	
	this.aggiungi = function () {
		$scope.catalogoDocumento = {};
		gestioneCatalogoDocumentiServicesFactory.generaVersione().success(function (data, status, config, headers) {
			 console.log('gestioneCatalogoDocumentiServicesFactory.generaVersione');
			 $rootScope.$broadcast("EVENT_SENDMESSAGE", {type: 'CLEAN'});
			 $scope.catalogoDocumento.periodoTriennioRiferimento = data.attribForm.gestioneCatalogoDTO.periodoTriennioRiferimento;
			 $scope.catalogoDocumento.numeroVersioneDocumento = data.attribForm.gestioneCatalogoDTO.numeroVersioneDocumento;
			 $scope.dialogAction ='I';
			 $scope.azione = "Nuovo";
			 $('#idDialog').modal('show');
		 });
	 }	
	
	this.modifica = function ( progresivoGestioneCatalogoDocumento ) {
		 gestioneCatalogoDocumentiServicesFactory.getCatalogoDcoumento(progresivoGestioneCatalogoDocumento).success(function (data, status, config, headers) {
				 console.log('gestioneCatalogoDocumentiServicesFactory.getCatalogoDcoumento', progresivoGestioneCatalogoDocumento);
	             $scope.catalogoDocumento= data.attribForm.gestioneCatalogoDTO;
	             $scope.dialogAction ='M';
	    		 $scope.azione = "Modifica";
	    		 
	    		 $rootScope.$broadcast("EVENT_SENDMESSAGE", {type: 'CLEAN'});
	    		 $('#idDialog').modal('show');
		 }); 
	}
	
	this.salva = function ( tr ) {
		console.log($scope.dialogAction);
		var that = this;
		 if (this.validazione()){
			 gestioneCatalogoDocumentiServicesFactory.salvaCatalogoDcoumento($scope.catalogoDocumento).success(function (data, status, config, headers) {
	    		 console.log(data);
	    		 that.loadCatalogoDocumenti();
			 });
		 }
	 }
	
	this.loadCatalogoDocumenti();
});