package utils;

public class LibraryLog {

	private String dateString;
	private String stack;
	private String logMessage;

	LibraryLog(String dateString, String stack, String logMessage) {
		this.dateString = dateString;
		this.stack = stack;
		this.logMessage = logMessage;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getTitle() {
		return stack;
	}

	public void setTitle(String title) {
		this.stack = title;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
}