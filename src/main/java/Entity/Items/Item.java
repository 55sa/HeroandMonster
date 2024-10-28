package Entity.Items;

import java.util.HashMap;

//parent class of all items
public abstract class Item {
    protected String name;
    protected int price;
    protected int level;
    protected Type type;
    protected HashMap<String, Object> info;

    public Item(String name, int price, int level, Type type) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.type = type;
        this.info = new HashMap<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }

    public Type getType() {
        return type;
    }

    public HashMap<String, Object> getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Item {" +
                "Name='" + name + '\'' +
                ", Price=" + price +
                ", Required Level=" + level +
                ", Type=" + type +
                '}';
    }
}
