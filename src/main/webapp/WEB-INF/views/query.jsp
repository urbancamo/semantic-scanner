<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="en">
<head>
	<title>Semantic Scanner Results Page</title>
	<meta charset="UTF-8" />
<title>Semantic Scanner - Scan</title>
<link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700,300italic,400italic,500italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Ubuntu+Mono' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="resources/css/style.css" />
<link type="text/css" rel="stylesheet" href="resources/css/ui-lightness/jquery-ui-1.8.18.custom.css" />
<script type="text/javascript" charset="utf-8" src="resources/js/jquery-1.7.1.js"></script>
<script type="text/javascript" charset="utf-8" src="resources/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" charset="utf-8" src="resources/js/html5-json-pie.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	    $("#dateFrom").datepicker();
	    $("#dateTo").datepicker();
	  });
</script>
</head>
<body>
<header>
	<hgroup>
	<h1>Semantic Scanner - Query</h1>
	</hgroup>  
</header>
<article>
	<form name="query" method="post">
		<label for="model">Model</label>
		<select id="model">
	        <c:forEach var="model" items="${fileList}">
	       		<option>${model}</option>
	        </c:forEach>
		</select>
		<label for="dateFrom">Date From</label>
		<select id="dateFrom">
			<c:forEach var="year" items="${yearList}">
				<option>${year}</option>
			</c:forEach>
		</select>
		<label for="dateTo">Date To</label>
		<select id="dateTo">
			<c:forEach var="year" items="${yearList}">
				<option>${year}</option>
			</c:forEach>
		</select>
		<label for="sizes">Size up to</label>
		<select id="sizes">
			<c:forEach var="size" items="${sizeList}">
				<option>${size}</option>
			</c:forEach>
		</select>
		<label for="features">Features</label>
		<select id="features">
			<c:forEach var="feature" items="${featureList}">
				<option>${feature}</option>
			</c:forEach>
		</select>
		<label for="types">Mime Type</label>
		<select id="types">
			<c:forEach var="type" items="${typeList}">
				<option>${type}</option>
			</c:forEach>
		</select>
		<input type="submit" name="action" value="Execute"/>
	</form>
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
