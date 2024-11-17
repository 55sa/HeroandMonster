package Entity.Items;

public class LightSpell extends Spell{
    public LightSpell(String name, int price, int level, int damage, int manaCost) {
        super(name, price, level, damage, manaCost);
        super.spellType = SpellType.LIGHTNING;
        info.put("spellType", SpellType.LIGHTNING);
    }
}
