<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">


	<jsp:include page="00-sezione-00-title.jsp"></jsp:include>

	<div class="content" ng-controller="GestioneSezione-03StoriaCtrl as gestioneSezioneCtrl">
		 
		<fieldset>

         
		  <div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  class="block-info block-dark"  ng-if = "'ALLEGATO'==componente.tipoComponente" >
		 		<h3>  {{componente.nome}}  {{componente.descrizione}}  </h3>
				<form  enctype="multipart/form-data" method="post" action="" >
			  		 <label ng-show = "!componente.file"    for="upload"   >SFOGLIA</label>   
			  		 <label ng-show = "componente.file"  ng-click= "gestioneSezioneCtrl.scarica()"   for= "inform" >SCARICA</label>
			  		 <label ng-show = "componente.file"  ng-click= "gestioneSezioneCtrl.cancellaAllegato(componente.key)"   for= "inform" >CANCELLA</label>
			  		 <label ng-show = "!componente.file && modelGestioneSezione.form.componenti[$index].file.fileName"  ng-click= "gestioneSezioneCtrl.allega()"   for= "inform" >ALLEGA</label>
			  		 
			  		 <input bs-key= "componente.key" data-url="gestitone-ptof/set-sezione-01-indice-allegato.json" bs-watch ="gestioneSezioneCtrl.gestioneAllegato" bs-upload type="file" name="upload" id="upload" aria-label="Carica un allegato PDF" tabindex="20"/>
			  		 <input type="hidden" name="keyComponente" value="{{componente.key}}"></input>  
			 	</form>	
			  	 <input id= "name_allegato_{{componente.key}}" type="text"  ng-model = "modelGestioneSezione.form.componenti[$index].file.fileName" readonly="readonly" class="form-control" id="usr" /> 
		   </div>
		
			<div ng-repeat="componente in modelGestioneSezione.sezione.componenti"  class="block-info block-dark"  ng-if = "'TEXTEDITOR'==componente.tipoComponente"  class="block-info-noborder" > 	
	 		 <textarea  ui-tinymce="tinymceOptions" ng-model = "componente.valore"   rows="10" cols="80" aria-label="Descrizione" tabindex="10">  </textarea>
			</div> 

			<jsp:include page="00-sezione-00-action.jsp"></jsp:include>
		
		</fieldset>
		
	</div>

    
</div>