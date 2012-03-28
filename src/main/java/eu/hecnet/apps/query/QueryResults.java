package eu.hecnet.apps.query;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import eu.hecnet.apps.query.form.QueryOptions;

public class QueryResults {

	private QueryOptions queryOptions;
	private String queryString;

	private Map<String, FileResult> results;

	public QueryResults() {
		results = new HashMap<String, FileResult>();
	}

	public QueryResults(String queryString, QueryOptions queryOptions) {
		this();
		setQueryString(queryString);
		setQueryOptions(queryOptions);
	}

	public void addResult(String fileUri, String lexical) {
		results.put(fileUri, new FileResult(fileUri, lexical));
	}

	public QueryOptions getQueryOptions() {
		return queryOptions;
	}

	public void setQueryOptions(QueryOptions queryOptions) {
		this.queryOptions = queryOptions;
	}

	public Collection<FileResult> getResults() {
		return results.values();
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
}
