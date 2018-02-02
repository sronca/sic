'use strict';

/* Directives */

angular.module('ptof.directives',
['RecursionHelper','base64','ptof.commons.services']) 
.directive('bsTxtmax', function () {
        return {
        	restrict: 'A',
            scope: {
            	value :'=ngModel',
            	max:'=bsTxtmax'
            },
            controller: function ( $scope  ) {
            	var max = ( $scope.max )  ? $scope.max : APPGLOBALCONSTANT.SMS02_CAMPO_TXTAREA_OVER_MAX.size;
            	$scope.$watch('value', function(newVal, oldVal) {
            		  if(newVal && newVal.length > max ) {       
            		    $scope.value = oldVal;
            		  }
               });
            }
}})
.directive("bsTablePagination", function() {
    return {
        scope: {opts: '=',  targetDialog:'='},
        template : '<h3>{{ opts.titolo }}  </h3>'+
'<div class="table-responsive">'+ 
   '<table class="table table-bordered table-striped" id= "tab_{{ opts.key }}">'+
				'<thead>'+
					'<tr ng-if ="opts.tabella.colsHead" >'+
						'<th colspan="{{ th.span }}"  ng-repeat="th in opts.tabella.colsHead" > {{ th.title }}   </th>'+
					'</tr>'+
					'<tr >'+
						//'<th ng-repeat="th in opts.tabella.cols"> <button ng-click="sort( th )"  class="button-testata-tabella">{{ th.title }} <span  ng-if="$index != opts.tabella.cols.length-1" class="icon miur-triangle{{ th.ord == undefined ? \'\' : th.ord ==\'DESC\'  ? \'-down\' :\'-up\' }}"></span></button> </th>'+   
						 '<th ng-repeat="th in opts.tabella.cols">{{ th.title }}</th>'+ 
					'</tr>'+
				'</thead>'+
				'<tbody>'+
				'<tr ng-repeat="tr in opts.tabella.items"  >'+
					'<td ng-repeat="td in opts.tabella.cols"   > '+
					 	'<div ng-if="!td.applyTrunk && !(td.iconDecorator) && $index < opts.tabella.cols.length-2" class="pop-div" >' + 
					 	 	'{{  { o:tr , s:td.data } | objectToString   }} '+ 
				 		'</div>' +
	
	  			         '<div  ng-if="$index == opts.tabella.cols.length-2  && opts.lastColShow===true " style="white-space: nowrap;" >' +  
	  			         '<a ng-show="tr.statoDocumento==\'PUBBLICATO_PARZIALMENTE\' || tr.statoDocumento==\'PUBBLICATO_COMPLETO\'" data-toggle="popover" data-placement="top" data-content="parziale" class="icon miur-dettaglio" ng-click="scaricaPtofParziale( tr )"  href="#"></a>&nbsp;' +     
	  			         '</div>' +
				 		 
				 		 '<div  ng-if="$index == opts.tabella.cols.length-1 && opts.lastColShow===true " style="white-space: nowrap;" >' +  
	  			          '<a ng-show="tr.statoDocumento==\'PUBBLICATO_COMPLETO\'"     data-toggle="popover" data-placement="top" data-content="completo" class="icon miur-dettaglio" ng-click="scaricaPtofCompleto( tr )"  href="#"></a>' + 		 
				 		 '</div>' +
				 		
					 '</td>'+
				'</tr>'+
		   '</tbody>'+
  '</table>'+
'</div>'+
'<div class="table-boot-pagination"></div>',
		controller: function ( $scope, $rootScope, $element ,$window ,$timeout,$log  ) {
 		       			// ON LOAD FIRST
 		       			var  maxVisible =10,
 		       			        drawPagina = function () {
 		       			            if ( $scope.opts.drawPagination  ) {
 		       						$timeout(function () {
			 		       				
 		       						 if (   $scope.opts.tabella.items && $scope.opts.tabella.items.length >0) {
 		       						 $('.table-boot-pagination').unbind("page");	 
 		       						 $('.table-boot-pagination').show();
 		       							$('.table-boot-pagination').bootpag({
					    				    total:  $scope.opts.tabella.pagineTotali,
					    				    page:  $scope.opts.tabella.paginaCorrente ,
					    				    maxVisible: maxVisible,
					    				    leaps: true,
					    				    firstLastUse: true,
					    				    wrapClass: 'pagination',
					    				    activeClass: 'active',
					    				    disabledClass: 'disabled',
					    				}).on("page", function(event, num){
					    					
					    					 var sort = [];
					 		    			$.each( $scope.opts.tabella.cols, function ( k,v ) {
					 		    					   if ( angular.isDefined(v.ord)   ) {
					 		    						 sort.push ( { nome : v.data  , dir : v.ord  }  );
					 		    					  } 
					 		    			});
					    					
					    				    $scope.opts.newPage(  num , sort ) ;
					    				    $(this).bootpag({
			 	    				    	     total: $scope.opts.tabella.pagineTotali  
			 	    				        });
					    				});
 		       						
 		       						 } else {
 			 		       			   $('.table-boot-pagination').hide()
 			 		       			  }

 		       						})
 		       			            }	
 		       			};
 		       			
 		       			
 		       			$scope.$watch('opts.drawPagination',   drawPagina() ) 
 		       			
 		         	    $scope.$watch('opts.tabella.items', 
 		                     function (  newVal , oldVal ){
 		         	    	  $timeout(function () {
 		         	    		    if ( $scope.opts.tabella.items && $scope.opts.tabella.items.length >0 )
 		 		       				$($element).find('[data-toggle="popover"]').popover({
				    				    trigger: 'hover'
				     				});	  
 		              		  });
 		              		  drawPagina();
 		              	});
 		         	    
 		         	    // ACTION
 		         	   $scope.sort = function ( col ) {
 		         	    	$log.debug('bsTablePagination->controller->sort');
 	    				    var sort = [];
 		    				$.each( $scope.opts.tabella.cols, function ( k,v ) {
 		    					  
 		    					   if ( col.data == v.data  ) {
 		    						 v.ord = !angular.isDefined(v.ord) || v.ord == 'ASC' ? 'DESC' :'ASC'; 
 		    						 sort.push ( { nome : v.data  , dir : angular.isDefined(v.ord)? v.ord : 'ASC'  }  );
 		    					  } else {
 		    						 v.ord = undefined ;
 		    					  }
 		    				});
 		    				$scope.opts.sortPage( sort ) ;
 		         	    }
 		         	
 		         	  $scope.scaricaPtofParziale = function ( tr ) {
 		         		    $scope.opts.onClickScaricaPtofParziale( tr ) ;
 		         	   }
 		         	   
 		         	  $scope.scaricaPtofCompleto = function ( tr ) {
		         		    $scope.opts.onClickScaricaPtofCompleto( tr ) ;
		         	  }
 		         	  
 		         	   
		         	  $scope.onClickCol = function ( tr, td ) {
		         	    	 if ( angular.isDefined ( td.action ) ) {
	 		         	    	 td.action.fnLoad( tr, td  ) ;
	 		         	    	 $('#'+  td.action.openDialogId ).modal('show');
		         	    	 }
		         	  }
 		         	   
		 }// controller
    };
})
.directive('sidilogout', function () {
        return {
        	replace: true,
            restrict: 'A',
            template :' <a ng-click="btlogout()"  href="javascript:void(0)" ><span class="miuricon miur-logout"></span> Torna al SIDI</a>  ',
            scope: {
            },
            controller: function ( $scope, $window , ptofCommonsServicesFactory ) {
            	$scope.btlogout = function (){
	            	ptofCommonsServicesFactory.logout()
	            	.success(function (data, status, config, headers) {
						console.log('Response from server: ' + data);
						$window.location.href = data;
					})
					.error(function (data, status, config, headers) {
						console.log('Some error occurred!'); 
					});
            	}
            }
}})
 
.directive('dateTimePicker', function ($rootScope) {
        return {
            require: '?ngModel',
            restrict: 'AE',
            scope: {
            	format: '@',
                model : '=ngModel'
            },
            link: function (scope, elem, attrs) {

            	$(elem).formatter({
          		  'pattern': '{{99}}/{{99}}/{{9999}}',
          		  'persistent': false
                }).on('blur', function () {
                  console.info('this', this);
                  console.info('scope', scope);
                  console.info('attrs', attrs);
                  var tm = $(elem).val();  
                  if ( tm ) {
                      scope.model =  tm  ;
                  } else {
                  	scope.model = null;
                  }
                  scope.$apply();
                });   
            }// LINK
}})
.directive('faMessaggio',  ['$rootScope','$window' ,function ($rootScope,$window){   
        return {
            restrict : 'A',
            scope : {},
            link: function (scope, elm, attrs) {
            	$(elm).html("");
	        	$rootScope.$on("EVENT_SENDMESSAGE", function (event, sms) {
	        		$(elm).html("");
	        		$( '.ptf-error-field' ).removeClass('ptf-error-field').data("title",''); 
	        		if ( sms.type =='CLEAN'){
	        			$( '.ptf-error-field' ).removeClass('ptf-error-field').data("title",'');
	        		}
	        		
	        		if( sms.type == "KO-SISTEMA") {
	        			$( '.ptf-error-field' ).removeClass('ptf-error-field').data("title",'');
		        		
	        			sms = {
			        		 title :"ERRORE",
			        		 text :"Sistema momentanemente non disponibile, riprovare piu' tardi",
		            		 type :"KO"
	        			}	 
	        		} 
	        		
	        		if( sms.type == "OK-STANDARD") {
	        			$( '.ptf-error-field' ).removeClass('ptf-error-field').data("title",'');
		        		
	        			sms = {
	        					  title :"MESSAGGIO",
	        		           	  text :"Operazione effettuata con successo",
	        		       		  type :"OK"
	        		      };
	        		} 
	        		
	        		if( sms.type == "OK") {
							 $('.modal').not($(elm)).each(function () {
							        $(this).modal('hide');
							 });

		        			var tempH =' <div  class="modal fade" role="dialog" > <div class="modal-dialog"> <div class="modal-content">  '+
	 	                   ' <div class="modal-body">  <div class="alert alert-success" >  <strong>'+sms.title+'</strong> : '+sms.text+'  </div> </div> '+
	 	                   ' <div class="modal-footer">'+
	 	                   '  <label  for="inform" data-dismiss="modal" class="chiudi">  CHIUDI </label>'+
	 	                   ' </div> </div> </div>';

 	    	  
						 $(elm).append ( tempH );
						 
						 // CHIUDE TUTTE LE FINESTRE MODAL
						 $('.modal').not($(elm)).each(function () {
						        $(this).modal('hide');
						 });
						 $(elm).find('.modal').modal('show');
						 if ( angular.isDefined( sms.onCloseRunFn ) ) {
						  $(elm).find('.chiudi').click(function (e) {								  
		      	    		sms.onCloseRunFn();
						  });
						 }
	        		
	        		} else   if(  sms.type == "CONFERMA" && sms.text ) {
	        	    	 // SUCCESSO valorizzato solo text
						 $('.modal').not($(elm)).each(function () {
						        $(this).modal('hide');
						 });
						 
	        	    	var tempH =' <div  class="modal fade" role="dialog" > <div  class="modal-dialog dgconferma"> <div class="modal-content">  '+
	        	                   ' <div class="modal-body">  <div class="alert alert-warning" >  <strong>'+sms.title+'</strong> : '+sms.text+'  </div> </div> '+
	        	                   ' <div class="modal-footer">'+
	        	                   '  <label  for="inform" data-dismiss="modal" class="">  ANNULLA </label>'+
	        	                   '  <label  for="inform" data-dismiss="modal" class="conferma" >  CONFERMA </label>'+
	        	                   ' </div> </div> </div>';

	        	    	  
						 $(elm).append ( tempH );
						 $(elm).find('.modal').modal('show');
 	        	    	 $(elm).find('.conferma').click(function (e) {
 	        	    		$('.modal-backdrop').remove(); // work dirt
 	        	    		sms.successRunFn();
 	        	    	 });
	        	    
	        	    } else if(  sms.type == "KO" && sms.text ) {
	        	    	var tempH =' <div  class="modal fade" role="dialog" > <div class="modal-dialog"> <div class="modal-content">  '+
	        	                   ' <div class="modal-body">  <div class="alert alert-danger" >  <strong>'+sms.title+'</strong> : '+sms.text+'  </div> </div> '+
	        	                   ' <div class="modal-footer">'+
	        	                   '  <label  for="inform" data-dismiss="modal" class="chiudi">  CHIUDI </label>'+
	        	                   ' </div> </div> </div>';

	        	    	  
						 $(elm).append ( tempH );
						 // CHIUDE TUTTE LE FINESTRE MODAL
						 $('.modal').not($(elm)).each(function () {
						        $(this).modal('hide');
						 });
						 $(elm).find('.modal').modal('show');
						 if ( angular.isDefined( sms.onCloseRunFn ) ) {
						  $(elm).find('.chiudi').click(function (e) {
	 	        	    		sms.onCloseRunFn();
	 	        	      });
						 }
	        	    } else if ( sms.type == "KO" && sms.errorsFunction ) { 
	        	    	// errore  
		        	    var tempH =' <div class="modal fade" role="dialog" >'+
		        	    	       ' <div class="modal-dialog"> ' + 
		        	               ' <div class="modal-content"> '+
	  	                           ' <div class="modal-body"> '+ 
		        	               ' <div class="alert alert-danger" >  <strong>'+sms.title+'</strong> ';
		        	         
		        	     tempH = tempH + ' <div class=\'row\' >  <div class="col-xs-12 col-md-12 text-left" > '+ sms.errorsFunction.message +' </div> </div > ';
        	    		 tempH = tempH + ' <div class=\'row\' > <div class="col-xs-4 col-md-4 text-left" > </div> <div class="col-xs-8 col-md-8 text-left" > <ui>';
				        	    	
					        	     $.each(sms.errorsFunction.fieldErrors , function (index, value) {
				 		        	     tempH= tempH + '  <li>' + value.message   + '</li>';
						        	 });
				        	    	 
 		        	     tempH = tempH + ' </ui> </div >  </div >';
					        	     
			        	 tempH= tempH+ ' </div>  ' + // body
		        	           ' <div class="modal-footer">'+
		  	                   '  <label  for="inform" data-dismiss="modal" class="chiudi">  CHIUDI </label>'+
		  	                   ' </div> </div> </div> </div> ';// footer content 
						    
		        		 $(elm).append ( tempH );
						 // CHIUDE TUTTE LE FINESTRE MODAL
						 $('.modal').not($(elm)).each(function () {
						        $(this).modal('hide');
						 });
						 $(elm).find('.modal').modal('show');
						 $(elm).find('.chiudi').click(function (e) {
							  if ( angular.isDefined( sms.onCloseRunFn ) ) {
								  sms.onCloseRunFn(); 
							  };
						  });
						 
						 
	        	    } else if( sms.type == "KO" && sms.fieldErrors )  {
	        	    	// errore valorizzarofieldErrors
	        	    	
	        	    		   $.each(sms.fieldErrors  , function (index, element) {
	        	    	              var $element = $('#'+element.field);
	        	    	              $element.attr("title", element.message)
	        	    	                      .addClass("ptf-error-field"); // Create a new tooltip based on the error messsage we just set in the title
	        	    	          });
	        	    		   
	        	    		   $('.ptf-error-field').first().focus();
	        	    		 
					 };
	        	     
               });// watch
            }
        };

}])
.directive("bsTablesmall", function() {
    return {
        scope: {opts: '=', addtargetdialog:'='},
        template: 
            '<h3>{{ opts.titolo }} </h3>'+
              '<div class="table-responsive"> <table class="table table-bordered table-striped" id= "tab_{{ opts.key }}">'+
				'<thead>'+
				'<tr ng-if ="opts.tabella.colsGroupHead" >'+
				  '<th colspan="{{ th.span }}"  ng-repeat="th in opts.tabella.colsGroupHead" class="{{th.cl}}"> {{ th.title }}   </th>'+
				'</tr>'+
				'<tr ng-if ="opts.tabella.colsHead" >'+
				  '<th colspan="{{ th.span }}"  ng-repeat="th in opts.tabella.colsHead" class="{{th.cl}}"> {{ th.title }}   </th>'+
				'</tr>'+
				
				'<tr >'+
				  '<th ng-repeat="th in opts.tabella.cols" > {{ th.title }}   </th>'+
				'</tr>'+
				'</thead>'+
				'<tbody>'+
				'<tr  id= "{{ opts.key + \'_\' + tr.key }}"  ng-repeat="tr in opts.tabella.items"  >'+
				  '<td ng-repeat="td in opts.tabella.cols"   > '+ 
				  ' {{ ( $index < opts.tabella.cols.length-1 || ( $index == opts.tabella.cols.length-1 && opts.btnotvis ) ) ? ( { o:tr , s:td.data } | objectToString ) :"" }}'+ 
				  ' <div  ng-if="$index == opts.tabella.cols.length-1" style="white-space: nowrap;" > '+
				  '     <span ng-if= "!opts.btnotcanc && ( tr.cancellabile==undefined || tr.cancellabile==true ) " class="icon miur-trash-small-1" ng-click="cancella( tr )" ></span>  '+  
				  '     <span ng-if= "!opts.btnotmod && ( tr.modificabile==undefined || tr.modificabile==true )   " class="icon miur-pencil-small" ng-click="modifica( tr  )" ></span> '+   
				  '     <span ng-if= "!opts.btnotvis && ( tr.visualizzabile==undefined || tr.visualizzabile==true ) " class="icon miur-eye-small" ng-click="visualizza( tr  )" ></span> '+
				  '  </div> '+
				  ' </div> '+ 
				  ' </td>'+
				'</tr>'+
				'</tbody>'+
			'</table></div>'+
		 	'<p ng-if= "!opts.btnotadd" class="text-right">'+
		 		  '<label ng-click="aggiungi()" for="inform" class=""><span class="icon miur-add-small"></span> AGGIUNGI</label>'+
	 		 '</p>',
		 controller: function ( $scope, $rootScope, $element ,$window ,$timeout , gestioneDocServicesFactory ) {
			 $scope.opts.selected = [];
			 
			 // TASTO OK SULLA DIALOG ( AGG / MOD )
			 $scope.opts.clickBtOK = function (){

				  console.log( "start clic");
				  if( $scope.opts.controlliSuperati() )
				  {  // supero i controlli  
					  
					   // IMPOSTO LA FORM INPUT
				       $scope.opts.componente.items= [ $scope.opts.newItem ];
				       $scope.opts.form.componenti = [];
				 	   $scope.opts.form.componenti.push ( $scope.opts.componente );
					  
						gestioneDocServicesFactory.saveItemInComponenteSezione( $scope.opts.form )
						.success(function (data, status, config, headers) {
							console.log('Response from server: ' + data);
							  
							  if ( $scope.opts.dialogAction =='I' ) {
								  $scope.opts.tabella.items.push( data.attribForm.componente.items[0] );
					    	  } else {
					    		  // ASSUMO CHE modifico un solo oggetto per volta quindi items[0]!!!
					    		  $scope.opts.tabella.items [ $scope.opts.itemIndexMod ] = data.attribForm.componente.items[0];
								  $scope.opts.selected = [];
					    	  }
							  //$scope.$apply();
							  $('#'+$scope.addtargetdialog).modal('hide');
						})
						.error(function (data, status, config, headers) {
							console.log('Some error occurred!'); 
						});
					  
					  
					  
				  }  
			 
			 }
 
 			 // TASTO visualizza SOPRA LA TABELLA
			 $scope.visualizza = function ( tr ) {
				 var selected = tr.key;
				 
				 $.each ( $scope.opts.tabella.items , function (j,e){
							if ( e && e.key == selected ){
								$scope.opts.newItem = jQuery.extend(true,{}, tr );
								$scope.opts.dialogAction ='V';
								$scope.opts.itemIndexMod = j;
							}
				 });
			     $rootScope.$broadcast("EVENT_SENDMESSAGE", {type: 'CLEAN'});
			     $('#'+$scope.addtargetdialog).modal('show');

			 }		

			 
			 // TASTO AGGIUNGI SOPRA LA TABELLA
			 $scope.aggiungi = function () {
				 if ( $scope.opts.loadDialog() ){
					    $rootScope.$broadcast("EVENT_SENDMESSAGE", {type: 'CLEAN'});
						$scope.opts.newItem = {};
						$scope.opts.dialogAction ='I';
						$('#'+$scope.addtargetdialog).modal('show');
				 }
			 }		
			 
			 // TASTO MODIFICA SOPRA LA TABELLA
			 $scope.modifica = function ( tr ) {
					 
				 var selected = tr.key;
				   //$.each ( $scope.opts.selected , function ( i,selected ){
			    	    $.each ( $scope.opts.tabella.items , function (j,e){
							if ( e && e.key == selected ){
								$scope.opts.newItem = jQuery.extend(true,{}, tr );
								$scope.opts.dialogAction ='M';
								$scope.opts.itemIndexMod = j;
							}
					//})
					});
					$rootScope.$broadcast("EVENT_SENDMESSAGE", {type: 'CLEAN'});
					$('#'+$scope.addtargetdialog).modal('show');
			 }
			 
			 // TASTO CANCELLA SOPRA LA TABELLA
			 $scope.cancella = function ( tr ) {
				       var successRunFn = function () {
				                       $scope.opts.selected = [tr.key];
								       var itemsDaCancellare =[];
								     
								       // CERCO TUTTI GLI ITEMS DA CANCELLARE
								       $.each ( $scope.opts.selected , function ( i,selected ){
								    	    $.each ( $scope.opts.tabella.items , function (j,e){
												if ( e && e.key == selected ){
													itemsDaCancellare.push (e);
												}
										})
										});
								        
								       $scope.opts.componente.items= itemsDaCancellare;
								       $scope.opts.form.componenti = [];
								 	   $scope.opts.form.componenti.push ( $scope.opts.componente );
								 	   
										gestioneDocServicesFactory.deleteItemInComponenteSezione( $scope.opts.form )
										.success(function (data, status, config, headers) {
											console.log('Response from server: ' + data);
				
									        // Rimuovo gli oggetti dalla key
									        $.each ( $scope.opts.selected , function ( i,selected ){
									    	    $.each ( $scope.opts.tabella.items , function (j,e){
													if ( e && e.key == selected ){
														$scope.opts.tabella.items.splice(j, 1);
													}
											})});
											$scope.opts.selected = [];
											
								    	  
										})
										.error(function (data, status, config, headers) {
											console.log('Some error occurred!'); 
										});
				       };
						  
			        	var sms = {
				           		 title :"ATTENZIONE",
				           		 text :"Si sta procedendo alla cancellazione si vuole proseguire?",
				           		 type :"CONFERMA",
				           		 successRunFn : successRunFn
				        };
						$rootScope.$broadcast("EVENT_SENDMESSAGE", sms );
			 }

			 // CHECK SU OGNI RIGA
			 $scope.check = function ( tr , idEl) {
				 var checkEl = $( '#'+idEl ) ;
				 if ( checkEl.is(':checked') ) {
					 $scope.opts.selected.push( tr.key ) ;
				 } else {
					 var idx = $.inArray( tr.key , $scope.opts.selected   ); 
					 $scope.opts.selected.splice(idx, 1);
				 }
			 }	 
				 
		 }// controller
    };
})
.directive("bsSidimenu", function(RecursionHelper) {
    return {
        scope: {item: '=',liv : '=',idliv : '='},
        template: 
        	'<li  ng-repeat="item2 in item.subItems">   '+
        	'       <a ng-if= " item2.tipoItem == \'FOGLIA\' "  href="{{ item2.url }}"  > 	 {{ item2.testo }} 	 </a> '+
        	'       <a ng-if= " item2.tipoItem == \'RAMO\' " href="javascript:void(0)" data-toggle="collapse" data-target="#idlevel-{{idliv}}-{{$index}}"> '+
        	'		{{  item2.testo }} <span	class="switchicon sidebar-icon miuricon miur-freccia-giu" '+
        	'		                 	data-switchicon-odd="miur-freccia-giu" '+
        	'		                    data-switchicon-even="miur-freccia-su"></span>'+
        	'       </a> '+
        	'<div ng-if= " item2.tipoItem == \'RAMO\' " id="idlevel-{{idliv}}-{{$index}}" class="multilevel-menu collapse">'+
			'<ul class="menu-level-{{liv}}">'+
                  ' <div bs-sidimenu ="" item = "item2" liv ="liv+1" idliv ="item2.idItem"  ></div > '+
	       ' </ul>  '+
	       '  </div> </li>' ,
        compile: function(element) {
            return RecursionHelper.compile(element, function(scope, iElement, iAttrs, controller, transcludeFn){
                // Define your normal link function here.
                // Alternative: instead of passing a function,
                // you can also pass an object with 
                // a 'pre'- and 'post'-link function.
            });
        }
    };
})
.directive("bsListastasezioni", function(RecursionHelper ,$base64, $timeout ) {
    return {
    	replace: false,
        scope: {sezioni: '=' , liv : '=' , key : '=', doc : '=', optnav :'='  },
        template:
        	' <ul class="list-group collapse"  ng-class="liv == 3 ? \'list-final\' : \'\'" id="collapse-{{ keyDocSezione }}"> '+
        	'  <li  ng-repeat="x in sezioni"  class="list-group-item"    >   '+
        	'     <div bs-itemsezione=""  data-liv = "liv" data-sezione = "x" data-doc ="doc" data-optnav="optnav" >  </div>' +
        	'     <span ng-if = "x.statoSezione == \'COMPILATA\'"  class="icon miur-check-1" ></span> <span ng-if ="x.statoSezione!=\'COMPILATA\'"     class="icon miur-setting-gear"></span>  '+
 	        '     <div data-doc = "doc" ng-if = "x.ramo==true" bs-listastasezioni=""  data-optnav="optnav"  data-key = "x.key" data-sezioni = "x.sottoSezione"  data-liv = "liv+1" ></div>'+
	        '  </li>'+
	        '</ul>' ,
        compile: function(element , scope ) {

        	return RecursionHelper.compile(element, function(scope, iElement, iAttrs, controller, transcludeFn){
        		scope.keyDocSezione = encodeURIComponent ( $base64.encode( scope.doc +'||'+ scope.key ));
                // Define your normal link function here.
                // Alternative: instead of passing a function,
                // you can also pass an object with 
                // a 'pre'- and 'post'-link function.
 
            });
        }
    };
})
.directive("bsItemsezione", function( $base64 ,$compile ) {
    return {
    	replace: true,
        scope: { sezione : '=' ,  liv : '=' , doc : '=' , optnav :'='   },
        template: 
        	'<a ng-click="toggleScroll()" class="collapsed"  ng-class="liv == 1 ? \'first-level\' : liv == 2 ? \'second-level\' : \'\'" data-toggle="collapse" ng-href ="#collapse-{{keyDocSezione}}" >{{ sezione.codice }} {{ sezione.nome }} <span ng-if= "sezione.ramo==false"  class="icon miur-freccia-avanti" > </span> <span ng-if= "sezione.ramo==true"  class="icon miur-chevron-down"></span>  </a>',
        controller: function ( $scope, $element ,$window , $timeout, $rootScope ) {
        	    $scope.keyDocSezione = encodeURIComponent ( $base64.encode( $scope.doc +'||'+ $scope.sezione.key ));
        		$timeout(function () {
        			 if ( $scope.optnav  && $scope.optnav.keyforfocus == $scope.optnav.focus  ){
        				    var center = $(window).height()/2 ,
        				        sez = $("#_id_sez_"+$scope.optnav.focus ),
        				        top = sez.offset().top ;
        				    sez.find('a').first().focus().addClass("bg-success text-success");
           				    if (top > center){
        				    	$(window).scrollTop(top-center);
        				    	$(window).on("scroll resize");
        				    }
           				    // FIX ZZzzz aggiusto il sidebars
           					if ($("html").hasClass("is-ie8") == false ){
           						$('#sidebar-container').height(   $('#main-page-content').height());	
           					} 
        			 } 
        		});
        	    $scope.toggleScroll = function () {
        	    	if (this.sezione.disabled == undefined || this.sezione.disabled == false){ 
		               	var  that =  $($element)  , target =  that.attr("href");
		               	if ( this.sezione.ramo == false) {
		               		$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-gestione-sezione-ptof.do?codsez='+this.sezione.tipoSezione+'#?key='+ encodeURIComponent ( $base64.encode( this.doc +"||"+this.sezione.key +"||"+this.optnav.keyforfocus ));
		               	} else  if(!that.children("span").hasClass("miur-freccia-avanti"))  
		                {
		                 if (!that.hasClass("collapsed")) 
		                 	{
		                	    that.children("span").removeClass("miur-chevron-up");
		                	    that.children("span").addClass("miur-chevron-down");
		                 		$(target).children("li").removeClass("show"); 
		                 	} else {
		                 		$('#accordion').on('shown.bs.collapse', function() 
		                 			{
		        						$(target).children("li").addClass("show");   
		        					});
		                 		that.children("span").removeClass("miur-chevron-down");
		                 		that.children("span").addClass("miur-chevron-up");
		                 	}
		                 } 	 
        	    	}else{
        	    		var sms = {
       		        		 title :"ATTENZIONE",
       		        		 text : this.sezione.disabledMessage ,  
       		        		 type :"KO"
	       		        };
	       				$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
	        	    }
               }
        }// CONTROLLER
    };
})
.directive('bsUpload', ['uploadManager', '$rootScope', function factory(uploadManager, $rootScope ) {
			return {
				restrict : 'A',
				scope : {
					watch : '=bsWatch', acceptfile :'=bsAcceptfile' , key :'=bsKey'
				},
				link : function ( scope, element, attrs) {
	                var jqThat = $(element), 
	                       acceptFileTypes  , key = scope.key ;
	                acceptFileTypes = scope.acceptfile ? scope.acceptfile : /(\.|\/)(pdf)$/i
					jqThat.fileupload({
						maxFileSize : 5000000,
						acceptFileTypes : acceptFileTypes ,
						dataType : 'text',
						add : function (e, file) {
							uploadManager.add(file);
							scope.watch( {action :'add' ,nome :  file.files[0].name , key : key } )
						},
						progressall : function (e, data) {
							var progress = parseInt(data.loaded / data.total * 100, 10);
							scope.watch( {action :'progress' , progress : progress , key : key } )
						},
						done : function (e, data) {
							// gestisco anche gli eventuali errori applicativi 
							console.log('done upload file', data);
							uploadManager.stopUpload();
							if (!data) {
								scope.watch( {action :'fail' , key : key  } )
								$rootScope.$broadcast("EVENT_SENDMESSAGE", { type : 'KO-SISTEMA' });
							} else if (  data.result && JSON.parse ( data.result )[0].errors &&  JSON.parse ( data.result )[0].errors.length > 0  ){
								
								$rootScope.$broadcast("EVENT_SENDMESSAGE", {
					        		 title :"ERRORE",
					        		 fieldErrors  : JSON.parse( data.result )[0].errors  ,
				            		 type :"KO"});

							} else {
								//$rootScope.$broadcast("EVENT_SENDMESSAGE", { type : 'OK-STANDARD' });
								$rootScope.$broadcast("EVENT_SENDMESSAGE", { type : 'CLEAN' });
								
								scope.watch( {action :'done' , key : key }  )
							}
						},
						fail : function (e, data) {
							console.log('error upload file', data);
							console.log('error upload file data.result ', data.result);
							
							uploadManager.stopUpload();
							if (data.errorThrown === 'abort') {
								return;
							}
							if (data.dataType &&
								data.dataType.indexOf('text') === data.dataType.length - 4) {
								try {
									data.result = angular.fromJson(data.jqXHR.responseText);
								} catch (ignore) {}
							}
							if ( 'Not Acceptable'== data.errorThrown && data.result[0].errors ) {
								$rootScope.$broadcast("EVENT_SENDMESSAGE", {
						        		 title :"ERRORE",
						        		 fieldErrors  : data.result[0].errors ,
					            		 type :"KO"
				        		 });
								
							} else {
								$rootScope.$broadcast("EVENT_SENDMESSAGE", { type : 'KO-SISTEMA' });
							}
							
							scope.watch( {action :'fail' } )
						}
					});
	                
				}
			};
		}
])
.directive('bsselectpicker', function( $parse, $timeout )
{
  return {
	  
      restrict: 'E',
      scope: {
    	  items: '=',
    	  selected: '=',
    	  bid:'@',
    	  show :'='
      },  
    // ( old v da cancellare) template: '<select class="form-control "  ng-model= "selected" ng-options="item as ( item.label.substring(0,40)+ ( item.label.length > 50 ? \'...\' : \'\') ) for item in items track by item.value"  ><option   value=""> </option> </select> ',
        template: '<select class="form-control   "   title="Seleziona" ng-model= "selected" ng-options="item as ( item.label ) for item in items track by item.value"  ><option   value=""> </option> </select> ',
      replace: true,
      link: function ( $scope, $element , $attrs ) {
      	  function refresh(newVal) {
              if ( $scope.show==true ) { 
        	   $element.selectpicker('refresh');
              }
          }
      	  function crSelect() {
	      	  $element.selectpicker({
				   style: 'btn-primary',
				    size: 10
				  });
	      	  $element.selectpicker('refresh');
	      	  // use: gestione degli errori
	      	  $($element)
	  		  .parent()
	  		  .find('button')
	  		  .attr('id',$scope.bid);
          }
          if (angular.isDefined( $scope.show) ) 
          {
        	  $scope.$watch('show', 
               function ( newVal ){
        		  if ( newVal== false ){
        		    $element.selectpicker('destroy');
        		    $element.hide();
        		  } else {
        		    $timeout( crSelect );       			  
        		  }
        	  }, true);
          } else {
        	  $timeout( crSelect );     
          }
          
          $scope.$watch('selected', refresh, true);
          $scope.$watch('items', refresh, true);
          
 
          $scope.$on('$destroy', function () {
            $timeout(function () {
            	$element.selectpicker('destroy');
            	$element.hide();
            });
          });
        }
  }
})
.directive('bsselectpicker2', function( $parse, $timeout )
{
  return {
	  
      restrict: 'E',
      scope: {
    	  items: '=',
    	  selected: '=',
    	  bid:'@',
    	  show :'='    	  
      },  
      template: '<select class="selectpicker form-control" title="Seleziona" data-style="btn-primary" data-live-search="true" ng-model= "selected" ng-options="item as ( item.label ) for item in items track by item.value"  ></select> ',
      replace: true,
      link: function ( $scope, $element , $attrs ) {
      	  function refresh(newVal) {
              if ( $scope.show==true || !angular.isDefined( $scope.show) ) { 
        	   $element.selectpicker('refresh');
              }
          }
      	  function crSelect() {
	      	  $($element)
	  		  .parent()
	  		  .find('button')
	  		  .attr('id',$scope.bid);
          }
          if (angular.isDefined( $scope.show) ) 
          {
        	  $scope.$watch('show', 
               function ( newVal ){
        		  if ( newVal== false ){
        		    $element.selectpicker('destroy');
        		    $element.hide();
        		  } else {
        		    $timeout( crSelect );       			  
        		  }
        	  }, true);
          } else {
        	  $timeout( crSelect );     
          }
          
          $scope.$watch('selected', refresh, true);
          $scope.$watch('items', refresh, true);
          
 
          $scope.$on('$destroy', function () {
            $timeout(function () {
            	$element.selectpicker('destroy');
            	$element.hide();
            });
          });
          $scope.selected = $scope.items[0];
        }
  }
})
.directive('faPattern', function() {
  return {
    require: 'ngModel',
    link: function (scope, element, attr, ngModelCtrl) {
      
      var reg =/[^0-9]/g, sms ="solo numeri.";	
      if(attr.faPattern == 'alfa'  ){
	  reg =/[^a-z]/ig;
	  sms ="solo lettere.";
      } else if( attr.faPattern=='alfanumeric' ){   
	  reg = /[^a-zA-Z0-9]/ig;
	  sms ="solo lettera e numeri.";
      } else if( attr.faPattern=='alfaUpperOnly' ){   
	  reg = /[^A-Z]/g;  
	  sms ="solo lettera maiuscole.";
      };  
      
      function fromUser(text) {
	var transformedInput = text.replace( reg , '');
        console.log(transformedInput);
        element.removeClass("errors");
        if(transformedInput !== text) {
           ngModelCtrl.$setViewValue(transformedInput);
           ngModelCtrl.$render();
           element.addClass("errors").attr('title', "Attenzione carattere inserito non valido, sono ammessi "+ sms );           
        }  
        return transformedInput;
      };
      ngModelCtrl.$parsers.push(fromUser);
    }
  }; 
}) 
.directive('faCombobox', [function () {
			return {
				restrict : 'A',
				scope : {
					model : '=faModel',
					sorgente : '=faCombobox',
					faWatchCall : '&faWatchCall',
					show : '=faShow'
				},
				link : function (scope, elem, attr, ctrl) {
					console.log("faCombobox ->", scope.sorgente);
					var wrapper = $("<div style = 'display:inline;' > </div>").insertAfter(elem);
					var select = elem.hide(),
					selected = select.children(":selected"),
					value = selected.val() ? selected.text() : "";

					var checkisValid = function (event, ui) {

						var valid = false;
						var inputVal = $(this).val();
						{
							var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(inputVal) + "$", "i"),
							valid = false ;
							select.children("option").each(function () {
								if ($(this).text().match(matcher)) {
									this.selected = valid = true;

									scope.model = this.value;
									scope.$apply();

									if (scope.faWatchCall) {
										scope.faWatchCall();
									}

									return false;
								}
							});
							if (!valid) {
								// remove invalid value, as it didn't match anything
								$(this).val("");
								select.val("");
								scope.model = "";
								scope.$apply();

								if (scope.faWatchCall) {
									scope.faWatchCall();
								}

								return false;
							};
						};

					}

					var input = $("<input style = 'display:inline-block; ' />")
					        .attr("id",attr.faId )
						.appendTo(wrapper)
						.val(value)
						.autocomplete({
							create : function () {
								$(this).data('ui-autocomplete')._renderItem = function (ul, item) {
									console.log(item);
									ul.addClass("menuThemeFa ui-menu ui-widget ui-widget-content ui-corner-all ");
									ul.css("background-color", "white").css("border-color", "black");

									return $("<li></li>").addClass("menuThemeFa")
									.data("ui-autocomplete-item", item)
									.append("<a> " + item.label + "</a>")
									.appendTo(ul);

								};
							},
							delay : 0,
							minLength : 0,
							source : function (request, response) {
								var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
								elem.html('<option value="" > </option>');
								if (scope.sorgente) {
									$.each(scope.sorgente, function (index, item) {
										var option = '<option value="' + item.value + '">' + item.label + '</option>';
										elem.append(option);
									});
								} else {}
								response(select.children("option").map(function () {
										var text = $(this).text();
										if (this.value && (!request.term || matcher.test(text)))
											return {
												label : text.replace(
													new RegExp(
														"(?![^&;]+;)(?!<[^<>]*)(" +
														$.ui.autocomplete.escapeRegex(request.term) +
														")(?![^<>]*>)(?![^&;]+;)", "gi"),
													"<strong>$1</strong>"),
												value : this.value,
												option : this,
												labelDesc : text
											};
									}));
							},
							select : function (event, ui) {
								ui.item.option.selected = true;
								scope.model = ui.item.value;
								$(this).val(ui.item.labelDesc);
								scope.$apply();

								if (scope.faWatchCall) {
									scope.faWatchCall();
								}
								
								$("ul.ui-autocomplete").hide();
								 
								return false;
							},
							close : checkisValid,
							change : checkisValid
						})
						.addClass("fnzThemeFa ng-valid ng-dirty")
						.focus(function () {
							 if ($("ul.ui-autocomplete").is(":hidden")) {
							         input.autocomplete("search", "");
							  }
						});

					scope.$watch('model', function (neww, old) {
						if (!neww || neww == '') {
							input.val("");
							select.val("");
						}
					}, true);

					if (attr.faShow) {
						scope.$watch('show', function (neww, old) {
							if (neww) {
								input.show();
							} else {
								input.hide();
							}
						}, true)
					}

				}
			};
		}
])
.directive('faProgressbar', [function factory() {
			return {
				restrict : 'A',
				scope : {
				        faProgressbar : "=faProgressbar" 
				},
				link : function (scope, element, attrs) {
				    scope.$watch('faProgressbar', function (opt, old) {
					  if( opt == undefined ){
					      return;
					  }
				          var temp = $(element).progressbar( {
						  value: opt
				          } ).height( 15 );
				          
				          temp.css({ 'background': 'yellow' });
				          $(temp).find('div').css({'background-color':'green'});

					   temp.addClass("faProgressBar");
				    });
				}//link
			}
		}
])	
.directive('faButtonConfermata', [function factory() {
			return {
				restrict : 'A',
				scope : {
					faButtonConfermata : "@faButtonConfermata",
					stato : '=faStato'
				},
				link : function (scope, element, attrs) {
  
				    
				    scope.$watch('stato', function (stato, old) {
				    
					  if( stato == undefined ){
					      return;
					  }
					
					  var _text = attrs.faButtonConfermata ,
						_primary;
						
					   if ( stato ){
					       _primary = "ui-icon-circle-check blue" ;
					   } else {
					       _primary =   "ui-icon-circle-close" ;
					   }	
					  
					  	
					   var temp = $(element).button({
							label : _text,
							icons : {
								primary : _primary
							}
					   });
					   temp.addClass("button-tabs");
					   
					   if ( stato ){
					       temp.addClass("button-tabs-confermati");
					   } else {
					       temp.removeClass("button-tabs-confermati");
					   }
					  
					   
				    });
					
					
				    
				    
				    
				}//link
			}
		}
])	
.directive("faNumeric", [function()
{
    return {
        restrict: 'A',
           scope: {
               model : "=faModel",
               faChange : '&faChange',
           },
        link: function(oScope, eInput, oAttrs)
        {
        
        $(eInput).css('text-align','right');
        
        var opts = {};
		opts.aSep = '.'; // italy style
		opts.aDec = ',';
		if (oAttrs.faNumeric && oAttrs.faNumeric == 'numeroIntero2DType') {
			opts.vMin = '0';
			opts.vMax = '99';
		} else	if (oAttrs.faNumeric && oAttrs.faNumeric == 'numeroInteroType') {
			opts.vMin = '0';
			opts.vMax = '999999';
		} else	if (oAttrs.faNumeric && oAttrs.faNumeric == 'pesoType') {
			opts.vMin = '0.00';
			opts.vMax = '9999.99';
		} else if (oAttrs.faNumeric && oAttrs.faNumeric == 'rateType') {
			opts.vMin = '0.00';
			opts.vMax = '100';
		} else if (oAttrs.faNumeric && oAttrs.faNumeric == 'amount8DecimalType') {
			opts.vMin = '-999999999.9999';
			opts.vMax = '999999999.9999';
		} else if (oAttrs.faNumeric && oAttrs.faNumeric == 'amount2DecimalType') {
			opts.vMin = '-99999999999.99';
			opts.vMax = '99999999999.99';
		} else if (oAttrs.faNumeric && oAttrs.faNumeric == 'capType') {
			opts.vMin = '0';
			opts.vMax = '99999';
			opts.aSep = '';
			opts.aPad = true ;
		} else if (oAttrs.faNumeric && oAttrs.faNumeric == 'quantitaType') {
			opts.vMin = '0.00';
			opts.vMax = '999999999.99';
		} else {
			opts = angular.extend({}, opts, oScope.$eval(oAttrs.faNumeric));
		}
            
            var tempNumFormattorato = '';

            oScope.$watch('model', function (current, old) {
        	 if( angular.isDefined( current ) ) {
                     jQuery(eInput).autoNumeric('set', current );
                     if ( oScope.faChange!==undefined ) {
                         oScope.faChange();
                     }  
        	 } else {
        	     eInput.val('');
        	 } 
        	 
        	  if ( oScope.faChange!==undefined ) {
                      oScope.faChange();
                  }
        	 
            });
            jQuery(eInput).autoNumeric('init', opts);
            jQuery(eInput).on('change', function (e) {
                        oScope.$apply(function () {
                            oScope.model = jQuery(eInput).autoNumeric('get');
                        });
                          
             });
                 
        }
    };
}])
.directive('faNumeric2', function () {

	// Declare a empty options object
	var options = {};
	return {
		// Require ng-model in the element attribute for watching changes.
		require : '?ngModel',
		// This directive only works when used in element's attribute (e.g: cr-numeric)
		restrict : 'A',
		compile : function (tElm, tAttrs) {

			var isTextInput = tElm.is('input:text');
		    $(tElm).css('text-align','right');
		      
			return function (scope, elm, attrs, controller) {
				// Get instance-specific options.

				var opts = {};

				opts.aSep = '.'; // italy style
				opts.aDec = ',';
				
				if (attrs.faNumeric2 && attrs.faNumeric2 == 'amount8DecimalType') {
					opts.vMin = '-999999999.9999';
					opts.vMax = '999999999.9999';
				} else if (attrs.faNumeric2 && attrs.faNumeric2 == 'amount2DecimalType') {
					opts.vMin = '-99999999999.99';
					opts.vMax = '99999999999.99';
				} else if (attrs.faNumeric2 && attrs.faNumeric2 == 'numeroInteroType') {
					opts.vMin = '0';
					opts.vMax = '99999';
					opts.aSep = '';
					opts.aPad = true ;
				} else {
					opts = angular.extend({}, options, scope.$eval(attrs.faNumeric));
				}


				// Helper method to update autoNumeric with new value.
				var updateElement = function (element, newVal) {
					// Only set value if value is numeric
					if ($.isNumeric(newVal)) {
						element.autoNumeric('set', newVal);
					}
				};

				// Initialize element as autoNumeric with options.
				elm.autoNumeric(opts);

				// if element has controller, wire it (only for <input type="text" />)
				if (controller && isTextInput) {
					// watch for external changes to model and re-render element
					scope.$watch(tAttrs.ngModel, function (current, old) {
						if (! current ) {
							elm.val('');
						}

						controller.$render();
					});
					// render element as autoNumeric
					controller.$render = function () {
						updateElement(elm, controller.$viewValue);
					}
					// Detect changes on element and update model.
					elm.on('change', function (e) {
						scope.$apply(function () {
							controller.$setViewValue(elm.autoNumeric('get'));
						});
					});
					
 				} else {
					// Listen for changes to value changes and re-render element.
					// Useful when binding to a readonly input field.
					if (isTextInput) {
						attrs.$observe('value', function (newVal) {
							//  updateElement(elm, val);
							if ($.isNumeric(newVal)) {
								elm.autoNumeric('set', newVal);
							} else { // necessario per svuotate il campo calcolato non e' un numero!!!
								elm.val('');
							}

						});
					}
				}
			}
		} // compile
	} // return
})
.directive('faDatetimepicker', function () {
	return {
		restrict : 'A',
		require : 'ngModel',
		scope : {
			model : '=ngModel'
		},
		link : function (scope, element, attrs ) {
			 
			var elm = element.datetimepicker({
				//showOn: "both",
				showButtonPanel : true,
				dateFormat : 'dd/mm/yy',
				onSelect : function (date) {
					scope.model = date;
					scope.$apply();
				},
				beforeShow : function (input, inst) {

					setTimeout(function () {
						$(input).datepicker("widget").find(".ui-datepicker-current").hide();
						var buttonPane = $(input)
							.datepicker("widget")
							.find(".ui-datepicker-buttonpane");

						var btn = $('<button class="ui-datepicker-current ui-state-default ui-priority-primary ui-corner-all" type="button">Cancella</button>');
						btn
						.unbind("click")
						.bind("click", function () {
						       scope.$apply(function() {
					                element.datepicker("setDate", "");
					                scope.model = '';
					              });
						});

						btn.appendTo(buttonPane);

					}, 1);

					$(input).datepicker("widget").addClass("menuThemeFa");
					
					  
				}
			}) ;
			//element.attr('readOnly', 'true');
		}
	};
})
.directive('faDatepicker', function () {
	return {
		restrict : 'A',
		require : 'ngModel',
		scope : {
			model : '=ngModel'
		},
		link : function (scope, element, attrs, ngModelCtrl) {
			// 		        $.datepicker.setDefaults($.datepicker.regional['it']);
			element.datepicker({
				//showOn: "both",
				showButtonPanel : true,
				dateFormat : 'dd/mm/yy',
				onSelect : function (date) {
					scope.model = date;
					scope.$apply();
				},
				beforeShow : function (input, inst) {

					setTimeout(function () {
						$(input).datepicker("widget").find(".ui-datepicker-current").hide();
						var buttonPane = $(input)
							.datepicker("widget")
							.find(".ui-datepicker-buttonpane");

						var btn = $('<button class="ui-datepicker-current ui-state-default ui-priority-primary ui-corner-all" type="button">Cancella</button>');
						btn
						.unbind("click")
						.bind("click", function () {
							$.datepicker._clearDate(input);
						});

						btn.appendTo(buttonPane);

					}, 1);

					$(input).datepicker("widget").addClass("menuThemeFa");
					
					  
				}
			}) ;
			//element.attr('readOnly', 'true');
		}
	};
})

.directive('faDialog', ['$parse', function ($parse) {
			return {
				restrict : 'A',
				//	        scope: {model:'='},
						scope : {
						    show : '=faWatchobj',
						    title : '=faTitle'
						    
						},
				transclude : false,
				replace : false,
				link : function (scope, elm, attrs) {
					if (attrs.faWatchobj == undefined) {
						console.log('ERRORE : faWatchobj obbligatorio!! ', openDialog);
						return;
					}
					var width = $(window).width() * 4 / 5;
					if( attrs.faDialogWidth ) {
					    width = $(window).width() / 100 * attrs.faDialogWidth ; 
					} 
					
					var open = function (event, ui) {};
					if( attrs.faDialogHclose ) {
					    open = function(event, ui) { $(".ui-dialog-titlebar-close").hide(); }
					}  

					var _title = ($.type(attrs.faTitle) == 'undefined') ? '' : scope.title;
					var myd = elm.dialog({
							autoOpen : false,
							modal : true,
							title : _title,
							resizable : false,
							draggable : false,
							//height : $(window).height() * 3 / 4,
							width : width ,// $(window).width() * 4 / 5,
							dialogClass : 'fnzThemeFa',
							open :  open ,
							beforeClose : function (event, ui) {
							    if(  scope.show ){
        							    scope.show= false;
        							    scope.$apply();
							    }
							}
						});
					
					 
			                myd.addClass('fnzThemeFa');
					 
					
					scope.$watch('show', function (show) {
						console.log('div show - >', elm, show);
						if (show == undefined)
							return;
						 
						if (show == true) {

						    myd.dialog('option', 'title', scope.title );
						    myd.dialog("open");
						} else {
						      if( myd.dialog( "isOpen" ) ){ 
							  myd.dialog("close");
						      }	
						};

					});
				}
			};
		}
	])
.directive('faTable', [function () {
			return {
				restrict : 'A',
				scope : {
				        tabella : '=faTable'
 				},
				link : function (scope, element, attrs) {
				    console.log('faTable ->   ');

					if (scope.$eval(attrs.faColumns) == undefined) {
						console.log('ERRORE : faTable -> attrs.faColumns obbligatorio es: { "data" : "cig",	"title" : "CIG","class" : "center" } ', attrs.faColumns);
					}

					var tableLocale = {
						sLengthMenu : "Visualizza _MENU_ righe per pagina",
						sZeroRecords : "Nessun dato da visualizzare",
						//sInfo: "Visualizzate righe da _START_ a _END_ di _TOTAL_ righe",
						sInfo : "Visualizzate righe da _START_ a _END_ di _TOTAL_ righe ",
						sInfoEmpty : "Nessun risultato",
						sInfoFiltered : "(filtro su _MAX_ righe totali)",
						sSearch : "Filtra",
						oPaginate : {
							sFirst : "Primo",
							sLast : "Ultimo",
							sNext : "Successivo",
							sPrevious : "Precedente"
						}
					};

 					var sortableP = ($.type(attrs.faSortable) == 'undefined' || attrs.faSortable == 'true');
					var simpleP = ($.type(attrs.faSimple) == 'undefined' || attrs.faSimple == 'false');
					var aoColumnDefs ,fnDrawCallback;
					
					if ( scope.tabella!= undefined && scope.tabella.fnDrawCallback ) {
					    fnDrawCallback = scope.tabella.fnDrawCallback;
					}
					
					if ( scope.tabella!= undefined && scope.tabella.aTargetsNum ) {
					  aoColumnDefs = [{
				                "aTargets":  scope.tabella.aTargetsNum ,
				                "sType": 'formatted-num',
				                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				                        var that =  $(nTd);
				                        
				                        if (!$.isNumeric(sData)) {
				                            that.html(sData);
				                        }else{
				                            var opts = {} ;
				                            opts.aSep = '.'; // italy style
							    opts.aDec = ',';
				                            opts.vMin = '-99999999999.9999';
							    opts.vMax = '99999999999.9999';
							    that.html(sData);
							    that.autoNumeric('init',opts);
							    that.addClass('right');
				                         
				                        }
				                }
				            }];
					}

					
					if ( scope.tabella!= undefined && scope.tabella.aTargetsNum2D ) {
						  aoColumnDefs = [{
					                "aTargets":  scope.tabella.aTargetsNum2D ,
					                "sType": 'formatted-num',
					                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					                        var that =  $(nTd);
					                        
					                        if (!$.isNumeric(sData)) {
					                            that.html(sData);
					                        }else{
					                            var opts = {} ;
					                            opts.aSep = '.'; // italy style
								    opts.aDec = ',';
					                            opts.vMin = '-99999999999.99';
								    opts.vMax = '99999999999.99';
								    that.html(sData);
								    that.autoNumeric('init',opts);
								    that.addClass('right');
					                         
					                        }
					                }
					            }];
						}
					
					var setupTable = function () {
                                               
					       
					     
					       if ( attrs.faScroll == undefined || attrs.faScroll ==false  ) {
							return element.dataTable({
								"bInfo" : simpleP,
								"bPaginate" : simpleP,
								"stateSave" : true,
								"bLengthChange" : true,
								"iDisplayLength" : 10,
								"bJQueryUI" : true,
								"bDestroy" : true,
								"bFilter" : simpleP,
	  							//"bAutoWidth" : true,  // CAMBIATO PER SCROLL
								"sPaginationType" : "full_numbers",
								//"scrollCollapse" : true, // CAMBIATO PER SCROLL
								"bSort" : sortableP,
								"aaSorting" : [],
								"oLanguage" : tableLocale,
								"lengthMenu" : [[10, 25, 50, -1], [10, 25, 50, "Tutte"]],
								"data" : scope.tabella.data,
								"columns" : scope.tabella.arColumns,
								"createdRow" : scope.tabella.fnCreatedRow,
								//"scrollX": false ,  // CAMBIATO PER SCROLL
								//"scrollY": '1600px',  // ALTEZZA MINIMA SENZA SCROLL 
								"aoColumnDefs" : aoColumnDefs,
								"fnDrawCallback": fnDrawCallback,
								// scrollHeight : "100%" // AGGIUTO PER fissare baco su Ie : altezza flottante  

							});
                                               }
					    
					    
						return element.dataTable({
							"bInfo" : simpleP,
							"bPaginate" : simpleP,
							"stateSave" : true,
							"bLengthChange" : true,
							"iDisplayLength" : 10,
							"bJQueryUI" : true,
							"bDestroy" : true,
							"bFilter" : simpleP,
  							"bAutoWidth" : true,  // CAMBIATO PER SCROLL
							"sPaginationType" : "full_numbers",
							"scrollCollapse" : true, // CAMBIATO PER SCROLL
							"bSort" : sortableP,
							"aaSorting" : [],
							"oLanguage" : tableLocale,
							"lengthMenu" : [[10, 25, 50, -1], [10, 25, 50, "Tutte"]],
							"data" : scope.tabella.data,
							"columns" : scope.tabella.arColumns,
							"createdRow" : scope.tabella.fnCreatedRow,
							"scrollX": "100%",  // CAMBIATO PER SCROLL
//							"scrollY": '1600px',  // ALTEZZA MINIMA SENZA SCROLL //Begin / End Briganti Luca - Copia - 12/10/2015
							"aoColumnDefs" : aoColumnDefs,
							"fnDrawCallback": fnDrawCallback,
							 scrollHeight : "100%" // AGGIUTO PER fissare baco su Ie : altezza flottante  

						});

					};
					var dt = null;
			                if( scope.tabella ) {
			                    dt= setupTable();
			                }
			 

					scope.$watch('tabella.data', function (value) {
							var val = value || null;
							if (val) {
								console.log('table rewrite!', element, value);
								element.html('');
								dt=setupTable();
								
								if ( bowser.msie && bowser.version == 9 && !scope.tabella.noScroll ) {
								     dt.fnDraw();
								 }
								

							}
					});
					
					 

					 $(window).bind('resize', function() {
					     
					     var testB = bowser.msie && bowser.version < 9; 
					     if (dt && !testB ) {
					           dt.fnDraw();
					     }; 
					     });
					 
					 
				    
				}
			}}
])
.directive(
	'tabCommittenteDettaglioRicerca', function () {
	return {
		restrict : 'A',
		scope : false,
		transclude : false,
		replace : false,
		link : function (scope, elm, attrs) {

			scope.$watch('modello.t3.subForm.ricercaCommittente.show', function (show) {
				console.log('modello.t3.subForm.ricerca.show - >' + show);

				if (show !== undefined) {
					elm.dialog({
						autoOpen : true,
						modal : true,
						title : 'Ricerca Committente',
						resizable : false,
						draggable : false,
						height : $(window).height() * 3 / 4,
						width : $(window).width() * 3 / 4,
						dialogClass : 'fnzThemeFa'
					});
					elm.addClass('fnzThemeFa');
				} else {
					elm.hide();
				};

			});

		}
	};
})
.directive(
	'faErrors', function () {
	return {
		restrict : 'A',
		scope : false,
		transclude : false,
		replace : false,
		link : function (scope, elm, attrs) {

		    var _lfnErrorView = function (tab, fieldErrors) {

			$("#tabs-" + tab + " .errors").removeClass("errors");
			var elmError = $("#tabs-" + tab + "-err");

			elmError.html('');
			if (fieldErrors !== undefined && fieldErrors != null) {
				$.each(fieldErrors, function (index, value) {
					//$("#modello_t"+tab+"_form_"+ (value.field).replace('.','_') ).addClass("errors").attr('title',value.message);

					var idErrorElement = ("modello.t" + tab + ".form." + (value.field));
					console.log("select elem >" + idErrorElement);
					$("#tabs-" + tab + " [ng-model = '" + idErrorElement + "']").addClass("errors").attr('title', value.message);

					//todo da mettete i messaggi  elmError.append('<p><span style="color:red" >' + value.message + "</span></p>");
				});
			}
		}

		var _lfnErrorView2 = function (fieldErrors, tag, tab) {
			$(".errors").removeClass("errors");
			var elmError = $("#tabs-err");
			elmError.html('');
			if (fieldErrors !== undefined && fieldErrors != null) {
				$.each(fieldErrors, function (index, value) {

					if (!value.id) {
						var idErrorElement = ("modello.t" + tab + ".form." + (value.field));
						console.log("select elem >" + idErrorElement);
						var campoDared = $(tag + " [ng-model = '" + idErrorElement + "']");

						if (campoDared.length) {
							campoDared.addClass("errors").attr('title', value.message);
						} else {
							var _temp = idErrorElement.replace(/\./g, '_');
							$("#" + _temp).addClass("errors").attr('title', value.message);
						}

					} else {

						$("#" + value.id).addClass("errors").attr('title', value.message);
					}

					//todo da mettete i messaggi  elmError.append('<p><span style="color:red" >' + value.message + "</span></p>");
				});
			}
		}

		scope.$watch('modello.t1.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t1.fieldErrors->');

			_lfnErrorView(1, fieldErrors);

		});
 
		scope.$watch('modello.t2.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t2.fieldErrors->');
			_lfnErrorView(2, fieldErrors);

		});
		scope.$watch('modello.t3.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t2.fieldErrors->');
			_lfnErrorView2(fieldErrors,'div', 3);

		});

		scope.$watch('modello.t31.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t2.fieldErrors->');
			_lfnErrorView2(fieldErrors,'div', 31);

		});
		
		scope.$watch('modello.t61.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t6.fieldErrors->');
			_lfnErrorView2(fieldErrors, 'div', 61);

		});

		scope.$watch('modello.t611.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t61.fieldErrors->');
			_lfnErrorView2(fieldErrors, 'div', 611);

		});
		
		scope.$watch('modello.t41.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t6.fieldErrors->');
			_lfnErrorView2(fieldErrors, 'div', 41);

		});
		
		scope.$watch('modello.t7.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t7.fieldErrors->');
			_lfnErrorView(7, fieldErrors);

		});

		scope.$watch('modello.t81.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t81.fieldErrors->'); ;
			_lfnErrorView2(fieldErrors, 'div', 81);

		});	 

		scope.$watch('modello.t101.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t101.fieldErrors->'); ;
			_lfnErrorView2(fieldErrors, 'div', 101);

		});
		

		scope.$watch('modello.t102.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t101.fieldErrors->'); ;
			_lfnErrorView2(fieldErrors, 'div', 102);

		});
		
		scope.$watch('modello.t11.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t101.fieldErrors->'); ;
			_lfnErrorView2(fieldErrors, 'div', 11);

		});
		
		scope.$watch('modello.t01.fieldErrors', function (fieldErrors) {
			console.log('scope.modello.t01.fieldErrors->'); ;
			_lfnErrorView2(fieldErrors, 'div', '01');

		});
		
		 
		
		}
	};
})
.directive(
	'fatcaTabs', function () {
	return {
		restrict : 'A',
		scope : {
		   reloadSection : '&faReloadSection'
		   
		},
		transclude : false,
		replace : false,
		link : function (scope, elm, attrs) {

			$(document).ready(function ($) {
				var tab = elm.tabs({
				    activate: function (event, ui) {
					    var idTab = ui.newPanel.attr('id').replace("tabs-","")
					    console.log (idTab) ;
					  
					    scope.reloadSection ({ idTab : idTab }  ) ;
				    }
				    
				});
				 
			
			});

			

		}
	};
})
.directive('fatcaAccordions', ['$window', '$q', '$templateCache', '$timeout', '$compile', '$http', function ($window, $q, $templateCache, $timeout, $compile, $http) {
			(function ($, sr) {
				// debounce the resize to use less cpu
				var debounce = function (func, threshold, execAsap) {
					var timeout;

					return function () {
						var obj = this,
						args = arguments;
						function delayed() {
							if (!execAsap) {
								func.apply(obj, args);
							}
							timeout = null;
						};

						if (timeout) {
							clearTimeout(timeout);
						}

						timeout = setTimeout(delayed, threshold);
					};
				}

				// smartresize
				jQuery.fn[sr] = function (fn, threshold) {
					return fn ? this.bind('resize', debounce(fn, threshold)) : this.trigger(sr);
				};
			})(jQuery, 'smartresize');

			return {
				restrict : 'A',
				scope : false,
				transclude : false,
				replace : false,
				link : function (scope, element, attributes, controller) {
					element.accordion({
						heightStyle : '600px',
						collapsible : false,
						active : false
					});

					angular.element($window).smartresize(function () {
						scope.$apply(function () {
							element.accordion('refresh');
						});
					}, attributes.refreshDebounceThreshold || 100);
				}
			};
		}
]).directive("bsTablePaginationR2", function() {
    return {
        	scope: {
        		opts: '=',  
        		targetDialog:'='
        	},
        	template : '<h3>{{ opts.titolo }}  </h3>'+
        			   '<div class="table-responsive">'+ 
        			   '<table class="table table-bordered table-striped" id= "tab_{{ opts.key }}">'+
        			   '   <thead>'+
					   '      <tr ng-if ="opts.tabella.colsHead" >'+
					   '	     <th colspan="{{ th.span }}"  ng-repeat="th in opts.tabella.colsHead" > {{ th.title }}   </th>'+
					   '	  </tr>'+
					   '	  <tr >'+
						//'<th ng-repeat="th in opts.tabella.cols"> <button ng-click="sort( th )"  class="button-testata-tabella">{{ th.title }} <span  ng-if="$index != opts.tabella.cols.length-1" class="icon miur-triangle{{ th.ord == undefined ? \'\' : th.ord ==\'DESC\'  ? \'-down\' :\'-up\' }}"></span></button> </th>'+   
					   '	     <th ng-repeat="th in opts.tabella.cols">{{ th.title }}</th>'+ 
					   '      </tr>'+
				       '   </thead>'+
				'<tbody>'+
				'<tr ng-repeat="tr in opts.tabella.items" id="{{tr.key}}" >'+
					'<td ng-repeat="td in opts.tabella.cols"   > '+
					 	'<div ng-if="!td.applyTrunk && !(td.iconDecorator) && $index < opts.tabella.cols.length-2" class="pop-div" >' + 
					 	 	'{{  { o:tr , s:td.data } | objectToString   }} '+ 
				 		'</div>' +
	  			         '<div  ng-if="$index == opts.tabella.cols.length-2  && opts.lastColShow===true " style="white-space: nowrap;" >' +  
	  			         '<a data-toggle="popover" data-placement="top" data-content="Convalida" class="icon miur-check" ng-click="convalida( tr )"  href="#"></a>&nbsp;' +     
	  			         '</div>' +
				 		 
				 		 '<div  ng-if="$index == opts.tabella.cols.length-1 && opts.lastColShow===true " style="white-space: nowrap;" >' +  
	  			          '<a data-toggle="popover" data-placement="top" data-content="Rettifica" class="icon miur-cancel-circle" ng-click="rettifica( tr )"  href="#"></a>' + 		 
				 		 '</div>' +
					 '</td>'+
				'</tr>'+
		   '</tbody>'+
  '</table>'+
'</div>'+
'<div class="table-boot-pagination"></div>',
		controller: function ( $scope, $rootScope, $element ,$window ,$timeout,$log  ) {
 		       			// ON LOAD FIRST
 		       			var  maxVisible =10,
 		       			        drawPagina = function () {
 		       			            if ( $scope.opts.drawPagination  ) {
 		       						$timeout(function () {
			 		       				
 		       						 if (   $scope.opts.tabella.items && $scope.opts.tabella.items.length >0) {
 		       						 $('.table-boot-pagination').unbind("page");
 		       						 $('.table-boot-pagination').show();
 		       							$('.table-boot-pagination').bootpag({
					    				    total:  $scope.opts.tabella.pagineTotali,
					    				    page:  $scope.opts.tabella.paginaCorrente ,
					    				    maxVisible: maxVisible,
					    				    leaps: true,
					    				    firstLastUse: true,
					    				    wrapClass: 'pagination',
					    				    activeClass: 'active',
					    				    disabledClass: 'disabled',
					    				}).on("page", function(event, num){
					    					
					    					 var sort = [];
					 		    			$.each( $scope.opts.tabella.cols, function ( k,v ) {
					 		    					   if ( angular.isDefined(v.ord)   ) {
					 		    						 sort.push ( { nome : v.data  , dir : v.ord  }  );
					 		    					  } 
					 		    			});
					    					
					    				    $scope.opts.newPage(  num , sort ) ;
					    				    $(this).bootpag({
			 	    				    	     total: $scope.opts.tabella.pagineTotali  
			 	    				        });
					    				});
 		       						
 		       						 } else {
 			 		       			   $('.table-boot-pagination').hide()
 			 		       			  }

 		       						})
 		       			            }	
 		       			};
 		       			
 		       			
 		       			$scope.$watch('opts.drawPagination',   drawPagina() ) 
 		       			
 		         	    $scope.$watch('opts.tabella.items', 
 		                     function (  newVal , oldVal ){
 		         	    	  $timeout(function () {
 		         	    		    if ( $scope.opts.tabella.items && $scope.opts.tabella.items.length >0 )
 		 		       				$($element).find('[data-toggle="popover"]').popover({
				    				    trigger: 'hover'
				     				});	  
 		              		  });
 		              		  drawPagina();
 		              	});
 		         	    
 		         	    // ACTION
 		         	   $scope.sort = function ( col ) {
 		         	    	$log.debug('bsTablePagination->controller->sort');
 	    				    var sort = [];
 		    				$.each( $scope.opts.tabella.cols, function ( k,v ) {
 		    					  
 		    					   if ( col.data == v.data  ) {
 		    						 v.ord = !angular.isDefined(v.ord) || v.ord == 'ASC' ? 'DESC' :'ASC'; 
 		    						 sort.push ( { nome : v.data  , dir : angular.isDefined(v.ord)? v.ord : 'ASC'  }  );
 		    					  } else {
 		    						 v.ord = undefined ;
 		    					  }
 		    				});
 		    				$scope.opts.sortPage( sort ) ;
 		         	    }
 		         	
 		         	  $scope.convalida = function ( tr ) {
 		         		    $scope.opts.onClickConvalida( tr ) ;
 		         	   }
 		         	   
 		         	  $scope.rettifica = function ( tr ) {
		         		    $scope.opts.onClickRettifica( tr ) ;
		         	  }
 		         	  
 		         	   
		         	  $scope.onClickCol = function ( tr, td ) {
		         	    	 if ( angular.isDefined ( td.action ) ) {
	 		         	    	 td.action.fnLoad( tr, td  ) ;
	 		         	    	 $('#'+  td.action.openDialogId ).modal('show');
		         	    	 }
		         	  }
 		         	   
		 }// controller
    };
})
.directive("bsTableFabbisognoPagination", function() {
    return {
        scope: {opts: '=',  targetDialog:'='},
        template : '<h3>{{ opts.titolo }}  </h3>'+
'<div class="table-responsive">'+ 
   '<table class="table table-bordered table-striped" id= "tab_{{ opts.key }}">'+
				'<thead>'+
					'<tr ng-if ="opts.tabella.colsHead" >'+
						'<th colspan="{{ th.span }}"  ng-repeat="th in opts.tabella.colsHead" > {{ th.title }}   </th>'+
					'</tr>'+
					'<tr >'+
						 '<th ng-repeat="th in opts.tabella.cols">{{ th.title }}</th>'+ 
					'</tr>'+
				'</thead>'+
				'<tbody>'+
				'<tr ng-repeat="tr in opts.tabella.items"  >'+
					'<td ng-repeat="td in opts.tabella.cols"   > '+
					 	'<div ng-if="!td.applyTrunk && !(td.iconDecorator) && $index < opts.tabella.cols.length-1" class="pop-div" >' + 
					 	 	'{{  { o:tr , s:td.data } | objectToString   }} '+ 
				 		'</div>' +
	  			         '<div  ng-if="$index == opts.tabella.cols.length-1  && opts.lastColShow===true " style="white-space: nowrap;" >' +  
	  			         '<a data-toggle="popover" data-placement="top" data-content="visualizza" class="icon miur-vai" ng-click="visualizzaDettaglio( tr )"  href="#"></a>&nbsp;' +     
	  			         '</div>' +
				 		
					 '</td>'+
				'</tr>'+
		   '</tbody>'+
  '</table>'+
'</div>'+
'<div class="table-boot-pagination"></div>',
		controller: function ( $scope, $rootScope, $element ,$window ,$timeout,$log  ) {
 		       			// ON LOAD FIRST
 		       			var  maxVisible =10,
 		       			        drawPagina = function () {
 		       			            if ( $scope.opts.drawPagination  ) {
 		       						$timeout(function () {
			 		       				
 		       						 if (   $scope.opts.tabella.items && $scope.opts.tabella.items.length >0) {
 		       						 $('.table-boot-pagination').unbind("page");	 
 		       						 $('.table-boot-pagination').show();
 		       							$('.table-boot-pagination').bootpag({
					    				    total:  $scope.opts.tabella.pagineTotali,
					    				    page:  $scope.opts.tabella.paginaCorrente ,
					    				    maxVisible: maxVisible,
					    				    leaps: true,
					    				    firstLastUse: true,
					    				    wrapClass: 'pagination',
					    				    activeClass: 'active',
					    				    disabledClass: 'disabled',
					    				}).on("page", function(event, num){
					    					
					    					 var sort = [];
					 		    			$.each( $scope.opts.tabella.cols, function ( k,v ) {
					 		    					   if ( angular.isDefined(v.ord)   ) {
					 		    						 sort.push ( { nome : v.data  , dir : v.ord  }  );
					 		    					  } 
					 		    			});
					    					
					    				    $scope.opts.newPage(  num , sort ) ;
					    				    $(this).bootpag({
			 	    				    	     total: $scope.opts.tabella.pagineTotali  
			 	    				        });
					    				});
 		       						
 		       						 } else {
 			 		       			   $('.table-boot-pagination').hide()
 			 		       			  }

 		       						})
 		       			            }	
 		       			};
 		       			
 		       			
 		       			$scope.$watch('opts.drawPagination',   drawPagina() ) 
 		       			
 		         	    $scope.$watch('opts.tabella.items', 
 		                     function (  newVal , oldVal ){
 		         	    	  $timeout(function () {
 		         	    		    if ( $scope.opts.tabella.items && $scope.opts.tabella.items.length >0 )
 		 		       				$($element).find('[data-toggle="popover"]').popover({
				    				    trigger: 'hover'
				     				});	  
 		              		  });
 		              		  drawPagina();
 		              	});
 		         	    
 		         	    // ACTION
 		         	   $scope.sort = function ( col ) {
 		         	    	$log.debug('bsTablePagination->controller->sort');
 	    				    var sort = [];
 		    				$.each( $scope.opts.tabella.cols, function ( k,v ) {
 		    					  
 		    					   if ( col.data == v.data  ) {
 		    						 v.ord = !angular.isDefined(v.ord) || v.ord == 'ASC' ? 'DESC' :'ASC'; 
 		    						 sort.push ( { nome : v.data  , dir : angular.isDefined(v.ord)? v.ord : 'ASC'  }  );
 		    					  } else {
 		    						 v.ord = undefined ;
 		    					  }
 		    				});
 		    				$scope.opts.sortPage( sort ) ;
 		         	    }
 		         	 
 		         	  $scope.visualizzaDettaglio = function ( tr ) {
 		         		    $scope.opts.onClickVisualizzaDettaglio( tr ) ;
 		         	  }
 		   
		         	  $scope.onClickCol = function ( tr, td ) {
		         	    	 if ( angular.isDefined ( td.action ) ) {
	 		         	    	 td.action.fnLoad( tr, td  ) ;
	 		         	    	 $('#'+  td.action.openDialogId ).modal('show');
		         	    	 }
		         	  }
 		         	   
		 }// controller
    };
})
.directive('bsinfocatalogodocumento', function( $parse, $timeout )
{
  return {
      restrict: 'E',
      scope: {
    	  bprogressivogestionecatalogodocumento:'@'
      },  
      template: '<div class="block-info">' +
      			'	<div class="form-group row">' +
      			'		<label class="col-xs-3 col-md-3 control-label" >Catalogo Documento</label>' +
      			'		<div class="col-xs-9 col-md-9" >' +
      			'     		<input type="text" value="{{opt.descrizioneDocumento}}" class="form-control" disabled >' +
      			'		</div>' +
      			'   </div>' +
      			'   <div class="form-group row">' +
      			'      <label class="col-xs-3 col-md-3 control-label" >Anno Triennio</label>' +
      			'      <div class="col-xs-2 col-md-2" >' +
      			'     		<input type="text" value="{{opt.periodoTriennioRiferimento}}" class="form-control" disabled>' +
      			'      </div>' +
      			'   </div>' +
      			'   <div class="form-group row">' +
      			'      <label class="col-xs-3 col-md-3 control-label" >Versione</label>' +
      			'      <div class="col-xs-1 col-md-1" >' +
      			'     		<input type="text" value="{{opt.numeroVersioneDocumento}}" class="form-control" disabled>' +
      			'      </div>' +
      			'   </div>' +
      			'</div> ',
      replace: true,
      controller: function ( $scope, $rootScope, $element ,$window ,$timeout , gestioneDocServicesFactory ) {
		  $scope.opt = {};
    	  gestioneDocServicesFactory.getGestioneCatalogoById($scope.bprogressivogestionecatalogodocumento)
    		.success(function (data, status, config, headers) {
    			console.log('Response from server: ' + data);
    			$scope.opt.descrizioneDocumento   = data.attribForm.gestioneCatalogoDTO.descrizioneDocumento;
    			$scope.opt.periodoTriennioRiferimento   = data.attribForm.gestioneCatalogoDTO.periodoTriennioRiferimentoAsString;
    			$scope.opt.numeroVersioneDocumento   = data.attribForm.gestioneCatalogoDTO.numeroVersioneDocumento;
    		})
    		.error(function (data, status, config, headers) {
    			console.log('Some error occurred!');s = data.fieldErrors; 
    		});
      }
  }
});
