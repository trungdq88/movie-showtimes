/**
 * Created by dinhquangtrung on 1/26/15.
 */

angular.module('app').controller('CinemaSelectController', function ($scope, $q, DataSession, DataService) {
    $scope.selectedCinema = $scope.getSelectedCinema();
    var _data = DataSession['MovieListController'];
    DataService.getCinemas().then(function (cinemas) {
        _data.cinemaItems = cinemas;
        $scope.cinemaItems = cinemas;
        _data.selectedCinema && $scope.selectCinema(_data.selectedCinema);
    });

    $scope.selectCinema = function (name) {
        _data.selectedCinema = name;
        _data.cinemaChange && _data.cinemaChange();
        $scope.setSelectedCinema(name);
        $scope.varSelectCinema.hide();
    }
});