package uk.m0nom.apps.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FileControllerTest {

	@Autowired
	private DataInitializer dataInitializer;

	@SuppressWarnings("unused")
	@Autowired
	private FileController fileController;

	@Before
	public void before() {
		dataInitializer.initData();
	}

	@Test
	public void shouldReturnFileListView() {
		// ModelAndView mav = fileController.listFiles();
		// assertEquals("list", mav.getViewName());
		//
		// @SuppressWarnings("unchecked")
		// List<File> files = (List<File>) mav.getModelMap().get("files");
		// assertNotNull(files);
		// assertEquals(DataInitializer.FILE_COUNT, files.size());
	}

	// public void shouldReturnNewFileWithEditMav() {
	// ModelAndView mav = fileController.editFile(null);
	// assertNotNull(mav);
	// assertEquals("edit", mav.getViewName());
	// Object object = mav.getModel().get("file");
	// assertTrue(File.class.isAssignableFrom(object.getClass()));
	// File file = (File) object;
	// assertNull(file.getId());
	// assertNull(file.getFilename());
	// assertNull(file.getFileType());
	// }

	@Test
	public void shouldReturnSecondFileWithEditMav() {
		// Long template = dataInitializer.files.get(1);
		// ModelAndView mav = fileController.editFile(template);
		// assertNotNull(mav);
		// assertEquals("edit", mav.getViewName());
		// Object object = mav.getModel().get("file");
		// assertTrue(File.class.isAssignableFrom(object.getClass()));
		// File file = (File) object;
		// assertEquals(template, file.getId());
	}

}
