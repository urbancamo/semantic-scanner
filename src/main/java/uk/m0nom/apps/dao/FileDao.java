package uk.m0nom.apps.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uk.m0nom.apps.model.File;

@Repository
public class FileDao {

	@PersistenceContext
	private EntityManager entityManager;

	public File find(Long id) {
		return entityManager.find(File.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<File> getFiles() {
		return entityManager.createQuery("select f from File f")
				.getResultList();
	}

	@Transactional
	public File save(File file) {
		if (file.getId() == null) {
			entityManager.persist(file);
			return file;
		} else {
			return entityManager.merge(file);
		}
	}

}
