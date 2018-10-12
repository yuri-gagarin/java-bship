import java.util.Scanner;

public class Setup {
	public static Scanner inputReader = new Scanner(System.in);
	public static void setupPlayer(Player player) {
		String setupMode;
		//player.playerGrid.printShips();
		System.out.println();
		
		
		while (true) {
			System.out.print("Would you like to auto setup your grid? YES or NO: ");
			setupMode = inputReader.next().toUpperCase();
			
			if (setupMode.equals("YES") || setupMode.equals("NO")) {
				break;
			}
			System.out.println("Invalid Input");
		}
		
		if (setupMode.equals("YES")) {
			autoSetup(player);
		}
		else {
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
							if (!ErrorChecker.checkPlayerForErrors(row, column, shipDirection, player, shipIterator)) break;
						}
						System.out.println("Invalid Location for a ship");
					}
					System.out.println("Setting ship location for Ship #"+ shipNumber);
					
					player.ships[shipIterator].setLocation(row, column);
					player.ships[shipIterator].setDirection(shipDirection);
					player.playerGrid.addShip(player.ships[shipIterator]);
					Display.printPlayerGrid(player.playerGrid, 1);
					System.out.println();
					System.out.println("You have " + player.numOfShipsLeft() + " ships to place!");
					
					shipNumber++;
					shipIterator++;
					
				}	
			}
		}
		
	}
	
	public static void setupComp(Player comp) {
		
		System.out.println();
		int shipIterator = 0;
		
		Randomizer random = new Randomizer();
		
		while (comp.numOfShipsLeft() > 0) {
			for (Ship ship : comp.ships) {
				int row = random.nextInt(0, 9);
				int column = random.nextInt(0, 9);
				int direction = random.nextInt(0, 1);
				
				while (ErrorChecker.checkForCompErrors(row, column, direction, comp, shipIterator) ) {
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
	}
	
	private static void autoSetup(Player player) {
		int shipIterator = 0;
		
		Randomizer random  = new Randomizer();
		while (player.numOfShipsLeft() > 0) {
			for (Ship ship : player.ships) {
				int row = random.nextInt(0, 9);
				int column = random.nextInt(0, 9);
				int direction  = random.nextInt(0, 1);
				
				while (ErrorChecker.checkForCompErrors(row, column, direction, player, shipIterator)) {
					row = random.nextInt(0, 9);
					column = random.nextInt(0, 9);
					direction = random.nextInt(0, 1);
				}
				
				ship.setLocation(row, column);
				ship.setDirection(direction);
				player.playerGrid.addShip(ship);
				shipIterator++;
			}
		}
		System.out.println("Auto Setup Complete");
	}
}
