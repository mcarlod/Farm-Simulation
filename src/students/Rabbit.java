package students;

import java.util.Random;

public class Rabbit {

	/*
	 * Rabbit Functions:
	 * 1. 20% chance of rabbit spawning in field when no rabbits exist
	 * 2. Rabbit hops 3 times before it leaves
	 * 3. Hopping causes the current state of a position in the field back
	 * to soil because rabbit destroys weed and eats grains and apples
	 * 4. User can buy carrots to lure rabbit out of field for 2$
	 */
	
	private static final Random random = new Random();
	private static final double rabbitAppearanceProbability = 0.2;
	private static final int maxHops = 3;
	private int x;
	private int y;
	private int hopsLeft;
	
	// Constructor to initialize rabbit's position and hops left
	public Rabbit() {
		this.x = -1;
		this.y = -1;
		this.hopsLeft = 0;
	}
	
	// checks if rabbit can still hop
	public boolean canHop() {
		return hopsLeft == 0;
	}
	
	// checks if rabbit should spawn based on probability
	public boolean spawn() {
		return random.nextDouble() < rabbitAppearanceProbability;
	}
	
	// Simulates rabbit hopping behavior
	public void hop(Field field) {
		if (canHop()) {
			// generates a random position within field
			x = random.nextInt(field.getHeight());
			y = random.nextInt(field.getWidth());
			hopsLeft = maxHops;
			// prints message indicating rabbits position in field
			System.out.println("Wild Rabbit Appears at (" + (x+1) + ", " + (y+1) + "). Buy a carrot to not risk it hopping on crops in the field.");
		}
		hopsLeft--; // decrements hopsLeft after each hop
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
