'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.gestione', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64'
]).controller('GestioneDocCtrl', function ($scope, $window , $base64 ,gestioneDocServicesFactory ) {
	console.log('GestioneDocCtrl->');
	$scope.modelGestioneDoc = {
			documentiAnnoIncorso : {},
			documentiArchivi : {}
	};
	 
	gestioneDocServicesFactory.initFormDoc({})
	.success(function (data, status, config, headers) {
		console.log('Response from server: ' + data);
		$scope.modelGestioneDoc.documentiAnnoIncorso   = data.attribForm.documentiAnnoIncorso;
		$scope.modelGestioneDoc.documentiArchivi   = data.attribForm.documentiArchivi;
	})
	.error(function (data, status, config, headers) {
		console.log('Some error occurred!');s = data.fieldErrors; 
	});
 	
	this.getPtofFileArchivio = function (keyDoc, tipoFile) {
		console.log('---getPtofFile--');
		gestioneDocServicesFactory.getPtofFileArchivio(keyDoc , tipoFile );
	}
	
	this.loadGestionePtof = function (keyDoc) {
		console.log('---loadGestionePtof--');
		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-gestione-ptof.do#?key='+encodeURIComponent( $base64.encode( keyDoc ));		
	};
}).controller('GestioneProfListaSezioniCtrl', function ($scope,$rootScope, $window ,$location , $base64 ,gestioneDocServicesFactory ) {
	console.log('GestioneProfCtrl->');
	var keyDocumento ;

	var that = this;
	
	if ( ( $location.path()+'') .indexOf('/collapse-')>-1  ){
		keyDocumento = decodeURIComponent( $base64.decode( $location.path().split('-')[1]  )).split('||')[0];
	} else {
		keyDocumento = decodeURIComponent( $base64.decode( $location.search().key ));
	}
	
	$scope.modelGestionePtof = {
			form : { keyDocumento : keyDocumento , keySezione : null },
			istitutoScolastico :{},
			documento : {},
			sezioni : {},
			focus : $location.search().keySezBk 
	};
	
	gestioneDocServicesFactory.initFormProf( $scope.modelGestionePtof.form )
	.success(function (data, status, config, headers) {
		console.log('Response from server: ' + data);
		$scope.modelGestionePtof.istitutoScolastico   = data.attribForm.istitutoScolastico;
		$scope.modelGestionePtof.documento   = data.attribForm.documento;
		$scope.modelGestionePtof.sezioni   = data.attribForm.sezioni;
	})
	.error(function (data, status, config, headers) {
		console.log('Some error occurred!'); 
	});
	
	this.refrestListSezioni = function(){
		gestioneDocServicesFactory.initFormProf( $scope.modelGestionePtof.form )
			.success(function (data, status, config, headers) {
				console.log('Response from server: ' + data);
				$scope.modelGestionePtof.istitutoScolastico   = data.attribForm.istitutoScolastico;
				$scope.modelGestionePtof.documento   = data.attribForm.documento;
				$scope.modelGestionePtof.sezioni   = data.attribForm.sezioni;
			})
			.error(function (data, status, config, headers) {
				console.log('Some error occurred!'); 
			});
	}
	
	this.convalida = function() {
		console.log('convalida');
		 var sms = {
     		 title :"ATTENZIONE",
     		 text :"Si sta procedendo con la richiesta di convalida. Non sara' possibile apportare successive modifiche alle sezioni. Si vuole procedere?",
     		 type :"CONFERMA",
     		 successRunFn : function() {
     			gestioneDocServicesFactory.convalidaProf( $scope.modelGestionePtof.form )
     			.success(function (data, status, config, headers) {
     				console.log('Response from server: ' + data);
     				$scope.modelGestionePtof.documento   = data.attribForm.documento;
     				that.refrestListSezioni()     				
     			})
     		 }
		  };
		  $rootScope.$broadcast("EVENT_SENDMESSAGE", sms );		

	}

	this.ritornaAllaCompilazione = function() {
		console.log('ritornaAllaCompilazione');
		console.log('convalida');
		 var sms = {
    		 title :"ATTENZIONE",
    		 text :"Si vuole procedere con l'azione selezionata?",
    		 type :"CONFERMA",
    		 successRunFn : function() {
    			gestioneDocServicesFactory.annullaConvalidaProf( $scope.modelGestionePtof.form )
    			.success(function (data, status, config, headers) {
    				console.log('Response from server: ' + data);
    				$scope.modelGestionePtof.documento   = data.attribForm.documento;
    				that.refrestListSezioni()
    			})
    		 }
		  };
		  $rootScope.$broadcast("EVENT_SENDMESSAGE", sms );		

	}

	
}).controller('GestioneSezioneCtrl', function ($scope,$rootScope, $window , $location , $base64, gestioneDocServicesFactory ,uploadManager ) {
	console.log('GestioneSezioneCtrl->');
	var key = ( $base64.decode( $location.search().key )).split('||'),
	    keySezBk = key[2];// keyforfocus
	$scope.tinymceOptions = {
			convert_fonts_to_spans : true ,
			invalid_styles: 'color font-size',
		    onChange: function(e) {
		      // put logic here for keypress and cut/paste changes
		    },
		    inline: false,
		    plugins : 'insertdatetime nonbreaking table contextmenu directionality',
		    language: 'it',
		    //menubar: 'edit insert view format table tools'
		    menubar: 'table',
		    fontsize_formats: '18pt 24pt 36pt', 
		    theme: 'modern',
		   // toolbar: "undo redo pastetext | styleselect | fontselect | fontsizeselect",	
		    content_css : APPGLOBALCONSTANT.contexPath+"/assets/plugins/an-tinymce/0.0.14/content_tinymce.css",
		    skin: "lightgray",
		    paste_preprocess : function(pl, o) {
		     	  // remove all tags => plain text
		    	  o.content = strip_tags( o.content,'' );
	    	}

    };	
	
	$scope.modelGestioneSezione = {
			form : {
				keySezione : key[1]	,
				keyDocumento : key[0],
				componenti : []
			},
			sezione : {}
	};
	 
	this.annulla = function (){
		console.log('GestioneSezioneCtrl->annulla');
		$scope.gotoIndiceSezione();
	}

	$scope.gotoIndiceSezione = function (){
		console.log('GestioneSezioneCtrl->annulla');
		$window.location.href = APPGLOBALCONSTANT.contexPath+
		'/fn-gestione-ptof.do#?key='+
		encodeURIComponent( $base64.encode( $scope.modelGestioneSezione.form.keyDocumento ))+
		'&keySezBk='+keySezBk;
	}

	// FUNC GENERICA RICHIAMATA SUL SALVA DELLE PAGINA DOPO AVER PASSATO I CONTROLLI
	$scope.salvaConConferma = function ( successRunFn ){
		console.log('GestioneSezioneCtrl->salvaConConferma');
 	 	var sms = {
          		 title :"ATTENZIONE",
          		 text :"Si sta procedendo all'aggiornamento si vuole proseguire?",
          		 type :"CONFERMA",
          		 successRunFn : successRunFn
       };
	   $rootScope.$broadcast("EVENT_SENDMESSAGE", sms );		
	  				
	}
	
	this.cancella = function (){
		console.log('GestioneSezioneCtrl->cancella');
		
		var successRunFn = function () {
			gestioneDocServicesFactory.cancellaSezione( {} )
			.success(function (data, status, config, headers) { 
				$scope.gotoIndiceSezione();
			})
			.error(function (data, status, config, headers) {
				console.log('Some error occurred!'); 
			});
		}
	 	var sms = {
          		 title :"ATTENZIONE",
          		 text :"Si sta procedendo alla cancellazione si vuole proseguire?",
          		 type :"CONFERMA",
          		 successRunFn : successRunFn
       };
	   $rootScope.$broadcast("EVENT_SENDMESSAGE", sms );		
	  				
	}
 
	// RINFRESCO  DA DB TUTTI I COMPONENTI
	var init  = function () {
	 	
		gestioneDocServicesFactory.initFormSezione( { keyDocumento : $scope.modelGestioneSezione.form.keyDocumento ,  keySezione : $scope.modelGestioneSezione.form.keySezione  } )
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			
			$scope.modelGestioneSezione.sezione = data.attribForm.sezione; // ATTENZIONE : non modifico MAI sulla maschera 
			
			$scope.modelGestioneSezione.form.componenti = jQuery.extend(true,{}, data.attribForm.sezione.componenti )  ;
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
	}

	this.allega = function () {
		console.log('GestioneSezioneCtrl->allega');
		uploadManager.upload();
	}

	this.gestioneAllegato = function (opt) {
		console.log('GestioneSezioneCtrl->gestioneAllegato');
		if ( opt.action =='add' ) {
			$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
				 if ( componente.tipoComponente == 'ALLEGATO' &&  componente.key == opt.key ) {
					 $scope.modelGestioneSezione.form.componenti[index].file = { fileName :   opt.nome } ;
					 $scope.$apply()
				 }
			})
		}
		if ( opt.action =='done' ) {
     		// ATTENZIONE NON RINFRESCO DA DB I COMPONENTI IN QUANTO UN UTENTE PUO AVER ABBOZZATO QUALCOSA
			$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
				 if ( componente.tipoComponente == 'ALLEGATO' &&  componente.key == opt.key ) {
					 $scope.modelGestioneSezione.sezione.componenti[index].file  = $scope.modelGestioneSezione.form.componenti[index].file
					 $scope.$apply()
				 }
			})
			 
		     //init();
		}
		
	}
	
	this.cancellaAllegato = function (key) {
		console.log('GestioneSezioneCtrl->cancellaAllegato');
		$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
			 if ( componente.tipoComponente == 'ALLEGATO' &&  componente.key == key ) {
				gestioneDocServicesFactory.cancellaAllegatoInSezione(  key  )
				.success(function (data, status, config, headers) {
					$scope.modelGestioneSezione.form.componenti[index].file = null ;
					componente.file = null ;
				})
				.error(function (data, status, config, headers) {
					console.log('Some error occurred!'); 
				});	
			 }// ENDIF 
			 
		})
	}
	
	this.scarica = function (key){
		console.log('GestioneSezioneCtrl->scarica');
		gestioneDocServicesFactory.downloadAllegatoInSezione( key );
		 
	}
	
}).controller('GestioneSezione-01IndiceCtrl', function ($scope, $window , $location , $base64, gestioneDocServicesFactory , uploadManager ) {
	console.log('GestioneSezione-01IndiceCtrl->');
	$scope.modelGestioneSezione01Indice = {
			form : {
				allegato : {
					nome : '',
					suDB : 'TRUE',
					key :''
				}
			}
	};

	gestioneDocServicesFactory.initFormSezione( $scope.modelGestioneSezione.form )
	.success(function (data, status, config, headers) {
		console.log('Response from server: ' + data);
		$scope.modelGestioneSezione.sezione = data.attribForm.sezione;
		$scope.modelGestioneSezione01Indice.form.allegato.key = $scope.modelGestioneSezione.sezione.componenti[0].key;
		if ( $scope.modelGestioneSezione.sezione.componenti[0].file ){
			$scope.modelGestioneSezione01Indice.form.allegato.nome  = $scope.modelGestioneSezione.sezione.componenti[0].file.fileName;
		} else {
			$scope.modelGestioneSezione01Indice.form.allegato.suDB ='FALSE';
		}
	})
	.error(function (data, status, config, headers) {
		console.log('Some error occurred!'); 
	});
	
	
	

	this.salva = function (){
		console.log('GestioneSezione-01IndiceCtrl->salva');
		
		gestioneDocServicesFactory.salvaSezione( $scope.modelGestioneSezione.form )
		.success(function (data, status, config, headers) {
			$scope.gotoIndiceSezione();
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
		
		
	}
 
	this.gestioneAllegato = function (opt) {
		console.log('GestioneSezione-01IndiceCtrl->gestioneAllegato');
		if ( opt.action =='add' ) {
		  $scope.modelGestioneSezione01Indice.form.allegato.nome = opt.nome ;
		  $scope.$apply();
		}
		
	}
	this.scarica = function (){
		console.log('GestioneSezione-01IndiceCtrl->scarica');
		gestioneDocServicesFactory.downloadAllegatoInSezione( $scope.modelGestioneSezione01Indice.form.allegato.key )
		.success(function (data, status, config, headers) {
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
	}
	this.cancellaAllegato = function () {
		console.log('GestioneSezione-01IndiceCtrl->cancellaAllegato');
		
		gestioneDocServicesFactory.cancellaAllegatoInSezione(   $scope.modelGestioneSezione01Indice.form.allegato.key  )
		.success(function (data, status, config, headers) {
			$scope.modelGestioneSezione01Indice = {
					form : {
						allegato : {
							nome : '',
							suDB : 'FALSE',
							key :''
						}
					}
				}
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});		
		

	}
	
	this.allega = function () {
		console.log('GestioneSezione-01IndiceCtrl->allega');
		uploadManager.upload();
	}
	
})
.controller('GestioneSezione-02FinalitaCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , uploadManager ) {
	console.log('GestioneSezione-01IndiceCtrl->');
	$scope.modelGestioneSezione02Finalita = {
			form : {
				componenti : '' 
			}
	};
 
	
	gestioneDocServicesFactory.initFormSezione( $scope.modelGestioneSezione.form )
	.success(function (data, status, config, headers) {
		console.log('Response from server: ' + data);
		$scope.modelGestioneSezione.sezione = data.attribForm.sezione;
		$scope.modelGestioneSezione02Finalita.form.componenti =  $scope.modelGestioneSezione.sezione.componenti  ;
	})
	.error(function (data, status, config, headers) {
		console.log('Some error occurred!'); 
	});
 	
 
	this.salva = function (){
		console.log('GestioneSezione-01IndiceCtrl->salva');
		if ( $scope.modelGestioneSezione02Finalita.form.componenti[0].obbligatorio  && 
		   ( !$scope.modelGestioneSezione02Finalita.form.componenti[0].valore || $scope.modelGestioneSezione02Finalita.form.componenti[0].valore< APPGLOBALCONSTANT.TEXTAREA_MIN_SIZE.size ) ) {
			var sms = {
	        		 title :"ATTENZIONE",
	        		 text : APPGLOBALCONSTANT.TEXTAREA_MIN_SIZE.messaggioErrore  , 
	        		 type :"KO"
	        };
			$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
			
			/*  } else if ( $scope.modelGestioneSezione02Finalita.form.testo.leangh <= APPGLOBALCONSTANT.TEXTAREA_MAX_SIZE.size ) {
			var sms = {
	        		 title :"ATTENZIONE",
	        		 text :  APPGLOBALCONSTANT.TEXTAREA_MAX_SIZE.messaggioErrore  , 
	        		 type :"KO"
	        };
       	    rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
       	  */  
		} else { // PASSIAMO IL CONTROLLI FORMALI 
			$scope.modelGestioneSezione.form.componenti =  $scope.modelGestioneSezione02Finalita.form.componenti;
			gestioneDocServicesFactory.salvaSezione( $scope.modelGestioneSezione.form )
			.success(function (data, status, config, headers) {
			
				$scope.gotoIndiceSezione();
			
			})
			.error(function (data, status, config, headers) {
				console.log('Some error occurred!'); 
			});
			
		}
		
	}
})
.controller('GestioneSezione-03StoriaCtrl', function ($scope, $rootScope, $window , $location , $base64, gestioneDocServicesFactory , uploadManager ) {
	console.log('GestioneSezione-01IndiceCtrl->');
 
 
	var init  = function () {
	 	
		gestioneDocServicesFactory.initFormSezione( { keyDocumento : $scope.modelGestioneSezione.form.keyDocumento ,  keySezione : $scope.modelGestioneSezione.form.keySezione  } )
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			
			$scope.modelGestioneSezione.sezione = data.attribForm.sezione; // ATTENZIONE : non modifico MAI sulla maschera 
			
			$scope.modelGestioneSezione.form.componenti = jQuery.extend(true,{}, data.attribForm.sezione.componenti )  ;
		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!'); 
		});
	}
	
	this.allega = function () {
		console.log('GestioneSezione-03StoriaCtrl->allega');
		uploadManager.upload();
	}
	
	
	
	this.gestioneAllegato = function (opt) {
		console.log('GestioneSezione-01IndiceCtrl->gestioneAllegato');
		if ( opt.action =='add' ) {
			$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
				 if ( componente.tipoComponente == 'ALLEGATO' &&  componente.key == opt.key ) {
					 $scope.modelGestioneSezione.form.componenti[index].file = { fileName :   opt.nome } ;
					 $scope.$apply()
				 }
			})
		}
		if ( opt.action =='done' ) {
		     init();
		}
		
	}

	this.cancellaAllegato = function (key) {
		console.log('GestioneSezione-03StoriaCtrl->cancellaAllegato');
		$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
			 if ( componente.tipoComponente == 'ALLEGATO' &&  componente.key == key ) {
				gestioneDocServicesFactory.cancellaAllegatoInSezione(  key  )
				.success(function (data, status, config, headers) {
					$scope.modelGestioneSezione.form.componenti[index].file = null ;
					componente.file = null ;
				})
				.error(function (data, status, config, headers) {
					console.log('Some error occurred!'); 
				});	
			 }// ENDIF 
			 
		})
	
		

	}
	
	this.salva = function (){
		console.log('GestioneSezione-01IndiceCtrl->salva');
		
		// TRA TESTO ALLEGATO e TABELLA almeno uno sia valoriazzarto
		var almenoUno = false, tipoCompoPresenti = { editor:0, allegati:0 , combo:0 , multibox:0 }, exit , fieldErrors= [] ;
		
		$.each( $scope.modelGestioneSezione.sezione.componenti , function (index, componente){
		    
			if (  componente.tipoComponente == 'TEXTEDITOR'  ) {
				tipoCompoPresenti.editor ++;
				if ( componente.valore ) {
					almenoUno = true;
				}
				if (  componente.obbligatorio  && 
						   ( !componente.valore ||  componente.valore < APPGLOBALCONSTANT.TEXTAREA_MIN_SIZE.size ) ) {
							var sms = {
					        		 title :"ATTENZIONE",
					        		 text : APPGLOBALCONSTANT.TEXTAREA_MIN_SIZE.messaggioErrore  , 
					        		 type :"KO"
					        };
							$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
							exit = true;
							return;
				}
			} else if (  componente.tipoComponente == 'ALLEGATO' ) {
				tipoCompoPresenti.allegati ++;
				if ( componente.file ) {
					almenoUno = true;
				}
				
				// NESSUN CONTROLLO PER ORA
				if (  componente.obbligatorio  && 
						   ( componente.file ||  componente.fileName ) ) {

					var sms = {
			        		 title :"ATTENZIONE",
			        		 text : "ALLEGATO OBBLIGATORIO"  , 
			        		 type :"KO"
			        };
					$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
					exit = true;
					return;
					
				}
			}  else if (  componente.tipoComponente == 'COMBO_BOX' ) {
				tipoCompoPresenti.combo ++;
				
				// NESSUN CONTROLLO PER ORA
				if (  componente.obbligatorio  && 
						   ( !componente.selected || !componente.selected.value ) ) {

					fieldErrors.push ( { field : 'combo_'+componente.key , message : 'Cambo Obbligatorio'  } );
					
					exit = true;
					return;
					
				}
			}  else if (  componente.tipoComponente == 'MULTI_BOX' ) {
				tipoCompoPresenti.multibox ++;
				
				// NESSUN CONTROLLO PER ORA
				if (  componente.obbligatorio ) {

					$.each ( componente.comboBox , function ( i, comboItem ) {
						
						if (  comboItem.obbligatorio  &&
								   ( !comboItem.selected || !comboItem.selected.value ) ) {
							
							fieldErrors.push (  { field : 'combo_'+comboItem.key , message : 'Cambo Obbligatorio'  } );
							exit = true;
						}	
					})
					
					return;
					
				}
			}

		})

		if (exit) {
			
			if ( fieldErrors.length>0  ) {
				var sms = {
		        		 title :"ATTENZIONE",
		        		 fieldErrors : fieldErrors , 
		        		 type :"KO"
		        };
				$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
			}
			
			return ;
		}
		
		if ( $scope.modelGestioneSezione.sezione.obbInsAlmenoUnCom && !almenoUno ) {
			    
				var sms = {
		        		 title :"ATTENZIONE",
		        		 text : "Inserito almeno uno tra Testo e Allegato."  , 
		        		 type :"KO"
		        };
				$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
				return 
	     }
		
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

