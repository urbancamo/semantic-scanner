package eu.hecnet.apps.query;

public class QueryCommand {
	public enum QueryCommandType {
		LOAD_MODEL, LOAD_QUERY, EXECUTE_QUERY
	}

	private QueryCommandType command;

	public QueryCommand() {
	}

	public QueryCommand(QueryCommandType command) {
		setCommand(command);
	}

	public QueryCommandType getCommand() {
		return command;
	}

	public void setCommand(QueryCommandType command) {
		this.command = command;
	}
}
