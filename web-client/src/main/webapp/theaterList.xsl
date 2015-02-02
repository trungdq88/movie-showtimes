<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="//movie/length">
        <a href="?movie={name}">
            <xsl:value-of select="."/>
        </a>
        <br/>
    </xsl:template>
</xsl:stylesheet>
<!-- /movies/movie[name='The Hobbit']/sessions/session/theater -->