package uk.m0nom.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TextFile extends File {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9213351348543354407L;

	@Column
	String contentSnippet;

	public TextFile(String filename, FileBundle fileBundle, FileType fileType,
			String contentSnippet) {
		setFilename(filename);
		setFileBundle(fileBundle);
		setFileType(fileType);
		setContentSnippet(contentSnippet);
	}

	public String getContentSnippet() {
		return contentSnippet;
	}

	public void setContentSnippet(String contentSnippet) {
		this.contentSnippet = contentSnippet;
	}

}
