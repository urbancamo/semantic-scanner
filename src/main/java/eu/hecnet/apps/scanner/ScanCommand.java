package eu.hecnet.apps.scanner;

public class ScanCommand {
	public enum ScanCommandType {
		START, STOP
	}

	public enum ScanCommandUpdateInterval {
		FILE, DIRECTORY, COMPLETE
	}

	private ScanCommandType command;
	private String archiveName;
	private String archiveBase;
	private ScanCommandUpdateInterval updateInterval;

	public ScanCommand() {
	}

	public ScanCommand(ScanCommandType command, String archiveName,
			String archiveBase, String updateInterval) {
		setCommand(command);
		setArchiveName(archiveName);
		setArchiveBase(archiveBase);
		setUpdateInterval(updateInterval);
	}

	public ScanCommand(ScanCommandType command) {
		setCommand(command);
	}

	public ScanCommandType getCommand() {
		return command;
	}

	public void setCommand(ScanCommandType command) {
		this.command = command;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getArchiveBase() {
		return archiveBase;
	}

	public void setArchiveBase(String archiveBase) {
		this.archiveBase = archiveBase;
	}

	public ScanCommandUpdateInterval getUpdateInterval() {
		return updateInterval;
	}

	public void setUpdateInterval(String updateInterval) {
		if ("DIRECTORY".equals(updateInterval)) {
			this.updateInterval = ScanCommandUpdateInterval.DIRECTORY;
		} else if ("FILE".equals(updateInterval)) {
			this.updateInterval = ScanCommandUpdateInterval.FILE;
		} else {
			this.updateInterval = ScanCommandUpdateInterval.COMPLETE;
		}
	}

	public boolean isUpdateIntervalOnCompletion() {
		return updateInterval == ScanCommandUpdateInterval.COMPLETE;
	}

	public boolean isUpdateIntervalOnDirectoryScan() {
		return updateInterval == ScanCommandUpdateInterval.DIRECTORY;
	}

	public boolean isUpdateIntervalOnFileScan() {
		return updateInterval == ScanCommandUpdateInterval.FILE;
	}
}
