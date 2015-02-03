<xsl:stylesheet version="1.0" 
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns="http://www.w3.org/TR/xhtml1/strict">
    <xsl:param name="movieName"/>
    <xsl:param name="theaterName"/>
    <xsl:template match="/">   
        <div class="time-list">
            <xsl:for-each select="//movie[name=$movieName]/sessions/session[theater[name=$theaterName]]">
                <div class="date">
                    <xsl:value-of select="show_time"/>
                    <!--<xsl:variable name="time" select="show_time"/>-->
                    <xsl:value-of select="xs:dateTime('1970-01-01T00:00:00') + 1236888746689 * xs:dayTimeDuration('PT0.001S')"/>
                    <!--<xsl:value-of select="date:add('1970-01-01T00:00:00Z', date:duration(1236888746689 div 1000))"/>-->

<!--                    <xsl:value-of select="floor(days-from-duration($time) * 3600 * 24 + 
                    hours-from-duration($time) * 3600 + 
                    minutes-from-duration($time) * 60 + 
                    seconds-from-duration($time))"/>-->
                </div>               
            </xsl:for-each>
        </div>
    </xsl:template>
</xsl:stylesheet>
