<%@ page import="it.istruzione.cercalatuascuola.common.util.Utility" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../includes/taglib.jsp" %>
<%@ include file="../includes/html.jsp" %>
<head>
    <%@ include file="../includes/head_dettaglio.jsp" %>
</head>
<body>


<%@ include file="../includes/toolbar_header.jsp" %>

<section class="sc-internal-main sc-detail">

  <%@ include file="../includes/breadcrumbandtitle.jsp" %>
  <%@ include file="../includes/menu.jsp" %>

  <div class="sc-main-content-detail">
    <div class="sc-wrapper sc-wrapper-alt">
      <div  class="sc-table-table sc-external-box">
        <div class="sc-table-row sc-external-box">
        
          <%@ include file="../includes/menu_aside_chisiamo.jsp" %>
          
          <article class="sc-internal-content sc-table-cell">
            <h2>Tempi scuola/indirizzi per l'a.s. <%=Utility.annoScolasticoCorrente() %></h2>
            <div class="sc-cols">
              <div class="sc-col-full sc-double-col">
                <p>Gli orari di funzionamento della scuola dell'infanzia, fissati dal Regolamento approvato con DPR n. 89/2009 (art. 2, comma 5), sono, di norma :</p>
                <ul>
	                  <li>
	                    orario ordinario delle attivit&agrave; educative per 40 ore settimanali
	                  </li>
	                  <li>
	                    orario ridotto delle attivit&agrave; educative con svolgimento nella fascia del mattino per 25 ore settimanali
	                  </li>
	                  <li>
	                    orario prolungato delle attivit&agrave; educative fino a 50 ore alla settimana
	                  </li> 	                  	                                       
                </ul>
              </div>


            </div>


            
          </article>
        </div>
      </div>
    </div>
  </div>
</section>
<%@ include file="../includes/footerDettaglioScuola.jsp" %>
</body>
</html>