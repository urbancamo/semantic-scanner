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
<script type="text/javascript" charset="utf-8" src="resources/js/jquery-1.7.1.js"></script>
<script type="text/javascript" charset="utf-8" src="resources/js/html5-json-pie.js"></script>
<script type="text/javascript">
	var ws;

	$(document).ready(
			function() {
				// Open a new websocket back to the server
				ws = new WebSocket("ws://localhost:8080/semantic-scanner/scanWebSocket/scan");

				// Required open handler - NOP
				ws.onopen = function(event) {
				}

				// Called when we receive a websocket message				
				ws.onmessage = function(event) {
					// event.data is a string - parse into a Javascript object via JSON
					var feedback = JSON.parse(event.data);
					
					// Update status fields
					$('#status').val(feedback.status);
					$('#fileCount').val(feedback.fileCount);
					$('#directoryCount').val(feedback.directoryCount);
					$('#file').val(feedback.file);
					$('#directory').val(feedback.directory);
					
					var updateInterval = $('#update').val();
					// On completion update the pie chart
					if (feedback.status == updateInterval) {
							$("#chart").pieChart(feedback.pieData,
					        	{} // empty styling - use default
							);
					}
				}
				
				// Required close handler - NOP
				ws.onclose = function(event) {
				}

			});

	// Send a command to the server via the websocket
	function sendAction(action) {
		// Insert command and archive parameters into javascript object
		var persist = $('#persist:checked').val() == 'Persist';
		
		var scanCommand = {
			"command": action, 
			"archiveName": $('#name').val(), 
			"archiveBase": $('#base').val(), 
			"updateInterval" : $('#update').val(),
			"persist" : persist,
			"format" : $('#format').val()
		};
		
		// If starting clear the feedback fields
		if (scanCommand.action == 'START') {
			$('#status').val("");
			$('#file').val("");
			$('#directory').val("");
			$('#chart').pieChart(null, {});
		}
		// Convert object into JSON string
		var jsonCommand = JSON.stringify(scanCommand);
		ws.send(jsonCommand);
	}
	
</script>
</head>
<body>
<header>
	<hgroup>
	<h1>Semantic Scanner - Scan</h1>
	</hgroup>  
</header>
<article>
	<form>
		<label for="name">Repository Name:</label><input id="name"type="text" width="40" value="${archiveName}">
		<label for="base">Repository Base:</label><input id="base" type="text" width="40 "value="${archiveBase}">
		<label for="status">Status:</label><input id="status" type="text" width="20" readonly="readonly">
		<label for="directoryCount">Directories Scanned:</label><input id="directoryCount" type="text" width="6" readonly="readonly">
		<label for="fileCount">Files Scanned:</label><input id="fileCount" type="text" width="6" readonly="readonly">
		<label for="file">File:</label><input id="file" type="text" width="40" readonly="readonly">
		<label for="directory">Directory:</label><input id="directory" type="text" width="40" readonly="readonly">
	</form>
	<canvas id="chart" width="600" height="500"></canvas>
</article>
<aside>
	<nav>
		<form name="actions" action="">
			<span>
				<input id="start" type="button" value="Start..." onclick="sendAction('START');" />
				<input id="stop" type="button" value="Stop..." onclick="sendAction('STOP');" />
			</span>
			<label for="realtime">Update Interval</label>
			<select id ="update">
				<option value="COMPLETE">Complete</option>
				<option value="DIRECTORY">Directory Scan</option>
				<option value="FILE">File Scan</option>
			</select>
			<label for="persist">Persist</label>
			<input id="persist" type="checkbox" name="persist" value="Persist" />
			<select id ="format">
				<option value="RDF/XML-ABBREV">RDF Abbrev</option>
				<option value="RDF/XML">RDF</option>
				<option value="N-TRIPLE">N-Triple</option>
				<option value="TURTLE">Turtle</option>
			</select>
		</form>
	</nav>
</aside>
<footer>
<a href="/semantic-scanner/spring/">Semantic Scanner</a>
</footer>
</body>
</html>
