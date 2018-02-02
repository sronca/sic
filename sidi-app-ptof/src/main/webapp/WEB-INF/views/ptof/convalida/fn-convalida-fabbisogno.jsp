<%@ include file="../../layout/taglib.jsp"%>
<div class="tab-content" ng-controller="CruscottoConvalidaFabbisognoCtrl as cruscottoConvalidaFabbisogno">
    <div class="wrapper-content">
 		<p class="title">${titoloReport}</p>
 	</div>
 	<div class="tab-pane fade in active" id="target2">
		<fieldset>	
	 	<!-- FILTRI -->	
	 		<div id="filtro-ricerca">
	 			<bsinfocatalogodocumento data-bprogressivogestionecatalogodocumento="${progressivoGestioneCatalogoDocumento}"></bsinfocatalogodocumento>
			    <div class="block-info">
	               <div class="form-group row"  > 
				         <form role="form" class = "form-horizontal">  
				          <input type="hidden" ng-model="vm.form.progressivoGestioneCatalogoDocumento" ng-init="vm.form.progressivoGestioneCatalogoDocumento=${progressivoGestioneCatalogoDocumento}">   
				          <label class="col-xs-1 col-md-1 control-label" >Regione</label>
				           <div class="col-xs-8 col-md-8" > 
				           		<input type="text" ng-readonly="true" class="form-control" ng-model="vm.form.regione">
				          </div>
				         </form>  
				   </div>
			    </div>      
			    
			    <div class="block-info"> 
			    	 <h3>Fabbisogno scuola posti comuni</h3>
			         <table class="table table-bordered">
						<thead>
							<tr>
							  <th>Dell'Infanzia</th>
							  <th>Primaria</th>
							  <th>Secondaria I Grado</th>
							  <th>Secondaria II Grado</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
						<tfoot>
							<tr>
							  <th class="text-center">{{vm.form.cruscottoFabbisogniPostiComuniDTO.totaleFabbisognoInfanzia}}</th>
							  <th class="text-center">{{vm.form.cruscottoFabbisogniPostiComuniDTO.totaleFabbisognoPrimaria}}</th>
							  <th class="text-center">{{vm.form.cruscottoFabbisogniPostiComuniDTO.totaleFabbisognoIGrado}}</th>
							  <th class="text-center">{{vm.form.cruscottoFabbisogniPostiComuniDTO.totaleFabbisognoIIGrado}}</th>
							</tr>
						</tfoot>
					</table>
			    </div> 
			    
			    <div class="block-info"> 
			    	 <h3>Fabbisogno dei posti di sostegno</h3>
			         <table class="table table-bordered">
						<thead>
							<tr>
							  <th>Sostegno</th>
							  <th>Potenziamento per il sostegno</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
						<tfoot>
							<tr>
							  <th class="text-center">{{vm.form.cruscottoFabbisogniPostiSostegnoDTO.totaleFabbisognoSostegno}}</th>
							  <th class="text-center">{{vm.form.cruscottoFabbisogniPostiSostegnoDTO.totaleFabbisognoPotenziamentoPerSostegno}}</th>
							</tr>
						</tfoot>
					</table>
			    </div> 
			    
			    <div class="block-info"> 
			    	<h3>Fabbisogno dei posti di potenziamento</h3>
			         <table class="table table-bordered">
						<thead>
							<tr>
							   <th>Primaria</th>
							  <th>Secondaria I Grado</th>
							  <th>Secondaria II Grado</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
						<tfoot>
							<tr>
							  <th class="text-center">{{vm.form.cruscottoFabbisogniPostiDiPotenziamentoDTO.totaleFabbisognoPrimaria}}</th>
							  <th class="text-center">{{vm.form.cruscottoFabbisogniPostiDiPotenziamentoDTO.totaleFabbisognoIGrado}}</th>
							  <th class="text-center">{{vm.form.cruscottoFabbisogniPostiDiPotenziamentoDTO.totaleFabbisognoIIGrado}}</th>
							</tr>
						</tfoot>
					</table>
			    </div> 
			    
			    <div class="row" >
					 <div class="col-xs-12 col-md-12  text-center">
						 <input  ng-click= "cruscottoConvalidaFabbisogno.indietro()" class="sendSmall" type="submit" value="INDIETRO" aria-label="Invia il form" tabindex="70">
						 <input  ng-click= "cruscottoConvalidaFabbisogno.convalidaSingola()" class="sendSmall" type="submit" value="CONVALIDA SINGOLA" aria-label="Invia il form" tabindex="70">
						 <input  ng-click= "cruscottoConvalidaFabbisogno.convalidaMassiva()" class="sendSmall" type="submit" value="CONVALIDA MASSIVA" aria-label="Invia il form" tabindex="70">
						 <input  ng-click= "cruscottoConvalidaFabbisogno.rettificaMassiva()" class="sendSmall" type="submit" value="RETTIFICA MASSIVA" aria-label="Invia il form" tabindex="70">
					 </div>
				</div>   
			</div>
		</fieldset>	
    </div>
  </div>	
<!-- FILTRI -->	
 