<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-16-GestioneOrganizzazioneClassiCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
	  	    
		    <div   class="block-info block-dark"  ng-if = "'TEXTEDITOR'==componente.tipoComponente"  class="block-info-noborder" > 	
			 		 <textarea   rows="10" cols="50"  ui-tinymce="tinymceOptions" ng-model = "componente.valore"   rows="10" cols="50" aria-label="Descrizione" tabindex="10">  </textarea>
            </div> 
	

<div class="block-info"  ng-if = "'S_ORGANIZZAZIONE_CLASSI_MMII'==componente.tipoComponente"  class="block-info-noborder" > 	
		   
<!-- TABELLA MMII -->		 
		   <div bs-tablesmall="" data-opts = "vm.tabella4" data-addtargetdialog = "'addDalogTabMMII'" ></div>
		   <div id = "addDalogTabMMII" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title">{{ componente.nome }}</h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
				                   <form role="form" class = "form-horizontal">
										
										<div  class="form-group row" >  
											<label class= "col-sm-3 col-xs-3 control-label">Indirizzo  </label>
											 <div class="col-sm-7 col-xs-7" > 
											   <textarea rows="5" readonly="readonly"    ng-model = "vm.tabella4.newItem.indirizzo "  placeholder="inserisci"  class="form-control" ></textarea>
											 </div>			   
										 </div>	
	 							  
	                                    <div class="form-group row" >  
											<label class= "col-sm-3 col-xs-3 control-label"> Classi I   </label>
											 <div class="col-sm-3 col-xs-3" > 
											   <input ng-readonly = "vm.tabella4.dialogAction=='V'"  fa-numeric="numeroInteroType" id="_classiI"   maxlength="10" type="text"    fa-model = "vm.tabella4.newItem.classiI"    class="form-control" placeholder="inserisci"></input>
											 </div>			   
										 </div>	
	
	                                    <div class="form-group row" >  
											<label class= "col-sm-3 col-xs-3 control-label"> Classi II   </label>
											 <div class="col-sm-3 col-xs-3" > 
											   <input ng-readonly = "vm.tabella4.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiII"   maxlength="10" type="text"    fa-model = "vm.tabella4.newItem.classiII"    class="form-control" placeholder="inserisci"></input>
											 </div>			   
										 </div>
										 
	                                    <div class="form-group row" >  
											<label class= "col-sm-3 col-xs-3 control-label"> Classi III   </label>
											 <div class="col-sm-3 col-xs-3" > 
											   <input ng-readonly = "vm.tabella4.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiIII"   maxlength="10" type="text"    fa-model = "vm.tabella4.newItem.classiIII"    class="form-control" placeholder="inserisci"></input>
											 </div>			   
										 </div>
	
	                                    <div class="form-group row" >  
											<label class= "col-sm-3 col-xs-3 control-label"> Classi IV      </label>
											 <div class="col-sm-3 col-xs-3" > 
											   <input ng-readonly = "vm.tabella4.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiIV"   maxlength="10" type="text"    fa-model = "vm.tabella4.newItem.classiIV"    class="form-control" placeholder="inserisci"></input>
											 </div>			   
										 </div>
										 
	                                    <div class="form-group row" >  
											<label class= "col-sm-3 col-xs-3 control-label">Classi V      </label>
											 <div class="col-sm-3 col-xs-3" > 
											   <input ng-readonly = "vm.tabella4.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiV"   maxlength="10" type="text"    fa-model = "vm.tabella4.newItem.classiV"    class="form-control" placeholder="inserisci"></input>
											 </div>			   
										 </div>
	
	
	                                    <div class="form-group row" >  
											<label class= "col-sm-3 col-xs-3 control-label">Classi VI      </label>
											 <div class="col-sm-3 col-xs-3" > 
											   <input ng-readonly = "vm.tabella4.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiVI"   maxlength="10" type="text"    fa-model = "vm.tabella4.newItem.classiVI"    class="form-control" placeholder="inserisci"></input>
											 </div>			   
										 </div>


 
	
									</form>                      
	
<!--  											<div class="row" >  
 							 		 			 <div class="col-sm-3 col-xs-3" > </div>							  
 												 <div class="col-sm-6 col-xs-6" > 
          				                           <table class = "table table-bordered table-striped" > 
	                                				       <thead>
	                                				     <tr><th> Dati Lingua </th></tr>
	                                				     </thead>  
	                                				     <tbody>
												 			 <tr  ng-repeat="td in vm.tabella4.newItem.datiLingua"  > 
															   <td   >  {{ td }}  </td> 
															 </tr>
													      </tbody>		  
												    </table> 
												  </div>  
 											</div> -->
										 					    			
	      


								</div>
								
								<div class="modal-footer">
								   <label   ng-if = "vm.tabella4.dialogAction!='V'" ng-click = "vm.tabella4.clickBtOK()"  for="inform"   class="">SALVA</label>
 								   <label  for="inform" data-dismiss="modal" class="" >CHIUDI</label>
								   
								</div>
							</div>
						</div>  <!-- modal dialog-->
			</div> <!-- modal -->
<!-- TABELLA MMII END-->	
</div>


		    	   
<div class="block-info"  ng-if = "'S_ORGANIZZAZIONE_CLASSI_AA'==componente.tipoComponente"  class="block-info-noborder" > 	
		   
<!-- TABELLA AA -->		 
		   <div bs-tablesmall="" data-opts = "vm.tabella1" data-addtargetdialog = "'addDalogTabAA'" ></div>
		   <div id = "addDalogTabAA" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title">{{ componente.nome }}</h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
 	 
<!-- 									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Tipologia Posti    </label>
										 <div class="col-sm-7 col-xs-7" > 
										   <textarea rows="5" readonly="readonly"    ng-model = "vm.tabella1.newItem.tipologiaPosti"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label">Descrizione   </label>
										 <div class="col-sm-7 col-xs-7" > 
										   <textarea rows="10" readonly="readonly"    ng-model = "vm.tabella1.newItem.descrizione"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>	 -->		   

                                    <div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Sezione Orario Normale</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input  ng-readonly = "vm.tabella1.dialogAction=='V'"  fa-numeric="numeroInteroType" id="_sezioneOrarioNormale"   maxlength="10" type="text"    fa-model = "vm.tabella1.newItem.sezioneOrarioNormale"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>	
                                    <div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Sezione Orario Ridotto</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input  ng-readonly = "vm.tabella1.dialogAction=='V'"  fa-numeric="numeroInteroType" id="_sezioneOrarioRidotto"   maxlength="10" type="text"    fa-model = "vm.tabella1.newItem.sezioneOrarioRidotto"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>										   	  
								</form>                      
								</div>
								
								<div class="modal-footer">
								   <label   ng-if = "vm.tabella1.dialogAction!='V'" ng-click = "vm.tabella1.clickBtOK()"  for="inform"   class="">SALVA</label>
 								   <label  for="inform" data-dismiss="modal" class=""> CHIUDI</label>
								   
								</div>
							</div>
						</div>
			</div>
<!-- TABELLA AA END-->	
</div> 




<div class="block-info"  ng-if = "'S_ORGANIZZAZIONE_CLASSI_MMI'==componente.tipoComponente"  class="block-info-noborder" > 	
		   
<!-- TABELLA MMI -->		 
		   <div bs-tablesmall="" data-opts = "vm.tabella3" data-addtargetdialog = "'addDalogTab3'" ></div>
		   <div id = "addDalogTab3" class="modal fade" >
						<div class="modal-dialog modal-small" >
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title">{{ componente.nome }}</h3>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
 				                <form role="form" class = "form-horizontal">
 									 <div class="accordion-ptof-list"> 
														<h5>  
															<a href="javascript:void(0)"  >
															    Tempo Normale  
															</a>
														</h5>
														<div   >
											              <p> </p>
												 	                                      <div  class="form-group row" >  
																								<label class= "col-sm-5 col-xs-5 control-label">Totali Classi I   </label>
																								 <div class="col-sm-4 col-xs-4" > 
																								   <input  ng-readonly = "vm.tabella3.dialogAction=='V'"  fa-numeric="numeroInteroType" id="_tempoNormaleTotaliClassiI"   maxlength="10" type="text"    fa-model = "vm.tabella3.newItem.tempoNormaleTotaliClassiI"    class="form-control" placeholder="inserisci"></input>
																								 </div>			   
																						   </div>			   
													                                      <div  class="form-group row" >  
																								<label class= "col-sm-5 col-xs-5 control-label">Totali Classi II   </label>
																								 <div class="col-sm-4 col-xs-4" > 
																								    <input  ng-readonly = "vm.tabella3.dialogAction=='V'" fa-numeric="numeroInteroType" id="_tempoNormaleTotaliClassiII"   maxlength="10" type="text"    fa-model = "vm.tabella3.newItem.tempoNormaleTotaliClassiII"    class="form-control" placeholder="inserisci"></input>
																								 </div>			   
																						   </div>			   
													                                      <div  class="form-group row" >  
																								<label class= "col-sm-5 col-xs-5 control-label">Totali Classi III   </label>
																								 <div class="col-sm-4 col-xs-4" > 
																								      <input  ng-readonly = "vm.tabella3.dialogAction=='V'" fa-numeric="numeroInteroType" id="_tempoNormaleTotaliClassiIII"   maxlength="10" type="text"    fa-model = "vm.tabella3.newItem.tempoNormaleTotaliClassiIII"    class="form-control" placeholder="inserisci"></input>
																								 </div>			   
																						   </div>					
									                     						 
														</div>
									                    
									                    <p> </p>			 
														<h5>  
															 
															<a href="javascript:void(0)"    >
															    Tempo Prolungato 
																 
															</a>
																
														</h5>
														<div >
											              <p> </p>
  																					<div class="form-group row">
																						<label class="col-sm-5 col-xs-5 control-label">Totali
																							Classi I </label>
																						<div class="col-sm-4 col-xs-4">
																							<input ng-readonly="vm.tabella3.dialogAction=='V'"
																								fa-numeric="numeroInteroType"
																								id="_tempoProlungatoTotaliClassiI" maxlength="10"
																								type="text"
																								fa-model="vm.tabella3.newItem.tempoProlungatoTotaliClassiI"
																								class="form-control" placeholder="inserisci"></input>
																						</div>
																					</div>
									
																					<div class="form-group row">
																						<label class="col-sm-5 col-xs-5 control-label">Totali
																							Classi II </label>
																						<div class="col-sm-4 col-xs-4">
																							<input ng-readonly="vm.tabella3.dialogAction=='V'"
																								fa-numeric="numeroInteroType"
																								id="_tempoProlungatoTotaliClassiII" maxlength="10"
																								type="text"
																								fa-model="vm.tabella3.newItem.tempoProlungatoTotaliClassiII"
																								class="form-control" placeholder="inserisci"></input>
																						</div>
																					</div>
									
																					<div class="form-group row">
																						<label class="col-sm-5 col-xs-5 control-label">Totali
																							Classi III </label>
																						<div class="col-sm-4 col-xs-4">
																							<input ng-readonly="vm.tabella3.dialogAction=='V'"
																								fa-numeric="numeroInteroType"
																								id="_tempoProlungatoTotaliClassiIII" maxlength="10"
																								type="text"
																								fa-model="vm.tabella3.newItem.tempoProlungatoTotaliClassiIII"
																								class="form-control" placeholder="inserisci"></input>
																						</div>
																					</div>		              
											              
									                    </div>
									                    
<!-- 									 	                <p> </p>
														<h5>  
															<a href="javascript:void(0)" >
															    Dati Lingua 
															</a>
														</h5>
														<div id="tot_3"     >
											              <p> </p>
											              <table class="table table-bordered table-striped">
									
																							<tbody>
																								<tr ng-repeat="td in vm.tabella3.newItem.datiLingua">
																									<td>{{ td }}</td>
																								</tr>
																							</tbody>
														  </table>
									                    </div> -->
									</div>
							</form>

								</div><!-- modal body -->
								
								<div class="modal-footer">
								   <label   ng-if = "vm.tabella3.dialogAction!='V'" ng-click = "vm.tabella3.clickBtOK()"  for="inform"   class="">SALVA</label>
 								   <label   for="inform" data-dismiss="modal" class="">CHIUDI</label>
								   
								</div>
							</div>
						</div>
			</div>
<!-- TABELLA MMI END-->	
</div> 




<div class="block-info"  ng-if = "'S_ORGANIZZAZIONE_CLASSI_EE'==componente.tipoComponente"  class="block-info-noborder" > 	
 <!-- TABELLA EE -->		 
		   <div bs-tablesmall="" data-opts = "vm.tabella2" data-addtargetdialog = "'addDalogTab2'" ></div>
		   <div id = "addDalogTab2" class="modal fade"  >
				<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title">{{ componente.nome }}</h1>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body"  >
		                                    
			                   <form role="form" class = "form-horizontal">
 	 
<!-- 									<div  class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label">Tipologia Posti    </label>
										 <div class="col-sm-7 col-xs-7" > 
										   <textarea  readonly="readonly" rows="5" readonly="readonly"    ng-model = "vm.tabella2.newItem.tipologiaPosti"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div  class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label">Descrizione   </label>
										 <div class="col-sm-7 col-xs-7" > 
										   <textarea readonly="readonly" rows="10" readonly="readonly"    ng-model = "vm.tabella2.newItem.descrizione"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>	 -->		   

                                    <div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo  Normale I</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoNormaleI"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoNormaleI"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>	
                                    <div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Normale II</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoNormaleII"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoNormaleII"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>	
								    <div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Normale III</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoNormaleIII"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoNormaleIII"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>	
									 
									  <div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Normale IV</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoNormaleIV"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoNormaleIV"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>
									 
									<div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Normale V</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoNormaleV"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoNormaleV"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>
									<div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Normale PLURICL</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoNormalePLURICL"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoNormalePLURICL"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>									 
									<div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Pieno I</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoPienoI"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoPienoI"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>		
									 
								    <div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Pieno II</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoPienoII"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoPienoII"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>	

								    <div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Pieno III </label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoPienoIII"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoPienoIII"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>

								    <div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Pieno IV</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoPienoIV"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoPienoIV"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>

								    <div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Pieno V</label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoPienoV"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoPienoV"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>

								    <div class="form-group row" >  
										<label class= "col-sm-4 col-xs-4 control-label"> Classi Tempo Pieno PLURICL </label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input ng-readonly = "vm.tabella2.dialogAction=='V'" fa-numeric="numeroInteroType" id="_classiAlunniTempoPienoPLURICL"   maxlength="10" type="text"    fa-model = "vm.tabella2.newItem.classiAlunniTempoPienoPLURICL"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>
									 									 									 									 								 									   	  
								</form>                      
								</div>
								
								<div class="modal-footer">
								   <label   ng-if = "vm.tabella2.dialogAction!='V'" ng-click = "vm.tabella2.clickBtOK()"  for="inform"   class="">SALVA</label>
								    <label  for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								   
								</div>
							</div>
		</div>
	</div>
<!-- TABELLA EE END-->	
</div> 
			     
    </div>           
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
