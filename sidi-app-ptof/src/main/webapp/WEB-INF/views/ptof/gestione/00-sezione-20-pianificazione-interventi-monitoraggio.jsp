<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-20-GestionePianifizioneInterventiMonitoraggioCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
 
<!-- TABELLA 1 -->		
<div class="block-info"  ng-if = "'S_PIANIFICAZIONE_ATTIVITA'==componente.tipoComponente"  class="block-info-noborder" > 	
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
										<label class= "col-sm-3 col-xs-3 control-label"> Pianificazione Attivi&agrave; </label>    
										 <div class="col-sm-9 col-xs-9" >  
										    <textarea  msd-elastic=""  rows="5"  ng-readonly = "vm.tabella1.dialogAction=='V'" id="_pianificazioneAttivita"   ng-model = "vm.tabella1.newItem.pianificazioneAttivita"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									 </div>			   
									<div class="form-group row" >  
										<label for="item1_tipologiaPromozione" class= "col-sm-3 col-xs-3 control-label"> Descrizione </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"   id="_descrizione" ng-model = "vm.tabella1.newItem.descrizione"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   
									<div class="form-group row" >  
										<label for="item1_azioniIntraprese" class= "col-sm-3 col-xs-3 control-label"> Capitolo PTOF </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic=""  rows="5" ng-readonly = "vm.tabella1.dialogAction=='V'"    id="_capitoloPTOF" ng-model = "vm.tabella1.newItem.capitoloPTOF"    class="form-control" placeholder="inserisci"></textarea>
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
<div class="block-info"  ng-if = "'S_MONITORAGGIO_PIANIFICAZIONE'==componente.tipoComponente"  class="block-info-noborder" > 	
 
  <form role="form" class = "form-horizontal">
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Modalit&agrave; di verifica </label>    
										 <div class="col-sm-9 col-xs-9" >  
										    <textarea rows="10" id="_modalitaVerifica"   ng-model = "componente.modalitaVerifica"    class="form-control"  placeholder="inserisci" ></textarea>
										 </div>			   
									 </div>			   
									<div class="form-group row" >  
										<label for="item1_tipologiaPromozione" class= "col-sm-3 col-xs-3 control-label"> Descrizione </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  rows="10"  id="_soluzioniAdottateRispettoPianificazione" ng-model = "componente.soluzioniAdottateRispettoPianificazione"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
			                        </div>
   </form>			                        	

</div>
 
			     
    </div>           
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
