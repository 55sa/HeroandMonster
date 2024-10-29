package Entity.Human;

import Entity.Items.Armor;
import Entity.Items.Item;
import Entity.Items.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero {
    protected String name;
    protected int level;
    protected int HP;
    protected int MP;
    protected int gold;
    protected int strength;
    protected int dexterity;
    protected int agility;
    protected Equipment equipment;
    private List<Item> items;

    public Hero(String name) {
        this.name = name;
        this.level = 1;
        this.HP = level * 100; // Initialize HP based on level
        this.MP = level * 50;  // Initialize MP based on level
        this.gold = 100;       // Starting gold
        this.strength = level * 5;
        this.dexterity = level * 5;
        this.agility = level * 5;
        this.items = new ArrayList<>();
        this.equipment = new Equipment(null, null, false, null);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = Math.max(0, HP); // HP should not be negative
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.removeIf(i -> i.getName().equals(item.getName()));
    }

    public boolean isAlive() {
        return HP > 0;
    }

    // Equip a weapon: handle one-handed and two-handed logic
    public void equipWeapon(Weapon weapon) {
        if (weapon.getHandsRequired() == 2) {
            equipment.setLeftHand(weapon);
            equipment.setRightHand(weapon);
            equipment.setDoubleHand(true);
        } else {
            if (equipment.getDoubleHand() || equipment.getRightHand() == null) {
                equipment.setRightHand(weapon);
            } else {
                equipment.setLeftHand(weapon);
            }
            equipment.setDoubleHand(false);
        }
    }

    // Equip armor
    public void equipArmor(Armor armor) {
        equipment.setArmor(armor);
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void increaseHealth(int amount) {
        this.HP += amount;
    }

    public void reduceHealth(int amount) {
        setHP(this.HP - amount);
    }

    public void increaseMana(int amount) {
        this.MP += amount;
    }

    public void reduceMana(int amount) {
        this.MP = Math.max(0, this.MP - amount);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
