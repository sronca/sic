<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">

 
	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-22-GestioneAlternanzaScuolaLavoroCtrl as gestioneSezioneCtrl">
		 
		<fieldset>
			<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  >				    
			    <jsp:include page="00-sezione-00-dati-plesso.jsp"></jsp:include>  
			    
				<jsp:include page="00-sezione-00-txt-allegato.jsp"></jsp:include>
			   
			    <div class="block-info"  ng-if = "'S_ALTERNANZA_SCUOLA_LAVORO'==componente.tipoComponente"  class="block-info-noborder" >
			    	<!-- TABELLA 1 -->		   
		   				<div bs-tablesmall="" data-opts = "vm.tabella1" ></div>
					<!-- TABELLA 1 END-->	
				</div> 
		    </div>           
			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

</div>
 
