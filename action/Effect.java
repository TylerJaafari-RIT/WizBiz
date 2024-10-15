package action;

import creatures.Character;

public interface Effect {
	public void passTurn();
	public void inflict(Character target);
}
