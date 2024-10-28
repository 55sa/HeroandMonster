package Entity.Monster;

public abstract class Monster {
    private String name;

    private int level;

    private int HP;

    private int damage;

    private int defense;

    private int dodge;

    public Monster(String name, int level) {
        this.name = name;
        this.level = level;
    }
}
