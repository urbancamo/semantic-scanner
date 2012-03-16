package eu.hecnet.apps.scanner.file;

public class FileTypeCounter {
	private String fileType;
	private long count;

	public FileTypeCounter(String fileType) {
		setFileType(fileType);
		setCount(0L);
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public void bumpCount() {
		this.count++;
	}
}
