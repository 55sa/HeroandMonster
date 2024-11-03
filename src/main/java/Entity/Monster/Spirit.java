package Entity.Monster;

// Spirit Class
public class Spirit extends Monster {
    public Spirit(String name, int level) {
        super(name, level);
        super.setDodgeChance(super.getDodgeChance() * 2);
    }
}
