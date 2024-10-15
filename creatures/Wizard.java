package creatures;

import java.util.ArrayList;

import action.*;

public class Wizard extends Character {
	private int maxMana;
	private int mana;
	private ArrayList<Spell> spellBook;

	private String callout = "It's Wizard time, motherfucker!";

	public int getMana() { return mana; }
	public void setMana(int mana) { this.mana = mana; }
	public int getMaxMana() { return this.maxMana; }

	public String getCallout() { return callout; }
	public void setCallout(String callout) { this.callout = callout; }

	public ArrayList<Spell> getSpellBook() { return spellBook; }

	public void learnSpell(Spell spell) {
		spellBook.add(spell);
	}

	public Wizard(String name, int speed, int maxHP, int maxMana) {
		super(name, speed, maxHP);
		this.maxMana = maxMana;
		this.mana = maxMana;
		this.spellBook = new ArrayList<Spell>();

		// this.learnSpell(new Spell("prestidigitation", 0, name + " looks very sparkly...", 0, 0));
	}

	/**
	 * Casts a spell targetting this wizard.
	 * @param spell
	 */
	public void castSpell(Spell spell) {
		this.castSpell(spell, new Character[]{this});
	}

	public void castSpell(Spell spell, Character [] targets) {
		System.out.println(spell);
		if(mana > spell.getManaCost()) {
			System.out.print(getName() + ": " + callout);
			System.out.println(" I cast " + spell.getName() + "!");

			int impact = spell.calculateImpact();
			if(impact != 0) System.out.println(" => " + impact);
			for(Character target : targets) {
				target.changeProperty(Property.HP, impact);
				spell.inflictEffects(target);
			}

			mana -= spell.getManaCost();
		}
	}

	@Override
	public void passTurn() {
		super.passTurn();
		changeMana(50);
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
	public void printStatus() {
		super.printStatus();
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
