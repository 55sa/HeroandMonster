package Entity.Items;

public class Spell extends Item {
    private int damage;          // Damage value of the spell
    private int manaCost;        // Mana cost to cast the spell
    private SpellType spellType; // Type of spell (e.g., FIRE, ICE, LIGHTNING)

    public enum SpellType {
        FIRE, ICE, LIGHTNING
    }

    public Spell(String name, int price, int level, int damage, int manaCost, SpellType spellType) {
        super(name, price, level, Type.SPELL);
        this.damage = damage;
        this.manaCost = manaCost;
        this.spellType = spellType;
        info.put("damage", damage);
        info.put("manaCost", manaCost);
        info.put("spellType", spellType);
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public void castSpell() {
        System.out.println(name + " casts a " + spellType + " spell with damage " + damage);
    }

    @Override
    public String toString() {
        return "Spell {" +
                "Name='" + name + '\'' +
                ", Price=" + price +
                ", Required Level=" + level +
                ", Damage=" + damage +
                ", Mana Cost=" + manaCost +
                ", Spell Type=" + spellType +
                ", Type=" + type +
                '}';
    }
}
