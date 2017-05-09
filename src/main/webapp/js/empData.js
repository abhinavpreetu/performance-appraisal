app.controller('empCtrl',['$scope','$http','$location','$state',
                          function($scope,$http,$location,$state){
	$scope.designation=[];
	$scope.roles=["admin","user"];
	$scope.validLength = 10;
	$scope.error={};
	$scope.isBeingEdited=false;
	$scope.editedEmp={}
	$scope.checkIfNum = function(event){
		return event.keyCode>=48 && event.keyCode<58
	}
	
	
	
	
	if($scope.designation.length==0){
		$http.get("/admin/getDesignation").then(function(response){
			debugger
			$scope.designation = response.data;
		},function(response){
			alert("failed"); 
		})
	}
	/*$scope.employee={
		fname: '',
		lname: '',
		contact: '',
		joinDate: '',
		birthDate: '',
		email: '',
		password: '',
		confPassword: '',
		designation: {
			desg: ''
		}
	}*/
	$scope.employee={
		designation:{}
	}
	/*$scope.employee={
		
		designation:{}
	}*/
	
	
	
	$scope.editEmp = JSON.parse(sessionStorage.getItem("editableEmp"));
	debugger
	if(sessionStorage.getItem("editableEmp")!=null){
		$scope.isBeingEdited=true;
		var birthDate = new Date($scope.editEmp.birthDate);
		var joinDate = new Date($scope.editEmp.joinDate)
		$scope.employee.fname=$scope.editEmp.fname;
		$scope.employee.lname=$scope.editEmp.lname;
		$scope.employee.contact=$scope.editEmp.contact;
		$scope.employee.joinDate=joinDate;
		$scope.employee.birthDate=birthDate;
		$scope.employee.email=$scope.editEmp.email;
		$scope.employee.password=$scope.editEmp.password;
		$scope.employee.confPassword=$scope.editEmp.password;
		$scope.employee.empId = $scope.editEmp.empId
		$http.get("/admin/getDesignation").then(function(response){
			
			/*angular.forEach(response.data,function(data){
				$scope.designation.push(data);
			})*/
			if($scope.designation.length==0)
				$scope.designation=response.data;
		switch($scope.editEmp.designation.desg){
		case 'Consultant' :
			$scope.employee.designation.desg = $scope.designation[0].desg;
			break;
		case 'Project Manager' :
			$scope.employee.designation.desg = $scope.designation[1].desg;
			break;
		case 'Sr. Consultant' :
			$scope.employee.designation.desg = $scope.designation[2].desg;
			break;
		case 'Technical Architect' :
			$scope.employee.designation.desg = $scope.designation[3].desg;
			break;
		}
			
		},function(response){
			alert("failed"); 
		})
		
		//$scope.designationOfEmp=$scope.editEmp.designation.desg;
		$scope.roleName=$scope.editEmp.role[0];
		debugger
		//console.log(typeof $scope.editEmp.birthDate);
	}
	debugger
	
	$scope.employee.role=[];
	
	$scope.goBack = function(){
		sessionStorage.removeItem("editableEmp");
		$location.url("/empList");
	}
	$scope.sbtForm = function(isValid){
		if(isValid){	
			debugger
			$http.get("/user/checkForDuplicate?email="+$scope.employee.email+"&contact="+$scope.employee.contact).then(function(response){
				var duplicateEmail=response.data.email;
				var duplicateContact = response.data.contact;
				debugger	
				if(($scope.employee.joinDate > $scope.employee.birthDate) && !duplicateEmail && !duplicateContact){
					$scope.error.date=false;
					/*
					if($scope.roleName!= undefined && $scope.employee.role.lenght==0)*/
						$scope.employee.role.push($scope.roleName);
						//$scope.employee.designation.desg = $scope.designationOfEmp;
					debugger
					$http.post("/admin/addEmployee",$scope.employee).then(function(response){
						$state.reload();
						},
						function(response){
							alert("error");
						})
					sessionStorage.removeItem("editableEmp");
					$state.go("empList");
				}
				else{
						$scope.error={};
						if(duplicateEmail)
							$scope.error.duplicateEmail=true;
						if(duplicateContact)
							$scope.error.duplicateContact=true;
						if($scope.employee.joinDate <= $scope.employee.birthDate)
							$scope.error.date=true
					}
			},function(){
				console.log("duplicate entries found");
			})			
		}
		else{
			debugger
			if($scope.employee.fname==""||$scope.employee.fname==undefined){
				$scope.error.name=true;
			}else
				$scope.error.name=false;
			
			if($scope.employee.joinDate < $scope.employee.birthDate){
				$scope.error.date=true;
			}else
				$scope.error.date=false;
			
			if($scope.employee.email==""||$scope.employee.email==undefined){
				$scope.error.email=true;
			}else
				$scope.error.email=false;
			
			if($scope.employee.designation.desg==""||$scope.employee.designation.desg==undefined){
				$scope.error.designation=true;
			}else
				$scope.error.designation=false;
			
			if($scope.employee.contact==""||$scope.employee.contact==undefined){
				$scope.error.contact=true;
			}else
				$scope.error.contact=false;
			
			if($scope.roleName==""||$scope.roleName==undefined){
				$scope.error.role=true;
			}else
				$scope.error.role=false;
			
			if($scope.employee.password==""||$scope.employee.password==undefined){
				$scope.error.password=true;
			}else
				$scope.error.password=false;
			
			if($scope.employee.confPassword==""||$scope.employee.confPassword==undefined){
				$scope.error.confPassword=true;
			}else
				$scope.error.confPassword=false;
			
			if($scope.employee.birthDate==""||$scope.employee.birthDate==undefined){
				$scope.error.birthDate=true;
			}else
				$scope.error.birthDate=false;
			
			if($scope.employee.joinDate==""||$scope.employee.joinDate==undefined){
				$scope.error.joinDate=true;
			}else
				$scope.error.joinDate=false;
		}
	}
}])

app.directive('nameErr', function(){
	return {
		require : 'ngModel',
		link : function(scope, elm, attrs, ctrl) {
			var NAME_REGEX = /^[A-Za-z]+[A-Za-z]*$/;
			ctrl.$validators.nameErr = function(modelValue, viewValue) {
				if (NAME_REGEX.test(viewValue)) {
					return true;
				}
				return false;
			}
		}
	}
})

app.directive('emailErr', function(){
	return{
		require: 'ngModel',
		link: function(scope, elm, attrs, ctrl){
			var EMAIL_REGEX = /^[A-z0-9]+@[A-z0-9]+\.[A-z]+$/;
			ctrl.$validators.emailErr = function(modelValue, viewValue){
				if(EMAIL_REGEX.test(viewValue)){
					return true;
				}
				return false;
			}
		}
	}
})

/*app.directive('contactErr', function($parse){
	return{
		scope:{
			blockLength: '='
		},
		link: function(scope, elm, attrs){
			elm.bind('keypress',function(event){
				if(elm[0].value.lenght >scope.blockLenght){
					event.preventDefault();
					return false;
				}
			})
										
		}
	}
})*/

/*app.directive('passErr',function(){
	return{
		require: 'ngModel',
		link: function(scope, elm, attrs, ctrl){
			//var PASS_REGEX = /^(?=.*\d)(?=.*[A-Z])(?=.*\W).+$/;
			ctrl.$validators.passErr = function(modelValue, viewValue){
				if(PASS_REGEX.test(viewValue)){
					return true;
				}
				return false;
			}
		}
	}
})*/
