app.controller("reviewsCtrl",['$scope','$http','$location',function($scope,$http,$location){
	$scope.triggeredProjects=[];
	$scope.reviewedEmps = [];
	$scope.comment = "";
	$scope.answers;
	$http.get("/admin/getTriggeredProjects").then(function(response){
		$scope.triggeredProjects = response.data;
		console.log("triggered projects fetched");
		debugger
	},function(){
		console.log("triggered projects error");
	})
	
	$scope.getEmps = function(empId,pid){
		$http.get("/admin/getReviewedEmps?id="+empId+"&pid="+pid).then(function(response){
			$scope.reviewedEmps = response.data;
			$scope.comment = "";
			$scope.answers = undefined;
			debugger
			console.log("reviewed emps fetched");
		},function(){
			console.log("reviewed emps not fetched");
		})
	}
	
	$scope.getReviewedEmps = function(parentIndex,childIndex){
		$scope.reviewingEmp = $scope.triggeredProjects[parentIndex].empList[childIndex];
		debugger;
		$scope.getEmps($scope.reviewingEmp.empId,$scope.triggeredProjects[parentIndex].projectId);
	}
	
	$scope.getReviewedEmpsForManager = function(index){
		$scope.reviewingEmp = $scope.triggeredProjects[index].manager.mngId;
		debugger;
		$scope.getEmps($scope.reviewingEmp.empId,$scope.triggeredProjects[index].projectId);
	}
	
	$scope.getAnswers = function(index){
		$http.get("/admin/getAnswers?id="+$scope.reviewedEmps[index].reviewId).then(function(response){			
			if(response.data.length == 0){
				$scope.comment = "Not reviewed Yet";
				$scope.answers = undefined;
				debugger
			}
			else{
				$scope.comment = "";
				$scope.answers = response.data;
				debugger
			}
			debugger;
			console.log("answers fetched");
		},function(){
			console.log("answers not fetched");
		})
	}
}])