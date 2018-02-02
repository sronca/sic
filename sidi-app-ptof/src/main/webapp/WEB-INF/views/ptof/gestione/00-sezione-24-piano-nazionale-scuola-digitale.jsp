<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-24-GestionePianoNazionaleScuolaDigitaleCtrl as gestioneSezioneCtrl">
		 
		<fieldset>
			<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >				    
			    <div class="block-info"  ng-if = "'S_PIANO_NAZIONALE_SCUOLA_DIGITALE'==componente.tipoComponente"  class="block-info-noborder" >
			    	<!-- TABELLA 1 -->		   
		   				<div bs-tablesmall="" data-opts = "vm.tabella1" ></div>
					<!-- TABELLA 1 END-->	
				</div> 
		    </div>           
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
