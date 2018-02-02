<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-31-GestioneFabbisognoConnessoFormazioneCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
		   <div class="block-info"  ng-if = "'S_FABBISOGNO_CONNESSO_FORMAZIONE'==componente.tipoComponente"  class="block-info-noborder" > 	
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
										<label for="item1_ambitoFormativo" class= "col-sm-3 col-xs-3 control-label"> Ambito Formativo </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <bsselectpicker  data-bid="item1_ambitoFormativo" data-show = "vm.tabella1.dialogAction=='I'"  data-items="vm.form.ambitiFormativiL" data-selected="vm.tabella1.newItem.ambitoFormativo"></bsselectpicker>
										   <textarea  msd-elastic=""  ng-if="!(vm.tabella1.dialogAction=='I')" readonly="readonly"  ng-model = "vm.tabella1.newItem.ambitoFormativo.label"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									</div>
									
			                   		<div class="form-group row"  ng-if = "vm.tabella1.newItem.ambitoFormativo!=null">  
										<label for="item1_denominazionePercorsoFormativo" class= "col-sm-3 col-xs-3 control-label"> Denominazione Percorso Formativo </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <bsselectpicker  data-bid="item1_denominazionePercorsoFormativo" data-show = "vm.tabella1.dialogAction=='I' && vm.tabella1.newItem.ambitoFormativo!=null"  data-items="vm.form.percorsiFormativiL" data-selected="vm.tabella1.newItem.denominazionePercorsoFormativo"></bsselectpicker>
										   <textarea  msd-elastic=""  ng-if="!(vm.tabella1.dialogAction=='I')" readonly="readonly"  ng-model = "vm.tabella1.newItem.denominazionePercorsoFormativo.label"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									</div>
									 
									<div class="form-group row" >  
										<label for="item1_nomeBeneSerizio" class= "col-sm-3 col-xs-3 control-label"> Nome Bene/Servizio </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic=""  rows="5"  ng-readonly = "vm.tabella1.dialogAction=='V'"   id="item1_nomeBeneServizio" ng-model = "vm.tabella1.newItem.nomeBeneServizio"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_descrizioneBeneSerizio" class= "col-sm-3 col-xs-3 control-label"> Descrizione Bene/Servizio </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic=""  rows="5"  ng-readonly = "vm.tabella1.dialogAction=='V'"   id="item1_descrizioneBeneServizio" ng-model = "vm.tabella1.newItem.descrizioneBeneServizio"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	
									 
									<div class="form-group row" >  
										<label for="item1_classificazione" class= "col-sm-3 col-xs-3 control-label"> Classificazione </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea msd-elastic=""  rows="5"  ng-readonly = "vm.tabella1.dialogAction=='V'"  id="item1_classificazione" ng-model = "vm.tabella1.newItem.classificazione"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_numeroDocentiATACoinvolti" class= "col-sm-3 col-xs-3 control-label"> Numero Docenti ATA Coinvolti</label>
										 <div class="col-sm-9 col-xs-9" > 
										  <input type="text"  ng-readonly = "vm.tabella1.dialogAction=='V'" fa-numeric="numeroInteroType" id="item1_numeroDocentiATACoinvolti" fa-model = "vm.tabella1.newItem.numeroDocentiATACoinvolti" class="form-control" placeholder="inserisci"></input>
										 </div>			   
									</div>	
									 
									<div class="form-group row" >  
										<label for="item1_importoStimato" class= "col-sm-3 col-xs-3 control-label"> Importo Stimato</label>
										 <div class="col-sm-9 col-xs-9" > 
										  <input type="text"  ng-readonly = "vm.tabella1.dialogAction=='V'" fa-numeric="amount2DecimalType" id="item1_importoStimato" fa-model = "vm.tabella1.newItem.importoStimato" class="form-control" placeholder="inserisci"></input>
										 </div>			   
									</div>	
									<div class="form-group row" ng-if="!(vm.tabella1.dialogAction=='I') && vm.tabella1.statoDocumento == 'CONVALIDATO'">  
										<label for="item1_totaleImportoStimatoAmbitoFormativo" class= "col-sm-3 col-xs-3 control-label"> Totale Importo Stimato per Ambito Formativo</label>
										 <div class="col-sm-9 col-xs-9" > 
										  <input type="text"  ng-readonly = "vm.tabella1.dialogAction=='V'" fa-numeric="amount2DecimalType" id="item1_totaleImportoStimatoAmbitoFormativo" fa-model = "vm.tabella1.newItem.totaleImportoStimatoAmbitoFormativo" class="form-control" placeholder=""></input>
										 </div>			   
									</div>			   

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Note </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea msd-elastic="" ng-readonly = "vm.tabella1.dialogAction=='V'" cols="50" id="item1_note" ng-model = "vm.tabella1.newItem.note" class="form-control" placeholder="inserisci"></textarea>
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
 
