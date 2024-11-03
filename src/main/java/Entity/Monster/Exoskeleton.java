package Entity.Monster;

//Exoskeleton Class
public class Exoskeleton extends Monster {
    public Exoskeleton(String name, int level) {
        super(name, level);
        super.setDefense(super.getDefense() * 2);
    }
}
