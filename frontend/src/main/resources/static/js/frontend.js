(function () {
    "use strict";
    angular.module('frontend', [])
        .controller("moviesController", function ($scope, $http, $filter) {
        $http.defaults.headers.common.Accept = "application/json";
        $scope.movies = [];
        $scope.countries = [
            {name: ''}
        ];
        $scope.country = $scope.countries[0];
        $scope.genres = [
            {name: ''}
        ];
        $scope.genre = $scope.genres[0];
        $scope.select = function(movie) {
            movie.selected = !movie.selected;
        };

        $scope.filter = function (movie) {
        return movie.Country.includes($scope.country.name) && movie.Genre.includes($scope.genre.name);
        };

        function getMovies() {
        $http.get('/Code Test Data.txt').then(function(result) {
            $scope.movies = result.data.Movies;
            $scope.movies.forEach(function(movie) {
                movie.Country.split(',').forEach(function(country) {
                    $scope.countries.push({name: country});
                });
                movie.Genre.split(',').forEach(function(genre) {
                    $scope.genres.push({name: genre});
                });
            });
        });
        }
        getMovies();
    });
}());
