package creatures;

import action.*;

public class Character {
	private String name;
	private int speed;
	private int maxHP;
	private int hp;
	private int armor;
	private EffectComposite effects;
	private double baseCritChance;

	public final int DEFAULT_ARMOR = 30;
	public final int MAX_ARMOR = 90;
	public final int MAX_SPEED = 90;
	public final double DEFAULT_CRITCHANCE = 10.0;

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getSpeed() { return speed; }
	public void setSpeed(int age) { this.speed = age; }

	public int getHp() { return hp; }
	public void setHp(int hp) { this.hp = hp; }
	public int getMaxHp() { return this.maxHP; }

	public Character(String name, int speed, int maxHP) {
		this.name = name;
		this.speed = speed;
		this.maxHP = maxHP;
		this.hp = maxHP;
		this.armor = DEFAULT_ARMOR;
		this.baseCritChance = DEFAULT_CRITCHANCE;
		effects = new EffectComposite();
	}

	public void inflictEffect(Effect effect) {
		effects.addEffect(effect);
	}

	public void passTurn() {
		effects.passTurn();
		effects.cleanUp();
	}

	public void changeProperty(Property property, int amount) {
		switch (property) {
			case HP:
				changeHP(amount);
				break;
			case Armor:
				changeArmor(amount);
				break;
			case Speed:
				changeSpeed(amount);
				break;
			default:
				break;
		}
	}

	public void changeSpeed(int amount) {
		this.speed += amount;
		if(speed < 0) speed = 0;
		// else if(speed > MAX_SPEED) speed = MAX_SPEED;
	}

	public void changeArmor(int amount) {
		this.armor += amount;
		if(armor < 0) armor = 0;
		// else if(armor > MAX_ARMOR) armor = MAX_ARMOR;
	}

	public void changeHP(int amount) {
		this.hp += amount;
		if(hp < 0) hp = 0;
		else if(hp > maxHP) hp = maxHP;
	}

	@Override
	public String toString() {
		return "Name: " + this.name + "\nAge: " + this.speed + "\nHP: " + hp + "/" + maxHP;
	}

	public void printStatus() {
		System.out.println(this);
		System.out.println(this.effects);
	}
}
