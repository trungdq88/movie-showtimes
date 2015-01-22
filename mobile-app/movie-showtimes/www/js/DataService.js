/**
 * Created by dinhquangtrung on 1/22/15.
 */
angular.module('app').service('DataService', function() {
    var _movies = [];
    this.getMovies = function (done) {
        if (_movies.length == 0) {
            // TODO: call API here
            setTimeout(function () {
                for (var i = 0; i < 20; i++) {
                    _movies.push({
                        name: 'Để mốt tính',
                        show_date: 'Hôm nay',
                        description: 'Bốn năm sau khi chia tay với Dũng ' +
                            'và Mai, Phạm Hương Hội giờ đã khác xưa rất nhiều',
                        theaterNum: 4
                    });
                }
                done(_movies);
            }, 3000);
        } else {
            done(_movies);
        }
    }
});