package uk.m0nom.apps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import uk.m0nom.apps.query.QueryExecutor;
import uk.m0nom.apps.query.QueryResults;
import uk.m0nom.apps.query.form.QueryDataBinding;
import uk.m0nom.apps.query.form.QueryOptions;
import uk.m0nom.apps.query.form.QuerySizeEditor;
import uk.m0nom.apps.query.form.QuerySize;

/**
 * Controller for the query page
 */
@Controller
public class QueryController {

	private static final Logger logger = LoggerFactory.getLogger(QueryController.class);

    @Autowired
	private QueryExecutor queryExecutor;

	public QueryController() {
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(QuerySize.class, new QuerySizeEditor());
	}

	/**
	 * Populate the query page with a list of models to use, and filetypes
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(ModelMap modelMap) {
		logger.info("Query page");

        QueryDataBinding dataBinding = new QueryDataBinding();
		modelMap.addAttribute("features", dataBinding.getFeatures());
		modelMap.addAttribute("fromYears", dataBinding.getFromYears());
		modelMap.addAttribute("lexicals", dataBinding.getLexicals());
		modelMap.addAttribute("models", dataBinding.getModels());
		modelMap.addAttribute("sizes", dataBinding.getSizes());
		modelMap.addAttribute("toYears", dataBinding.getToYears());
		modelMap.addAttribute("types", dataBinding.getTypes());
		modelMap.addAttribute("queryOptions", new QueryOptions());
		return "query";
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String update(@ModelAttribute("queryOptions") QueryOptions queryOptions, BindingResult result, SessionStatus status,
			ModelMap modelMap) {
		logger.info("Received query request");
		if ("Home".equals(queryOptions.getAction())) {
			return "redirect:/";
		}

		QueryResults queryResult = queryExecutor.executeQuery(queryOptions);
		modelMap.put("query", queryResult.getQueryString());
		modelMap.put("results", queryResult.getResults());
		return "results";
	}

}
