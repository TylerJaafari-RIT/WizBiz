package game;

import action.*;
import creatures.*;
import creatures.Character;

public class Team {
	public static final int MAX_SLOTS = 5;

	private Character [] members;

	public Team() {
		members = new Character[MAX_SLOTS];
	}
}
