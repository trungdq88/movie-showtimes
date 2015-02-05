<xsl:stylesheet version="1.0" 
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://www.w3.org/TR/xhtml1/strict">
    <xsl:output encoding="UTF-8" />
    <xsl:param name="movieName"/>
    <xsl:param name="theaterName"/>
    <xsl:template match="/">   
        <xsl:for-each select="//movie[name=$movieName]/sessions/session[theater[name=$theaterName]]">
            <div class="movie" data-name="{name}" tabindex="0">
                <a href="#">
                    <div class="date">
                        <xsl:value-of select="show_time"/>
                        <br/>
                    </div>            
                </a>
            </div>   
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
