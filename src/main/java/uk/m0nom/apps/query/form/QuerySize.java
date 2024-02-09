package uk.m0nom.apps.query.form;

public class QuerySize {
	private String name;
	private Integer size;

	public QuerySize(String name, int size) {
		setName(name);
		setSize(size);
	}

	public QuerySize(String text) {
		setName(text);
		int len = Integer.parseInt(text.substring(0, text.length() - 1));
		if (text.endsWith("B")) {
			setSize(len);
		}
		if (text.endsWith("K")) {
			setSize(len * 1000);
		}
		if (text.endsWith("M")) {
			setSize(len * 1000000);
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof QuerySize) {
			QuerySize otherQuerySize = (QuerySize) other;
			if (otherQuerySize.getSize() != null) {
				return size.equals(otherQuerySize.getSize());
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
