package eu.hecnet.apps.query;

public class QueryFeedback {

	public enum QueryStatus {
		FILE, DIRECTORY, PERSISTING, COMPLETE
	}

	private QueryStatus status;

	public QueryFeedback(QueryStatus status) {
		setStatus(status);
	}

	public QueryStatus getStatus() {
		return status;
	}

	public void setStatus(QueryStatus status) {
		this.status = status;
	}
}
