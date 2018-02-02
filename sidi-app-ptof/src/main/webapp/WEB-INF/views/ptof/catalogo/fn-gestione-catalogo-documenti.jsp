<%@ include file="../../layout/taglib.jsp"%>
<script type="text/javascript">
</script>

<div ng-controller = "GestioneCatalogoDocumentiCtrl as gestioneCatalogoDocumentiCtrl"  >
 		<div class="wrapper-content">
			<p class="title">${titoloReport}</p>
			<p class="text no-b-space"></p>
			</br>
		</div>
  		<div class="content1000px">
			<table class="table table-bordered">
				<thead>
			  	  	 <tr>
					  <th>Documento</th>
					  <th>Data Inizio Validit&agrave</th>
					  <th>Data Fine Validit&agrave</th>
					  <th >Triennio</th>
					  <th class="hidden-xs" >Versione</th>
					  <th></th>
					</tr>
				</thead>
				<tbody>

				<tr ng-repeat="x in modelGestioneCatalogoDocumenti.documentiAttivabili">
				  <td class="text-left-important">{{ x.nomeDocumento }}</td>
				  <td>{{ x.dataInizioValidita }}</td>
				  <td>{{ x.dataFineValidita }}</td>
				  <td >{{ x.triennio }}</td>
				  <td class="hidden-xs" >{{ x.versione }}</td>
		    	  <td class="last-td">
		  			<a ng-click="gestioneCatalogoDocumentiCtrl.modifica(x.key)"  href="#"  class="icon miur-pencil-small" title="Modifica"></a>
		  		  </td>	
				</tr>
				</tbody>
			</table>
			
			<div class="row" >
	 			 <div class="col-xs-2 col-md-3 text-left">  </div>
				 <div class="col-xs-10 col-md-9  text-right">
						 <input  ng-click= "gestioneCatalogoDocumentiCtrl.aggiungi()" class="send" type="submit" value="AGGIUNGI" aria-label="Invia il form" tabindex="70" > </input>
				 </div>
			</div>
			
			<!--  Modal -->
			<div id = "idDialog" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h3 class="modal-title"> ${titoloReport} - {{ azione }}</h3>
									<!-- sottotitolo -->
								</div>
								<div class="modal-body">
		                                    
			                   <form role="form" class = "form-horizontal">
				                   <input maxlength="10" 
							      	      type="hidden" 
							      	      id = "progresivoGestioneCatalogoDocumento"  
							    		  ng-model = "catalogoDocumento.progresivoGestioneCatalogoDocumento"></input>
									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label"> Descrizione   </label>
										 <div class="col-sm-9 col-xs-9" > 
										   <textarea rows="5" 
										   			 id="descrizioneDocumento"  
										   			 msd-elastic=""   
										   			 ng-readonly = "dialogAction=='V'" 
										   			 ng-model = "catalogoDocumento.descrizioneDocumento" 
										   			 class="form-control" 
										   			 placeholder="inserisci" ></textarea>
										 </div>			   
									 </div>			   

									<div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Data inizio validit&agrave;    </label>
										 <div class="col-sm-3 col-xs-3" > 
										      <input maxlength="10" 
										      	     type="text" 
										      	     id = "dataInizioValidita"  
										      		 ng-readonly = "dialogAction=='V'"
										    		 data-date-time-picker =""
										    		 ng-model = "catalogoDocumento.dataInzioValidita"    
										    		 class="form-control" 
										    		 placeholder="inserisci"></input>
										 </div>			   
									 </div>			   

							        <div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Data fine validit&agrave;    </label>
										 <div class="col-sm-3 col-xs-3" > 
										     <input maxlength="10" 
										      	     type="text" 
										      	     id = "dataFineValidita"  
										      		 ng-readonly = "dialogAction=='V'"
										    		 data-date-time-picker =""
										    		 ng-model = "catalogoDocumento.dataFineValidita"    
										    		 class="form-control" 
										    		 placeholder="inserisci"></input>
										 </div>			   
									</div>			   

							        <div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Triennio    </label>
										 <div class="col-sm-3 col-xs-3" > 
										   <input 
										    	   id="triennio"  
										    	   ng-readonly = "true"   
										    	   maxlength="10" 
										    	   type="text"    
										    	   ng-model = "catalogoDocumento.periodoTriennioRiferimento"    
										    	   class="form-control" 
										    	   placeholder="triennio"></input>  
										 </div>			   
									 </div>
									 
									 <div class="form-group row" >  
										<label for="item1_obbiettivi" class= "col-sm-3 col-xs-3 control-label">Versione    </label>
										 <div class="col-sm-2 col-xs-2" > 
										   <input type="text"
										   		  fa-numeric="numeroIntero2DType"
										   		  id="versione"  
										   		  ng-readonly = "true"    
										   		  fa-model = "catalogoDocumento.numeroVersioneDocumento"    
										   		  class="form-control" 
										   		  placeholder="versione" ></input>  
										 </div>			   
									 </div>			   
								</form>                      
								</div>
								<div class="modal-footer">
								   <label  ng-if = "dialogAction!='V'"  ng-click = "gestioneCatalogoDocumentiCtrl.salva()"  for="inform"   class="">SALVA</label>
								    <label    for="inform" data-dismiss="modal" class="">  CHIUDI</label>
								</div>
							</div>
						</div>
			</div>
		</div>
</div>