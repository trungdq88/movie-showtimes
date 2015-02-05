function ready(fn) {
    if (document.readyState != 'loading') {
        fn();
    } else {
        document.addEventListener('DOMContentLoaded', fn);
    }
}

function $class(className) {
    return document.getElementsByClassName(className);
}

function addClass(el, className) {
    if (el.classList)
        el.classList.add(className);
    else
        el.className += ' ' + className;
}

function transformDate() {
    var dates = $class('date');
    Array.prototype.forEach.call(dates, function (el, i) {
        var d = new Date(+el.innerText.trim());
        var dateStr = d.getDate() + " tháng " + (d.getMonth() + 1);
        var now = new Date();

        var show_date = +d;
        var MSECONDS_PER_DAY = 24 * 60 * 60 * 1000;
        var today = Math.floor(now / MSECONDS_PER_DAY) * MSECONDS_PER_DAY;
        var tomorrow = Math.floor(now / MSECONDS_PER_DAY) * MSECONDS_PER_DAY + MSECONDS_PER_DAY;
        var later = Math.floor(now / MSECONDS_PER_DAY) * MSECONDS_PER_DAY + 2 * MSECONDS_PER_DAY;

        if (show_date >= today && show_date < tomorrow) {
            dateStr = "Hôm nay";
        } else if (show_date >= tomorrow && show_date < later) {
            dateStr = "Ngày mai";
        }


        el.innerHTML = "<b>" + dateStr + "</b> vào lúc <b>" + d.getHours() + "h" + d.getMinutes() + "</b>";
        el.title = d;
    });
}

function getParams() {
    var parts = location.href.split('?');
    var params = {};
    if (parts.length === 2) {
        var paramsArray = parts[1].split('&');
        var _key;
        for (var i = 0; i < paramsArray.length; i++) {
            var _p = paramsArray[i].split('=');
            params[_p[0]] = decodeURI(_p[1]);
        }
    }
    return params;
}

function processSelectedPage() {
    var params = getParams();
    var keys = Object.keys(params);
    for (var i = 0; i < keys.length; i++) {
        var item = document.querySelector('[data-name="' + params[keys[i]] + '"]');
        addClass(item, 'selected');
        item.focus();
    }
}

ready(function () {
    transformDate();
    processSelectedPage();
});