package eu.hecnet.apps.websocket;

import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import eu.hecnet.apps.scanner.ArchiveScanExecutor;
import eu.hecnet.apps.scanner.ArchiveScanner;
import eu.hecnet.apps.scanner.ScanCommand;
import eu.hecnet.apps.scanner.ScanCommand.ScanCommandType;
import eu.hecnet.apps.scanner.ScanFeedback;

public class FeedbackWebSocket implements OnTextMessage {
	private Logger logger = Logger.getLogger(this.getClass());

	private ArchiveScanExecutor archiveScanExecutor;

	private ArchiveScanner scanner;

	private Connection connection;
	private Set<FeedbackWebSocket> feedbackStreams;

	private ScanCommand newState;
	private ScanCommand currentState = new ScanCommand(ScanCommandType.STOP);

	/** Jackson object mapper */
	private ObjectMapper mapper = new ObjectMapper();

	public FeedbackWebSocket(Set<FeedbackWebSocket> streams) {
		this.feedbackStreams = streams;
	}

	public void onMessage(String data) {
		logger.info("FeedbackWebSocket.onMessage() received \"" + data + "\"");
		try {
			newState = mapper.readValue(data, ScanCommand.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newState.getCommand() == currentState.getCommand()) {
			return;
		}
		if (newState.getCommand() == ScanCommandType.START) {
			archiveScanExecutor = new ArchiveScanExecutor();
			scanner = new ArchiveScanner();
			scanner.setCommand(newState);
			scanner.initialise(this);

			archiveScanExecutor.execute(scanner);
		} else if (newState.getCommand() == ScanCommandType.STOP) {
			if (scanner != null) {
				scanner.setCommand(newState);
				scanner.stop();
			}
		}
		currentState = newState;
	}

	public void sendMessage(ScanFeedback feedback) {
		String json = getJsonForFeedback(feedback);
		logger.info(json);
		for (FeedbackWebSocket stream : feedbackStreams) {
			try {
				logger.debug("FeedbackWebSocket.onMessage() sending \"" + json
						+ "\"");
				stream.connection.sendMessage(json);
			} catch (Exception e) {
			}
		}
	}

	public void onOpen(Connection connection) {
		this.connection = connection;
		feedbackStreams.add(this);
	}

	public void onClose(int closeCode, String message) {
		feedbackStreams.remove(this);
		scanner = null;
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
