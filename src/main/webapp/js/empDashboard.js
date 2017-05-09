app.controller('empDashCtrl',['$scope','$http','$state',function($scope,$http,$state){
	$scope.error;
	$http.get('/login').then(function(response){
		$scope.emp = response.data;
		getMembers();
		console.log("got Logged in employee");
	},function(){
		console.log("cant get logged in employee");
	})
	
	/*$http.get("/login").success(function(res){
        $scope.emp=angular.copy(res);
        debugger
	}).error(function(err){
		debugger
	})
	*/
	/*$scope.managerProjectList=[];
	$scope.members=[];
	$scope.project={};*/
	var getMembers = function(){
		$http.post('/user/getMembers',$scope.emp).then(function(response){
			$scope.empList = response.data;
			/*for(i=0;i<$scope.empList.length;i++){
				debugger
				if($scope.empList[i].reviewedEmp.projectId!== null){
					$scope.project = $scope.empList[i].reviewedEmp.projectId;
					break;
				}
			}*/
			console.log("to be reviewed employees fetched");
			},function(){
				console.log("to be reviewed employees error");
			})
	}
	
	$scope.reviewedRelation;
	$scope.answer={}
	$scope.empList={};
	$scope.qsnList=[];
	
	$scope.setReviewQsn = function(reviewId){
		$scope.reviewedRelation = reviewId;
		$http.post("/user/generateQuuestionnaire",$scope.reviewedRelation).then(function(response){
			$scope.qsnList = response.data;
			$scope.answer={};
			console.log("fetched qsns");
		},function(){
			console.log("qsns not fetched");
		})
	}
	
	$scope.submitAnswer = function(isValid){
		/*$scope.answer.questions=$scope.qsnList;*/

		/*$('#reviewModal').modal('hide');
		*/
		if(isValid){
			$('#reviewModal').modal('hide');
			console.log($scope.answer);
			debugger
			$http.post("/user/addAnswers/?reviewId="+$scope.reviewedRelation.reviewId,$scope.answer).then(function(response){
				console.log("answers saved");	
				getMembers();
			},function(){
				console.log("answers not saved");
			})
		}
		else{
			debugger
			if($scope.answer.length< $scope.qsnList.length || $scope.answer.length==undefined){
				$scope.error=true;
			}
			else{
				$scope.error=false;				
			}
		}
	}
}])