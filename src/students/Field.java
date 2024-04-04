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
	
	public Field(int height, int width){
		this.height = height;
		this.width = width;
		this.field = new Item[height][width];
		for (int r=0; r<height; r++) {
			for (int c=0; c < width; c++) {
				field[r][c] = new Soil();
			}
		}
	}
	
	public void tick() {
		Random random = new Random();
		for (int r=0; r < height; r++) {
			for (int c=0; c < width; c++) {
				field[r][c].tick();
				if (field[r][c] instanceof Soil && random.nextDouble() < 0.2) {
					field[r][c] = new Weed();
				}
				if (field[r][c].died()) {
					field[r][c] = new UntilledSoil();
				}
			}
		}
	}
	
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
				sb.append(field[r][c]).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void till(int x, int y) {
		if (x >= 0 && x < height && y >= 0 && y < width) {
			field[x][y] = new Soil();
		}
	}
	
	public Item get(int x, int y) {
		if (x >= 0 && x < height && y >= 0 && y < width) {
			return field[x][y];
		}
		return null;
	}
	
	public void plant(int x, int y, Item item) {
		if (x >= 0 && x < height && y >= 0 && y < width) {
			field[x][y] = item;
			if (item instanceof Apples) {
				applesCreated++;
			} else if (item instanceof Grain) {
				grainsCreated++;
			}
		}
	}
	
	public int getValue() {
		int totalValue = 0;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				totalValue += field[r][c].getValue();
			}
		}
		return totalValue;
	}
	
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
