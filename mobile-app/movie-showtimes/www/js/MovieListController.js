/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieListController', function ($scope, DataService) {
    $scope.movieItems = [];
    DataService.getMovies(function (movies) {
        $scope.movieItems = movies;
        $('#loading-holder').hide();
        $scope.$apply();
    });
});