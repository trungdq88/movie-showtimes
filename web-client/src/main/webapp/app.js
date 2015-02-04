function ready(fn) {
  if (document.readyState != 'loading'){
    fn();
  } else {
    document.addEventListener('DOMContentLoaded', fn);
  }
}

function $class(className) {
    return document.getElementsByClassName(className);
}

ready(function () {
    var dates = $class('date');
    Array.prototype.forEach.call(dates, function(el, i){
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
});