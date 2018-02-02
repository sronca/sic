<%@ include file="../../layout/taglib.jsp"%>
<script type="text/javascript">
	
</script>

<div ng-controller = "GestioneDocCtrl as gestioneDocCtrl"  >

 		<div class="wrapper-content">
 
			<p class="title">CATALOGO DOCUMENTI</p>
 
			<p class="text no-b-space"></p>
		
		</div>
  		<div class="content">
  			
  			<h3>Anno in corso</h3>
  				
			<table class="table table-bordered">
				<thead>
				<tr>
				  <th>Documento</th>
				  <th class="hidden-xs" >Data Inizio Validit&agrave</th>
				  <th class="hidden-xs" >Data Fine Validit&agrave</th>
				  <th  >Triennio</th>
				  <th class="hidden-xs" >Versione</th>
				  <th >Stato</th>
				  <th class="xs"></th>
				</tr>
				</thead>
				<tbody>

				<tr  ng-repeat="x in modelGestioneDoc.documentiAnnoIncorso"  >
				  <td>{{ x.nomeDocumento }}</td>
				  <td class="hidden-xs" >{{ x.dataInizioValidita }}</td>
				  <td class="hidden-xs" >{{ x.dataFineValidita }}</td>
				  <td >{{ x.triennio }}</td>
				  <td class="hidden-xs" >{{ x.versione }}</td>
				  <td>{{ x.statoDocumentoAsString }}</td>
				  <td class="last-td"><a ng-click="gestioneDocCtrl.loadGestionePtof(x.key)"  href="#"  class="icon miur-vai"></a></td>
				</tr>

				</tbody>
			</table>
			<h3>Archivio</h3>
			<table class="table table-bordered">
				<thead>
				<tr>
				  <th>Documento</th>
				  <th>Triennio</th>
				  <th>Versione</th>
				  <th >Stato</th>
				  <th class="xs">Pdf Parziale</th>
				  <th class="xs">Pdf Completo</th>
				</tr>
				</thead>
				<tbody>
				
   			   <tr  ng-repeat="x in modelGestioneDoc.documentiArchivi"  ng-class-odd="'odd'" >
				  <td>{{ x.nomeDocumento }}</td>
 				  <td>{{ x.triennio }}</td>
				  <td>{{ x.versione }}</td>
				  <td>{{ x.statoDocumentoAsString }}</td>
				  <td class="last-td"><a ng-show = "x.statoDocumento=='PUBBLICATO_PARZIALMENTE' || x.statoDocumento=='PUBBLICATO_COMPLETO'" ng-click="gestioneDocCtrl.getPtofFileArchivio(x.key,'PUBBLICATO_PARZIALMENTE')"  href="#"  class="icon miur-dettaglio"></a></td>
				  <td class="last-td"><a ng-show = "x.statoDocumento=='PUBBLICATO_COMPLETO' " ng-click="gestioneDocCtrl.getPtofFileArchivio(x.key,'PUBBLICATO_COMPLETO')"  href="#"  class="icon miur-dettaglio"></a></td>
				</tr>
	 			 
				</tbody>
			</table>
		</div>

</div>


