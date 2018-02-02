<div ng-controller="GestioneSezioneCtrl as gestioneRootSezioneCtrl">


 <jsp:include page="00-sezione-00-title.jsp"></jsp:include>

<div class="content" ng-controller="GestioneSezione-01IndiceCtrl as gestioneSezioneCtrl" >
 <fieldset>			
  <div  class="block-info block-dark"   >
 		<h3>Inserimento dell'immagine di copertina   </h3>
		<form  enctype="multipart/form-data" method="post" action="" >
	  		 <label ng-show = "modelGestioneSezione01Indice.form.allegato.suDB=='FALSE'"    for="upload"   >SFOGLIA</label>   
	  		 <label ng-show = "modelGestioneSezione01Indice.form.allegato.suDB=='TRUE'"  ng-click= "gestioneSezioneCtrl.scarica()"   for= "inform" >SCARICA</label>
	  		 <label ng-show = "modelGestioneSezione01Indice.form.allegato.suDB=='TRUE'"  ng-click= "gestioneSezioneCtrl.cancellaAllegato()"   for= "inform" >CANCELLA</label>
	  		 <label ng-show = "modelGestioneSezione01Indice.form.allegato.nome && modelGestioneSezione01Indice.form.allegato.suDB=='FALSE'"  ng-click= "gestioneSezioneCtrl.allega()"   for= "inform" >ALLEGA</label>
	  		 
	  		 <input data-url="gestitone-ptof/set-sezione-01-indice-allegato.json" bs-watch ="gestioneSezioneCtrl.gestioneAllegato" bs-upload type="file" name="upload" id="upload" aria-label="Carica un allegato PDF" tabindex="20"/>
	  		 
	  		 <input type="hidden" name="keyComponente" value="{{modelGestioneSezione01Indice.form.allegato.key}}"></input>  
	 	</form>	
	  	 <input id= "name_allegato_{{modelGestioneSezione01Indice.form.allegato.key}}" type="text"  ng-model = "modelGestioneSezione01Indice.form.allegato.nome" readonly="readonly" class="form-control" id="usr" /> 
   </div>

     <jsp:include page="00-sezione-00-action.jsp"></jsp:include>

     
   
  </fieldset> 
</div>

</div>