package eu.hecnet.apps.websocket;

import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import eu.hecnet.apps.scanner.ScanCommand;
import eu.hecnet.apps.scanner.ScanFeedback;

public class QueryWebSocket implements OnTextMessage {
	private Logger logger = Logger.getLogger(this.getClass());

	private Connection connection;
	private Set<QueryWebSocket> scanStreams;

	private ScanCommand scanCommand;

	/** Jackson object mapper */
	private ObjectMapper mapper = new ObjectMapper();

	public QueryWebSocket(Set<QueryWebSocket> streams) {
		this.scanStreams = streams;
	}

	public void onMessage(String data) {
		logger.info("ScanWebSocket.onMessage() received \"" + data + "\"");
		// try {
		// scanCommand = mapper.readValue(data, ScanCommand.class);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public void sendMessage(ScanFeedback feedback) {
		String json = getJsonForFeedback(feedback);
		logger.info(json);
		for (QueryWebSocket stream : scanStreams) {
			try {
				logger.debug("ScanWebSocket.onMessage() sending \"" + json + "\"");
				stream.connection.sendMessage(json);
			} catch (Exception e) {
			}
		}
	}

	public void onOpen(Connection connection) {
		this.connection = connection;
		scanStreams.add(this);
	}

	public void onClose(int closeCode, String message) {
		scanStreams.remove(this);
	}

	private String getJsonForFeedback(ScanFeedback feedback) {
		String json = "";
		try {
			json = mapper.writeValueAsString(feedback);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
