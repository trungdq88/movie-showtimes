<%-- 
    Document   : movie
    Created on : Feb 3, 2015, 9:34:50 AM
    Author     : HaiNNT
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="user" value="${param.txtUser}" scope="session"/>
<c:set var="pass" value="${param.txtPass}"/>
<c:import var="xml" url="data.xml"  charEncoding="UTF-8"/>
<x:parse var="doc" xml="${xml}" scope="session"/>

<div id="body">
    <div id="movie-container">
        <h2>Chọn Phim</h2>
        <!-- TODO: use XSL here -->
        <c:import var="movieXsl" url="movieList.xsl"/>
        <x:transform xslt="${movieXsl}" xml="${xml}">
            <x:param name="movieName" value="${param.movie}"/>
        </x:transform>
    </div>

    <c:if test="${not empty param.movie}">   

        <h1>Danh sách rạp</h1>
        <c:import var="theaterXsl" url="theaterList.xsl"/>
        <x:transform xslt="${theaterXsl}" xml="${xml}">
            <x:param name="movieName" value="The Hobbit"/>
        </x:transform>


    </c:if>

    <c:if test="${not empty param.theater and not empty param.movie}">
        <h1>Giờ chiếu</h1>


        <c:import var="timeXsl" url="timeList.xsl"/>
        <x:transform xslt="${timeXsl}" xml="${xml}">
            <x:param name="movieName" value="${param.movie}"/>
            <x:param name="theaterName" value="${param.theater}"/>
        </x:transform>
    </c:if>

</div>