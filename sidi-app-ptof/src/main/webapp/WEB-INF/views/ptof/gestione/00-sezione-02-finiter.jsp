<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">


	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-02FinalitaCtrl as gestioneSezioneCtrl">
		
		 
		<fieldset>
		
		<div  class="block-info-noborder" > 	
 		 <textarea  ui-tinymce="tinymceOptions" ng-model = "modelGestioneSezione02Finalita.form.componenti[0].valore"   rows="10" cols="80" aria-label="Descrizione" tabindex="10">  </textarea>
		</div> 
		<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

    
</div>