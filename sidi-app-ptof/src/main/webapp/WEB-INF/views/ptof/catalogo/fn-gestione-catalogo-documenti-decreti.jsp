<%@ include file="../../layout/taglib.jsp"%>
<script type="text/javascript">
</script>

<div ng-controller = "GestioneCatalogoDocumentiDecretiCtrl as gestioneCatalogoDocumentiDecretiCtrl"  >
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
		  			<a ng-click="gestioneCatalogoDocumentiDecretiCtrl.upload(x.key)"  href="#"  class="icon miur-vai" title="Upload dati decreti"></a>
		  		  </td>	
				</tr>
				</tbody>
			</table>
		</div>
</div>