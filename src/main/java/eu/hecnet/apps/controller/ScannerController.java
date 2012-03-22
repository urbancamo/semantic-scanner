package eu.hecnet.apps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for the scanner page - very simple as the scanner page is Web
 * Socket based
 */
@Controller
public class ScannerController {

	private static final Logger logger = LoggerFactory.getLogger(ScannerController.class);

	/**
	 * Selects the home page and populates the model with a message
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/scan")
	public String scan(Model model) {
		logger.info("Scanner started");
		return "scan";
	}

	@RequestMapping(method = RequestMethod.POST, value = "scan")
	public String startScan(Model model) {
		logger.info("Received start scan request");
		return "results";
	}
}
