'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controllers.convalida.cruscottoConvalidaSingolaFabbisogno', [
  'ptof.commons.services',                                            
  'ptof.directives',
  'base64'
]).controller('CruscottoConvalidaSingolaFabbisognoCtrl', function ($scope, $window ,$rootScope, $element ,$timeout,  $base64, $filter , ptofCommonsServicesFactory,gestioneConvalidaFabbisognoServicesFactory) {
	console.log('CruscottoConvalidaSingolaFabbisognoCtrl->');
	
	$scope.vm = {
			form : {
				ricercaConvalidaSingola : {}
			},
			attribForm : {
				ricercaConvalidaSingolaEmpty : {},
				ricercaConvalidaSingola : {
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
		    				        { title:'Provincia',data:'provincia',cl:'' , ord:'DESC'} ,
		    				        { title:'Comune',data:'comune',cl:'', ord:'DESC' } ,
		    				        { title:'Codice Mec.',  data:'codiceMecUtente', cl:''} ,
		    				        { title:'Denominazione',data:'denominazione',cl:''},
		    				        { title:'Convalida',data:'key',cl:''},
		    				        { title:'Rettifica',data:'key',cl:''}
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
	    				    		regione :   $scope.vm.form.ricercaConvalidaSingola.regione ?  $scope.vm.form.ricercaConvalidaSingola.regione.value : '' ,      
	    				    		provincia : $scope.vm.form.ricercaConvalidaSingola.provincia ? $scope.vm.form.ricercaConvalidaSingola.provincia.value : '',
	    				    		comune :    $scope.vm.form.ricercaConvalidaSingola.comune ? $scope.vm.form.ricercaConvalidaSingola.comune.value : '', 
	    				    		codiceMeccanografico : $scope.vm.form.ricercaConvalidaSingola.codMeccanografico ? $scope.vm.form.ricercaConvalidaSingola.codMeccanografico : '',
	    				    		denominazione : $scope.vm.form.ricercaConvalidaSingola.denominazione ? $scope.vm.form.ricercaConvalidaSingola.denominazione : ''	
	    				   };
	    				   gestioneConvalidaFabbisognoServicesFactory.loadData({   
	    					   convalidaFabbisognoSingolaScuolaFiltro : form,
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
		    			  gestioneConvalidaFabbisognoServicesFactory.loadReportCompletoPageable(  
		    					  {  reportCompleto :  $scope.vm.form.ricercaConvalidaSingola, 
		    					     paginaCorrente :  $scope.vm.attribForm.richiestaT.tabella.paginaCorrente,
		    					     sort : sort
		    			  }).success(function (data, status, config, headers) {
		    				     console.log('gestioneConvalidaFabbisognoServicesFactory->loadReportCompleto');
		    				     $scope.vm.attribForm.richiestaT.tabella.items          = data.attribForm.richiestaT.items.result;
				    		     $scope.vm.attribForm.richiestaT.tabella.pagineTotali   = data.attribForm.richiestaT.pagineTotali
				    		     $scope.vm.attribForm.richiestaT.tabella.paginaCorrente = data.attribForm.richiestaT.paginaCorrente;
				    			 $scope.vm.attribForm.richiestaT.tabella.righeTotali    = data.attribForm.richiestaT.righeTotali;
		    			  });   
		    			 
	    			},
	    			onClickConvalida : function ( tr ) {
	    				console.log('onClickConvalida'  + tr.key);
	    				var form = {
	  							key : tr.key
  						};
    				    
	    				var currentTr = tr;
	    				
  						var sms = {
  							title : "ATTENZIONE",
  							text : "Si sta procedendo alla convalida si vuole proseguire?",
  							type : "CONFERMA",
  							successRunFn : function() {
	  							gestioneConvalidaFabbisognoServicesFactory
	  									.convalidaSingola(form).success(
	  											function(data, status, config,
	  													headers) {
	  												console.log(data);
	  												$("#" + currentTr.key).remove();
	  											});
	  						}
  						};
  						$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
	    			},
	    			onClickRettifica : function ( tr ) {
	    				console.log('onClickRettifica'  + tr.key);
	    				var form = {
	  							key : tr.key
	  					};

	    				var currentTr = tr;
	    				
	    				var sms = {
  							title : "ATTENZIONE",
  							text : "Si sta procedendo alla rettifica si vuole proseguire?",
  							type : "CONFERMA",
  							successRunFn : function() {
	  							gestioneConvalidaFabbisognoServicesFactory
	  									.rettificaSingola(form).success(
	  											function(data, status, config,
	  													headers) {
	  												console.log(data);
	  												$("#" + currentTr.key).remove();
	  											});
	  						}
  						};
  						$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
	    			}
				}//Tabella PTOF PUBBLICATI per paginazione
			}
	};
	
	//1)inizializzo il form
	var init  = function () {
		console.log('Init della pagina');
		var tempForm =  { keyDocumento : '' ,  keySezione : ''  } ;
		gestioneConvalidaFabbisognoServicesFactory.initReportCompleto(tempForm)
		.success(function (data, status, config, headers) {
			$scope.vm.attribForm.ricercaConvalidaSingola.regioniL =   data.attribForm.REGIONI  ;
			$scope.vm.attribForm.ricercaConvalidaSingola.provinceL =  data.attribForm.PROVINCE ;
			$scope.vm.attribForm.ricercaConvalidaSingola.statoPtofL = data.attribForm.STATO_PTOF ;
			
			if($scope.vm.attribForm.ricercaConvalidaSingola.regioniL.length==1){
						$scope.vm.form.ricercaConvalidaSingola.regione = {  value : $scope.vm.attribForm.ricercaConvalidaSingola.regioniL[0].value , 
																		    label : $scope.vm.attribForm.ricercaConvalidaSingola.regioniL[0].label };
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
	
	$scope.$watch("vm.form.ricercaConvalidaSingola.regione", function(newValue, oldValue){
		if($scope.vm.attribForm.ricercaConvalidaSingola.regioniL.length!=1){
			if ( newValue && newValue!=null ) { 
					var form = {
							value :    newValue.value  , //BeanValueDTO.setValue(elem.getCodReg());
							label :    newValue.label  ,      
				 			tipologica  :{tipo:'PROVINCE'}  		
					};    
					ptofCommonsServicesFactory.getProvince(form)
					.success(function (data, status, config, headers) {
						$scope.vm.attribForm.ricercaConvalidaSingola.provinceL =  data.attribForm.PROVINCE;
					}).error(function (data, status, config, headers) {
						console.log('Some error occurred! $watch regione'); 
					});	
			} else {
				$scope.vm.attribForm.ricercaConvalidaSingola.provinceL =[];
		    }
			$scope.vm.attribForm.ricercaConvalidaSingola.comuniL =[];
			$scope.vm.form.ricercaConvalidaSingola.provincia  = '';
	 		$scope.vm.form.ricercaConvalidaSingola.comune  = '';
		}
	});
	
	$scope.$watch("vm.form.ricercaConvalidaSingola.provincia", function(newValue, oldValue){
		if ( newValue && newValue!=null ) {

			var form = {
					value :    newValue.value  , //BeanValueDTO.setValue(elem.getCodReg());
					label :    newValue.label  ,       
		 			tipologica  :{tipo:'COMUNI'}  		
			};    
			ptofCommonsServicesFactory.getComuni(form)
			.success(function (data, status, config, headers) {
				$scope.vm.attribForm.ricercaConvalidaSingola.comuniL =  data.attribForm.COMUNI ;   	//jQuery.extend(true,{},data.attribForm.COMUNI); ?
			}).error(function (data, status, config, headers) {
				console.log('Some error occurred! $watch provincia'); 
			});	
		}else {
			$scope.vm.attribForm.ricercaConvalidaSingola.comuniL =[];
	    }
		 $scope.vm.form.ricercaConvalidaSingola.comune = '';
	});
	
	/**********/
	/*END INIT VM */
	/**********/

	/*********/
   /* AZIONI */
   $scope.ricercaReport = function() {
	    var form = { 
	    		convalidaFabbisognoSingolaScuolaFiltro: {
		    		progresivoDocumento : $scope.vm.form.progressivoGestioneCatalogoDocumento,
		    		regione :   $scope.vm.form.ricercaConvalidaSingola.regione ?  $scope.vm.form.ricercaConvalidaSingola.regione.value : '' ,      
		    		provincia : $scope.vm.form.ricercaConvalidaSingola.provincia ? $scope.vm.form.ricercaConvalidaSingola.provincia.value : '',
		    		comune :    $scope.vm.form.ricercaConvalidaSingola.comune ? $scope.vm.form.ricercaConvalidaSingola.comune.value : '', 
		    		codiceMeccanografico : $scope.vm.form.ricercaConvalidaSingola.codMeccanografico ? $scope.vm.form.ricercaConvalidaSingola.codMeccanografico : '',
		    		denominazione : $scope.vm.form.ricercaConvalidaSingola.denominazione ? $scope.vm.form.ricercaConvalidaSingola.denominazione : ''
	    		}
		};
	    
	    gestioneConvalidaFabbisognoServicesFactory.loadData(form).success(function (data, status, config, headers) {
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
			 $scope.vm.form.ricercaConvalidaSingola  = $.extend(true, {}, $scope.vm.attribForm.ricercaConvalidaSingolaEmpty );
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



