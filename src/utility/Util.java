package utility;

public class Util {
	/**
	 * Helper method that returns the string with only its first letter capitalized
	 * @requires s.length() > 0
	 * @param s the input string
	 * @return the string with its first letter capitalized
	 */
	public static String capitalize(String s) {
		s = s.toLowerCase();
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
