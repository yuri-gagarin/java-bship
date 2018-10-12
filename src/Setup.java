import java.util.Scanner;

public class Setup {
	public static Scanner inputReader = new Scanner(System.in);
	
	//setup method for a human player
	public static void setupPlayer(Player player) {
		String setupMode;
		
		Display.clearScreen();
		//give an option for a grid setup
		while (true) {
			System.out.println();
			for (int i = 0; i < 50; i++) {
				System.out.print("*");
			}
			System.out.println();

			System.out.print("Would you like to auto setup your grid? YES or NO: ");
			setupMode = inputReader.next().toUpperCase();
			
			if (setupMode.equals("YES") || setupMode.equals("NO")) {
				break;
			}
			System.out.println("Invalid Input");
		}
		//run randomized auto setup of ships
		if (setupMode.equals("YES")) {
			autoSetup(player);
		}
		
		//if not auto setup, then manually setup the player grid
		else {
			int shipNumber = 1;
			//run while loop as long as there are ships to set up
			while (player.numOfShipsLeft() > 0) {
				//iterate through each ship and attempt to set each ship on user board
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
						//check for correct input from the user, direction and out of bounds
						if (column >= 0 && column <= 9 && row != -1 && shipDirection != -1) {
							if (!ErrorChecker.checkPlayerForErrors(row, column, shipDirection, player, ship)) break;
						}
						System.out.println("Invalid Location for a ship");
					}
					System.out.println("Setting ship location for Ship #"+ shipNumber);
					
					//set the ship
					ship.setLocation(row, column);
					ship.setDirection(shipDirection);
					player.playerGrid.addShip(ship);
					
					//print the updated grid with the ship
					Display.printPlayerGrid(player.playerGrid, 1);
					System.out.println();
					System.out.println("You have " + player.numOfShipsLeft() + " ships to place!");
					
					shipNumber++;					
				}	
			}
		}
		
	}
	//setup method for COMP or PLAYER
	public static void autoSetup(Player player) {
		//make sure unset ships location from previous run
		for (Ship ship : player.ships) {
			ship.setLocation(-1, -1);
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				player.playerGrid.grid[i][j].setShip(false);
			}
		}
		
		Randomizer random = new Randomizer();
		//run while there are ships to set
		while (player.numOfShipsLeft() > 0) {
			for (Ship ship : player.ships) {
				//grab a random int for row, column and direction
				int row = random.nextInt(0, 9);
				int column = random.nextInt(0, 9);
				int direction = random.nextInt(0, 1);
				//check for errors and if there are errors keep randomizing
				while (ErrorChecker.checkForCompErrors(row, column, direction, player, ship) ) {
					row = random.nextInt(0, 9);
					column = random.nextInt(0, 9);
					direction = random.nextInt(0, 1);
				}
				//set the ship on a grid
				ship.setLocation(row, column);
				ship.setDirection(direction);
				player.playerGrid.addShip(ship);
			}
		}
		System.out.println("Auto Setup Complete");
	}
}
