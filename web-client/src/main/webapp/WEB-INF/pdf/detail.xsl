<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : timeList.xsl.xsl
    Created on : February 9, 2015, 10:56 AM
    Author     : HaiNNT
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>
    <xsl:param name="movieId"/>
    <xsl:template match="/">
        <xsl:variable name="movie" select="//movie[id=$movieId]" />
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="x" page-height="8.5in"
                                       page-width="11in" margin-top="0.5in" margin-bottom="0.5in"
                                       margin-left="1in" margin-right="1in">
                    <fo:region-body margin-top="0.5in" />
                    <fo:region-before extent="1in" />
                    <fo:region-after extent="0.75in" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="x">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="14pt" font-family="Arial"
                              line-height="24pt" background-color="cyan"
                              space-after.optimum="15pt" text-align="center"
                              padding-top="3pt">                       
                        <xsl:value-of select="$movie/name" />                        
                    </fo:block>
                </fo:static-content>
                
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block font-size="18pt" font-family="sans-serif"
                              line-height="24pt" space-after.optimum="15pt" 
                              text-align="center" padding-top="3pt">
                    </fo:block>
                </fo:static-content>
                
                <fo:flow flow-name="xsl-region-body">
                    <!--<fo:block font-family="Arial">-->
                        <fo:block font-family="Arial">
                            Đạo diễn: <xsl:value-of select="$movie/director"/>
                        </fo:block>
                        <fo:block font-family="Arial">
                            Diễn viên: <xsl:value-of select="$movie/actor"/>
                        </fo:block>
                        <fo:block font-family="Arial">
                            Thể loại: <xsl:value-of select="$movie/genre"/>
                        </fo:block>
                        <fo:block font-family="Arial">
                            Khởi chiếu: <xsl:value-of select="$movie/show_date"/>
                        </fo:block>
                        <fo:block font-family="Arial">
                            Ngôn ngữ: <xsl:value-of select="$movie/audio_type"/>
                        </fo:block>
                        <fo:block font-family="Arial">
                            Chi tiết: <xsl:value-of select="$movie/description"/>
                        </fo:block>
                        <fo:block font-family="Arial">
                            Trailer: <xsl:value-of select="$movie/trailer"/>
                        </fo:block>
                    <!--</fo:block>-->
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>

