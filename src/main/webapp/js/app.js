var app = angular.module('performance', [ 'ui.router','ngCookies']);

app.controller('mainCtrl', [ '$scope', '$location', '$http','$state','$window','$rootScope',
		function($scope, $location, $http,$state,$window,$rootScope) {
			$scope.getDesi = function(designation) {
                  //questionnaireType.desigType = designation;
                  //watching the change while selecting the link for questionnaire
                  $scope.$watch(angular.bind('questionnaireType.questType'),function(){
                	  $state.go('questionnaire',{},{reload:"questionnaire"});
                  })                
			}
			$scope.isActive = function(viewLocation) {
				return viewLocation === $location.path();
			};
			
			/*$scope.$on('checkIfAdmin',function(event,data){
				$scope.isAdmin();
			})*/
			
			$scope.logout = function(){
				$http.post("/logout").then(function(){
					$rootScope.authorized=false;
				})
			}
			
			/*$scope.isAdmin = function(){
				
				$http.get("/login").then(function(response){
					if(response.data.role[0].role=="admin"){
						return true;
					}
					else{
						return false;
					}
				},function(){
					console.log("is admin function error");
				})
			}*/
			
			/*$window.location.reload(){
				$scope.isAdmin();
			}*/
			
			$scope.getClass = function(url){
				if($state.current.url==url){
					return true;
				}
				else{
					return false;
				}
			}
			
		} ])	
		
		
		app.run(['$rootScope','$http','$location',function($rootScope,$http,$location){
			$rootScope.authenticated=false;
			debugger
			/*if($cookies.get('JSESSIONID')==null)
				$location.path('/login');*/
			$http.get("/login").then(function(response){
				if(response.data.role[0].role=="admin")
					$rootScope.authenticated = true;
				else
					$rootScope.authenticated = false;
			})
			
		}]);
		
app.factory('myHttpResponseInterceptor',['$q','$location','$rootScope',function($q,$location,$rootScope){
	  return {
		  responseError : function(response){
			  if(response.status === 401){
				  	$rootScope.authorized=false;
				    $location.path('/login');
				    return $q.reject(response);
			  }
			  else{
				    return $q.reject(response); 
			  }
		  }
	  }
	}]);	
		

app
		.config(function($stateProvider, $httpProvider, $urlRouterProvider) {
			$stateProvider.state('login', {
				url : "/login",
				views : {
					'' : {
						templateUrl : "html/login.html"
					}
				}
			}).state('emp', {
				url : "/emp",
				views : {
					'' : {
						templateUrl : 'html/employeeData.html'
					}
				}
			}).state('index', {
				url : "/index",
				views : {
					'' : {
						templateUrl : 'index.html'
					}
				}
			}).state('project', {
				url : "/project",
				views : {
					'' : {
						templateUrl : 'html/projectData.html',
						controller : 'projectCtrl'
					}
				}
			}).state('questions', {
				url : '/questions',
				data: { id: "" },
				reloadOnSearch:false,
				views : {
					'' : {
						templateUrl : 'html/qsnView.html'
					}
				}
			}).state('questions.questionnaire', {
				parent: 'questions',
				url: '/questions/',
				views : {
					'@questions' : {
						templateUrl : 'html/questionnaire.html'
					}
				},
				
			}).state('designation', {
				url : '/designation',
				views : {
					'' : {
						templateUrl : 'html/designation.html'
					}
				}
			}).state('empList', {
				url : "/empList",
				views : {
					'' : {
						templateUrl : 'html/empList.html'
					}
				}
			}).state('showProject',{
				url: '/projectList',
				views: {
					'': {
						templateUrl : 'html/showProject.html'
					}
				}
			}).state('reviews',{
				url: '/review',
				views: {
					'': {
						templateUrl : 'html/reviews.html'
					}
				}
			}).state('home',{
				url: '/home',
				views: {
					'': {
						templateUrl : 'html/empDashboard.html'
					}
				}
			})/*.state('notice',{
				url: '/home/notice',
				views:{
					'@home' :{
						templateUrl:'html/notice.html'
					}
				}
			})*/.state('profile',{
				url: '/profile',
				views:{
					'' :{
						templateUrl:'html/profile.html'
					}
				}
			})
			$urlRouterProvider.otherwise("/login");
			$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
			$httpProvider.interceptors.push('myHttpResponseInterceptor');
		});
