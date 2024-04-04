package students;

import java.util.Random;

public class Rabbit {

	private static final Random random = new Random();
	private static final double rabbitAppearanceProbability = 0.2;
	private static final int maxHops = 3;
	private int x;
	private int y;
	private int hopsLeft;
	
	public Rabbit() {
		this.x = -1;
		this.y = -1;
		this.hopsLeft = 0;
	}
	
	public boolean canHop() {
		return hopsLeft == 0;
	}
	
	public boolean spawn() {
		return random.nextDouble() < rabbitAppearanceProbability;
	}
	
	public void hop(Field field) {
		if (canHop()) {
			x = random.nextInt(field.getHeight());
			y = random.nextInt(field.getWidth());
			hopsLeft = maxHops;
			System.out.println("Wild Rabbit Appears. Buy a carrot to not risk it hopping on crops in the field.");
		}
		hopsLeft--;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
