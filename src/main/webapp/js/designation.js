app.controller('desigCtrl',['$scope','$location','$http','$state',function($scope,$location,$http,$state){
	$scope.designation={};
	$scope.error={}
	$scope.submitForm=function(isValid){		
		if(isValid){
			$http.post("/admin/addDesignation",$scope.designation).then(function(response){
			},
			function(response){
				alert('error adding designation');
			})
			$state.reload();
		}else{
			debugger
			$scope.error.desig=true;
		}
	}
}])