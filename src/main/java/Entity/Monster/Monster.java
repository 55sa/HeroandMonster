package Entity.Monster;

public abstract class Monster {
    private String name; // Monster's name
    private int level; // Monster's level
    private int HP; // Monster's health points
    private int damage; // Monster's damage
    private int defense; // Monster's defense
    private double dodgeChance; // Monster's dodge chance

    // Constants for default values
    private static final int BASE_HP = 100;
    private static final int BASE_DAMAGE = 20;
    private static final int BASE_DEFENSE = 10;
    private static final double BASE_DODGE_MULTIPLIER = 1;

    public Monster(String name, int level) {
        this.name = name;
        this.level = level;
        this.HP = BASE_HP * level; // Scale HP with level
        this.damage = BASE_DAMAGE * level; // Scale damage with level
        this.defense = BASE_DEFENSE * level; // Scale defense with level
        this.dodgeChance = level * BASE_DODGE_MULTIPLIER; // Dodge chance based on level
    }

    public boolean isAlive() {
        return HP > 0;
    }

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
        this.HP = Math.max(0, HP);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = Math.max(0, damage);
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = Math.max(0, defense);
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = Math.max(0, Math.min(dodgeChance, 1));
    }

    public void reduceHealth(int amount) {
        int effectiveDamage = Math.max(0, amount - defense);
        setHP(HP - effectiveDamage);
        System.out.println(name + " takes " + effectiveDamage + " damage, HP now at " + HP + ".");
    }

    public void reduceDefense(double reduction) {
        this.defense = Math.max(0, (int)(this.defense - reduction));
        System.out.println(name + "'s defense reduced by " + reduction + ", now at " + this.defense + ".");
    }

    public void reduceBaseDamage(double reduction) {
        this.damage = Math.max(0, (int)(this.damage - reduction));
        System.out.println(name + "'s base damage reduced by " + reduction + ", now at " + this.damage + ".");
    }

    public void reduceDodgeChance(double reduction) {
        this.dodgeChance = Math.max(0, this.dodgeChance - reduction);
        System.out.println(name + "'s dodge chance reduced by " + reduction + ", now at " + this.dodgeChance + ".");
    }
}
