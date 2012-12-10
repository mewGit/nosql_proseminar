package parser;

public class Ratings implements Database {

	private int movieID;
	private int userID;
	private float score;
	private long timeStamp;
	
	
	public Ratings(String line) {
		fill(line);
	}
	
	@Override
	public void fill(String line) {
		String[] data = line.split("::", 4);
		if (data.length == 4) {
			movieID = Integer.parseInt(data[0]);
			userID = Integer.parseInt(data[1]);
			score = Float.parseFloat(data[2]);
			timeStamp = Long.parseLong(data[3]);
		} else {
			System.out.println("Error :: not enough arguments");
		}
	}

	@Override
	public String buildXQuery() {
		String query = 
				"<rating>" +
					"<movieID>"+ movieID +"</movieID>" +
					"<userID>"+ userID +"</userID>" +
					"<score>"+ score +"</score>" +
					"<timeStamp>"+ timeStamp +"</timeStamp>" +
				"</rating>";
		return query;
	}

	@Override
	public String toString() {
		return "MovieID::"+movieID+
				" UserID::"+userID+
				" Score::"+score+
				" Time::"+timeStamp;
	}

	@Override
	public String getQueryStart() {
		return "insert node (";
	}

	@Override
	public String getQueryEnd() {
		return ") as last into /ratings";
	}
}