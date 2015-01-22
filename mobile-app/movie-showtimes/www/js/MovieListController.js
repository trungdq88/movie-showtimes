/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieListController', function ($scope, DataService) {
    $scope.movieItems = [];
    $scope.isLoaded = false;
    DataService.getMovies(function (movies) {
        $scope.movieItems = movies;
        $scope.isLoaded = true;
        $scope.$apply();
    });
});