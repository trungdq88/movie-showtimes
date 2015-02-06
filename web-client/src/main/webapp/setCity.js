ready(function () {
    $id('set-city').style.display='block';
    var city = $id('city-select');
    // http://jbossews-trungdq88.rhcloud.com/API/getCities
    ajax('http://jbossews-trungdq88.rhcloud.com/API/getCities', function (data) {
        city.innerHTML = data;
        bindCityEvent();
    });

    
});

function bindCityEvent() {
    var els = document.getElementsByTagName('city');
    Array.prototype.forEach.call(els, function (el, i) {
        el.addEventListener('click', function () {
            var city = this.innerText.trim();
            location.href = '?city=' + encodeURI(city);
        });
    });
}