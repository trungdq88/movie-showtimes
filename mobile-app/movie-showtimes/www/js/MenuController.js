/**
 * Created by dinhquangtrung on 1/23/15.
 */


angular.module('app').controller('MenuController', function ($scope, DataService) {

    $scope.setCity = function () {
        $scope.ons.createPopover('views/select_city.html').then(function(popover) {
            popover.show('#set-city-btn');
        });
    };
    $scope.refreshData = function () {
        location.reload();
    };
});