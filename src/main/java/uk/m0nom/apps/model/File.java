package uk.m0nom.apps.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class File implements Serializable {

	private static final long serialVersionUID = -1308795024262635690L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String filename;

	@ManyToOne
	private FileBundle fileBundle;

	@Column
	private FileType fileType;

	@Column
	private Date creationDate;

	public File() {
	}

	public File(String filename, FileType fileType) {
		this(filename, null, fileType);
	}

	public File(String filename, FileBundle fileBundle, FileType fileType) {
		this(filename, fileBundle, fileType, null);
	}

	public File(String filename, FileBundle fileBundle, FileType fileType,
			Date creationDate) {
		super();
		setFilename(filename);
		setFileBundle(fileBundle);
		setFileType(fileType);
		setCreationDate(creationDate);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", filename=" + filename + ", fileBundle="
				+ fileBundle + "]";
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public FileBundle getFileBundle() {
		return fileBundle;
	}

	public void setFileBundle(FileBundle fileBundle) {
		this.fileBundle = fileBundle;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		return id.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		File other = (File) obj;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (fileBundle == null) {
			if (other.fileBundle != null)
				return false;
		} else if (!fileBundle.equals(other.fileBundle))
			return false;
		return true;
	}

}
