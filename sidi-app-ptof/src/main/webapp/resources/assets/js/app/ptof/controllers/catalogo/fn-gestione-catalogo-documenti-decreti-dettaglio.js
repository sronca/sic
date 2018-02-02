'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controller.catalogo.documentoDecretiDettaglio', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64'
]).controller('DocumentoDecretiDettaglioCtrl', function ($scope, $window , $rootScope, $timeout, $base64, ptofCommonsServicesFactory,monitoraggioServicesFactory, uploadManager) {
	console.log('DocumentoDecretiDettaglioCtrl->');
	
	$scope.vm = {
				form : {
					allegato : {
						nome : '',
						suDB : 'TRUE',
						key :'pippo'
					}
				}
	};

		
	this.allega = function () {
		console.log('GestioneCatalogoDocumentiDecretiCtrl->allega');
		uploadManager.upload();
	}
	
	this.gestioneAllegato = function (opt) {
		console.log('GestioneCatalogoDocumentiDecretiCtrl->gestioneAllegato');
		
		if ( opt.action =='add' ) {
			$scope.vm.form.allegato.nome = opt.nome;
			$scope.$apply();
		}else if ( opt.action =='done' ) {
			var sms = {
	    		 title :"MESSAGGIO",
	    		 text :"Operazione effettuata con successo",
	    		 type :"OK"
	    	};
			$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
		}
	}
	
	this.scarica = function (key){
		console.log('GestioneCatalogoDocumentiDecretiCtrl->scarica');
	}
	
	$scope.indietro = function () {
		window.history.back();
	}
	
	//1)inizializzo il form
	var init  = function () {
		$scope.vm.form.allegato.suDB ='FALSE';
	}
	
   
   //1)inizializzo il form
   init();
})



