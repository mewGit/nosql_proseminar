package benchmark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.xquery.XQException;

import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.server.ClientSession;


import connection.XBaseConnection;

public class Benchmark {
	
	static ClientSession session;
	
	public static void resetTestDB() throws IOException {
		session = new ClientSession("localhost", 1984, "admin", "admin");
		session.execute(new DropDB("test"));
		session.execute(new CreateDB("test", Benchmark.class.getResource("../xml/testRatings.xml").toString()));
		session.close();
	}
	
	public static void main(String[] args) {
		// variables
		int max = 10;
		int runs = 100000/max;
		long timeValueNano = 0;
		long timeTotalAverage = 0;
		String queryStart = "insert node (";
		String queryEnd = ") as last into /ratings";
		String queryBuffer = "";
		
		// connect to db
		try {
		// reset Test database
		resetTestDB();
		System.out.println("test database reseted");
		XBaseConnection connection = new XBaseConnection();
		connection.dbConnect("localhost", "1984", "admin", "admin", "Test");
		
		// open file
		String name = "ratings.dat";
		InputStreamReader in = new InputStreamReader(Benchmark.class.getResourceAsStream("../" + name));
		BufferedReader reader = new BufferedReader(in);
		String line = null;

		System.out.println("starting benchmark ... ");
		for (int i = 0; i < runs; i++) {
			// build query
			queryBuffer = "";
			int count = 0;
			while (count <= max) {
				count++;
				line = reader.readLine();
				String[] data = line.split("::", 4);
				String query = 
						"<rating>" +
							"<movieID>"+ data[0] +"</movieID>" +
							"<userID>"+ data[1] +"</userID>" +
							"<userRating>"+ data[2] +"</userRating>" +
							"<time>"+ data[3] +"</time>" +
						"</rating>";
				queryBuffer += query + ',';
			}
			queryBuffer = queryBuffer.substring(0, queryBuffer.length() - 1);

			// startTimer
			Timer timer = new Timer();
			timer.start();			
			
			// execute query
			connection.execQuery(queryStart + queryBuffer + queryEnd);

			// stopTimer
			timeValueNano = timer.stop();
			//System.out.printf("[RUN #%d] average insertion time for one node in ms: %f %n" ,i ,timeValueNano/(100000*1000000.0));
			timeTotalAverage += timeValueNano;
		}
		System.out.println("###########################################################");
		System.out.printf("[TOTAL] average insertion time for one node in ms: %f %n" ,timeTotalAverage/(runs*100000*1000000.0));
		// close connection
		connection.dbDisconnect();
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
