package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import action.*;
import action.AtomicEffect.EffectType;
import creatures.*;
import creatures.Character;

public class Arena {

	public static Character findTarget(Character attacker, Combatant [] combatants) {
		Character [] potentialTargets = new Character[combatants.length - 1];
		int i = 0;
		for (Combatant combatant : combatants) {
			if(attacker != combatant.character) {
				potentialTargets[i] = combatant.character;
				i++;
			}
		}

		Random rng = new Random();
		return potentialTargets[rng.nextInt(i)];
	}

	public static void main(String [] args) throws IOException {
		Wizard wiz1 = new Wizard("Suq Madiq", 69, 420, 1337);
		Wizard wiz2 = new Wizard("Valindra Shadowmantle", 30, 600, 9999);
		wiz2.setCallout("Skidaddle Skidoodle, your dick is now a noodle!");

		Spell magicMissile = new Spell("Magic Missile", 100, "Fires magical darts which always hit their targets", -4, 12);
		Spell fireball = new Spell("Fireball", 400, "Hurl an exploding ball of fire", -5, 32);
		Spell lesserheal = new Spell("Lesser Healing", 200, "Heal a target", 2, 15);
		Spell immolate = new Spell("Immolate", 60, "Set a target aflame, dealing damage over time", -10, 1);
		immolate.addEffect(new AtomicEffect("Deals fire damage over time", Property.HP, EffectType.BURN, -50, null, 4));
		wiz1.learnSpell(magicMissile);
		wiz1.learnSpell(fireball);
		wiz1.learnSpell(lesserheal);
		wiz1.learnSpell(immolate);
		wiz2.learnSpell(magicMissile);
		wiz2.learnSpell(fireball);
		wiz2.learnSpell(lesserheal);
		wiz2.learnSpell(immolate);

		// ARENA LOOP
		boolean fightOver = false;
		Random rng = new Random();

		Combatant [] combatants = {new Combatant(wiz1), new Combatant(wiz2)};

		while(!fightOver) {
			System.out.println("========================================");
			for(Combatant combatant : combatants) {
				combatant.character.printStatus();
				System.out.print("\n");
			}
			System.out.println("========================================");
			System.out.println("Press any key to continue...");
			System.in.read();

			Wizard readyToGo = null;
			for(Combatant combatant : combatants) {
				combatant.turn += combatant.character.getSpeed();
				if(combatant.turn >= 100) {
					combatant.turn -= 100;
					readyToGo = (Wizard)combatant.character;
					ArrayList<Spell> spells = readyToGo.getSpellBook();
					Spell chosenSpell = spells.get(rng.nextInt(spells.size()));
					if(chosenSpell.getImpact() > 0) {
						readyToGo.castSpell(chosenSpell);
					} else {
						Character target = findTarget(readyToGo, combatants);
						readyToGo.castSpell(chosenSpell, new Character[]{target});
					}
					combatant.character.passTurn();
				}
			}
			int deadWizards = 0;
			for (Combatant combatant : combatants) {
				if(combatant.character.getHp() == 0) {
					deadWizards++;
				} else {
					combatant.character.passTurn();
				}
			}
			if(deadWizards >= combatants.length - 1) {
				fightOver = true;
			}
		}

		System.out.println("\nThe Wizard Fight is over! The winner is:");
		for(Combatant combatant : combatants) {
			if(combatant.character.getHp() > 0) {
				System.out.println(combatant.character.getName() + "!\n");
			}
		}
	}
}
