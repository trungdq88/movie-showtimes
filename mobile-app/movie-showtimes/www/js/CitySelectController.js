/**
 * Created by dinhquangtrung on 1/23/15.
 */
angular.module('app').controller('CitySelectController', function ($scope, DataService) {
    $scope.currentCity = DataService.getCurrentCity();
    DataService.getCities().then(function (cities) {
        $scope.cities = cities;
        if (!$scope.currentCity ||
            $scope.cities.indexOf($scope.currentCity) == -1) {
            // Please set city
            $scope.currentCity = false;
            $scope.varSelectCity.show();
        }
    });

    $scope.selectCity = function (city) {
        $scope.currentCity = city;
    };
    $scope.saveSelection = function () {
        if ($scope.currentCity) {
            DataService.setCity($scope.currentCity);
            $scope.varSelectCity.hide();
            // Refresh all data in all controllers
            location.href = 'index.html';
        }
    };

    $scope.hideSelectCity = function (event) {
        if (event.target.parentElement.id == "city-select") {
            $scope.varSelectCity.hide();
        }
    }
});