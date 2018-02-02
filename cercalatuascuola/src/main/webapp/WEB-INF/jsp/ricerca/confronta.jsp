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
                <h1>Confronta Istituti Selezionati</h1>
            </div>
        </div>

        <div class="sc-action-bar">
            <div class="sc-wrapper">
                <ul>
                    <li class="sc-icon-search-bar">
                        <a  class="sc-modal" href="/cercalatuascuola/ricerca/nuova/">
                            <span>Nuova ricerca</span>
                        </a>
                    </li>
                    <li class="sc-icon-print-bar">
                        <a href="javascript:window.print();">
                            <span>Stampa elenco</span>
                        </a>
                    </li>
                    <li class="sc-icon-info-bar">
                        <a href="/cercalatuascuola/info1/" class="sc-info-modal">
                            <span>Aiuto</span>
                        </a>
                    </li>
                    <li class="sc-icon-mappa-bar">
                        <a href="/cercalatuascuola/mappa_istituti_confronta?idform=${idform}" class="sc-map-modal">
                            <span>Ingrandisci Mappa</span>
                        </a>
                    </li>
                </ul>

                <div class="sc-back-action">
                    <!-- <a href="/cercalatuascuola${linkRisultatiRicerca}" class="sc-button">Torna ai risultati ricerca</a> -->
                    <a href="javascript:history.back();" class="sc-button">Torna indietro</a>
                </div>

            </div>
        </div>

        <!-- <div id="sc-map-canvas"></div>  -->

        <article class="sc-internal-content sc-compare">

            <div class="sc-wrapper sc-wrapper-alt">

                <table class="sc-compare-table">
                    <thead class="sc-table-header">
                        <tr>
                            <th>
                                <span class="sc-cont-prev-next">
                                    <a class="sc-table-prev" href="#">
                                        <span>Prev</span>
                                    </a>
                                    <a class="sc-table-next" href="#">
                                        <span>Next</span>
                                    </a>
                                </span>
                            </th>
                            
							<c:forEach items="${scuoleDaConfrontare}" var="voScuola" varStatus="status">
	                            <th id="id_th_confronta_${status.index +1}">
	                                <span class="sc-table-header-title">
										<span class="sc-map-htitle">
											<c:if test="${voScuola.latitudine ne ''}">
												<c:if test="${voScuola.cfp}">
													<a href="#sc-map-canvas" title="Posizione mappa ${status.index +1}" class="sc-map-cfp sc-map-${status.index +1}">
												</c:if>
												<c:if test="${not voScuola.cfp and voScuola.stataleNonStatale eq 0}">
													<a href="#sc-map-canvas" title="Posizione mappa ${status.index +1}" class="sc-map-stat sc-map-${status.index +1}">
												</c:if>
												<c:if test="${not voScuola.cfp and voScuola.stataleNonStatale eq 1}">
													<a href="#sc-map-canvas" title="Posizione mappa ${status.index +1}" class="sc-map-par sc-map-${status.index +1}">
												</c:if>																						
													Posizione mappa ${status.index +1}
												</a>
											</c:if>
										</span> 	                                
	                                </span>
	                                <span class="sc-table-header-content sc-center"><a href="/cercalatuascuola/istituti/${voScuola.codMecc}/${voScuola.normalizedName}/">${voScuola.codMecc} - ${voScuola.descrizione} (${voScuola.comune})</a></span>
	                                <!-- dati per la mappa - inizio -->
	                                <span class="dati_scuola" style="display: none;"
	                                	   data-index="${status.index +1}"
	                                	   data-lat="${voScuola.latitudine}"
	                                	   data-lon="${voScuola.longitudine}"
	                                	   data-nome="${voScuola.descrizione}"
	                                	   data-tiposcuola="${voScuola.ordine} ${voScuola.tipologia}"
	                                	   data-indirizzo="${voScuola.indirizzoCompleto}"
	                                	   data-telefono="${voScuola.telefono}"
	                                	   data-codicemeccanografico="${voScuola.codMecc}"
	                                	   data-normalizedname="${voScuola.normalizedName}"
	                                	   data-stataleNonStatale="${voScuola.stataleNonStatale}"
	                                ></span>
	                            </th>
							 </c:forEach>
                        </tr>
                    </thead>
                    <tbody class="sc-table-title">
                        <tr>
                            <td colspan="${scuoleDaConfrontareSize +1}">
                                <h2>La scuola in numeri</h2>
                            </td>
                        </tr>
                    </tbody>
                    <tbody class="sc-table-content">
                        <tr>
                            <td>
                                <span class="sc-val-tab">Numero alunni</span>
                            </td>
                            <td>
                                <span class="sc-val-tab">${scuoleDaConfrontare[0].numeroAlunni}</span>
                            </td>
                            <td>
                                <span class="sc-val-tab">${scuoleDaConfrontare[1].numeroAlunni}</span>
                            </td>
                            <c:if test="${scuoleDaConfrontareSize gt 2}">
	                            <td>
	                                <span class="sc-val-tab">${scuoleDaConfrontare[2].numeroAlunni}</span>
	                            </td>
	                            <c:if test="${scuoleDaConfrontareSize gt 3}">
		                            <td>
		                                <span class="sc-val-tab">${scuoleDaConfrontare[3].numeroAlunni}</span>
		                            </td>
		                            <c:if test="${scuoleDaConfrontareSize gt 4}">
			                            <td>
			                                <span class="sc-val-tab">${scuoleDaConfrontare[4].numeroAlunni}</span>
			                            </td>
			                            <c:if test="${scuoleDaConfrontareSize gt 5}">
				                            <td>
				                                <span class="sc-val-tab">${scuoleDaConfrontare[5].numeroAlunni}</span>
				                            </td>				                            
			                            </c:if>	 			                            
		                            </c:if>	 		                            
	                            </c:if>	                            
                            </c:if>
                        </tr>
                        <tr>
                            <td>
                                <span class="sc-val-tab">Numero classi</span>
                            </td>
                            <td>
                                <span class="sc-val-tab">${scuoleDaConfrontare[0].numeroClassi}</span>
                            </td>
                            <td>
                                <span class="sc-val-tab">${scuoleDaConfrontare[1].numeroClassi}</span>
                            </td>
                            <c:if test="${scuoleDaConfrontareSize gt 2}">
	                            <td>
	                                <span class="sc-val-tab">${scuoleDaConfrontare[2].numeroClassi}</span>
	                            </td>
	                            <c:if test="${scuoleDaConfrontareSize gt 3}">
		                            <td>
		                                <span class="sc-val-tab">${scuoleDaConfrontare[3].numeroClassi}</span>
		                            </td>
		                            <c:if test="${scuoleDaConfrontareSize gt 4}">
			                            <td>
			                                <span class="sc-val-tab">${scuoleDaConfrontare[4].numeroClassi}</span>
			                            </td>
			                            <c:if test="${scuoleDaConfrontareSize gt 5}">
				                            <td>
				                                <span class="sc-val-tab">${scuoleDaConfrontare[5].numeroClassi}</span>
				                            </td>				                            
			                            </c:if>	 			                            
		                            </c:if>	 		                            
	                            </c:if>	                            
                            </c:if>
                        </tr>
                        <tr>
                            <td>
                                <span class="sc-val-tab">Media alunni/classi</span>
                            </td>
                            <td>
                                <span class="sc-val-tab">${scuoleDaConfrontare[0].mediaAlunniClassi}</span>
                            </td>
                            <td>
                                <span class="sc-val-tab">${scuoleDaConfrontare[1].mediaAlunniClassi}</span>
                            </td>
                            <c:if test="${scuoleDaConfrontareSize gt 2}">
	                            <td>
	                                <span class="sc-val-tab">${scuoleDaConfrontare[2].mediaAlunniClassi}</span>
	                            </td>
	                            <c:if test="${scuoleDaConfrontareSize gt 3}">
		                            <td>
		                                <span class="sc-val-tab">${scuoleDaConfrontare[3].mediaAlunniClassi}</span>
		                            </td>
		                            <c:if test="${scuoleDaConfrontareSize gt 4}">
			                            <td>
			                                <span class="sc-val-tab">${scuoleDaConfrontare[4].mediaAlunniClassi}</span>
			                            </td>
			                            <c:if test="${scuoleDaConfrontareSize gt 5}">
				                            <td>
				                                <span class="sc-val-tab">${scuoleDaConfrontare[5].mediaAlunniClassi}</span>
				                            </td>				                            
			                            </c:if>	 			                            
		                            </c:if>	 		                            
	                            </c:if>	                            
                            </c:if>
                        </tr>                                           
                    </tbody>
                    
                    
                    
                    
                    
                    
			  <%@page import="java.util.List"%>
			  <%@page import="java.util.ArrayList"%>
			  <%@page import="it.istruzione.cercalatuascuola.ricerca.dao.model.VOServizi"%>
			  <%
			  	String codiceLv1Appoggio = "";
			  	String codiceLv2Appoggio = "";
			  	List<VOServizi> servizi = (List<VOServizi>)request.getAttribute("servizi");
			  	int scuoleDaConfrontareSize = ((Integer)request.getAttribute("scuoleDaConfrontareSize")).intValue();
			    
			  	for (int i = 0; i < servizi.size(); i++)
			    {
			        VOServizi voServizio = (VOServizi)servizi.get(i);
			        
			        
					if(
							"S".equals(voServizio.getScuola1())
							|| "S".equals(voServizio.getScuola2())
							|| ((scuoleDaConfrontareSize >= 3) && "S".equals(voServizio.getScuola3()))
							|| ((scuoleDaConfrontareSize >= 4) && "S".equals(voServizio.getScuola4()))
							|| ((scuoleDaConfrontareSize >= 5) && "S".equals(voServizio.getScuola5()))
							|| ((scuoleDaConfrontareSize >= 6) && "S".equals(voServizio.getScuola6()))
						)
					{
			        
					if(codiceLv1Appoggio.equals("") || !codiceLv1Appoggio.equals(voServizio.getCodiceServizio1lv()))
					{
						//if(!codiceLv1Appoggio.equals(""))
						if (1==2)//nascondo la riga vuota
					    {%>
		                    <tbody class="sc-table-title">
		                        <tr>
		                            <td colspan="<%=(scuoleDaConfrontareSize+1)%>">
		                                <h2>&nbsp;</h2>
		                            </td>
		                        </tr>
		                    </tbody>
						   
					  <%}%>

							<%
							//if (!voServizio.getDescrizione1Livello().equals("SERVIZI WEB")){
							if (1==2){//nascondo il primo livello
							%>
		                    <tbody class="sc-table-title">
		                        <tr>
		                            <td colspan="<%=(scuoleDaConfrontareSize+1)%>">
		                                <h2><%=voServizio.getDescrizione1Livello()%></h2>
		                            </td>
		                        </tr>
		                    </tbody>
		                    <%}%>
		                    					    
					  <%codiceLv1Appoggio = voServizio.getCodiceServizio1lv();
					  	codiceLv2Appoggio = "";%>
				  <%}			        
			        
					if(codiceLv2Appoggio.equals("") || !codiceLv2Appoggio.equals(voServizio.getCodiceServizio2lv()))
					{%>
		                    <tbody class="sc-table-title">
		                        <tr>
		                            <td colspan="<%=(scuoleDaConfrontareSize+1)%>">
		                                <h2><%=voServizio.getDescrizione2Livello()%></h2>
		                            </td>
		                        </tr>
		                    </tbody>				    	
										  
					  <%codiceLv2Appoggio = voServizio.getCodiceServizio2lv();
					}
					
				}%>
					
					
					
					
				<%if(
						"S".equals(voServizio.getScuola1())
						|| "S".equals(voServizio.getScuola2())
						|| ((scuoleDaConfrontareSize >= 3) && "S".equals(voServizio.getScuola3()))
						|| ((scuoleDaConfrontareSize >= 4) && "S".equals(voServizio.getScuola4()))
						|| ((scuoleDaConfrontareSize >= 5) && "S".equals(voServizio.getScuola5()))
						|| ((scuoleDaConfrontareSize >= 6) && "S".equals(voServizio.getScuola6()))
					)
					{%>
					<tbody class="sc-table-content">					  					
					<tr>
					<%}else{%>
					<tbody class="sc-table-content" style="display:none;">
					<tr>
					<%}%>
						
						<td>
							<span class="sc-val-tab"><%=voServizio.getDescrizione3Livello()%></span>
						</td>	
						<td>
					      <%if("S".equals(voServizio.getScuola1()))
					    	{%>
						    	<span class="sc-check-true">Check</span>
					      <%}
					        else
					        {%>
					        	&nbsp;
					      <%}%>
						</td>
						<td>
					      <%if("S".equals(voServizio.getScuola2()))
					    	{%>
						    	<span class="sc-check-true">Check</span>
					      <%}
					        else
					        {%>
					        	&nbsp;
					      <%}%>
						</td>
					<%if(scuoleDaConfrontareSize >= 3)
					  {%>
							<td>
						      <%if("S".equals(voServizio.getScuola3()))
						    	{%>
							    	<span class="sc-check-true">Check</span>
						      <%}
						        else
						        {%>
						        	&nbsp;
						      <%}%>
							</td>
					 	  <%if(scuoleDaConfrontareSize >= 4)
						  	{%>
								<td>
							      <%if("S".equals(voServizio.getScuola4()))
							    	{%>
								    	<span class="sc-check-true">Check</span>
							      <%}
							        else
							        {%>
							        	&nbsp;
							      <%}%>
								</td>	
							  <%if(scuoleDaConfrontareSize >= 5)
						  	    {%>		
									<td>
								      <%if("S".equals(voServizio.getScuola5()))
								    	{%>
									    	<span class="sc-check-true">Check</span>
								      <%}
								        else
								        {%>
								        	&nbsp;
								      <%}%>
									</td>
								  <%if(scuoleDaConfrontareSize >= 6)
							  	    {%>		
										<td>
									      <%if("S".equals(voServizio.getScuola6()))
									    	{%>
										    	<span class="sc-check-true">Check</span>
									      <%}
									        else
									        {%>
									        	&nbsp;
									      <%}%>
										</td>														  	    
							  	  <%}%>															  	    
						  	  <%}%>		  	
						 <%}%>		  
					<%}%>	
					</tr>
					</tbody>						    	
			  <%}%>
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                </table>

                <div class="sc-table-fixed-container">
                    <table class="sc-table-header-fixed"></table>
                </div>
            </div>

        </article>
        
        <div id="sc-map-canvas"></div>

    </section>
	<%@ include file="../includes/footer.jsp" %>
  
    
    
    
	</body>
</html>