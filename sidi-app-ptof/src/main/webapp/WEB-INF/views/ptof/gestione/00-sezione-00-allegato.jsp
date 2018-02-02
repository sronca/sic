
	
<div  class="block-info block-dark"  ng-if = "'ALLEGATO'==componente.tipoComponente" >
		 		<h3>  {{componente.nome}}  {{componente.descrizione}}  </h3>
				<form  enctype="multipart/form-data" method="post" action="" >
			  		 <label ng-show = "!componente.file"    for="upload"   >SFOGLIA</label>   
			  		 <label ng-show = "componente.file"  ng-click= "gestioneRootSezioneCtrl.scarica(componente.key)"   for= "inform" >SCARICA</label>
			  		 <label ng-show = "componente.file"  ng-click= "gestioneRootSezioneCtrl.cancellaAllegato(componente.key)"   for= "inform" >CANCELLA</label>
			  		 <label ng-show = "!componente.file && modelGestioneSezione.form.componenti[$index].file.fileName"  ng-click= "gestioneRootSezioneCtrl.allega()"   for= "inform" >ALLEGA</label>
			  		 <input bs-key= "componente.key" data-url="gestitone-ptof/set-sezione-01-indice-allegato.json" bs-watch ="gestioneRootSezioneCtrl.gestioneAllegato" bs-upload type="file" name="upload" id="upload" aria-label="Carica un allegato PDF" tabindex="20"/>
			  		 <input type="hidden" name="keyComponente" value="{{componente.key}}"></input>  
			 	</form>	
			  	 <input id= "name_allegato_{{componente.key}}" type="text"  ng-model = "modelGestioneSezione.form.componenti[$index].file.fileName" readonly="readonly" class="form-control" id="usr" /> 
			  	 
</div>