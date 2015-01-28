/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieListController', function ($scope, DataSession, DataService) {
    $scope.fullMovies = [];

    $scope.defaultCinema = "Tất cả cụm rạp";
    $scope.defaultTheater = "Tất cả rạp";

    $scope.isLoaded = false;
    $scope.openSearch = false;

    $scope.searchFilter = function(searchString, searchTerm) {
        if (searchTerm && searchTerm.length > 0) {
            return removeDiacritics(searchString.toLowerCase().trim())
                .indexOf(removeDiacritics(searchTerm.toLowerCase().trim())) != -1;
        } else {
            return true;
        }
    };
    $scope.toggleSearch = function () {
        $scope.openSearch = !$scope.openSearch;
        if (!$scope.openSearch) {
            $scope.searchTerm = "";
        }
    };

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


    $scope.showDetail = function (movieName) {
        $scope.ons.navigator.pushPage('views/movie_detail/movie_detail.html', {data: movieName})
    };
});