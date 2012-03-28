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
<link type="text/css" rel="stylesheet" href="resources/css/ui-lightness/jquery-ui-1.8.18.custom.css" />
<script type="text/javascript" charset="utf-8" src="resources/js/jquery-1.7.1.js"></script>
<script type="text/javascript" charset="utf-8" src="resources/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" charset="utf-8" src="resources/js/html5-json-pie.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
        $("#dclDiv").hide();
 
		$("#type").change(function() {
			var type = $("#type").val();
			if (type = "DCL Script") {
				$("#dclDiv").show();
			} else {
				$("#dclDiv").hide();
			}
		});
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
	<sf:form method="POST" modelAttribute="queryOptions">
		<sf:label path="model">Model</sf:label>
		<sf:select path="model" items="${models}">
		</sf:select>

		<sf:label path="fromYear">Date From</sf:label>
		<sf:select path="fromYear" items="${fromYears}">
		</sf:select>

		<sf:label path="toYear">Date To</sf:label>
		<sf:select path="toYear" items="${toYears}">
		</sf:select>

		<sf:label path="size">Size up to</sf:label>
		<sf:select path="size" items="${sizes}" itemLabel="name" itemValue="size" />

		<sf:label path="feature">Features</sf:label>
		<sf:select path="feature" items="${features}">
		</sf:select>

		<sf:label path="type">Mime Type</sf:label>
		<sf:select path="type" items="${types}"  itemLabel="description" itemValue="mimeType"/>

		<sf:label path="searchText">Search Text:</sf:label>
		<sf:input path="searchText"/>

		<div id="dclDiv">
			<sf:label path="lexical">Lexical Function:</sf:label>
			<sf:select path="lexical">
				<sf:options items="${lexicals}"/>
			</sf:select>
		</div>
		<sf:input path="action" type="submit" value="Execute"/>
	</sf:form>
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
