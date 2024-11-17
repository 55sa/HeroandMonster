package Entity.Items;

public class MpPotion extends Potion{
    public MpPotion(String name, int price, int level, int effectAmount) {
        super(name, price, level, effectAmount);
        super.statType = StatType.MP;
        info.put("statType",StatType.MP);
    }
}
