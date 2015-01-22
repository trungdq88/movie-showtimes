/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieDetailController', function ($scope, DataService) {
    var movieId = $scope.ons.navigator.getCurrentPage().options.data;
    DataService.getMovie(movieId).then(function (movie) {
        $scope.test = movie.id;
    }, function (reason) {
        console.log('Could not get movie.', reason);
    });
});