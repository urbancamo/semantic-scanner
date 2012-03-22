package eu.hecnet.apps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eu.hecnet.apps.scanner.ScanCommand;

/**
 * Sample controller for going to the home page with a message
 */
@Controller
public class HomeController {

	@Autowired
	private ScanCommand defaultScanCommand;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Selects the home page and populates the model with a message
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Home page called");
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String processCommand(Model model, @RequestParam("action") String action) {
		logger.info("Received home page request for " + action);
		// Add information to the model here...
		if ("Scan".equals(action)) {
			model.addAttribute("archiveName", defaultScanCommand.getArchiveName());
			model.addAttribute("archiveBase", defaultScanCommand.getArchiveBase());
			return "scan";
		} else if ("Query".equals(action)) {
			return "redirect:query";
		}
		return "";
	}
}
