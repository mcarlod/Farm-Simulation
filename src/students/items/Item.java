package students.items;

public class Item {

	int age;
	int maturationAge;
	int deathAge;
	double monetaryValue;
	
	public Item(int maturationAge, int deathAge, double monetaryValue) {
		this.age = 0;
		this.maturationAge = maturationAge;
		this.deathAge = deathAge;
		this.monetaryValue = monetaryValue;
	}
	
	public void tick() {
		this.age += 1;
	}
	
	public void setAge(int newAge) {
		this.age = newAge;
	}
	
	public boolean died() {
		if (this.age > this.deathAge) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getValue() {
		
	}
	
	public boolean equals(Item otherItem) {
		return this.age == otherItem.age && this.maturationAge == otherItem.maturationAge && this.deathAge == otherItem.deathAge && this.monetaryValue == otherItem.monetaryValue;
	}
	
	public String toString() {
		
	}
}
