
public class Grid {
	private Location[][] grid;
	private int points;
	
	public static final int NUM_OF_ROWS = 10;
	public static final int NUM_OF_COLUMNS = 10;
	
	//grid constructor 
	public Grid() {
		this.grid  = new Location[NUM_OF_ROWS][NUM_OF_COLUMNS];
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++) {
				Location tempLocation = new Location();
				grid[row][column] = tempLocation;
			}
		}
	}
	//mark a hit
	public void markHit(int row, int column) {
		this.grid[row][column].markHit();
		this.points++;
	}
	//mark a miss 
	public void markMiss(int row, int column) {
		this.grid[row][column].markMiss();
	}
	//set the status of this location object
	public void setStatus(int row, int column, int status) {
		this.grid[row][column].setStatus(status);
	}
	//get the status of this location object
	public int getStatus(int row, int column) {
		return this.grid[row][column].getStatus();
	}
	//check if the location object has been guessed
	public boolean isGuessed(int row, int column) {
		return !this.grid[row][column].checkUnguessed();
	}
	//set whether or not there is a ship at this tile
	public void setShip(int row, int column, boolean value) {
		this.grid[row][column].setShip(value);
	}
	//check if there is a ship at this value
	public boolean hasShip(int row, int column) {
		return this.grid[row][column].hasShip();
	}
	//get the Location object at specific row and column
	public Location getLocation(int row, int column) {
		return this.grid[row][column];
	}
	//return number of rows in a grid
	public int getRows() {
		return NUM_OF_ROWS;
	}
	//return number of columns in a grid
	public int getColumns() {
		return NUM_OF_COLUMNS;
	}
	public void printStatus() {
		generalPrintFunction(0);
	}
	public void printShips() {
		generalPrintFunction(1);
	}
	public void printCombined() {
		generalPrintFunction(2);
	}
	public boolean hasLost() {
		if (this.points >= 17) {
			return true;
		}
		else {
			return false;
		}
	}
	//add a ship to grid
	public void addShip(Ship ship) {
		int row = ship.getRow();
		int column = ship.getColumn();
		int direction = ship.getDirection();
		int shipLength = ship.getLength();
		//0 == Horizontal; 1 == Vertical
		if (direction == 0) {
			for (int i = column; i < shipLength + column; i++) {
				this.grid[row][i].setShip(true);
				this.grid[row][i].setLengthOfShip(shipLength);
				this.grid[row][i].setDirectionOfShip(direction);
			}
		}
		else if (direction == 1) {
			for (int i = row; i < shipLength + row; i++) {
				this.grid[i][column].setShip(true);
				this.grid[i][column].setLengthOfShip(shipLength);
				this.grid[i][column].setDirectionOfShip(direction);
			}
		}
	}
	//Type: 0 for status; 1 for ships; 2 for combined
	private void generalPrintFunction(int type) {
		System.out.println();
		//print HEADER
		System.out.print("\t");
		for (int i = 1; i <= NUM_OF_COLUMNS; i++) {
			System.out.print(" "+i+"\t");
			
		}
		System.out.println();
		
		//print rows
		int endLetterForLoop = ((NUM_OF_ROWS - 1) + 65); 
		for (int i = 65; i <= endLetterForLoop; i++) {
			char theCharacter = (char)i;
			System.out.print(theCharacter+"\t");
			
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				switch(type) {
				case 0: if (this.grid[switchCounterToInt(i)][j].checkUnguessed()) {
							System.out.print("[~]\t");	
						}
						else if (this.grid[switchCounterToInt(i)][j].checkMiss()) {
							System.out.print("[0]\t");
						}
						else if (this.grid[switchCounterToInt(i)][j].checkHit()) {
							System.out.print("[X]\t");
						}
						break;
				case 1: if (this.grid[switchCounterToInt(i)][j].hasShip()) {
							if (this.grid[switchCounterToInt(i)][j].getLengthOfShip() == 2) {
								System.out.print("[D]\t");
							}
							else if (this.grid[switchCounterToInt(i)][j].getLengthOfShip() == 3) {
								System.out.print("[C]\t");
							}
							else if (this.grid[switchCounterToInt(i)][j].getLengthOfShip() == 4) {
								System.out.print("[B]\t");
							}
							else if (this.grid[switchCounterToInt(i)][j].getLengthOfShip() == 5) {
								System.out.print("[A]\t");
							}		
						}
						else if (!(this.grid[switchCounterToInt(i)][j].hasShip())) {
							System.out.print("[-]\t");
						}
						break;
				case 2: if (this.grid[switchCounterToInt(i)][j].checkHit()) {
							System.out.print("[X]\t");
						}
						else if (this.grid[switchCounterToInt(i)][j].hasShip()) {
							if (this.grid[switchCounterToInt(i)][j].getLengthOfShip() == 2) {
								System.out.print("[D]\t");
							}
							else if (this.grid[switchCounterToInt(i)][j].getLengthOfShip() == 3) {
								System.out.print("[C]\t");
							}
							else if (this.grid[switchCounterToInt(i)][j].getLengthOfShip() == 4) {
								System.out.print("[B]\t");
							}
							else if (this.grid[switchCounterToInt(i)][j].getLengthOfShip() == 5) {
								System.out.print("[A]\t");
							}
						}
						else if (!(this.grid[switchCounterToInt(i)][j].hasShip())) {
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
	public int switchCounterToInt(int val) {
		if ((val - 65) < 0) {
			throw new IllegalArgumentException("Error Occured In conversion");
		}
		else {
			return val - 65;
		}
	}
	
} 
