<%-- 
    Document   : header
    Created on : Feb 3, 2015, 9:37:31 AM
    Author     : HaiNNT
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lịch Chiếu Phim</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <script src="app.js"></script>
        <c:if test="${empty requestScope.city}">
            <script src="setCity.js"></script>
        </c:if>

        <link href="style.css" media="all" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="set-city">
            <h1>Vui lòng chọn thành phố của bạn</h1>
            <div id="city-select">
                Đang tải...
            </div>
        </div>
        <header>
            <div id="nav-bar">
                <a class="nav-item ${(param.action == 'movie') ? 'selected' : ''}" 
                   href="?action=movie">
                    Tìm lịch chiếu theo phim
                </a>
                <a class="nav-item ${(param.action == 'theater') ? 'selected' : ''}" 
                   href="?action=theater">
                    Tìm lịch chiếu theo rạp
                </a>
                <a class="nav-item ${(param.action == '') ? 'selected' : ''}" href="#">Tìm lịch chiếu theo ngày</a>
                Dữ liệu cập nhật từ: ${requestScope.date} - ${requestScope.url}
            </div>

        </header>
