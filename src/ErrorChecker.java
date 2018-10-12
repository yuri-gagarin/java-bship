
public class ErrorChecker {
	public static boolean checkForCompErrors(int row, int column, int direction, Player player, Ship ship) {
		int length = ship.getLength();
		//check off grid horizontally
		if (direction == 0) {
			if ((column+length) > 10) return true;
		}
		//check off grid vertically
		if (direction == 1) {
			if ((row+length) > 10) return true;
		}
		//check for horizontal overlap
		if (direction == 0) {
			for (int i = column; i < column+length; i++) {
				if (player.playerGrid.hasShip(row, i)) return true;
			}
		}
		//check for vertical overlap
		if (direction == 1) {
			for (int i = row; i < row+length; i++) {
				if (player.playerGrid.hasShip(i, column)) return true;
			}		
		}
		
		return false;
	}
	public static boolean checkPlayerForErrors(int row, int col, int direction, Player player, Ship ship) {
		int length = ship.getLength();
		
		//Check if off the grid horizontally
		if (direction == 0) {
			if((col + length) > 10) {
				System.out.println("DOES NOT FIT HORIZONTALLY");
				return true;
			}
		}
		//Check if off the grid vertically
		if (direction == 1) {
			if((row+length) > 10) {
				System.out.println("DOES NOT FIR VERTICALY");
				return true;
			}
		}
		//Check for an overlap horizontally
		if (direction == 0) {
			for (int i = col; i < col+length; i++) {
				if (player.playerGrid.hasShip(row, i)) {
					System.out.println("SPACE ALREADY OCCUPIED");
					return true;
				}
			}
		}
		//Check for an overlap vertically
		if (direction == 1) {
			for (int i = row; i < row+length; i++) {
				if (player.playerGrid.hasShip(i, col)) {
					System.out.println("SPACE ALREADY OCCUPIED");
					return true;
				}
			}
		}
		return false;
		
	}
}
