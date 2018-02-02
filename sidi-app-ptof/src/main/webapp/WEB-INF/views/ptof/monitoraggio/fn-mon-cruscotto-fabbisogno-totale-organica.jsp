<%@ include file="../../layout/taglib.jsp"%>
<div class="tab-content" ng-controller="MonitoraggioFabbisognoTotaleOrganicaCtrl as monitoraggioFabbisognoTotaleOrganicaCtrl">
    <div class="wrapper-content">
 		<p class="title">${titoloReport}</p>
 	</div>
 	<div class="tab-pane fade in active" id="target2">
		<fieldset>	
	 	<!-- FILTRI -->	
	 		<div id="filtro-ricerca">
	 			<bsinfocatalogodocumento data-bprogressivogestionecatalogodocumento="${progressivoGestioneCatalogoDocumento}"></bsinfocatalogodocumento>
	 			  
			    <div class="block-info">
	               <div class="form-group row"  > 
				         <form role="form" class = "form-horizontal">  
				          <input type="hidden" ng-model="vm.form.progressivoGestioneCatalogoDocumento" ng-init="vm.form.progressivoGestioneCatalogoDocumento=${progressivoGestioneCatalogoDocumento}">   
				          <label class="col-xs-1 col-md-1 control-label" >Regioni</label>
				          <div class="col-xs-6 col-md-4" ng-if = "vm.attribForm.regioniL.length==1">   
	  						  <input type="text" ng-model ="vm.form.regione.label"  class="form-control" disabled />
						  </div>
				           <div class="col-xs-3 col-md-3" ng-if = "vm.attribForm.regioniL.length>1"> 
				           		<bsselectpicker2 data-show = "true" data-bid="item1" data-items="vm.attribForm.regioniL" data-selected="vm.form.regione"></bsselectpicker2>
				          </div>
				         </form>  
				   </div>
			    </div>      
			    
			    <div class="row" >
		 			 <div class="col-xs-2 col-md-3 text-left">  </div>
						 <div class="col-xs-10 col-md-9  text-right">
						 <input  ng-click= "indietro()" class="send" type="submit" value="INDIETRO" aria-label="Invia il form" tabindex="70">
						 <input  ng-click= "scaricaReport()" class="send" type="submit" value="SCARICA" aria-label="Invia il form" tabindex="70" > </input> 
					 </div>
				</div>   
			</div>
		</fieldset>	
    </div>
  </div>	
<!-- FILTRI -->	
 