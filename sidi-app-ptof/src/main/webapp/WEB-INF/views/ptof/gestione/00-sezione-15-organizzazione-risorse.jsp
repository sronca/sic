<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-15-GestioneOrganizzazioneRisorseCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
	  	    
		   <jsp:include page="00-sezione-00-allegato.jsp"></jsp:include>

<!-- TABELLA 1 -->			    	   
		   <div class="block-info"  ng-if = "'S_ORGANIZZAZIONE_RISORSE'==componente.tipoComponente"  class="block-info-noborder" > 	
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
										<label class= "col-sm-3 col-xs-3 control-label">Ruolo	 </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <bsselectpicker data-bid="_ruolo" data-show = "vm.tabella1.dialogAction=='I'"  data-items="vm.form.ruoliL" data-selected="vm.tabella1.newItem.ruolo"></bsselectpicker>
										    <input type="text"  ng-if="!(vm.tabella1.dialogAction=='I')"     readonly="readonly"  ng-model = "vm.tabella1.newItem.ruolo.label"    class="form-control" /> 
										 </div>			   
									 </div>	
	 
									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Nome </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea     msd-elastic=""  rows="5"  ng-readonly = "vm.tabella1.dialogAction=='V'"   id="_nome"   ng-model = "vm.tabella1.newItem.nome"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   
  	 
 								     <div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Responsabilit&agrave; </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"  id="_responsabilita"   ng-model = "vm.tabella1.newItem.responsabilita"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>	

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Note </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_noteResponsabilita"  ng-model = "vm.tabella1.newItem.noteResponsabilita"    class="form-control" placeholder="inserisci"></textarea>
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

<!-- TABELLA 2 -->			    	   
		   <div class="block-info"  ng-if = "'S_ORGANIZZAZIONE_RISORSE_ALTRI_RUOLI'==componente.tipoComponente"  class="block-info-noborder" > 	
		   <div bs-tablesmall="" data-opts = "vm.tabella2" data-addtargetdialog = "'addDalogTab2'" ></div>
		   <div id = "addDalogTab2" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title">{{ componente.nome }} </h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">

	                                 <div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Ruolo	 </label>
										 <div class="col-sm-9 col-xs-9" > 
										    <textarea ng-readonly = "vm.tabella2.dialogAction=='V'"  msd-elastic=""  rows="5"   id="_t2_ruolo"  ng-model = "vm.tabella2.newItem.ruolo.label"    class="form-control" ></textarea>
										 </div>			   
									 </div>	
	 
									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Nome </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella2.dialogAction=='V'"  msd-elastic=""  rows="5"  id="_t2_nome"   ng-model = "vm.tabella2.newItem.nome"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   
  	 
 								     <div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Responsabilit&agrave; </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella2.dialogAction=='V'" rows="10"  id="_t2_responsabilita"   ng-model = "vm.tabella2.newItem.responsabilita"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>	

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Note </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella2.dialogAction=='V'" rows="10" cols="50" id="_t2_noteResponsabilita"  ng-model = "vm.tabella2.newItem.noteResponsabilita"    class="form-control" placeholder="inserisci"></textarea>
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
 
