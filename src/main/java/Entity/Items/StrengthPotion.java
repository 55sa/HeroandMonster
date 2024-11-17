package Entity.Items;

public class StrengthPotion extends Potion{
    public StrengthPotion(String name, int price, int level, int effectAmount) {
        super(name, price, level, effectAmount);
        super.statType = StatType.STRENGTH;
        info.put("statType",StatType.STRENGTH);
    }
}
