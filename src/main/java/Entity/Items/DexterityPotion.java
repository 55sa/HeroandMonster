package Entity.Items;

public class DexterityPotion extends Potion{
    public DexterityPotion(String name, int price, int level, int effectAmount) {
        super(name, price, level, effectAmount);
        super.statType = StatType.DEXTERITY;
        info.put("statType",StatType.DEXTERITY);
    }
}
