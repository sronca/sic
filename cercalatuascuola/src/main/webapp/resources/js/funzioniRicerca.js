var scuoleSelezionate = [];
var checkRicercaAvanzataSelezionate = [];
var parentAccordion;
var parentAccordionAvanzata;
var nestedAccordionAvanzata;
var nestedAccordionAvanzata1;
var LICEI = "Licei";
var TECNICI = "Tecnici";
var PROFESSIONALI = "Professionali_-_Diploma_quinquennale";
var PROFESSIONALI_PQ = "Professionali_-_Diploma_quinquennale_+_qualifica_IeFP";
var PROFESSIONALI_Q3 = "Professionali_-_Percorso_triennale_(solo_qualifica_IeFP)";
var PROFESSIONALI_Q4 = "Professionali_-_Percorso_quadriennale_(diploma_IeFP)";

function resettaOrdine() 
{	
	$("#codiceOrdine").val("");
	$("#tipologiaStataleNuovoOrdinamento_container").fadeOut("fast");
	$("#tipologiaStataleNuovoOrdinamento_container").val("");
	$("#codiceTipologiaStataleNuovoOrdinamento").fadeOut("fast");
	$("#codiceTipologiaStataleNuovoOrdinamento").val("");	
	//$("#tipologiaNonStatale_container").fadeOut("fast");
	//$("#tipologiaNonStatale_container").val("");
	$("#indirizzoStudio_container").fadeOut("fast");
	$("#indirizzoStudio_container").val("");
	
	//tempi scuole
	$("#tempiScuolaScuolaPrimaria_container").fadeOut("fast");
	$("#tempiScuolaScuolaPrimaria_container").val("");
	$("#tempiScuolaScuolaSecondaria1Grado_container").fadeOut("fast");
	$("#tempiScuolaScuolaSecondaria1Grado_container").val("");
	$("#indirizzoMusicale_container").fadeOut("fast");
	$("#indirizzoMusicale_container").val("");
	$("#settoreScuola_container").fadeOut("fast");
	$("#settoreScuola_container").val("");
	$("#biennio_triennio_container").fadeOut("fast");
	
	$("#tipologia_row").fadeOut("fast");	
}		

function caricaTipologia(codiceOrdine,tipo) 
{    	
	if(codiceOrdine == "4")
	{
		$("#tempiScuolaScuolaSecondaria1Grado_container" + tipo).fadeOut("fast");
		$("#codiceTempoSecondaria1Grado" + tipo).val("");
		$("#tempiScuolaScuolaSecondaria1Grado_container" + tipo).val("");
		$("#tempiScuolaScuolaPrimaria_container" + tipo).fadeOut("fast");
		$("#codiceTempoPrimaria" + tipo).val("");
		$("#tempiScuolaScuolaPrimaria_container" + tipo).val("");
		$("#indirizzoMusicale_container" + tipo).fadeOut("fast");
		$("#indirizzoMusicale_container" + tipo).val("");
		$("#biennio_triennio_container" + tipo).fadeOut("fast");
		
		var i=0;
		var stataleNonStatale="";
		
		$("#tipoScuola_container" + tipo).fadeIn("fast");
		$("#codiceMecc_container" + tipo).fadeIn("fast");
		$("#codiceMecc_row" + tipo).fadeIn("fast");
		
//		$('.radioTipoScuola' + tipo + ':radio').each(function()
//				{					
//					$(this).css("display" , "block");
//					i++;
//		});
		
		
//		stataleNonStatale = $('.radioTipoScuola' + tipo + ':checked').val();
		stataleNonStatale = "Statale";
		
//		$("#checkStatale" + tipo).css("display" , "none");
//		$("#checkStatale" + tipo).attr('checked', false);
//		$("#checkNonStatale" + tipo).css("display" , "none");
//		$("#checkNonStatale" + tipo).attr('checked', false);

		if(stataleNonStatale == "Statale")
		{			
			$("#tipologiaNonStatale_container" + tipo).fadeOut("fast");
			$("#tipologiaNonStatale_container" + tipo).val("");					
			$("#tipologiaStataleNuovoOrdinamento_container" + tipo).fadeIn("slow");				
			$.getJSON('/cercalatuascuola/caricaTipologia.json?codiceOrdine=' + codiceOrdine + '&stataleNonStatale=' + stataleNonStatale,function(data){			
				$("#codiceTipologiaStataleNuovoOrdinamento" + tipo).empty();
				$("#codiceTipologiaStataleNuovoOrdinamento" + tipo).append($('<option value="">Seleziona</option>'));
				$.each(data,function(key,value){
					$("#codiceTipologiaStataleNuovoOrdinamento" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
				});		
			});
		}
		else
		{
			$("#tipologiaStataleNuovoOrdinamento_container" + tipo).fadeOut("fast");
			$("#tipologiaStataleNuovoOrdinamento_container" + tipo).val("");
			$("#indirizzoStudio_container" + tipo).fadeOut("fast");
			$("#codiceIndirizzo" + tipo).val("");
			$("#indirizzoStudio_container" + tipo).val("");
			$("#settoreScuola_container" + tipo).fadeOut("fast");
			$("#codiceSettoreScuola" + tipo).val("");
			$("#settoreScuola_container" + tipo).val("");
			
			$("#tipologiaNonStatale_container" + tipo).fadeIn("slow");
			$.getJSON('/cercalatuascuola/caricaTipologia.json?codiceOrdine=' + codiceOrdine + '&stataleNonStatale=' + stataleNonStatale,function(data){			
				$("#codiceTipologiaNonStatale" + tipo).empty();
				$("#codiceTipologiaNonStatale" + tipo).append($('<option value="">Seleziona</option>'));
				$.each(data,function(key,value){
					$("#codiceTipologiaNonStatale" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
				});		
			});
		}
		
//		$("#tipologia_row" + tipo).fadeIn("slow");
		
		$("#codiceCFP_container" + tipo).fadeOut("fast");
		$("#settoreCFP_container" + tipo).fadeOut("fast");
		$("#indirizzoCFP_container" + tipo).fadeOut("fast");
	}
	else if(codiceOrdine == "5")
	{
	    
		$("#tipoScuola_container" + tipo).fadeOut("fast");
		$("#tipoScuola_container" + tipo).val("");
				
		$("#codiceMecc_container" + tipo).fadeOut("fast");
		$("#codiceMecc_container" + tipo).val("");
		$("#codiceMecc_row" + tipo).fadeOut("fast");
				
		$("#tipologiaStataleNuovoOrdinamento_container" + tipo).fadeOut("fast");
		$("#tipologiaStataleNuovoOrdinamento_container" + tipo).val("");
		
		//$("#tipologiaNonStatale_container" + tipo).fadeOut("fast");
		//$("#tipologiaNonStatale_container" + tipo).val("");		
		//$("#tipologia_row" + tipo).fadeOut("fast");
		
		$("#tempiScuolaScuolaSecondaria1Grado_container" + tipo).fadeOut("fast");
		$("#codiceTempoSecondaria1Grado" + tipo).val("");
		$("#tempiScuolaScuolaSecondaria1Grado_container" + tipo).val("");
		$("#tempiScuolaScuolaPrimaria_container" + tipo).fadeOut("fast");
		$("#codiceTempoPrimaria" + tipo).val("");
		$("#tempiScuolaScuolaPrimaria_container" + tipo).val("");
		$("#indirizzoMusicale_container" + tipo).fadeOut("fast");
		$("#indirizzoMusicale_container" + tipo).val("");
		$("#indirizzoStudio_container" + tipo).fadeOut("fast");
		$("#codiceIndirizzo" + tipo).val("");
		$("#indirizzoStudio_container" + tipo).val("");
		$("#settoreScuola_container" + tipo).fadeOut("fast");
		$("#codiceSettoreScuola" + tipo).val("");
		$("#settoreScuola_container" + tipo).val("");
		$("#biennio_triennio_container" + tipo).fadeOut("fast");
		
		$("#codiceCFP_container" + tipo).fadeIn("fast");
		$("#settoreCFP_container" + tipo).fadeOut("fast");
		$("#indirizzoCFP_container" + tipo).fadeOut("fast");
		
		//$("#codiceCFP" + tipo).load("/cercalatuascuola/jsp/common/caricaTipologia.jsp?codiceOrdine=" + codiceOrdine);
		$.getJSON('/cercalatuascuola/caricaTipologia.json?codiceOrdine=' + codiceOrdine,function(data){			
			$("#codiceCFPPercorso" + tipo).empty();
			$("#codiceCFPPercorso" + tipo).append($('<option value="">Seleziona</option>'));
			$.each(data,function(key,value){
				$("#codiceCFPPercorso" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
		
		
	}
	//tempi scuola primaria
	else if(codiceOrdine == "2")
	{	  				
		$("#tempiScuolaScuolaPrimaria_container" + tipo).fadeIn("fast");
		//$("#codiceTempoPrimaria" + tipo).load("/cercalatuascuola/jsp/common/caricaTempiScuolaPrimaria.jsp?codiceOrdine=" + codiceOrdine);
		$.getJSON('/cercalatuascuola/caricaTempiScuolaPrimaria.json',function(data){			
			$("#codiceTempoPrimaria" + tipo).empty();
			$("#codiceTempoPrimaria" + tipo).append($('<option value="">Seleziona</option>'));
			$.each(data,function(key,value){
				$("#codiceTempoPrimaria" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
		$("#tempiScuolaScuolaSecondaria1Grado_container" + tipo).fadeOut("fast");
		$("#codiceTempoSecondaria1Grado" + tipo).val("");
		$("#tempiScuolaScuolaSecondaria1Grado_container" + tipo).val("");
		$("#indirizzoStudio_container" + tipo).fadeOut("fast");
		$("#codiceIndirizzo" + tipo).val("");
		$("#indirizzoStudio_container" + tipo).val("");
		//$("#tipologiaNonStatale_container" + tipo).fadeOut("fast");
		//$("#tipologiaNonStatale_container" + tipo).val("");						
		$("#tipologiaStataleNuovoOrdinamento_container" + tipo).fadeOut("fast");	
		$("#tipologiaStataleNuovoOrdinamento_container" + tipo).val("");
		$("#indirizzoMusicale_container" + tipo).fadeOut("fast");
		$("#indirizzoMusicale_container" + tipo).val("");
		$("#settoreScuola_container" + tipo).fadeOut("fast");
		$("#codiceSettoreScuola" + tipo).val("");
		$("#settoreScuola_container" + tipo).val("");
		$("#biennio_triennio_container" + tipo).fadeOut("fast");
		
		$("#codiceMecc_container" + tipo).fadeIn("fast");
		$("#codiceMecc_row" + tipo).fadeIn("fast");
		
		$("#tipologia_row" + tipo).fadeOut("fast");
		
		//checkbox statale paritaria
		$("#tipoScuola_container" + tipo).fadeIn("fast");	
//		$('.radioTipoScuola' + tipo + ':radio').each(function(){
//			
//			$(this).css("display" , "none");
//		});
		$("#checkStatale" + tipo).css("display" , "block");
		//$("#checkStatale" + tipo).attr('checked', true);
		$("#checkNonStatale" + tipo).css("display" , "block");
		//$("#checkNonStatale" + tipo).attr('checked', true);
		
		$("#codiceCFP_container" + tipo).fadeOut("fast");
		$("#settoreCFP_container" + tipo).fadeOut("fast");
		$("#indirizzoCFP_container" + tipo).fadeOut("fast");
		
	}
	//tempi scuola secondaria 1 grado
	else if(codiceOrdine == "3")
	{    		
		$("#tempiScuolaScuolaSecondaria1Grado_container" + tipo).fadeIn("fast");
		//$("#codiceTempoSecondaria1Grado" + tipo).load("/cercalatuascuola/jsp/common/caricaTempiScuolaSecondariaPrimoGrado.jsp?codiceOrdine=" + codiceOrdine);
		$.getJSON('/cercalatuascuola/caricaTempiScuolaSecondariaDiPrimoGrado.json',function(data){			
			$("#codiceTempoSecondaria1Grado" + tipo).empty();
			$("#codiceTempoSecondaria1Grado" + tipo).append($('<option value="">Seleziona</option>'));
			$.each(data,function(key,value){
				$("#codiceTempoSecondaria1Grado" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
		$("#tempiScuolaScuolaPrimaria_container" + tipo).fadeOut("fast");
		$("#codiceTempoPrimaria" + tipo).val("");
		$("#tempiScuolaScuolaPrimaria_container" + tipo).val("");
		$("#indirizzoStudio_container" + tipo).fadeOut("fast");
		$("#codiceIndirizzo" + tipo).val("");
		$("#indirizzoStudio_container" + tipo).val("");
		$("#tipologiaStataleNuovoOrdinamento_container" + tipo).fadeOut("fast");	
		$("#tipologiaStataleNuovoOrdinamento_container" + tipo).val("");
		//$("#tipologiaNonStatale_container" + tipo).fadeOut("fast");	
		//$("#tipologiaNonStatale_container" + tipo).val("");
		$("#indirizzoMusicale_container" + tipo).fadeIn("fast");
		$("#checkIndirizzoMusicale" + tipo).css("display" , "block");
		$("#checkIndirizzoMusicale" + tipo).attr('checked', false);

		$("#settoreScuola_container" + tipo).fadeOut("fast");
		$("#codiceSettoreScuola" + tipo).val("");
		$("#settoreScuola_container" + tipo).val("");
		$("#biennio_triennio_container" + tipo).fadeOut("fast");
		
		$("#codiceMecc_container" + tipo).fadeIn("fast");
		$("#codiceMecc_row" + tipo).fadeIn("fast");
				
		$("#tipologia_row" + tipo).fadeOut("fast");
		
		//checkbox statale paritaria
		$("#tipoScuola_container" + tipo).fadeIn("fast");	
//		$('.radioTipoScuola' + tipo + ':radio').each(function()
//		{					
//			$(this).css("display" , "none");
//		});
		$("#checkStatale" + tipo).css("display" , "block");
		//$("#checkStatale" + tipo).attr('checked', true);
		$("#checkNonStatale" + tipo).css("display" , "block");
		//$("#checkNonStatale" + tipo).attr('checked', true);
		
		$("#codiceCFP_container" + tipo).fadeOut("fast");
		$("#settoreCFP_container" + tipo).fadeOut("fast");
		$("#indirizzoCFP_container" + tipo).fadeOut("fast");
	}
	else
	{
		
		$("#tipoScuola_container" + tipo).fadeIn("fast");
		$("#codiceMecc_container" + tipo).fadeIn("fast");
		$("#codiceMecc_row" + tipo).fadeIn("fast");
		
//		$('.radioTipoScuola' + tipo + ':radio').each(function()
//		{					
//			$(this).css("display" , "none");
//		});
		$("#checkStatale" + tipo).css("display" , "block");
		//$("#checkStatale" + tipo).attr('checked', true);
		$("#checkNonStatale" + tipo).css("display" , "block");
		//$("#checkNonStatale" + tipo).attr('checked', true);
								
		$("#tipologiaStataleNuovoOrdinamento_container" + tipo).fadeOut("fast");
		$("#tipologiaStataleNuovoOrdinamento_container" + tipo).val("");
		
//		$("#tipologiaNonStatale_container" + tipo).fadeOut("fast");
//		$("#tipologiaNonStatale_container" + tipo).val("");		
//		$("#tipologia_row" + tipo).fadeOut("fast");
		
		$("#indirizzoStudio_container" + tipo).fadeOut("fast");
		$("#codiceIndirizzo" + tipo).val("");
		$("#indirizzoStudio_container" + tipo).val("");
		$("#settoreScuola_container" + tipo).fadeOut("fast");
		$("#codiceSettoreScuola" + tipo).val("");
		$("#settoreScuola_container" + tipo).val("");
		$("#indirizzoMusicale_container" + tipo).fadeOut("fast");
		$("#indirizzoMusicale_container" + tipo).val("");
		$("#tempiScuolaScuolaPrimaria_container" + tipo).fadeOut("fast");
		$("#codiceTempoPrimaria" + tipo).val("");
		$("#tempiScuolaScuolaPrimaria_container" + tipo).val("");
		$("#tempiScuolaScuolaSecondaria1Grado_container" + tipo).fadeOut("fast");
		$("#codiceTempoSecondaria1Grado" + tipo).val("");
		$("#tempiScuolaScuolaSecondaria1Grado_container" + tipo).val("");
		$("#biennio_triennio_container" + tipo).fadeOut("fast");	
		
		$("#codiceCFP_container" + tipo).fadeOut("fast");
		$("#settoreCFP_container" + tipo).fadeOut("fast");
		$("#indirizzoCFP_container" + tipo).fadeOut("fast");
	}

	$("#indirizzoStudio_container" + tipo).fadeOut("fast");
	$("#indirizzoStudio_container" + tipo).val("");
}	



function caricaSettoreScuola(codiceIstituto,tipo) 
{	
	var codiceIstitutoCodified = codiceIstituto.replace("+","%2B");
	
	if(codiceIstituto != "")
	{
		if(codiceIstituto == PROFESSIONALI_Q3){
			$("#indirizzo_studio_qualifica" + tipo).html("Qualifica");
			$("#indirizzoStudio_container" + tipo).fadeIn("slow");
			//$("#codiceIndirizzo" + tipo).load("/cercalatuascuola/jsp/common/caricaIndirizzoStudio.jsp?codiceTipologia=" + codiceIstitutoCodified + "&codiceSettore=&biennioTriennio=");
			$.getJSON('/cercalatuascuola/caricaIndirizziDiStudio.json?codiceTipologia=' + codiceIstitutoCodified + '&codiceSettore=&biennioTriennio=',function(data){			
				$("#codiceIndirizzo" + tipo).empty();
				$("#codiceIndirizzo" + tipo).append($('<option value="">Seleziona</option>'));
				$.each(data,function(key,value){
					$("#codiceIndirizzo" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
				});		
			});
			$("#settore_percorso" + tipo).html("Settore/Percorso");
			$("#settoreScuola_container" + tipo).fadeOut("fast");
	    	$("#codiceSettoreScuola" + tipo).val("");
	    	$("#biennio_triennio_container" + tipo).fadeOut("fast");
		}
		else if(codiceIstituto == PROFESSIONALI_Q4){
			$("#indirizzo_studio_qualifica" + tipo).html("Indirizzo di studio");
			$("#indirizzoStudio_container" + tipo).fadeIn("slow");
			//$("#codiceIndirizzo" + tipo).load("/cercalatuascuola/jsp/common/caricaIndirizzoStudio.jsp?codiceTipologia=" + codiceIstitutoCodified + "&codiceSettore=&biennioTriennio=");
			$.getJSON('/cercalatuascuola/caricaIndirizziDiStudio.json?codiceTipologia=' + codiceIstitutoCodified + '&codiceSettore=&biennioTriennio=',function(data){			
				$("#codiceIndirizzo" + tipo).empty();
				$("#codiceIndirizzo" + tipo).append($('<option value="">Seleziona</option>'));
				$.each(data,function(key,value){
					$("#codiceIndirizzo" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
				});		
			});
			$("#settore_percorso" + tipo).html("Settore/Percorso");
			$("#settoreScuola_container" + tipo).fadeOut("fast");
	    	$("#codiceSettoreScuola" + tipo).val("");
	    	$("#biennio_triennio_container" + tipo).fadeOut("fast");
		}
		else{
			if(codiceIstituto == LICEI){
				$("#settore_percorso" + tipo).html("Percorso");
				$("#biennio_triennio_container" + tipo).fadeOut("fast");
			}
			else if(codiceIstituto == PROFESSIONALI_PQ){
				$("#settore_percorso" + tipo).html("Indirizzo di studio");
				$("#biennio_triennio_container" + tipo).fadeOut("fast");
			}
			//infine per IP e IT
			else{
				$("#settore_percorso" + tipo).html("Settore");
				$("#biennio_triennio_container" + tipo).fadeIn("slow");
			}
			$("#settoreScuola_container" + tipo).fadeIn("slow");
	    	//$("#codiceSettoreScuola" + tipo).load("/cercalatuascuola/jsp/common/caricaSettoreScuola.jsp?codiceIstituto=" + codiceIstitutoCodified);
			$.getJSON('/cercalatuascuola/caricaSettoreScuola.json?codiceIstituto=' + codiceIstitutoCodified,function(data){			
				$("#codiceSettoreScuola" + tipo).empty();
				$("#codiceSettoreScuola" + tipo).append($('<option value="">Seleziona</option>'));
				$.each(data,function(key,value){
					$("#codiceSettoreScuola" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
				});		
			});
	    	$("#indirizzoStudio_container" + tipo).fadeOut("fast");
	    	$("#indirizzoStudio_container" + tipo).val("");
	    	$("#codiceIndirizzo" + tipo).val("");
		}
	}
	else
		{
		$("#settore_percorso" + tipo).html("Settore/Percorso");
		$("#settoreScuola_container" + tipo).fadeOut("fast");
		$("#settoreScuola_container" + tipo).val("");
		$("#codiceSettoreScuola" + tipo).val("");
		$("#indirizzo_studio_qualifica" + tipo).html("Indirizzo di studio");
		$("#indirizzoStudio_container" + tipo).fadeOut("fast");
		$("#indirizzoStudio_container" + tipo).val("");
		$("#codiceIndirizzo" + tipo).val("");
		$("#biennio_triennio_container" + tipo).fadeOut("fast");
	}
	
}


function caricaIndirizzoStudio(tipo) 
{	
    var codiceTipologiaCodified = $("#codiceTipologiaStataleNuovoOrdinamento" + tipo).val().replace("+","%2B");
    	
	var codiceTipologia = "";
	var codiceSettore = "";
	if($("#codiceTipologiaStataleNuovoOrdinamento" + tipo).val() != null && $("#codiceTipologiaStataleNuovoOrdinamento" + tipo).val() != "")
		codiceTipologia = $("#codiceTipologiaStataleNuovoOrdinamento" + tipo).val();
	if($("#codiceSettoreScuola" + tipo).val() != null && $("#codiceSettoreScuola" + tipo).val() != "")
		var codiceSettore = $("#codiceSettoreScuola" + tipo).val();
	
	var biennioTriennio = "";
	if(codiceTipologia == TECNICI || 
			codiceTipologia == PROFESSIONALI ||
			(codiceTipologia == LICEI && codiceSettore == "ARTISTICO")){
		
		$("#biennio_triennio_container" + tipo).fadeIn("slow");
//		if($("input[name='radioBiennioTriennio" + tipo +"']:checked").val() == "Biennio"){
//			biennioTriennio = "Biennio";
//		}
//		else if($("input[name='radioBiennioTriennio" + tipo +"']:checked").val() == "Triennio"){
//			biennioTriennio = "Triennio";
//		}
		if($('.radioBiennioTriennio' + tipo +':checked').val() == 'Biennio'){
			biennioTriennio = "Biennio";
		}
		else if($('.radioBiennioTriennio' + tipo +':checked').val() == "Triennio"){
			biennioTriennio = "Triennio";
		}
	}
	else{
		$("#biennio_triennio_container" + tipo).fadeOut("fast");
	}
	
	if(codiceTipologia != "")
	{
		if(codiceTipologia == PROFESSIONALI_Q3 || codiceTipologia == PROFESSIONALI_PQ){
			$("#indirizzo_studio_qualifica" + tipo).html("Qualifica");
		}
		else{
			$("#indirizzo_studio_qualifica" + tipo).html("Indirizzo di studio");
		}	
		
		if(codiceTipologia == PROFESSIONALI_Q3 || codiceTipologia == PROFESSIONALI_Q4 || codiceSettore != ""){
	    	$("#indirizzoStudio_container" + tipo).fadeIn("slow");
	    	//$("#codiceIndirizzo" + tipo).load("/cercalatuascuola/jsp/common/caricaIndirizzoStudio.jsp?codiceTipologia=" + codiceTipologiaCodified + "&codiceSettore=" + codiceSettore + "&biennioTriennio=" + biennioTriennio);
			$.getJSON('/cercalatuascuola/caricaIndirizziDiStudio.json?codiceTipologia=' + codiceTipologiaCodified + '&codiceSettore=' + codiceSettore + '&biennioTriennio=' + biennioTriennio,function(data){			
				$("#codiceIndirizzo" + tipo).empty();
				$("#codiceIndirizzo" + tipo).append($('<option value="">Seleziona</option>'));
				$.each(data,function(key,value){
					$("#codiceIndirizzo" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
				});		
			});
		}
		else{
			$("#indirizzo_studio_qualifica" + tipo).html("Indirizzo di studio");
			$("#indirizzoStudio_container" + tipo).fadeOut("fast");
			$("#indirizzoStudio_container" + tipo).val("");
			$("#codiceIndirizzo" + tipo).val("");
		}
	}
	else
	{
		$("#indirizzo_studio_qualifica" + tipo).html("Indirizzo di studio");
		$("#indirizzoStudio_container" + tipo).fadeOut("fast");
		$("#indirizzoStudio_container" + tipo).val("");
		$("#codiceIndirizzo" + tipo).val("");
	}
}

function caricaCFP(codiceIstituto,tipo) 
{	
	var codiceIstitutoCodified = codiceIstituto.replace("+","%2B");
	if(codiceIstituto != "")
	{
		$("#settoreCFP_container" + tipo).fadeIn("slow");
		//$("#codiceCFPSettore" + tipo).load("/cercalatuascuola/jsp/common/caricaSettoreCFP.jsp?codiceIstituto=" + codiceIstitutoCodified);
		$.getJSON('/cercalatuascuola/caricaSettoreCFP.json?codiceIstituto=' + codiceIstitutoCodified,function(data){			
			$("#codiceCFPSettore" + tipo).empty();
			$("#codiceCFPSettore" + tipo).append($('<option value="">Seleziona</option>'));
			$.each(data,function(key,value){
				$("#codiceCFPSettore" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
		$("#indirizzoCFP_container" + tipo).fadeOut("fast");
		$("#codiceCFPIndirizzo" + tipo).val("");
	}else{
		$("#settoreCFP_container" + tipo).fadeOut("fast");
		$("#settoreCFP_container" + tipo).val("");
		$("#indirizzoCFP_container" + tipo).fadeOut("fast");
		$("#codiceCFPIndirizzo" + tipo).val("");
	}
}


function caricaCFPIndirizzoStudio(tipo) 
{	
    var codiceTipologiaCodified = $("#codiceCFPSettore" + tipo).val().replace("+","%2B");	
	if(codiceTipologiaCodified != "")
	{
		$("#indirizzoCFP_container" + tipo).fadeIn("slow");
		//$("#codiceCFPIndirizzo" + tipo).load("/cercalatuascuola/jsp/common/caricaIndirizzoCFP.jsp?codiceTipologia=" + codiceTipologiaCodified);
		$.getJSON('/cercalatuascuola/caricaIndirizzoCFP.json?codiceTipologia=' + codiceTipologiaCodified,function(data){			
			$("#codiceCFPIndirizzo" + tipo).empty();
			$("#codiceCFPIndirizzo" + tipo).append($('<option value="">Seleziona</option>'));
			$.each(data,function(key,value){
				$("#codiceCFPIndirizzo" + tipo).append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
	}else{
		$("#indirizzoCFP_container" + tipo).fadeOut("fast");
		$("#codiceCFPIndirizzo" + tipo).val("");
	}
}


function caricaProvincia(codiceRegione) 
{	
	if(codiceRegione != "" && codiceRegione.substring(0,codiceRegione.indexOf('_')) != -1)
	{	
		//$("#provincia_container").fadeIn("slow");
		$.getJSON('/cercalatuascuola/caricaProvincia.json?codiceRegione=' + codiceRegione.substring(0,codiceRegione.indexOf('_')),function(data){			
			$("#codiceProvincia").empty();
			$.each(data,function(key,value){
				$("#codiceProvincia").append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
	}
	else
	{
		//$("#provincia_container").fadeOut("fast");
		//$("#provincia_container").val("");
		$("#codiceProvincia").empty();
		$("#codiceProvincia").val("");
	}

	//$("#comune_container").fadeOut("fast");
	//$("#comune_container").val("");
	$("#codiceComune").empty();
	$("#codiceComune").val("");
}

function caricaComune(codiceProvincia) 
{	
	if(codiceProvincia != "" && codiceProvincia.substring(0,codiceProvincia.indexOf('_')) != -1)
	{
		//$("#comune_container").fadeIn("slow");
		$.getJSON('/cercalatuascuola/caricaComune.json?codiceProvincia=' + codiceProvincia.substring(0,codiceProvincia.indexOf('_')),function(data){			
			$("#codiceComune").empty();
			$.each(data,function(key,value){
				$("#codiceComune").append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
	}
	else
	{
		//$("#comune_container").fadeOut("fast");
		//$("#comune_container").val("");
		$("#codiceComune").empty();
		$("#codiceComune").val("");
	}
}

function caricaCheckAvanzata() 
{
	$.ajax({ url: "/cercalatuascuola/jsp/common/caricaCheckAvanzata.jsp", async: false, 
	beforeSend: function ()
	{
		TINYB.box.show(0,0,"",0,'/cercalatuascuola/jsp/popup/popupElaborazione.jsp' ,1,300,127,1);
	},
	success: function(data)
	{
		$("#sceltaCheckAvanzata").html(data);

		TINYB.box.hide();
		
    	if(document.getElementById("erroreCaricaCheckAvanzata") != null && document.getElementById("erroreCaricaCheckAvanzata").value != "")
    	{
    		TINYB.box.show(1,0,"",0,'/cercalatuascuola/jsp/popup/popupErrore.jsp?messaggio=' + document.getElementById("erroreCaricaCheckAvanzata").value,1,350,170,1);
    	}
    	else
    	{
    		parentAccordion=new TINY.accordion.slider("parentAccordion");
    		parentAccordion.init("acc","h3",true,0);

    		parentAccordionAvanzata=new TINY.accordion.slider("parentAccordionAvanzata");
    		parentAccordionAvanzata.init("accAvanzata","h3",false,-1);
    		
    		nestedAccordionAvanzata=new TINY.accordion.slider("nestedAccordionAvanzata");
    		nestedAccordionAvanzata.init("nestedAvanzata","h3",true,-1,"acc-selected");
    		
    		nestedAccordionAvanzata1=new TINY.accordion.slider("nestedAccordionAvanzata1");
    		nestedAccordionAvanzata1.init("nestedAvanzata1","h3",true,-1,"acc-selected");
    	}
    }});

	$("erroreCaricaCheckAvanzata").remove();
}

function selezioneCheckRicercaAvanzata()
{
	$("#sceltaCheckAvanzata").find('input:checkbox').each(function()
	{ 
		if($(this).attr("checked") == true)
			checkRicercaAvanzataSelezionate.push($(this).attr("id"));
	});
	
	document.getElementById("passaggioCheckRicercaAvanzata").value = checkRicercaAvanzataSelezionate;
}

function ricercaScuoleProssimita() {
	geocode(true);
}

/*
function ricercaScuole(tipoRicerca) 
{
	// reset pagina
	
	$("#popupSelezioneScuole").css({display: "none"});
	$("#quinta8").css({display: "none"});
	$("#criteriRicerca").css({display: "none"});
	$("#seleziona_apri").css({display: "none"});
	
	if (tipoRicerca == "prossimita" && $("#raggio").val() == "" ) {
		tipoRicerca = "diretta";
	}
	
	
	
	if (tipoRicerca == "avanzata" ){
		impostaMappaAvanzata();
	}else{
		if (tipoRicerca == "prossimita") {
			impostaMappaProssimita();
		}else{
			if(tipoRicerca == "diretta") {
				impostaMappaDiretta();
			}else{
				if(tipoRicerca == "rapida") {
					if ( $("#rapida").val().length < 3 || $("#rapida").val() == "Ricerca rapida" ) {
						alert("Inserire almeno 3 caratteri");
						return false;
					}
					impostaMappaDiretta();
				}else {	
					impostaMappaAvanzata();
				}
			}
		}
	}
	// end reset
	var parametriRicerca = "";
	var checkStatale = "N";
	var checkNonStatale = "N";
	var codiceTipologia = "";
	scuoleSelezionate = [];
	checkRicercaAvanzataSelezionate = [];
	var tempoScuolaPrimaria = "";
	var tempoScuolaMedia = "";
	var checkIndirizzoMusicale = "N";
	var biennioTriennio = "";
	var codiceIndirizzoDescrizione = "";
	
	var codiceCFPPercorso="";
	var codiceCFPSettore="";
	var codiceCFPIndirizzo="";
	
	

	if(tipoRicerca == "prossimita")
	{
		if($("#codiceOrdine").val() == "5"){
			if($("#codiceCFPPercorso option:selected") != null && $("#codiceCFPPercorso option:selected") != "undefined" && $("#codiceCFPPercorso option:selected") !=""){
				codiceCFPPercorso = $("#codiceCFPPercorso option:selected").val();
			}
			if($("#codiceCFPSettore option:selected") != null && $("#codiceCFPSettore option:selected") != "undefined" && $("#codiceCFPSettore option:selected") !=""){
				codiceCFPSettore = $("#codiceCFPSettore option:selected").val();
			}
			if($("#codiceCFPIndirizzo option:selected") != null && $("#codiceCFPIndirizzo option:selected") != "undefined" && $("#codiceCFPIndirizzo option:selected") !=""){
				codiceCFPIndirizzo = $("#codiceCFPIndirizzo option:selected").val();
			}	
		}
		
		if($("#codiceIndirizzo option:selected") != null && $("#codiceIndirizzo option:selected") != "undefined" && $("#codiceIndirizzo option:selected") !=""){
			codiceIndirizzoDescrizione = $("#codiceIndirizzo option:selected").text();
		}
		
		if($("#codiceOrdine").val() == "2")
		{
			tempoScuolaPrimaria = $("#codiceTempoPrimaria").val();
		}
		
		if($("#codiceOrdine").val() == "3")
		{
	    	if(document.forms[0].checkIndirizzoMusicale.checked == true){
	    		checkIndirizzoMusicale = "S";
	    	}
	    	tempoScuolaMedia = $("#codiceTempoSecondaria1Grado").val();
		}
		
		
		if($("#codiceOrdine").val() == "4")
		{
			if($("#codiceTipologiaStataleNuovoOrdinamento").val() == TECNICI || 
					$("#codiceTipologiaStataleNuovoOrdinamento").val() == PROFESSIONALI ||
					($("#codiceTipologiaStataleNuovoOrdinamento").val() == LICEI && $("#codiceSettoreScuola").val() == "ARTISTICO")){
				
				if($("input[name='radioBiennioTriennio']:checked").val() == "Biennio"){
					biennioTriennio = "Biennio";
				}
				else if($("input[name='radioBiennioTriennio']:checked").val() == "Triennio"){
					biennioTriennio = "Triennio";
				}
			}
		}
		
		if($("#codiceOrdine").val() != "4")
		{
			if(document.forms[0].checkStatale.checked == true)
				checkStatale = "S";

			if(document.forms[0].checkNonStatale.checked == true)
				checkNonStatale = "S";
		}
		else
		{
			var i=0;
			if ($('input[name=radioTipoScuola]:checked').val() == "Statale") {
				checkStatale = "S";
				codiceTipologia = $("#codiceTipologiaStataleNuovoOrdinamento").val();
			}else{
				checkNonStatale = "S";
				codiceTipologia = $("#codiceTipologiaNonStatale").val();
			}
		}
		if($("#codiceOrdine").val() != "4")
		{
			if(document.forms[0].checkStatale.checked == true)
				checkStatale = "S";

			if(document.forms[0].checkNonStatale.checked == true)
				checkNonStatale = "S";
		}
		else
		{
			var i=0;
			if(document.forms[0].checkStatale.checked == true) {
				checkStatale = "S";
				codiceTipologia = $("#codiceTipologiaStataleNuovoOrdinamento").val();
			}else{
				checkNonStatale = "S";
				codiceTipologia = $("#codiceTipologiaStataleNuovoOrdinamento").val();
			}
		}		
		
		//aggiunto per gestire le tipologie con caratteri riservati in querystring
		//(es. il carattere + in Professionali - Diploma quinquennale + qualifica IeFP)
		var codiceTipologiaCodified = codiceTipologia.replace("+","%2B");
		
		var codReg = document.getElementById("codiceRegione").value;
		
		if(codReg.indexOf("_") != -1){
			codReg = codReg.substring(codReg.indexOf("_")+1);
		}
		
		var codPrv = document.getElementById("codiceProvincia").value;
		
		if(codPrv.indexOf("_") != -1){
			codPrv = codPrv.substring(codPrv.indexOf("_")+1);
		}
		
		var codCom = document.getElementById("codiceComune").value;
		
		if(codCom.indexOf("_") != -1){
			codCom = codCom.substring(codCom.indexOf("_")+1);
		}
		
		var indirizzo = document.getElementById("indirizzoRiferimento").value; 
						//+ ", "+codReg
						//+ ", "+codPrv
						//+ ", "+codCom
						//+ ", "+document.getElementById("cap").value
						//+ ", italia";	
		
		parametriRicerca = "&codiceRegione=" + "" +
					   	   "&codiceProvincia=" + "" +
					   	   "&codiceComune=" + "" +
					   	   "&indirizzoRiferimento=" + indirizzo +
						   "&raggio=" + document.forms[0].raggio.value + 
						   "&codiceOrdine=" + $("#codiceOrdine").val() +
						   "&checkStatale=" + checkStatale + 
						   "&checkNonStatale=" + checkNonStatale +					
						   "&codiceTipologia=" + codiceTipologiaCodified +
						   "&codiceSettore=" + $("#codiceSettoreScuola").val() +
						   "&codiceIndirizzo=" + $("#codiceIndirizzo").val() + 	 
						   "&codiceIndirizzoDescrizione=" + codiceIndirizzoDescrizione +
						   "&tempoScuolaPrimaria=" + tempoScuolaPrimaria + 
						   "&tempoScuolaMedia=" + tempoScuolaMedia +
						   "&checkIndirizzoMusicale=" + checkIndirizzoMusicale +
						   "&biennioTriennio=" + biennioTriennio +	
						   "&coordinateRicercaProssimita=" + document.forms[0].coordinateRicercaProssimita.value +					   
	   				   	   "&tipoRicerca=" + tipoRicerca+	   				   	   
	   				   	   "&codiceCFPPercorso=" + codiceCFPPercorso+
	   				   	   "&codiceCFPSettore=" + codiceCFPSettore+
	   				   	   "&codiceCFPIndirizzo=" + codiceCFPIndirizzo+
	   				   	   "&serviziAggiuntivi=" + document.getElementById("serviziAggiuntivi").value;
		
	}
	else if(tipoRicerca == "diretta")
	{
		
		if($("#codiceOrdine").val() == "5"){
			if($("#codiceCFPPercorso option:selected") != null && $("#codiceCFPPercorso option:selected") != "undefined" && $("#codiceCFPPercorso option:selected") !=""){
				codiceCFPPercorso = $("#codiceCFPPercorso option:selected").val();
			}
			if($("#codiceCFPSettore option:selected") != null && $("#codiceCFPSettore option:selected") != "undefined" && $("#codiceCFPSettore option:selected") !=""){
				codiceCFPSettore = $("#codiceCFPSettore option:selected").val();
			}
			if($("#codiceCFPIndirizzo option:selected") != null && $("#codiceCFPIndirizzo option:selected") != "undefined" && $("#codiceCFPIndirizzo option:selected") !=""){
				codiceCFPIndirizzo = $("#codiceCFPIndirizzo option:selected").val();
			}	
		}

		
		if($("#codiceIndirizzo option:selected") != null && $("#codiceIndirizzo option:selected") != "undefined" && $("#codiceIndirizzo option:selected") !=""){
			codiceIndirizzoDescrizione = $("#codiceIndirizzo option:selected").text();
		}
		
		if($("#codiceOrdine").val() == "2")
		{
			tempoScuolaPrimaria = $("#codiceTempoPrimaria").val();
		}
		
		if($("#codiceOrdine").val() == "3")
		{
	    	if(document.forms[0].checkIndirizzoMusicale.checked == true){
	    		checkIndirizzoMusicale = "S";
	    	}
	    	tempoScuolaMedia = $("#codiceTempoSecondaria1Grado").val();
		}
		
		
		if($("#codiceOrdine").val() == "4")
		{
			if($("#codiceTipologiaStataleNuovoOrdinamento").val() == TECNICI || 
					$("#codiceTipologiaStataleNuovoOrdinamento").val() == PROFESSIONALI ||
					($("#codiceTipologiaStataleNuovoOrdinamento").val() == LICEI && $("#codiceSettoreScuola").val() == "ARTISTICO")){
				
				if($("input[name='radioBiennioTriennio']:checked").val() == "Biennio"){
					biennioTriennio = "Biennio";
				}
				else if($("input[name='radioBiennioTriennio']:checked").val() == "Triennio"){
					biennioTriennio = "Triennio";
				}
			}
		}
		
		if($("#codiceOrdine").val() != "4")
		{
			if(document.forms[0].checkStatale.checked == true)
				checkStatale = "S";

			if(document.forms[0].checkNonStatale.checked == true)
				checkNonStatale = "S";
		}
		else
		{
			var i=0;
			if(document.forms[0].checkStatale.checked == true) {
				checkStatale = "S";
				codiceTipologia = $("#codiceTipologiaStataleNuovoOrdinamento").val();
			}else{
				checkNonStatale = "S";
				codiceTipologia = $("#codiceTipologiaStataleNuovoOrdinamento").val();
			}
		}	
		
		//aggiunto per gestire le tipologie con caratteri riservati in querystring
		//(es. il carattere + in Professionali - Diploma quinquennale + qualifica IeFP)
		var codiceTipologiaCodified = codiceTipologia.replace("+","%2B");

		
		parametriRicerca = "&codiceOrdine=" + $("#codiceOrdine").val() +
						   "&checkStatale=" + checkStatale + 
						   "&checkNonStatale=" + checkNonStatale +
						   "&codiceTipologia=" + codiceTipologiaCodified +	
						   "&codiceSettore=" + $("#codiceSettoreScuola").val() +
						   "&codiceIndirizzo=" + $("#codiceIndirizzo").val() + 
						   "&codiceIndirizzoDescrizione=" + codiceIndirizzoDescrizione +
						   "&tempoScuolaPrimaria=" + tempoScuolaPrimaria + 
						   "&tempoScuolaMedia=" + tempoScuolaMedia +
						   "&checkIndirizzoMusicale=" + checkIndirizzoMusicale +
						   "&biennioTriennio=" + biennioTriennio +	
						   "&coordinateRicercaDiretta=" + document.forms[0].coordinateRicercaDiretta.value +
	   				   	   "&tipoRicerca=" + tipoRicerca+
	   				   	   "&codiceCFPPercorso=" + codiceCFPPercorso+
	   				   	   "&codiceCFPSettore=" + codiceCFPSettore+
	   				   	   "&codiceCFPIndirizzo=" + codiceCFPIndirizzo+
	   				   	   "&serviziAggiuntivi=" + document.getElementById("serviziAggiuntivi").value;
	}
	else if(tipoRicerca == "rapida")
	{
		parametriRicerca = "&codiceOrdine=0&tipoRicerca=" + tipoRicerca +
							"&ricerca_rapida=" + $("#rapida").val();
	}
	else if(tipoRicerca == "avanzata")
	{
		
		if($("#codiceOrdineAvanzata").val() == "5"){
			if($("codiceCFPPercorsoAvanzata option:selected") != null && $("#codiceCFPPercorsoAvanzata option:selected") != "undefined" && $("#codiceCFPPercorsoAvanzata option:selected") !=""){
				codiceCFPPercorso = $("#codiceCFPPercorsoAvanzata option:selected").val();
			}
			if($("#codiceCFPSettoreAvanzata option:selected") != null && $("#codiceCFPSettoreAvanzata option:selected") != "undefined" && $("#codiceCFPSettoreAvanzata option:selected") !=""){
				codiceCFPSettore = $("#codiceCFPSettoreAvanzata option:selected").val();
			}
			if($("#codiceCFPIndirizzoAvanzata option:selected") != null && $("#codiceCFPIndirizzoAvanzata option:selected") != "undefined" && $("#codiceCFPIndirizzoAvanzata option:selected") !=""){
				codiceCFPIndirizzo = $("#codiceCFPIndirizzoAvanzata option:selected").val();
			}	
		}

		if($("#codiceIndirizzoAvanzata option:selected") != null && $("#codiceIndirizzoAvanzata option:selected") != "undefined" && $("#codiceIndirizzoAvanzata option:selected") !=""){
			codiceIndirizzoDescrizione = $("#codiceIndirizzoAvanzata option:selected").text();
		}
		
		if($("#codiceOrdineAvanzata").val() == "2")
		{
			tempoScuolaPrimaria = $("#codiceTempoPrimariaAvanzata").val();
		}
		
		if($("#codiceOrdineAvanzata").val() == "3")
		{
	    	if(document.forms[0].checkIndirizzoMusicaleAvanzata.checked == true){
	    		checkIndirizzoMusicale = "S";
	    	}
	    	tempoScuolaMedia = $("#codiceTempoSecondaria1GradoAvanzata").val();
		}
		
		
		if($("#codiceOrdineAvanzata").val() == "4")
		{
			if($("#codiceTipologiaStataleNuovoOrdinamentoAvanzata").val() == TECNICI || 
					$("#codiceTipologiaStataleNuovoOrdinamentoAvanzata").val() == PROFESSIONALI ||
					($("#codiceTipologiaStataleNuovoOrdinamentoAvanzata").val() == LICEI && $("#codiceSettoreScuolaAvanzata").val() == "ARTISTICO")){
				
				if($("input[name='radioBiennioTriennioAvanzata']:checked").val() == "Biennio"){
					biennioTriennio = "Biennio";
				}
				else if($("input[name='radioBiennioTriennioAvanzata']:checked").val() == "Triennio"){
					biennioTriennio = "Triennio";
				}
			}
		}
		
		if($("#codiceOrdineAvanzata").val() != "4")
		{
			if(document.forms[0].checkStataleAvanzata.checked == true)
				checkStatale = "S";

			if(document.forms[0].checkNonStataleAvanzata.checked == true)
				checkNonStatale = "S";
		}
		else
		{
			var i=0;
			 
			if ($('input[name=radioTipoScuolaAvanzata]:checked').val() == "Statale") {
				checkStatale = "S";
				codiceTipologia = $("#codiceTipologiaStataleNuovoOrdinamentoAvanzata").val();
			}else{
				checkNonStatale = "S";
				codiceTipologia = $("#codiceTipologiaNonStataleAvanzata").val();
			}
		}
		
		//aggiunto per gestire le tipologie con caratteri riservati in querystring
		//(es. il carattere + in Professionali - Diploma quinquennale + qualifica IeFP)
		var codiceTipologiaCodified = codiceTipologia.replace("+","%2B");
		
		selezioneCheckRicercaAvanzata();
		
		parametriRicerca = "&codiceRegione=" + $("#codiceRegione").val() +
		   				   "&codiceProvincia=" + $("#codiceProvincia").val() +
	   				   	   "&codiceComune=" + $("#codiceComune").val() +
	   				   	   //"&codiceOrdine=" + document.forms[0].codiceOrdineAvanzata.value +
	   				   	   "&codiceOrdine=" + $("#codiceOrdineAvanzata").val() +
	   				   	   "&checkStatale=" + checkStatale + 
	   				   	   "&checkNonStatale=" + checkNonStatale +	   				  
	   				   	   "&codiceTipologia=" + codiceTipologiaCodified +	
	   				       "&codiceSettore=" + $("#codiceSettoreScuolaAvanzata").val() + 
	   				   	   "&codiceIndirizzo=" + $("#codiceIndirizzoAvanzata").val() + 
	   			    	   "&codiceIndirizzoDescrizione=" + codiceIndirizzoDescrizione +
	   			    	   "&tempoScuolaPrimaria=" + tempoScuolaPrimaria + 
						   "&tempoScuolaMedia=" + tempoScuolaMedia +
						   "&checkIndirizzoMusicale=" + checkIndirizzoMusicale +
						   "&biennioTriennio=" + biennioTriennio +	
	   				   	   "&denominazione=" + $("#denominazioneAvanzata").val() + 
	   				   	   "&codMecc=" + $("#codMeccAvanzata").val() +
	   				   	   "&checkRicercaAvanzataSelezionate=" + document.getElementById("passaggioCheckRicercaAvanzata").value +
	   				   	   "&tipoRicerca=" + tipoRicerca+
	   				   	   "&codiceCFPPercorso=" + codiceCFPPercorso+
	   				   	   "&codiceCFPSettore=" + codiceCFPSettore+
	   				   	   "&codiceCFPIndirizzo=" + codiceCFPIndirizzo+	   				   	   
	   				   	   "&serviziAggiuntivi=" + document.getElementById("serviziAggiuntivi").value;
	}

	var currentDate = new Date();
	
	$.ajax({ url: "/cercalatuascuola/jsp/common/ricercaScuole.jsp?IDTIME=" + currentDate.getTime()+parametriRicerca,
		beforeSend: function ()
		{
			$("#content").html("");
			$("#desRegione").remove();
			$("#desProvincia").remove();
			$("#desComune").remove();
			$("#desTipoScuola").remove();
			$("#desParitaria").remove();
			$("#desAnnoAccademico").remove();
			$("#desOrdineScuola").remove();
			$("#desTipologia").remove();
			$("#desSettore").remove();
			$("#desIndirizzo").remove();
			$("#desDenominazione").remove();
			$("#desCodiceMeccanografico").remove();
			$("#desIndirizzoRiferimento").remove();
			$("#desCoordinateIndirizzoRiferimento").remove();
			$("#desCoordinateIndirizzoDiretto").remove();
			$("#desRaggio").remove();
			$("#numeroScuole").remove();
			$("#listaScuoleDaCaricare").remove();

			TINYB.box.show(0,0,"",0,'/cercalatuascuola/jsp/popup/popupElaborazione.jsp' ,1,300,127,1);
		}, 
		success: function(data)
		{
			$("#datiRitornoJQuery").html(data);

			if(document.getElementById("erroreRicerca") != null && document.getElementById("erroreRicerca").value != "")
        	{
				TINYB.box.hide();
				
        		TINYB.box.show(1,0,"",0,'/cercalatuascuola/jsp/popup/popupErrore.jsp?messaggio=' + document.getElementById("erroreRicerca").value,1,350,200,1);

				$("#cav_basso_uno").css("backgroundColor" , "transparent" );
				$("#cav_basso_due").css("visibility" , "hidden");
				$("#cav_basso_tre").css("visibility" , "hidden");
        	}
			else
			{
				//if(document.getElementById("numeroScuole").value > 50)
				if(document.getElementById("numeroScuole").value > 0)
				{
					TINYB.box.hide();
					var currentDate = new Date();
					
					downloadUrl("/cercalatuascuola/jsp/popup/popupSelezioneScuole.jsp?IDTIME=" + currentDate.getTime(),
							function(data, responseCode) {
								$("#popupSelezioneScuole").html(data);
								listaBoxSelezionePage(0,1);
								//window.setTimeout("listaBoxSelezionePage(0,1)",3000);
								$("#popupSelezioneScuole").css({display: "block"});
								
								var left = ($(window).innerWidth() - 488);
								if ($(window).innerWidth() < 1280) {
									left= 1280 - 488;
								}
								//var top = ((document.documentElement.clientHeight - height)/3);
								var top = 97;
								$('#popupSelezioneScuole').css({top:'' + top + 'px',left:'' + left + 'px'});
								// end
								window.setTimeout("aggiornaCriteriRicerca('criteriRicercaSelezioneScuole')",3000);
								
							});
					
										
					//TINYB.box.showSelezione(1,1,'/cercalatuascuola/jsp/popup/popupSelezioneScuole.jsp?IDTIME=' + currentDate.getTime(),1,440,540,1);
					//$("#tinymask").css('display','none');
					//listaBoxSelezionePage(lista_box_selezione_page);
					//window.setTimeout("listaBoxSelezionePage(0,1)",3000);
				}	
				else //MOSTRO LE SCUOLE 
				{
					preparaFinestra(true);
		    		loadMappe();
				}
			}
      }});
	$("erroreRicerca").remove();
}*/


function preparaFinestra(aggiornaCriteri)
{	
	$("#cav_basso_due").css("visibility" , "visible");

	if($("#tipoRicerca").val() == "RicercaProssimita")
	{
		$("#cav_basso_uno").css('backgroundColor' , '#585959' );
		mostra_nascondi_quinte('intorno','risultati');
	}
	else if($("#tipoRicerca").val() == "RicercaDiretta")
	{
		$("#cav_basso_uno").css('backgroundColor' , '#585959' );
		mostra_nascondi_quinte('territorio','risultati');
	}
	else if($("#tipoRicerca").val() == "RicercaAvanzata")
	{
		$("#cav_basso_uno").css('backgroundColor' , '#585959' );
		mostra_nascondi_quinte('avanzata','risultati');
	}	
	
	pulisciAmbiente();
	
	parentAccordion=new TINY.accordion.slider("parentAccordion");
	parentAccordion.init("acc","h3",1,0);
	
	$("#filtroRegioni").val("");
	$("#filtroRegioni").empty();
	$('<option>').val('').text('Tutte').appendTo('select[name=filtroRegioni]');

	$("#filtroProvince").val("");
	$("#filtroProvince").empty();
	$('<option>').val('').text('Tutte').appendTo('select[name=filtroProvince]');
								
	$("#filtroComuni").val("");
	$("#filtroComuni").empty();
	$('<option>').val('').text('Tutti').appendTo('select[name=filtroComuni]');

	$("#filtroRaggi").val("");
	$("#filtroRaggi").empty();
	$('<option>').val('').text($("#filtroRaggi").val("raggioRicercaProssimita") + ' KM').appendTo('select[name=filtroRaggi]');
	
	$("#scuoleStatali").val("");
	$("#scuoleStatali").attr('checked', false);
	$("#scuoleNonStatali").val("");
	$("#scuoleNonStatali").attr('checked', false);
	$("#scuoleParitarie").val("");
	$("#scuoleParitarie").attr('checked', false);
	$("#scuoleNonParitarie").val("");
	$("#scuoleNonParitarie").attr('checked', false);
	
	$("#filtroOrdini").val("");
	$("#filtroOrdini").empty();
	$('<option>').val('').text('Tutti').appendTo('select[name=filtroOrdini]');
	
	$("#pannelloTipologia1").css("display" , "none");
	$("#pannelloTipologia2").css("display" , "none");
	$("#filtroTipologie").val("");
	$("#filtroTipologie").empty();
	$('<option>').val('').text('Tutte').appendTo('select[name=filtroTipologie]');
	
	//gm modifica per tempi scuola, settore e indirizzo musicale
	$("#pannelloSettoriScuola1").css("display" , "none");
	$("#pannelloSettoriScuola2").css("display" , "none");
	$("#filtroSettoriScuola").val("");
	$("#filtroSettoriScuola").empty();
	$('<option>').val('').text('Tutti').appendTo('select[name=filtroSettoriScuola]');
	
	$("#pannelloTempiScuolaPrimaria1").css("display" , "none");
	$("#pannelloTempiScuolaPrimaria2").css("display" , "none");
	$("#filtroTempiScuolaPrimaria").val("");
	$("#filtroTempiScuolaPrimaria").empty();
	$('<option>').val('').text('Tutti').appendTo('select[name=filtroTempiScuolaPrimaria]');
	
	$("#pannelloTempiScuolaSecondaria1").css("display" , "none");
	$("#pannelloTempiScuolaSecondaria2").css("display" , "none");
	$("#filtroTempiScuolaSecondaria").val("");
	$("#filtroTempiScuolaSecondaria").empty();
	$('<option>').val('').text('Tutti').appendTo('select[name=filtroTempiScuolaSecondaria]');
	
	$("#indirizzoMusicale").val("");
	$("#indirizzoMusicale").attr('checked', false);
	//gm modifica per tempi scuola, settore e indirizzo musicale
	
	$("#pannelloIndirizzoStudio1").css("display" , "none");
	$("#pannelloIndirizzoStudio2").css("display" , "none");
	$("#filtroIndirizziStudio").val("");
	$("#filtroIndirizziStudio").empty();
	$('<option>').val('').text('Tutti').appendTo('select[name=filtroIndirizziStudio]');
	
	$("#ricercaFullText").val("");
	$("#criteriRicerca").html("");

	if(aggiornaCriteri)
		aggiornaCriteriRicerca("criteriRicerca");	
}

