package uk.m0nom.apps.scanner;

import uk.m0nom.apps.scanner.file.FileTypeCounterPie;
import uk.m0nom.apps.scanner.file.FileTypesMap;

public class ScanFeedback {

	public enum ScanStatus {
		FILE, DIRECTORY, PERSISTING, COMPLETE
	}

	private ScanStatus status;
	private String file;
	private String directory;
	private long fileCount;
	private long directoryCount;
	private FileTypeCounterPie[] pieData;

	public ScanFeedback(ScanStatus status, String directory, String file,
			long directoryCount, long fileCount) {
		setStatus(status);
		setFile(file);
		setDirectory(directory);
		setFileCount(fileCount);
		setDirectoryCount(directoryCount);
	}

	public ScanStatus getStatus() {
		return status;
	}

	public void setStatus(ScanStatus status) {
		this.status = status;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public long getFileCount() {
		return fileCount;
	}

	public void setFileCount(long fileCount) {
		this.fileCount = fileCount;
	}

	public long getDirectoryCount() {
		return directoryCount;
	}

	public void setDirectoryCount(long directoryCount) {
		this.directoryCount = directoryCount;
	}

	public FileTypeCounterPie[] getPieData() {
		return pieData;
	}

	public void setPieData(FileTypesMap map) {
		pieData = map.asPieArray();
	}
}
