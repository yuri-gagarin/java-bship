
public class Grid {
	public Location[][] grid;
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
	
} 
