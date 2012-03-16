package eu.hecnet.apps.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class FileBundle extends File {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3205541607016660601L;

	@OneToMany
	private Set<File> files;

	public FileBundle(String filename, FileType fileType) {
		super(filename, FileType.DIRECTORY);
	}

	public void addFile(File file) {
		files.add(file);
	}

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}
}
