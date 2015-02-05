<%-- 
    Document   : header
    Created on : Feb 3, 2015, 9:37:31 AM
    Author     : HaiNNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lịch Chiếu Phim</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <script src="app.js"></script>
        <style>
            html {
                height: 100%;
                width: 100%;
                font-family: 'Arial';
                background: url(bg.jpg);
                background-size: 100% 100%;
                background-repeat: no-repeat;
            }
            a {
                text-decoration: none;
                color: #fff;
            }
            .description {
                color: #ccc;
            }
            i.icon.restriction-icon {
                background: rgb(255, 133, 133);
                display: inline-block;
                width: 30px;
                height: 30px;
                line-height: 30px;
                border-radius: 15px;
                font-size: 13px;
                text-align: center;
                margin-left: 10px;
            }
            .container {
                background: rgba(255, 255, 255, 0.3);
                float: left;
                width: 25%;
                margin: 10px;
                height: 80vh;
                overflow: auto;
                border-radius: 10px;
            }
            img.movie-poster {
                width: 70px;
                height: 100px;
                float: left;
                margin-right: 10px;
                margin-bottom: 10px;
            }
            .movie {
                border-bottom: 1px solid #ccc;
                overflow: hidden;
            }
            .movie a {
                display: block;
                overflow: hidden;
                padding: 10px;
            }
            .movie:hover {
                background: rgba(255, 255, 255, 0.3);
            }
            .movie.selected {
                background: rgba(255, 255, 255, 0.3);
            }
            .movie h3 {
                margin: 0;
            }
            #nav-bar {
                color: white;
            }
            .nav-item {
                padding: 10px;
                display: inline-block;
                border-radius: 10px;
                margin-right: 20px;
            }
            .nav-item.selected {
                background-color: #fff;
                color: #333;
            }
            #caution {
                font-size: 10px;
                color: #989898;
                position: fixed;
                bottom: 10px;
                right: 10px;
            }
            .date {
                color: #fff;
            }
        </style>
    </head>
    <body>
        <header>
            <div id="nav-bar">
                <a class="nav-item selected" href="#">Tìm lịch chiếu theo phim</a>
                <a class="nav-item" href="#">Tìm lịch chiếu theo rạp</a>
                <a class="nav-item" href="#">Tìm lịch chiếu theo ngày</a>
                Dữ liệu cập nhật từ: 04/02/2015
            </div>

        </header>
