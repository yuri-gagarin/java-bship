
public class Converter {
	public static int converLetterToInt(String letter) {
		char userRowChar = letter.charAt(0);
		int row = ((int)userRowChar - 65);
		return row;
	}
	public static int convertDirection(String direction) {
		int result = -1;
		if (direction.equals("H")) {
			result = 0;
		}
		else if (direction.equals("V")) {
			result = 1;
		}
		return result;
	}
} 
