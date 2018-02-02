<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-30-GestioneFabbisognoConnessoProgettoCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
		   <div class="block-info"  ng-if = "'S_FABBISOGNO_CONNESSO_PROGETTO'==componente.tipoComponente"  class="block-info-noborder" > 	
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
										<label for="item1_tipoProgetti" class= "col-sm-3 col-xs-3 control-label"> Tipo Progetto </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <bsselectpicker  data-bid="item1_tipoProgetti" data-show = "vm.tabella1.dialogAction=='I'"  data-items="vm.form.tipoProgettiScuolaL" data-selected="vm.tabella1.newItem.tipoProgetti"></bsselectpicker>
										   <textarea  msd-elastic=""  ng-if="!(vm.tabella1.dialogAction=='I')" readonly="readonly"  ng-model = "vm.tabella1.newItem.tipoProgetti.label"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									</div>
									
									<div class="form-group row" ng-if = "vm.tabella1.newItem.tipoProgetti!=null">  
										<label for="item1_denominazioneProgettiScuola" class= "col-sm-3 col-xs-3 control-label"> Denominazione Progetti Scuola</label>
										 <div class="col-sm-9 col-xs-9" > 
										   <bsselectpicker  data-bid="item1_denominazioneProgettiScuola" data-show = "vm.tabella1.dialogAction=='I' && vm.tabella1.newItem.tipoProgetti!=null"  data-items="vm.form.denominazioneProgettiScuolaL" data-selected="vm.tabella1.newItem.denominazioneProgettiScuola"></bsselectpicker>
										   <textarea  msd-elastic=""  ng-if="!(vm.tabella1.dialogAction=='I')" readonly="readonly"  ng-model = "vm.tabella1.newItem.denominazioneProgettiScuola.label"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									</div>
									
									<div class="form-group row" >  
										<label for="item1_codiceMeccanograficoPlesso" class= "col-sm-3 col-xs-3 control-label"> Codice Meccanografico </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic="" ng-readonly = "true" id="item1_codiceMeccanograficoPlesso" ng-model = "vm.tabella1.newItem.codiceMeccanograficoPlesso" class="form-control" placeholder=""></textarea>
										 </div>			   
									 </div>
									 
									<div class="form-group row" >  
										<label for="item1_nomeBeneServizio" class= "col-sm-3 col-xs-3 control-label"> Nome Bene/Servizio </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic="" ng-readonly = "vm.tabella1.dialogAction=='V'"   id="item1_nomeBeneServizio" ng-model = "vm.tabella1.newItem.nomeBeneServizio"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_descrizioneBeneServizio" class= "col-sm-3 col-xs-3 control-label"> Descrizione Bene/Servizio </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic=""  ng-readonly = "vm.tabella1.dialogAction=='V'"   id="item1_descrizioneBeneServizio" ng-model = "vm.tabella1.newItem.descrizioneBeneServizio"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	
									 
									<div class="form-group row" >  
										<label for="item1_classificazione" class= "col-sm-3 col-xs-3 control-label"> Classificazione </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea msd-elastic=""  ng-readonly = "vm.tabella1.dialogAction=='V'"  id="item1_classificazione" ng-model = "vm.tabella1.newItem.classificazione"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_numeroDocentiATACoinvolti" class= "col-sm-3 col-xs-3 control-label"> Numero Docenti ATA Coinvolti </label>
										 <div class="col-sm-9 col-xs-9" > 
										  <input type="text"  ng-readonly = "vm.tabella1.dialogAction=='V'" fa-numeric2="numeroInteroType" id="item1_numeroDocentiATACoinvolti"  ng-model = "vm.tabella1.newItem.numeroDocentiATACoinvolti" class="form-control" placeholder="inserisci"></input>
										 </div>			   
									</div>	
									 
									<div class="form-group row" >  
										<label for="item1_importoStimato" class= "col-sm-3 col-xs-3 control-label"> Importo Stimato</label>
										 <div class="col-sm-9 col-xs-9" > 
										  <input type="text"  ng-readonly = "vm.tabella1.dialogAction=='V'" fa-numeric2="amount2DecimalType" id="item1_importoStimato" ng-model = "vm.tabella1.newItem.importoStimato" class="form-control" placeholder="inserisci"></input>
										 </div>			   
									</div>	
									
									<div class="form-group row" ng-if="!(vm.tabella1.dialogAction=='I') && vm.tabella1.statoDocumento == 'CONVALIDATO'">  
										<label for="item1_totaleImportoStimatoProgetto" class= "col-sm-3 col-xs-3 control-label"> Totale Importo Stimato Progetto</label>
										 <div class="col-sm-9 col-xs-9" > 
										  <input type="text"  ng-readonly = "true" fa-numeric="amount2DecimalType" id="item1_totaleImportoStimatoProgetto" fa-model = "vm.tabella1.newItem.totaleImportoStimatoProgetto" class="form-control" placeholder=""></input>
										 </div>			   
									</div>			   

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Note </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea msd-elastic="" ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="item1_note" ng-model = "vm.tabella1.newItem.note" class="form-control" placeholder="inserisci"></textarea>
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
 
