app.controller("empListCtrl",['$scope','$location','$http','$state',
                              function($scope,$location,$http,$state){
	$scope.empList = [];
	$scope.viewEmp={};
	$http.get('/admin/getEmpList').then(function(response){
		$scope.empList = response.data;
		debugger
		console.log("empList fetched");
	},function(){
		console.log("empList not fetched");
	})
	$scope.addEmployee = function(){
		$location.url("/emp");
	}	
	$scope.editEmpAdmin = function(index){
		debugger
		sessionStorage.setItem("editableEmp",JSON.stringify($scope.empList[index]));
		
		$state.go("emp");
	}
	
	$scope.viewEmployee = function(index){
		$scope.viewEmp = $scope.empList[index];
	}
	$scope.editProfile = function(){
		$('#viewEmpDetails').modal('hide');
		$('.modal-backdrop').remove();
		sessionStorage.setItem("editableEmp",JSON.stringify($scope.viewEmp));
		//$state.go("emp");
		$location.url('/emp');
	}
}])