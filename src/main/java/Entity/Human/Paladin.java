package Entity.Human;
// Hero type Paladin
public class Paladin extends Hero {

    public Paladin(String name) {
        super(name);
        // Double the strength and dexterity for Paladins
        super.setStrength(super.getStrength() * 2);
        super.setDexterity(super.getDexterity() * 2);
    }
}
