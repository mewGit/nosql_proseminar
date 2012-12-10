package insert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;

import net.xqj.basex.BaseXXQDataSource;

import parser.Ratings;

public class DoInsert {

	/**
	 * @param args
	 */
	
	private static XQDataSource xqs;
	private static XQConnection conn;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start("ratings.dat", "imdb-ratings");
	}

	private static void start(String dbFile, String dbName) {
		ConcurrentLinkedQueue<Ratings> ratingQueue = new ConcurrentLinkedQueue<>();
		InputStreamReader in = new InputStreamReader(DoInsert.class.getResourceAsStream("../" + dbFile));
		try {
			BufferedReader reader = new BufferedReader(in);
			String line = null;
			System.out.println("Connecting to database...");
			dbConnect(dbName);
			System.out.println("Parsing and inserting data...");
			XQPreparedExpression xqpe;
			while ((line = reader.readLine()) != null) {
				ratingQueue.add(new Ratings(line));
			}
			System.out.println("Starting insert into database...");
			for (Ratings rating : ratingQueue) {
				xqpe = conn.prepareExpression(rating.getQueryStart() + rating.buildXQuery() + rating.getQueryEnd());
				xqpe.executeQuery();
			}
			System.out.println("Done. Close connection and file...");
			reader.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void dbConnect(String dbName) throws XQException {
		xqs = new BaseXXQDataSource();
		xqs.setProperty("serverName", "localhost");
		xqs.setProperty("port", "1984");
		xqs.setProperty("user", "admin");
		xqs.setProperty("password", "admin");
		xqs.setProperty("databaseName", dbName);
		conn = xqs.getConnection();
	}
}
