 	<div class="wrapper-content">
		<p class="title">
		<span ng-if ="modelGestioneSezione.sezione.statoSezione=='COMPILATA'"  class="icon miur-check-title-small"></span>
		<span ng-if ="modelGestioneSezione.sezione.statoSezione!='COMPILATA'"  class="icon miur-setting-gear-title-small"></span> 
		
		{{ modelGestioneSezione.sezione.codice }} {{ modelGestioneSezione.sezione.nome }} 	 </p>
	</div>