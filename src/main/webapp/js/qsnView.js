app.controller('qsnViewCtrl',['$scope','$http','$location','$state','$rootScope',function($scope,$http,$location,$state,$rootScope){
	$scope.designations=[];
	$rootScope.designation = {};
	$scope.firstTimeFlag= false;
	$scope.error={}
	$scope.questions=[];
	$scope.questionnaire = {
		question: '',
		yesOrNo: '',
		desg: {
			desg: ''
		}
}
	$http.get("/admin/getDesignation").then(function(response) {
		$scope.designations = response.data;
		
		if(!$location.search().designation)
		$location.search('designation',$scope.designations[0].desg);
		debugger
		$scope.getQuestions();
	}, function(response) {
		alert("failed");
	})
	
	$scope.selectedDesg = function(){
	$scope.firstTimeFlag=true;
	return $location.search().designation;
	}


if($location.search().designation)
	$scope.firstTimeFlag=true;
	$scope.selectedDesg();
	
	$scope.getDesg = function(desig){
		
		$location.search('designation',desig);
		debugger;
		$scope.getQuestions();
	}
	
	$scope.getQuestions=function()
	{
		debugger;
		if($location.search().designation)
			{
		$http.post("/admin/getQuestions",$location.search().designation).then(function(response){
			$scope.questions = response.data;	
			debugger
		},function(response){
			console.log("question retrieval error");
		})
			}
	}
	
	$scope.getActiveTab= function(tabId){
		debugger
		if($location.search().designation==tabId){
			return true;
		}
		else
			return false;
	}
	
	$scope.getQuestions();
	
	$scope.addQuestion = function(isValid){
		/*$('#addQuestion').modal('hide');
		*/
		if(isValid){
			$('#addQuestion').modal('hide');			
			$scope.questionnaire.desg.desg = $location.search().designation;
			debugger
			$http.post("/admin/addQuestion",$scope.questionnaire).then(function(response){
				console.log("success");
				$scope.getQuestions();
				$scope.questionnaire = {
						question: '',
						yesOrNo: '',
						desg: {
							desg: ''
						}
				}
			},function(response){
				console.log("questionnaire error");
			})
		}
		else{
			debugger
			if($scope.questionnaire.question=="" || $scope.questionnaire.question==undefined)
				$scope.error.question = true;
			else
				$scope.error.question = false;
			debugger
			if($scope.questionnaire.yesOrNo=="" || $scope.questionnaire.yesOrNo==undefined)
				$scope.error.yesOrNo = true;
			else
				$scope.error.yesOrNo = false;
		}
	}
}])