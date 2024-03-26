package students.items;

public abstract class Item {

	int age;
	int maturationAge;
	int deathAge;
	int monetaryValue;
	
	public Item(int maturationAge, int deathAge, int monetaryValue) {
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
	
	public int getValue() {
		return (age >= maturationAge) ? monetaryValue : 0;
	}
	
	public boolean equals(Item otherItem) {
		return this.age == otherItem.age && this.maturationAge == otherItem.maturationAge && this.deathAge == otherItem.deathAge && this.monetaryValue == otherItem.monetaryValue;
	}
	
	public abstract String toString();
}
