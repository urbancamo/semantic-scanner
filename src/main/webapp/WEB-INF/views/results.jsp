<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false" %>
<html lang="en">
<head>
	<title>Semantic Scanner Results Page</title>
	<meta charset="UTF-8" />
<title>Semantic Scanner - Scan</title>
<link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700,300italic,400italic,500italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Ubuntu+Mono' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="resources/css/style.css" />
</head>
<body>
<header>
	<hgroup>
	<h1>Semantic Scanner - Query Results</h1>
	</hgroup>  
</header>
<article>
	<textarea rows="8" cols="50">
		${query}
	</textarea>
	
	<c:forEach items="${results}" var="v_result">
		${v_result.uri}, ${v_result.lexical}
		<!-- <a href="edit?id=${v_file.id}">${v_file.id} -
			${v_file.filename} ${v_file.fileBundle} ${v_file.fileType}</a> -->
		<br/>
	</c:forEach>
</article>
<aside>
	<nav>
	<nav>
		<form name="navigate" method="post">
			<input type="submit" name="action" value="Home"/>
		</form>
	</nav>
	</nav>
</aside>
<footer>
<a href="/semantic-scanner/spring/">Semantic Scanner</a>
</footer>
</body>
</html>
