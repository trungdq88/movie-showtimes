/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieDetailControllerTitle', function ($scope, DataService) {
    var movieId = $scope.ons.navigator.getCurrentPage().options.data;
    DataService.getMovie(movieId).then(function (movie) {
        $scope.movie = movie;
    }, function (reason) {
        console.log('Could not get movie.', reason);
    });
});

angular.module('app').controller('MovieDetailControllerInfo', function ($scope, DataService) {
    var movieId = $scope.ons.navigator.getCurrentPage().options.data;
    DataService.getMovie(movieId).then(function (movie) {
        $scope.movie = movie;
    }, function (reason) {
        console.log('Could not get movie.', reason);
    });
});
angular.module('app').controller('MovieDetailControllerSessions', function ($scope, DataService) {
    $scope.isTomorrowAvailable = true;
    $scope.isLaterAvailable = false;
    setTimeout(function () {
        $scope.isLaterAvailable = true;
        $scope.$apply();
    }, 5000);
    $scope.showSessionDetail = function (session) {
        $scope.ons.navigator.pushPage('views/movie_detail/session_detail.html', {data: session})
    }
});
