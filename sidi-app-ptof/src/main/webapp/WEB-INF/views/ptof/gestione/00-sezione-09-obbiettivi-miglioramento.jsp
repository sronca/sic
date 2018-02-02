<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-09-ObbiettiviMiglioramentoCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

	<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >
			<div   class="block-info block-dark"  ng-if = "'TEXTEDITOR'==componente.tipoComponente"  class="block-info-noborder" > 	
			 		 <textarea   rows="10" cols="50"  ui-tinymce="tinymceOptions" ng-model = "componente.valore"   rows="10" cols="50" aria-label="Descrizione" tabindex="10">  </textarea>
			</div> 
			 
		   <div class="block-info"  ng-if = "'S_OBBIETTIVI_MIGLIORAMENTO'==componente.tipoComponente"  class="block-info-noborder" > 	

		    <h3>PRIORIT&Agrave E TRAGUARDI</h3>
			<table class="table table-bordered table-striped">
				<thead>
				<tr>
				  <th>Area  </th>
				  <th>Descrizione delle priorit&agrave;</th>
				  <th>Descrizione del traguardo</th>
				</tr>
				</thead>
				<tbody>
				<tr  ng-repeat="x in componente.prioritaTraguardi"  >
				  <td>{{ x.area }}</td>
				  <td>{{ x.descPriorita }}</td>
				  <td>{{ x.descTraguardo }}</td>
				</tr>
				</tbody>
			</table> <!--  priorita -->

		    <h3></h3>
			<table class="table table-bordered table-striped">
			
				<thead>
				 <tr>
				  <th> Motivazione scelta priorit&agrave;   </th>
			     </tr>
				</thead>
				
				<tbody>	
				<tr >
				  <td>{{ componente.motivazioneSceltaPriorita }}</td>
				</tr>
				</tbody>
			</table> <!--  motivazioe -->

		    <h3>SCHEMA DI SINTESI OBIETTIVI</h3>
			<table class="table table-bordered table-striped">
				<thead>
				<tr>
				  <th>Area di processo  </th>
				  <th>Descrizione dell'obiettivo di processo </th>
				</tr>
				</thead>
				<tbody>
				<tr  ng-repeat="x in componente.sintesiProcesso"  >
				  <td>{{ x.areaProcesso }}</td>
				  <td>{{ x.descObiettivoProcesso }}</td>
				</tr>
				</tbody>
			</table> <!--  schema di sintesi obiettivi -->


         </div> <!--block-info  -->
  </div> <!-- REPEAT -->	
  	
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
