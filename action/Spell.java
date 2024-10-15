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

	public Spell(String name, int manaCost, String description, int impact, int magnitude, Effect effect) {
		this(name, manaCost, description, impact, magnitude);
		this.effects = effect;
	}

	public Spell(String name, int manaCost, String description, int impact, int magnitude) {
		this.name = name;
		this.manaCost = manaCost;
		this.description = description;
		this.impact = impact;
		this.magnitude = magnitude;
	}

	public int calculateImpact() {
		if(impact == 0) {
			System.out.println(description);
			return 0;
		} else {
			System.out.print("Rolling " + magnitude + "d" + Math.abs(impact));
			Random diceRoller = new Random();
			int damage = 0;
			for(int d = 0; d < magnitude; d++) {
				damage += diceRoller.nextInt(Math.abs(impact)) + 1;
			}
			return (impact/Math.abs(impact)) * damage;
		}
	}

	public void addEffect(Effect effect) {
		if(effects == null) {
			effects = effect;
		} else if(effects instanceof AtomicEffect) {
			EffectComposite effectComp = new EffectComposite(new Effect[]{effects});
			effects = effectComp;
			effectComp.addEffect(effect);
		} else {
			((EffectComposite)effects).addEffect(effect);
		}
	}

	public void inflictEffects(Character target) {
		if(effects != null) {
			effects.inflict(target);
		}
	}

	@Override
	public String toString() {
		int min = magnitude;
		int max = Math.abs(impact) * magnitude;
		return name + "\n" + "\"" + description + "\"\nImpact: " + min + "/" + max + "\nMana Cost: " + manaCost;
	}
}
