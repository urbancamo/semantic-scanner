package eu.hecnet.apps.query.form;

import java.beans.PropertyEditorSupport;

public class QuerySizeEditor extends PropertyEditorSupport {
	public QuerySizeEditor() {
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(new QuerySize(text));
	}

	@Override
	public String getAsText() {
		QuerySize s = (QuerySize) getValue();
		if (s == null) {
			return null;
		} else {
			return s.getName();
		}
	}
}
