<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <header>
        <h1>Hello World!</h1>
    </header>
    <div id="body">
        <div id="nav-bar">
            <a href="#">Tìm Theo Phim</a>
            <a href="#">Tìm Theo Rạp</a>
            <a href="#">Tìm Theo Ngày</a>
        </div>
        <div id="content-container">
            <div id="selected-movie"></div>
            <div id="selected-theater"></div>
            <div id="browsed-content">
                <div class="browsed-item">
                    <img src="" class="poster"/>
                    <div class="item-info">
                        <div class="movie-name">Chàng Trai Năm Ấy</div>
                        <div class="actor">MTP, Hari Won</div>
                        <div class="theater-num">4 rạp đang chiếu.</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer></footer>
    </body>
</html>
