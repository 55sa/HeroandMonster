package Entity.Items;

//Weapon Class
public class Weapon extends Item {
    private int damage;          // Base damage of the weapon
    private int handsRequired;   // Number of hands required to use the weapon

    public Weapon(String name, int price, int level, int damage, int handsRequired) {
        super(name, price, level, Type.WEAPON);
        this.damage = damage;
        this.handsRequired = handsRequired;
        info.put("damage", damage);
        info.put("handsRequired", handsRequired);
    }

    public int getDamage() {
        return damage;
    }

    public int getHandsRequired() {
        return handsRequired;
    }

    @Override
    public String toString() {
        return "Weapon {" +
                "Name='" + name + '\'' +
                ", Price=" + price +
                ", Required Level=" + level +
                ", Damage=" + damage +
                ", Hands Required=" + handsRequired +
                ", Type=" + type +
                '}';
    }
}
