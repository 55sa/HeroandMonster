package Entity.Items;

import Entity.Human.Hero;

// Potion class
public class Potion extends Item {
    private int effectAmount;    // The amount of increase for the specific stat
    private StatType statType;   // The stat affected by the potion

    public Potion(String name, int price, int level, int effectAmount, StatType statType) {
        super(name, price, level, Type.POTION);
        this.effectAmount = effectAmount;
        this.statType = statType;
        info.put("effectAmount", effectAmount);
        info.put("statType", statType);
    }

    // Enum to define potion types
    public enum StatType {
        HP, MP, STRENGTH, DEXTERITY, AGILITY
    }

    public int getEffectAmount() {
        return effectAmount;
    }

    public StatType getStatType() {
        return statType;
    }

    public void applyEffect(Hero hero) {
        // Example method to apply potion effect based on stat type
        System.out.println("Applying " + effectAmount + " to " + statType);
    }

    @Override
    public String toString() {
        return "Potion {" +
                "Name='" + name + '\'' +
                ", Price=" + price +
                ", Required Level=" + level +
                ", Effect Amount=" + effectAmount +
                ", Stat Type=" + statType +
                ", Type=" + type +
                '}';
    }
}
