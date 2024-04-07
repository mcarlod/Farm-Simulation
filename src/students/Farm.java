package students;

import java.util.Scanner;

import students.items.Apples;
import students.items.Food;
import students.items.Grain;
import students.items.Item;
import students.items.Soil;

public class Farm {
	public Field field;
	public Scanner scanner;
	private int bankBalance;

	// Constructor to initialize farm
	public Farm(int fieldWidth, int fieldHeight, int startingFunds){
		scanner = new Scanner(System.in);
		field = new Field(fieldWidth, fieldHeight);
		bankBalance = startingFunds;
	}
	
	// Method to run the simulation
	public void run() {
		int hopsRemaining = Rabbit.maxHops; // Maximum number of hops allowed
		while (true) {
			// Display current state of field and controls
			System.out.println(field);
			System.out.println("Bank balance: $" + bankBalance);
			System.out.println("Enter your next action:");
			System.out.println("  t x y: till");
			System.out.println("  h x y: harvest");
			System.out.println("  p x y: plant");
			System.out.println("  c: lure rabbit with carrots");
			System.out.println("  s: field summary");
			System.out.println("  w: wait");
			System.out.println("  q: quit");
			
			// Decrement hops remaining after each turn
            hopsRemaining--;
			
            // Check if the rabbit has used all its hops
            if (hopsRemaining <= 0) {
                // Remove the rabbit from the field
                field.removeRabbit();
                // Reset hops remaining for the next rabbit
                hopsRemaining = Rabbit.maxHops;
            }
			
			// Gets user input
			String[] input = scanner.nextLine().split(" ");
			if (input.length == 0) {
				System.out.println("Invalid input.");
				continue;
			}
            
			String action = input[0];
			try {
				int x, y;
				switch (action) {
					case "t":
						// tills soil at x y
						x = Integer.parseInt(input[1]) - 1;
						y = Integer.parseInt(input[2]) - 1;
						field.till(x,y);
						break;
					case "h":
						// harvests item at x y
						x = Integer.parseInt(input[1]) - 1;
						y = Integer.parseInt(input[2]) - 1;
						Item item = field.get(x, y);
						if (item instanceof Food) {
							int value = ((Food) item).getValue();
							if (value > 0) {
								bankBalance += value;
								field.plant(x, y, new Soil());
							}
						}
						break;
					case "p":
						// plant item at x y
						x = Integer.parseInt(input[1]) - 1;
						y = Integer.parseInt(input[2]) - 1;
						System.out.println("Enter:");
						System.out.println(" - 'a' to buy an apple for $2");
						System.out.println(" - 'g' to buy grain for $1");
						
						String plantType = scanner.nextLine();
						switch (plantType) {
							case "a":
								// plants apple
								if (bankBalance >= 2) {
									bankBalance -= 2;
									field.plant(x,y, new Apples());
								} else {
									System.out.println("Insufficient funds.");
								}
								field.tick();
								break;
							case "g":
								// plants grain
								if (bankBalance >= 1) {
									bankBalance -= 1;
									field.plant(x, y,  new Grain());
								} else {
									System.out.println("Insufficient funds.");
								}
								field.tick();
								break;
							default:
								System.out.println("Invalid input.");
						}
						break;
					case "c":
						// lure rabbit with carrots bought
						if (field.rabbitSpawned == true) {
							field.lureRabbit(bankBalance);
							bankBalance -= 2;
						} else {
							System.out.println("There's no rabbits!");
						}
						break;	
					case "s":
						// displays summary of field
						System.out.println(field.getSummary());
						break;
					case "w":
						// wait and let field tick
						field.tick();
						break;
					case "q":
						// quit game
						System.out.println("Ending game.");
						return;
					default:
						System.out.println("Invalid input.");
				}
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid input.");
			}
		}
	}
	
	// Main method to start program
	public static void main(String[] args) {
		Farm farm = new Farm(5,10,10);
		farm.run();
	}
}
