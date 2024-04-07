package students;

import java.util.Random;

import students.items.Apples;
import students.items.Grain;
import students.items.Item;
import students.items.Soil;
import students.items.UntilledSoil;
import students.items.Weed;

public class Field {
	
	public Item[][] field;
	public int height;
	public int width;
	private int applesCreated;
	private int grainsCreated;
	
	private Rabbit rabbit;
	public boolean rabbitSpawned;
	
	// Initialize the field with given dimensions and fill it with soil
	public Field(int height, int width){
		this.height = height;
		this.width = width;
		this.field = new Item[height][width];
		for (int r=0; r<height; r++) {
			for (int c=0; c < width; c++) {
				field[r][c] = new Soil();
			}
		}
		// Initialize rabbit and set rabbitSpawned to false
		this.rabbit = new Rabbit();
		this.rabbitSpawned = false;
	}
	
	public void placeRabbit(int x, int y) {
		if (x >= 0 && x < height && y >= 0 && y < width) {
            rabbit.setPosition(x,y);
        }
	}
	
	// Advances the simulation once per tick
	public void tick() {
		Random random = new Random();
		for (int r=0; r < height; r++) {
			for (int c=0; c < width; c++) {
				field[r][c].tick(); // Update state of each item
                // Replace soil with weed with 20% probability
                if (field[r][c] instanceof Soil && random.nextDouble() < 0.2) {
                    field[r][c] = new Weed();
                }
                // Replace dead plants with Untilled soil
                if (field[r][c].died()) {
                    field[r][c] = new UntilledSoil();
                }
			}
		}
		// Spawn or move rabbit
        if (rabbit.spawn() && !rabbitSpawned) {
            placeRabbit();
            rabbitSpawned = true;
        } else if (rabbit.canHop()) {
            rabbit.hop(this);
        } else {
            removeRabbit();
        }
	}
	
	// Method to convert field into a string representation
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	    sb.append(" ");
	    for (int r = 1; r <= width; r++) {
	        sb.append(r).append(" ");
	    }
	    sb.append("\n");
	    for (int r = 0; r < height; r++) {
	        sb.append(r+1).append(" ");
	        for (int c = 0; c < width; c++) {
	            if (rabbit != null && r == rabbit.getPosition().getX() && c == rabbit.getPosition().getY()) {
	                sb.append("R ");
	            } else {
	                sb.append(field[r][c]).append(" ");
	            }
	        }
	        sb.append("\n");
	    }
	    return sb.toString();
	}
	
	// Method to till Soil at a given position
	public void till(int x, int y) {
		// Checks if within field boundaries
		if (x >= 0 && x < height && y >= 0 && y < width) {
			field[x][y] = new Soil();
		}
	}
	
	// Method to retrieve item at a given position
	public Item get(int x, int y) {
		if (x >= 0 && x < height && y >= 0 && y < width) {
			return field[x][y];
		}
		return null;
	}
	
	// Method to plant an item at a given position
	public void plant(int x, int y, Item item) {
		if (x >= 0 && x < height && y >= 0 && y < width) {
            field[x][y] = item;
            // Increment counters for apples and grains created
            if (item instanceof Apples) {
                applesCreated++;
            } else if (item instanceof Grain) {
                grainsCreated++;
            }
        }
	}

	
	// Calculates total value of all items in field
	public int getValue() {
		int totalValue = 0;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				totalValue += field[r][c].getValue();
			}
		}
		return totalValue;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	// Removes rabbit from the field and sets rabbitSpawned to false
	public void removeRabbit() {
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				if (r == rabbit.getRabbitX() && c == rabbit.getRabbitY()) {
                    field[r][c] = new Soil();
                    rabbitSpawned = false;
                    rabbit.setPosition(-1, -1); // Reset rabbit position
                    return;
                }
	        }
	    }
	}
	
	 // Place the rabbit on the field
    private void placeRabbit() {
        Random random = new Random();
        int x = random.nextInt(height);
        int y = random.nextInt(width);
        while (!(field[x][y] instanceof Soil)) {
            x = random.nextInt(height);
            y = random.nextInt(width);
        }
        rabbit.setPosition(x, y);
    }
	
	// Lures rabbit away with carrots
	public void lureRabbit(int bankBalance) {
		if (bankBalance >= 2) {
			removeRabbit();
			bankBalance -= 2;
			System.out.println("You successfully lured rabbit away with carrots.");
		} else {
			System.out.println("Insufficient funds.");
		}
	}
	
	// Generates summary of field contents
	public String getSummary() {
		int totalSoil = 0;
		int totalUntilledSoil = 0;
		int totalWeed = 0;
		
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				if (field[r][c] instanceof Soil) {
					totalSoil++;
				} else if (field[r][c] instanceof UntilledSoil) {
					totalUntilledSoil++;
				} else if (field[r][c] instanceof Weed) {
					totalWeed++;
				}
			}
		}
	
		StringBuilder summary = new StringBuilder();
		summary.append("Apples:        ").append(applesCreated).append("\n");
		summary.append("Grain:         ").append(grainsCreated).append("\n");
		summary.append("Soil:          ").append(totalSoil).append("\n");
		summary.append("Untilled Soil: ").append(totalUntilledSoil).append("\n");
		summary.append("Weed:          ").append(totalWeed).append("\n");
		summary.append("For a total of $").append(getValue()).append("\n");
		summary.append("Total Apples Created: ").append(applesCreated).append("\n");
		summary.append("Total Grain Created: ").append(grainsCreated).append("\n");
		return summary.toString();
	}
}
