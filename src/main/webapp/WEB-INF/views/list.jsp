<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Query Results</title>
</head>
<body>
<h1>Listing Files</h1>
<c:forEach items="${files}" var="v_file">
	<a href="edit?id=${v_file.id}">${v_file.id} -
	${v_file.filename} ${v_file.fileBundle} ${v_file.fileType}</a>
	<br />
</c:forEach>
<a href="edit"> Add File</a>
</body>
</html>
