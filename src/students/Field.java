package students;

import java.util.Random;

import students.items.Item;
import students.items.Soil;
import students.items.UntilledSoil;
import students.items.Weed;

public class Field {
	
	public Item[][] field;
	public int height;
	public int width;
	
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
	
	public String toString() {
		return;
	}
	
	public void till() {
		
	}
	
	public void get() {
		
	}
	
	public void plant() {
		
	}
	
	public void getValue() {
		
	}
	
	public void getSummary() {
		
	}
}
