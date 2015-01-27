/**
 * Created by dinhquangtrung on 1/27/15.
 */

angular.module('app').filter('theaterNames', function() {
    return function(sessions) {
        var theaters = sessions.map(function (session) {
            return session.theater.name;
        });
        theaters = uniqBy(theaters, function (theater) {
            return theater;
        });
        return "Có " + theaters.length + " rạp chiếu (" + theaters.join(', ') + ")";
    };
});