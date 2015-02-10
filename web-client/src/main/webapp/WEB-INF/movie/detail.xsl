<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : detail.xsl
    Created on : February 8, 2015, 9:31 PM
    Author     : HaiNNT
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output encoding="UTF-8" />
    <xsl:param name="movieId"/>   
    <xsl:template match="/">
            <xsl:variable name="movie" select="//movie[id=$movieId]" />
             <img class="movie-poster" src="{$movie/poster}" />
            <div>
                <h2> 
                    <xsl:value-of select="$movie/name"/>
                </h2>
                <div>
                    <label>Đạo diễn: </label>
                    <span>
                        <xsl:value-of select="$movie/director"/>
                    </span>
                </div>
                <div>
                    <label>Diễn viên: </label>
                    <span>
                        <xsl:value-of select="$movie/actor"/>
                    </span>
                </div>
                <div>
                    <label>Thể loại: </label>
                    <span>
                        <xsl:value-of select="$movie/genre"/>
                    </span>
                </div>
                <div>
                    <label>Khởi chiếu: </label>
                    <span>
                        <xsl:value-of select="$movie/show_date"/>
                    </span>
                </div>
                <div>
                    <label>Ngôn ngữ: </label>
                    <span>
                        <xsl:value-of select="$movie/audio_type"/>
                    </span>
                </div>
                <div>
                    <label>Chi tiết: </label>
                    <span>
                        <xsl:value-of select="$movie/description"/>
                    </span>
                </div>
                <div>
                    <label>Trailer: </label>
                    <a href="http://{$movie/trailer}">
                        <xsl:value-of select="$movie/trailer"/>
                    </a>
                </div>
                <div>
                    <a href="?action=pdf&amp;movie={$movie/id}">
                        Download PDF
                    </a>
                </div>
            </div>
    </xsl:template>
</xsl:stylesheet>