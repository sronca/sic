(function(){
	'use strict';
	
	/* Constants */
	
	angular.module('ptof.constant',[])
	.constant('statoDocumento',{
								IN_COMPILAZIONE: 'IN_COMPILAZIONE',
								CONVALIDATO: 'CONVALIDATO',
								NON_INSERITO :'NON_INSERITO',
								PUBBLICATO_PARZIALEMENTE : 'PUBBLICATO_PARZIALEMENTE',
								PUBBLICATO_COMPLETO : 'PUBBLICATO_COMPLETO'
		
	});
})();	