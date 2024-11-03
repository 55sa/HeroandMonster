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

//Battle Page of the game
public class BattleEvent {

    private CharacterFactory characterFactory = new CharacterFactoryImp();
    private ItemFactory itemFactory = new ItemFactoryImp();
    private Event<Hero, Monster> heroEvent = new HeroEventImp();
    private Event<Monster, Hero> monsterEvent = new MonsterEventImp();
    private Scanner scanner = new Scanner(System.in);

    public void startBattle(Team<Hero> heroTeam, Team<Monster> monsterTeam) {
        System.out.println("The battle begins!");

        int heroIndex = 0;
        int monsterIndex = 0;

        while (heroIndex < heroTeam.getTeams().size() && monsterIndex < monsterTeam.getTeams().size()) {
            Hero hero = heroTeam.getTeams().get(heroIndex);
            Monster monster = monsterTeam.getTeams().get(monsterIndex);

            if (hero.isAlive() && monster.isAlive()) {
                heroTurn(hero, monster);

                if (!monster.isAlive()) {
                    System.out.println(monster.getName() + " is defeated!");
                    monsterIndex++;
                    if (heroEvent.win(monsterTeam)) {
                        System.out.println("Heroes win the battle!");
                        rewardHeroes(heroTeam);
                        return;
                    }
                    continue;
                }

                monsterEvent.attack(hero, monster);
                if (!hero.isAlive()) {
                    System.out.println(hero.getName() + " is defeated!");
                    heroIndex++;
                    if (monsterEvent.win(heroTeam)) {
                        System.out.println("Monsters win the battle. Game Over!");
                        System.exit(0);
                    }
                }
            }
        }
    }

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

    private void heroTurn(Hero hero, Monster monster) {
        boolean turnOver = false;

        while (!turnOver && hero.isAlive() && monster.isAlive()) {
            System.out.println(hero.getName() + "'s turn. Choose an action:");
            System.out.println("0. End Turn");
            System.out.println("1. Attack");
            System.out.println("2. Cast Spell");
            System.out.println("3. Use Potion");
            System.out.println("4. Equip Weapon");
            System.out.println("5. Equip Armor");
            System.out.println("6. Check Attributes");

            int choice = Utils.getIntInRange("Enter a number (0-6): ", 0, 6);

            switch (choice) {
                case 0:
                    System.out.println("Ending turn...");
                    turnOver = true;
                    break;
                case 1:
                    heroEvent.attack(monster, hero);
                    turnOver = true;
                    break;
                case 2:
                    if (!useSpell(hero, monster)) {
                        System.out.println("Spell not used.");
                    }
                    else {
                        turnOver = true;
                    }
                    break;
                case 3:
                    if (!usePotion(hero)) {
                        System.out.println("Potion not used.");
                    }
                    else {
                        turnOver = true;
                    }
                    break;
                case 4:
                    if (!useWeapon(hero)) {
                        System.out.println("Weapon not equipped.");
                    }
                    break;
                case 5:
                    if (!useArmor(hero)) {
                        System.out.println("Armor not equipped.");
                    }
                    break;
                case 6:
                    checkAttributes(hero);
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

    private boolean useSpell(Hero hero, Monster target) {
        List<Item> spells = new ArrayList<>();
        for (Item item : hero.getItems()) {
            if (item.getType().equals(Type.SPELL)) {
                spells.add(item);
            }
        }

        if (spells.isEmpty()) {
            System.out.println("No spells available.");
            return false;
        }

        int choice;
        do {
            System.out.println("Select a spell to use (0 to exit):");
            System.out.println("0. Exit");
            for (int i = 0; i < spells.size(); i++) {
                System.out.println((i + 1) + ". " + spells.get(i).getName() +
                        " (Mana Cost: " + ((Spell) spells.get(i)).getManaCost() + ")");
            }

            choice = Utils.getIntInRange("Enter a number: ", 0, spells.size());
            if (choice == 0) {
                System.out.println("Exiting spell selection.");
                return false;
            } else {
                Spell selectedSpell = (Spell) spells.get(choice - 1);
                if (hero.getMP() >= selectedSpell.getManaCost()) {
                    heroEvent.castSpell(target, selectedSpell, hero);
                    return true;
                } else {
                    System.out.println("Insufficient mana to cast " + selectedSpell.getName());
                }
            }
        } while (choice != 0);
        return false;
    }

    private boolean usePotion(Hero hero) {
        List<Item> potions = new ArrayList<>();
        for (Item item : hero.getItems()) {
            if (item.getType().equals(Type.POTION)) {
                potions.add(item);
            }
        }

        if (potions.isEmpty()) {
            System.out.println("No potions available.");
            return false;
        }

        int choice;
        do {
            System.out.println("Select a potion to use (0 to exit):");
            System.out.println("0. Exit");
            for (int i = 0; i < potions.size(); i++) {
                System.out.println((i + 1) + ". " + potions.get(i).getName());
            }

            choice = Utils.getIntInRange("Enter a number: ", 0, potions.size());
            if (choice == 0) {
                System.out.println("Exiting potion selection.");
                return false;
            } else {
                Item selectedPotion = potions.get(choice - 1);
                heroEvent.usePotion(hero, selectedPotion, hero);
                return true;
            }
        } while (choice != 0);
    }

    private boolean useArmor(Hero hero) {
        List<Item> armors = new ArrayList<>();
        for (Item item : hero.getItems()) {
            if (item.getType().equals(Type.ARMOR)) {
                armors.add(item);
            }
        }

        if (armors.isEmpty()) {
            System.out.println("No armors available.");
            return false;
        }

        int choice;
        do {
            System.out.println("Select an armor to equip (0 to exit):");
            System.out.println("0. Exit");
            for (int i = 0; i < armors.size(); i++) {
                System.out.println((i + 1) + ". " + armors.get(i).getName());
            }

            choice = Utils.getIntInRange("Enter a number: ", 0, armors.size());
            if (choice == 0) {
                System.out.println("Exiting armor selection.");
                return false;
            } else {
                Item selectedArmor = armors.get(choice - 1);
                hero.equipArmor(selectedArmor);
                System.out.println(hero.getName() + " has equipped " + selectedArmor.getName() + ".");
                return true;
            }
        } while (choice != 0);
    }

    private boolean useWeapon(Hero hero) {
        List<Item> weapons = new ArrayList<>();
        for (Item item : hero.getItems()) {
            if (item.getType().equals(Type.WEAPON)) {
                weapons.add(item);
            }
        }

        if (weapons.isEmpty()) {
            System.out.println("No weapons available.");
            return false;
        }

        int choice;
        do {
            System.out.println("Select a weapon to equip (0 to exit):");
            System.out.println("0. Exit");
            for (int i = 0; i < weapons.size(); i++) {
                System.out.println((i + 1) + ". " + weapons.get(i).getName());
            }

            choice = Utils.getIntInRange("Enter a number: ", 0, weapons.size());
            if (choice == 0) {
                System.out.println("Exiting weapon selection.");
                return false;
            } else {
                Item selectedWeapon = weapons.get(choice - 1);
                System.out.println("Select hand to equip (L for left, R for right):");
                char hand = scanner.next().toUpperCase().charAt(0);

                if (hand == 'L' || hand == 'R') {
                    hero.equipWeapon(selectedWeapon, hand);
                    System.out.println(hero.getName() + " has equipped " + selectedWeapon.getName() + " in the " + (hand == 'L' ? "left" : "right") + " hand.");
                    return true;
                } else {
                    System.out.println("Invalid hand selection. Please enter 'L' for left or 'R' for right.");
                }
            }
        } while (choice != 0);
        return false;
    }
}
