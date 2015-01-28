/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('TheaterListController', function ($scope, DataSession, DataService) {
    $scope.fullTheaters = [];

    $scope.defaultCinema2 = "Tất cả cụm rạp";
    $scope.defaultMovie = "Tất cả phim";

    $scope.isLoaded = false;


    function updateFilter() {
        var cinemaFilterResults, movieFilterResults;
        if ($scope.selectedCinema2 != $scope.defaultCinema2) {
            cinemaFilterResults = $scope.fullTheaters.filter(function (theater) {
                return theater.cinema == $scope.selectedCinema2;
            });
        } else {
            cinemaFilterResults = $scope.fullTheaters;
        }

        if ($scope.selectedMovie != $scope.defaultMovie) {
            movieFilterResults = cinemaFilterResults.filter(function (theater) {
                return theater.sessions.filter(function (session) {
                    return session.movie == $scope.selectedMovie;
                }).length;
            });
        } else {
            movieFilterResults = cinemaFilterResults;
        }

        $scope.theaterItems = movieFilterResults;
    }

    var _data = DataSession['TheaterListController'] = DataSession['TheaterListController'] || {};
    $scope.selectedCinema2 = _data.selectedCinema2 || $scope.defaultCinema2;
    $scope.selectedMovie = _data.selectedMovie || $scope.defaultMovie;

    $scope.getSelectedCinema2 = function () {
        return _data.selectedCinema2 || $scope.defaultCinema2;
    };
    $scope.getSelectedMovie = function () {
        return _data.selectedMovie || $scope.selectedMovie;
    };

    $scope.setSelectedCinema2 = function (name) {
        $scope.selectedCinema2 = name;
        updateFilter();
    };

    $scope.setSelectedMovie = function (name) {
        $scope.selectedMovie = name;
        updateFilter();
    };


    DataService.getTheaters().then(function (theaters) {
        $scope.fullTheaters = theaters;
        $scope.theaterItems = $scope.fullTheaters;
        $scope.isLoaded = true;
    }, function (reason) {
        console.log("Could not get theaters.", reason);
        // Maybe provide a "Try again" button?
    });


    $scope.showDetail = function (theatername) {
        $scope.ons.navigator.pushPage('views/theater_detail/theater_detail.html', {data: theatername})
    }
});