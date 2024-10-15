package action;

import creatures.Character;

public interface Effect {
	/**
	 * Handles duration decrementing and effects over time.
	 */
	public void passTurn();

	/**
	 * Clones this Effect or EffectComposites onto the specified target.
	 * @param target
	 */
	public void inflict(Character target);
}
