
public class Ship {
	private int row;
	private int column;
	private int length;
	private int direction;
	
	public static final int UNSET = -1;
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	
	public Ship(int length) {
		this.length = length;
		this.row = -1;
		this.column = -1;
		this.direction = UNSET;
	}
	//is location been initialized
	public boolean isLocationSet() {
		if (this.row == -1 || this.column == -1) {
			return false;
		}
		else {
			return true;
		}
	}
	//has direction been set
	public boolean isDirectionSet() {
		if (this.direction == UNSET) {
			return false;
		}
		else {
			return true;
		}
	}
	//set the location of the ship
	public void setLocation(int row, int column) {
		this.row = row;
		this.column = column;
	}
	//getters
	public int getRow() {
		return this.row;
	}
	public int getColumn( ) {
		return this.column;
	}
	public int getLength() {
		return this.length;
	}
	public int getDirection() {
		return this.direction;
	}
	//setters
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public String directionToString() {
		if (this.direction == UNSET) {
			return "UNSET";
		}
		else if (this.direction == VERTICAL) {
			return "VERTICAL";
		}
		else {
			return "HORIZONTAL";
		}
	}
}
