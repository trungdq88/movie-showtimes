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
        return pData.then(function (data) {
            return data.movies;
        });
    };
    this.getTheaters = function () {
        return pData.then(function (data) {
            return data.theaters;
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
        return $q(function (rs, rj) {
            rs(["Galaxy", "CGV", "Lotte", "BHD Star"]);
        }) ;
    };
    this.getTheaters = function () {
        return $q(function (rs, rj) {
            rs(["Galaxy Tân Bình", "Galaxy Quang Trung", "Galaxy ABC", "Galaxy XYZ"]);
        }) ;
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