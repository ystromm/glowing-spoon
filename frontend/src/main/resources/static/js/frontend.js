(function () {
    "use strict";
    angular.module('frontend', [])
        .controller("moviesController", function ($scope, $http) {
        $http.defaults.headers.common.Accept = "application/json";
        $scope.movies = {Movies: []}
        $scope.error = {message: ""};
        $scope.select = function(movie) {
        console.log(movie.Title);
        movie.selected = !movie.selected;
        };

        function getMovies() {
        $http.get('/Code Test Data.txt').then(function(result) {
        $scope.movies = result.data;
        });
        }
        getMovies();
    });
}());
