package students.items;

public class Food extends Item {

	public Food(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}

	public String toString() {
		return "Food";
	}
}
