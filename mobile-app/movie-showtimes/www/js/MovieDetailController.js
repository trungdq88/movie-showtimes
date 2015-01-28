/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieDetailController', function ($scope, DataService) {
    var movieName = $scope.ons.navigator.getCurrentPage().options.data;
    DataService.getMovie(movieName).then(function (movie) {
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



        if ($scope.laterSessions.sessions.length) {
            $scope.isLaterAvailable = true;
            $scope.activeSessions = $scope.laterSessions;
        }
        if ($scope.tomorrowSessions.sessions.length) {
            $scope.isTomorrowAvailable = true;
            $scope.activeSessions = $scope.tomorrowSessions;
        }
        if ($scope.todaySessions.sessions.length) {
            $scope.isTodayAvailable = true;
            $scope.activeSessions = $scope.todaySessions;
        }

        // Methods
        $scope.changeSession = function (session) {
            $scope.activeSessions = session;
        };

        $scope.showSessionDetail = function (session) {
            // $scope.ons.navigator.pushPage('views/movie_detail/session_detail.html', {data: session})
        };
    }, function (reason) {
        console.log('Could not get movie.', reason);
    });
    $scope.openTrailer = function () {
        window.open($scope.movie.trailer, '_system');
    }
});