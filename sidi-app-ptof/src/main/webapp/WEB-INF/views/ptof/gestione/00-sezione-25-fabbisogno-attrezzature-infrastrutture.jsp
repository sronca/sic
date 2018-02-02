<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-25-FabbisognoAttrezzatureCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti" ng-init="idDialogX = 'iddiaolog' + ( $index + 1 ) " >
		    
		   
		   <div class="block-info"  ng-if = "'S_FABBISOGNO_ATTREZZATURE_INFRA'==componente.tipoComponente"  class="block-info-noborder" > 	
	  
<!-- TABELLA 1 -->		   
		   <div  bs-tablesmall="" data-opts = "gestioneSezioneCtrl.getTabella( componente.key ).tab" data-addtargetdialog = "idDialogX" ></div>
		   
		   <div id = "{{idDialogX}}" class="modal fade" ng-init="tabX = gestioneSezioneCtrl.getTabella( componente.key )  ">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title"> {{ componente.nome }}   </h3>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label"> Tipologia   </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea rows="5" id="item_tipologia_{{ tabX.tab.index }}"  msd-elastic=""   ng-readonly = "tabX.tab.dialogAction=='V'"    ng-model = "tabX.tab.newItem.tipologia"    class="form-control" placeholder="inserisci" ></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Descrizione    </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea rows="5" id="item_descrizione_{{ tabX.tab.index }}"  msd-elastic=""  ng-readonly = "tabX.tab.dialogAction=='V'"    ng-model = "tabX.tab.newItem.descrizione"    class="form-control" placeholder="inserisci" ></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Numeri pezzi    </label>
										 <div class="col-sm-3 col-xs-3" > 
										     <input fa-numeric="numeroInteroType" id="item_numero_pezzi_{{ tabX.tab.index }}"  ng-readonly = "tabX.tab.dialogAction=='V'"   maxlength="10" type="text"    fa-model = "tabX.tab.newItem.numeroPezzi"    class="form-control" placeholder="inserisci (###.###)"></input>
										 </div>			   
									 </div>			   

							        <div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Area tematica PNSD    </label>
										 <div class="col-sm-9 col-xs-9" > 
										     <textarea rows="5" id="item_area_tematica_{{ tabX.tab.index }}"  msd-elastic=""  ng-readonly = "tabX.tab.dialogAction=='V'"    ng-model = "tabX.tab.newItem.areaTematicaPNSD"    class="form-control" placeholder="inserisci" ></textarea>
										 </div>			   
									 </div>			   

							        <div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Stima costi attesi    </label>
										 <div class="col-sm-3 col-xs-3" > 
										    <input fa-numeric="amount2DecimalType" id="item_stima_costi_{{ tabX.tab.index }}"  ng-readonly = "tabX.tab.dialogAction=='V'"   maxlength="10" type="text"    fa-model = "tabX.tab.newItem.stimaCosti"    class="form-control" placeholder="inserisci (#.###.###,##)"></input>   
										 </div>			   
									 </div>			   

							        <div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Fonte finanziamento    </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea rows="5" id="item_fonte_finanziamento_{{ tabX.tab.index }}"  msd-elastic=""  ng-readonly = "tabX.tab.dialogAction=='V'"    ng-model = "tabX.tab.newItem.fonteFinanziamento"    class="form-control" placeholder="inserisci" ></textarea>  
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
<!-- TABELLA 1 END-->	
		</div>
	
	   <div class="block-info"  ng-if = "'S_FABBISOGNO_ATTREZZATURE_INFRA_ALTRE_INFO'==componente.tipoComponente"  class="block-info-noborder" > 	
		        <form role="form" class = "form-horizontal">
					 	<div class="form-group row" >  
                                  <label class= "col-sm-3 col-xs-3 control-label"> Motivazione	 </label>
								  <div class="col-sm-7 col-xs-7" >
								          <textarea rows="5"  msd-elastic="" ng-model = "componente.motivazione"    class="form-control" ></textarea>
								   </div>			   
				 	   </div>			   

					 	<div class="form-group row" >  
                                  <label class= "col-sm-3 col-xs-3 control-label"> Finalit&agrave;	 </label>
								  <div class="col-sm-7 col-xs-7" >
								          <textarea rows="5"   msd-elastic=""  ng-model = "componente.finalita"    class="form-control" ></textarea>
								   </div>			   
				 	   </div>		


					 	<div class="form-group row" >  
                                  <label class= "col-sm-3 col-xs-3 control-label"> Benefici Attesi  </label>
								  <div class="col-sm-7 col-xs-7" >
								          <textarea rows="5"   msd-elastic=""  ng-model = "componente.beneficiAttesi"    class="form-control" ></textarea>
								   </div>			   
				 	   </div>		
				 	   
				 	   	   

		         </form>
		 </div>
		 
	</div> <!-- componente --> 	
		</fieldset>
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>     
    
    </div>           
		
		
</div>

 
 
