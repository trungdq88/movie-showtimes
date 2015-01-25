(function() {

    ons.ready(function() {
        // Hide Cordova splash screen when Onsen UI is loaded completely
        // API reference: https://github.com/apache/cordova-plugin-splashscreen/blob/master/doc/index.md
        // navigator.splashscreen.hide()
    });

    var module = angular.module('app', ['onsen']);

    module.controller('AppController', function($scope) {

    });
})();