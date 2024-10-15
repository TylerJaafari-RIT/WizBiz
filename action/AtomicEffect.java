package action;

import creatures.Character;
import creatures.Property;

public class AtomicEffect implements Effect {
	public enum EffectType {
		BURN,
		HEAL,
		WEAKEN,
		FORTIFY
	}

	public final String DESCRIPTION;
	public final Property AFFECTED_PROPERTY;
	public final EffectType EFFECT_TYPE;
	public final Character AFFECTED_CHARACTER;
	public final int IMPACT;

	private int duration;
	public int getDuration() { return this.duration; }

	public AtomicEffect(String description, Property affectedProperty, EffectType effectType, int impact, Character character, int duration) {
		DESCRIPTION = description;
		AFFECTED_PROPERTY = affectedProperty;
		EFFECT_TYPE = effectType;
		AFFECTED_CHARACTER = character;
		IMPACT = impact;
		this.duration = duration;
	}

	@Override
	public void passTurn() {
		duration--;
		if(EFFECT_TYPE == EffectType.BURN || EFFECT_TYPE == EffectType.HEAL) {
			AFFECTED_CHARACTER.changeProperty(AFFECTED_PROPERTY, IMPACT);
		}
	}

	@Override
	public void inflict(Character target) {
		target.inflictEffect(new AtomicEffect(this.DESCRIPTION, this.AFFECTED_PROPERTY, this.EFFECT_TYPE, this.IMPACT, target, this.duration));
	}

	@Override
	public String toString() {
		return DESCRIPTION + " for " + duration + " turns.";
	}
}
