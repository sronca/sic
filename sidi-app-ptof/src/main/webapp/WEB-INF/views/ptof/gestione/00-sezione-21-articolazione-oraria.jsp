<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-21-ArticolazioneOrariaCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
		    
		    <jsp:include page="00-sezione-00-dati-plesso.jsp"></jsp:include>  
		    
			<jsp:include page="00-sezione-00-txt-allegato.jsp"></jsp:include>
		   
<div class="block-info"  ng-if = "'S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO'==componente.tipoComponente"  class="block-info-noborder" > 	
			<!-- TABELLA 1 -->		   
					   <div bs-tablesmall="" data-opts = "vm.tabella1" data-addtargetdialog = "'addDalogTab1'" ></div>
			<!-- TABELLA 1 END-->
         <form role="form" class = "form-horizontal">
					 	<div class="form-group row" >  
									<label class= "col-sm-1 col-xs-1 control-label"> Nota	 </label>
									<div class="col-sm-12 col-xs-12" > 
								          <textarea rows="10"    ng-model = "componente.nota"    class="form-control" ></textarea>
									 </div>			   
				 	   </div>			   
		 </form>
				
 </div> <!-- S_ARTICOLAZIONE_ORARIA_INDIRIZZI_STUDIO -->

<div class="block-info"  ng-if = "'S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO'==componente.tipoComponente"  class="block-info-noborder" > 	
 <!-- TABELLA 2 -->		   
		   <div bs-tablesmall="" data-opts = "vm.tabella2" data-addtargetdialog = "'addDalogTab2'" ></div>
 					   <div id = "addDalogTab2" class="modal fade">
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
													<label class= "col-sm-3 col-xs-3 control-label"> Anno Scolastico  	 </label>
													 <div class="col-sm-9 col-xs-9" > 
													    <input type="text"   readonly="readonly"  ng-model = "vm.tabella2.newItem.annoScolastico"    class="form-control" ></input>
													 </div>			   
												 </div>			   
			  	  								 
											</form>                      

						                   <form role="form" class = "form-horizontal">
												<div class="form-group row" >  
													<label class= "col-sm-3 col-xs-3 control-label"> Indirizzo di Studio  </label>
													 <div class="col-sm-9 col-xs-9" > 
													    <input type="text"     readonly="readonly"  ng-model = "vm.tabella2.newItem.descrizioneIndirizzoScolastico"    class="form-control" ></input>
													 </div>			   
												 </div>			   
			  	  								 
											</form>                      

						                   <form role="form" class = "form-horizontal">
												<div class="form-group row" >  
													<label class= "col-sm-3 col-xs-3 control-label"> Descrizione   Piano di Studio            	 </label>
													 <div class="col-sm-9 col-xs-9" > 
													    <textarea rows="5"    readonly="readonly"  ng-model = "vm.tabella2.newItem.descrizionePianoStudio"    class="form-control" ></textarea>
													 </div>			   
												 </div>			   
			  	  								 
											</form>                      
					                         
										 <!--  <h3> SENZA TITOLO </h3> -->
							            	<table class="table table-bordered">
							            	<thead>
												<tr>
												  <th rowspan="2" class="col-md-4">Discipline</th>
												  <th  colspan="6" class="col-md-1"> ORE SETTIMANALI</th>
												  <th rowspan="2" class="col-md-1" ng-if = "vm.colPresenzaClassi">Presenza di Classi (S/N)</th> 
												</tr>
												<tr>
												 
												  <th class="col-md-1">I</th>
												  <th class="col-md-1">II</th> 
												  <th class="col-md-1">III</th>   
												  <th class="col-md-1">IV</th> 
												  <th class="col-md-1">V</th>
												   <th class="col-md-1">VI</th>
												  
												</tr>
											</thead>
											<tbody>
												<tr  ng-repeat = "x in vm.tabella2.newItem.items" ng-class="$index%2 == 0 ? 'odd-light' :''"  >
												  <td  > {{ x.descrizioneMateriaScuola }} </td>
												  <td  > {{ x.numeroAlunniPrimo }} </td>
												  <td  > {{ x.numeroAlunniSecondo }}  </td>
												  <td  > {{ x.numeroAlunniTerzo }}  </td>
												  <td  > {{ x.numeroAlunniQuarto }}  </td>
												  <td  > {{ x.numeroAlunniQuinto }}  </td>
												  <td  > {{ x.numeroAlunniSesto }}  </td>
												  <td ng-if = "vm.colPresenzaClassi" > {{ x.presenzaClassi }}  </td>
												  
												</tr>
											 
											</tbody>
							            	</table>             
	 				                                 
											</div>
											
											<div class="modal-footer">
											    <label   for="inform" data-dismiss="modal" class="">  CHIUDI</label>
											</div>
										
										</div>
									</div>
						</div>
<!-- TABELLA 2 END--> 
         <form role="form" class = "form-horizontal">
					 	<div class="form-group row" >  
                                    <label class= "col-sm-1 col-xs-1 control-label"> Nota	 </label>
									<div class="col-sm-12 col-xs-12" >
								          <textarea rows="10"    ng-model = "componente.nota"    class="form-control" ></textarea>
									 </div>			   
				 	   </div>			   
		 </form>
</div>  <!-- S_ARTICOLAZIONE_ORARIA_QUADRO_ORARIO -->

<div class="block-info"  ng-if = "'S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA'==componente.tipoComponente"  class="block-info-noborder" > 	

 <!-- TABELLA 3 -->		   
		   <div bs-tablesmall="" data-opts = "vm.tabella3" data-addtargetdialog = "'addDalogTab3'" ></div>
		   <div id = "addDalogTab3" class="modal fade">
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
													<label class= "col-sm-3 col-xs-3 control-label"> Ordine Scuola  	 </label>
													 <div class="col-sm-9 col-xs-9" > 
													    <input type="text"   readonly="readonly"  ng-model = "vm.tabella3.newItem.ordineScuola"    class="form-control" ></input>
													 </div>			   
												 </div>			   
			  	  								 
											</form>                      

						                   					                         
										 <!--  <h3> SENZA TITOLO </h3> -->
							            	<table class="table table-bordered">
							            	<thead>
												<tr>
												  <th class="col-md-6">Descrizione tempo scuola</th>
												  <th class="col-md-3"> Anno inizio validit&agrave;  </th>
												  <th class="col-md-3"> Anno fine validit&agrave; </th> 
												</tr>
											</thead>
											<tbody>
												<tr  ng-repeat = "x in vm.tabella3.newItem.items" ng-class="$index%2 == 0 ? 'odd-light' :''"  >
												  <td  > {{ x.descrizioneTipoTempoScuola }} </td>
												  <td  > {{ x.dataInizioValidita }} </td>
												  <td  > {{ x.dataFineValidita }}  </td>
												</tr>
											 
											</tbody>
							            	</table>             
	 				                                 
									</div> <!-- modal-body -->
		                                   
								<div class="modal-footer">
								    <label   ng-if = "vm.tabella3.dialogAction!='V'" ng-click = "vm.tabella3.clickBtOK()"  for="inform"   class="">SALVA</label>
								    <label   for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								</div>
						
						</div> <!--  modal-content -->
								
				 	</div> <!--  modal-dialog -->
			 </div> <!-- addDalogTab3 -->
			
<!-- TABELLA 3 END-->
         <form role="form" class = "form-horizontal">
					 	<div class="form-group row" >  
                                   <label class= "col-sm-1 col-xs-1 control-label"> Nota	 </label>
									<div class="col-sm-12 col-xs-12" >
								          <textarea rows="10"    ng-model = "componente.nota"    class="form-control" ></textarea>
									 </div>			   
				 	   </div>			   
		 </form> 
 </div> <!-- S_ARTICOLAZIONE_ORARIA_TEMPI_SCUOLA -->
			     
    </div>           
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
