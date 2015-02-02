<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="movieName"/>
    <xsl:param name="theaterName"/>
    <xsl:template match="/">    
        <xsl:for-each select="//movie[name='The Hobbit']/sessions/session[theater[name='Galaxy Quang Trung 1']]">
                <xsl:value-of select="show_time"/>
            <br/>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
