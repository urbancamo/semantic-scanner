package eu.hecnet.apps.model;

import eu.hecnet.apps.scanner.file.FileTypesMap;

public class ScanResults {

	private long directoriesRead;
	private long filesRead;
	private boolean success;

	private String errorMessage;
	private Archive archive;

	private FileTypesMap fileTypes;

	public ScanResults(Archive archive) {
		setArchive(archive);
		fileTypes = new FileTypesMap();
	}

	public FileTypesMap getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(FileTypesMap fileTypes) {
		this.fileTypes = fileTypes;
	}

	public long getFilesRead() {
		return filesRead;
	}

	public void setFilesRead(long filesRead) {
		this.filesRead = filesRead;
	}

	public void bumpFilesRead() {
		filesRead++;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Archive getArchive() {
		return archive;
	}

	public void setArchive(Archive archive) {
		this.archive = archive;
	}

	public long getDirectoriesRead() {
		return directoriesRead;
	}

	public void setDirectoriesRead(long directoriesRead) {
		this.directoriesRead = directoriesRead;
	}

	public void bumpDirectoriesRead() {
		directoriesRead++;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ directoriesRead = " + directoriesRead);
		sb.append(", filesRead = " + filesRead);
		sb.append(", success = " + success);
		sb.append(", errorMessage = "
				+ (errorMessage == null ? "null" : errorMessage));
		sb.append(", archive = " + archive.toString());
		sb.append(", fileTypes = " + fileTypes.toString());
		sb.append("]");

		return sb.toString();

	}
}
