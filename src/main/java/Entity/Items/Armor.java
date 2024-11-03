package Entity.Items;

//Armor class
public class Armor extends Item {
    private int damageReduction;  // Damage reduction value of the armor

    // Constructor
    public Armor(String name, int price, int level, int damageReduction) {
        super(name, price, level, Type.ARMOR);  // Set type to ARMOR
        this.damageReduction = damageReduction;
        info.put("damageReduction", damageReduction);  // Store in info map
    }

    // Getter for damageReduction
    public int getDamageReduction() {
        return damageReduction;
    }

    // toString method for displaying armor details
    @Override
    public String toString() {
        return "Armor {" +
                "Name='" + name + '\'' +
                ", Price=" + price +
                ", Required Level=" + level +
                ", Damage Reduction=" + damageReduction +
                ", Type=" + type +
                '}';
    }
}
