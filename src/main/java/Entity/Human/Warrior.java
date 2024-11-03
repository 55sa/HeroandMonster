package Entity.Human;

// warrior hero
public class Warrior extends Hero {


    public Warrior(String name) {
        super(name);
        super.setAgility(super.getAgility() * 2);
        super.setStrength(super.getStrength() *2);
    }
}
