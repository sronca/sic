'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controller.monitoraggio.documenti_attivabili', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64'
]).controller('DocumentiAttivabiliCtrl', function ($scope, $window , $base64 ,$timeout ,gestioneDocServicesFactory ) {
	console.log('DocumentiAttivabiliCtrl->');
	$scope.modelGestioneDoc = {
			inCorso: 'ppp',
			documentiAttivabili : {}
	};
	
	var init = function() {
		console.log('Init -> DocumentiAttivabiliCtrl');

		if ($scope.modelGestioneDoc.inCorso!='CRUSCOTTO_CONVALIDA_FABBISOGNO' && $scope.modelGestioneDoc.inCorso!='CONSULTAZIONE_PUNTUALE_PTOF' ){
			gestioneDocServicesFactory.initFormDocAttivabili({})
			.success(function (data, status, config, headers) {
				console.log('Response from server: ' + data);
				$scope.modelGestioneDoc.documentiAttivabili   = data.attribForm.documentiAttivabili;
			})
			.error(function (data, status, config, headers) {
				console.log('Some error occurred!');s = data.fieldErrors; 
			});
		}else{
			gestioneDocServicesFactory.initFormDocAttivabiliInCorso({})
			.success(function (data, status, config, headers) {
				console.log('Response from server: ' + data);
				$scope.modelGestioneDoc.documentiAttivabili   = data.attribForm.documentiAttivabiliInCorso;
			})
			.error(function (data, status, config, headers) {
				console.log('Some error occurred!');s = data.fieldErrors; 
			});
		}
	}
	
	this.loadReportCompleto = function (keyDoc) {
		console.log('---loadReportCompleto--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-mon-report-completo-dettaglio.do?progresivoGestioneCatalogoDocumento='+ keyDoc; //encodeURIComponent( $base64.encode( keyDoc ));		
	};
	this.loadPostiComuni = function (keyDoc) {
		console.log('---loadPostiComuni--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-mon-cruscotto-fabbisogno-posti-comuni.do?progresivoGestioneCatalogoDocumento='+ keyDoc;		
	};
	this.loadPostiSostegno = function (keyDoc) {
		console.log('---loadPostiSostegno--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-mon-cruscotto-fabbisogno-posti-sostegno.do?progresivoGestioneCatalogoDocumento='+ keyDoc;		
	};
	this.loadPostiPotenziamento = function (keyDoc) {
		console.log('---loadPostiPotenziamento--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-mon-cruscotto-fabbisogno-posti-potenziamento.do?progresivoGestioneCatalogoDocumento='+ keyDoc;		
	};
	this.loadTotaleOrganico = function (keyDoc) {
		console.log('---loadTotaleOrganico--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-mon-cruscotto-fabbisogno-totale-organica.do?progresivoGestioneCatalogoDocumento=' + keyDoc;		
	};
	this.loadStatistiche = function (keyDoc) {
		console.log('---loadStatistiche--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-mon-statistiche-dettaglio.do?progresivoGestioneCatalogoDocumento='+keyDoc;		
	};
	this.loadDettaglioBisogno = function (keyDoc) {
		console.log('---loadDettaglioBisogno--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-mon-dettaglio-fabbisogno-dettaglio.do?progresivoGestioneCatalogoDocumento='+ keyDoc;		
	};
	this.loadCruscottoConvalidaFabbisogno = function (keyDoc) {
		console.log('---loadCruscottoConvalidaFabbisogno--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-cruscotto-convalida-fabbisogno.do?progresivoGestioneCatalogoDocumento='+ keyDoc;		
	};
	this.loadConsultazionePuntualePtof = function (keyDoc) {
		console.log('---loadConsultazionePuntualePtof--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-consultazione-puntuale-dettaglio.do?progresivoGestioneCatalogoDocumento='+ keyDoc;		
	};
	
	// 1)inizializzo il form
	$timeout(function() {
		init();
	});
});