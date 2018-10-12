
import java.util.Scanner;

class Game {
	public static Scanner inputReader = new Scanner(System.in);
	
	public static void main (String[] args)  {
		Player user = new Player();
		Player computer = new Player();
		//main game loop
		while (true) {			
			
			String answer = "";
			
			System.out.println("Start of the Challenge");
			System.out.println("Player Setup: ");
			
			//setup player and COMP
			Setup.setupPlayer(user);
			Setup.autoSetup(computer);
			
			Display.clearScreen();
			//inner loop when the game is set up; allows player to rearrange ships
			while (true) {
				System.out.println("Here is Your Grid. Would you like to rearrange grid or proceed?");
				Display.printPlayerGrid(user.playerGrid, 1);
				System.out.print("Type PROCEED to proceed. RESET to rearrange grid: ");
				while (true) {
					answer = inputReader.next().toUpperCase();
					if (answer.equals("PROCEED") || answer.equals("RESET")) break;
					System.out.println("Please type a Valid response");
				}
				if (answer.equals("RESET")) {
					Setup.setupPlayer(user);
					continue;
				}
				else if (answer.equals("PROCEED")) {
					break;
				}
			}
			
			Display.clearScreen();
			//versus loop; keeps going until someone loses all ships
			while (true) {
				String result = "";
				//scoreboard for player and COMP
				Display.printInfoHeader(user, computer);
				Display.printInGameGrid(user.playerGrid, computer.playerGrid);
				//user guess and check if user won
				result = Guesser.askForUserGuess(user, computer);
				Display.clearScreen();
				System.out.println(result);
				if (computer.playerGrid.hasLost()) {
					System.out.println("COMP has lost");
					break;
				}
				System.out.println("Now COMP will guess");
				//COMP guess and check if comp won
				result = Guesser.askForCompGuess(computer, user);
				System.out.println(result);
				if (user.playerGrid.hasLost()) {
					System.out.println("You have lost");
					break;
				}
	
			}
			
			//continue main loop if user wants to play again
			System.out.print("Would you like to play again? YES or NO: ");
			answer = inputReader.next().toUpperCase();
			if (answer.equals("YES")) {
				continue;
			}
			else if (answer.equals("NO")) {
				break;
			}
		}
		System.out.println("Goodbye");
		System.exit(0);	
	//end of main game loop
	}
	
}
