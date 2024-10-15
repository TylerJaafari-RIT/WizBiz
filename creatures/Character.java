package creatures;

import action.*;

public class Character {
	private String name;
	private int age;
	private int maxHP;
	private int hp;
	private int armor;
	private EffectComposite effects;
	private double baseCritChance;

	public final int DEFAULT_ARMOR = 30;
	public final double DEFAULT_CRITCHANCE = 10.0;

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
		this.armor = DEFAULT_ARMOR;
		this.baseCritChance = DEFAULT_CRITCHANCE;
		effects = new EffectComposite();
	}

	public void inflictEffect(Effect effect) {
		effects.addEffect(effect);
	}

	public void changeProperty(Property property, int amount) {
		switch (property) {
			case HP:
				changeHP(amount);
				break;
			case Armor:
				changeArmor(amount);
				break;
			default:
				break;
		}
	}

	public void changeArmor(int amount) {
		this.armor += amount;
		if(armor < 0) armor = 0;
		else if(armor > 90) armor = 90;
	}

	public void changeHP(int amount) {
		this.hp += amount;
		if(hp < 0) hp = 0;
		else if(hp > maxHP) hp = maxHP;
	}

	@Override
	public String toString() {
		return "Name: " + this.name + "\nAge: " + this.age + "\nHP: " + hp + "/" + maxHP;
	}
}
