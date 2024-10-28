package Entity.Human;

public class Paladin extends Hero {


    public Paladin(String name) {
        super(name);
        super.setStrength(super.getStrength() * 2);
        super.setDexterity(super.getDexterity() * 2);
    }
}
