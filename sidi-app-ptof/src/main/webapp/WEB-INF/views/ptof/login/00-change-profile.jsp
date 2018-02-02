<div  >

	<h2>
		<span class="miuricon miur-profili"></span> 
		Selezione profilo e	contesto
	</h2>


	<div class="content-frame"  ng-controller = "ChangeProfileCtrl as changeProfileCtrl"  >
	
		<!-- primo livello -->
		<ul id="profile-list">
		
		    <li  ng-repeat="profilo in modelProfili.v1.attribForm.profiloUtenteL"> 
				<h5>
					<a ng-if = "profilo.contestoUtenteL.length>0" href="javascript:void(0)" data-toggle="collapse"
						data-target="#profile-{{$index}}"> {{ profilo.desc }} <span
						class="switchicon sidebar-icon miuricon miur-freccia-giu"
						data-switchicon-odd="miur-freccia-giu"
						data-switchicon-even="miur-freccia-su"></span></a>
						
					<a ng-if = "profilo.contestoUtenteL.length==0" href="home.do"> {{ profilo.desc }} </a>
						
				</h5>
		 
				<div id="profile-{{$index}}" class="collapse"  ng-if = "profilo.contestoUtenteL" >
	
					<ul class="menu-level-2"  ng-repeat="contesto in profilo.contestoUtenteL"   >
						<li><a ng-click="changeProfileCtrl.setProfilo(profilo.id+'||'+contesto.id)"  href="">{{ contesto.desc }} </a></li>
					</ul>
				</div> 
		    </li>
	 </ul>
	
	</div>
</div>

