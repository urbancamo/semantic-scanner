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

public class ScanWebSocket implements OnTextMessage {
	private Logger logger = Logger.getLogger(this.getClass());

	private ArchiveScanExecutor archiveScanExecutor;

	private ArchiveScanner scanner;

	private Connection connection;
	private Set<ScanWebSocket> scanStreams;

	private ScanCommand scanCommand;

	/** Jackson object mapper */
	private ObjectMapper mapper = new ObjectMapper();

	public ScanWebSocket(Set<ScanWebSocket> streams) {
		this.scanStreams = streams;
	}

	public void onMessage(String data) {
		logger.info("ScanWebSocket.onMessage() received \"" + data + "\"");
		try {
			scanCommand = mapper.readValue(data, ScanCommand.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (scanCommand.getCommand() == ScanCommandType.START) {
			archiveScanExecutor = new ArchiveScanExecutor();
			scanner = new ArchiveScanner();
			scanner.setCommand(scanCommand);
			scanner.initialise(this);

			archiveScanExecutor.execute(scanner);
		} else if (scanCommand.getCommand() == ScanCommandType.STOP) {
			if (scanner != null) {
				scanner.setCommand(scanCommand);
				scanner.stop();
			}
		}
	}

	public void sendMessage(ScanFeedback feedback) {
		String json = getJsonForFeedback(feedback);
		logger.info(json);
		for (ScanWebSocket stream : scanStreams) {
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
