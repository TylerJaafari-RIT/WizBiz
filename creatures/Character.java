package creatures;

import java.security.InvalidParameterException;

public class Character {
	private String name;
	private int age;
	private int maxHP;
	private int hp;

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }

	public int getHp() { return hp; }
	public void setHp(int hp) { this.hp = hp; }
	public int getMaxHp() { return this.maxHP; }

	public Character(String name, int age, int maxHP) {
		this.name = name;
		this.age = age;
		this.maxHP = maxHP;
		this.hp = maxHP;
	}

	public void heal(int amount) {
		if(amount > 0) {
			this.hp += amount;
			if(hp > maxHP)
				hp = maxHP;
		}
		else {
			throw new InvalidParameterException("Cannot heal for a non-positive amount.");
		}
	}

	public void takeDamage(int amount) {
		if(amount > 0) {
			this.hp -= amount;
		} else {
			throw new InvalidParameterException("Cannot take negative damage.");
		}
	}

	@Override
	public String toString() {
		return "Name: " + this.name + "\nAge: " + this.age + "\nHP: " + hp + "/" + maxHP;
	}
}
