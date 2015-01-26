/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieListController', function ($scope, DataSession, DataService) {
    $scope.movieItems = [];
    $scope.isLoaded = false;
    DataSession['MovieListController'] = DataSession['MovieListController'] || {};
    $scope.selectedCinema =
        DataSession['MovieListController'].selectedCinema || "Chọn cụm rạp";
    $scope.selectedTheater =
        DataSession['MovieListController'].selectedTheater || "Chọn rạp";

    $scope.getSelectedCinema = function () {
        return DataSession['MovieListController'].selectedCinema || -1;
    };

    $scope.setSelectedCinema = function (name) {
        $scope.selectedCinema = name;
    };
    $scope.selectedTheater =
        DataSession['MovieListController'].selectedTheater || "Chọn rạp";

    $scope.getSelectedTheater = function () {
        return DataSession['MovieListController'].selectedTheater || -1;
    };

    $scope.setSelectedTheater = function (name) {
        $scope.selectedTheater = name;
    };

    DataService.getMovies().then(function (movies) {
        $scope.movieItems = movies;
        $scope.isLoaded = true;
    }, function (reason) {
        console.log("Could not get movies.", reason);
        // Maybe provide a "Try again" button?
    });


    $scope.showDetail = function (movieId) {
        $scope.ons.navigator.pushPage('views/movie_detail/movie_detail.html', {data: movieId})
    };
});