<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
	<head>
		<%@ include file="../includes/head.jsp" %>
	</head>
	<body>
	
	<%@ include file="../includes/toolbar_header.jsp" %>
    <section class="sc-internal-main">

        <div class="sc-title">
            <div class="sc-wrapper">
                <h1>Criteri di precedenza</h1>
            </div>
        </div>

        <article class="sc-general-info sc-internal-content sc-zoom-anim-dialog">
		    <div class="sc-wrapper">
		      <div class="sc-cont-full cs-sitemap2">
		      
			    <h2 class="sc-title-modal">Criteri di precedenza stabiliti dal Consiglio di istituto <br/> per le iscrizioni all'anno scolastico ${annoIOL}</h2>
       		    
       		    <br/><br/> 	
		  		<pre>
		  		${testo}
		  		</pre>
		      </div>
		    </div>
		    
		    <script>
		    
/* 			var jqxhr = $.getJSON('http://mpv20132.sidi.mpi.it:8081/iolservizi/iol/regole/NAEE345029',function(data){
				console.log( "success" );
				if (data != null){
					$('#testo_criteri_precedenza').html(data.regoleIscrizione);
				}


			})
			.done(function() {
				console.log( "second success" );
			})
			.fail(function(jqxhr) {
				console.log( "error" );
				console.log(jqxhr);

			})
			.always(function() {
				console.log( "complete" );
			}); */
		    </script>

        </article>

    </section>
	<%@ include file="../includes/footer.jsp" %>
    
	</body>
</html>