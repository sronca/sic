<div class="row">
 
	 <div class="col-xs-2 col-md-3 text-left">
	  <input ng-click= "gestioneRootSezioneCtrl.annulla()" class="send" type="submit" value="INDIETRO" aria-label="Annulla il form" tabindex="70">
	 </div>
	 
	 <div class="col-xs-10 col-md-9  text-right">
	  <input ng-if ="modelGestioneSezione.sezione.cancellabile" ng-click= "gestioneRootSezioneCtrl.cancella()" class="send" type="submit" value="CANCELLA" aria-label="Cancella il form" tabindex="70">
	  <input ng-if ="modelGestioneSezione.sezione.modificabile" ng-click= "gestioneSezioneCtrl.salva()" class="send" type="submit" value="SALVA" aria-label="Invia il form" tabindex="70">
	</div>
</div>