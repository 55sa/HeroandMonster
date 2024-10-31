package Controller;

import Entity.Human.Hero;
import Entity.Items.Item;
import Entity.Items.Spell;
import Entity.Items.Type;
import Entity.Monster.Monster;
import Entity.Team;
import Repository.*;
import Util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleEvent {

    private CharacterFactory characterFactory = new CharacterFactoryImp();
    private ItemFactory itemFactory = new ItemFactoryImp();
    private Event<Hero, Monster> heroEvent = new HeroEventImp();
    private Event<Monster, Hero> monsterEvent = new MonsterEventImp();
    private Scanner scanner = new Scanner(System.in);

    // Method to start a battle between hero and monster teams
    public void startBattle(Team<Hero> heroTeam, Team<Monster> monsterTeam) {
        System.out.println("The battle begins!");

        int heroIndex = 0; // Start from the first hero
        int monsterIndex = 0; // Start from the first monster

        // Loop through heroes and monsters until one team wins
        while (heroIndex < heroTeam.getTeams().size() && monsterIndex < monsterTeam.getTeams().size()) {
            Hero hero = heroTeam.getTeams().get(heroIndex);
            Monster monster = monsterTeam.getTeams().get(monsterIndex);

            if (hero.isAlive() && monster.isAlive()) {
                // Hero's turn
                heroTurn(hero, monster);

                // Check if monster is defeated
                if (!monster.isAlive()) {
                    System.out.println(monster.getName() + " is defeated!");
                    monsterIndex++;  // Move to the next monster
                    if (heroEvent.win(monsterTeam)) {
                        System.out.println("Heroes win the battle!");
                        rewardHeroes(heroTeam);  // Reward heroes
                        return;  // Exit the battle immediately if heroes win
                    }
                    continue;  // Skip to the next loop iteration
                }

                // Monster's turn
                monsterEvent.attack(hero, monster);

                // Check if hero is defeated
                if (!hero.isAlive()) {
                    System.out.println(hero.getName() + " is defeated!");
                    heroIndex++;  // Move to the next hero
                    if (monsterEvent.win(heroTeam)) {
                        System.out.println("Monsters win the battle. Game Over!");
                        System.exit(0);  // Exit the battle immediately if monsters win
                    }
                }
            }
        }
    }






    // Reward heroes after a victory
    private void rewardHeroes(Team<Hero> heroTeam) {
        int expReward = 100;
        int goldReward = 50;

        System.out.println("Distributing rewards to surviving heroes...");

        for (Hero hero : heroTeam.getTeams()) {
            if (hero.isAlive()) {
                hero.setHP((int) (hero.getHP() * 1.1));
                hero.setMP((int) (hero.getMP() * 1.1));
                hero.addExp(expReward);
                hero.setGold(hero.getGold() + goldReward);
                System.out.println(hero.getName() + " receives " + expReward + " EXP and " + goldReward + " gold.");
            } else {
                hero.revive();
            }
        }
    }

    // Selects an alive monster from the monster team
    private Monster selectAliveMonster(Team<Monster> monsterTeam) {
        for (Monster monster : monsterTeam.getTeams()) {
            if (monster.isAlive()) {
                return monster;
            }
        }
        return null;
    }

    // Selects an alive hero from the hero team
    private Hero selectAliveHero(Team<Hero> heroTeam) {
        for (Hero hero : heroTeam.getTeams()) {
            if (hero.isAlive()) {
                return hero;
            }
        }
        return null;
    }

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

            // Use utils function for input validation
            int choice = Utils.getIntInRange("Enter a number (1-6): ", 1, 6); // Assume Utils has this input method

            switch (choice) {
                case 1:
                    heroEvent.attack(monster, hero);
                    opt = 1;
                    break;
                case 2:
                    useSpell(hero, monster);
                    opt = 2;
                    break;
                case 3:
                    usePotion(hero);
                    opt = 3;
                    break;
                case 4:
                    useWeapon(hero);
                    opt = 4;
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

    // Method to let heroes cast spells on monsters with mana check
    private void useSpell(Hero hero, Monster target) {
        List<Item> spells = new ArrayList<>();
        List<Item> inventory = hero.getItems();

        for (Item item : inventory) {
            if (item.getType().equals(Type.SPELL)) {
                spells.add(item);
            }
        }

        if (spells.isEmpty()) {
            System.out.println("No spells available.");
            return;
        }

        int choice;
        do {
            System.out.println("Select a spell to use:");
            for (int i = 0; i < spells.size(); i++) {
                System.out.println((i + 1) + ". " + spells.get(i).getName() +
                        " (Mana Cost: " + ((Spell) spells.get(i)).getManaCost() + ")");
            }

            choice = scanner.nextInt();

            if (choice > 0 && choice <= spells.size()) {
                Spell selectedSpell = (Spell) spells.get(choice - 1);

                if (hero.getMP() >= selectedSpell.getManaCost()) {
                    heroEvent.castSpell(target, selectedSpell, hero);
                } else {
                    System.out.println("Insufficient mana to cast " + selectedSpell.getName() +
                            ". Please select another action.");
                    choice = 0;
                }
            } else {
                System.out.println("Invalid choice. No spell used.");
            }
        } while (choice <= 0 || choice > spells.size());
    }

    private void usePotion(Hero hero) {
        List<Item> potions = new ArrayList<>();
        List<Item> inventory = hero.getItems();

        for (Item item : inventory) {
            if (item.getType().equals(Type.POTION)) {
                potions.add(item);
            }
        }

        if (potions.isEmpty()) {
            System.out.println("No potions available.");
            return;
        }

        int choice = 0;
        while (choice == 0 || choice < 0 || choice > potions.size()) {
            System.out.println("Select a potion to use:");
            for (int i = 0; i < potions.size(); i++) {
                System.out.println((i + 1) + ". " + potions.get(i).getName());
            }

            choice = scanner.nextInt();

            if (choice > 0 && choice <= potions.size()) {
                Item selectedPotion = potions.get(choice - 1);
                heroEvent.usePotion(hero, selectedPotion, hero);
            } else {
                System.out.println("Invalid choice. No potion used.");
            }
        }
    }

    private void useArmor(Hero hero) {
        List<Item> armors = new ArrayList<>();
        List<Item> inventory = hero.getItems();

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

            if (choice > 0 && choice <= armors.size()) {
                Item selectedArmor = armors.get(choice - 1);
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

            if (choice > 0 && choice <= weapons.size()) {
                Item selectedWeapon = weapons.get(choice - 1);
                System.out.println("Select hand to equip (L for left, R for right):");
                hand = scanner.next().toUpperCase().charAt(0);

                if (hand == 'L' || hand == 'R') {
                    hero.equipWeapon(selectedWeapon, hand);
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
