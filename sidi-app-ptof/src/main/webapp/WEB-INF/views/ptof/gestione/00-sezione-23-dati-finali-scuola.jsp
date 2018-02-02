<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>
	<div class="content" ng-controller="GestioneSezione-23-GestioneDatiFinaliScuolaCtrl as gestioneSezioneCtrl">
		<fieldset>
		<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
 		  <div class="block-info"  ng-if = "'S_DATI_FINALI_SCUOLA'==componente.tipoComponente"  class="block-info-noborder" > 
			  <form role="form" class = "form-horizontal">
					<div class="form-group row" >  
						<label class= "col-sm-6 col-xs-6 control-label"> Data ratifica atto di indirizzo Dirigente Scolastico </label>    
						 <div class="col-sm-3 col-xs-3" >  
						    <input ng-model = "componente.dataRatificaAttoIndirizzoDirigente"    
						    	   class="form-control" 
						    	   placeholder="inserisci" 
						    	   maxlength="10" 
						    	   type="text" 
						    	   data-date-time-picker =""  
						    	   id="_dataRatificaAttoIndirizzoDirigente" />
						 </div>			   
					 </div>			   
					<div class="form-group row" >  
						<label class= "col-sm-6 col-xs-6 control-label"> Data predisposizione PTOF Collegio dei Docenti </label>    
						 <div class="col-sm-3 col-xs-3" >  
						    <input ng-model = "componente.dataPredisposizionePTOFCollegioDocenti"    
						    	   class="form-control" 
						    	   placeholder="inserisci" 
						    	   maxlength="10" 
						    	   type="text" 
						    	   data-date-time-picker =""  
						    	   id="_dataPredisposizionePTOFCollegioDocenti" />
						 </div>			   
					 </div>
					 <div class="form-group row" >  
						<label class= "col-sm-6 col-xs-6 control-label"> Data approvazione Consiglio d'Istituto </label>    
						 <div class="col-sm-3 col-xs-3" >  
						    <input ng-model = "componente.dataApprovazioneConsiglioDocenti"    
						    	   class="form-control" 
						    	   placeholder="inserisci" 
						    	   maxlength="10" 
						    	   type="text" 
						    	   data-date-time-picker =""  
						    	   id="_dataApprovazioneConsiglioDocenti" />
						 </div>			   
					 </div>
					 <div class="form-group row" >  
						<label class= "col-sm-6 col-xs-6 control-label"> Data invio USR </label>    
						 <div class="col-sm-3 col-xs-3" >  
						    <input ng-model="componente.dataInvioUSR"    
						    	   class="form-control" 
						    	   placeholder="" 
						    	   maxlength="10" 
						    	   type="text"
						    	   ng-readonly="true" 
						    	   id="_dataInvioUSR" />
						 </div>			   
					 </div>
			   </form>	
		   </div>	
		</div>			               
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
