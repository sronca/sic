'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controllers.monitoraggio.reportCompleto', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64'
]).factory('monitoraggioCompletoServicesFactory', function ($http, $rootScope) {
	return {
		 initReportCompleto : function (form) {
				console.log('monitoraggioCompletoServicesFactory.init-mon-report-completo-base.json ->');
				return $http.post(APPGLOBALCONSTANT.contexPath+'/monitoraggio-report/init-mon-report-completo-base.json',form);
		 },
		 loadReportCompletoBase : function (form) {
			console.log('monitoraggioCompletoServicesFactory.get-mon-report-completo-base.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/monitoraggio-report/get-mon-report-completo-base.json', form);
		},
		loadReportCompletoPageable : function (form) {
			console.log('monitoraggioCompletoServicesFactory.get-mon-report-completo-pageable.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/monitoraggio-report/get-mon-report-completo-pageable.json', form);
		},
		getPtofFile : function (key) {
			console.log('monitoraggioCompletoServicesFactory.getPtofFile ->', key);
			fileDownloadManager.fileDownload({
				url : ''+key ,
				data : { key : key }
			})
			return;
		}
  }																 
}).controller('MonitoraggioReportCompletoCtrl', function ($scope, $window ,$rootScope, $element ,$timeout,  $base64, $filter , ptofCommonsServicesFactory,monitoraggioServicesFactory,monitoraggioCompletoServicesFactory) {
	console.log('MonitoraggioReportCompletoCtrl->');
	
	
	$scope.vm = {
			form : {
				ricercaReportCompleto : {}
	
			},
			attribForm : {
				ricercaReportCompletoEmpty : {},
				ricercaReportCompleto : {
					regioniL :[] ,provinceL :[] ,comuniL :[] , statoPtofL : [], codMeccanografico : ''
				},
				scuolePtofPubblicatoL : [],// lista scuole che hanno pubblicato il PTOF
				richiestaT : {
					 key : {},
		    		 titolo : '',
		    		 lastColShow : true,
		    		 tabella : { 
		    				cols : [
		    				        { title:'Regione',data:'regione',cl:'', ord:'DESC' },
		    				        { title:'Prov',data:'siglaProvincia',cl:'' , ord:'DESC'} ,
		    				        { title:'Comune',data:'comune',cl:'', ord:'DESC' } ,
		    				        { title:'Codice Mec.',  data:'codiceMecUtente', cl:''} ,
		    				        { title:'Denominazione',data:'denominazione',cl:''},
		    				        { title:'Stato',data:'statoDocumentoAsString',cl:''},
		    				        { title:'Data Pubbl.',data:'dataPubblicazione',cl:''},
		    				        { title:'Ptof Parziale',data:'key',cl:''},
		    				        { title:'Ptof Completo',data:'key',cl:''}
		    				        ],
		    				items : [],
		    				pagineTotali : 0 ,
		    				paginaCorrente : 0,
		    				righeTotali : 0 
		    		},
		    		newPage : function (paginaCorrente , sort ) {
		    			   console.log('reportCompleto->newPage');
	    				   var form = {
	    						    progresivoDocumento : $scope.vm.form.progressivoGestioneCatalogoDocumento,
	    				    		regione :   $scope.vm.form.ricercaReportCompleto.regione ?  $scope.vm.form.ricercaReportCompleto.regione.value : '' ,      
	    				    		provincia : $scope.vm.form.ricercaReportCompleto.provincia ? $scope.vm.form.ricercaReportCompleto.provincia.value : '',
	    				    		comune :    $scope.vm.form.ricercaReportCompleto.comune ? $scope.vm.form.ricercaReportCompleto.comune.value : '', 
	    				    		statoPTOF : $scope.vm.form.ricercaReportCompleto.statoPtof ? $scope.vm.form.ricercaReportCompleto.statoPtof.value : '', 
	    				    		codiceMeccanografico : $scope.vm.form.ricercaReportCompleto.codMeccanografico ? $scope.vm.form.ricercaReportCompleto.codMeccanografico : ''
	    					};
	    				  monitoraggioCompletoServicesFactory.loadReportCompletoBase( {   
	    				        reportCompleto : form,
	    				        paginaCorrente :  paginaCorrente
	    					 }).success(function (data, status, config, headers) {
	    						 console.log('bsTablePagination->controller->watch -> opts.tabella.items');
	    			             $scope.vm.attribForm.richiestaT.tabella.items          = data.attribForm.richiestaT.items.result;
	    						 $scope.vm.attribForm.richiestaT.tabella.pagineTotali   = data.attribForm.richiestaT.pagineTotali
	    						 $scope.vm.attribForm.richiestaT.tabella.paginaCorrente = data.attribForm.richiestaT.paginaCorrente;
	    						 $scope.vm.attribForm.richiestaT.tabella.righeTotali    = data.attribForm.richiestaT.righeTotali;
	    						 $scope.vm.attribForm.richiestaT.drawPagination = true; 
	    				    	 
	    				     });  	
	    			},
		    		sortPage : function ( sort ) {
		    			  console.log('Sort della Pagina');
		    			  monitoraggioCompletoServicesFactory.loadReportCompletoPageable(  
		    					  {  reportCompleto :  $scope.vm.form.ricercaReportCompleto, 
		    					     paginaCorrente :  $scope.vm.attribForm.richiestaT.tabella.paginaCorrente,
		    					     sort : sort
		    					 }).success(function (data, status, config, headers) {
		    				     console.log('monitoraggioCompletoServicesFactory->loadReportCompleto');
		    				     $scope.vm.attribForm.richiestaT.tabella.items          = data.attribForm.richiestaT.items.result;
				    		     $scope.vm.attribForm.richiestaT.tabella.pagineTotali   = data.attribForm.richiestaT.pagineTotali
				    		     $scope.vm.attribForm.richiestaT.tabella.paginaCorrente = data.attribForm.richiestaT.paginaCorrente;
				    			 $scope.vm.attribForm.richiestaT.tabella.righeTotali    = data.attribForm.richiestaT.righeTotali;
		    			  });   
		    			 
	    			},
	    			onClickScaricaPtofParziale : function ( tr ) {
	    				  console.log('onClickScaricaPtofParziale'  + tr.key);
	    				  monitoraggioServicesFactory.getPtofFile(tr.key ,'PUBBLICATO_PARZIALMENTE');
	    				 
	    			},
	    			onClickScaricaPtofCompleto : function ( tr ) {
	    				  console.log('onClickScaricaPtofCompleto' + tr.key);
	    				  monitoraggioServicesFactory.getPtofFile(tr.key,'PUBBLICATO_COMPLETO');
	    				  
	    			}
					
					
				}//Tabella PTOF PUBBLICATI per paginazione
			}
	};
	

	
	//1)inizializzo il form
	var init  = function () {
		 
		console.log('Init della pagina');
		var tempForm =  { keyDocumento : '' ,  keySezione : ''  } ;
		monitoraggioCompletoServicesFactory.initReportCompleto(tempForm)
		.success(function (data, status, config, headers) {
			$scope.vm.attribForm.ricercaReportCompleto.regioniL =   data.attribForm.REGIONI  ;
			$scope.vm.attribForm.ricercaReportCompleto.provinceL =  data.attribForm.PROVINCE ;
			$scope.vm.attribForm.ricercaReportCompleto.statoPtofL = data.attribForm.STATO_PTOF ;
			
			if($scope.vm.attribForm.ricercaReportCompleto.regioniL.length==1){
						$scope.vm.form.ricercaReportCompleto.regione = {  value : $scope.vm.attribForm.ricercaReportCompleto.regioniL[0].value , 
																		  label : $scope.vm.attribForm.ricercaReportCompleto.regioniL[0].label };
			}
		}).error(function (data, status, config, headers) {
			console.log('Some error occurred! init'); 
		});	
		
	
		 $scope.vm.attribForm.richiestaT.tabella.items  = {};
		 $scope.vm.attribForm.richiestaT.tabella.pagineTotali   = -1;
		 $scope.vm.attribForm.richiestaT.tabella.paginaCorrente = -1;
		 $scope.vm.attribForm.richiestaT.tabella.righeTotali    = -1;
		 $scope.vm.attribForm.richiestaT.drawPagination = false; 
	}
	
	
	
	$scope.$watch("vm.form.ricercaReportCompleto.regione", function(newValue, oldValue){
		if($scope.vm.attribForm.ricercaReportCompleto.regioniL.length!=1){
			if ( newValue && newValue!=null ) { 
					var form = {
							value :    newValue.value  , //BeanValueDTO.setValue(elem.getCodReg());
							label :    newValue.label  ,      
				 			tipologica  :{tipo:'PROVINCE'}  		
					};    
					ptofCommonsServicesFactory.getProvince(form)
					.success(function (data, status, config, headers) {
						$scope.vm.attribForm.ricercaReportCompleto.provinceL =  data.attribForm.PROVINCE;
					}).error(function (data, status, config, headers) {
						console.log('Some error occurred! $watch regione'); 
					});	
			} else {
				$scope.vm.attribForm.ricercaReportCompleto.provinceL =[];
		    }
			$scope.vm.attribForm.ricercaReportCompleto.comuniL =[];
			$scope.vm.form.ricercaReportCompleto.provincia  = '';
	 		$scope.vm.form.ricercaReportCompleto.comune  = '';
		}
	});
	
	
	$scope.$watch("vm.form.ricercaReportCompleto.provincia", function(newValue, oldValue){
		if ( newValue && newValue!=null ) {
			var form = {
					value :    newValue.value  , //BeanValueDTO.setValue(elem.getCodReg());
					label :    newValue.label  ,       
		 			tipologica  :{tipo:'COMUNI'}  		
			};    
			ptofCommonsServicesFactory.getComuni(form)
			.success(function (data, status, config, headers) {
				$scope.vm.attribForm.ricercaReportCompleto.comuniL =  data.attribForm.COMUNI ;   	//jQuery.extend(true,{},data.attribForm.COMUNI); ?
			}).error(function (data, status, config, headers) {
				console.log('Some error occurred! $watch provincia'); 
			});	
		}else {
			$scope.vm.attribForm.ricercaReportCompleto.comuniL =[];
	    }
		 $scope.vm.form.ricercaReportCompleto.comune = '';
	});
	
	

	
	/**********/
	/*END INIT VM */
	/**********/

	/*********/
   /* AZIONI */
   $scope.ricercaReport = function() {
	    var form = {
	    		progresivoDocumento : $scope.vm.form.progressivoGestioneCatalogoDocumento,
	    		regione :   $scope.vm.form.ricercaReportCompleto.regione ?  $scope.vm.form.ricercaReportCompleto.regione.value : '' ,      
	    		provincia : $scope.vm.form.ricercaReportCompleto.provincia ? $scope.vm.form.ricercaReportCompleto.provincia.value : '',
	    		comune :    $scope.vm.form.ricercaReportCompleto.comune ? $scope.vm.form.ricercaReportCompleto.comune.value : '', 
	    		statoPTOF : $scope.vm.form.ricercaReportCompleto.statoPtof ? $scope.vm.form.ricercaReportCompleto.statoPtof.value : '', 
	    		codiceMeccanografico : $scope.vm.form.ricercaReportCompleto.codMeccanografico ? $scope.vm.form.ricercaReportCompleto.codMeccanografico : ''
		};
	    
	    monitoraggioCompletoServicesFactory.loadReportCompletoBase( {   
	        reportCompleto : form   //Non imposto paginazione nè orders
		 }).success(function (data, status, config, headers) {
		    //$scope.vm.attribForm.scuolePtofPubblicatoL =  data.attribForm.scuolePtofPubblicatoL ;
	    	 console.log('bsTablePagination->controller->watch -> opts.tabella.items');
             $scope.vm.attribForm.richiestaT.tabella.items          = data.attribForm.richiestaT.items.result;
			 $scope.vm.attribForm.richiestaT.tabella.pagineTotali   = data.attribForm.richiestaT.pagineTotali
			 $scope.vm.attribForm.richiestaT.tabella.paginaCorrente = data.attribForm.richiestaT.paginaCorrente;
			 $scope.vm.attribForm.richiestaT.tabella.righeTotali    = data.attribForm.richiestaT.righeTotali;
			 $scope.vm.attribForm.richiestaT.drawPagination = true; 
	    	 
	     });   
   }
   
   $scope.cancellaFiltriReport = function() {
	   $timeout(function() {
			 $scope.vm.form.ricercaReportCompleto  = $.extend(true, {}, $scope.vm.attribForm.ricercaReportCompletoEmpty );
			 $scope.$apply();
			 $scope.vm.attribForm.scuolePtofPubblicatoL = {};
	   })
		 
	   init();
   }
   
   
   $scope.indietro = function () {
	   window.history.back();
	}
   
   

   
   //1)inizializzo il form
   init();

})



