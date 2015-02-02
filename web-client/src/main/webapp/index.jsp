<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="user" value="${param.txtUser}" scope="session"/>
<c:set var="pass" value="${param.txtPass}"/>
<c:import var="xml" url="WEB-INF/data.xml"  charEncoding="UTF-8"/>
<x:parse var="doc" xml="${xml}" scope="session"/>

<c:if test="${empty param.movie}">
    <h1>Danh sách phim</h1>
    <!-- TODO: use XSL here -->
    <c:import var="movieXsl" url="movieList.xsl"/>
    <x:transform xslt="${movieXsl}" xml="${xml}">
        <x:param name="movieName" value="${param.movie}"/>
    </x:transform>
</c:if>
<%--<x:forEach var="node" select="$doc//movie">
    Movie name:
    <a href="?movie=<x:out select="$node/name" />">
        <x:out select="$node/name"/>
    </a>
    <br/>
</x:forEach>--%>

<c:if test="${not empty param.movie and empty param.theater}">   
    
    <h1>Danh sách rạp</h1>
    <c:import var="theaterXsl" url="theaterList.xsl"/>
    <x:transform xslt="${theaterXsl}" xml="${xml}">
        <x:param name="movieName" value="The Hobbit"/>
    </x:transform>

    <%--<c:set var="movieParam" value="${param.movie}" />
    <x:forEach var="theater"
               select="$doc/movies/movie/name[text()=$movieParam]/../sessions/session/theater">
        Theater name:
        <a href="?movie=${param.movie}&theater=<x:out select="$theater/name"/>">
            <x:out select="$theater/name"/>
        </a>
        <br/>
    </x:forEach>--%>

</c:if>

<c:if test="${not empty param.theater and not empty param.movie}">
    <h1>Giờ chiếu</h1>
    <%--   <c:set var="theaterParam" value="${param.theater}" />
       <x:forEach var="session"
                  select="$doc/movies/movie/name[text()=$movieParam]/../sessions/session/theater/name[text()=$theaterParam]/../..">
           Show time:
               <x:out select="$session/show_time"/>
           <br/>
       </x:forEach> --%>

    <c:import var="timeXsl" url="timeList.xsl"/>
    <x:transform xslt="${timeXsl}" xml="${xml}">
        <x:param name="movieName" value="The Hobbit"/>
    </x:transform>
</c:if>
