<%-- 
    Document   : theater
    Created on : Feb 8, 2015, 9:03:23 PM
    Author     : HaiNNT
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="xml" value="${requestScope.xml}"/>
<x:parse var="doc" xml="${xml}" scope="session"/>
<c:import url="header.jsp"/>
<div id="body">
            <div id="theater-container"  class="container">
            <c:import var="theaterXsl" url="theater/theaterList.xsl" charEncoding="UTF-8"/>
            <x:transform xslt="${theaterXsl}" xml="${xml}">
            </x:transform>
        </div>


    <c:if test="${not empty param.theater}">   
    <div id="movie-container" class="container">
        <c:import var="movieXsl" url="theater/movieList.xsl" charEncoding="UTF-8"/>
        <x:transform xslt="${movieXsl}" xml="${xml}">
            <x:param name="theaterId" value="${param.theater}"/>
        </x:transform>
    </div>

    </c:if>

    <c:if test="${not empty param.movie and not empty param.theater}">
        <div id="showtime-container" class="container">
            <c:import var="timeXsl" url="theater/timeList.xsl" charEncoding="UTF-8"/>
            <x:transform xslt="${timeXsl}" xml="${xml}">
                <x:param name="movieId" value="${param.movie}"/>
                <x:param name="theaterId" value="${param.theater}"/>
            </x:transform>
        </div>
    </c:if>

</div>
<c:import url="footer.jsp"/>
