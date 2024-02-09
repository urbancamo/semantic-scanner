package uk.m0nom.apps.websocket;

import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import uk.m0nom.apps.query.QueryCommand;
import uk.m0nom.apps.query.QueryFeedback;

public class QueryWebSocket implements OnTextMessage {
	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	private Connection connection;
	private final Set<QueryWebSocket> queryStreams;

	private QueryCommand queryCommand;

	/** Jackson object mapper */
	private final ObjectMapper mapper = new ObjectMapper();

	public QueryWebSocket(Set<QueryWebSocket> streams) {
		this.queryStreams = streams;
	}

	public void onMessage(String data) {
		logger.info("QueryWebSocket.onMessage() received \"" + data + "\"");
		// try {
		// queryCommand = mapper.readValue(data, QueryCommand.class);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public void sendMessage(QueryFeedback feedback) {
		String json = getJsonForFeedback(feedback);
		logger.info(json);
		for (QueryWebSocket stream : queryStreams) {
			try {
				logger.debug("QueryWebSocket.onMessage() sending \"" + json + "\"");
				stream.connection.sendMessage(json);
			} catch (Exception ignored) {
			}
		}
	}

	public void onOpen(Connection connection) {
		this.connection = connection;
		queryStreams.add(this);
	}

	public void onClose(int closeCode, String message) {
		queryStreams.remove(this);
	}

	private String getJsonForFeedback(QueryFeedback feedback) {
		String json = "";
		try {
			json = mapper.writeValueAsString(feedback);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
