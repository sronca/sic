<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-14-GestioneAttivitaSostegnoCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
		    
		   <jsp:include page="00-sezione-00-dati-plesso.jsp"></jsp:include>  
		    
		   <jsp:include page="00-sezione-00-allegato.jsp"></jsp:include>
		    	   
		   <div class="block-info"  ng-if = "'S_ATTIVITA_SOSTEGNO'==componente.tipoComponente"  class="block-info-noborder" > 	
		   
		   
<!-- TABELLA 1 -->		   
		   <div bs-tablesmall="" data-opts = "vm.tabella1" data-addtargetdialog = "'addDalogTab1'" ></div>
		   <div id = "addDalogTab1" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title">Attivita sostegno</h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">


	 
									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Contenuti dell'attivit&agrave; di sostegno </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"  id="_contenutiAttivitaSostegno"   ng-model = "vm.tabella1.newItem.contenutiAttivitaSostegno"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Metodologie utilizzate </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"  id="_metodologieUtilizzate"   ng-model = "vm.tabella1.newItem.metodologieUtilizzate"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Cooperazione con servizi socio-sanitari e associazioni di settore </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"  id="_coopServiziSSAssocSettore"   ng-model = "vm.tabella1.newItem.coopServiziSSAssocSettore"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Aspetti di supporto logistico </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10"  id="_aspettiSupportoLogistico"   ng-model = "vm.tabella1.newItem.aspettiSupportoLogistico"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   


									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Note</label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea ng-readonly = "vm.tabella1.dialogAction=='V'"  rows="10" cols="50"  ng-model = "vm.tabella1.newItem.note"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	
  
								</form>                      
		                                     
		                                 
								</div>
								
								<div class="modal-footer">
								   <label   ng-if = "vm.tabella1.dialogAction!='V'" ng-click = "vm.tabella1.clickBtOK()"  for="inform"   class="">SALVA</label>
								   
								   <label     for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								   
								</div>
							
							</div>
						</div>
			</div>
<!-- TABELLA 1 END-->	

 

			</div> 
			     
    </div>    <!-- repeat componenti -->
    		   
    
    
           
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
