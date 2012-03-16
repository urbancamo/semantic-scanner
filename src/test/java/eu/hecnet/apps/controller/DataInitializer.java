package eu.hecnet.apps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import eu.hecnet.apps.model.File;
import eu.hecnet.apps.model.FileType;

@Component
@Scope("prototype")
public class DataInitializer {

	public static final int FILE_COUNT = 3;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Long> files = new ArrayList<Long>();

	public void initData() {
		files.clear();// clear out the previous list of files
		addFile("ADVENT.FOR", FileType.FORTRAN_SOURCE);
		addFile("DESCRIP.MMS", FileType.MMS_MAKEFILE);
		addFile("READ.ME", FileType.README);
		entityManager.flush();
		entityManager.clear();
	}

	public void addFile(String filename, FileType fileType) {
		File f = new File();
		f.setFilename(filename);
		f.setFileType(fileType);
		entityManager.persist(f);
		files.add(f.getId());
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
