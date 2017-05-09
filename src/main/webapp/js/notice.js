app.controller("noticeCtrl",['$scope','$http','$state','getEmpId',function($scope,$http,$state,getEmpId){
	var emp = getEmpId.EmpId;
	$scope.reviewedRelation;
	/*$scope.answer = {
			answers:[
				answer:''
			],
			reviewRelationId :{},
			questions : [
			             questionId:{}
	
			
			]
			
	}*/
	
	$scope.answer={};
	
	
	debugger
	$scope.empList=[];
	$scope.qsnList=[];
	$http.post('/user/getMembers',emp).then(function(response){
		$scope.empList = response.data;
		debugger
		console.log("to be reviewed employees fetched");
	},function(){
		console.log("to be reviewed employees error");
	})
	$scope.setReviewQsn = function(index){
		$scope.reviewedRelation = $scope.empList[index];
		debugger
		$http.post("/user/generateQuuestionnaire",$scope.reviewedRelation).then(function(response){
			$scope.qsnList = response.data;
			$scope.answer={};
			debugger;
			console.log("fetched qsns");
		},function(){
			console.log("qsns not fetched");
		})
	}
	
	$scope.submitAnswer = function(){
		/*$scope.answer.questions=$scope.qsnList;*/
		debugger;

		
		console.log($scope.answer);
		$http.post("/user/addAnswers/?reviewId="+$scope.reviewedRelation.reviewId,$scope.answer).then(function(response){
			console.log("answers saved");
			$state.go("home.notice");
		},function(){
			console.log("answers not saved");
		})
	}
}])