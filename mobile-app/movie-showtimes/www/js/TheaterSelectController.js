/**
 * Created by dinhquangtrung on 1/26/15.
 */

angular.module('app').controller('TheaterSelectController', function ($scope, DataSession, DataService) {
    $scope.selectedTheater = $scope.getSelectedTheater();

    DataService.getTheaters().then(function (theaters) {
        $scope.theaterItems = theaters;
    });

    $scope.selectTheater = function (index) {
        DataSession['MovieListController'].selectedTheater = index;
        $scope.setSelectedTheater(index == -1 ? "Chọn rạp" : $scope.theaterItems[index]);
        $scope.varSelectTheater.hide();
    }
});