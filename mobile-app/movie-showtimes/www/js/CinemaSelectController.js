/**
 * Created by dinhquangtrung on 1/26/15.
 */

angular.module('app').controller('CinemaSelectController', function ($scope, $q, DataSession, DataService) {
    $scope.selectedCinema = $scope.getSelectedCinema();

    DataService.getCinemas().then(function (cinemas) {
        $scope.cinemaItems = cinemas;
    });

    $scope.selectCinema = function (index) {
        DataSession['MovieListController'].selectedCinema = index;
        $scope.setSelectedCinema(index == -1 ? "Chọn cụm rạp" : $scope.cinemaItems[index]);
        $scope.varSelectCinema.hide();
    }
});