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

        $scope.openTrailer = function () {
            navigator.app.loadUrl($scope.movie.trailer, {openExternal: true});
        }

    }, function (reason) {
        console.log('Could not get movie.', reason);
    });

});
angular.module('app').controller('MovieDetailControllerSessions', function ($scope, DataService) {
    var movieId = $scope.ons.navigator.getCurrentPage().options.data;
//    setTimeout(function () {
//        $scope.isLaterAvailable = true;
//        $scope.$apply();
//    }, 5000);
    DataService.getMovie(movieId).then(function (movie) {
        $scope.movie = movie;
        var MSECONDS_PER_DAY = 24 * 60 * 60 * 1000;
        var today = Math.floor(+new Date / MSECONDS_PER_DAY) * MSECONDS_PER_DAY;
        var tomorrow = Math.floor(+new Date / MSECONDS_PER_DAY) * MSECONDS_PER_DAY + MSECONDS_PER_DAY;
        var later = Math.floor(+new Date / MSECONDS_PER_DAY) * MSECONDS_PER_DAY + 2 * MSECONDS_PER_DAY;
        $scope.todaySessions = {
            label: "Hôm nay",
            sessions: $scope.movie.sessions.filter(function (o) {
                var show_date = Math.floor(o.show_time / MSECONDS_PER_DAY) * MSECONDS_PER_DAY;
                return show_date >= today && show_date < tomorrow;
            })
        };
        $scope.tomorrowSessions = {
            label: "Ngày mai",
            sessions: $scope.movie.sessions.filter(function (o) {
                var show_date = Math.floor(o.show_time / MSECONDS_PER_DAY) * MSECONDS_PER_DAY;
                return show_date >= tomorrow && show_date < later;
            })
        };
        $scope.laterSessions = {
            label: "",
            sessions: $scope.movie.sessions.filter(function (o) {
                var show_date = Math.floor(o.show_time / MSECONDS_PER_DAY) * MSECONDS_PER_DAY;
                return show_date >= later;
           })
        };

        $scope.activeSessions = $scope.todaySessions;

        if ($scope.tomorrowSessions.sessions.length) {
            $scope.isTomorrowAvailable = true;
        }
        if ($scope.laterSessions.sessions.length) {
            $scope.isLaterAvailable = true;
        }

        // Methods
        $scope.changeSession = function (session) {
            $scope.activeSessions = session;
        };

        $scope.showSessionDetail = function (session) {
            $scope.ons.navigator.pushPage('views/movie_detail/session_detail.html', {data: session})
        };
    }, function (reason) {
        console.log('Could not get movie.', reason);
    });
});
