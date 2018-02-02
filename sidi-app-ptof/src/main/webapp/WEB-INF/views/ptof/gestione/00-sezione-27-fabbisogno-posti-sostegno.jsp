<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-27-FabbisognoSostegnoCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti" ng-init="idDialogX = 'iddiaolog' + ( $index + 1 ) " >
		    
<!-- componente S_FABBISOGNO_POSTI_SOSTEGNO_AA_EE_MMI init --> 			   
<div class="block-info"  ng-if = "'S_FABBISOGNO_POSTI_SOSTEGNO_AA_EE_MMI'==componente.tipoComponente"  class="block-info-noborder" > 	
	  
<!-- TABELLA 1 -->		   
		   <div  bs-tablesmall="" data-opts = "gestioneSezioneCtrl.getTabella( componente.key ).tab" data-addtargetdialog = "idDialogX" ></div>
		   
		   <div id = "{{idDialogX}}" class="modal fade" ng-init="tabX = gestioneSezioneCtrl.getTabella( componente.key )  ">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title"> {{ componente.nome }} </h3>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Posti di Sostegno Udito    </label>
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
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 0) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = "tabX.tab.newItem.postiSostegnoUditoPrimoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 		<div class="col-sm-3 col-xs-3">
										 			<input type="text"  
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 1) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = "tabX.tab.newItem.postiSostegnoUditoSecondoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 		<div class="col-sm-3 col-xs-3">
										 			<input type="text"  
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 2) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = "tabX.tab.newItem.postiSostegnoUditoTerzoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 	</div>										   
										 </div>		
									</div>			   


									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Posti di Sostegno Vista    </label>
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
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 0) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = "tabX.tab.newItem.postiSostegnoVistaPrimoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 		<div class="col-sm-3 col-xs-3">
										 			<input type="text"  
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 1) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = "tabX.tab.newItem.postiSostegnoVistaSecondoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 		<div class="col-sm-3 col-xs-3">
										 			<input type="text"  
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 2) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = " tabX.tab.newItem.postiSostegnoVistaTerzoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 	</div>										   
										 </div>		
									</div>	
									
									
									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Posti di Sostegno Psicofisico    </label>
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
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 0) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = "tabX.tab.newItem.postiSostegnoPsicofisicoPrimoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 		<div class="col-sm-3 col-xs-3">
										 			<input type="text"  
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 1) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = "tabX.tab.newItem.postiSostegnoPsicofisicoSecondoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 		<div class="col-sm-3 col-xs-3">
										 			<input type="text"  
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 2) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = " tabX.tab.newItem.postiSostegnoPsicofisicoTerzoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 	</div>										   
										 </div>			   
									</div>	

									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Motivazione    </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea rows="5"    msd-elastic=""  ng-readonly = "tabX.tab.dialogAction=='V'"    ng-model = "tabX.tab.newItem.motivazione"    class="form-control" placeholder="inserisci" ></textarea>
										 </div>			   
									</div> 
 
								</form>                      
		                                     
		                                 
								</div>
								
								<div class="modal-footer">
								   <label  ng-if = "tabX.tab.dialogAction!='V'"  ng-click = "tabX.tab.clickBtOK()"  for="inform"   class="">SALVA</label>
								    <label    for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								   
								</div>
							
							</div>
						</div>
			</div> <!-- TABELLA 1 END-->

</div>  
<!-- componente S_FABBISOGNO_POSTI_CATTEDRE_AA_EE end --> 	



<!-- init componente S_FABBISOGNO_POSTI_SOSTEGNO_MMII --> 	
<div class="block-info"  ng-if = "'S_FABBISOGNO_POSTI_SOSTEGNO_MMII'==componente.tipoComponente"  class="block-info-noborder" > 	
	  
<!-- TABELLA 2 -->		   
		   <div  bs-tablesmall="" data-opts = "gestioneSezioneCtrl.getTabella( componente.key ).tab" data-addtargetdialog = "idDialogX" ></div>
		   
		   <div id = "{{idDialogX}}" class="modal fade" ng-init="tabX = gestioneSezioneCtrl.getTabella( componente.key )  ">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title"> {{ componente.nome }} </h3>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Posti Comuni</label>
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
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 0) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = "tabX.tab.newItem.numeroPostiPrimoAnnoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 		<div class="col-sm-3 col-xs-3">
										 			<input type="text"  
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 1) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = "tabX.tab.newItem.numeroPostiSecondoAnnoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 		<div class="col-sm-3 col-xs-3">
										 			<input type="text"  
										 				   ng-readonly = "( tabX.tab.dialogAction=='V' || !(componente.versione <= 2) )"  
										 				   fa-numeric="numeroInteroType"   
										 				   fa-model = " tabX.tab.newItem.numeroPostiTerzoAnnoTriennio"    
										 				   class="form-control" 
										 				   placeholder="inserisci" > </input>
										 		</div>
										 	</div>
										   
										 </div>			   
									</div>	 
	
									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Motivazione    </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea rows="5"    msd-elastic=""  ng-readonly = "tabX.tab.dialogAction=='V'"    ng-model = "tabX.tab.newItem.motivazione"    class="form-control" placeholder="inserisci" ></textarea>
										 </div>			   
									</div> 
 
								</form>                      
		                                     
		                                 
								</div>
								
								<div class="modal-footer">
								   <label  ng-if = "tabX.tab.dialogAction!='V'"  ng-click = "tabX.tab.clickBtOK()"  for="inform"   class="">SALVA</label>
								    <label    for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								   
								</div>
							
							</div>
						</div>
			</div> 
<!-- TABELLA 2 END-->

		</div>  <!-- componente end --> 	

</div> 	 <!-- each componente --> 	


		 

		</fieldset>
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>     
    
    </div>           
		
		
</div>

 
 
