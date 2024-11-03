package Entity.Monster;

// Dragon class
public class Dragon extends Monster {
    public Dragon(String name, int level) {
        super(name, level);
        super.setDamage(super.getDamage() * 2);
    }
}
