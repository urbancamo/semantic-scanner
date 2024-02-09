package uk.m0nom.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Archive extends File {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5044281954393166019L;

	@Column
	private String archiveName;

	public Archive(String archiveName, String archivePath, FileType fileType) {
		super(archivePath, null, FileType.REPOSITORY);
		setArchiveName(archiveName);
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}
}
