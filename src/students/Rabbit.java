package students;

import java.util.Random;

public class Rabbit {

	/*
	 * Rabbit Functions:
	 * 1. 40% chance of rabbit spawning in field when no rabbits exist
	 * 2. Rabbit hops 3 times before it leaves
	 * 3. Hopping causes the current state of a position in the field back
	 * to soil because rabbit destroys weed and eats grains and apples
	 * 4. User can buy carrots to lure rabbit out of field for 2$
	 */
	
	private static final Random random = new Random();
	public static final int maxHops = 3;
	private int x;
	private int y;
	private int hopsLeft;
	
	// Constructor to initialize rabbit's position and hops left
	public Rabbit() {
		this.x = -1;
		this.y = -1;
		this.hopsLeft = 0;
	}
	
	// Setter method for rabbit
	public int getRabbitX() {
		return x;
	}
	
	public int getRabbitY() {
		return y;
	}
	
	// Getter for position
    public Position getPosition() {
        return new Position(x, y);
    }
	
	// Method to update rabbit's position
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
	
	// checks if rabbit can still hop
	public boolean canHop() {
		return hopsLeft == 0;
	}
	
	// checks if rabbit should spawn which has 20% probability
	public boolean spawn() {
		return random.nextDouble() < 0.4;
	} 
	
	// Simulates rabbit hopping behavior
	 public void hop(Field field) {
		 if (canHop()) {
		        int newX = random.nextInt(field.getHeight());
		        int newY = random.nextInt(field.getWidth());

		        // Move the rabbit to the new position
		        field.removeRabbit(); // Remove rabbit from the current position
		        field.placeRabbit(newX, newY); // Place rabbit at the new position

		        // Decrement the hops left
		        hopsLeft--;

		        // Print message indicating rabbit's position in the field
		        System.out.println("Rabbit hopped to position (" + (newX + 1) + ", " + (newY + 1) + ").");

		        // If the rabbit has no more hops left, remove it from the field
		        if (!canHop()) {
		            field.removeRabbit();
		        }
		    } else {
		        System.out.println("Rabbit has no hops left.");
		    }
	    }

	// Setter for the number of hops
	    public void setHopsLeft(int hopsLeft) {
	        this.hopsLeft = hopsLeft;
	    }

	    // Getter for the number of hops
	    public int getHopsLeft() {
	        return hopsLeft;
	    }
}


//Define a Position class
class Position {
 private int x;
 private int y;

 // Constructor
 public Position(int x, int y) {
     this.x = x;
     this.y = y;
 }

 // Getter for x position
 public int getX() {
     return x;
 }

 // Getter for y position
 public int getY() {
     return y;
 }
}
