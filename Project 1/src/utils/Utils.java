package utils;

/**Includes static methods*/
public class Utils<Type> {
	
	public static final int GREATER = 1;
	public static final int LESSER = 2;
	public static final int EQUAL = 3;
	
	/* Helper method that compares strings*/
	public static int compareValue(String value, String nodeValue) {
		System.out.println("COMPARE VALUE");
		//assume that list is of String type
		int compare = (value.compareToIgnoreCase(nodeValue));
		if (compare > 0) {
			System.out.println("GREATER");
			return GREATER;
		}
		else if (compare == 0) {
			System.out.println("EQUAL");
			return EQUAL;
		}
		System.out.println("LESSER");
		return LESSER;
	}
}
