    
$(document).ready(function() 
{

    $(".panel-group a").on('click', function() 
    {
    	var target = $(this).attr("href");
         
        if(!$(this).children("span").hasClass("miur-freccia-avanti"))  
        {

         if (!$(this).hasClass("collapsed")) 
         	{
         		$(this).children("span").removeClass("miur-chevron-up");
         		$(this).children("span").addClass("miur-chevron-down");
         		$(target).children("li").removeClass("show"); 
         	}
         	
         	else
         	{

         		$('#accordion').on('shown.bs.collapse', function() 
         			{
						$(target).children("li").addClass("show");   
					});

         		$(this).children("span").removeClass("miur-chevron-down");
         		$(this).children("span").addClass("miur-chevron-up");

         	}
         
         }        
     
    });
    
 });

    