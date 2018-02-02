<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-17-GestioneProgrammazioneFormazioneCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
		    
		   
		   <div class="block-info"  ng-if = "'S_PROGRAMMAZIONE_FORMAZIONE_'==componente.tipoComponente.substr(0,componente.tipoComponente.length-3)"  class="block-info-noborder" > 	
		   
		   
<!-- TABELLA 1 -->		   
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
										<label class= "col-sm-3 col-xs-3 control-label"> Ambito formativo </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <bsselectpicker data-bid="_ambitoFormativo" data-show = " vm.tabella1.dialogAction!='V' "  data-items="vm.attribForm.ambitiFormativiL" data-selected="vm.tabella1.newItem.ambitoFormativo"></bsselectpicker>
										    <textarea  msd-elastic=""  rows="5"   readonly="readonly"  ng-model = "vm.tabella1.newItem.ambitoFormativo.label"    class="form-control" ></textarea>
										 </div>			   
									 </div>			   
 
									<div class="form-group row" >  
										<label for="item1_denominazione" class= "col-sm-3 col-xs-3 control-label"> Denominazione percorso formativo </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_denominazionePercorsoFormativo" ng-model = "vm.tabella1.newItem.denominazionePercorsoFormativo"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Obiettivi </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_obiettivi" ng-model = "vm.tabella1.newItem.obiettivi"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Contenuti </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_contenuti" ng-model = "vm.tabella1.newItem.contenuti"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   
 								
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Tempi/ore complessive </label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input type="text" fa-numeric="numeroIntero2DType"  placeholder="inserisci" ng-readonly = "vm.tabella1.dialogAction=='V'"   id="_tempiOreComplessive"  fa-model = "vm.tabella1.newItem.tempiOreComplessive"    class="form-control"  ></input>
										 </div>			   
									 </div>		 

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Data inizio   </label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input maxlength="10" type="text" id = "_dataInizio"  ng-readonly = "vm.tabella1.dialogAction=='V'"
										    data-date-time-picker =""
										    ng-model = "vm.tabella1.newItem.dataInizio"  class="form-control text-center" placeholder="inserisci"></input>
										 </div>			   
									 </div>		 

 									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Data fine   </label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input maxlength="10" type="text" id = "_dataFine"  ng-readonly = "vm.tabella1.dialogAction=='V'"
										    data-date-time-picker =""
										    ng-model = "vm.tabella1.newItem.dataFine"  class="form-control text-center" placeholder="inserisci"></input>
										 </div>			   
									 </div>		 
 	 
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Area tematica PNSD </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea   msd-elastic=""  rows="5" ng-readonly = "vm.tabella1.dialogAction=='V'"   cols="50" id="_areaTematicaPNSD" ng-model = "vm.tabella1.newItem.areaTematicaPNSD"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	


									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Percorso formativo organizzato tra pi&ugrave; scuole </label>
										 <div class="col-sm-2 col-xs-2" > 
										    <bsselectpicker data-bid="_ambitoFormativo" data-show = " vm.tabella1.dialogAction!='V'"  data-items="vm.attribForm.checkSiNoL" data-selected="vm.tabella1.newItem.percorsoFormativo"></bsselectpicker>
										    <input type="text" readonly="readonly" ng-if = "vm.tabella1.dialogAction=='V'" ng-model = "vm.tabella1.newItem.percorsoFormativo.label"    class="form-control" ></input>
										 </div>			   
									 </div>


									<div class="form-group row" ng-if ="vm.tabella1.newItem.percorsoFormativo && vm.tabella1.newItem.percorsoFormativo.value=='S'"  >  
										<label class= "col-sm-3 col-xs-3 control-label">  Collaborazione con Rete Scolastica </label>
										 <div class="col-sm-2 col-xs-2" > 
										   <bsselectpicker data-bid="_collaborazioneReteScolastic" data-show = "vm.tabella1.dialogAction!='V'"  data-items="vm.attribForm.checkSiNoL" data-selected="vm.tabella1.newItem.collaborazioneReteScolastic"></bsselectpicker>
										   <input type="text" readonly="readonly" ng-if = "vm.tabella1.dialogAction=='V'" ng-model = "vm.tabella1.newItem.collaborazioneReteScolastic.label"    class="form-control" ></input>
										 </div>			   
									 </div>

									<div class="form-group row" ng-if ="vm.tabella1.newItem.percorsoFormativo && vm.tabella1.newItem.percorsoFormativo.value=='S'" >  
										<label class= "col-sm-3 col-xs-3 control-label">  Condiviso </label>
										 <div class="col-sm-2 col-xs-2" > 
										   <bsselectpicker data-bid="_condiviso" data-show = "vm.tabella1.dialogAction!='V'"  data-items="vm.attribForm.checkSiNoL" data-selected="vm.tabella1.newItem.condiviso"></bsselectpicker>
										   <input type="text" readonly="readonly" ng-if = "vm.tabella1.dialogAction=='V'" ng-model = "vm.tabella1.newItem.condiviso.label"    class="form-control" ></input>
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
<!-- TABELLA 1 END-->	

 

			</div> 
			     
    </div>           
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
