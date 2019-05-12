package app.core;

public interface Reporting {
	
	public void writeLog(String line);
	
	public void writeError(String line);
	
	public void clearLogs();

}
