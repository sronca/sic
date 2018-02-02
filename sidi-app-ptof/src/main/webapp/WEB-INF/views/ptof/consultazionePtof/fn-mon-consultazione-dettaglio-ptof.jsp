<%@ include file="../../layout/taglib.jsp"%>
<div class="tab-content" ng-controller="ConsultazioneDettaglioPtofCtrl as consultazioneDettaglioPtofCtrl">
    <div class="wrapper-content">
 		<p class="title">${titoloReport}   </p>
 	</div>
 	<div class="tab-pane fade in active" id="target2">
		<fieldset>	
	 	<!-- FILTRI -->	
	 		<div id="filtro-ricerca">
	 			<bsinfocatalogodocumento data-bprogressivogestionecatalogodocumento="{{vm.attribForm.progCatDoc}}"></bsinfocatalogodocumento>
	 			
	 			<div class="block-info">
      			 	<div class="form-group row">
      					<label class="col-xs-3 col-md-3 control-label" >Codice Meccanografico</label>
      					<div class="col-xs-9 col-md-9" >
      			      		<input type="text" value="{{vm.attribForm.consultazionePtof.istitutoScolastico.codiceMecIstPrin}}" class="form-control" disabled >
      			 		</div>
      			    </div>
      			   <div class="form-group row">
      			       <label class="col-xs-3 col-md-3 control-label" >Denominazione</label>
      			       <div class="col-xs-9 col-md-9" >
      			      		<input type="text" value="{{vm.attribForm.consultazionePtof.istitutoScolastico.denominazione}}" class="form-control" disabled>
      			       </div>
      			    </div>
      			</div>
	 			
	 			
			    <div class="block-info">
			        <div> 
				    	 <h3>FABBISOGNO POSTI COMUNI</h3>
				    	 
				    	 <div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniInfanzia ">
					    	 <table class="table table-bordered">
								<thead>
									<tr>
										<th colspan="3" class="groupTitleTh">INFANZIA</th>
									</tr>
									<tr>
									  <th>{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno0}}</th>
									  <th>{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno1}}</th>
									  <th>{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno2}}</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
								<tfoot>
									<tr>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniInfanzia.postiAnnoZero}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniInfanzia.postiAnnoUno}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniInfanzia.postiAnnoDue}}</th>
									</tr>
								</tfoot>
							</table>
					    	 
			    	 	    <div class="form-group row">
		      			       <label class="col-xs-2 col-md-2 control-label" >Motivazione</label>
		      			       <div class="col-xs-10 col-md-10" >
		      			       		<textarea rows="5"  class="form-control" disabled>{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniInfanzia.motivazione}}</textarea>
		      			       </div>
		      			    </div>
	      			    </div>
				    		 
				    	<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniPrimaria ">
				    		<table class="table table-bordered">
								<thead>
									<tr>
										<th colspan="3" class="groupTitleTh">PRIMARIA</th>
									</tr>
									<tr>
									  <th>{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno0}}</th>
									  <th>{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno1}}</th>
									  <th>{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno2}}</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
								<tfoot>
									<tr>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniPrimaria.postiAnnoZero}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniPrimaria.postiAnnoUno}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniPrimaria.postiAnnoDue}}</th>
									</tr>
								</tfoot>
							</table>
					    	 
			    	 	    <div class="form-group row">
		      			       <label class="col-xs-2 col-md-2 control-label" >Motivazione</label>
		      			       <div class="col-xs-10 col-md-10" >
		      			       		<textarea rows="5" class="form-control" disabled>{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniPrimaria.motivazione}}</textarea>
		      			       </div>
		      			    </div>	 
						</div>
										    		 
				    		 <div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniIGrado.length > 0 ">
				    		 	<table class="table table-bordered">
									<thead>
										<tr>
											<th colspan="5" class="groupTitleTh">SECONDARIA DI I GRADO</th>
										</tr>
										<tr>
										  <th class="text-center">Classe di concorso</th>
										  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno0}}</th>
										  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno1}}</th>
										  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno2}}</th>
										  <th class="text-center">Motivazione</th>
										</tr>
									</thead>
									<tbody>
										 <tr ng-repeat="IGrado in vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniIGrado">
											  <td class="text-left-important">{{ IGrado.classeDiConcorso }}</td>
											  <td class="text-center">{{ IGrado.postiAnnoZero }}</td>
											  <td class="text-center">{{ IGrado.postiAnnoUno }}</td>
											  <td class="text-center">{{ IGrado.postiAnnoDue }}</td>
											  <td class="text-center">{{ IGrado.motivazione }}</td>  
										</tr>
									</tbody>
									<tfoot>
									 
									</tfoot>
								</table>
				    		  </div>	
				    		  
				    		  <div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniIIGrado.length > 0 ">
				    		 	<table class="table table-bordered">
									<thead>
										<tr>
											<th colspan="5" class="groupTitleTh">SECONDARIA DI II GRADO</th>
										</tr>
										<tr>
										  <th class="text-center">Classe di concorso</th>
										  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno0}}</th>
										  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno1}}</th>
										  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.labelAnno2}}</th>
										  <th class="text-center">Motivazione</th>
										</tr>
									</thead>
									<tbody>
									  <tr ng-repeat="IIGrado in vm.attribForm.consultazionePtof.fabbisognoPostiComuniIntestaz.fabbisognoPostiComuniIIGrado">
										  <td class="text-left-important">{{ IIGrado.classeDiConcorso }}</td>
										  <td class="text-center">{{ IIGrado.postiAnnoZero }}</td>
										  <td class="text-center">{{ IIGrado.postiAnnoUno }}</td>
										  <td class="text-center">{{ IIGrado.postiAnnoDue }}</td>
										  <td class="text-center">{{ IIGrado.motivazione }}</td>  
									  </tr>
									</tbody>
									<tfoot>
									</tfoot>
								</table>
				    		  </div>
				    	</div>
			    </div>
			    
			    <div class="block-info">
			        <div> 
				    	 <h3>FABBISOGNO POSTI DI SOSTEGNO</h3>
				
				    	<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia ">
			    		 	<table class="table table-bordered">
								<thead>
									<tr>
										<th rowspan="2" class="fillBlankThTableSmallLeft"></th>
										<th colspan="3" class="groupTitleTh">INFANZIA</th>
									</tr>
									<tr>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno0}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno1}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno2}}</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
								<tfoot>
									<tr>
										<td class="footerBg-primary">Udito</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.postiSostegnoUditoPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.postiSostegnoUditoSecondoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.postiSostegnoUditoTerzoTriennio }}</td>
									</tr>
									<tr>
										<td class="footerBg-primary">Vista</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.postiSostegnoVistaPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.postiSostegnoVistaSecondoTriennio }}</td>  
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.postiSostegnoVistaTerzoTriennio }}</td>
									</tr>
									<tr>
										<td class="footerBg-primary">Psicofisico</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.postiSostegnoPsicofisicoPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.postiSostegnoPsicofisicoSecondoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.postiSostegnoPsicofisicoTerzoTriennio }}</td>
									</tr>
								</tfoot>
							</table>
							
							<div class="form-group row">
		      			       <label class="col-xs-2 col-md-2 control-label" >Motivazione</label>
		      			       <div class="col-xs-10 col-md-10" >
		      			       		<textarea rows="5" class="form-control" disabled>{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoInfanzia.motivazione }}</textarea>
		      			       </div>
		      			    </div>
			    		</div>
			    		
			    		
			    		<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria ">
			    		 	<table class="table table-bordered">
								<thead>
									<tr>
										<th rowspan="2" class="fillBlankThTableSmallLeft"></th>
										<th colspan="3" class="groupTitleTh">PRIMARIA</th>
									</tr>
									<tr>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno0}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno1}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno2}}</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
								<tfoot>
									<tr>
										<td class="footerBg-primary">Udito</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.postiSostegnoUditoPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.postiSostegnoUditoSecondoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.postiSostegnoUditoTerzoTriennio }}</td>
									</tr>
									<tr>
										<td class="footerBg-primary">Vista</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.postiSostegnoVistaPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.postiSostegnoVistaSecondoTriennio }}</td>  
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.postiSostegnoVistaTerzoTriennio }}</td>
									</tr>
									<tr>
										<td class="footerBg-primary">Psicofisico</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.postiSostegnoPsicofisicoPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.postiSostegnoPsicofisicoSecondoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.postiSostegnoPsicofisicoTerzoTriennio }}</td>
									</tr>
								</tfoot>
							</table>
							
							<div class="form-group row">
		      			       <label class="col-xs-2 col-md-2 control-label" >Motivazione</label>
		      			       <div class="col-xs-10 col-md-10" >
		      			       		<textarea rows="5"  class="form-control" disabled>{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoPrimaria.motivazione }}</textarea>
		      			       </div>
		      			    </div>
			    		</div>
			    		
			    		
			    		<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado ">
			    		 	<table class="table table-bordered">
								<thead>
									<tr>
										<th rowspan="2" class="fillBlankThTableSmallLeft"></th>
										<th colspan="3" class="groupTitleTh">SECONDARIA DI I GRADO</th>
									</tr>
									<tr>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno0}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno1}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno2}}</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
								<tfoot>
									<tr>
										<td class="footerBg-primary">Udito</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.postiSostegnoUditoPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.postiSostegnoUditoSecondoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.postiSostegnoUditoTerzoTriennio }}</td>
									</tr>
									<tr>
										<td class="footerBg-primary">Vista</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.postiSostegnoVistaPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.postiSostegnoVistaSecondoTriennio }}</td>  
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.postiSostegnoVistaTerzoTriennio }}</td>
									</tr>
									<tr>
										<td class="footerBg-primary">Psicofisico</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.postiSostegnoPsicofisicoPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.postiSostegnoPsicofisicoSecondoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.postiSostegnoPsicofisicoTerzoTriennio }}</td>
									</tr>
								</tfoot>
							</table>
							
							<div class="form-group row">
		      			       <label class="col-xs-2 col-md-2 control-label" >Motivazione</label>
		      			       <div class="col-xs-10 col-md-10" >
		      			       		<textarea rows="5" class="form-control" disabled>{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIGrado.motivazione }}</textarea>
		      			       </div>
		      			    </div>
			    		</div>
			    		
			    		
			    		<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIIGrado ">
			    		 	<table class="table table-bordered">
								<thead>
									<tr>
										<th colspan="3" class="groupTitleTh">SECONDARIA DI II GRADO</th>
									</tr>
									<tr>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno0}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno1}}</th>
									  <th class="text-center">{{vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.labelAnno2}}</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
								<tfoot>
									<tr>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIIGrado.postiSostegnoPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIIGrado.postiSostegnoSecondoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIIGrado.postiSostegnoTerzoTriennio }}</td>
									</tr>
								</tfoot>
							</table>
							
							<div class="form-group row">
		      			       <label class="col-xs-2 col-md-2 control-label" >Motivazione</label>
		      			       <div class="col-xs-10 col-md-10" >
		      			       		<textarea rows="5" class="form-control" disabled>{{ vm.attribForm.consultazionePtof.fabbisognoPostiSostegnoIntestaz.fabbisognoPostiSostegnoIIGrado.motivazione }}</textarea>
		      			       </div>
		      			    </div>
			    		</div>
			    	</div>
			    </div>
			    		  
			    <div class="block-info">
			  	    <div> 
				    	<h3>FABBISOGNO POSTI DI POTENZIAMENTO</h3>
				    	<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoPrimaria ">
			    		 	<table class="table table-bordered">
								<thead>
									<tr>
										<th colspan="6" class="groupTitleTh">PRIMARIA</th>
									</tr>
									<tr>
										<th colspan="3">Posti di potenziamento</th>
										<th colspan="3">Potenziamento al sostegno</th>
									</tr>
									<tr>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno0 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno1 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno2 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno0 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno1 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno2 }}</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
								<tfoot>
									<tr>
									 <td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoPrimaria.postiPotenziamentoPrimoTriennio }}</td>
									 <td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoPrimaria.postiPotenziamentoSecondoTriennio }}</td>
									 <td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoPrimaria.postiPotenziamentoTerzoTriennio }}</td>
									 <td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoPrimaria.postiPotenziamentoDiSostegnoPrimoTriennio }}</td>
									 <td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoPrimaria.postiPotenziamentoDiSostegnoSecondoTriennio }}</td>
									 <td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoPrimaria.postiPotenziamentoDiSostegnoTerzoTriennio }}</td>
									</tr>
								</tfoot>
							</table>
							
							<div class="form-group row">
		      			       <label class="col-xs-2 col-md-2 control-label" >Motivazione</label>
		      			       <div class="col-xs-10 col-md-10" >
		      			       		<textarea rows="5" class="form-control" disabled>{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoPrimaria.motivazione }}</textarea>
		      			       </div>
		      			    </div>
			    		</div>
			    		
			    		
			    		<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoIGrado.length > 0 ">
			    		 	<table class="table table-bordered">
								<thead>
									<tr>
										<th colspan="5" class="groupTitleTh">SECONDARIA DI I GRADO</th>
									</tr>
									<tr>
									  <th class="text-center">Classe di concorso</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno0 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno1 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno2 }}</th>
									  <th class="text-center">Motivazione</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="IGrado in vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoIGrado">
									  <td class="text-left-important">{{ IGrado.classeDiConcorso }}</td>
									  <td class="text-center">{{ IGrado.postiPotenziamentoPrimoTriennio }}</td>
									  <td class="text-center">{{ IGrado.postiPotenziamentoSecondoTriennio }}</td>
									  <td class="text-center">{{ IGrado.postiPotenziamentoTerzoTriennio }}</td>
									  <td class="text-center">{{ IGrado.motivazione }}</td>  
									</tr>
								</tbody>
								<tfoot>
								</tfoot>
							</table>
			    		</div>
			    		
			    		
			    		<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIGrado ">
			    		 	<table class="table table-bordered">
								<thead>
									<tr>
										<th colspan="3" class="groupTitleTh">POTENZIAMENTO AL SOSTEGNO SECONDARIA DI I GRADO</th>
									</tr>
									<tr>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno0 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno1 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno2 }}</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
								<tfoot>
									<tr>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIGrado.postiPotenziamentoPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIGrado.postiPotenziamentoSecondoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIGrado.postiPotenziamentoTerzoTriennio }}</td>
									</tr>
								</tfoot>
							</table>
							
							<div class="form-group row">
		      			       <label class="col-xs-2 col-md-2 control-label" >Motivazione</label>
		      			       <div class="col-xs-10 col-md-10" >
		      			       		<textarea rows="5" class="form-control" disabled>{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIGrado.motivazione }}</textarea>
		      			       </div>
		      			    </div>
		      			    
			    		</div>
			    		
			    		
			    		<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoIIGrado.length > 0 ">
			    		 	<table class="table table-bordered">
								<thead>
									<tr>
										<th colspan="5" class="groupTitleTh">SECONDARIA DI II GRADO</th>
									</tr>
									<tr>
									  <th class="text-center">Classe di concorso</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno0 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno1 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno2 }}</th>
									  <th class="text-center">Motivazione</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="IIGrado in vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoIIGrado ">
									  <td class="text-left-important">{{ IIGrado.classeDiConcorso }}</td>
									  <td class="text-center">{{ IIGrado.postiPotenziamentoPrimoTriennio }}</td>
									  <td class="text-center">{{ IIGrado.postiPotenziamentoSecondoTriennio }}</td>
									  <td class="text-center">{{ IIGrado.postiPotenziamentoTerzoTriennio }}</td>
									  <td class="text-center">{{ IIGrado.motivazione }}</td>  
									</tr>
								</tbody>
								<tfoot>
								</tfoot>
							</table>
			    		</div>
			    		
			    		
			    		<div class="block-info" ng-show=" vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIIGrado ">
			    		 	<table class="table table-bordered">
								<thead>
									<tr>
										<th colspan="3" class="groupTitleTh">POTENZIAMENTO AL SOSTEGNO SECONDARIA DI II GRADO</th>
									</tr>
									<tr>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno0 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno1 }}</th>
									  <th class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.labelAnno2 }}</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
								<tfoot>
									<tr>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIIGrado.postiPotenziamentoPrimoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIIGrado.postiPotenziamentoSecondoTriennio }}</td>
										<td class="text-center">{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIIGrado.postiPotenziamentoTerzoTriennio }}</td>
									</tr>
								</tfoot>
							</table>
							
							<div class="form-group row">
		      			       <label class="col-xs-2 col-md-2 control-label" >Motivazione</label>
		      			       <div class="col-xs-10 col-md-10" >
		      			       		<textarea rows="5" class="form-control" disabled>{{ vm.attribForm.consultazionePtof.fabbisognoPostiPotenziamentoIntestaz.fabbisognoPostiPotenziamentoDiSostegnoIIGrado.motivazione }}</textarea>
		      			       </div>
		      			    </div>
		      			    
			    		</div>
				    </div> 
			    </div>
			    
			    <div class="row" >
					 <div class="col-xs-12 col-md-12  text-center">
						 <input  ng-click= "indietro()" class="sendSmall" type="submit" value="INDIETRO" aria-label="Invia il form" tabindex="70">
				     </div>
				</div>   
			</div>
		</fieldset>	
    </div>
  </div>	
<!-- FILTRI -->	
 