<!-- sidebar container -->
<div id="sidebar-container" ng-controller="MenuCtrl as menuCtrl" style="min-height: 768px;" >
	<div id="sidebar">

		<div id="sidebar-menu-header">
			<h4>Navigazione</h4>
		</div>

		<ul id="sidebar-menu">
			         
			        
			             
			<li ng-repeat="item in modelMenu.items"  class="active" > 
	 			      
			     <span class="vline"></span> 
			     <a ng-if= " item.tipoItem == 'FOGLIA' " href="{{ item.url }}"  > 	 {{ item.testo }} 	 </a> 
                 <a ng-if= " item.tipoItem == 'RAMO' " href="javascript:void(0)" data-toggle="collapse" data-target="#idlevel-{{$index}}" aria-expanded="false" class="collapsed"> {{ item.testo }}  <span class="switchicon sidebar-icon miuricon miur-freccia-giu" data-switchicon-odd="miur-freccia-giu" data-switchicon-even="miur-freccia-su"></span></a>
				 <!-- secondo livello -->

					<div ng-if= " item.tipoItem == 'RAMO' " id="idlevel-{{$index}}" class="multilevel-menu collapse">
						<ul class="menu-level-2">
			                <div bs-sidiMenu="" data-item = "item"  data-idliv ="item.idItem" data-liv = "3" ></div>
				        </ul>
				    </div>  
			  
			     
			</li>

		</ul>
	</div>
</div> 
<!-- sidebar container -->