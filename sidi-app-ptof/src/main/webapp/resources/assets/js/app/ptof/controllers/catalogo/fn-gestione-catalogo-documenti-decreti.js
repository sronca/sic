'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controller.catalogo.documentoDecreti', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64'
]).controller('GestioneCatalogoDocumentiDecretiCtrl', function ($scope, $rootScope, $window , $base64 , gestioneDocServicesFactory, gestioneCatalogoDocumentiServicesFactory, commonsUtilityPtofServicesFactory, uploadManager ) {
	console.log('GestioneCatalogoDocumentiDecretiCtrl->');

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

	this.upload = function ( keyDoc ){
		console.log('GestioneCatalogoDocumentiDecretiCtrl -> upload');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-gestione-catalogo-documenti-decreti-dettaglio.do?progresivoGestioneCatalogoDocumento='+ keyDoc; 		
	}
	
	$scope.indietro = function () {
		window.history.back();
	}
	
	this.loadCatalogoDocumenti();
});