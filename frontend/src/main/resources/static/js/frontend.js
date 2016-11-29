(function () {
    "use strict";
    angular.module('frontend', [])
        .controller("moviesController", function ($scope, $http) {
        $http.defaults.headers.common.Accept = "application/json";
        $scope.movies = {Movies: []}
        $scope.error = {message: ""};

        function getMovies() {
        $http.get('/Code Test Data.txt').then(function(result) {
        $scope.movies = result.data;
        });
//        	return {"Movies" : [
//                      {"Title":"Die Hard",
//                      "Year":"1988",
//                      "Rated":"R",
//                      "Released":"20 Jul 1988",
//                      "Runtime":"131 min",
//                      "Genre":"Action, Thriller",
//                      "Director":"John McTiernan",
//                      "Writer":"Roderick Thorp (novel), Jeb Stuart (screenplay), Steven E. de Souza (screenplay)",
//                      "Actors":"Bruce Willis, Bonnie Bedelia, Reginald VelJohnson, Paul Gleason","Plot":"John McClane, officer of the NYPD, tries to save wife Holly Gennaro and several others, taken hostage by German terrorist Hans Gruber during a Christmas party at the Nakatomi Plaza in Los Angeles.","Language":"English, German, Italian","Country":"USA","Awards":"Nominated for 4 Oscars. Another 6 wins & 2 nominations.","Poster":"http://ia.media-imdb.com/images/M/MV5BMTY4ODM0OTc2M15BMl5BanBnXkFtZTcwNzE0MTk3OA@@._V1_SX300.jpg","Metascore":"70","imdbRating":"8.3","imdbVotes":"511278","imdbID":"tt0095016","Type":"movie",
//                      "Response":"True"}]};
        }
        getMovies();
    });
}());
