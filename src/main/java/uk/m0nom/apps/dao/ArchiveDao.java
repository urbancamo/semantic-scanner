package uk.m0nom.apps.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uk.m0nom.apps.model.Archive;

@Repository
public class ArchiveDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Archive find(Long id) {
		return entityManager.find(Archive.class, id);
	}

	@Transactional
	public Archive save(Archive archive) {
		if (archive.getId() == null) {
			entityManager.persist(archive);
			return archive;
		} else {
			return entityManager.merge(archive);
		}
	}

}
