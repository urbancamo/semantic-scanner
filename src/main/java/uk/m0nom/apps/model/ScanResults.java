package uk.m0nom.apps.model;

import uk.m0nom.apps.scanner.file.FileTypesMap;

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
        String sb = "[ directoriesRead = " + directoriesRead +
                ", filesRead = " + filesRead +
                ", success = " + success +
                ", errorMessage = "
                + (errorMessage == null ? "null" : errorMessage) +
                ", archive = " + archive.toString() +
                ", fileTypes = " + fileTypes.toString() +
                "]";

		return sb;

	}
}
