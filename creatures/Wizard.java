package creatures;

import java.util.Dictionary;
import java.util.Hashtable;

import action.Spell;
import action.Spell.EffectType;
import action.Spell.Target;

public class Wizard extends Character {
	private int maxMana;
	private int mana;

	private String callout = "It's Wizard time, motherfucker!";

	// public String getName() { return name; }
	// public void setName(String name) { this.name = name; }

	// public int getAge() { return age; }
	// public void setAge(int age) { this.age = age; }

	// public int getHp() { return hp; }
	// public void setHp(int hp) { this.hp = hp; }
	// public int getMaxHp() { return this.maxHP; }

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

		this.learnSpell(new Spell("prestidigitation", 0, name + " looks very sparkly...", 0, 0, EffectType.ILLUSION, Target.SELF));
	}

	public void regenMana(int amount) {
		if(amount > 0) {
			this.mana += amount;
			if(mana > maxMana)
				mana = maxMana;
		}
	}

	public void castSpell(Spell spell, Character target) {
		if(mana > spell.getManaCost()) {
			System.out.println(getName() + ": " + callout);
			System.out.println(spell.getName() + "!");

			int damage = spell.doEffect();
			if(spell.effectType == EffectType.DAMAGE) {
				target.takeDamage(damage);
				System.out.println(target.getName() + " took " + damage + " " + spell.getEffect());
			} else if(spell.effectType == EffectType.HEAL) {
				target.heal(damage);
				System.out.println(target.getName() + " healed for " + damage);
			}
		}
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
