
public class Display {
	private static final int NUM_OF_COLUMNS = 10;
	private static final int NUM_OF_ROWS = 10;
	
	//0: For Status of Board; 1: For Print Ships; 2: For whole details;
	public static void printPlayerGrid(Grid player, int type) {
		System.out.println("Here is the Player Grid: ");
		System.out.print("\t");
		for (int i = 1; i <= NUM_OF_COLUMNS; i++) {
			System.out.print(" "+i+"\t");
			
		}
		System.out.println();
		System.out.println();
		
		int endLetterForLoop = ((NUM_OF_ROWS - 1) + 65); 
		for (int i = 65; i <= endLetterForLoop; i++) {
			char rowCharacter = (char)i;
			System.out.print(rowCharacter+"\t");
			
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				switch(type) {
				case 0: if (player.grid[switchCounterToInt(i)][j].checkUnguessed()) {
							System.out.print("[~]\t");	
						}
						else if (player.grid[switchCounterToInt(i)][j].checkMiss()) {
							System.out.print("[0]\t");
						}
						else if (player.grid[switchCounterToInt(i)][j].checkHit()) {
							System.out.print("[X]\t");
						}
						break;
				case 1: if (player.grid[switchCounterToInt(i)][j].hasShip()) {
							if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 2) {
								System.out.print("[D]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 3) {
								System.out.print("[C]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 4) {
								System.out.print("[B]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 5) {
								System.out.print("[A]\t");
							}		
						}
						else if (!(player.grid[switchCounterToInt(i)][j].hasShip())) {
							System.out.print("[-]\t");
						}
						break;
				case 2: if (player.grid[switchCounterToInt(i)][j].checkHit()) {
							System.out.print("[X]\t");
						}
						else if (player.grid[switchCounterToInt(i)][j].hasShip()) {
							if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 2) {
								System.out.print("[D]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 3) {
								System.out.print("[C]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 4) {
								System.out.print("[B]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 5) {
								System.out.print("[A]\t");
							}
						}
						else if (!(player.grid[switchCounterToInt(i)][j].hasShip())) {
							System.out.print("[-]\t");
						}
						break;			
				}				
			}
			
			System.out.println();
			System.out.println();
		}
	}
	
	public static void printInGameGrid(Grid player, Grid opponent) {
		System.out.print("\t");
		for (int i = 1; i <= NUM_OF_COLUMNS; i++) {
			System.out.print(" "+i+"\t");
			
		}
		System.out.print("\t\t\t");
		for (int j = 1; j <= NUM_OF_COLUMNS; j++) {
			System.out.print(" "+j+"\t");
		}
		System.out.println();
		System.out.println();
		
		int endLetterForLoop = ((NUM_OF_ROWS-1)+65);
		for (int i = 65; i <= endLetterForLoop; i++) {
			char rowCharacter = (char)i;
			System.out.print(rowCharacter + "\t");
			
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				int row = switchCounterToInt(i);
				if (player.grid[row][j].checkHit()) {
					System.out.print("[X]\t");
				}
				else if(!player.grid[row][j].checkHit()) {
					
					if (player.grid[row][j].hasShip()) {
						int shipLength = player.grid[row][j].getLengthOfShip();
						if (shipLength == 2) {
							System.out.print("[C]\t");
						}
						else if (shipLength == 3) {
							System.out.print("[D]\t");
						}
						else if (shipLength == 4) {
							System.out.print("[B]\t");
						}
						else if (shipLength == 5) {
							System.out.print("[A]\t");
						}
					}
					else if(player.grid[row][j].checkMiss()) {
						System.out.print("[0]\t");
					}
					else {
						System.out.print("[~]\t");
					}
				}
			}
			//end of user board
			System.out.print("\t\t"+rowCharacter+"\t");
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				int row = switchCounterToInt(i);
				if (opponent.grid[row][j].checkHit()) {
					System.out.print("[X]\t");
				}
				else if (opponent.grid[row][j].checkMiss()) {
					System.out.print("[0]\t");
				}
				else {
					System.out.print("[~]\t");
				}
			}
			System.out.println();
			System.out.println();
		}
	}
	public static void printBattleGrid(Grid player, Grid opponent, int type) {
		
		System.out.print("\t");
		for (int i = 1; i <= NUM_OF_COLUMNS; i++) {
			System.out.print(" "+i+"\t");
			
		}
		System.out.print("\t\t\t");
		for (int j = 1; j <= NUM_OF_COLUMNS; j++) {
			System.out.print(" "+j+"\t");
		}
		System.out.println();
		System.out.println();

		
		//print rows
		int endLetterForLoop = ((NUM_OF_ROWS - 1) + 65); 
		for (int i = 65; i <= endLetterForLoop; i++) {
			char theCharacter = (char)i;
			System.out.print(theCharacter+"\t");
			
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				switch(type) {
				case 0: if (player.grid[switchCounterToInt(i)][j].checkUnguessed()) {
							System.out.print("[~]\t");	
						}
						else if (player.grid[switchCounterToInt(i)][j].checkMiss()) {
							System.out.print("[0]\t");
						}
						else if (player.grid[switchCounterToInt(i)][j].checkHit()) {
							System.out.print("[X]\t");
						}
						break;
				case 1: if (player.grid[switchCounterToInt(i)][j].hasShip()) {
							if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 2) {
								System.out.print("[D]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 3) {
								System.out.print("[C]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 4) {
								System.out.print("[B]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 5) {
								System.out.print("[A]\t");
							}		
						}
						else if (!(player.grid[switchCounterToInt(i)][j].hasShip())) {
							System.out.print("[-]\t");
						}
						break;
				case 2: if (player.grid[switchCounterToInt(i)][j].checkHit()) {
							System.out.print("[X]\t");
						}
						else if (player.grid[switchCounterToInt(i)][j].hasShip()) {
							if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 2) {
								System.out.print("[D]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 3) {
								System.out.print("[C]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 4) {
								System.out.print("[B]\t");
							}
							else if (player.grid[switchCounterToInt(i)][j].getLengthOfShip() == 5) {
								System.out.print("[A]\t");
							}
						}
						else if (!(player.grid[switchCounterToInt(i)][j].hasShip())) {
							System.out.print("[-]\t");
						}
						break;	
				
				}
					
			}
			System.out.print("\t\t"+theCharacter+"\t");
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				switch(type) {
				case 0: if (opponent.grid[switchCounterToInt(i)][j].checkUnguessed()) {
							System.out.print("[~]\t");	
						}
						else if (opponent.grid[switchCounterToInt(i)][j].checkMiss()) {
							System.out.print("[0]\t");
						}
						else if (opponent.grid[switchCounterToInt(i)][j].checkHit()) {
							System.out.print("[X]\t");
						}
						break;
				case 1: if (opponent.grid[switchCounterToInt(i)][j].hasShip()) {
							if (opponent.grid[switchCounterToInt(i)][j].getLengthOfShip() == 2) {
								System.out.print("[D]\t");
							}
							else if (opponent.grid[switchCounterToInt(i)][j].getLengthOfShip() == 3) {
								System.out.print("[C]\t");
							}
							else if (opponent.grid[switchCounterToInt(i)][j].getLengthOfShip() == 4) {
								System.out.print("[B]\t");
							}
							else if (opponent.grid[switchCounterToInt(i)][j].getLengthOfShip() == 5) {
								System.out.print("[A]\t");
							}		
						}
						else if (!(opponent.grid[switchCounterToInt(i)][j].hasShip())) {
							System.out.print("[-]\t");
						}
						break;
				case 2: if (opponent.grid[switchCounterToInt(i)][j].checkHit()) {
							System.out.print("[X]\t");
						}
						else if (opponent.grid[switchCounterToInt(i)][j].hasShip()) {
							if (opponent.grid[switchCounterToInt(i)][j].getLengthOfShip() == 2) {
								System.out.print("[D]\t");
							}
							else if (opponent.grid[switchCounterToInt(i)][j].getLengthOfShip() == 3) {
								System.out.print("[C]\t");
							}
							else if (opponent.grid[switchCounterToInt(i)][j].getLengthOfShip() == 4) {
								System.out.print("[B]\t");
							}
							else if (opponent.grid[switchCounterToInt(i)][j].getLengthOfShip() == 5) {
								System.out.print("[A]\t");
							}
						}
						else if (!(opponent.grid[switchCounterToInt(i)][j].hasShip())) {
							System.out.print("[-]\t");
						}
						break;	
				
				}
					
			}
				
			System.out.println();
			System.out.println();
			System.out.println();
		}
		
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	private static int switchCounterToInt(int val) {
		if ((val - 65) < 0) {
			throw new IllegalArgumentException("Error Occured In conversion");
		}
		else {
			return val - 65;
		}
	}
}
