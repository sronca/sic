<%@ include file="../../layout/taglib.jsp"%>
<script type="text/javascript">
	
</script>

<div ng-controller = "PubblicaDocCtrl as pubblicaDocCtrl"  >

 		<div class="wrapper-content">
 
			<p class="title">PUBBLICAZIONE DOCUMENTI</p>
 
			<p class="text no-b-space"></p>
		
		</div>
  		<div class="content">
  			<fieldset>
  			<h3></h3>
  			 
				<table class="table table-bordered">
					<thead>
					<tr>
					  <th >Documento  </th>
					  <th >Triennio</th>
					  <th >Stato</th>
					  <th class="hidden-xs" >Versione</th>
					  <th > Azione </th>
					  <th >  </th>
					</tr>
					</thead>
					<tbody>
	
					<tr  ng-repeat="x in modelPubblicaDoc.documenti"  >
					  <td>{{ x.nomeDocumento }}</td>
					  <td >{{ x.triennio }}</td>
					  <td>{{ x.statoDocumentoAsString }}</td>
					  <td class="hidden-xs" >{{ x.versione }}  </td>
					  <td ng-if ="x.statoRichiestaCreazione != 'RICHIESTA_DA_EVADERE' "  > {{ x.statoRichiestaCreazione != 'RICHIESTA_DA_EVADERE' && x.statoDocumento!='PUBBLICATO_PARZIALMENTE' && x.statoDocumento!='PUBBLICATO_COMPLETO' ?'':'Nessuna Azione Richiesta' }} <input ng-if="x.statoRichiestaCreazione != 'RICHIESTA_DA_EVADERE' && x.statoDocumento!='PUBBLICATO_PARZIALMENTE' && x.statoDocumento!='PUBBLICATO_COMPLETO' " ng-click= "pubblicaDocCtrl.pubblica(x)" class="sendForTabella" type="submit" value="{{ x.statoDocumento =='CONVALIDATO' ? 'Anteprima del Documento' : x.statoDocumento =='DOCUMENTO_IN_ANTEPRIMA' ? 'Pubblicazione Parziale ' : x.statoDocumento =='FABBISOGNO_VALIDATO' ? 'Pubblicazione Definitiva' :''  }}" tabindex="70"> </td>
						  
					  <td ng-if ="x.statoRichiestaCreazione != 'RICHIESTA_DA_EVADERE'" > <a ng-if="x.statoDocumento !='CONVALIDATO'" class="icon miur-dettaglio" ng-click= "pubblicaDocCtrl.scarica(x.key)"  title="SCARICA"  for= "inform" ></a> </td>
				  
					  <td ng-if ="x.statoRichiestaCreazione == 'RICHIESTA_DA_EVADERE'" colspan="2"  >  Attesa Generazione </td>
					  
					</tr>
	
					<tr ng-if ="modelPubblicaDoc.documenti == null || modelPubblicaDoc.documenti.lenght==0" >
						<td colspan="6" > Nessun documento da pubblicare </td>
					</tr>
	
	
					</tbody>
				</table>
		 </fieldset>
	</div>

</div>


