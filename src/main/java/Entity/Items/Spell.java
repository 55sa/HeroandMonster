package Entity.Items;

//Spell class
public abstract class Spell extends Item {
    private int damage;          // Damage value of the spell
    private int manaCost;        // Mana cost to cast the spell
    protected SpellType spellType; // Type of spell (e.g., FIRE, ICE, LIGHTNING)

    public  enum SpellType {
        FIRE, ICE, LIGHTNING
    }

    public Spell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, Type.SPELL);
        this.damage = damage;
        this.manaCost = manaCost;
        info.put("damage", damage);
        info.put("manaCost", manaCost);

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
