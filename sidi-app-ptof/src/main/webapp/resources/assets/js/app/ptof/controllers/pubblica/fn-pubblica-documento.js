'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.pubblica', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64'
])
.factory('pubblicaDocServicesFactory', function ($http, $rootScope,fileDownloadManager) {
	return {
		initFormPubblicazioneDoc : function (form) {
			console.log('pubblicaDocServicesFactory.initFormPubblicazioneDoc ->', form);
			return $http.post(APPGLOBALCONSTANT.contexPath+'/pubblicazione-ptof/init-form.json', form);
		},
		downloadPtodPubblicato  : function (key) {
		    console.log('pubblicaDocServicesFactory.downloadPtodPubblicato ->', key);
			fileDownloadManager.fileDownload({
					url : 'pubblicazione-ptof/download-doc/'+key ,
					data : { key : key }
				})
			return;
	    },
	    pubblica  : function (form) {
		    console.log('pubblicaDocServicesFactory.pubblica.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/pubblicazione-ptof/pubblica.json', form);
	    }
  }
})
.controller('PubblicaDocCtrl', function ($scope,$rootScope, $window ,$location , $base64 ,pubblicaDocServicesFactory ) {
	console.log('GestioneProfCtrl->');
	
	var keyDocumento ;

	var that = this;
	
	$scope.modelPubblicaDoc = {
			form : {  },
			labelPubblicaDoc :'',
			documenti :[]  
	};
	
	var init  = function () {
	
		pubblicaDocServicesFactory.initFormPubblicazioneDoc( $scope.modelPubblicaDoc.form )
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			
			$scope.modelPubblicaDoc.istitutoScolastico   = data.attribForm.istitutoScolastico;
			$scope.modelPubblicaDoc.documenti   = data.attribForm.documenti;
			
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
	
	}
	
	this.pubblica = function(x ){
		console.log('pubblica');
		 
		
		var testo =  'richiesta di generazione '+ ( x.statoDocumento =='CONVALIDATO' ? 'dell\'anteprima del Documento' : x.statoDocumento =='DOCUMENTO_IN_ANTEPRIMA' ? 'della pubblicazione parziale' : x.statoDocumento =='FABBISOGNO_VALIDATO' ? 'della pubblicazione definitiva' :'' ) ,
		     sms = {
	     		 title :"ATTENZIONE",
	     		 text :"Si sta procedendo con la " + testo + ". Si vuole procedere?",
	     		 type :"CONFERMA",
	     		 successRunFn : function() {
	     			
	     			pubblicaDocServicesFactory.pubblica( x.key )
	     				.success(function (data, status, config, headers) {
	     					console.log('Response from server: ' + data);
	     					$scope.modelPubblicaDoc.istitutoScolastico   = data.attribForm.istitutoScolastico;
	     					$scope.modelPubblicaDoc.documento   = data.attribForm.documento;
	     					$scope.modelPubblicaDoc.sezioni   = data.attribForm.sezioni;
	     					
	     					init();
	     				})
	     				.error(function (data, status, config, headers) {
	     					console.log('Some error occurred!'); 
	     				});

	     			
	     		 }
			  };
			  $rootScope.$broadcast("EVENT_SENDMESSAGE", sms );		
	}
	
	this.scarica = function(key) {
	        	console.log('scarica');
	 			pubblicaDocServicesFactory.downloadPtodPubblicato( key )
     			 
    }
	
	init();
	
})
