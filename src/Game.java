
import java.util.Scanner;

class Game {
	public static Scanner inputReader = new Scanner(System.in);
	
	public static void main (String[] args)  {
		Player user = new Player();
		Player computer = new Player();
		String answer = "";
		
		System.out.println("Start of the Challenge");
		System.out.println("Player Setup: ");
		
		
		Display.printPlayerGrid(user.playerGrid, 1);

		Setup.setupPlayer(user);
		Setup.setupComp(computer);
		
		Display.clearScreen();
		while (true) {
			System.out.println("Here is Your Grid. Would you like to rearrange grid or proceed?");
			Display.printPlayerGrid(user.playerGrid, 1);
			System.out.print("Type YES to proceed. RESET to rearrange grid: ");
			while (true) {
				answer = inputReader.next().toUpperCase();
				if (answer.equals("YES") || answer.equals("RESET")) break;
				System.out.println("Please type a Valid response");
			}
			if (answer.equals("RESET")) {
				Setup.setupPlayer(user);
				continue;
			}
			else if (answer.equals("YES")) {
				break;
			}
		}
		
		Display.clearScreen();
		
		while (true) {
			String result = "";
			System.out.println("User make a guess");
			Display.printInGameGrid(user.playerGrid, computer.playerGrid);
			result = askForUserGuess(user, computer);
			Display.clearScreen();
			System.out.println(result);
			System.out.println("Now COMP will guess");
			result = askForCompGuess(computer, user);
			System.out.println(result);
			
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
		//Display.clearScreen();
		String response = "No guesses yet";
		int guessedRow = -1;
		int guessedColumn = -1;
		
		String literalRow = "X";
		
		while (true) {
			System.out.print("Choose a Row (A-J): ");
			
			literalRow = inputReader.next().toUpperCase();
			guessedRow = Converter.converLetterToInt(literalRow);
			System.out.println();
			
			System.out.print("Choose a Column (1-10): ");
			guessedColumn = inputReader.nextInt()-1;
			
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
			response = "You hit a ship at Row: " + literalRow + ". Column: " + (guessedColumn+1);
		}
		else {
			comp.playerGrid.markMiss(guessedRow, guessedColumn);
			user.opponentGrid.markMiss(guessedRow, guessedColumn);
			response = "You missed a ship at Row: " + literalRow + ". Column: " + (guessedColumn+1);
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
		if (user.playerGrid.hasShip(row, column)) {
			comp.opponentGrid.markHit(row, column);
			user.playerGrid.markHit(row, column);
			response = "The COMP hit at Row: " + Converter.converIntToLetter(row) + "; Column: " + (column+1);
			System.out.println("RESPONSE: "+response);
			return response;
		}
		else {
			user.playerGrid.markMiss(row, column);
			comp.opponentGrid.markMiss(row, column);
			response = "The COMP missed at Row: " + Converter.converIntToLetter(row) + "; Column: " + (column+1);
			System.out.println("RESPONSE: "+response);
			return response;
		}
	}
	
}
