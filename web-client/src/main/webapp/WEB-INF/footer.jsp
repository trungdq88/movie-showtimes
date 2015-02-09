
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <div id="caution">
        <a class="nav-item ${(param.action == '') ? 'selected' : ''}" href="#">Tìm lịch chiếu theo ngày</a>
                Dữ liệu cập nhật từ: ${requestScope.date} 
                <!-- ${requestScope.url} -->
                - 
                Lịch chiếu có thể thay đổi mà không báo trước, vui lòng liên hệ rạp phim trước khi mua vé
    </div>
    </body>
</html>