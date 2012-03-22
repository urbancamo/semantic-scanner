package eu.hecnet.apps.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eu.hecnet.apps.scanner.file.ModelFilenameFilter;
import eu.hecnet.file.detectors.CustomMimeType;
import eu.hecnet.file.detectors.openvms.VmsCustomMimeTypeLibrary;
import eu.hecnet.file.detectors.openvms.VmsFeature;

/**
 * Controller for the query page
 */
@Controller
public class QueryController {

	public final String DONT_CARE = "Don't care";

	private static final Logger logger = LoggerFactory.getLogger(QueryController.class);

	private VmsCustomMimeTypeLibrary mimeTypes;

	public QueryController() {
		mimeTypes = new VmsCustomMimeTypeLibrary();
	}

	/**
	 * Populate the query page with a list of models to use, and filetypes
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/query")
	public String query(Model model) {
		logger.info("Query page");

		// Determine list of model files to add
		File defaultDir = new File(".");

		String modelFiles[] = defaultDir.list(new ModelFilenameFilter());
		model.addAttribute("fileList", modelFiles);

		// Year options
		List<String> years = new ArrayList<String>();
		years.add(DONT_CARE);
		for (int i = 1979; i <= 2012; i++) {
			years.add("" + i);
		}
		model.addAttribute("yearList", years.toArray());

		// Size options
		List<String> sizes = new ArrayList<String>();
		sizes.add(DONT_CARE);
		for (int i = 1; i <= 90; i += (i == 1 ? 9 : 10)) {
			sizes.add("" + i + "B");
		}
		for (int i = 1; i <= 90; i += (i == 1 ? 9 : 10)) {
			sizes.add("" + i + "K");
		}
		for (int i = 1; i <= 90; i += (i == 1 ? 9 : 10)) {
			sizes.add("" + i + "M");
		}
		sizes.add(">100M");
		model.addAttribute("sizeList", sizes.toArray());

		// VMS Features
		List<String> features = new ArrayList<String>();
		features.add(DONT_CARE);
		features.add(VmsFeature.CALLING_STANDARD_LANGUAGE.getDescription());
		features.add(VmsFeature.SOURCE_FILE.getDescription());
		features.add(VmsFeature.MAKEFILE.getDescription());
		model.addAttribute("featureList", features.toArray());

		// Mime type options
		List<String> types = new ArrayList<String>();
		for (String mimeType : mimeTypes.keySet()) {
			CustomMimeType customMimeType = mimeTypes.get(mimeType);
			String typeString = customMimeType.getDescription();
			types.add(typeString);
		}
		Collections.sort(types);
		types.add(0, DONT_CARE);
		model.addAttribute("typeList", types.toArray());

		return "query";
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String processCommand(Model model, @RequestParam("action") String action) {
		logger.info("Received query page action request for " + action);
		if ("Home".equals(action)) {
			return "redirect:/";
		} else if ("Execute".equals(action)) {
			// TORA TORA
			// doIt();
		}
		return "";
	}

}
