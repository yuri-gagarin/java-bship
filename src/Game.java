import java.io.Reader;
import java.util.Random;
import java.util.Scanner;

class Game {
	public static Scanner inputReader = new Scanner(System.in);
	
	public static void main (String[] args)  {
		System.out.println("Start of the Challenge");
		System.out.println("\nPlayer Setup: ");
		
		Player user = new Player();
		Player computer = new Player();
		setup(user);
		setupComputer(computer);
		
		String result = "";
		
		while (true) {
			System.out.println(result);
			System.out.println("User make a guess");
			
			result = askForUserGuess(user, computer);
		}
		
	}
	
	private static void setup(Player player) {
		player.playerGrid.printShips();
		System.out.println();
		int shipNumber = 1;
		int shipIterator = 0;
		while (player.numOfShipsLeft() > 0) {
			for (Ship ship : player.ships) {
				System.out.println("\nShip #"+ shipNumber + ": Length: " + ship.getLength());
				int row = -1;
				int column = -1;
				int shipDirection = -1;
				while (true) {
					System.out.print("Type in Row for ship(A - J): ");
					String userRowSelection = inputReader.next().toUpperCase();
					row = Converter.converLetterToInt(userRowSelection);
					
					System.out.print("Type in a Column for ship(1-10): ");
					column = inputReader.nextInt() - 1;
			
					System.out.print("Type in a Direction: H = Horizontal V = Vertical: ");
					String direction = inputReader.next().toUpperCase();
					shipDirection = Converter.convertDirection(direction);
					
					if (column >= 0 && column <= 9 && row != -1 && shipDirection != -1) {
						if (!checkForErrors(row, column, shipDirection, player, shipIterator)) break;
					}
					System.out.println("Invalid Location for a ship");
				}
				System.out.println("Setting ship location for Ship #"+ shipNumber);
				
				player.ships[shipIterator].setLocation(row, column);
				player.ships[shipIterator].setDirection(shipDirection);
				player.playerGrid.addShip(player.ships[shipIterator]);
				player.playerGrid.printShips();
				System.out.println();
				System.out.println("You have " + player.numOfShipsLeft() + " ships to place!");
				
				shipNumber++;
				shipIterator++;
				
			}	
		}
	}
	private static void setupComputer(Player comp) {
		System.out.println();
		int shipNumber = 1;
		int shipIterator = 0;
		
		Randomizer random = new Randomizer();
		
		while (comp.numOfShipsLeft() > 0) {
			for (Ship ship : comp.ships) {
				int row = random.nextInt(0, 9);
				int column = random.nextInt(0, 9);
				int direction = random.nextInt(0, 1);
				
				while (checkForCompErrors(row, column, direction, comp, shipIterator) ) {
					row = random.nextInt(0, 9);
					column = random.nextInt(0, 9);
					direction = random.nextInt(0, 1);
				}
				
				ship.setLocation(row, column);
				ship.setDirection(direction);
				comp.playerGrid.addShip(ship);
				shipIterator++;
			}
			
		}
		comp.playerGrid.printShips();
		
	}
	private static String askForUserGuess(Player user, Player comp) {
		String response = "No guesses yet";
		int guessedRow = -1;
		int guessedColumn = -1;
		
		String literalRow = "X";
		int literalCol = -1;
		
		
		System.out.println("Here is the opponents Board:");
		comp.playerGrid.printStatus();
		
		while (true) {
			System.out.print("Choose a Row (A-J): ");
			
			literalRow = inputReader.next().toUpperCase();
			guessedRow = Converter.converLetterToInt(literalRow);
			System.out.println();
			
			System.out.print("Choose a Column (1-10): ");
			guessedColumn = inputReader.nextInt() - 1;
			literalCol = guessedColumn;
			
			if (guessedRow > -1 && guessedRow < 10 && guessedColumn > -1 && guessedColumn < 10) {
				break;
			}
			System.out.println("Invalid Location on the Grid");
		}
		
		if (comp.playerGrid.hasShip(guessedRow, guessedColumn)) {
			comp.playerGrid.markHit(guessedRow, guessedColumn);
			user.opponentGrid.markHit(guessedRow, guessedColumn);
			response = "You hit a ship at Row: " + literalRow + ". Column: " + literalCol;
		}
		else {
			comp.playerGrid.markMiss(guessedRow, guessedColumn);
			user.opponentGrid.markMiss(guessedRow, guessedColumn);
			response = "You missed a ship at Row: " + literalRow + ". Column: " + literalCol;
		}
		
		return response;
		
	}
	private static boolean checkForErrors(int row, int col, int direction, Player player, int count) {
		int length = player.ships[count].getLength();
		
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
	private static boolean checkForCompErrors(int row, int column, int direction, Player player, int count) {
		int length = player.ships[count].getLength();
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
	
}
