package Entity.Items;

public class IceSpell extends Spell{
    public IceSpell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, damage, manaCost);
        super.spellType = SpellType.ICE;
        info.put("spellType", SpellType.ICE);

    }
}
