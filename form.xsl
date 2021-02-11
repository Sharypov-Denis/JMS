<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Test Case Results</h1>
                <table border="1">
                    <tr>
                        <th>target</th>
                        <th>dispatched</th>
                        <th>sometags</th>
                    </tr>
                    <xsl:for-each select="urlset/url">
                        <tr>
                            <td>
                                <xsl:value-of select="target"/>
                            </td>
                            <td>
                                <xsl:value-of select="dispatched"/>
                            </td>
                            <td>
                                <xsl:value-of select="sometags"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>