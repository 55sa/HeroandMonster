package DataBase;

import Entity.Items.Item;
import Entity.Items.Armor;
import Entity.Items.Spell;
import Entity.Items.Weapon;
import Entity.Items.Potion;

import java.util.ArrayList;
import java.util.List;

public class Gamedatabase {
    // Static list to store all items
    public static final List<Item> ITEMS = new ArrayList<>();

    static {
        initializeArmors();
        initializeIceSpells();
        initializeFireSpells();
        initializeLightningSpells();
        initializeWeapons();
        initializePotions();
    }

    private static void initializeArmors() {
        ITEMS.add(new Armor("Platinum_Shield", 150, 1, 200));
        ITEMS.add(new Armor("Breastplate", 350, 3, 600));
        ITEMS.add(new Armor("Full_Body_Armor", 1000, 8, 1100));
        ITEMS.add(new Armor("Wizard_Shield", 1200, 10, 1500));
        ITEMS.add(new Armor("Guardian_Angel", 1000, 10, 1000));
    }

    private static void initializeIceSpells() {
        ITEMS.add(new Spell("Snow_Cannon", 500, 2, 650, 250, Spell.SpellType.ICE));
        ITEMS.add(new Spell("Ice_Blade", 250, 1, 450, 100, Spell.SpellType.ICE));
        ITEMS.add(new Spell("Frost_Blizzard", 750, 5, 850, 350, Spell.SpellType.ICE));
        ITEMS.add(new Spell("Arctic_Storm", 700, 6, 800, 300, Spell.SpellType.ICE));
    }

    private static void initializeFireSpells() {
        ITEMS.add(new Spell("Flame_Tornado", 700, 4, 850, 300, Spell.SpellType.FIRE));
        ITEMS.add(new Spell("Breath_of_Fire", 350, 1, 450, 100, Spell.SpellType.FIRE));
        ITEMS.add(new Spell("Heat_Wave", 450, 2, 600, 150, Spell.SpellType.FIRE));
        ITEMS.add(new Spell("Lava_Comet", 800, 7, 1000, 550, Spell.SpellType.FIRE));
        ITEMS.add(new Spell("Hell_Storm", 600, 3, 950, 600, Spell.SpellType.FIRE));
    }

    private static void initializeLightningSpells() {
        ITEMS.add(new Spell("Lightning_Dagger", 400, 1, 500, 150, Spell.SpellType.LIGHTNING));
        ITEMS.add(new Spell("Thunder_Blast", 750, 4, 950, 400, Spell.SpellType.LIGHTNING));
        ITEMS.add(new Spell("Electric_Arrows", 550, 5, 650, 200, Spell.SpellType.LIGHTNING));
        ITEMS.add(new Spell("Spark_Needles", 500, 2, 600, 200, Spell.SpellType.LIGHTNING));
    }

    private static void initializeWeapons() {
        ITEMS.add(new Weapon("Sword", 500, 1, 800, 1));
        ITEMS.add(new Weapon("Bow", 300, 2, 500, 2));
        ITEMS.add(new Weapon("Scythe", 1000, 6, 1100, 2));
        ITEMS.add(new Weapon("Axe", 550, 5, 850, 1));
        ITEMS.add(new Weapon("TSwords", 1400, 8, 1600, 2));
        ITEMS.add(new Weapon("Dagger", 200, 1, 250, 1));
    }

    private static void initializePotions() {
        ITEMS.add(new Potion("Health Potion", 50, 1, 100, Potion.StatType.HP));
        ITEMS.add(new Potion("Mana Potion", 40, 1, 100, Potion.StatType.MP));
        ITEMS.add(new Potion("Strength Elixir", 60, 2, 10, Potion.StatType.STRENGTH));
        ITEMS.add(new Potion("Dexterity Serum", 70, 2, 15, Potion.StatType.DEXTERITY));
        ITEMS.add(new Potion("Agility Boost", 80, 3, 20, Potion.StatType.AGILITY));
    }
}
