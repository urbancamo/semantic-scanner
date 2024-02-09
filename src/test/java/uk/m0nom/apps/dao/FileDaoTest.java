package uk.m0nom.apps.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import uk.m0nom.apps.controller.DataInitializer;
import uk.m0nom.apps.model.File;
import uk.m0nom.apps.model.FileType;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FileDaoTest {

	@Autowired
	private FileDao fileDao;

	@Autowired
	private DataInitializer dataInitializer;

	@Before
	public void prepareData() {
		dataInitializer.initData();
	}

	@Test
	public void shouldSaveAPerson() {
		File f = new File();
		f.setFilename("TEST.BAS");
		f.setFileType(FileType.BASIC_SOURCE);
		fileDao.save(f);
		Long id = f.getId();
		Assert.assertNotNull(id);
	}

	@Test
	public void shouldLoadAPerson() {
		Long template = dataInitializer.files.get(0);
		File p = fileDao.find(template);

		Assert.assertNotNull("Person not found!", p);
		Assert.assertEquals(template, p.getId());
	}

	public void shouldListPeople() {
		List<File> files = fileDao.getFiles();
		Assert.assertEquals(DataInitializer.FILE_COUNT, files.size());

	}

}
