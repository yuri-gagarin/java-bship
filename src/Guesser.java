import java.util.Scanner;

public class Guesser {
	public static Scanner inputReader = new Scanner(System.in);
			
	public static String askForUserGuess(Player user, Player comp) {
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
	public static String askForCompGuess(Player comp, Player user) {
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
