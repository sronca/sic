
	<div class="tab-content" ng-controller="ConsultazionePtofCtrl as consultazionePtofCtrl">
        <div class="wrapper-content">
	 		<p class="title">${titoloReport}</p>
	 			<p class="text"></p>
        </div>


 	<div class="tab-pane fade in active" id="target2">
	<fieldset>
 <!-- FILTRI -->
 		 <!-- FILTRI -->	
 		<div id="filtro-ricerca">
		      <bsinfocatalogodocumento data-bprogressivogestionecatalogodocumento="${progressivoGestioneCatalogoDocumento}"></bsinfocatalogodocumento>
		      <div class="block-info">
		      
		      			 <div class="form-group row">   
					        <form role="form" class = "form-horizontal">     
					                <input type="hidden" ng-model="vm.form.progressivoGestioneCatalogoDocumento" ng-init="vm.form.progressivoGestioneCatalogoDocumento=${progressivoGestioneCatalogoDocumento}">   
						            
						             <label  class="col-xs-6 col-md-2 control-label" >Regioni</label>
							         <div class="col-xs-6 col-md-4" ng-if = "vm.attribForm.consultazionePtof.regioniL.length==1">   
	  											<input type="text" ng-model ="vm.form.consultazionePtof.regione.label"  class="form-control" disabled />
								     </div>
								        
								     <div class="col-xs-6 col-md-4" ng-if = "vm.attribForm.consultazionePtof.regioniL.length>1">
							             	<bsselectpicker2  data-show = "true" data-bid="item1"  data-items="vm.attribForm.consultazionePtof.regioniL"  data-selected="vm.form.consultazionePtof.regione" ></bsselectpicker2> 
							         </div>
					            
						             <label  class="col-xs-6 col-md-2 control-label">Province</label>
							          <div class="col-xs-6 col-md-4" >  									  					
							           		<bsselectpicker2  data-show = "true" data-bid="item2"  data-bdisabled="false" data-items="vm.attribForm.consultazionePtof.provinceL" data-selected="vm.form.consultazionePtof.provincia" ></bsselectpicker2> 
							          </div>
					        </form>
					     </div> 
					     
					     
					     <div class="form-group row">   
					        <form role="form" class = "form-horizontal">     
					             
						            <label   class="col-xs-6 col-md-2 control-label" >Comuni</label>
							        <div class="col-xs-6 col-md-4" >   
							        	<bsselectpicker2   data-show = "true" data-bid="item3"  data-items="vm.attribForm.consultazionePtof.comuniL" data-selected="vm.form.consultazionePtof.comune" ></bsselectpicker2> 
							        </div>
							        
							        <label   class="col-xs-6 col-md-2 control-label" >Cod. mecc.</label>
				                     <div class="col-xs-6 col-md-4" >
				                            <input type="text" ng-model ="vm.form.consultazionePtof.codMeccanografico"   class="form-control" />
				                     </div>
					        </form>
					     </div>
		    </div>      
		    
		    <div class="row" >
				 			 <div class="col-xs-2 col-md-3 text-left">  </div>
							 <div class="col-xs-10 col-md-9  text-right">
									 <input  ng-click= "indietro()" class="send" type="submit" value="INDIETRO" aria-label="Invia il form" tabindex="70">
									 <input  ng-click= "cancellaFiltriReport()" class="send" type="submit" value="ANNULLA" aria-label="Invia il form" tabindex="70" > </input> 
									 <input  ng-click= "ricercaConsultazionePtof()" class="send" type="submit" value="RICERCA" aria-label="Invia il form" tabindex="70" > </input> 
							 </div>
			</div>   
		    
		</div>
<!-- FILTRI -->	
 

 
	<!--    -->	
 		 	
 		 <div ng-if="(vm.attribForm.richiestaT.tabella.items && vm.attribForm.richiestaT.tabella.items.length >0)">
		 		 <div class="wrapper-content">
			 		<p class="title">CONSULTAZIONE FABBISOGNO</p>
			 			<p class="text"></p>
		        </div>
 				<div data-bs-table-fabbisogno-pagination="" data-opts = "vm.attribForm.richiestaT"  ></div>
		 </div>
		 
		 <div ng-if="(vm.attribForm.richiestaT.tabella.items && vm.attribForm.richiestaT.tabella.righeTotali == 0)" class="alert alert-warning">			
			<span class="icon miur-attenzione text-warning">&nbsp;&nbsp;<strong>Attenzione: </strong>nessun risultato trovato.</span>
		</div>
 

  		</fieldset>
    </div>
  </div>
