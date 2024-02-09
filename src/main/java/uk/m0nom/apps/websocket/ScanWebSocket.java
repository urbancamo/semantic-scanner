package uk.m0nom.apps.websocket;

import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import uk.m0nom.apps.scanner.ArchiveScanExecutor;
import uk.m0nom.apps.scanner.ArchiveScanner;
import uk.m0nom.apps.scanner.ScanCommand;
import uk.m0nom.apps.scanner.ScanFeedback;

public class ScanWebSocket implements OnTextMessage {
	private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private ArchiveScanner scanner;

	private Connection connection;
	private final Set<ScanWebSocket> scanStreams;

	private ScanCommand scanCommand;

	/** Jackson object mapper */
	private final ObjectMapper mapper = new ObjectMapper();

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

		if (scanCommand.getCommand() == ScanCommand.ScanCommandType.START) {
            ArchiveScanExecutor archiveScanExecutor = new ArchiveScanExecutor();
			scanner = new ArchiveScanner();
			scanner.setCommand(scanCommand);
			scanner.initialise(this);

			archiveScanExecutor.execute(scanner);
		} else if (scanCommand.getCommand() == ScanCommand.ScanCommandType.STOP) {
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
			} catch (Exception ignored) {
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
