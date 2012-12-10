package parser;

public interface Database {
	
	public String getQueryStart();
	public String getQueryEnd();
	
	public void fill(String line);
	public String buildXQuery();
	public String toString();
}
