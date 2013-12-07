package code;

public class Utils {

	public static int compare (String one, String two) {
		//if string is a number, convert to number
		if (one.matches("\\d+") && two.matches("\\d+")) {
			int uno = Integer.parseInt(one);
			int dos = Integer.parseInt(two);
			if (uno > dos) {
				return 1;
			}
			if (uno < dos) {
				return -1;
			}
			return 0;
		}
		if (one.equals("inf")) {
			return 1;
		}
		if (two.equals("inf")) {
			return -1;
		}
		if (one.equals("inf") && two.equals("inf")){
			return 0;
		}
		//else compares strings and as lower case
		return one.compareToIgnoreCase(two);	
	}
}
