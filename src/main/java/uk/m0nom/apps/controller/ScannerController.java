package uk.m0nom.apps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.m0nom.apps.scanner.ScanCommand;

/**
 * Controller for the scanner page - very simple as the scanner page is Web
 * Socket based
 */
@Controller
public class ScannerController {

	@Autowired
	private ScanCommand defaultScanCommand;

	private static final Logger logger = LoggerFactory.getLogger(ScannerController.class);

	/**
	 * Selects the home page and populates the model with a message
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/scan")
	public String scan(Model model) {
		logger.info("Received scan page request");
		model.addAttribute("archiveName", defaultScanCommand.getArchiveName());
		model.addAttribute("archiveBase", defaultScanCommand.getArchiveBase());
		return "scan";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/scan")
	public String startScan(Model model) {
		return "redirect:/";
	}
}
