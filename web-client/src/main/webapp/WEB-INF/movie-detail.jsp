<%-- 
    Document   : movie-detail
    Created on : Feb 8, 2015, 9:28:56 PM
    Author     : HaiNNT
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="xml" value="${requestScope.xml}"/>
<x:parse var="doc" xml="${xml}" scope="session"/>
<c:import var="movieXsl" url="movie/detail.xsl" charEncoding="UTF-8"/>
<x:transform xslt="${movieXsl}" xml="${xml}">
    <x:param name="movieId" value="${param.movie}"/>
</x:transform>