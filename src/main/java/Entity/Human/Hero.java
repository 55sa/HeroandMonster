package Entity.Human;

import Entity.Items.Item;

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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public Hero(String name) {
        this.name = name;
        this.level = 1;
        this.HP = 100;
        this.MP = 1;
        this.gold = 100;
        this.strength = 1;
        this.dexterity = 1;
        this.agility = 1;
        this.items = new ArrayList<>();
        this.equipment=new Equipment(null, null, false, null);
    }

    private List<Item> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
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

    public List<Item> getIteams() {
        return items;
    }

    public void setIteams(List<Item> iteams) {
        this.items = items;
    }

    public void addItem(Item t){

        items.add(t);

    }

    public void deleteItem(Item t){
        String name = t.getName();
        for (int i=0; i<items.size();i++){
            if(items.get(i).getName().equals(name)){
                items.remove(i);
            }
        }
    }


}
