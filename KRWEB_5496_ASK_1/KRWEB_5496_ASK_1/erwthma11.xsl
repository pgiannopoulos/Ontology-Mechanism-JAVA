<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>Schedule</h2>
    <table border="1">
      <tr bgcolor="#d8d8d8">
        <th>Title</th>
        <th>Professor</th>
		<th>Day</th>
      </tr>
      <xsl:for-each select="Schedule/Lesson/Lecture">
	  <xsl:sort select="Day"/>
	  <xsl:if test="Day = 'Monday'">
      <tr bgcolor="#d05637">
        <td><xsl:value-of select="../Title"/></td>
        <td><xsl:value-of select="../Professor"/></td>
		<td><xsl:value-of select="Day"/></td>
      </tr>
	  </xsl:if>
	  <xsl:if test="Day = 'Wednesday'">
      <tr bgcolor="#3498db">
        <td><xsl:value-of select="../Title"/></td>
        <td><xsl:value-of select="../Professor"/></td>
		<td><xsl:value-of select="Day"/></td>
      </tr>
	  </xsl:if>
	  <xsl:if test="Day = 'Thursday'">
      <tr bgcolor="#e3db27">
        <td><xsl:value-of select="../Title"/></td>
        <td><xsl:value-of select="../Professor"/></td>
		<td><xsl:value-of select="Day"/></td>
      </tr>
	  </xsl:if>
	  <xsl:if test="Day = 'Friday'">
      <tr bgcolor="#e201f0">
        <td><xsl:value-of select="../Title"/></td>
        <td><xsl:value-of select="../Professor"/></td>
		<td><xsl:value-of select="Day"/></td>
      </tr>
	  </xsl:if>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>
