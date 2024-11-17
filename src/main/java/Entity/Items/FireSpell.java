package Entity.Items;

public class FireSpell extends Spell{


    public FireSpell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, damage, manaCost);
        super.spellType = SpellType.FIRE;
        info.put("spellType", SpellType.FIRE);

    }
}
