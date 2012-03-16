package eu.hecnet.apps.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import eu.hecnet.apps.dao.FileDao;
import eu.hecnet.apps.model.File;

@Controller
@RequestMapping("/file/")
public class FileController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileController.class);

	@Autowired
	private FileDao fileDao;

	@RequestMapping(method = RequestMethod.GET, value = "edit")
	public ModelAndView editFile(
			@RequestParam(value = "id", required = false) Long id) {
		logger.debug("Received request to edit file id : " + id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("edit");
		File file = null;
		if (id == null) {
			file = new File();
		} else {
			file = fileDao.find(id);
		}

		mav.addObject("file", file);
		return mav;

	}

	@RequestMapping(method = RequestMethod.POST, value = "edit")
	public String saveFile(@ModelAttribute File file) {
		logger.debug("Received postback on file " + file);
		fileDao.save(file);
		return "redirect:list";

	}

	@RequestMapping(method = RequestMethod.GET, value = "list")
	public ModelAndView listFiles() {
		logger.debug("Received request to list files");
		ModelAndView mav = new ModelAndView();
		List<File> files = fileDao.getFiles();
		logger.debug("File Listing count = " + files.size());
		mav.addObject("files", files);
		mav.setViewName("list");
		return mav;

	}

}
