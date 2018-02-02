'use strict';

/* Services */

angular.module('ptof.commons.services', [])
.factory('uploadManager', function ($rootScope) {
	var _files = [],
	pendingRequestsUploadCount = 0;
	return {
		pendingRequest: 0 ,
		pendingRequestsUpload : function () {
			return pendingRequestsUploadCount;
		},
		add : function (file) {
			_files = []; // gestisco solo un file alla volta per colpa di IE8
			_files.push(file);
		},
		clear : function () {
			_files = [];
		},
		files : function () {
			var fileNames = [];
			$.each(_files, function (index, file) {
				fileNames.push(file.files[0].name);
			});
			return fileNames;
		},
		upload : function (dataSend) {
			if ( _files.length > 0  ){
				$rootScope.$broadcast('UPLOADMANAGER_UPLOAD', 'START');
				$.each(_files, function (index, file) {
					file.submit();
				});
			}
		},
		stopUpload : function (data) {
			$rootScope.$broadcast('UPLOADMANAGER_UPLOAD', 'STOP');
			//$rootScope.$broadcast('FileUploadCtrl.fail', data);
		}
	};
})
.factory('fileDownloadManager', function ( $rootScope  ) {
   return {
		   fileDownload : function (opt){
				$rootScope.$broadcast('UPLOADMANAGER_UPLOAD', 'START');
	 			$.fileDownload(  opt.url , {
					httpMethod : "GET",
					data :  opt.data,
				    successCallback: function (url) {
				    	$rootScope.$broadcast('UPLOADMANAGER_UPLOAD', 'STOP');
				    },
				    failCallback: function (html, url) {
				    	$rootScope.$broadcast('UPLOADMANAGER_UPLOAD', 'STOP');
			        	var sms = {
			           		 title :"ERRORE",
			           		 text :"Sistema momentanemente non disponibile, riprovare piu' tardi",
			           		 type :"KO"
			           	};
			           	$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
				    }
				});
		 }
   }
})
.factory('monitoraggioServicesFactory', function ($window, $http, $rootScope, fileDownloadManager ) {
   return {
		   initReportCompleto : function (form) {
				console.log('monitoraggioCompletoServicesFactory.monitoraggio-init--report-completo.json ->');
				return $http.post(APPGLOBALCONSTANT.contexPath+'/monitoraggio-report/init-mon-report-completo-base.json', form);
			},
	   		loadReportCompleto : function (form) {
	   			console.log('monitoraggioCompletoServicesFactory.monitoraggio-reportget-report-completo.json ->');
				return $http.post(APPGLOBALCONSTANT.contexPath+'/monitoraggio-report/get-report-completo.json', form);
			},
			getPtofFile : function (key,statoPtof) {
				console.log('monitoraggioCompletoServicesFactory.getPtofFile ->', key);
				fileDownloadManager.fileDownload({
					url : 'monitoraggio-report/download-ptof-file/'+key ,
					data : { key : key, statoPtof : statoPtof }
				})
				return;
			},
		    downloadFabbisognoPostiComuni : function (form) {
				console.log('monitoraggioServicesFactory.getFabbisognoPostiComuni ->', form);
				var uri = APPGLOBALCONSTANT.contexPath+'/fn-mon-cruscotto-fabbisogno-posti-comuni-dettaglio.do?progressivoGestioneCatalogoDocumento=' + form.progressivoGestioneCatalogoDocumento + '&regione=';
				
				if (form.regione != undefined){
					uri+=form.regione;
				}
				    
				$window.location.href = uri;
		    },
		    downloadFabbisognoPostiSostegno : function (form) {
				console.log('monitoraggioServicesFactory.getFabbisognoPostiSostegno ->', form);
				
				var uri = APPGLOBALCONSTANT.contexPath+'/fn-mon-cruscotto-fabbisogno-posti-sostegno-dettaglio.do?progressivoGestioneCatalogoDocumento=' + form.progressivoGestioneCatalogoDocumento + '&regione=';
				
				if (form.regione != undefined){
					uri+=form.regione;
				}
				
				$window.location.href = uri;
		    },
		    downloadFabbisognoPostiPotenziamento : function (form) {
				console.log('monitoraggioServicesFactory.getFabbisognoPostiPotenziamento ->', form);
				
				var uri = APPGLOBALCONSTANT.contexPath+'/fn-mon-cruscotto-fabbisogno-posti-potenziamento-dettaglio.do?progressivoGestioneCatalogoDocumento=' + form.progressivoGestioneCatalogoDocumento + '&regione=';
				
				if (form.regione != undefined){
					uri+=form.regione;
				}
				
				$window.location.href = uri;
		    },
		    downloadFabbisognoTotaleOrganica : function (form) {
				console.log('monitoraggioServicesFactory.getFabbisognoTotaleOrganica ->', form);
				
				var uri = APPGLOBALCONSTANT.contexPath+'/fn-mon-cruscotto-fabbisogno-totale-organica-dettaglio.do?progressivoGestioneCatalogoDocumento=' + form.progressivoGestioneCatalogoDocumento + '&regione=';
				
				if (form.regione != undefined){
					uri+=form.regione;
				}
				
				$window.location.href = uri;
		    }
          }
})
.factory('ptofCommonsServicesFactory', function ($http, $rootScope) {
	return {
 		getTipologia : function (form) {
			console.log('ptofCommonsServicesFactory.initFormProfili.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/get-titologica-sezione.json', form);

		},
		getRegioni : function (form) {
			console.log('ptofCommonsServicesFactory.getRegioni.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/get-tipologica-regioni.json', form);

		},
		getProvince : function (form) {
			console.log('ptofCommonsServicesFactory.getProvince.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/get-tipologica-province.json', form);

		},
		getComuni : function (form) {
			console.log('ptofCommonsServicesFactory.getComuni.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/get-tipologica-comuni.json', form);

		},
		getStatoPtof : function (form) {
			console.log('ptofCommonsServicesFactory.getStatoPtof.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/get-tipologica-statoPtof.json', form);
		},
 		logout : function (form) {
			console.log('ptofCommonsServicesFactory.logout.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/home/logout.json', form);

		}}
}).factory('gestioneDocServicesFactory', function ($http, $rootScope,fileDownloadManager) {
	return {
 		initFormDoc : function (form) {
			console.log('gestioneDocServicesFactory.initFormProfili.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-documenti/init-form.json', form);

		},getPtofFileArchivio : function (key, tipoFile) {
			console.log('gestioneDocServicesFactory.getPtofFileArchivio ->', key);
			fileDownloadManager.fileDownload({
				url : 'gestitone-documenti/download-ptof-file-archivio/'+key ,
				data : { tipo : tipoFile }
			})
			return;
		},initFormProf : function (form) {
			console.log('gestioneDocServicesFactory.initFormProf.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/init-form.json', form );
		},convalidaProf : function (form) {
			console.log('gestioneDocServicesFactory.initFormProf.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/set-convalida-ptof-form.json', form );
		},annullaConvalidaProf : function (form) {
			console.log('gestioneDocServicesFactory.initFormProf.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/set-annulla-convalida-ptof-form.json', form );
		},initFormSezione  : function (form) {
			console.log('gestioneDocServicesFactory.initFormSezione.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/init-sezione-form.json', form);
	    },cancellaSezione  : function (form) {
		    console.log('gestioneDocServicesFactory.initFormSezione.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/set-sezione-cancella-form.json', form);
	    },cancellaAllegatoInSezione  : function (form) {
		    console.log('gestioneDocServicesFactory.cancellaAllegatoInSezione.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/set-sezione-cancella-allegato-form.json', form );
	    },salvaSezione  : function (form) {
		    console.log('gestioneDocServicesFactory.salvaSezione.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/set-sezione-form.json', form);
	    },saveItemInComponenteSezione  : function (form) {
		    console.log('gestioneDocServicesFactory.salvaItemInComponenteSezione.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/save-item-componente-form.json', form);
	    },deleteItemInComponenteSezione  : function (form) {
		    console.log('gestioneDocServicesFactory.deleteItemInComponenteSezione.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-ptof/delete-item-componente-form.json', form);
	    },downloadAllegatoInSezione : function (key) {
			console.log('gestioneDocServicesFactory.getAllegatoInSezione ->', key);
			fileDownloadManager.fileDownload({
				url : 'gestitone-ptof/download-allegato-sezione/'+key ,
				data : { key : key }
			})
			return;
	    },initFormDocAttivabili : function (form) {
			console.log('gestioneDocServicesFactory.initFormDocAttivabili.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-documenti/documenti-attivabili.json', form);
		},getGestioneCatalogoById : function (key) {
			console.log('gestioneDocServicesFactory.getGestioneCatalogoById ->');
			return $http.get(APPGLOBALCONSTANT.contexPath+'/gestione-documenti/catalogo-documento/' + key );
		},initFormDocAttivabiliInCorso : function (form) {
			console.log('gestioneDocServicesFactory.initFormDocAttivabiliInCorso.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestitone-documenti/documenti-attivabili-in-corso.json', form);
		}
  }
}).factory('commonsUtilityPtofServicesFactory', function ($http, $rootScope,fileDownloadManager,$filter) {
	return {
		testCombo : function( opt ){
			if ( opt.o ) {
				if ( !opt.value || !opt.value.value )
				opt.fieldErrors.push ( { field : opt.field , message : APPGLOBALCONSTANT.SMS01_CAMPO_OBBLIGATORIO.messaggioErrore  } );
			}
		},
		testTextArea : function( opt ){
			if ( opt.o ) {
				if ( !opt.value )
				opt.fieldErrors.push ( { field : opt.field , message : APPGLOBALCONSTANT.SMS01_CAMPO_OBBLIGATORIO.messaggioErrore  } );
			}
            if ( opt.value && APPGLOBALCONSTANT.SMS02_CAMPO_TXTAREA_OVER_MAX.size <= opt.value.length ) {
				opt.fieldErrors.push ( { field : opt.field , message : APPGLOBALCONSTANT.SMS02_CAMPO_TXTAREA_OVER_MAX.messaggioErrore  } );
			}
		},
		testTextAreas : function( opt ){
			$.each( opt.field , function (imdex,item){
				var value = opt.that[ item ], field = ( opt.perFix ? opt.perFix :'' ) + '_' + item ;
				if ( opt.o ) {
					if ( !value )
					opt.fieldErrors.push ( { field : field , message : APPGLOBALCONSTANT.SMS01_CAMPO_OBBLIGATORIO.messaggioErrore  } );
				}
	            if ( value && APPGLOBALCONSTANT.SMS02_CAMPO_TXTAREA_OVER_MAX.size <= value.length ) {
					opt.fieldErrors.push ( { field : field , message : APPGLOBALCONSTANT.SMS02_CAMPO_TXTAREA_OVER_MAX.messaggioErrore  } );
				}
			})
		},
		testDate : function( opt ){
			if ( opt.o ) {
				if ( !opt.value )
				opt.fieldErrors.push ( { field : opt.field , message : APPGLOBALCONSTANT.SMS01_CAMPO_OBBLIGATORIO.messaggioErrore  } );
			}
			if (  opt.value ) {
				 var d = moment(opt.value ,'DD/MM/YYYY');
				 if( d == null || (''+opt.value).length !=10  || !d.isValid() ) {
					opt.fieldErrors.push ( { field : opt.field , message : APPGLOBALCONSTANT.SMS03_CAMPO_DATA_NON_VALID.messaggioErrore  } );
				 } else if ( d.isValid() && (  !d.isAfter('1900-01-01T00:00:00Z')
								             || d.isAfter('2200-01-01T00:00:00Z'))){
							 opt.fieldErrors.push ( { field : opt.field , message : APPGLOBALCONSTANT.SMS03_CAMPO_DATA_NON_VALID.messaggioErrore  } );
				 }
			}
		},
		textMaxSizeTextArea : function (opt) {
			
			var search =  opt.idTab ? $('#'+ opt.idTab).find("textarea") : $("textarea")  ;
				
			 
			
			$.each( search , function( index , itemS ) {
				 var item = $(itemS);
				 
	 	        if ( item.val().length > APPGLOBALCONSTANT.SMS02_CAMPO_TXTAREA_OVER_MAX.size ) {  
	 	        	opt.fieldErrors.push ( { field : item.attr('id') , message : APPGLOBALCONSTANT.SMS02_CAMPO_TXTAREA_OVER_MAX.messaggioErrore  } );
	 	        }  
	 	    })			
		},
		testDateLower : function( opt ){
			if ( opt.dataStart && opt.dataEnd ) {
				 var dStart = moment(opt.dataStart ,'DD/MM/YYYY');
				 var dEnd = moment(opt.dataEnd ,'DD/MM/YYYY');
				 
				 if( dStart.isValid() && dEnd.isValid() ) {
					if ( !dStart.isBefore(dEnd)){
							 opt.fieldErrors.push ( { field : opt.field , message : $filter('replaceParamsMessage')(APPGLOBALCONSTANT.SMS03_CAMPO_DATA_LOWER_NON_VALID.messaggioErrore,opt.paramsMessage)  } );
					}
				 }
			}
		}
	}
}).factory('gestioneConvalidaFabbisognoServicesFactory', function ($http, $rootScope,fileDownloadManager) {
	return {
 		getFabbisognoScuolaPostiComuni : function (form) {
			console.log('gestioneConvalidaFabbisognoServicesFactory.getFabbisognoScuolaPostiComuni ->', form);
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestione-convalida-fabbisogno/convalida.json', form);
		},
		convalidaMassiva  : function (form) {
		    console.log('gestioneConvalidaFabbisognoServicesFactory.convalidaMassiva.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/gestione-convalida-fabbisogno/set-m-convalida-massiva.json', form);
	    },
	    rettificaMassiva  : function (form) {
		    console.log('gestioneConvalidaFabbisognoServicesFactory.rettificaMassiva.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/gestione-convalida-fabbisogno/set-m-rettifica-massiva.json', form);
	    },
	    initReportCompleto : function (form) {
			console.log('monitoraggioCompletoServicesFactory.init-mon-report-completo-base.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/monitoraggio-report/init-mon-report-completo-base.json',form);
	    },
	    loadData : function (form) {
	    	console.log('gestioneConvalidaFabbisognoServicesFactory.loadData ->',form);
	    	return $http.post(APPGLOBALCONSTANT.contexPath+'/gestione-convalida-fabbisogno/get-data.json', form);
	    },convalidaSingola  : function (form) {
		    console.log('gestioneConvalidaFabbisognoServicesFactory.convalidaSingola.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/gestione-convalida-fabbisogno/set-m-convalida-singola.json', form);
	    },
	    rettificaSingola  : function (form) {
		    console.log('gestioneConvalidaFabbisognoServicesFactory.rettificaSingola.json ->');
		    return $http.post(APPGLOBALCONSTANT.contexPath+'/gestione-convalida-fabbisogno/set-m-rettifica-singola.json', form);
	    }
  }
}).factory('gestioneCatalogoDocumentiServicesFactory', function ($http, $rootScope) {
	return {
 		getCatalogoDcoumento : function (progresivoGestioneCatalogoDocumento) {
			console.log('gestioneCatalogoDocumentiServicesFactory.getCatalogoDcoumento/' +progresivoGestioneCatalogoDocumento);
			return $http.get(APPGLOBALCONSTANT.contexPath+'/gestione-catalogo-documento/' + progresivoGestioneCatalogoDocumento);
		},
		salvaCatalogoDcoumento : function (form) {
			console.log('gestioneCatalogoDocumentiServicesFactory.getCatalogoDcoumento ->', form);
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestione-catalogo-documento/set-m-salvaCatalogoDocumento.json', form);
		},
		generaVersione : function (form) {
			console.log('gestioneCatalogoDocumentiServicesFactory.generaVersione ->', form);
			return $http.get(APPGLOBALCONSTANT.contexPath+'/gestione-catalogo-documento/generaVersione');
		},
		caricaDecretiExcel : function (form) {
			console.log('gestioneCatalogoDocumentiServicesFactory.caricaDecretiExcel ->', form);
			return $http.post(APPGLOBALCONSTANT.contexPath+'/gestione-catalogo-documento/set-m-caricaDecretiExcel.json', form);
		}
		
	}
});	
 
