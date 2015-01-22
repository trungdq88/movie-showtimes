/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieListController', function ($scope, DataService) {
    $scope.movieItems = [];
    $scope.isLoaded = false;


    DataService.getMovies().then(function (movies) {
        $scope.movieItems = movies;
        $scope.isLoaded = true;
    }, function (reason) {
        console.log("Could not get movies.", reason);
        // Maybe provide a "Try again" button?
    });


    $scope.showDetail = function (movieId) {
        $scope.ons.navigator.pushPage('views/movie_detail/index.html', {data: movieId})
    }
});