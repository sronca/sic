function caricaProvincia(codiceRegione) 
{	
	if(codiceRegione != "")
	{	
		$.getJSON('/poninchiaro/caricaProvincia.json?codiceRegione=' + codiceRegione,function(data){			
			$("#codiceProvincia").empty();
			$.each(data,function(key,value){
				$("#codiceProvincia").append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
	}
	else
	{
		$("#codiceProvincia").empty();
		$("#codiceProvincia").val("");
	}

}

function caricaComune(codiceProvincia) 
{	
	if(codiceProvincia != "")
	{	
		$.getJSON('/poninchiaro/caricaComune.json?codiceProvincia=' + codiceProvincia,function(data){			
			$("#codiceComune").empty();
			$.each(data,function(key,value){
				$("#codiceComune").append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
	}
	else
	{
		$("#codiceComune").empty();
		$("#codiceComune").val("");
	}

}

function caricaSottoCategoria(tipoFondo) 
{	
	if(tipoFondo != "" && tipoFondo != "NESSUNO")
	{	
		$.getJSON('/poninchiaro/caricaSottoCategoria.json?tipoFondo=' + tipoFondo,function(data){			
			$("#codiceSottoCategoria").empty();
			$.each(data,function(key,value){
				$("#codiceSottoCategoria").append($('<option value="'+key+'">'+value+'</option>'));
			});		
		});
	}
	else
	{
		$("#codiceSottoCategoria").empty();
		$("#codiceSottoCategoria").val("");
	}

}