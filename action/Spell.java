package action;

import java.util.Random;

public class Spell {
    public enum EffectType {
        DAMAGE, HEAL, ILLUSION
    }

    public enum Target {
        SELF, OTHER
    }

    private String name;
    private int manaCost;
    private String effect;
    private int damageDie;
    private int diceCount;
    public final Target target;
    public final EffectType effectType;
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getManaCost() { return manaCost; }
    public void setManaCost(int manaCost) { this.manaCost = manaCost; }
    public String getEffect() { return effect; }
    public void setEffect(String effect) { this.effect = effect; }
    public int getDamageDie() { return damageDie; }
    public void setDamageDie(int damageDie) { this.damageDie = damageDie; }
    public int getDiceCount() { return diceCount; }
    public void setDiceCount(int diceCount) { this.diceCount = diceCount; }
    public Target getTarget() { return target; }
    public EffectType getEffectType() { return effectType; }

    public Spell(String name, int manaCost, String effect, int damageDie, int diceCount, EffectType effectType, Target target) {
        this.name = name;
        this.manaCost = manaCost;
        this.effect = effect;
        this.damageDie = damageDie;
        this.diceCount = diceCount;
        this.effectType = effectType;
        this.target = target;
    }

    public int doEffect() {
        if(damageDie == 0) {
            System.out.println(effect);
            return 0;
        } else {
            System.out.println("Rolling " + diceCount + "d" + damageDie);
            Random diceRoller = new Random();
            int damage = 0;
            for(int d = 0; d < diceCount; d++) {
                damage += diceRoller.nextInt(damageDie) + 1;
            }
            return damage;
        }
    }

}
