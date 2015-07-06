'use strict';

/* Controllers */

angular
  .module('BeerControllers', [])
  .controller('LoginCtrl',['$scope', '$http', '$location', '$rootScope', function($scope, $http, $location, $rootScope) {
	  $scope.doSend = function() {
		  console.log("I did it");
		  $http({
				  method: 'POST',
				  url: "/authorized",
				  headers: {
				        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
				        'authorization': 'Basic '
	                              + btoa($scope.user + ":" + $scope.password)
				  }
		  }).success(function(data) {
		    // Now we have a true error code, so if we are here, we are logged in
        console.log("Login successful", data);
        console.log("Referer", $rootScope.referer)
        if ($rootScope.referer === undefined) {
          $rootScope.referer = "";
        }
        $location.path($rootScope.referer);
        
      }).error(function(err){
        console.log("Login Fail", err);
        $location.path("/login");
        $scope.fail = true;
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
  .controller('BeerEditCtrl', ['$scope', '$routeParams', '$http', '$location', '$rootScope',
                                 function($scope, $routeParams, $http, $location, $rootScope) {
	  	$scope.action = "Edit"
	  		
	  	$http.get('/authorized').success(function(data) {
	  			console.log("Authorized");	  		
	  	}).error(function(err) {
	  	  console.log("Authorization needed");
        $rootScope.referer = $location.path();
        $location.path("/login");
	  	});
	  		
	  		
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
  .controller('BeerCreateCtrl', ['$scope', '$http', '$location', '$rootScope',
                                 function($scope, $http, $location, $rootScope) {
	  
    $http.get('/authorized').success(function(data) {
      console.log("Authorized");        
    }).error(function(err) {
      console.log("Authorization needed");
      $rootScope.referer = $location.path();
      $location.path("/login");
    });
    
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

