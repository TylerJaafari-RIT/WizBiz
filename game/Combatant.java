package game;

import creatures.Character;

public class Combatant {
	public Character character;
	public int turn;

	public Combatant(Character character) {
		this.character = character;
		this.turn = 0;
	}
}
