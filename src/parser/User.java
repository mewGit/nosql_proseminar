package parser;


public class User implements Database {
	
	// UserID::Gender::Age::Occupation::Zip-code
	private int userID;
	private int gender;
	private int age;
	private int occupation;
	private String zipCode;
	
	public void fill(String line) {
		String[] data = line.split("::", 5);
		if (data.length == 5) {
			userID = Integer.parseInt(data[0]);
			//gender = (data[1].compareTo("M") == 0)? "Male" : "Female";
			gender = Integer.parseInt(data[1]);
			age = Integer.parseInt(data[2]);
			//occupation = Occupation[Integer.parseInt(data[3])];
			occupation = Integer.parseInt(data[3]);
			zipCode = data[4];
		} else {
			System.out.println("Error :: not enough arguments");
		}
	}
	
	public String buildXQuery() {
		String query = 
				"<user>" +
					"<userID>"+ userID +"</userID>" +
					"<gender>'"+ gender +"'</gender>" +
					"<age>"+ age +"</age>" +
					"<occupation>'"+ occupation +"'</occupation>" +
					"<zipCode>'"+ zipCode +"'</zipCode>" +
				"</user>";
		return query;
	}
	
	// for debug
	public String toString() {
		return "UserID::"+userID+
				" Gender::"+gender+
				" Age::"+age+
				" Occupation::"+occupation+
				" Zip-code::"+zipCode;
	}

	@Override
	public String getQueryStart() {
		return "insert node (";
	}

	@Override
	public String getQueryEnd() {
		return ") as last into /users";
	}
}