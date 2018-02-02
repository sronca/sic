<%@ include file="../../layout/taglib.jsp"%>
<script type="text/javascript">
</script>

<div ng-controller = "DocumentiAttivabiliCtrl as gestioneDocCtrl">
		<input type="hidden" ng-model="modelGestioneDoc.inCorso" ng-init="modelGestioneDoc.inCorso='${tipologiaReport}'">
 		<div class="wrapper-content">
			<p class="title">${titoloReport} - CATALOGO DOCUMENTI</p>
			<p class="text no-b-space"></p>
			</br>
		</div>
  		<div class="content1000px">
			<table class="table table-bordered">
				<thead>
				<c:choose>
				  	<c:when test="${tipologiaReport=='CRUSCOTTO_FABISOGNO'}">
				  		<tr>				  			
				  			<th colspan="3" class="fillBlankThTableSmallLeft"></th>
				  			<th colspan="4">Fabbisogno posti</th>
				  		</tr>
						<tr>
						  <th>Documento</th>
						  <th>Triennio</th>
						  <th class="hidden-xs" >Vers.</th>
						  <th>Comuni</th>
						  <th>Sostegno</th>
						  <th>Potenziamento</th>
						  <th>Dotazione organica</th>
						</tr>
					</c:when>
					<c:otherwise>
						 <tr>				  			
				  			<th colspan="1" class="fillBlankThTableSmallLeft"></th>
				  			<th colspan="2">Data Validit&agrave;</th>
				  		</tr>
					  	 <tr>
						  <th>Documento</th>
						  <th>Inizio</th>
						  <th>Fine</th>
						  <th >Triennio</th>
						  <th class="hidden-xs" >Vers.</th>
						  <th></th>
						</tr>
					 </c:otherwise>
				</c:choose>	
				</thead>
				<tbody>
				<tr ng-repeat="x in modelGestioneDoc.documentiAttivabili">
				  <td class="text-left-important">{{ x.nomeDocumento }}</td>
				  <c:if test="${tipologiaReport!='CRUSCOTTO_FABISOGNO'}">
				  	<td>{{ x.dataInizioValidita }}</td>
				  	<td>{{ x.dataFineValidita }}</td>
				  </c:if>
				  <td >{{ x.triennio }}</td>
				  <td class="hidden-xs" >{{ x.versione }}</td>
					  <c:choose>
					  	<c:when test="${tipologiaReport=='CRUSCOTTO_FABISOGNO'}">
					  		<td class="last-td">
					  			<a ng-click="gestioneDocCtrl.loadPostiComuni(x.key)"  href="#"  class="icon miur-vai" title="Cruscotto fabbisogno posti comuni"></a>
					  		</td>	
					  		<td class="last-td">
					  			<a ng-click="gestioneDocCtrl.loadPostiSostegno(x.key)"  href="#"  class="icon miur-vai" title="Cruscotto fabbisogno posti sostegno"></a>
					  		</td>
					  		<td class="last-td">	
					  			<a ng-click="gestioneDocCtrl.loadPostiPotenziamento(x.key)"  href="#"  class="icon miur-vai" title="Cruscotto fabbisogno posti potenziamento"></a>
					  		</td>
					  		<td class="last-td">	
					  			<a ng-click="gestioneDocCtrl.loadTotaleOrganico(x.key)"  href="#"  class="icon miur-vai" title="Cruscotto riepilogo delle dotazioni organiche dell'autonomia"></a>
					  		</td>	
					  	</c:when>
					  	<c:when test="${tipologiaReport=='REPORT_COMPLETO'}">
					  		<td class="last-td">
					  			<a ng-click="gestioneDocCtrl.loadReportCompleto(x.key)"  href="#"  class="icon miur-vai" title="Report completo"></a>
					  		</td>	
					  	</c:when>
					  	<c:when test="${tipologiaReport=='STATISTICHE'}">
					  		<td class="last-td">
					  			<a ng-click="gestioneDocCtrl.loadStatistiche(x.key)"  href="#"  class="icon miur-vai" title="Scarica il file delle statistiche"></a>
					  		</td>	
					  	</c:when>
					  	<c:when test="${tipologiaReport=='DETTAGLIO_FABBISOGNO'}">
					  		<td class="last-td">
					  			<a ng-click="gestioneDocCtrl.loadDettaglioBisogno(x.key)"  href="#"  class="icon miur-vai" title="Consultazione puntuale fabbisogno"></a>
					  		</td>	
					  	</c:when>
					  	<c:when test="${tipologiaReport=='CRUSCOTTO_CONVALIDA_FABBISOGNO'}">
					  		<td class="last-td">
					  			<a ng-click="gestioneDocCtrl.loadCruscottoConvalidaFabbisogno(x.key)"  href="#"  class="icon miur-vai" title="Cruscotto convalida fabbisogno"></a>
					  		</td>	
					  	</c:when>
					  	<c:when test="${tipologiaReport=='CONSULTAZIONE_PUNTUALE_PTOF'}">
					  		<td class="last-td">
					  			<a ng-click="gestioneDocCtrl.loadConsultazionePuntualePtof(x.key)"  href="#"  class="icon miur-vai" title="Consultazione puntuale fabbisogno"></a>
					  		</td>	
					  	</c:when>
					  </c:choose>
				  </td>
				</tr>
				</tbody>
			</table>
		</div>
</div>


