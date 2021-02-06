<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <xsl:template match="/sproject">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><xsl:value-of select="//code"/> - <xsl:value-of select="//seminar"/> - <xsl:value-of select="//aname"/> </title> 
        <link rel="stylesheet" type="text/css" href="project.css"/>
      </head>

      <body>

<!-- Informace o kurzu -->
        <h3 class="course">
          <xsl:value-of select="//code"/> -            <xsl:value-of select="//cname"/>,            <xsl:value-of select="//semester"/>
        </h3>

<!-- Informace o autorovi -->
        <h3 class="author">
          <xsl:value-of select="//aname"/> ( <xsl:value-of select="//uname"/> )          <xsl:value-of select="//seminar"/>
        </h3>
        <h4 class="author">
         <xsl:element name="a">
		<xsl:attribute name ="href">
			mailto:<xsl:value-of select="//email"/>
               </xsl:attribute>
              <xsl:value-of select="//email"/>
	   </xsl:element>
        </h4>

<!-- Deklarace -->
        <p class="declaration">
          <xsl:value-of select="//declaration"/>
        </p>

<!-- Nazev semestralky -->
        <h1>
          <xsl:value-of select="//content/title"/>
        </h1>

<!-- Popis -->
        <h2>
          <xsl:value-of select="//content/description/title"/>
        </h2>
        <xsl:for-each select="//content/description/para">
		<p> <xsl:apply-templates /> </p>
        </xsl:for-each>
<!-- Patterns -->
        <a name='content' />
        <h2>
          <xsl:value-of select="//content/patterns/title"/>
        </h2>
        <ul> 
        <xsl:for-each select="//content/patterns/pattern/title">
		    <li> <a>
                <xsl:attribute name="href">#<xsl:value-of select="position()"/></xsl:attribute>
                <xsl:apply-templates />
            </a></li>
        </xsl:for-each>
        </ul>

    <xsl:for-each select="//content/patterns/pattern">
    <div class='pattern'>
        <a>
            <xsl:attribute name="name"><xsl:value-of select="position()"/></xsl:attribute>
        </a>
        <h2><xsl:value-of select="title"/></h2>
        <h3><xsl:value-of select="context/title"/></h3>
        <xsl:for-each select="context/description/para">
            <p><xsl:apply-templates /></p>
        </xsl:for-each>
        <h4>Codes with usage</h4>
        <xsl:for-each select="context/code">
            <h5>Usage Example #<xsl:value-of select="position()" /></h5>
            <pre><xsl:apply-templates select="." /></pre>
        </xsl:for-each>
        <h4>Links to the Source code, where the pattern is used</h4>
        <ul>
        <xsl:for-each select="context/link">
            <li><xsl:apply-templates select="." /></li>
        </xsl:for-each>
        </ul>

        <h3><xsl:value-of select="implementation/title"/> of <xsl:value-of select="title"/></h3>
        <xsl:for-each select="implementation/description/para">
            <p><xsl:apply-templates /></p>
        </xsl:for-each>
        <h4>UML diagramy</h4> 
        <xsl:for-each select="implementation/uml">
            <h5><xsl:value-of select="title"/></h5> 
            <xsl:element name="img">
		        <xsl:attribute name ="src">
			       <xsl:value-of select="img/@url"/>
            </xsl:attribute>
		        <xsl:attribute name ="alt">
			         <xsl:value-of select="img/@alt"/>
            </xsl:attribute>
		        <xsl:attribute name ="title">
			         <xsl:value-of select="img/@alt"/>
            </xsl:attribute>
	        </xsl:element>
        </xsl:for-each>
        
        <h4>Codes with Implementation</h4>
        <xsl:for-each select="implementation/code">
            <h5>Implementation Example #<xsl:value-of select="position()" /></h5>
            <pre><xsl:apply-templates select="." /></pre>
        </xsl:for-each>
        <h4>Links to the Source code, where the pattern is implemented</h4>
        <ul>
        <xsl:for-each select="implementation/link">
            <li><xsl:apply-templates select="." /></li>
        </xsl:for-each>
        </ul>
        
        <h3><xsl:value-of select="advantages/title"/> of <xsl:value-of select="title"/></h3>
        <xsl:for-each select="advantages/description/para">
            <p><xsl:apply-templates /></p>
        </xsl:for-each>
        <div class='right'><a href='#content'>Go to the pattern list</a></div>
    </div>
    </xsl:for-each>

<!-- Zaver -->
        <h2>
          <xsl:value-of select="//conclussions/title"/>
        </h2>
        <xsl:for-each select="//conclussions/*">
 		 <xsl:if test="local-name()='para'"> 
			<p> <xsl:apply-templates /> </p>
		 </xsl:if>
        </xsl:for-each>

<!-- Odkazy -->
        <h2>
          <xsl:value-of select="//references/title"/>
        </h2>
        <xsl:for-each select="//references/*">
 		 <xsl:if test="local-name()='para'"> 
			<p> <xsl:apply-templates /> </p>
		 </xsl:if>
        </xsl:for-each>

       </body>
    </html>
</xsl:template>

<!-- Nasledujici 2 templates resi mixec content v elementu para  a comment-->
<xsl:template match="*" >
  <xsl:copy>
   <xsl:copy-of select="@*"/>
   <xsl:apply-templates />
  </xsl:copy>
</xsl:template>

<xsl:template match="link" >
  <a href="{@url}">
   <xsl:apply-templates />
  </a>
</xsl:template>

<xsl:template match="para" >
  <p> <xsl:apply-templates /></p>
</xsl:template>

<xsl:template match="tt">
  <span class='code'> <xsl:apply-templates /></span>
</xsl:template>
</xsl:stylesheet>
