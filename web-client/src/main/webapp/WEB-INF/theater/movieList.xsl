<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output encoding="UTF-8" />
    <xsl:param name="theaterId"/>   
    <xsl:template match="/">
        <div id="movie-list">       
            <xsl:for-each select="//movie[sessions[session[theater[id=$theaterId]]]]">
                <div class="movie" data-id="{id}" tabindex="0">
                    <a href="?action=theater&amp;movie={id}&amp;theater={$theaterId}">
                        <img class="movie-poster" src="{poster}" />
                        <div>
                            <h3>
                                <xsl:value-of select="name"/>
                                <xsl:if test="age_restriction != ''" >
                                    <i class="icon restriction-icon">
                                        <xsl:value-of select="age_restriction"/>
                                    </i>
                                </xsl:if>
                            </h3>
                        
                            <div class="description">
                                <span>Thể loại:</span>
                                <xsl:value-of select="genre"/>
                            </div>
                            <div class="description">
                                <span>Thời lượng:</span>
                                <xsl:value-of select="length"/>
                            </div>
                        </div>                           
                    </a>
                </div>
            </xsl:for-each>
        </div>
    </xsl:template>

</xsl:stylesheet>