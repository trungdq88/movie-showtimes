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
    <xsl:param name="theaterId"/>
    <xsl:template match="/">
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
                    <fo:block font-size="14pt" font-family="sans-serif"
                              line-height="24pt" background-color="cyan"
                              space-after.optimum="15pt" text-align="center"
                              padding-top="3pt">
                        
                        
                        <xsl:value-of select="//movie[id=$movieId]/name" /> - <xsl:value-of select="//theater[id=$theaterId]/name" />
                        
                        
                    </fo:block>
                </fo:static-content>
                
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block font-size="18pt" font-family="sans-serif"
                              line-height="24pt" space-after.optimum="15pt" 
                              text-align="center" padding-top="3pt">
                    </fo:block>
                </fo:static-content>
                
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:table border-collapse="separate" table-layout="fixed">
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="5cm"/>
                            
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell border-color="blue"
                                                   border-width="0.5pt"
                                                   border-style="solid">
                                        <fo:block text-align="center">No</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                                   border-width="0.5pt"
                                                   border-style="solid">
                                        <fo:block text-align="center">Date</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                                   border-width="0.5pt"
                                                   border-style="solid">
                                        <fo:block text-align="center">Time</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                
                                <xsl:for-each select="//movie[id=$movieId]/sessions/session[theater[id=$theaterId]]">
                                    <fo:table-row>
                                        <fo:table-cell border-color="blue"
                                                       border-width="0.5pt"
                                                       border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:number level="single" count="session"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue"
                                                       border-width="0.5pt"
                                                       border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="username" />
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue"
                                                       border-width="0.5pt"
                                                       border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="password" />
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                                
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>

