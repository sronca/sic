(function(){
    angular.module('ngLoadingSpinner', ['angularSpinner'])
    .directive('usSpinner',   ['$http', '$rootScope','uploadManager' ,function ($http, $rootScope,uploadManager){
        return {
            link: function (scope, elm, attrs)
            {
                $rootScope.spinnerActive = false;
                
                var  uploadManager_pendingRequest=0;
                
                $rootScope.$on("UPLOADMANAGER_UPLOAD", function (e, optBackEvent) {
                	if( optBackEvent=='START' ){
                	     uploadManager_pendingRequest++;
                	} else {
                		 uploadManager_pendingRequest--;
                	} 
                	  var loading =  $http.pendingRequests.length > 0 ||  uploadManager_pendingRequest > 0;
                	  $rootScope.spinnerActive = loading;
                      if(loading){
                          elm.removeClass('ng-hide');
                      }else{
                          elm.addClass('ng-hide');
                      }
                	
    			});
                
                
                scope.isLoading = function () {
                    
                    return $http.pendingRequests.length > 0 ||  uploadManager_pendingRequest > 0  ;
                };

                scope.$watch(scope.isLoading, function (loading)
                {
                    $rootScope.spinnerActive = loading;
                    if(loading){
                        elm.removeClass('ng-hide');
                    }else{
                        elm.addClass('ng-hide');
                    }
                });
                
                
               
            }
        };

    }]);
}).call(this);
