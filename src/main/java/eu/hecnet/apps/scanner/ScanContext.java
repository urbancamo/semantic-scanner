package eu.hecnet.apps.scanner;

import java.io.File;

import eu.hecnet.apps.websocket.ScanWebSocket;

public class ScanContext {

	private ScanWebSocket socket;

	private File archiveBase;
	private File current;

	public File getArchiveBase() {
		return archiveBase;
	}

	public void setArchiveBase(File archiveBase) {
		this.archiveBase = archiveBase;
	}

	public File getCurrent() {
		return current;
	}

	public void setCurrent(File current) {
		this.current = current;
	}

	public ScanWebSocket getSocket() {
		return socket;
	}

	public void setSocket(ScanWebSocket socket) {
		this.socket = socket;
	}
}
