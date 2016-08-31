var app = angular.module('activiti-demo', ['ngRoute','ngResource', 'ngMaterial','ui.bootstrap']);

app.config(function($routeProvider){
    $routeProvider
        .when('/home',{
            templateUrl: '/views/home.html',
            controller: 'homeController'
        })
        .when('/login',{
            templateUrl: '/views/login.html',
            controller: 'loginController'
        })
        .when('/taskList',{
            templateUrl: '/views/taskList/pending-tasklist.html',
            controller: function() {
            	
            }
        }).when('/createOrder',{        	
        	//templateUrl: '/views/order/createOrder.html',
        	templateUrl: '/views/taskList/order-creation.html',
        	controller: 'orderController'
        	
        })
        .otherwise(
            { redirectTo: '/login'
            	
            }
        );
});


angular.module('activiti-demo').controller('AccordionDemoCtrl', function ($scope) {
	  $scope.oneAtATime = true;

	  $scope.groups = [
	    {
	      title: 'Dynamic Group Header - 1',
	      content: 'Dynamic Group Body - 1'
	    },
	    {
	      title: 'Dynamic Group Header - 2',
	      content: 'Dynamic Group Body - 2'
	    }
	  ];

	  $scope.items = ['Item 1', 'Item 2', 'Item 3'];

	  $scope.addItem = function() {
	    var newItemNo = $scope.items.length + 1;
	    $scope.items.push('Item ' + newItemNo);
	  };

	  $scope.status = {
	    isCustomHeaderOpen: false,
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	});




