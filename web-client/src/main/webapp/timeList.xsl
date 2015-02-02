<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <span>111111111</span>
        <br/>
        <xsl:for-each select="//movie[name='The Hobbit']/sessions/session/theater">
            <span>111111111</span>
        <a href="?movie={name}">
            <xsl:value-of select="name"/>
        </a>
        <br/>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
