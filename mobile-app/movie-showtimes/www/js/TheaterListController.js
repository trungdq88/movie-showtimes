/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('TheaterListController', function ($scope, DataSession, DataService) {
    $scope.theaterItems = [];
    $scope.isLoaded = false;


    DataService.getTheaters().then(function (theaters) {
        $scope.theaterItems = theaters;
        $scope.isLoaded = true;
    }, function (reason) {
        console.log("Could not get theaters.", reason);
        // Maybe provide a "Try again" button?
    });


    $scope.showDetail = function (theaterId) {
        $scope.ons.navigator.pushPage('views/theater_detail/theater_detail.html', {data: theaterId})
    }
});