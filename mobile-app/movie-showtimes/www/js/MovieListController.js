/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieListController', function ($scope, DataSession, DataService) {
    $scope.fullMovies = [];

    $scope.defaultCinema = "Chọn cụm rạp";
    $scope.defaultTheater = "Chọn rạp";

    $scope.isLoaded = false;

    function updateFilter() {
        var cinemaFilterResults, theaterFilterResults;
        if ($scope.selectedCinema != $scope.defaultCinema) {
            cinemaFilterResults = $scope.fullMovies.filter(function (movie) {
                return movie.sessions.filter(function (session) {
                    return session.theater.cinema == $scope.selectedCinema;
                }).length;
            });
        } else {
            cinemaFilterResults = $scope.fullMovies;
        }

        if ($scope.selectedTheater != $scope.defaultTheater) {
            theaterFilterResults = cinemaFilterResults.filter(function (movie) {
                return movie.sessions.filter(function (session) {
                    return session.theater.name == $scope.selectedTheater;
                }).length;
            });
        } else {
            theaterFilterResults = cinemaFilterResults;
        }

        $scope.movieItems = theaterFilterResults;
    }

    var _data = DataSession['MovieListController'] = DataSession['MovieListController'] || {};
    $scope.selectedCinema = _data.selectedCinema || $scope.defaultCinema;
    $scope.selectedTheater = _data.selectedTheater || $scope.defaultTheater;

    $scope.getSelectedCinema = function () {
        return _data.selectedCinema || $scope.defaultCinema;
    };
    $scope.getSelectedTheater = function () {
        return _data.selectedTheater || $scope.selectedTheater;
    };

    $scope.setSelectedCinema = function (name) {
        $scope.selectedCinema = name;
        updateFilter();
    };

    $scope.setSelectedTheater = function (name) {
        $scope.selectedTheater = name;
        updateFilter();
    };

    DataService.getMovies().then(function (movies) {
        $scope.fullMovies = movies;
        $scope.movieItems = $scope.fullMovies;
        $scope.isLoaded = true;
    }, function (reason) {
        console.log("Could not get movies.", reason);
        // Maybe provide a "Try again" button?
    });


    $scope.showDetail = function (movieId) {
        $scope.ons.navigator.pushPage('views/movie_detail/movie_detail.html', {data: movieId})
    };
});