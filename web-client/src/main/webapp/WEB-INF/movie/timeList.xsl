<xsl:stylesheet version="1.0" 
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://www.w3.org/TR/xhtml1/strict">
    <xsl:output encoding="UTF-8" />
    <xsl:param name="movieId"/>
    <xsl:param name="theaterId"/>
    <xsl:template match="/">   
        <xsl:for-each select="//movie[id=$movieId]/sessions/session[theater[id=$theaterId]]">
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
