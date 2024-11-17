package Entity.Items;

public class AgilityPotion extends Potion{
    public AgilityPotion(String name, int price, int level, int effectAmount) {
        super(name, price, level, effectAmount);
        super.statType = StatType.AGILITY;
        info.put("statType",StatType.AGILITY);
    }
}
