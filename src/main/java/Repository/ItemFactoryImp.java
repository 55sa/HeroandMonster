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
                switch (statType) {
                    case HP:
                        return new HpPotion(name, price, level, effectAmount);
                    case MP:
                        return new MpPotion(name, price, level, effectAmount);
                    case STRENGTH:
                        return new StrengthPotion(name, price, level, effectAmount);
                    case DEXTERITY:
                        return new DexterityPotion(name, price, level, effectAmount);
                    case AGILITY:
                        return new AgilityPotion(name, price, level, effectAmount);
                    default:
                        throw new IllegalArgumentException("Unknown potion type: " + statType);
                }

            case SPELL:
                int spellDamage = (int) info.getOrDefault("damage", 0);
                int manaCost = (int) info.getOrDefault("manaCost", 0);
                Spell.SpellType spellType = (Spell.SpellType) info.get("spellType");
                switch (spellType) {
                    case FIRE:
                        return new FireSpell(name, price, level, spellDamage, manaCost);
                    case ICE:
                        return new IceSpell(name, price, level, spellDamage, manaCost);
                    case LIGHTNING:
                        return new LightSpell(name, price, level, spellDamage, manaCost);
                    default:
                        throw new IllegalArgumentException("Unknown spell type: " + spellType);
                }

            default:
                throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }
}

