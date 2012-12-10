package insert;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


final class Helper {

	static final String[] USER_OCCUPATION = {
			"other",
			"academic/educator",
			"artist",
			"clerical/admin",
			"college/grad student",
			"customer service",
			"doctor/health care",
			"executive/managerial",
			"farmer",
			"homemaker",
			"K-12 student",
			"lawyer",
			"programmer",
			"retired",
			"sales/marketing",
			"scientist",
			"self-employed",
			"technician/engineer",
			"tradesman/craftsman",
			"unemployed",
			"writer"
		};
	
	static final String[] USER_GENDER = {
		"Male",
		"Female"
	};
	
	static final Map<Integer, String> USER_AGE_RANGE = Collections.unmodifiableMap(
			new HashMap<Integer, String>() {
				private static final long serialVersionUID = -1654741795452548329L;
				{ 
			        put(1, "Under 18");
			        put(18, "18-24");
			        put(25, "25-34");
			        put(35, "35-44");
			        put(45, "45-49");
			        put(50, "50-55");
			        put(56, "56+");
			    }
			});
}






