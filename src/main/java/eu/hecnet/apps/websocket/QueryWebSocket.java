package eu.hecnet.apps.websocket;

import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import eu.hecnet.apps.query.QueryCommand;
import eu.hecnet.apps.query.QueryFeedback;

public class QueryWebSocket implements OnTextMessage {
	private Logger logger = Logger.getLogger(this.getClass());

	private Connection connection;
	private Set<QueryWebSocket> queryStreams;

	private QueryCommand queryCommand;

	/** Jackson object mapper */
	private ObjectMapper mapper = new ObjectMapper();

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
			} catch (Exception e) {
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
