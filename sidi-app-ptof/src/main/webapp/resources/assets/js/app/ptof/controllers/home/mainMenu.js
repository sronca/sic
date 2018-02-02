'use strict';


// Declare app level module which depends on filters, and services
angular.module('ptof.controlles.home', [
  'ngLoadingSpinner',
  'ngSanitize',
  'ptof.directives'
]).factory('menuServicesFactory', function ($http, $rootScope) {
	return {
 		getMenu : function (form) {
			console.log('menuServicesFactory.getMenu ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/home/menu.json', form);

		},
		initFormProfili : function (form) {
			console.log('menuServicesFactory.initFormProfili.json ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/home/initFormProfili.json', form);

		},
		setProfilo : function (form) {
			console.log('menuServicesFactory.profilo ->');
			return $http.post(APPGLOBALCONSTANT.contexPath+'/home/profilo.json', form);

		}
		
	}
}).controller('MenuCtrl', function ($scope, $location, menuServicesFactory ) {
	console.log('MenuCtrl->');
	$scope.modelMenu = {
			items : {}
	};
	 
	menuServicesFactory.getMenu({})
	.success(function (data, status, config, headers) {
		console.log('Response from server: ' + data);
	 
		//  $scope.modelMenu.menu.fieldErrors = null;
		$scope.modelMenu.items  = data.attribForm.menu;
	 

	})
	.error(function (data, status, config, headers) {
		console.log('Some error occurred!');
	   //	$scope.modelMenu.menu.fieldErrors = data.fieldErrors; 
	});	
	
	
}).controller('ChangeProfileCtrl', function ($scope, $window , menuServicesFactory ) {
	console.log('MenuCtrl->');
    $scope.modelProfili = {
    		v1 : {}
    };
    $scope.modelProfili.v1.form = {
			idProfilo : {},
			idContesto : {}
	};
    $scope.modelProfili.v1.attribForm = {};
    $scope.modelProfili.v1.fieldErrors = [];
	
	menuServicesFactory.initFormProfili({})
	.success(function (data, status, config, headers) {
		console.log('Response from server: ' + data);
		$scope.modelProfili.v1.attribForm  = data.attribForm;
	})
	.error(function (data, status, config, headers) {
		console.log('Some error occurred!');
	});
	
	this.setProfilo = function (inputData) {
		var form = $scope.modelProfili.v1.form ,
		    inputDataS = inputData.split('||');
		form.idProfilo = inputDataS[0];
		form.idContesto = inputDataS[1];
			
		menuServicesFactory.setProfilo(form)
		.success(function (data, status, config, headers) {
			console.log('Response from server: ' + data);
			$window.location.href = APPGLOBALCONSTANT.contexPath+'/home.do';
 		})
		.error(function (data, status, config, headers) {
			console.log('Some error occurred!');
		});
		
	}
	
	
	
	
}) 