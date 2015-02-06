function ready(fn) {
    if (document.readyState != 'loading') {
        fn();
    } else {
        document.addEventListener('DOMContentLoaded', fn);
    }
}
function encodeURI(uri) {
    return encodeURIComponent(uri).replace(/'/g,"%27").replace(/"/g,"%22");
}
function $id(id) {
    return document.getElementById(id);
}
function $class(className) {
    return document.getElementsByClassName(className);
}

function addClass(el, className) {
    if (!el) return;
    if (el.classList)
        el.classList.add(className);
    else
        el.className += ' ' + className;
}
function ajax(url, cb) {
    var request = new XMLHttpRequest();
    request.open('GET', url, true);

    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            // Success!
            cb(request.responseText);
        } else {
            // We reached our target server, but it returned an error
            console.error("Error!");
        }
    };

    request.onerror = function () {
        // There was a connection error of some sort
        console.error("Error!");
    };

    request.send();
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
        if (!item) break;
        addClass(item, 'selected');
        item.focus();
    }
}

ready(function () {
    transformDate();
    processSelectedPage();
});