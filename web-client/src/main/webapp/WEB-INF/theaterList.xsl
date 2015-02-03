<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="movieName"/>
    <xsl:template match="/">   
        <div id="theater-list">
            <xsl:for-each select="//movie[name=$movieName]/sessions/session/theater">
                <div class="theater"> 
                    <a href="?movie={$movieName}&amp;theater={name}">
                        <h3>
                            <xsl:value-of select="name"/>
                        </h3>
                    </a>
                    <div>
                        <span>Address:</span><xsl:value-of select="address"/>
                    </div>
                    <a href="#">Chi tiết</a>                               
                </div>
            </xsl:for-each>       
        </div>
    </xsl:template>
</xsl:stylesheet>