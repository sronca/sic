<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">


	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-03StoriaCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
      
		    <jsp:include page="00-sezione-00-dati-plesso.jsp"></jsp:include>  
		    
			<jsp:include page="00-sezione-00-txt-allegato.jsp"></jsp:include>
 

			<div class="block-info"  ng-if = "'DATI_ISTITUTO_PRINCIPALE'==componente.tipoComponente"  class="block-info-noborder" > 	
	 	       
	 	      
	 	     	   
	 		  <h3>Dati istituto principale</h3>
	            	<table class="table table-one-col table-bordered table-striped">
	            		<tbody>
	            			<tr >
							  <td>Denominazione: <b>{{componente.istitutoScolasticoDTO.denominazione}}</b></td>
							</tr>
							<tr>
							  <td>Tipologie Scuole associate:  <b> {{componente.istitutoScolasticoDTO.tipologieScuoleAssociate}} </b></td>
							</tr>
							<tr >
							  <td>Dirigente Scolastico:  <b> {{componente.istitutoScolasticoDTO.dirigenteScolastico}} </b></td>
							</tr>

							<tr >
							  <td>Indirizzo:  <b> {{componente.istitutoScolasticoDTO.indirizzo}} </b></td>
							</tr>
														
							<tr >
							  <td>Codice Meccanografico:  <b> {{componente.istitutoScolasticoDTO.codiceMecIstPrin}} </b></td>
							</tr>
						
						   <tr >
							  <td>Telefono:   <b> {{componente.istitutoScolasticoDTO.telefono}} </b></td>
							</tr>	
							
						   <tr >
							  <td>Fax:  <b>{{componente.istitutoScolasticoDTO.fax}} </b></td>
							</tr>
						
						   <tr >
							  <td>Email:  <b> {{componente.istitutoScolasticoDTO.email}} </b></td>
							</tr>	
						
						    <tr >
							  <td>PEC:   <b>{{componente.istitutoScolasticoDTO.pec}} </b></td>
							</tr>
						
						   <tr >
							  <td>Sito WEB: <b> {{componente.istitutoScolasticoDTO.sitoWeb}} </b></td>
							</tr>	

						    <tr >
							  <td>Numero Plessi/scuole:  <b>{{componente.istitutoScolasticoDTO.numeroPlessi}} </b></td>
							</tr>
						
						   <tr >
							  <td>Di cui:  <b> {{componente.istitutoScolasticoDTO.numeroPlessiPerTipologiaScuola}} </b></td>
							</tr>	
							
							
	            		</tbody>
	            	</table>
	            	
	             <h3>Dati Plessi </h3>
	            	<table class="table table-bordered">
	            	<thead>
						<tr>
						  <th class="col-md-4">Plesso/Scuola</th>
						  <th class="col-md-4">Indirizzo</th>
						  <th class="col-md-4">Telefono</th> 
						  <th class="col-md-4">Mail</th>   
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat = "plesso in componente.istitutoScolasticoDTO.plessi" ng-class="$index%2 == 0 ? 'odd-light' :''"  >
						  <td  >{{ plesso.plessoScuola }} </td>
						  <td  > {{ plesso.indirizzo }}  </td>
						  <td  > {{ plesso.telefono }}  </td>
						  <td  > {{ plesso.email }}  </td>
						</tr>
					 
					</tbody>
	            	</table>
	 		   
	    </div>
	 		 

            <div class="block-info"  ng-if = "'COMBO_BOX'==componente.tipoComponente"  class="block-info-noborder" > 	
                    <form role="form" class = "form-horizontal">
						<div class="form-group">  
							<label for="combo_{{componente.key}}" class= "col-sm-6 col-xs-6 control-label">  {{ componente.nome }} </label>
							 <div class="col-sm-6 col-xs-6" > 
							  <bsselectpicker data-bid="combo_{{componente.key}}"  data-items="componente.itemSelezionabili" data-selected="componente.selected"></bsselectpicker>
							 </div>			   
						</div>
					</form>                      
		   </div>


           <div class="block-info"  ng-if = "'MULTI_BOX'==componente.tipoComponente"  class="block-info-noborder" > 	
                  
                    <h3>  {{componente.nome}}  {{componente.descrizione}}  </h3>
                  
                    <form role="form" class = "form-horizontal">
						<div class="form-group" ng-repeat = "combo in componente.comboBox" >  
							<label for="combo_{{combo.key}}" class= "col-sm-6 col-xs-6 control-label">  {{ combo.nome }} </label>
							 <div class="col-sm-6 col-xs-6" > 
							  <bsselectpicker data-bid="combo_{{combo.key}}"  data-items="combo.itemSelezionabili" data-selected="combo.selected"></bsselectpicker>
							 </div>			   
						</div>
					</form>                      
		   </div>
 

      </div>	 	    
	 	    

			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

    
</div>