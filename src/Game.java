
import java.util.Scanner;

class Game {
	public static Scanner inputReader = new Scanner(System.in);
	
	public static void main (String[] args)  {
		System.out.println("Start of the Challenge");
		System.out.println("\nPlayer Setup: ");
		Player user = new Player();
		Player computer = new Player();
		
		Display.printPlayerGrid(user.playerGrid, 1);

		Setup.setupPlayer(user);
		Setup.setupComp(computer);
		
		String result = "";
		
		while (true) {
			System.out.println(result);
			System.out.println("User make a guess");
			Display.printInGameGrid(user.playerGrid, computer.playerGrid);
			result = askForUserGuess(user, computer);
			System.out.println(result);
			System.out.println("Now COMP will guess");
			result = askForCompGuess(computer, user);
			if (user.playerGrid.hasLost()) {
				System.out.println("You have lost");
				break;
			}
			else if (computer.playerGrid.hasLost()) {
				System.out.println("COMP has lost");
				break;
			}
		}
		System.out.println("Goodbye");
		System.exit(0);
		
	}
	
	
	
	private static String askForUserGuess(Player user, Player comp) {

		String response = "No guesses yet";
		int guessedRow = -1;
		int guessedColumn = -1;
		
		String literalRow = "X";
		int literalCol = -1;
		
		
		System.out.println("Here is the opponents Board: /t /t/ /t  Here is your Grid:");
		
		
		while (true) {
			System.out.print("Choose a Row (A-J): ");
			
			literalRow = inputReader.next().toUpperCase();
			guessedRow = Converter.converLetterToInt(literalRow);
			System.out.println();
			
			System.out.print("Choose a Column (1-10): ");
			guessedColumn = inputReader.nextInt() - 1;
			literalCol = guessedColumn;
			
			if (guessedRow > -1 && guessedRow < 10 && guessedColumn > -1 && guessedColumn < 10) {
				if (comp.playerGrid.isGuessed(guessedRow, guessedColumn)) {
					System.out.println("YOU ALEREADY GUESSED THIS LOCATION");
					continue;
				}
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
	private static String askForCompGuess(Player comp, Player user) {
		String response = "";
		int row = -1;
		int column = -1;
		Randomizer random = new Randomizer();
		while (true) {
			row = random.nextInt(0, 9);
			column = random.nextInt(0, 9);
			if (row > -1 && row < 10 && column > 0 && column < 10) break;
		}
		if (comp.opponentGrid.hasShip(row, column)) {
			comp.opponentGrid.markHit(row, column);
			user.playerGrid.markHit(row, column);
			response = "The COMP hit at Row: " + Converter.converIntToLetter(row) + "; Column: " + (column+1);
			return response;
		}
		else {
			comp.opponentGrid.markMiss(row, column);
			user.playerGrid.markMiss(row, column);
			response = "The COMP missed at Row: " + Converter.converIntToLetter(row) + "; Column: " + (column+1);
			return response;
		}
	}
	
}
