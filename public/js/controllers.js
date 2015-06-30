'use strict';

/* Controllers */

angular
  .module('BeerControllers', [])
  .controller('LoginCtrl',['$scope', '$http', '$location', function($scope, $http, $location) {
	  $scope.doSend = function() {
		  console.log("I did it");
		  $http({
				  method: 'POST',
				  url: "/login",
				  data: {
					  user: $scope.user,
					  password: $scope.password
				  },
				  headers: {
				        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
				  },
				  transformRequest: function(obj) {
				        var str = [];
				        for(var p in obj)
				        	if (obj[p] !== undefined) {
				        		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				        	}
				        return str.join("&");
				  }
		  }).success(function(data) {
				    console.log("Login successful data", data);
			    	$location.path("/beers"); 
			  });
	  };
  }])
  .controller('BeerListCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {

    $scope.getBeerList = function() {
    	$http.get('/beer/list').success(function(data) {
	      $scope.beers = data;
	    });
    }
    
    $scope.orderProp = 'alcohol';
    $scope.editBeer = function(beer) {
    	$location.path("/edit/"+beer)
    }
    $scope.deleteBeer = function(beer) {
    	  console.log("Deleting beer "+beer);
		  $http({
			  method: 'POST',
			  url: "/beer/delete",
			  data: {id: beer}
		  })
		  .success( function(data) {
			console.log("Deleted beer"); 
			$scope.getBeerList();
		  });
	}
    $scope.getBeerList();
  }])
  .controller('BeerDetailCtrl', ['$scope', '$routeParams', '$http', 
                                 function($scope, $routeParams, $http) {
	  	$scope.action = "Create new"
	    $http.get('beer/details?id='+ $routeParams.beerId).success(function(data) {
	        $scope.beer = data;
	      });
  }])
  .controller('BeerEditCtrl', ['$scope', '$routeParams', '$http', 
                                 function($scope, $routeParams, $http) {
	  	$scope.action = "Edit"
	    $http.get('/beer/details?id='+ $routeParams.beerId).success(function(data) {
	    	
	    	for (var param in data) {
	    		$scope[param] = data[param]
	    	}
	      });
	  	  $scope.doAction = function() {
	  		  $http({
	  			  method: 'POST',
				  url: "/beer/edit",
				  data: {
					  id: $scope.id,
					  name:$scope.name, 
					  description:$scope.description, 
					  img: $scope.img,
					  alcohol: $scope.alcohol,
					  availabilty: $scope.availability,
					  brewery: $scope.brewery,
					  serving: $scope.serving,
					  style: $scope.style,
					  label: $scope.label				  
				  }
			  }).success(function(data) {
			    	$location.path("/beers"); 
			  });
	  	  }	    
  }])
  .controller('BeerCreateCtrl', ['$scope', '$http', 
                                 function($scope, $http) {
	  	  $scope.doAction = function() {
		  $http({
			  method: 'POST',
			  url: "/beer/create",
			  data: {
				  name:$scope.name, 
				  description:$scope.description, 
				  img: $scope.img,
				  alcohol: $scope.alcohol,
				  availabilty: $scope.availability,
				  brewery: $scope.brewery,
				  serving: $scope.serving,
				  style: $scope.style,
				  label: $scope.label				  
			  }			  
			  //data: "name="+$scope.name+"&description="+$scope.description
		  }).success(function(data) {
		    	$location.path("/beers"); 
		  });
	  }
 	  
  }]);

