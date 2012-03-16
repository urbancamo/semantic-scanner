<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="en">
<head>
	<title>Semantic Scanner</title>
	<meta charset="UTF-8" />
<title>Semantic Scanner - Scan</title>
<script type="text/javascript" charset="utf-8" src="resources/js/jquery-1.7.1.js"></script>
<link type="text/css" rel="stylesheet" href="resources/css/style.css" />
</head>
<body>
<header>
	<hgroup>
	<h1>Semantic Scanner</h1>
	</hgroup>  
</header>
<article>
${controllerMessage}<br/>
<br/>
<a href="file/list">Go to the file list</a>
<br/>
</article>
<aside>
	<nav>
		<form method="post" action="start">
			<input type="Submit" value="Scan Control"/>
		</form>
	</nav>
</aside>
<footer>Semantic Scanner</footer>
</body>
</html>
