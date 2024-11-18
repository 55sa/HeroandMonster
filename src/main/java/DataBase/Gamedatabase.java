package DataBase;

import Entity.Items.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Item database used to store in market
public class Gamedatabase {
    // Static list to store all items
    public static final List<Item> ITEMS = new ArrayList<>();
    private static final Random RANDOM = new Random();

    static {
        initializeArmors();
        initializeSpells();
        initializeWeapons();
        initializePotions();
    }

    private static void initializeArmors() {
        ITEMS.add(new Armor("Platinum_Shield", 150, 1, 200));
        ITEMS.add(new Armor("Breastplate", 350, 3, 600));
        ITEMS.add(new Armor("Full_Body_Armor", 1000, 8, 1100));
        ITEMS.add(new Armor("Wizard_Shield", 1200, 10, 1500));
        ITEMS.add(new Armor("Guardian_Angel", 1000, 10, 1000));

        for (int i = 0; i < 20; i++) {
            ITEMS.add(new Armor("Armor_" + (i + 1), 100 + RANDOM.nextInt(900), RANDOM.nextInt(10) + 1, 150 + RANDOM.nextInt(1500)));
        }
    }

    private static void initializeSpells() {
        // Fire Spells
        ITEMS.add(new FireSpell("Flame_Tornado", 700, 4, 850, 300));
        ITEMS.add(new FireSpell("Breath_of_Fire", 350, 1, 450, 100));
        ITEMS.add(new FireSpell("Heat_Wave", 450, 2, 600, 150));
        ITEMS.add(new FireSpell("Lava_Comet", 800, 7, 1000, 550));
        ITEMS.add(new FireSpell("Hell_Storm", 600, 3, 950, 600));

        for (int i = 0; i < 20; i++) {
            ITEMS.add(new FireSpell("FireSpell_" + (i + 1), 200 + RANDOM.nextInt(800), RANDOM.nextInt(10) + 1, 300 + RANDOM.nextInt(700), 100 + RANDOM.nextInt(500)));
        }

        // Ice Spells
        ITEMS.add(new IceSpell("Snow_Cannon", 500, 2, 650, 250));
        ITEMS.add(new IceSpell("Ice_Blade", 250, 1, 450, 100));
        ITEMS.add(new IceSpell("Frost_Blizzard", 750, 5, 850, 350));
        ITEMS.add(new IceSpell("Arctic_Storm", 700, 6, 800, 300));

        for (int i = 0; i < 20; i++) {
            ITEMS.add(new IceSpell("IceSpell_" + (i + 1), 200 + RANDOM.nextInt(800), RANDOM.nextInt(10) + 1, 300 + RANDOM.nextInt(700), 100 + RANDOM.nextInt(500)));
        }

        // Lightning Spells
        ITEMS.add(new LightSpell("Lightning_Dagger", 400, 1, 500, 150));
        ITEMS.add(new LightSpell("Thunder_Blast", 750, 4, 950, 400));
        ITEMS.add(new LightSpell("Electric_Arrows", 550, 5, 650, 200));
        ITEMS.add(new LightSpell("Spark_Needles", 500, 2, 600, 200));

        for (int i = 0; i < 20; i++) {
            ITEMS.add(new LightSpell("LightningSpell_" + (i + 1), 200 + RANDOM.nextInt(800), RANDOM.nextInt(10) + 1, 300 + RANDOM.nextInt(700), 100 + RANDOM.nextInt(500)));
        }
    }

    private static void initializeWeapons() {
        ITEMS.add(new Weapon("Sword", 500, 1, 800, 1));
        ITEMS.add(new Weapon("Bow", 300, 2, 500, 2));
        ITEMS.add(new Weapon("Scythe", 1000, 6, 1100, 2));
        ITEMS.add(new Weapon("Axe", 550, 5, 850, 1));
        ITEMS.add(new Weapon("TSwords", 1400, 8, 1600, 2));
        ITEMS.add(new Weapon("Dagger", 200, 1, 250, 1));

        for (int i = 0; i < 20; i++) {
            ITEMS.add(new Weapon("Weapon_" + (i + 1), 100 + RANDOM.nextInt(1300), RANDOM.nextInt(10) + 1, 200 + RANDOM.nextInt(1500), 1 + RANDOM.nextInt(2)));
        }
    }

    private static void initializePotions() {
        ITEMS.add(new HpPotion("Health Potion", 50, 1, 100));
        ITEMS.add(new MpPotion("Mana Potion", 40, 1, 100));
        ITEMS.add(new StrengthPotion("Strength Elixir", 60, 2, 10));
        ITEMS.add(new DexterityPotion("Dexterity Serum", 70, 2, 15));
        ITEMS.add(new AgilityPotion("Agility Boost", 80, 3, 20));

        for (int i = 0; i < 20; i++) {
            Potion.StatType randomStatType = Potion.StatType.values()[RANDOM.nextInt(Potion.StatType.values().length)];
            switch (randomStatType) {
                case HP:
                    ITEMS.add(new HpPotion("Potion_HP_" + (i + 1), 30 + RANDOM.nextInt(80), RANDOM.nextInt(10) + 1, 5 + RANDOM.nextInt(95)));
                    break;
                case MP:
                    ITEMS.add(new MpPotion("Potion_MP_" + (i + 1), 30 + RANDOM.nextInt(80), RANDOM.nextInt(10) + 1, 5 + RANDOM.nextInt(95)));
                    break;
                case STRENGTH:
                    ITEMS.add(new StrengthPotion("Potion_Strength_" + (i + 1), 30 + RANDOM.nextInt(80), RANDOM.nextInt(10) + 1, 5 + RANDOM.nextInt(95)));
                    break;
                case DEXTERITY:
                    ITEMS.add(new DexterityPotion("Potion_Dexterity_" + (i + 1), 30 + RANDOM.nextInt(80), RANDOM.nextInt(10) + 1, 5 + RANDOM.nextInt(95)));
                    break;
                case AGILITY:
                    ITEMS.add(new AgilityPotion("Potion_Agility_" + (i + 1), 30 + RANDOM.nextInt(80), RANDOM.nextInt(10) + 1, 5 + RANDOM.nextInt(95)));
                    break;
            }
        }
    }

}
