app.controller('loginCtrl',['$scope','$location','$http','$rootScope','$state',
                            function($scope,$location,$http,$rootScope,$state){
	$scope.login={};
	$scope.error={};
	var authData="";
	var encodedAuthData="";
	
	
	if($rootScope.authorized){
		$state.go("home");
	}
	else{
		$state.go("login");
	}
	
	$scope.admin = false;
	$scope.sbtForm= function(isValid){
		if(isValid){
			$scope.error={};
			authData = $scope.login.username + ':' + $scope.login.password;
			encodedAuthData = btoa(authData);
			$http({
				method : 'GET',
				url : '/login',
				headers : {
					'Authorization' : 'Basic ' + encodedAuthData
				}
			}).then(function(response){
				$rootScope.authorized=true;
				console.log("login success");
				debugger				
				if(response.data.role[0].role == "admin")
					$rootScope.authenticated = true;
				else
					$rootScope.authenticated = false;
				/*$scope.$emit("checkIfAdmin",'');*/
				$location.url("/home");
				debugger
			},function(response){
				$scope.error.userNotFound=true;
				console.log("login error");
			})
		}
		else{
			if($scope.login.username==""||$scope.login.username==undefined)
				$scope.error.username=true;
			else
				$scope.error.username=false;
			if($scope.login.password==""||$scope.login.password==undefined)
				$scope.error.password=true;
			else
				$scope.error.password=false;			
		}
	}
	/*$scope.sbtForm = function(){
		alert("fdfdfgdsfg");
	}*/
}])


app.service('getLoggedInEmp',function(){
	debugger
	var loggedEmp = {};
	return loggedEmp;
})
