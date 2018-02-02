<header class="shadow-mobile" id="header">
		<h1 class="logo-img">
			<a href="<c:url value="/"/>">
				<img src="<c:url value="/resources/img/logo-large.png"/>" alt="Torna alla homepage">
				<span class="hidden">Pon in Chiaro</span>
			</a>
		</h1>
    <div id="right-menu">
		<a href="http://hubmiur.pubblica.istruzione.it/web/istruzione/pon/2014_2020"><p class="funds">Fondi Strutturali</p></a>
        <a href="<c:url value="/contatti/"/>"><span class="contact"></span>Contatti</a>
        <a href="<c:url value="/faq/"/>"><span class="faq"></span>FAQ</a>
        <a href="<c:url value="/glossario/"/>"><span class="glossary"></span>Glossario</a>
    </div>
</header>
<div class="nav-container">
    <div id="menu-responsive">
        <span></span>
        <span></span>
        <span></span>
    </div>
    <div class="nav shadow-nav nav-space">
        <div class="logo-small">
        	<div class="logo-small-position">
				<a href="<c:url value="/"/>">
					<img src="<c:url value="/resources/img/logo-small.png"/>" alt="Torna alla homepage">
				</a>        	
        	</div>
        </div>
        <ul id="menu">
            <li class="item-menu"><a class="link-menu" href="javascript: void(0);">Beneficiari<span></span></a>
                <ul class="item-sub-menu" style="display: none;">
					<li><a href="<c:url value="/beneficiari/ricerca/"/>" class="link-sub-menu link-sub-menu-first">Istituti</a></li>
					<!-- <li><a href="search-e.php" class="link-sub-menu">Enti locali</a></li>  -->
                    <li><a href="<c:url value="/beneficiari/fornitori/"/>" class="link-sub-menu">Fornitori</a></li>
                </ul>
            </li>
            <li class="item-menu short"><a class="link-menu" href="<c:url value="/scuoledelpon/ricerca/"/>">Le scuole del PON</a></li>
            <li class="item-menu short"><a class="link-menu" href="<c:url value="/bandiscuole/ricerca/"/>">Bandi delle scuole</a></li>
            <li class="item-menu short"><a class="link-menu" href="<c:url value="/opendata/"/>">Open Data</a></li>
			<li class="item-menu"><a class="link-menu" href="javascript: void(0);">Pon in cifre<span></span></a>
                <ul class="item-sub-menu" style="display: none;">
                    <li><a href="<c:url value="/ponincifre/progetti/"/>" class="link-sub-menu link-sub-menu-first">Progetti</a></li>
                    <li><a href="<c:url value="/ponincifre/beneficiari/"/>" class="link-sub-menu">Beneficiari</a></li>
                    <li><a href="<c:url value="/ponincifre/programmazione/"/>" class="link-sub-menu">Programmazione</a></li>
                </ul>
            </li>            

            <li class="item-menu rsp-only"><a class="link-menu" href="<c:url value="/glossario/"/>" >Glossario</a></li>
            <%-- <li class="item-menu rsp-only"><a class="link-menu" href="<c:url value="/resources/doc/Glossario.pdf"/>" >Glossario</a></li> --%>
            <li class="item-menu rsp-only"><a class="link-menu" href="<c:url value="/faq/"/>">FAQ</a></li>
            <li class="item-menu rsp-only"><a class="link-menu" href="<c:url value="/contatti/"/>">Contatti</a></li>
			<li class="item-menu rsp-only"><a class="link-menu" href="http://hubmiur.pubblica.istruzione.it/web/istruzione/pon/2014_2020">Fondi Strutturali</a></li>
        </ul>
    </div>
</div>