package uk.m0nom.apps.query.form;

public class QueryOptions {

	private String model;
	private String fromYear;
	private String toYear;
	private QuerySize size;
	private String feature;
	private String type;
	private String lexical;

	private String searchText;
	private String action;

	public QueryOptions() {
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public QuerySize getSize() {
		return size;
	}

	public void setSize(QuerySize size) {
		this.size = size;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLexical() {
		return lexical;
	}

	public void setLexical(String lexical) {
		this.lexical = lexical;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
