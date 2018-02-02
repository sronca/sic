<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-12-GestioneProgettiExstraCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
		    
		    <jsp:include page="00-sezione-00-dati-plesso.jsp"></jsp:include>  
		    
			<jsp:include page="00-sezione-00-txt-allegato.jsp"></jsp:include>
		    	   
		   <div class="block-info"  ng-if = "'S_ALTRI_PROGETTI_CURRICULARI_EXSTRA'==componente.tipoComponente"  class="block-info-noborder" > 	
		   
		   
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
										<label class= "col-sm-3 col-xs-3 control-label"> Ambito	 </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <bsselectpicker data-bid="item1_ambito" data-show = "vm.tabella1.dialogAction=='I'"  data-items="vm.form.ambitiL" data-selected="vm.tabella1.newItem.ambito"></bsselectpicker>
										    <textarea msd-elastic=""  rows="5"   readonly="readonly"  ng-model = "vm.tabella1.newItem.ambito.label"    class="form-control" ></textarea>
										 </div>			   
									 </div>			   
 
									<div  class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Tipologia Progetto	 </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea msd-elastic=""  rows="5"  ng-readonly = "vm.tabella1.dialogAction=='V'"    id="item1_tipologiaProgetto"   ng-model = "vm.tabella1.newItem.tipologiaProgetto.label"  placeholder="inserisci"  class="form-control" ></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_denominazione" class= "col-sm-3 col-xs-3 control-label"> Denominazione </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic=""  rows="5"  ng-readonly = "vm.tabella1.dialogAction=='V'"   id="item1_denominazione" ng-model = "vm.tabella1.newItem.denominazione"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Destinatari </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea msd-elastic=""  rows="5"  ng-readonly = "vm.tabella1.dialogAction=='V'"  id="item1_destinatari" ng-model = "vm.tabella1.newItem.destinatari"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Obiettivi/Altre Priorit&agrave; </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_obiettiviAltrePriorita" ng-model = "vm.tabella1.newItem.obiettiviAltrePriorita"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Principali Contenuti </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_principaliContenuti" ng-model = "vm.tabella1.newItem.principaliContenuti"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Modalit&agrave;/Approcci formativi utilizzati </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50"  id="_modalitApprocciFormativiUtilizzati" ng-model = "vm.tabella1.newItem.modalitApprocciFormativiUtilizzati"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Periodo di svolgimento </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic=""  rows="5" ng-readonly = "vm.tabella1.dialogAction=='V'"    id="_periodoDiSvolgimento" ng-model = "vm.tabella1.newItem.periodoDiSvolgimento"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Durata dal   </label>
										 <div class="col-sm-9 col-xs-9" > 
										   
										   <input maxlength="10" type="text" id = "date1_durataDal"  ng-readonly = "vm.tabella1.dialogAction=='V'"
										    data-date-time-picker =""
										    ng-model = "vm.tabella1.newItem.durataDal"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>		 

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Durata al </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <input type="text" id = "date1_durataAl"  ng-readonly = "vm.tabella1.dialogAction=='V'"
										   ng-model = "vm.tabella1.newItem.durataAl"    
										   class="form-control" placeholder="inserisci"
										   data-date-time-picker =""
										   ></input>
										 </div>			   
									 </div>	
									 
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Area tematica PNSD </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  msd-elastic=""  rows="5" ng-readonly = "vm.tabella1.dialogAction=='V'"   id="_areaTematicaPNSD" ng-model = "vm.tabella1.newItem.areaTematicaPNSD"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Rilevanza per la scuola </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_rilevanzaPerScuola"  ng-model = "vm.tabella1.newItem.rilevanzaPerScuola"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	


									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Cooperazione con altre scuole </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50"  id="_cooperazioneConAltreScuole" ng-model = "vm.tabella1.newItem.cooperazioneConAltreScuole"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Risorse Finanziare necessarie </label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input  ng-readonly = "vm.tabella1.dialogAction=='V'" fa-numeric="amount8DecimalType" id="date1_RisorseFinanziareNecessarie"   maxlength="10" type="text"    fa-model = "vm.tabella1.newItem.risorseFinanziareNecessarie"    class="form-control" placeholder="inserisci"></input>
										 </div>			   
									 </div>	

									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Risorse umane/area </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_risorseUmaneArea"  ng-model = "vm.tabella1.newItem.risorseUmaneArea"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	
									 
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Altre Risorse necessarie </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_altreRisorseNecessarie"  ng-model = "vm.tabella1.newItem.altreRisorseNecessarie"    class="form-control" placeholder="inserisci"></textarea>
										 </div>			   
									 </div>	
								
									<div class="form-group row" >  
										<label class= "col-sm-3 col-xs-3 control-label"> Stato Avanzamento </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea  ng-readonly = "vm.tabella1.dialogAction=='V'" rows="10" cols="50" id="_statoAvanzamento"  ng-model = "vm.tabella1.newItem.statoAvanzamento"    class="form-control" placeholder="inserisci"></textarea>
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
 
