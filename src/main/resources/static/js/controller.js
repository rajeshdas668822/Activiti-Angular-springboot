app.controller('usersController', function($scope) {
	$scope.headingTitle = "User List";
});

app.controller('rolesController', function($scope) {
	$scope.headingTitle = "Roles List";
});

app.controller('orderController', function($scope) {

});

app.controller('loginController', function($scope, $http, $location,
		userService) {
	var urlBase = "http://localhost:8080/validateUser";
	var vaildUser = false;

	$scope.message = 'hello';
	$scope.user = userService.user;
	$scope.verifyUser = function(user) {
		// var newUser = angular.copy(user);
		console.log(user);

		$http.post(urlBase, user).success(function(data) {
			$scope.vaildUser = data;

			if (data) {
				console.log("Should change url");
				$location.path("/taskList");
				if (typeof(Storage) !== "undefined") {
				    // Store
				    localStorage.setItem("user", user);
				    // Retrieve
				   // document.getElementById("result").innerHTML = localStorage.getItem("lastname");
				} 
			} else {
				$location.path("/login");
			}
		}).error(function(error) {
			$scope.data.orderError = error;
		})
	}
});

app.controller('assignController', function($scope,$uibModalInstance){
	//$uibModalInstance.close({"sdsd":sds, "s"})
	
})

app.controller('taskController',function($scope, $http,userService ,$uibModal,$location){
	$scope.pendingTask =[{}];
	$scope.initTask=[{}];
	$scope.workingTask=[{}];
	$scope.pendingCancelTask=[{}];
	$scope.partialFillTask = [{}];
	$scope.filledTask = [{}];

   
    var taskUrl =   "http://localhost:8080/loadTask";
    var approveUrl = "http://localhost:8080/approveOrder"
    var assignUrl = "http://localhost:8080/assignOrder"
    var cancelUrl = "http://localhost:8080/cancelOrder"
    var rejectUrl = "http://localhost:8080/rejectOrder"
    var fillUrl = "http://localhost:8080/fillOrder"
    var cancelWorkingUrl = "http://localhost:8080/cancelWorkingOrder"
 		
	$scope.user = userService.user;
	init();
	
	  $scope.taskStatus = {
		        isPendingTaskExist:  false,
		        isInitTaskExist:   false,
		        isWorkingExist : false,
		        isPendingCancelExist :  false,
		        isPartialFillExist :  false,
		        isFillExist :  false,
		        isInitExist : false
	   };

	
	function init(){
		 
		//$http.get(taskUrl,$scope.user).success(function(data) {	
		$http.post(taskUrl,$scope.user).success(function(data) {	
			 $scope.pendingTask = data['Pending'];  
			 $scope.initTask = data['Initial'];
			 $scope.workingTask = data['Working']
			 $scope.filledTask = data['Filled']
			 $scope.partialFillTask = data['Partially Filled']
			 $scope.pendingCancelTask = data['Pending Cancel']
			 console.log("Data Geting returned is "+data['Pending']);
			 console.log("Data Geting returned is "+data['Initial']);
			 console.log("Data Geting returned is "+data['Working']);
             console.log("Data Geting returned is "+data['Pending Cancel']);
             console.log("Data $scope.filledTask is "+$scope.filledTask);
			 
           
             if(angular.isDefined(data['Pending'])){
                console.log("isPendingTaskExist :  "+data['Pending'].length);
                $scope.taskStatus.isPendingTaskExist = data['Pending'].length > 0 ? true : false;
             }if(angular.isDefined(data['Working'])){
                console.log("isWorkingExist :  "+data['Working'].length);
                $scope.taskStatus.isWorkingExist = data['Working'].length > 0 ? true : false;                
                console.log("taskStatus.isWorkingExist :  "+$scope.taskStatus.isWorkingExist);
             } if (angular.isDefined(data['Initial'])){
                console.log("isInitTaskExist :  "+data['Initial'].length);
                $scope.taskStatus.isInitTaskExist = data['Initial'].length > 0 ? true : false;
                console.log("taskStatus.isInitTaskExist :  "+$scope.taskStatus.isInitTaskExist);
             }if (angular.isDefined(data['Pending Cancel'])){
                  console.log("isPendingCancelExist :  "+data['Pending Cancel'].length);
                  $scope.taskStatus.isPendingCancelExist = data['Pending Cancel'].length > 0 ? true : false;
                  console.log("taskStatus.isPendingCancelExist :  "+$scope.taskStatus.isPendingCancelExist);
             }if (angular.isDefined(data['Filled'])){
                console.log("isFillExist :  "+data['Filled'].length);
                $scope.taskStatus.isFillExist = data['Filled'].length > 0 ? true : false;
                console.log("taskStatus.isFillExist :  "+$scope.taskStatus.isFillExist);
             }if (angular.isDefined(data['Partially Filled'])){
                  console.log("isPartialFillExist :  "+data['Partially Filled'].length);
                  $scope.taskStatus.isPartialFillExist = data['Partially Filled'].length > 0 ? true : false;
                  console.log("taskStatus.isPartialFillExist :  "+$scope.taskStatus.isPartialFillExist);
             }
             
		}).error(function(error) {
				$scope.data.orderError = error;
	    }) 
		 
		 
	 }
	
	
	   $scope.cancelWorking = function(task){
	          $http.post(cancelWorkingUrl,task).success(function(data) {
	        	  console.log(" Got Canceled:"+data);
	        	   init();
	          }).error(function(error) {
					$scope.data.orderError = error;
	  	      }) 
			};
	
		$scope.approve = function(task){
          $http.post(approveUrl,task).success(function(data) {
        	  console.log(" Got Approved :"+data);
        	   init();
          }).error(function(error) {
				$scope.data.orderError = error;
  	      }) 
		};

		$scope.cancel = function(task){
		$http.post(cancelUrl,task).success(function(data) {
                	  console.log(" Got Canceled :"+data);
                	  init();
                  }).error(function(error) {
        				$scope.data.orderError = error;
          	      })
		};


		$scope.reject = function(task){
        $http.post(rejectUrl,task).success(function(data) {
                        	  console.log(" Got Rejected :"+data);
                        	 init();
                          }).error(function(error) {
                				$scope.data.orderError = error;
                  	      })
        };
        
          
        
          //for Fill 
        
           $scope.fill = function(task){
        	   var promise = $uibModal.open({
 			      animation:true,
 			      ariaLabelledBy: 'modal-title',
 			      ariaDescribedBy: 'modal-body',
 			      templateUrl: 'group-template.html',
 			      //scope:dlgScope,
 			      controller:function($scope, $uibModalInstance, taskFromModal,$http) { 			    	  
 			        $scope.order = {};
 			    	$scope.test = 'hello';  
 			    	$scope.order = taskFromModal;
 			    	$uibModalInstance.close($scope.order);
 			      },
 			      size: task,
 			      resolve: {
 			        taskFromModal: function () {
 			        	return task;
 			        }
 			      }
 			    }).result;

        	   promise.then(function (response) {
        		   $scope.order = response;
        		   console.log("Order After Fill :"+$scope.order);
        		   $http.post(fillUrl,$scope.order).success(function(data) {
		                   console.log(" Got Filled :"+data);
		                   init();
		               }).error(function(error) {
		         		   $scope.data.orderError = error;
		           })
 			      
 			      
 			    }, function () {
// 			      $log.info('Modal dismissed at: ' + new Date());
 			    });
        	    
        	   
           }
        
        
		   //for Assign
		
		   $scope.assign = function(task){
			
			var dlgScope = $scope.$new(true);
			dlgScope.selected = null;
			dlgScope.ok = function(selected){
			console.log(selected);	
			userService.requestinfo.user = selected;
			modalInstance.close();
			};
			
			
			userService.requestinfo.order = task;
			dlgScope.users=[{userId:'kermit',userName:"Kermit User"},{userId:'fozzie',userName:"Fozzi User"}];
			 var modalInstance = $uibModal.open({
			      animation:true,
			      ariaLabelledBy: 'modal-title',
			      ariaDescribedBy: 'modal-body',
			      templateUrl: 'myModalContent.html',
			      scope:dlgScope,

			     //controller:'assignController',
			     
			      size: "md",
			      resolve: {
			        items: function () {
			           
			        }
			      }
			    });

			    modalInstance.result.then(function (selectedItem) {
			    	
			    	//userService.requestinfo.user = selectedItem;
			    	
			    	$http.post(assignUrl,userService.requestinfo)
		             .success(function(data) {
		            	 
		            	 
		                  console.log(" Got Assigned :"+data);
		                  init();
		                }).error(function(error) {
		                    $scope.data.orderError = error;
		              });
			      
			      
			    }, function () {
//			      $log.info('Modal dismissed at: ' + new Date());
			    });
		};
	

});	

 app.controller('homeController',
		function($scope, $http, $location, userService) {
	    var orderUrl =  "http://localhost:8080/submitOrder";	 
	  //  var taskUrl =   "http://localhost:8080/loadTask";
			$scope.message="hello";
			user = localStorage.getItem('user');			
			$scope.user = userService.user;
			console.log($scope.user);
			
			
			//Load task list 
			/*$scope.pendingTask =[{}];
			$scope.initTask=[{}];
			$scope.workingTask=[{}];
			$scope.pendingCancelTask=[{}];
			$scope.partialFillTask = [{}];
			$scope.filledTask = [{}];

			
			$scope.taskStatus = {
			        isPendingTaskExist:  false,
			        isInitTaskExist:   false,
			        isWorkingExist : false,
			        isPendingCancelExist :  false,
			        isPartialFillExist :  false,
			        isFillExist :  false,
			        isInitExist : false
		   };
			function init(){
				 
				//$http.get(taskUrl,$scope.user).success(function(data) {	
				$http.post(taskUrl,$scope.user).success(function(data) {	
					 $scope.pendingTask = data['Pending'];  
					 $scope.initTask = data['Init'];
					 $scope.workingTask = data['Working']
					 $scope.filledTask = data['Filled']
					 $scope.partialFillTask = data['Partially Filled']
					 $scope.pendingCancelTask = data['Pending Cancel']
					 console.log("Data Geting returned is "+data['Pending']);
					 console.log("Data Geting returned is "+data['init']);
					 console.log("Data Geting returned is "+data['Working']);
		             console.log("Data Geting returned is "+data['Pending Cancel']);
		             console.log("Data $scope.filledTask is "+$scope.filledTask);
					 
		           
		             if(angular.isDefined(data['Pending'])){
		                console.log("isPendingTaskExist :  "+data['Pending'].length);
		                $scope.taskStatus.isPendingTaskExist = data['Pending'].length > 0 ? true : false;
		             }if(angular.isDefined(data['Working'])){
		                console.log("isWorkingExist :  "+data['Working'].length);
		                $scope.taskStatus.isWorkingExist = data['Working'].length > 0 ? true : false;                
		                console.log("taskStatus.isWorkingExist :  "+$scope.taskStatus.isWorkingExist);
		             } if (angular.isDefined(data['Init'])){
		                console.log("isInitTaskExist :  "+data['Init'].length);
		                $scope.taskStatus.isInitTaskExist = data['Init'].length > 0 ? true : false;
		                console.log("taskStatus.isInitTaskExist :  "+$scope.taskStatus.isInitTaskExist);
		             }if (angular.isDefined(data['Pending Cancel'])){
		                  console.log("isPendingCancelExist :  "+data['Pending Cancel'].length);
		                  $scope.taskStatus.isPendingCancelExist = data['Pending Cancel'].length > 0 ? true : false;
		                  console.log("taskStatus.isPendingCancelExist :  "+$scope.taskStatus.isPendingCancelExist);
		             }if (angular.isDefined(data['Filled'])){
		                console.log("isFillExist :  "+data['Filled'].length);
		                $scope.taskStatus.isFillExist = data['Filled'].length > 0 ? true : false;
		                console.log("taskStatus.isFillExist :  "+$scope.taskStatus.isFillExist);
		             }if (angular.isDefined(data['Partially Filled'])){
		                  console.log("isPartialFillExist :  "+data['Partially Filled'].length);
		                  $scope.taskStatus.isPartialFillExist = data['Partially Filled'].length > 0 ? true : false;
		                  console.log("taskStatus.isPartialFillExist :  "+$scope.taskStatus.isPartialFillExist);
		             }
		             
				}).error(function(error) {
						$scope.data.orderError = error;
			    }) 
				 
				 
			 }*/
			
			
			
			 
			
			//Order service			
			 $scope.order = userService.order;	
			 $scope.standAloneOptions = [{code:true,value:'Yes'},{code:false, value:'No'}];			
			 $scope.sendOrder = function(order){
				 console.log(order);
				 $http.post(orderUrl, order,user).success(function(data) {		
					 $location.path("/taskList")
					}).error(function(error) {
						$scope.data.orderError = error;
					})
			 }
			 
			 
			 $scope.oneAtATime = true;
			 
			  $scope.status = {
			    isCustomHeaderOpen: false,
			    isFirstOpen: true,
			    isFirstDisabled: false
			  };
			 
			 
			
			
			
		});
