package action;

import java.util.Random;

import creatures.Character;

public class Spell {
	private String name;
	private int manaCost;
	private String description;
	private int impact;
	private int magnitude;

	private Effect effects;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getManaCost() { return manaCost; }
	public void setManaCost(int manaCost) { this.manaCost = manaCost; }

	public String getDescription() { return description; }
	public void setDescription(String effect) { this.description = effect; }

	public int getImpact() { return impact; }
	public void setImpact(int damageDie) { this.impact = damageDie; }

	public int getMagnitude() { return magnitude; }
	public void setMagnitude(int diceCount) { this.magnitude = diceCount; }

	public Spell(String name, int manaCost, String description, int damageDie, int diceCount) {
		this.name = name;
		this.manaCost = manaCost;
		this.description = description;
		this.impact = damageDie;
		this.magnitude = diceCount;
	}

	public int calculateImpact() {
		if(impact == 0) {
			System.out.println(description);
			return 0;
		} else {
			System.out.println("Rolling " + magnitude + "d" + Math.abs(impact));
			Random diceRoller = new Random();
			int damage = 0;
			for(int d = 0; d < magnitude; d++) {
				damage += diceRoller.nextInt(Math.abs(impact)) + 1;
			}
			return damage;
		}
	}

	public void cast(Character target) {
		effects.inflict(target);
	}

	@Override
	public String toString() {
		int min = magnitude;
		int max = Math.abs(impact) * magnitude;
		return name + "\n" + "\"" + description + "\"\nImpact: " + min + "/" + max + "\nMana Cost: " + manaCost;
	}
}
