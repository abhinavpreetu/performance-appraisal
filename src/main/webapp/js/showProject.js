app.controller('showProjectCtrl',['$scope','$http','$state','$location',function($scope,$http,$state,$location){
	$scope.projectList=[];
	$scope.projectDetails={};
	$scope.project={};
	$scope.newProject={};
	$scope.error={};
	$http.get('/admin/getProjects').then(function(response){
		$scope.projectList = response.data;
		debugger
	},function(response){
		console.log("project load error");
	});
	
	
	$scope.openProject = function(index){
		$scope.project = $scope.projectList[index];
		$http.post('/admin/showDetails',$scope.projectList[index]).then(function(response){			
			$scope.projectDetails = response.data;
			debugger
			console.log("details successful");
			
		},function(){
			console.log("details error");
		})
	};
	
	$scope.generateQsn = function(){
		$('#projectDetails').modal('hide');
		$http.post('/admin/triggerAction',$scope.project).then(function(){
			console.log("action triggered");
		},function(){
			console.log("action not triggered");
		})
	};
	
	$scope.addProject = function(){
		$location.url("/project");
	}
	
	$scope.resetEditProject = function(index){
		$scope.project= $scope.projectList[index];
		$scope.newProject.name = $scope.project.name;
		$scope.newProject.desc = $scope.project.desc;
		debugger
	}
	
	$scope.editProjectFunc = function(isValid){
		if(isValid){
			$('#editProject').modal('hide');
			$http.post("/admin/editProject?id="+$scope.project.projectId,$scope.newProject).then(function(response){
				console.log("all correct");
				$state.reload();
				$('.modal-backdrop').remove();
			},function(){
				console.log("project not edited");
			})
		}
		else{
			if($scope.newProject.name==""||$scope.newProject.name==undefined)
				$scope.error.name=true;
			else
				$scope.error.name=false;
			if($scope.newProject.desc==""||$scope.newProject.desc==undefined)
				$scope.error.desc=true;
			else
				$scope.error.desc=false;
		}
	}
}])