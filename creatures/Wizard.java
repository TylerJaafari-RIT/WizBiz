package creatures;

import java.util.Dictionary;
import java.util.Hashtable;

import action.*;

public class Wizard extends Character {
	private int maxMana;
	private int mana;

	private String callout = "It's Wizard time, motherfucker!";

	public int getMana() { return mana; }
	public void setMana(int mana) { this.mana = mana; }
	public int getMaxMana() { return this.maxMana; }

	public String getCallout() { return callout; }
	public void setCallout(String callout) { this.callout = callout; }

	private Dictionary<String, Spell> spellBook;

	public void learnSpell(Spell spell) {
		spellBook.put(spell.getName(), spell);
	}

	public Wizard(String name, int age, int maxHP, int maxMana) {
		super(name, age, maxHP);
		this.maxMana = maxMana;
		this.mana = maxMana;
		this.spellBook = new Hashtable<>();

		// this.learnSpell(new Spell("prestidigitation", 0, name + " looks very sparkly...", 0, 0));
	}

	public void castSpell(Spell spell, Character [] targets) {
		if(mana > spell.getManaCost()) {
			System.out.println(getName() + ": " + callout);
			System.out.println(spell.getName() + "!");

			int impact = spell.calculateImpact();
		}
	}

	@Override
	public void changeProperty(Property property, int amount) {
		super.changeProperty(property, amount);
		switch (property) {
			case Mana:
				changeMana(amount);
				break;
			case Armor:
				changeArmor(amount);
				break;
			default:
				break;
		}
	}

	public void changeMana(int amount) {
		this.mana += amount;
		if(mana < 0) mana = 0;
		else if(mana > maxMana) mana = maxMana;
	}

	@Override
	public String toString() {
		return super.toString() + "\nMana: " + mana + "/" + maxMana;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Wizard) {
			Wizard otherWiz = (Wizard)o;
			return (this.getMaxHp() == otherWiz.getMaxHp() && this.maxMana == otherWiz.maxMana);
		} else {
			return false;
		}
	}

}
