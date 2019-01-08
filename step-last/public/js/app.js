'use strict';

/* App Module */

var angularBeer = angular.module('AngularBeer', [
  'ngRoute',
  'ngCookies',
  'BeerControllers',
  'BeerFilters'
]);

angularBeer.config(['$routeProvider', '$httpProvider',
  function($routeProvider, $httpProvider) {
    $routeProvider.
      when('/beers', {
        templateUrl: 'partials/beer-list.html',
        controller: 'BeerListCtrl'
      }).
      when('/beers/:beerId', {
        templateUrl: 'partials/beer-detail.html',
        controller: 'BeerDetailCtrl'
      }).
      when('/create', {
          templateUrl: 'partials/beer-create-edit.html',
          controller: 'BeerCreateCtrl'
      }).
      when('/edit/:beerId', {
          templateUrl: 'partials/beer-create-edit.html',
          controller: 'BeerEditCtrl'
      }).  
      when('/login', {
    	templateUrl: 'partials/login.html',
    	controller: 'LoginCtrl'
      }).  
      when('/loginFail', {
        templateUrl: 'partials/loginFail.html',
        controller: 'LoginCtrl'
        }).
      otherwise({
        redirectTo: '/beers'
      });
    
      $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
  }]);