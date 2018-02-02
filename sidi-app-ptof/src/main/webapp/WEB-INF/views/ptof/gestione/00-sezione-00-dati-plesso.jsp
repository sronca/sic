           <div class="block-info  table-responsive"  ng-if = "'DATI_PLESSO'==componente.tipoComponente"  class="block-info-noborder" > 	
                  
	             <h3>DATI IDENTIFICATIVI  DEL PLESSO  </h3>
	            	<table class="table table-bordered">
	            	<thead>
						<tr>
						  <th class="col-md-4">Denominazione Plesso</th>
						  <th class="col-md-4">Indirizzo</th>
						  <th class="col-md-4">Comune</th> 
						  <th class="col-md-4">Codice Meccanografico</th>   
						</tr>
					</thead>
					<tbody>
						<tr >
						  <td  >{{ componente.plesso.denominazione }} </td>
						  <td  > {{ componente.plesso.indirizzo }}  </td>
						  <td  > {{ componente.plesso.comune }}  </td>
						  <td  > {{ componente.plesso.codiceMecUtente }}  </td>
						</tr>
					 
					</tbody>
	            	</table>
	     </div>