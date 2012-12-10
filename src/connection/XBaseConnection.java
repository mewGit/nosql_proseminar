package connection;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.basex.BaseXXQDataSource;

public class XBaseConnection {

	private XQDataSource xqs = null;
	private XQConnection conn = null;
	
	public void dbConnect(String server, String port, 
			String user, String password, String database) throws XQException {
		xqs = new BaseXXQDataSource();
		xqs.setProperty("serverName", server);
		xqs.setProperty("port", port);
		xqs.setProperty("user", user);
		xqs.setProperty("password", password);
		xqs.setProperty("databaseName", database);
		conn = xqs.getConnection();
		System.out.println("open connection to " + database);
	}
	
	public void dbDisconnect() throws XQException {
		conn.close();
		System.out.println("connection closed");
	}
	
	public XQResultSequence execQuery(String query) throws XQException {
		XQResultSequence rs = null;
		if (conn != null) {
			//System.out.println("executing query ...");
			XQPreparedExpression xqpe = conn.prepareExpression(query);
			rs = xqpe.executeQuery();
		} else {
			System.out.println("Warning! Not connected, please use dbConnect first. Result is null.");
		}
		return rs;
	}
	
}
