/**
 * Created by dinhquangtrung on 1/22/15.
 */
function uniqBy(a, key) {
    var seen = {};
    return a.filter(function(item) {
        var k = key(item);
        return seen.hasOwnProperty(k) ? false : (seen[k] = true);
    })
}
angular.module('app').service('DataService', function($q, $http) {
    var self = this;
    var API_SOURCE = 'fake-api/data.json';
    var CITY_KEY = 'movie-showtimes-city';
    var pData = $http.get(API_SOURCE)
            .then(function(payload) {
                return payload.data;
            });
    this.getMovies = function () {
        return pData.then(function (data) {
            return data.movies;
        });
    };
    this.getTheaters = function () {
        return this.getMovies().then(function (movies) {
            var theaterArrays = movies.map(function (movie) {
                return movie.sessions.map(function (session) {
                    return session.theater;
                });
            });

            //Flatten
            var theaters = [];
            for (var i = 0; i < theaterArrays.length; i++) {
                theaters = theaters.concat(theaterArrays[i]);
            }

            // Remove duplicate and sort
            theaters = uniqBy(theaters, function (theater) {
                return theater.name;
            })
            return theaters;
        })
    };
    this.getCities = function () {
        return $q(function (resolve, reject) {
            resolve([
                'Hồ Chí Minh',
                'Hà Nội',
                'Hải Phòng',
                'Đà Nẵng',
                'Thừa Thiên Huế'
            ]);
        });
    };
    this.getCinemas = function () {
        return this.getMovies().then(function (movies) {
            var cinemaNamesArrays = movies.map(function (movie) {
                return movie.sessions.map(function (session) {
                    return session.theater.cinema;
                });
            });
            var cinemaNames = [];
            for (var i = 0; i < cinemaNamesArrays.length; i++) {
                cinemaNames = cinemaNames.concat(cinemaNamesArrays[i]);
            }
            return cinemaNames.filter(function (cinemaName, index) { // Remove duplicate
                return cinemaNames.indexOf(cinemaName) == index;
            });
        });
    };
    this.getTheaterNames = function () {
        return this.getTheaters().then(function (theaters) {
            var names = theaters.map(function (o) {
                return o.name;
            });
            return names.filter(function(item, pos) { // Remove duplicate items
                return names.indexOf(item) == pos;
            });
        });
    };
    this.setCity = function (city) {
        localStorage[CITY_KEY] = city;
    };
    this.getCurrentCity = function () {
        return localStorage[CITY_KEY];
    };
    this.getMovie = function (movieId) {
        return self.getMovies().then(function (movies) {
            var result = movies.filter(function (o) {
                return o.id == movieId;
            });
            if (result.length) {
                // If there is a duplicate id movies, return the first one
                return result[0];
            } else {
                return $q.reject('Không tìm thấy phim');
            }
        });
    };
    this.getTheater = function (theaterId) {
        return self.getTheaters().then(function (theaters) {
            var result = theaters.filter(function (o) {
                return o.id == theaterId;
            });
            if (result.length) {
                // If there is a duplicate id theaters, return the first one
                return result[0];
            } else {
                return $q.reject('Không tìm thấy rạp');
            }
        });
    };
});