/**
 * Created by dinhquangtrung on 1/26/15.
 */

angular.module('app').controller('Cinema2SelectController', function ($scope, $q, DataSession, DataService) {
    $scope.selectedCinema2 = $scope.getSelectedCinema2();
    var _data = DataSession['TheaterListController'];
    DataService.getCinemas().then(function (cinemas) {
        _data.cinemaItems2 = cinemas;
        $scope.cinemaItems2 = cinemas;
        _data.selectedCinema2 && $scope.selectCinema2(_data.selectedCinema2);
    });

    $scope.selectCinema2 = function (name) {
        _data.selectedCinema2 = name;
        _data.cinemaChange2 && _data.cinemaChange2();
        $scope.setSelectedCinema2(name);
        $scope.varSelectCinema2.hide();
    }
});