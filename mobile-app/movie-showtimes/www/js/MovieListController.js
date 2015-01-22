/**
 * Created by dinhquangtrung on 1/22/15.
 */

angular.module('app').controller('MovieListController', function ($scope) {
    $scope.movieItems = [];
    setTimeout(function () {
        for (var i = 0; i < 200; i++) {
            $scope.movieItems.push({
                name: 'Để mốt tính',
                show_date: 'Hôm nay',
                description: 'Bốn năm sau khi chia tay với Dũng ' +
                    'và Mai, Phạm Hương Hội giờ đã khác xưa rất nhiều',
                theaterNum: 4
            });
        }
        $('#loading-holder').hide();
        $scope.$apply();
    }, 3000);

});