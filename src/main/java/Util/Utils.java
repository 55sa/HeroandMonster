package Util;

import Entity.Human.Hero;
import Entity.Monster.Monster;
import Entity.Team;
import Repository.CharacterFactory;
import Repository.CharacterFactoryImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//tools for the whole program
public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    private static CharacterFactory characterFactory =new CharacterFactoryImp();

    private static Random random =new Random();

    /**
     * Validates an integer input within a specific range.
     *
     * @param prompt the message to display to the user
     * @param min    the minimum valid value (inclusive)
     * @param max    the maximum valid value (inclusive)
     * @return a valid integer within the specified range
     */
    public static int getIntInRange(String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Validates string input against a set of allowed characters or strings.
     *
     * @param prompt      the message to display to the user
     * @param validInputs an array of allowed inputs (e.g., ["Y", "N"])
     * @return a valid string input from the allowed options
     */
    public static String getStringFromOptions(String prompt, String[] validInputs) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toUpperCase();
            for (String validInput : validInputs) {
                if (input.equals(validInput.toUpperCase())) {
                    return input;
                }
            }
            System.out.printf("Invalid input. Please enter one of the following options: %s%n", String.join(", ", validInputs));
        }
    }

    /**
     * Validates a yes/no input, returning true for 'Y' and false for 'N'.
     *
     * @param prompt the message to display to the user
     * @return true if the user enters 'Y', false if 'N'
     */
    public static boolean getYesNo(String prompt) {
        String[] validInputs = {"Y", "N"};
        String input = getStringFromOptions(prompt, validInputs);
        return input.equals("Y");
    }


    public static Team<Hero> createHeroes() {
        List<Hero> heroes = new ArrayList<>();

        // Validate the number of heroes input
        int numHeroes = Utils.getIntInRange(
                "Enter the number of heroes you want in your team (1 to 3): ", 1, 3
        );

        int createdHeroes = 0;

        while (createdHeroes < numHeroes) {
            System.out.println("Creating Hero " + (createdHeroes + 1));
            System.out.print("Enter hero name: ");
            String name = scanner.nextLine().trim();

            // Validate class type
            String classType = Utils.getStringFromOptions(
                    "Choose hero class (Warrior, Sorcerer, Paladin): ", new String[]{"Warrior", "Sorcerer", "Paladin"}
            );

            // Create hero using the factory
            Hero hero = characterFactory.createHero(classType, name);
            if (hero != null) {
                heroes.add(hero);
                System.out.println("Hero " + hero.getName() + " of class " + classType + " created successfully!\n");
                createdHeroes++;
            } else {
                System.out.println("Invalid class type entered. Please choose a valid class (Warrior, Sorcerer, Paladin).");
            }
        }
        return new Team<>(heroes);
    }

    public static Team<Monster> createMonsterTeam(Team<Hero> heroTeam) {
        int highestHeroLevel = heroTeam.getTeams().stream()
                .mapToInt(Hero::getLevel)
                .max()
                .orElse(1); // Default to level 1 if no heroes exist

        List<Monster> monsters = new ArrayList<>();
        String[] monsterTypes = {"dragon", "exoskeleton", "spirit"};

        for (int i = 0; i < heroTeam.getTeams().size(); i++) {
            String monsterType = monsterTypes[random.nextInt(monsterTypes.length)];
            String monsterName = "M" + (i + 1); // Name monsters for uniqueness
            Monster monster = characterFactory.createMonster(monsterType, monsterName, highestHeroLevel);
            if (monster != null) {
                monsters.add(monster);
            }
        }

        return new Team<>(monsters);
    }

    public static   void displayInfo(Team<Hero> heroTeam) {
        System.out.println("Heroes:");
        for (Hero hero : heroTeam.getTeams()) {
            System.out.println("Name: " + hero.getName() + ", Level: " + hero.getLevel() + ", HP: " + hero.getHP() + ", MP: " + hero.getMP());
        }

    }
}
