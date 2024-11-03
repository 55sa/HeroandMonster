package Repository;


import Entity.Items.*;

import java.util.HashMap;

//Item factory implementation
public class ItemFactoryImp implements ItemFactory {



    @Override
    public Item createItem(String name, int price, int level, Type type, HashMap<String, Object> info) {
        switch (type) {
            case WEAPON:
                int weaponDamage = (int) info.getOrDefault("damage", 0);
                int handsRequired = (int) info.getOrDefault("handsRequired", 1);
                return new Weapon(name, price, level, weaponDamage, handsRequired);

            case ARMOR:
                int damageReduction = (int) info.getOrDefault("damageReduction", 0);
                return new Armor(name, price, level, damageReduction);

            case POTION:
                int effectAmount = (int) info.getOrDefault("effectAmount", 0);
                Potion.StatType statType = (Potion.StatType) info.getOrDefault("statType", Potion.StatType.HP);
                return new Potion(name, price, level, effectAmount, statType);

            case SPELL:
                int spellDamage = (int) info.getOrDefault("damage", 0);
                int manaCost = (int) info.getOrDefault("manaCost", 0);
                Spell.SpellType spellType = (Spell.SpellType) info.getOrDefault("spellType", Spell.SpellType.FIRE);
                return new Spell(name, price, level, spellDamage, manaCost, spellType);

            default:
                throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }
}

