<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">
 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-29-GestioneFabbisognoPostiPersonaleAmmTecAusiCtrl as gestioneSezioneCtrl">
		 
		<fieldset>
			<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
			    <div class="block-info"  ng-if = "'S_FABBISOGNO_POSTI_PERSONALE_AMM_TEC_AUSI'==componente.tipoComponente"  class="block-info-noborder" >
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
												<label for="item1_figuraProfessionale" class= "col-sm-3 col-xs-3 control-label"> Profilo Professionale </label>
												 <div class="col-sm-9 col-xs-9" > 
												   <textarea   msd-elastic=""  rows="5" readonly="readonly"   id="item1_figuraProfessionale" ng-model = "vm.tabella1.newItem.figuraProfessionale"    class="form-control" placeholder="inserisci"></textarea>
												 </div>			   
											 </div>			
		
											<div class="form-group row" >  
												<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Posti</label>
												 <div class="col-sm-9 col-xs-9" >
												 	<div class="row">
												 		<div class="col-sm-3 col-xs-3 text-center">
												 			{{ componente.labelAnno0 }}
												 		</div>
												 		<div class="col-sm-3 col-xs-3 text-center">
												 			{{ componente.labelAnno1 }}
												 		</div>
												 		<div class="col-sm-3 col-xs-3 text-center">
												 			{{ componente.labelAnno2 }}
												 		</div>
												 	</div>
												 	<div class="row">
												 		<div class="col-sm-3 col-xs-3">
												 			<input type="text"  
												 				   ng-readonly = "( vm.tabella1.dialogAction=='V' || !(componente.versione <= 0) )"  
												 				   fa-numeric="numeroInteroType"   
												 				   fa-model = "vm.tabella1.newItem.numeroPostiPrimoAnnoTriennio"    
												 				   class="form-control" 
												 				   placeholder="inserisci" > </input>
												 		</div>
												 		<div class="col-sm-3 col-xs-3">
												 			<input type="text"  
												 				   ng-readonly = "( vm.tabella1.dialogAction=='V' || !(componente.versione <= 1) )"  
												 				   fa-numeric="numeroInteroType"   
												 				   fa-model = "vm.tabella1.newItem.numeroPostiSecondoAnnoTriennio"    
												 				   class="form-control" 
												 				   placeholder="inserisci" > </input>
												 		</div>
												 		<div class="col-sm-3 col-xs-3">
												 			<input type="text"  
												 				   ng-readonly = "( vm.tabella1.dialogAction=='V' || !(componente.versione <= 2) )"  
												 				   fa-numeric="numeroInteroType"   
												 				   fa-model = "vm.tabella1.newItem.numeroPostiTerzoAnnoTriennio"    
												 				   class="form-control" 
												 				   placeholder="inserisci" > </input>
												 		</div>
												 	</div>
												   
												 </div>			   
											</div>		
											 
											 <div class="form-group row" >  
												<label for="item1_motivazione" class= "col-sm-3 col-xs-3 control-label"> Motivazione </label>
												 <div class="col-sm-9 col-xs-9" > 
												   <textarea   msd-elastic=""  rows="5" ng-readonly = "vm.tabella1.dialogAction=='V'"   id="item1_motivazione" ng-model = "vm.tabella1.newItem.motivazione"    class="form-control" placeholder="inserisci"></textarea>
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
 
