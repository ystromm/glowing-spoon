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
        $scope.writers = [
            {name: ''}
        ];
        $scope.writer = $scope.writers[0];
        $scope.select = function(movie) {
            movie.selected = !movie.selected;
        };

        $scope.filter = function (movie) {
        return movie.Country.includes($scope.country.name)
            && movie.Genre.includes($scope.genre.name)
            && movie.Writer.includes($scope.writer.name);
        };

        function getMovies() {
        $http.get('/Code Test Data.txt').then(function(result) {
            $scope.movies = result.data.Movies;
            $scope.movies.forEach(function(movie) {
                movie.Country.split(',').forEach(function(country) {
                    $scope.countries.push({name: country.trim()});
                });
                movie.Genre.split(',').forEach(function(genre) {
                    $scope.genres.push({name: genre.trim()});
                });
                movie.Writer.split(',').forEach(function(writer) {
                    var index = writer.indexOf('(');
                    if (index != -1) {
                        $scope.writers.push({name: writer.substring(0, index).trim()});
                    }
                    else {
                        $scope.writers.push({name: writer.trim()});
                    }
                });
            });
            $scope.countries = _.uniqBy($scope.countries, 'name');
            $scope.genres = _.uniqBy($scope.genres, 'name');
            $scope.writers = _.uniqBy($scope.writers, 'name');
        });
        }
        getMovies();
    });
}());
