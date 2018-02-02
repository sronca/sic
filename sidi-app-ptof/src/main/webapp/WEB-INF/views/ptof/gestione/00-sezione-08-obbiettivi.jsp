<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-08-ObbiettiviCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
		    
			<div   class="block-info block-dark"  ng-if = "'TEXTEDITOR'==componente.tipoComponente"  class="block-info-noborder" > 	
			 		 <textarea   rows="10" cols="50"  ui-tinymce="tinymceOptions" ng-model = "componente.valore"   rows="10" cols="50" aria-label="Descrizione" tabindex="10">  </textarea>
			</div> 
		   
		   <div class="block-info"  ng-if = "'S_OBBIETTIVI_FORMATIVI'==componente.tipoComponente"  class="block-info-noborder" > 	
	  
<!-- TABELLA 1 -->		   
		   <div bs-tablesmall="" data-opts = "vm.tabella1" data-addtargetdialog = "'addDalogTab1'" ></div>
		   
		   <div id = "addDalogTab1" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title"> {{ componente.nome }} </h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label"> Obiettivo </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <bsselectpicker data-show= "vm.tabella1.dialogAction=='I'" data-bid="item1_obbiettivi"  data-items="vm.form.obbiettiviL" data-selected="vm.tabella1.newItem.obbiettivo"></bsselectpicker>
										   <textarea rows="10" cols="50"    id="item1_obbiettivi"  readonly  ng-model = "vm.tabella1.newItem.obbiettivo.label"    class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_finalita" class= "col-sm-3 col-xs-3 control-label"> Finalit&agrave; </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea ng-readonly = "vm.tabella1.dialogAction=='V'"  rows="10" cols="50"  id="item1_finalita" ng-model = "vm.tabella1.newItem.finalita"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_benefici" class= "col-sm-3 col-xs-3 control-label"> Benefici Attesi </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="item1_benefici" ng-model = "vm.tabella1.newItem.benefici"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_priorita" class= "col-sm-3 col-xs-3 control-label"> Priorit&agrave;  </label>
										 <div class="col-sm-9 col-xs-9" >
										    <bsselectpicker data-bid="item1_priorita" data-show = "vm.tabella1.dialogAction!='V'" data-items="vm.attribForm.prioritaL" data-selected="vm.tabella1.newItem.priorita"></bsselectpicker>
										    <textarea rows="10" cols="50"  ng-if = "vm.tabella1.dialogAction=='V'" id="item1_obbiettivi"  readonly  ng-model = "vm.tabella1.newItem.priorita.label"    class="form-control" > </textarea>

										 </div>			   
									</div>
								</form>                      
		                                     
		                                 
								</div>
								
								<div class="modal-footer">
								   <label  ng-if = "vm.tabella1.dialogAction!='V'"  ng-click = "vm.tabella1.clickBtOK()"  for="inform"   class="">SALVA</label>
								    <label    for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								   
								</div>
							
							</div>
						</div>
			</div>
<!-- TABELLA 1 END-->	
		</div>

		<div class="block-info"  ng-if = "'S_OBBIETTIVI_FORMATIVI_ALTRI'==componente.tipoComponente"  class="block-info-noborder" > 	
<!-- TABELLA 2 -->		   
		   <div bs-tablesmall="" data-opts = "vm.tabella2" data-addtargetdialog = "'addDalogTab2'" ></div>
		   <div id = "addDalogTab2" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title"> {{ componente.nome }} </h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
									<div class="form-group row" >  
										<label for="item2_obbiettivi" class= "col-sm-3 col-xs-3 control-label"> Obiettivo </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella2.dialogAction=='V'" rows="10" cols="50"  id="item2_obbiettivi"  ng-model = "vm.tabella2.newItem.obbiettivo.label"    class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item2_finalita" class= "col-sm-3 col-xs-3 control-label"> Finalit&agrave; </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella2.dialogAction=='V'" rows="10" cols="50"  id="item2_finalita" ng-model = "vm.tabella2.newItem.finalita"    class="form-control" placeholder="inserisci" ></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item2_benefici" class= "col-sm-3 col-xs-3 control-label"> Benefici Attesi </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella2.dialogAction=='V'" rows="10" cols="50"  id="item2_benefici" ng-model = "vm.tabella2.newItem.benefici"    class="form-control" placeholder="inserisci"> </textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item2_priorita" class= "col-sm-3 col-xs-3 control-label"> Priorit&agrave;  </label>
										 <div class="col-sm-9 col-xs-9" > 
                                            										 
										    <bsselectpicker data-bid="item2_priorita" data-show = "vm.tabella2.dialogAction!='V'" data-items="vm.attribForm.prioritaL" data-selected="vm.tabella2.newItem.priorita"></bsselectpicker>
                                            <textarea rows="10" cols="50"  ng-if = "vm.tabella2.dialogAction=='V'" id="item2_priorita"  readonly  ng-model = "vm.tabella2.newItem.priorita.label"    class="form-control" ></textarea>
										 </div>			   
									</div>
								</form>                      
		                                     
		                                 
								</div>
								
								<div class="modal-footer">
								   <label   ng-if = "vm.tabella2.dialogAction!='V'" ng-click = "vm.tabella2.clickBtOK()"  for="inform"   class="">SALVA</label>
								    <label     for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								</div>
							
							</div>
						</div>
			</div>
<!-- TABELLA 2 END-->	


			</div> 
			     
    </div>           
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
