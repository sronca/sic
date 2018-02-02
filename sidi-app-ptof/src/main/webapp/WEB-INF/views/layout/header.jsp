<%@ include file="taglib.jsp"%>
<sec:authorize access="!hasRole('ROLE_ANONYMOUS')" var="loggedin"></sec:authorize>
<sec:authentication property="principal" var="principal"></sec:authentication>

 			<!-- container fluid -->
			<div id="header" class="container-fluid page-header">

				<!-- wrap per calcolare altezza header -->
				<div id="header-wrapper">

					<!-- prima barra blu scuro -->
					<div id="top-nav">
						<div id="top-nav-wrapper">

							<p id="top-nav-title">
								<a href="http://www.governo.it/">Ministero dell'Istruzione dell'Universita'� e della Ricerca</a>
							</p>

							<!-- nome profilo con menu -->
							<div class="desktop-usermenu-wrapper dropdown" data-clone-source="desktop-usermenu">
								<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
									${principal.nome} ${principal.cognome} <span class="miuricon miur-utente"></span>
								</a>

								<!-- menu utente -->
								<div class="dropdown-menu dropdown-menu-right dropdown-arrow" role="menu">
									<ul>
										<li>
											<p class="row-1"><span class="miuricon miur-profili"></span> Profilo:</p>
											<p class="row-2"> ${empty principal.currentProfile ? '' : principal.currentProfile.descrizione} </p>
										</li>
										<li>
											<p class="row-1"><span class="miuricon miur-contesti"></span> Contesto:</p>
											<p class="row-2"> ${empty principal.currentContesto ? '' : principal.currentContesto.descrizione}  </p>
										</li>
										<li>
												<a href="<%=request.getContextPath()%>/login/change-profile.do"><span class="miuricon miur-single-user"></span> Cambia Profilo</a>
										</li>
										<li class="last">

										 		<div sidilogout="" > </div>  

										</li>
									</ul>
								</div>
								<!-- menu utente -->

							</div>
							<!-- nome profilo con menu -->

						</div>
					</div>
					<!-- prima barra blu scuro -->

					<!-- barra blu per titolo -->
					<div id="head-lead">

						<!-- hamburger solo su breakpoint mobile -->
						<div id="nav-mobile">
							<a href="javascript:void(0)">
								<span class="nav-wrapper">
									<span class="nav-bar"></span>
									<span class="nav-bar"></span>
									<span class="nav-bar"></span>
								</span>
								<span class="nav-text">Menu</span>
							</a>
						</div>
						<!-- hamburger solo su breakpoint mobile -->

						<div class="main-title-wrapper">
							<div class="logo">
								<a href="http://www.istruzione.it/"><img src="<%=request.getContextPath()%>/assets/img/logo_white.png" alt="Homepage del MIUR"></a>
							</div>
							<h1>
								SIDI <span>Sistema informativo dell'Istruzione</span>
							</h1>
						</div>

					</div>
					<!-- barra blu per titolo -->

				</div>

				
				<!-- fixed bar - offset è il valore in pixel a partire dal quale bloccare la barra -->
				<div id="fixed-bar" data-offset="170">

					<div id="fixed-bar-wrapper" data-clone-target="fixed-bar-wrapper">

						<div class="logo">
							<a href="http://www.istruzione.it/"><img src="<%=request.getContextPath()%>/assets/img/logo_white.png" alt="Homepage del MIUR"></a>
						</div>
						<h1>SIDI</h1>

					</div>

				</div>
				<!-- fixed bar - offset è il valore in pixel da cui bloccare la barra -->

			</div>
			<!-- container fluid -->





