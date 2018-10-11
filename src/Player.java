
public class Player {
	
	private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
	private static final int NUM_OF_SHIPS = 5;
	
	public Ship[] ships;
	public Grid playerGrid;
	public Grid opponentGrid;
	
	public Player() {
		ships = new Ship[NUM_OF_SHIPS];
		for (int i = 0; i < NUM_OF_SHIPS; i++) {
			Ship tempShip = new Ship(SHIP_LENGTHS[i]);
			ships[i] = tempShip;
		}
		
		playerGrid = new Grid();
		opponentGrid = new Grid();
	}
	
	public void addShips() {
		for (Ship ship : ships) {
			playerGrid.addShip(ship);
		}
	}
	
	public int numOfShipsLeft() {
		int counter = 5;
		for (Ship ship : ships) {
			if (ship.isLocationSet() && ship.isDirectionSet()) {
				counter--;
			}
		}
		return counter;
	}
	
	public void chooseShipLocation(Ship ship, int row, int column, int direction) {
		ship.setLocation(row, column);
		ship.setDirection(direction);
		playerGrid.addShip(ship);
	}
}
