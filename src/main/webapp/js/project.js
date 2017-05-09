app.controller('projectCtrl',['$scope','$location','$http','$state',function($scope,$location,$http,$state){
	$scope.project={
			empList:[]
	};
	$scope.error={};
	$scope.managers=[];
	$http.get("/admin/getManager").then(function(response){
		
		$scope.managers=response.data;
	},
		function(resonse){
			alert("manager loading failed")
		}
	)
	
	$http.get("/admin/getEmployees").then(function(response){
		$scope.members = response.data;
	},function(response){
		alert("employees error");
	})
	
	$scope.submitForm = function(isValid){
		if(isValid){
			/**/
			debugger
			if($scope.id1==$scope.id2){
				$scope.error.member12=true;
			}
			else{
				$scope.error.member12 = false;
			}
			if($scope.id2==$scope.id3){
				$scope.error.member23=true;
			}
			else{
				$scope.error.member23 = false;
			}
			if($scope.id3==$scope.id1){
				$scope.error.member31=true;
			}
			else{
				$scope.error.member31 = false;
			}
			alert(isValid);
			if(($scope.project.endDate > $scope.project.startDate)&& (!$scope.error.member12) && (!$scope.error.member23)
					&& (!$scope.error.member31) ){
				$scope.project.empList.push($scope.id1);
				$scope.project.empList.push($scope.id2);
				$scope.project.empList.push($scope.id3);
				debugger;
				$http.post("/admin/addProject", $scope.project).then(function(response){
					$scope.project = {};
					$location.url("/projectList");
					},
					function(response){
						alert("some error");
				})
				$state.go('showProject');
				/*$scope.error.date=false;
				$scope.error.member12=false;
				$scope.error.member23=false;
				$scope.error.member31=false;*/
			}else{
				if($scope.project.endDate < $scope.project.startDate)
					$scope.error.date=true;
				else
					$scope.error.date= false;
				/*$scope.error.member12=true;
				$scope.error.member23=true;
				$scope.error.member31=true;*/
			}
			
		}
		else{
			alert(isValid);
			if($scope.project.name==undefined){
				$scope.error.name=true;
			}else
				$scope.error.name=false;
			
			if($scope.project.desc==undefined){
				$scope.error.desc=true;
			}else
				$scope.error.desc=false;
			
			if($scope.project.startDate==undefined){
				$scope.error.startDate=true;
			}else
				$scope.error.startDate=false;
			
			if($scope.project.endDate==undefined){
				$scope.error.endDate=true;
			}
			else
				$scope.error.endDate=false;
			
			if($scope.project.manager==undefined){
				$scope.error.manager=true;
			}else
				$scope.error.manager=false;
			
			if($scope.id1==undefined){
				$scope.error.id1=true;
			}else
				$scope.error.id1=false;
			
			if($scope.id2==undefined){
				$scope.error.id2=true;
			}else
				$scope.error.id2=false;
			
			if($scope.id3==undefined){
				$scope.error.id3=true;
			}else
				$scope.error.id3=false;
			if($scope.project.endDate < $scope.project.startDate)
				$scope.error.date=true;
			else
				$scope.error.date=false;
		}
	};
	$scope.goBack = function(){
		$location.url("/projectList");
	}
}]);