<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:wicket="http://wicket.apache.org/dtds.data/wicket-xhtml1.4-strict.dtd"
	exclude-result-prefixes="wicket">
	<xsl:output method="text" encoding="UTF-8" />

	<xsl:template match="/">
		A message should be below.
		<br />
		<xsl:apply-templates />
		<xsl:value-of select="message" />
		<br />
		below is above here
	</xsl:template>

</xsl:stylesheet>