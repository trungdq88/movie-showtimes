/**
 * Created by dinhquangtrung on 1/22/15.
 */
angular.module('app').service('DataService', function($q, $http) {
    var self = this;
    var API_SOURCE = 'fake-api/data.json';
    var CITY_KEY = 'movie-showtimes-city';
    var pData = $http.get(API_SOURCE)
            .then(function(payload) {
                return payload.data;
            });
    this.getMovies = function () {
        return pData;
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
            });

            // Add sessions
            for (i = 0; i < theaters.length; i++) {
                var currentTheater = theaters[i];
                currentTheater.sessions = [];
                // Loop through movies
                for (var k = 0; k < movies.length; k++) {
                    // Get all sessions of each movies
                    // In Array(Session), filter all the session of current theater
                    var _sessions = movies[k].sessions.filter(function (session) {
                        return session.theater.name == currentTheater.name;
                    });
                    // Sessions in _sessions are currently missing `movie` property, add it now
                    for (var j = 0; j < _sessions.length; j++) {
                        _sessions[j].movie = movies[k].name;
                    }
                    // And add to current theater's sessions
                    currentTheater.sessions = currentTheater.sessions.concat(_sessions);
                }
            }


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
    this.getMovie = function (movieName) {
        return self.getMovies().then(function (movies) {
            var result = movies.filter(function (movie) {
                return movie.name == movieName;
            });
            if (result.length) {
                // If there is a duplicate id movies, return the first one
                return result[0];
            } else {
                return $q.reject('Không tìm thấy phim');
            }
        });
    };
    this.getTheater = function (theaterName) {
        return self.getTheaters().then(function (theaters) {
            var result = theaters.filter(function (theater) {
                return theater.name == theaterName;
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