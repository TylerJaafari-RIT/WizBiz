package action;

import creatures.Character;

public class EffectComposite implements Effect {
	public static final int MAX_EFFECT_LIST_SIZE = 3;

	private Effect [] effects;

	public EffectComposite() {
		effects = new Effect[MAX_EFFECT_LIST_SIZE];
	}

	public EffectComposite(Effect [] effects) {
		this.effects = effects;
		// for(Effect effect : effects) {
		//     this.addEffect(effect);
		// }
	}

	/**
	 * Adds a new effect to the composite object.
	 * @param effect
	 */
	public void addEffect(Effect effect) {
		Effect lastEffect = effects[effects.length - 1];
		if(lastEffect != null) {
			if(lastEffect instanceof AtomicEffect) {
				// create a new branch at the end of the list and add the
				// effect as a leaf
				EffectComposite effectComp = new EffectComposite();
				effectComp.addEffect(lastEffect);
				effectComp.addEffect(effect);
				effects[effects.length - 1] = effectComp;
			} else if(lastEffect instanceof EffectComposite) {
				((EffectComposite)lastEffect).addEffect(effect);
			}
		} else {
			for(int i = 0; i < effects.length; i++) {
				if(effects[i] == null) {
					effects[i] = effect;
					break;
				}
			}
		}
	}

	@Override
	public void passTurn() {
		for(Effect effect : effects) {
			if(effect != null)
				effect.passTurn();
		}
	}

	@Override
	public void inflict(Character target) {
		for(Effect effect : effects) {
			effect.inflict(target);
		}
	}

	public void cleanUp() {
		for(int i = 0; i < effects.length; i++) {
			if(effects[i] != null) {
				if(effects[i] instanceof AtomicEffect) {
					if(((AtomicEffect)effects[i]).getDuration() <= 0) {
						effects[i] = null;
					}
				} else if(effects[i] instanceof EffectComposite) {
					((EffectComposite)effects[i]).cleanUp();
				}
			}
		}
	}

	@Override
	public String toString() {
		String str = "";
		for(Effect effect : effects) {
			if(effect != null)
				str += effect.toString() + "\n";
		}
		return str.trim();
	}
}
