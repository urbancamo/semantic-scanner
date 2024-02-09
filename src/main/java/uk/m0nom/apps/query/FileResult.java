package uk.m0nom.apps.query;

public class FileResult {

	private String uri;
	private String lexical;

	public FileResult(String uri, String lexical) {
		setUri(uri);
		setLexical(lexical);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getLexical() {
		return lexical;
	}

	public void setLexical(String lexical) {
		this.lexical = lexical;
	}
}
