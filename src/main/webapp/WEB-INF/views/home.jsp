<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="en">
<head>
	<title>Semantic Scanner</title>
	<meta charset="UTF-8" />
<title>Semantic Scanner - Home Page</title>
<script type="text/javascript" charset="utf-8" src="resources/js/jquery-1.7.1.js"></script>
<link type="text/css" rel="stylesheet" href="resources/css/style.css" />
</head>
<body>
<div id="a">
<header>
	<hgroup>
	<h1>Semantic Scanner - Home</h1>
	</hgroup>  
</header>
<article>
	<p>
		The semantic scanner is a prototype application for generating a semantic model
		containing meta data of file archives, with a slant towards 
		<a href="http://en.wikipedia.org/wiki/OpenVMS">VMS</a> files.
	</p>
	<p>
		The Scan page allows scanning of a local repository which is defined in the 
		configuration file <em>environment.properties</em> with the parameters
		<em>archive.name</em> and <em>archive.base</em>. These can be modified on
		the form.
	</p>
	<p>
		The scanner makes use of the <a href="http://incubator.apache.org/jena/">Apache Jena library</a>
		to create an in-memory model of meta data associated with files and their contents. The model can
		optionally be persisted to a number of standard formats including <a href="http://www.w3.org/RDF/">RDF</a>,
		<a href="http://www.w3.org/2001/sw/RDFCore/ntriples/">N-triples</a> and 
		<a href="http://www.dajobe.org/2003/11/ntriplesplus/">Turtle</a> formats. 
	</p>
	<p>
		The query page allows the formation and execution of <a href="http://www.w3.org/standards/semanticweb/query">SPARQL</a> 
		queries against model files.
	</p> 
	<p>
		The explore page can be used to browse the file archive with the benefit of semantic meta-data.
	</p>
</article>
<aside>
	<nav>
		<form method="post">
			<input type="submit" name="action" value="Scan"/>
			<input type="submit" name="action" value="Query"/>
			<input type="submit" name="action" value="Explore"/>
		</form>
	</nav>
</aside>
<footer><a href="/semantic-scanner/spring/">Semantic Scanner</a></footer>
</div>
</body>
</html>
