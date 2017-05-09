app.controller('questCtrl',['$scope','$http','$location','$state','$rootScope',function($scope,$http,$location,$state,$rootScope){
	$scope.questionnaire = {
			question: '',
			yesOrNo: '',
			desg: {
				desg: ''
			}
	}
	$scope.questionnaire.desg = $rootScope.designation;
	debugger;
	
	$http.post("/admin/getQuestions",$scope.questionnaire.desg).then(function(response){
		$scope.questions = response.data;	
		debugger
	},function(response){
		console.log("question retrieval error");
	})
	
	$scope.questions=[];
	$scope.deleteQsn= function(question){
		$http.post("/admin/deleteQsn",question).then(function(response){
			console.log("deletion successful");
			$state.reload();
		},function(response){
			console.log("deletion error");
		})
	}
	/*$scope.$on("sendDesignation",function(event, data){
		$scope.questionnaire.desg = data;
		debugger
		
		$http.post("/admin/getQuestions",$scope.questionnaire.desg).then(function(response){
			$scope.questions = response.data;	
			debugger
		},function(response){
			console.log("question retrieval error");
		})
	})*/
  	 // $state.go('questionnaire',{},{reload:"questionnaire"});
      
		debugger
		
	$scope.addQuestion = function(){
		debugger
		$http.post("/admin/addQuestion",$scope.questionnaire).then(function(response){
			console.log("success");
			$http.post("/admin/getQuestions",$scope.questionnaire.desg).then(function(response){
				$scope.questions = response.data;	
				debugger
			},function(response){
				console.log("question retrieval error");
			})
		},function(response){
			console.log("questionnaire error");
		})
	}
	
}])

	