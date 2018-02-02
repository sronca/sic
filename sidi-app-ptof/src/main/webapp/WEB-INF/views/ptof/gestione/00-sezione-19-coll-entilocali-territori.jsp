<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-19-GestioneCollEntiLocaliCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
	
	
		  	    
		    <div   class="block-info block-dark"  ng-if = "'TEXTEDITOR'==componente.tipoComponente"  class="block-info-noborder" > 	
			 		 <textarea   rows="10" cols="50"  ui-tinymce="tinymceOptions" ng-model = "componente.valore"   rows="10" cols="50" aria-label="Descrizione" tabindex="10">  </textarea>
            </div>	    

<!-- TABELLA 1 -->		
    
		   <div class="block-info"  ng-if = "'S_PROMOZIONE_RAPPORTI_ENTI_LOCALI_TERRITORIO'==componente.tipoComponente"  class="block-info-noborder" > 	
		   <div bs-tablesmall="" data-opts = "vm.tabella1" data-addtargetdialog = "'addDalogTab1'" ></div>
		   <div id = "addDalogTab1" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title">{{ componente.nome }}</h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Enti Locali </label>   
										 <div class="col-sm-9 col-xs-9" > 
										    <textarea  msd-elastic=""  rows="5" ng-readonly = "vm.tabella1.dialogAction=='V'" id="_entiLocali"   ng-model = "vm.tabella1.newItem.entiLocali"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									 </div>			   
 
									<div class="form-group row" >  
										<label for="item1_tipologiaPromozione" class= "col-sm-3 col-xs-3 control-label"> Tipologia Promozione </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"   id="_tipologiaPromozione" ng-model = "vm.tabella1.newItem.tipologiaPromozione"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   


									<div class="form-group row" >  
										<label for="item1_azioniIntraprese" class= "col-sm-3 col-xs-3 control-label"> Azioni Intraprese </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"   id="_descrizione" ng-model = "vm.tabella1.newItem.azioniIntraprese"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	
									 
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Area tematica  PNSD </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea   msd-elastic=""  rows="5" ng-readonly = "vm.tabella1.dialogAction=='V'"     id="_obiettivi" ng-model = "vm.tabella1.newItem.areaTematicaPNSD"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   
 									 
								</form>                      
		                                     
		                                 
								</div>
								
								<div class="modal-footer">
								   <label   ng-if = "vm.tabella1.dialogAction!='V'" ng-click = "vm.tabella1.clickBtOK()"  for="inform"   class="">SALVA</label>
								    <label   for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								</div>
							
							</div>
						</div>
			</div>
		</div> 
<!-- TABELLA 1 END-->	

<!-- TABELLA 2  , , ,  -->		   

		   <div class="block-info"  ng-if = "'S_CONVENZIONI_STIPULATE_POTENZIAMENTO_FORMATIVO'==componente.tipoComponente"  class="block-info-noborder" > 	
		   <div bs-tablesmall="" data-opts = "vm.tabella2" data-addtargetdialog = "'addDalogTab2'" ></div>
		   <div id = "addDalogTab2" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title">{{ componente.nome }}</h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Convenzioni </label>   
										 <div class="col-sm-9 col-xs-9" > 
										    <textarea rows="10" ng-readonly = "vm.tabella2.dialogAction=='V'" id="_t2_convenzioni"   ng-model = "vm.tabella2.newItem.convenzioni"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									 </div>
									 			   
 								    <div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Organizzazione </label>   
										 <div class="col-sm-9 col-xs-9" > 
										    <textarea  msd-elastic=""  rows="5" ng-readonly = "vm.tabella2.dialogAction=='V'" id="_t2_organizzazione"   ng-model = "vm.tabella2.newItem.organizzazione"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									 </div> 		   
	                                 
 								    <div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Note </label>   
										 <div class="col-sm-9 col-xs-9" > 
										    <textarea rows="10" ng-readonly = "vm.tabella2.dialogAction=='V'" id="_t2_note"   ng-model = "vm.tabella2.newItem.note"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									 </div> 		   
	 	
										 
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Area tematica  PNSD </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic=""  rows="5" ng-readonly = "vm.tabella2.dialogAction=='V'"     id="_t2_obiettivi" ng-model = "vm.tabella2.newItem.areaTematicaPNSD"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   
 									 
								</form>                      
		                                     
		                                 
								</div>
								
								<div class="modal-footer">
								   <label   ng-if = "vm.tabella2.dialogAction!='V'" ng-click = "vm.tabella2.clickBtOK()"  for="inform"   class="">SALVA</label>
								    <label   for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								</div>
							
							</div>
						</div>
			</div>
		</div> 
<!-- TABELLA 2 END-->
			     
    </div>           
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
