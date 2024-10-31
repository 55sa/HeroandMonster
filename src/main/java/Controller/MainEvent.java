package Controller;


import Entity.Board.HeroBoard;
import Entity.Board.State;
import Entity.Human.Hero;
import Entity.Market.Market;
import Entity.Monster.Monster;
import Entity.Team;
import Repository.*;
import Util.Utils;

import java.util.*;

public class MainEvent {
    private HeroBoard heroBoard = new HeroBoard(8);
    private Event<Hero, Monster> heroEvent = new HeroEventImp();
    private Event<Monster, Hero> monsterEvent = new MonsterEventImp();
    private MarketEvent marketEvent = new MarketEvent();
    private BattleEvent battleEvent = new BattleEvent();
    private Scanner scanner = new Scanner(System.in);

    private State pre = heroBoard.getCell(7, 0).getState();


    private Random random =new Random();

    private Team<Hero> heroTeam;

    private CharacterFactory characterFactory = new CharacterFactoryImp();

    private int heroRow = 7; // Starting row position of the hero
    private int heroCol = 0; // Starting column position of the hero

    // Display game controls for user
    public void displayInstructions() {
        System.out.println("Game Instructions:");
        System.out.println("W/w: Move up");
        System.out.println("A/a: Move left");
        System.out.println("S/s: Move down");
        System.out.println("D/d: Move right");
        System.out.println("Q/q: Quit game");
        System.out.println("I/i: Show hero and monster info");
        System.out.println("M/m: Enter market (when on a market tile)");
        System.out.println("Create heroes and explore the world. Enjoy the game!");
    }

    // Display information about heroes
    private  void displayInfo(Team<Hero> heroTeam) {
        System.out.println("Heroes:");
        for (Hero hero : heroTeam.getTeams()) {
            System.out.println("Name: " + hero.getName() + ", Level: " + hero.getLevel() + ", HP: " + hero.getHP() + ", MP: " + hero.getMP());
        }

    }

    // Method to update the hero's position on the board
    private void updateHeroPosition(int newRow, int newCol) {
        // Reset the hero's previous position to a common state

        heroBoard.getCell(heroRow, heroCol).setState(pre);
        pre = heroBoard.getCell(newRow, newCol).getState();

        // Update hero's current row and column position
        heroRow = newRow;
        heroCol = newCol;

        // Set the new position to HERO state
        heroBoard.getCell(heroRow, heroCol).setState(State.HERO);

        // Print the updated board to reflect the hero's new position
        heroBoard.print();
    }


    // Handle hero movement and potential encounters
    private void moveAndCheckForEncounter(int newRow, int newCol) {
        if(heroBoard.getCell(newRow,newCol).getState()==State.COMMON){
        updateHeroPosition(newRow, newCol);
        if (new Random().nextDouble() < 0.3) { // 30% chance for encounter
            System.out.println("A wild monster appears!");
            Team<Monster> monsterTeam = createMonsterTeam(heroTeam);
            battleEvent.startBattle(heroTeam, monsterTeam);
        }}
        else if(heroBoard.getCell(newRow,newCol).getState()==State.INACCESSIBLE){
            System.out.println("You can not cross the wall!");
        }
        else if(heroBoard.getCell(newRow,newCol).getState()==State.MARKET){
            updateHeroPosition(newRow, newCol);
        }
    }




    public MainEvent() {
    }


    // Method to create heroes using CharacterFactory
    private Team<Hero> createHeroes() {
        List<Hero> heroes = new ArrayList<>();

        // Validate the number of heroes input
        int numHeroes = Utils.getIntInRange(
                "Enter the number of heroes you want in your team (1 to 3): ", 1, 3
        );

        int createdHeroes = 0;

        while (createdHeroes < numHeroes) {
            System.out.println("Creating Hero " + (createdHeroes + 1));
            System.out.print("Enter hero name: ");
            String name = scanner.next();

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


    // Creates a team of monsters based on the hero team's highest level
    private Team<Monster> createMonsterTeam(Team<Hero> heroTeam) {
        int highestHeroLevel = heroTeam.getTeams().stream()
                .mapToInt(Hero::getLevel)
                .max()
                .orElse(1); // Default to level 1 if no heroes exist

        List<Monster> monsters = new ArrayList<>();
        String[] monsterTypes = {"dragon", "exoskeleton", "spirit"};

        for (int i = 0; i < heroTeam.getTeams().size(); i++) {
            String monsterType = monsterTypes[random.nextInt(monsterTypes.length)];
            String monsterName = monsterType + (i + 1); // Name monsters for uniqueness
            Monster monster = characterFactory.createMonster(monsterType, monsterName, highestHeroLevel);
            if (monster != null) {
                monsters.add(monster);
            }
        }

        return new Team<>(monsters);
    }




     char choice = '@';
    public void Start() {
        System.out.println("Welcome to Hero and Monster!");

        // Step 1: Create hero team
        heroTeam = createHeroes();

        // Step 2: Place heroes on the board and display the initial setup
        heroBoard.getCell(heroRow, heroCol).setState(State.HERO); // Starting position


        // Main game loop
        while (choice != 'q') {
            heroBoard.print();
            displayInstructions();
            System.out.print("Enter a command: ");
            choice = scanner.next().toLowerCase(Locale.ROOT).charAt(0);

            switch (choice) {
                case 'w': // Move up
                    if (heroRow > 0) {
                        moveAndCheckForEncounter(heroRow - 1, heroCol);
                    } else {
                        System.out.println("Cannot move up.");
                    }
                    break;
                case 'a': // Move left
                    if (heroCol > 0) {
                        moveAndCheckForEncounter(heroRow, heroCol - 1);
                    } else {
                        System.out.println("Cannot move left.");
                    }
                    break;
                case 's': // Move down
                    if (heroRow < 7) {
                        moveAndCheckForEncounter(heroRow + 1, heroCol);
                    } else {
                        System.out.println("Cannot move down.");
                    }
                    break;
                case 'd': // Move right
                    if (heroCol < 7) {
                        moveAndCheckForEncounter(heroRow, heroCol + 1);
                    } else {
                        System.out.println("Cannot move right.");
                    }
                    break;
                case 'i': // Show hero information
                    displayInfo(heroTeam);
                    break;
                case 'm': // Enter market if on a market tile
                    if (heroBoard.getCell(heroRow, heroCol).getPiece() != null &&
                            heroBoard.getCell(heroRow, heroCol).getPiece().getEvent() instanceof Market) {
                        marketEvent.action((Market) heroBoard.getCell(heroRow, heroCol).getPiece().getEvent(), heroTeam);
                    } else {
                        System.out.println("No market at this location.");
                    }
                    break;
                case 'q': // Quit the game
                    System.out.println("Thank you for playing! Exiting game.");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }


}
