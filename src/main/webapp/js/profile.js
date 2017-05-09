app.controller("profileCtrl",['$scope','$http','$state',function($scope,$http,$state){
	$scope.emp = {};
	$scope.employee={};
	$scope.error={};
	$scope.editEmp={};
	$scope.projects=[];
	$scope.editPass={};
	$http.get("/login").then(function(response){
		$scope.employee = response.data;
		debugger
		$http.get("/user/getDetails?id="+$scope.employee.empId).then(function(response){
			$scope.emp = response.data;
			$scope.editEmp.contact = $scope.emp.contact;
			$scope.editEmp.email = $scope.emp.email;
		},function(){
			console.log("emp details not fetched");
		})
		$http.get("/user/getProjectOfEmp?id="+$scope.employee.empId).then(function(response){
			$scope.projects=response.data;
			debugger;
		},function(){
			console.log("emp projects error");
		})
	},function(){
		console.log("emp details fetched");
	})	
	
	$scope.resetEditProfile = function(){
		$scope.editEmp.contact = $scope.emp.contact;
		$scope.editEmp.email = $scope.emp.email;
		debugger
	}
	
	$('#editPassword').on('show.bs.modal',function(){
		$(this).find('form').trigger('reset');
		debugger
	})
	
	$scope.editProfileFunc = function(isValid){
		if(isValid){
			$scope.error={};
			debugger
			if(($scope.editEmp.contact != $scope.emp.contact) || ($scope.editEmp.email != $scope.emp.email)){
				$http.get("/user/checkForDuplicate?email="+$scope.editEmp.email+"&contact="+$scope.editEmp.contact).then(function(response){
					var duplicateContact;
					var duplicateEmail;
					debugger
					if($scope.editEmp.contact != $scope.emp.contact)
						duplicateContact = response.data.contact;
					else
						duplicateContact=false;
					if($scope.editEmp.email != $scope.emp.email)
						duplicateEmail=response.data.email;
					else
						duplicateEmail=false;
					debugger
					if(!duplicateEmail && !duplicateContact){
						$scope.error={};
						
						$http.post("/user/editProfile?id="+$scope.emp.empId,$scope.editEmp).then(function(){
							console.log("successfully edited");	
							debugger					
							$('#editProfile').modal('hide');
							$state.reload();
							$('.modal-backdrop').remove();
						},function(){
							console.log("not edited");
						})	
					}
					else{
						if(duplicateEmail)
							$scope.error.duplicateEmail = true;
						else
							$scope.error.duplicateEmail = false;
						if(duplicateContact)
							$scope.error.duplicateContact = true;
						else
							$scope.error.duplicateContact = false;
					}
				},function(){
					console("duplicate entries found in profile tab");
				})
			}
			else{
				$('#editProfile').modal('hide');
			}
			debugger
					
		}else{
			if($scope.emp.email=="" || $scope.emp.email==undefined){
				$scope.error.email=true;
			}else
				$scope.error.email=false;
			
			if($scope.emp.contact=="" || $scope.emp.contact==undefined)
				$scope.error.contact=true;
			else
				$scope.error.contact=false;
		}
	}
	
	$scope.editPasswordFunc = function(isValid){
		$scope.error={};
		debugger
		if(isValid){
			$scope.error = {};
			$scope.currentPassword = {};
			if($scope.editPass.password==$scope.editPass.confPassword){
				$http.get("/user/getPassword?id="+$scope.emp.empId).then(function(response){
					$scope.currentPassword = response.data.password;
					$scope.empId = response.data.empId;
					if($scope.oldPass!=$scope.currentPassword)
						$scope.error.oldPassword=true;
					else{
						debugger;
						$http.post("/user/editPassword?id="+$scope.empId,$scope.editPass).then(function(){
							console.log("password edited");
							$('#editPassword').modal('hide');
							$scope.editPass={};
							$scope.oldPass="";
						},function(){
							console.log("password not edited");
						})
					}
				},function(){
					console.log("password not fetched");
				})
			}			
		}
		else{
			if($scope.editPass.password==""||$scope.editPass.password==undefined)
				$scope.error.password=true;
			else
				$scope.error.password=false;
			
			if($scope.oldPass==""||$scope.oldPass==undefined)
				$scope.error.reqOldPass=true;
			else
				$scope.error.reqOldPass=false;
			
			if($scope.editPass.confPassword==""||$scope.editPass.confPassword==undefined)
				$scope.error.confPassword=true;
			else
				$scope.error.confPassword=false;		
		}
	}
}])