import java.util.Random;

public class Randomizer {
	public Random theInstance = null;
	
	public Random getInstance() {
		if (theInstance == null) {
			theInstance = new Random();
		}
		return theInstance;
	}
	
	public boolean nextBoolean() {
		return getInstance().nextBoolean();
	}
	public boolean nextBoolean(double probability) {
		return nextDouble() < probability;
	}
	public int nextInt() {
		return getInstance().nextInt();
	}
	public int nextInt(int n) {
		return getInstance().nextInt(n);
	}
	public int nextInt(int min, int max) {
		return min + nextInt(max - min + 1);
	}
	public double nextDouble() {
		return getInstance().nextDouble();
	}
	public double nextDouble(double min, double max) {
		return min + (max - min) * nextDouble();
	}
} 
 