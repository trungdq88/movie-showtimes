function ready(fn) {
    if (document.readyState != 'loading') {
        fn();
    } else {
        document.addEventListener('DOMContentLoaded', fn);
    }
}
function encodeURI(uri) {
    return encodeURIComponent(uri).replace(/'/g, "%27").replace(/"/g, "%22");
}
function $id(id) {
    return document.getElementById(id);
}
function $class(className) {
    return document.getElementsByClassName(className);
}
function change_alias(alias)
{
    var str = alias;
    str = str.toLowerCase();
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ  |ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ  |ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    str = str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'| |\"|\&|\#|\[|\]|~|$|_/g, "-");
    /* tìm và thay thế các kí tự đặc biệt trong chuỗi sang kí tự - */
    str = str.replace(/-+-/g, "-"); //thay thế 2- thành 1-
    str = str.replace(/^\-+|\-+$/g, "");
    //cắt bỏ ký tự - ở đầu và cuối chuỗi 
    return str.replace(/-/g, ' ');
}
function addClass(el, className) {
    if (!el)
        return;
    if (el.classList)
        el.classList.add(className);
    else
        el.className += ' ' + className;
}
function removeClass(el, className) {
    if (el.classList)
  el.classList.remove(className);
else
  el.className = el.className.replace(new RegExp('(^|\\b)' + className.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
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
        var item = document.querySelector('[data-id="' + params[keys[i]] + '"]');
        if (item) {
            addClass(item, 'selected');
            item.focus();
        }
    }
}

var showDetailDelay = 0;
var isShow = false;
var popup;
function closePopup() {
    popup.innerHTML = '';
    popup.style.display = 'none';
    isShow = false;
}
function processMovieDetail() {
    popup = $id('movie-detail');
    var els = $class('movie-detail');
    clearTimeout(showDetailDelay);
    Array.prototype.forEach.call(els, function (el, i) {
        el.addEventListener('mouseenter', function (e) {
            var self = this;
            showDetailDelay = setTimeout(function () {
                var movieId = self.dataset.name;
                ajax('?action=detail&movie=' + movieId, function (msg) {
                    popup.innerHTML = msg;
                    popup.style.left = (50 + e.x) + 'px';
                    popup.style.display = 'block';
                    isShow = true;
                });
            }, 500);
        });
        el.addEventListener('mouseleave', function () {
            clearTimeout(showDetailDelay);
            // closePopup();
        });
        el.addEventListener('mousemove', function (e) {
            if (isShow) {
                //popup.style.left = (50 + e.x) + 'px';
            }
        });
    });
}

function prepairSearch() {
    var els = document.getElementsByClassName('movie-detail');
    Array.prototype.forEach.call(els, function (el, i) {
        el.dataset.searchTerm = change_alias(el.innerText.trim());
    });
    var searchBox = $id('search');
    searchBox.addEventListener('keyup', function () {
        var keyword = this.value;
        Array.prototype.forEach.call(els, function (el, i) {
            console.log(el.dataset.searchTerm + " _ " + keyword + " _ " + (el.dataset.searchTerm.indexOf(keyword) == -1))
            if (el.dataset.searchTerm.indexOf(keyword) == -1) {
                addClass(el.parentElement.parentElement.parentElement, 'hide');
            } else {
                removeClass(el.parentElement.parentElement.parentElement, 'hide');
            }
        });
    });
}

ready(function () {
    transformDate();
    processSelectedPage();
    processMovieDetail();
    prepairSearch();
});