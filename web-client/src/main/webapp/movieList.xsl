<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="//movie">
        <a href="?movie={name}">
            <xsl:value-of select="name"/>
        </a>
        <br/>
    </xsl:template>
</xsl:stylesheet>