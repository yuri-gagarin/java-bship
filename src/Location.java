public class Location {
	public static final int UNGUESSED = 0;
	public static final int HIT = 1;
	public static final int MISSED = -1;
	
	//instance variables 
	private boolean hasShip;
	private int status;
	private int lengthOfShip;
	private int directionOfShip;
	//Location constructor and initial values
	public Location() {
		this.status = 0;
		this.hasShip = false;
		this.lengthOfShip = -1;
		this.directionOfShip = -1;
	}
	//check if location is a hit
	public boolean checkHit() {
		if (this.status == HIT) {
			return true;
		}
		else {
			return false;
		}
	}
	//check if location is a miss
	public boolean checkMiss() {
		if (this.status == MISSED) { 
			return true;
		}
		else {
			return false;
		}
	}
	//check if location is unguessed
	public boolean checkUnguessed() {
		if (this.status == UNGUESSED) {
			return true;
		}
		else {
			return false;
		}
	} 
	//mark location as hit
	public void markHit() {
		this.status = HIT;
	}
	//mark location as miss
	public void markMiss() {
		this.status = MISSED;
	}
	//check if location has a ship
	public boolean hasShip() {
		return this.hasShip;
	}
	//set a ship or part of ship to this location
	public void setShip(boolean val) {
		this.hasShip = val;
	}
	//set status of the location
	public void setStatus(int status) {
		this.status = status;
	}
	//get the status of the location
	public int getStatus() {
		return this.status;
	}
	//get length of ship at the location
	public int getLengthOfShip() {
		return this.lengthOfShip;
	}
	//set length of ship at the location
	public void setLengthOfShip(int lengthOfShip) {
		this.lengthOfShip = lengthOfShip;
	}
	//get direction of ship
	public int getDirectionOfShip() {
		return this.directionOfShip;
	}
	//set direction of ship
	public void setDirectionOfShip(int directionOfShip) {
		this.directionOfShip = directionOfShip;
	}
	
}
