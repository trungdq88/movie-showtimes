<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <div id="movie-list">       
            <xsl:for-each select="//movie">
                <div class="movie">
                    <div>
                        <img class="movie-poster" src="{poster}" />
                    </div>
                    <div>
                        <a href="?movie={name}">
                            <h3><xsl:value-of select="name"/></h3>
                        </a>
                        <div>
                            <span>Thể loại:</span><xsl:value-of select="genre"/></div>
                        <div>
                            <span>Thời lượng:</span><xsl:value-of select="length"/></div>                           
                        <div>
                            <span>Tóm tắt: </span>
                            <p>
                                <xsl:value-of select="description"/>
                            </p>
                        </div>
                        <xsl:if test="age_restriction != ''" >
                            <i class="icon restriction-icon">
                                <xsl:value-of select="age_restriction"/>
                            </i>
                        </xsl:if>
                        <a href="#">Chi tiết</a>
                    </div>                               
                </div>
            </xsl:for-each>
        </div>
    </xsl:template>

</xsl:stylesheet>