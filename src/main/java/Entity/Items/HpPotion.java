package Entity.Items;

public class HpPotion extends Potion {
    public HpPotion(String name, int price, int level, int effectAmount) {
        super(name, price, level, effectAmount);
        super.statType = StatType.HP;
        info.put("statType",StatType.HP);
    }
}
