<%@ include file="../../layout/taglib.jsp"%>
<div class="tab-content" ng-controller="DocumentoDecretiDettaglioCtrl as documentoDecretiDettaglioCtrl">
    <div class="wrapper-content">
 		<p class="title">${titoloReport}</p>
 	</div>
 	<div class="tab-pane fade in active" id="target2">
		<fieldset>	
	 	<!-- FILTRI -->	
	 		<div id="filtro-ricerca">
	 		    <bsinfocatalogodocumento data-bprogressivogestionecatalogodocumento="${progressivoGestioneCatalogoDocumento}"></bsinfocatalogodocumento>
                
               		<div  class="block-info block-dark">
						<form  enctype="multipart/form-data" method="post" action="" >
							<input type="text" ng-show="false" name="progressivoGestioneCatalogoDocumento" ng-model="vm.form.progressivoGestioneCatalogoDocumento" ng-init="vm.form.progressivoGestioneCatalogoDocumento=${progressivoGestioneCatalogoDocumento}">
					  		 <label ng-show = "vm.form.allegato.suDB=='FALSE'" for="upload">SFOGLIA</label>   
					  		 <label ng-show = "vm.form.allegato.suDB=='TRUE'" ng-click= "documentoDecretiDettaglioCtrl.scarica()" for= "inform" >SCARICA</label>
					  		 <label ng-show = "vm.form.allegato.nome && vm.form.allegato.suDB=='FALSE'" ng-click= "documentoDecretiDettaglioCtrl.allega()" for= "inform" >ALLEGA</label>
					  		 
					  		 <input data-url="gestione-catalogo-documento/set-m-caricaDecretiExcel.json" bs-watch ="documentoDecretiDettaglioCtrl.gestioneAllegato" bs-upload type="file" name="upload" id="upload" aria-label="Carica un allegato PDF" tabindex="20"/>
					  		 
					  		 <input type="hidden" name="keyComponente" value="{{vm.form.allegato.key}}"></input>  
					 	</form>	
					  	<input id="name_allegato_000" type="text" ng-model="vm.form.allegato.nome" readonly="readonly" class="form-control"/> 				          
			    	</div>
			    	<div class="row" >
			 			 <div class="col-xs-2 col-md-3 text-left">  </div>
							 <div class="col-xs-10 col-md-9  text-right">
							 <input  ng-click= "indietro()" class="send" type="submit" value="INDIETRO" aria-label="Invia il form" tabindex="70">
						 </div>
					</div>   
			</div>
		</fieldset>	
    </div>
  </div>	 