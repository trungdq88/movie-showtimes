/**
 * Created by dinhquangtrung on 1/26/15.
 */

angular.module('app').controller('TheaterSelectController', function ($scope, DataSession, DataService) {
    $scope.selectedTheater = $scope.getSelectedTheater();

    var _data = DataSession['MovieListController'];

    // This method will be called when cinema filter is changed
    _data.cinemaChange = function () {
        if (_data.selectedCinema != $scope.defaultCinema) {
            $scope.theaterItems = _data.theaterItems.filter(function (theater) {
                return theater.cinema == _data.selectedCinema;
            }).map(function (theater) {
                return theater.name;
            });
        } else {
            $scope.theaterItems = _data.theaterItems.map(function (theater) {
                return theater.name;
            });
        }
        $scope.selectTheater($scope.defaultTheater);
    };

    DataService.getTheaters().then(function (theaters) {
        _data.theaterItems = theaters;
        $scope.theaterItems = theaters.map(function (theater) {
            return theater.name;
        });
    });

    $scope.selectTheater = function (name) {
        _data.selectedTheater = name;
        $scope.setSelectedTheater(name);
        $scope.varSelectTheater.hide();
    }
});