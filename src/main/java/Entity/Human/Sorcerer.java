package Entity.Human;

public class Sorcerer extends Hero {


    public Sorcerer(String name) {
        super(name);
        super.setAgility(super.getAgility() * 2);
        super.setDexterity(super.getDexterity() * 2);
    }
}
