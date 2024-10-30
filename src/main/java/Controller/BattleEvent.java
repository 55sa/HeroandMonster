package Controller;

import Entity.Human.Hero;
import Entity.Items.Item;
import Entity.Items.Spell;
import Entity.Items.Type;
import Entity.Monster.Monster;
import Repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleEvent {

  private   CharacterFactory characterFactory=new CharacterFactoryImp();
  private   ItemFactory itemFactory = new ItemFactoryImp();

  private   Event<Hero, Monster> heroEvent = new HeroEventImp();

  private   Event<Monster, Hero> monsterEvent = new MonsterEventImp();

  private Scanner scanner = new Scanner(System.in);



    private void heroTurn(Hero hero, Monster monster) {
        int opt = 0;

        while ((opt == 0 || opt >= 6) && (hero.isAlive() && monster.isAlive())) {
            System.out.println(hero.getName() + "'s turn. Choose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Cast Spell");
            System.out.println("3. Use Potion");
            System.out.println("4. Equip Weapon");
            System.out.println("5. Equip Armor");
            System.out.println("6. Check Attributes");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    heroEvent.attack(monster, hero);
                    monsterEvent.attack(hero, monster);
                    opt = 1;
                    break;
                case 2:
                    useSpell(hero, monster);
                    opt = 2;
                    monsterEvent.attack(hero, monster);
                    break;
                case 3:
                    usePotion(hero);
                    opt = 3;
                    monsterEvent.attack(hero, monster);
                    break;
                case 4:
                    useWeapon(hero);
                    opt = 4;
                    monsterEvent.attack(hero, monster);
                    break;
                case 5:
                    useArmor(hero);
                    opt = 5;
                    break;
                case 6:
                    checkAttributes(hero);
                    opt = 6;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void checkAttributes(Hero hero) {
        System.out.println("Hero Attributes:");
        System.out.println("Health: " + hero.getHP());
        System.out.println("Mana: " + hero.getMP());
        System.out.println("Strength: " + hero.getStrength());
        System.out.println("Dexterity: " + hero.getDexterity());
        System.out.println("Agility: " + hero.getAgility());
        System.out.println("Equipped Weapon Left Hand: " + (hero.getEquipment().getLeftHand() != null ? hero.getEquipment().getLeftHand().getName() : "None"));
        System.out.println("Equipped Weapon Right Hand: " + (hero.getEquipment().getRightHand() != null ? hero.getEquipment().getRightHand().getName() : "None"));
        System.out.println("Equipped Armor: " + (hero.getEquipment().getArmor() != null ? hero.getEquipment().getArmor().getName() : "None"));
    }

    private void useSpell(Hero hero, Monster target) {
        List<Item> spells = new ArrayList<>();
        List<Item> inventory = hero.getItems();

        // Gather all spells from hero's inventory
        for (Item item : inventory) {
            if (item.getType().equals(Type.SPELL)) {
                spells.add(item);
            }
        }

        if (spells.isEmpty()) {
            System.out.println("No spells available.");
            return;
        }

        // Display available spells for the user to choose
        int choice = 0;
        while(choice == 0 || choice < 0 || choice > spells.size()){
        System.out.println("Select a spell to use:");
        for (int i = 0; i < spells.size(); i++) {
            System.out.println((i + 1) + ". " + spells.get(i).getName());
        }


         choice = scanner.nextInt();

        // Check if the user's choice is valid
        if (choice > 0 && choice <= spells.size()) {
            Spell selectedSpell = (Spell) spells.get(choice - 1);
            heroEvent.castSpell(target, selectedSpell, hero);
        } else {
            System.out.println("Invalid choice. No spell used.");
        }}
    }

    private void usePotion(Hero hero) {
        List<Item> potions = new ArrayList<>();
        List<Item> inventory = hero.getItems();

        // Gather all potions from hero's inventory
        for (Item item : inventory) {
            if (item.getType().equals(Type.POTION)) {
                potions.add(item);
            }
        }

        if (potions.isEmpty()) {
            System.out.println("No potions available.");
            return;
        }

        // Display available potions for the user to choose
        int choice = 0;
        while(choice == 0 || choice < 0 || choice > potions.size()){
        System.out.println("Select a potion to use:");
        for (int i = 0; i < potions.size(); i++) {
            System.out.println((i + 1) + ". " + potions.get(i).getName());
        }

         choice = scanner.nextInt();

        // Check if the user's choice is valid
        if (choice > 0 && choice <= potions.size()) {
            Item selectedPotion = potions.get(choice - 1);
            heroEvent.usePotion(hero, selectedPotion, hero); // Apply the potion effect
        } else {
            System.out.println("Invalid choice. No potion used.");
        }
        }
    }


    private void useArmor(Hero hero) {
        List<Item> armors = new ArrayList<>();
        List<Item> inventory = hero.getItems();

        // Gather all armors from hero's inventory
        for (Item item : inventory) {
            if (item.getType().equals(Type.ARMOR)) {
                armors.add(item);
            }
        }

        if (armors.isEmpty()) {
            System.out.println("No armors available.");
            return;
        }

        int choice = 0;
        while (choice <= 0 || choice > armors.size()) {
            System.out.println("Select an armor to equip:");
            for (int i = 0; i < armors.size(); i++) {
                System.out.println((i + 1) + ". " + armors.get(i).getName());
            }

            choice = scanner.nextInt();

            // Check if the user's choice is valid
            if (choice > 0 && choice <= armors.size()) {
                Item selectedArmor =  armors.get(choice - 1);
                hero.equipArmor(selectedArmor);
                System.out.println(hero.getName() + " has equipped " + selectedArmor.getName() + ".");
            } else {
                System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private void useWeapon(Hero hero) {
        List<Item> weapons = new ArrayList<>();
        List<Item> inventory = hero.getItems();

        // Gather all weapons from hero's inventory
        for (Item item : inventory) {
            if (item.getType().equals(Type.WEAPON)) {
                weapons.add(item);
            }
        }

        if (weapons.isEmpty()) {
            System.out.println("No weapons available.");
            return;
        }

        int choice = 0;
        char hand = 'R';
        while (choice <= 0 || choice > weapons.size()) {
            System.out.println("Select a weapon to equip:");
            for (int i = 0; i < weapons.size(); i++) {
                System.out.println((i + 1) + ". " + weapons.get(i).getName());
            }

            choice = scanner.nextInt();

            // Check if the user's choice is valid
            if (choice > 0 && choice <= weapons.size()) {
                Item selectedWeapon = weapons.get(choice - 1);

                // Prompt for hand selection (L or R)
                System.out.println("Select hand to equip (L for left, R for right):");
                hand = scanner.next().toUpperCase().charAt(0);

                if (hand == 'L' || hand == 'R') {
                    hero.equipWeapon(selectedWeapon, hand); // Equip weapon in specified hand
                    System.out.println(hero.getName() + " has equipped " + selectedWeapon.getName() + " in the " + (hand == 'L' ? "left" : "right") + " hand.");
                } else {
                    System.out.println("Invalid hand selection. Please enter 'L' for left or 'R' for right.");
                }
            } else {
                System.out.println("Invalid choice. Please select again.");
            }
        }
    }






}
