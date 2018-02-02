'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controllers.consultazionePtof.consultazionePtof', [
  'ptof.commons.services',
  'ptof.directives',
  'base64'
]).factory('ptofConsultazionePuntualeFactory', function ($http, $rootScope) {
	return {
		 initConsultazionePtof : function (form) {
				console.log('ptofConsultazionePuntualeFactory.consultazione-ptof.init-consultazione-puntuale-ptof.json ->');
				return $http.post(APPGLOBALCONSTANT.contexPath+'/consultazione-ptof/init-consultazione-puntuale-ptof.json',form);
		 },
     	 loadConsultazionePuntualePtof : function (form) {
		       console.log('ptofConsultazionePuntualeFactory.consultazione-ptof.load-consultazione-puntuale-ptof.json ->');
		       return $http.post(APPGLOBALCONSTANT.contexPath+'/consultazione-ptof/load-consultazione-puntuale-ptof.json',form);
     	 },
	    getDettaglioFabbisogno : function (key) {
		       console.log('ptofConsultazionePuntualeFactory.consultazione-ptof.get-dettaglio-fabbisogno.json ->');
		       return $http.post(APPGLOBALCONSTANT.contexPath+'/consultazione-ptof/get-dettaglio-fabbisogno.json',key);
		}
  }
}).controller('ConsultazionePtofCtrl', function ($scope, $window ,$rootScope, $element ,$timeout,  $base64, $filter , ptofConsultazionePuntualeFactory, ptofCommonsServicesFactory) {
	console.log('ConsultazionePtofCtrl->');


	$scope.vm = {
			form : {
				consultazionePtof : {}

			},
			attribForm : {
				consultazionePtofEmpty : {},
				consultazionePtof : {
					regioniL :[] ,provinceL :[] ,comuniL :[] , tipologiaScuolaL : [], codMeccanografico : ''
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
		    				        //{ title:'Tipologia',data:'tipologiaScuola',cl:''},
		    				        //{ title:'Stato fabbisogno',data:'statoFabbisogno',cl:''},
		    				        { title:'Dettaglio',data:'key',cl:''}
		    				        ],
		    				items : [],
		    				pagineTotali : 0 ,
		    				paginaCorrente : 0,
		    				righeTotali : 0
		    		},
		    		newPage : function (paginaCorrente , sort ) {
		    			   console.log('GestionePtof->newPage');   
	    				   var form = {
	    				    		progresivoDocumento : $scope.vm.form.progressivoGestioneCatalogoDocumento,
	    				    		regione :   $scope.vm.form.consultazionePtof.regione ?  $scope.vm.form.consultazionePtof.regione.value : '' ,      
	    				    		provincia : $scope.vm.form.consultazionePtof.provincia ? $scope.vm.form.consultazionePtof.provincia.value : '',
	    				    		comune :    $scope.vm.form.consultazionePtof.comune ? $scope.vm.form.consultazionePtof.comune.value : '', 
	    				    		tipologiaScuola : $scope.vm.form.consultazionePtof.tipologiaScuola ? $scope.vm.form.consultazionePtof.tipologiaScuola.value : '', 
	    				    		codiceMeccanografico : $scope.vm.form.consultazionePtof.codMeccanografico ? $scope.vm.form.consultazionePtof.codMeccanografico : ''
	    					};
	    				    
	    				    ptofConsultazionePuntualeFactory.loadConsultazionePuntualePtof( {   
	    				        consultazioneFabbisogno : form   //Non imposto paginazione nè orders
	    					 }).success(function (data, status, config, headers) {
	    						 console.log('bsTablePagination->controller->watch -> opts.tabella.items');
	    			             $scope.vm.attribForm.richiestaT.tabella.items          = data.attribForm.richiestaT.items.result;
	    						 $scope.vm.attribForm.richiestaT.tabella.pagineTotali   = data.attribForm.richiestaT.pagineTotali
	    						 $scope.vm.attribForm.richiestaT.tabella.paginaCorrente = data.attribForm.richiestaT.paginaCorrente;
	    						 $scope.vm.attribForm.richiestaT.tabella.righeTotali    = data.attribForm.richiestaT.righeTotali;
	    						 $scope.vm.attribForm.richiestaT.drawPagination = true; 
	    				    	 
	    				     });   
	    				   
	    				   
	    			},
		    		onClickVisualizzaDettaglio : function ( tr ) {
	    				  console.log('visualizzaDettaglio'  + tr.key);
	    				    var progCatDoc = $scope.vm.form.progressivoGestioneCatalogoDocumento;
	    				    var key = tr.key;
	    				  
	    				  $window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-consultazione-puntuale-fabbisogno.do#?key='+encodeURIComponent( $base64.encode( key  + '||' + progCatDoc))   ;	
	    				 
	    			} 

				}//Tabella PTOF PUBBLICATI per paginazione
			}
	};



	//1)inizializzo il form
	var init  = function () {

		console.log('Init della pagina');
		var tempForm =  { keyDocumento : '' ,  keySezione : ''  } ;
		
		 
		ptofConsultazionePuntualeFactory.initConsultazionePtof(tempForm)
		.success(function (data, status, config, headers) {
				$scope.vm.attribForm.consultazionePtof.regioniL =   data.attribForm.REGIONI ;
				$scope.vm.attribForm.consultazionePtof.provinceL =  data.attribForm.PROVINCE ;
    			// $scope.vm.attribForm.consultazionePtof.tipologiaScuolaL = data.attribForm.TIPOLOGIA_SCUOLA ;
    			if($scope.vm.attribForm.consultazionePtof.regioniL.length==1){
					$scope.vm.form.consultazionePtof.regione = {  	 value : $scope.vm.attribForm.consultazionePtof.regioniL[0].value , 
																	 label : $scope.vm.attribForm.consultazionePtof.regioniL[0].label };
    			}
		}).error(function (data, status, config, headers) {
			   console.log('Some error occurred! init');
		});
		 

		 $scope.vm.attribForm.richiestaT.tabella.items  = {};
		 $scope.vm.attribForm.richiestaT.tabella.pagineTotali    = -1;
		 $scope.vm.attribForm.richiestaT.tabella.paginaCorrente  = -1;
		 $scope.vm.attribForm.richiestaT.tabella.righeTotali     = -1;
		 $scope.vm.attribForm.richiestaT.drawPagination = false;
	}


 
	$scope.$watch("vm.form.consultazionePtof.regione", function(newValue, oldValue){
		if($scope.vm.attribForm.consultazionePtof.regioniL.length!=1){
			if ( newValue && newValue!=null ) { 
					var form = {
							value :    newValue.value  , //BeanValueDTO.setValue(elem.getCodReg());
							label :    newValue.label  ,      
				 			tipologica  :{tipo:'PROVINCE'}  		
					};    
					ptofCommonsServicesFactory.getProvince(form)
					.success(function (data, status, config, headers) {
						$scope.vm.attribForm.consultazionePtof.provinceL =  data.attribForm.PROVINCE;
					}).error(function (data, status, config, headers) {
						console.log('Some error occurred! $watch regione'); 
					});	
			} else {
				$scope.vm.attribForm.consultazionePtof.provinceL =[];
		    }
			$scope.vm.attribForm.consultazionePtof.comuniL =[];
			$scope.vm.form.consultazionePtof.provincia  = '';
	 		$scope.vm.form.consultazionePtof.comune  = '';
		}
	});
	
	
	$scope.$watch("vm.form.consultazionePtof.provincia", function(newValue, oldValue){
		if ( newValue && newValue!=null ) {
			var form = {
					value :    newValue.value  , //BeanValueDTO.setValue(elem.getCodReg());
					label :    newValue.label  ,       
		 			tipologica  :{tipo:'COMUNI'}  		
			};    
			ptofCommonsServicesFactory.getComuni(form)
			.success(function (data, status, config, headers) {
				$scope.vm.attribForm.consultazionePtof.comuniL =  data.attribForm.COMUNI ;   	//jQuery.extend(true,{},data.attribForm.COMUNI); ?
			}).error(function (data, status, config, headers) {
				console.log('Some error occurred! $watch provincia'); 
			});	
		}else {
			$scope.vm.attribForm.consultazionePtof.comuniL =[];
	    }
		 $scope.vm.form.consultazionePtof.comune = '';
	});
	


	/**********/
	/*END INIT VM */
	/**********/

	/*********/
   /* AZIONI */
	$scope.ricercaConsultazionePtof = function() {
	    var form = {
	    		progresivoDocumento : $scope.vm.form.progressivoGestioneCatalogoDocumento,
	    		regione :   $scope.vm.form.consultazionePtof.regione ?  $scope.vm.form.consultazionePtof.regione.value : '' ,      
	    		provincia : $scope.vm.form.consultazionePtof.provincia ? $scope.vm.form.consultazionePtof.provincia.value : '',
	    		comune :    $scope.vm.form.consultazionePtof.comune ? $scope.vm.form.consultazionePtof.comune.value : '', 
	    		tipologiaScuola : $scope.vm.form.consultazionePtof.tipologiaScuola ? $scope.vm.form.consultazionePtof.tipologiaScuola.value : '', 
	    		codiceMeccanografico : $scope.vm.form.consultazionePtof.codMeccanografico ? $scope.vm.form.consultazionePtof.codMeccanografico : ''
		};
	    
	    ptofConsultazionePuntualeFactory.loadConsultazionePuntualePtof( {   
	        consultazioneFabbisogno : form   //Non imposto paginazione nè orders
		 }).success(function (data, status, config, headers) {
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
			 $scope.vm.form.consultazionePtof  = $.extend(true, {}, $scope.vm.attribForm.consultazionePtofEmpty );
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
