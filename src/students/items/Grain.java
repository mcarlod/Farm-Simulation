package students.items;

public class Grain extends Food{
	private static int generationCount = 0;
	
	public Grain() {
		super(2, 6, 2);
		generationCount += 1;
	}

	public static int getGenerationCount() {
		return generationCount;
	}
	
	public String toString() {
		if (age < maturationAge)
			return "g";
		else
			return "G";
	}
	
}
