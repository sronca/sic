<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-13-GestioneIniziativeDidaCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
		    
		    <jsp:include page="00-sezione-00-dati-plesso.jsp"></jsp:include>  
		    	   
		   <div class="block-info"  ng-if = "'S_ALTRE_INIZIATIVE_DIDATTICO'==componente.tipoComponente"  class="block-info-noborder" > 	
		   
		   
<!-- TABELLA 1 -->		   
		   <div bs-tablesmall="" data-opts = "vm.tabella1" data-addtargetdialog = "'addDalogTab1'" ></div>
		   <div id = "addDalogTab1" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title">Iniziative Didattiche</h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
	 
									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Titolo 	 </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'"  msd-elastic=""  rows="5" id="_titoli"   ng-model = "vm.tabella1.newItem.titoli"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Obiettivi 	 </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"  id="_obiettivi"   ng-model = "vm.tabella1.newItem.obiettivi"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>		
									 	   
									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Contenuti 	 </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"  id="_contenuti"   ng-model = "vm.tabella1.newItem.contenuti"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Modalit&agrave;</label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"  id="_modalita"   ng-model = "vm.tabella1.newItem.modalita"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Data inizio</label>
										 <div class="col-sm-9 col-xs-9" > 
										   <input  ng-readonly = "vm.tabella1.dialogAction=='V'" maxlength="10" type="text"  data-date-time-picker =""  id="_dataInizio"   ng-model = "vm.tabella1.newItem.dataInizio"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Data fine</label>
										 <div class="col-sm-9 col-xs-9" > 
										   <input  ng-readonly = "vm.tabella1.dialogAction=='V'" maxlength="10" type="text"  data-date-time-picker =""  id="_dataFine"   ng-model = "vm.tabella1.newItem.dataFine"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   
	   
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Area tematica PNSD </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'"  msd-elastic=""  rows="5" cols="50" id="_areaTematicaPNSD" ng-model = "vm.tabella1.newItem.areaTematicaPNSD"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Note</label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50"  ng-model = "vm.tabella1.newItem.note"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	
  
								</form>                      
		                                     
		                                 
								</div>
								
								<div class="modal-footer">
								   <label   ng-if = "vm.tabella1.dialogAction!='V'" ng-click = "vm.tabella1.clickBtOK()"  for="inform"   class="">SALVA</label>
								    <label    for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								   
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
 
