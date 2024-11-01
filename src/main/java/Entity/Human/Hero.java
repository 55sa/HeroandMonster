package Entity.Human;

import Entity.Items.Armor;
import Entity.Items.Item;
import Entity.Items.Type;
import Entity.Items.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero {
    protected String name;
    protected int level;
    protected int exp;
    protected int HP;
    protected int MP;
    protected int gold;
    protected int strength;
    protected int dexterity;
    protected int agility;
    protected Equipment equipment;
    private List<Item> items;

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Hero(String name) {
        this.name = name;
        this.level = 10;
        this.exp = 0;
        this.HP = level * 100; // Initialize HP based on level
        this.MP = level * 50;  // Initialize MP based on level
        this.gold = 10000;       // Starting gold
        this.strength = 10000;
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
    public void equipWeapon(Item item, char hand) {
        if (item.getType() != Type.WEAPON) {
            System.out.println("Selected item is not a weapon.");
            return;
        }

        Weapon weapon = (Weapon) item; // Cast item to Weapon

        if (weapon.getHandsRequired() == 2) {
            // Double-handed weapon: Equip in right hand by default and clear the left hand
            equipment.setRightHand(weapon);
            equipment.setLeftHand(null); // Clear the left hand as it requires both hands
            equipment.setDoubleHand(true);
            System.out.println(name + " has equipped double-handed weapon " + weapon.getName() + " in the right hand.");
        } else {
            // Single-handed weapon
            if (hand == 'R') {
                // Equip in right hand; if a double-handed weapon is already equipped, replace it
                if (equipment.getDoubleHand()) {
                    equipment.setLeftHand(null); // Clear left hand if replacing double-handed weapon
                }
                equipment.setRightHand(weapon);
                equipment.setDoubleHand(false);
                System.out.println(name + " has equipped single-handed weapon " + weapon.getName() + " in the right hand.");
            } else if (hand == 'L') {
                // Equip in left hand; if a double-handed weapon is already equipped, replace it
                if (equipment.getDoubleHand()) {
                    equipment.setRightHand(null); // Clear right hand if replacing double-handed weapon
                }
                equipment.setLeftHand(weapon);
                equipment.setDoubleHand(false);
                System.out.println(name + " has equipped single-handed weapon " + weapon.getName() + " in the left hand.");
            } else {
                System.out.println("Invalid hand choice. Use 'L' for left hand or 'R' for right hand.");
            }
        }
    }


    public void equipArmor(Item item) {
        if (item.getType() != Type.ARMOR) {
            System.out.println("Selected item is not armor.");
            return;
        }

        Armor armor = (Armor) item; // Cast item to Armor
        equipment.setArmor(armor);
        System.out.println(name + " has equipped " + armor.getName() + ".");
    }

    // Add experience and handle level-up if experience threshold is reached
    public void addExp(int amount) {
        exp += amount;
        int expThreshold = level * 10; // Experience required to level up

        while (exp >= expThreshold) { // Loop to handle multiple level-ups
            exp -= expThreshold; // Deduct threshold amount for level-up
            levelUp();
            expThreshold = level * 10; // Update threshold for the new level
        }
    }

    // Method to handle leveling up: resets HP, MP, and improves skills
    private void levelUp() {
        level++; // Increment level

        // Reset HP and update MP
        HP = level * 100; // Reset HP to level-based formula
        MP *= 1.1; // Increase current mana by 10%

        // Increase all skills by 5%, and favored skills by an extra 5%
        strength += (int) (strength * 0.05); // Increase strength by 5%
        dexterity += (int) (dexterity * 0.05); // Increase dexterity by 5%
        agility += (int) (agility * 0.05); // Increase agility by 5%

        // Additional favored skill increases if applicable
        strength += (int) (strength * 0.05); // Extra 5% for favored skill
        System.out.println(name + " leveled up to " + level + "! New stats:");
        displayStats(); // Show updated stats
    }

    // Method to display hero's current stats
    public void displayStats() {
        System.out.println("Level: " + level);
        System.out.println("HP: " + HP);
        System.out.println("MP: " + MP);
        System.out.println("Strength: " + strength);
        System.out.println("Dexterity: " + dexterity);
        System.out.println("Agility: " + agility);
    }

    public void revive(){
        HP = (int) (level * 100 * 0.5);
        MP = (int) (level * 50 * 0.5);

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
