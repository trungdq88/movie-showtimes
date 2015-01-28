/**
 * Created by dinhquangtrung on 1/26/15.
 */

angular.module('app').controller('MovieSelectController', function ($scope, DataSession, DataService) {
    $scope.selectedMovie = $scope.getSelectedMovie();

    var _data = DataSession['TheaterListController'];

    // This method will be called when cinema filter is changed
    _data.cinemaChange2 = function () {
        if (_data.selectedCinema2 && _data.selectedCinema2 != $scope.defaultCinema2) {
            $scope.movieItems = _data.movieItems.filter(function (movie) {
                return movie.sessions.filter(function (session) {
                    return session.theater.cinema == _data.selectedCinema2;
                }).length;
            }).map(function (movie) {
                return movie.name;
            });
        } else {
            $scope.movieItems = _data.movieItems.map(function (movie) {
                return movie.name;
            });
        }
        $scope.selectMovie(_data.selectedMovie || $scope.defaultMovie);
    };

    DataService.getMovies().then(function (movies) {
        _data.movieItems = movies;
        _data.cinemaChange2();
    });

    $scope.selectMovie = function (name) {
        _data.selectedMovie = name;
        $scope.setSelectedMovie(name);
        $scope.varSelectMovie.hide();
    }
});