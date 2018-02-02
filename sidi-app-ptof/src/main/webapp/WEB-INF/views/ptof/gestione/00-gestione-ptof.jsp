<div ng-controller = "GestioneProfListaSezioniCtrl as listaSezioniCtrl"  >
 
		<div class="wrapper-content">
 
		<p class="title">Lista Sezioni</p>
 
			<p class="text"></p>
 
		</div>
 
         <div class="block-info">
	            	<h3>Dati istituto principale</h3>
	            	<table class="table table-one-col table-bordered">
	            		<tbody>
	            			<tr class="odd-light">
							  <td>Codice Meccanografico Istituto Principale: <b>{{ modelGestionePtof.istitutoScolastico.codiceMecIstPrin  }}</b></td>
							</tr>
							<tr>
							  <td>Tipologia scuola: <b> {{ modelGestionePtof.istitutoScolastico.tipologiaScuola  }} </b></td>
							</tr>
							<tr class="odd-light">
							  <td>Denominazione: <b> {{ modelGestionePtof.istitutoScolastico.denominazione  }} </b></td>
							</tr>
							<tr>
							  <td>Comune: <b> {{ modelGestionePtof.istitutoScolastico.comune  }}  </b></td>
							</tr>
	            		</tbody>
	            	</table>
		</div>

 
		<div class="content">
		 
		 	<div class="panel-group" id="accordion">
			 
				  <div class="panel panel-default" ng-repeat="sezione in modelGestionePtof.sezioni"  id ="_id_sez_{{$index}}" >
			  
				    <div class="panel-heading"  ng-class="{green : sezione.statoSezione == 'COMPILATA' }" >
				      <h4 class="panel-title" >
				        <div bs-itemsezione ="" data-optnav="{ focus : modelGestionePtof.focus , keyforfocus : $index }" data-doc = "modelGestionePtof.form.keyDocumento" class="panel-title" data-sezione = "sezione"  ></div>
				      </h4>
				      <span ng-if = "sezione.statoSezione == 'COMPILATA'"  class="icon miur-check-1" ></span>
				      <span ng-if ="sezione.statoSezione!='COMPILATA'"     class="icon miur-setting-gear"></span> 
				    </div>

                     <div bs-listastasezioni="" data-optnav="{ focus : modelGestionePtof.focus , keyforfocus : $index  }"  data-doc = "modelGestionePtof.form.keyDocumento" data-sezioni= "sezione.sottoSezione"  data-key ="sezione.key" data-liv = "1" ></div>				    
	 
				    <div class="vertical-line"></div>
  
			      </div>
			  
			  
           <div class="panel panel-default" >
			    <div class="validate">
			    	
			    	<input  ng-if ="modelGestionePtof.documento.statoDocumento=='IN_COMPILAZIONE'" ng-click= "listaSezioniCtrl.convalida()"               class="send" type="submit" value="CONVALIDA" aria-label="Invia il form">
			    	<input  ng-if ="(modelGestionePtof.documento.statoDocumento=='DOCUMENTO_IN_ANTEPRIMA') || (modelGestionePtof.documento.statoDocumento=='CONVALIDATO') " ng-click= "listaSezioniCtrl.ritornaAllaCompilazione()" class="send" type="submit" value="RITORNA ALLA COMPILAZIONE" aria-label="Invia il form">
			    	<input  ng-if ="(modelGestionePtof.documento.statoDocumento=='FABBISOGNO_NON_VALIDATO') " ng-click= "listaSezioniCtrl.ritornaAllaCompilazione()" class="send" type="submit" value="RITORNA ALLA VALIDAZIONE USR" aria-label="Invia il form">
			    	
			    </div>
           </div> 
			  
			</div>
		</div>
 
	</div>
	
 