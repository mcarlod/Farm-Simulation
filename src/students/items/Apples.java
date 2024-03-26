package students.items;

public class Apples extends Food {

	private static int generationCount = 0;
	
	public Apples() {
		super(3, 5, 3);
		generationCount += 1;
	}
	
	public static int getGenerationCount() {
		return generationCount;
	}
	
	public String toString() {
		if (age < maturationAge)
			return "a";
		else
			return "A";
	}

}
