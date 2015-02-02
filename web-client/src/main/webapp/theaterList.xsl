<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="movieName"/>
    <xsl:template match="/">   
            <xsl:value-of select="//movie[name=$movieName]"/><br/>
        <xsl:for-each select="//movie[name=$movieName]/sessions/session/theater">
            <xsl:variable name="name" select="name" />
            <a href="?movie={$movieName}&amp;theater={name}">
                <xsl:value-of select="name"/>
            </a>
            <br/>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
<!-- /movies/movie[name='The Hobbit']/sessions/session/theater -->