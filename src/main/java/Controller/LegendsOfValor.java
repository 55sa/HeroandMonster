package Controller;

import Entity.Board.*;
import Entity.Human.Hero;
import Entity.Market.Market;
import Entity.Monster.Monster;
import Entity.Team;
import Repository.Event;
import Repository.HeroEventImp;
import Repository.MonsterEventImp;
import Util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LegendsOfValor implements Game{

    Event heroEvent =new HeroEventImp();

    MarketEvent marketEvent =new MarketEvent();

    Event monsterEvent = new MonsterEventImp();
    private Team<Hero> heroTeam = new Team<>();

    private Team<Monster> monsterTeam =new Team<>();

   private   MainEvent HeroAndMonster =  new MainEvent();

   private MusicPlayer musicPlayer;

   private Board board = new LegendBoard();

   private boolean endTurn = false;

   private boolean heroWin = false;

   private boolean monsterWin = false;

   private HashMap<String, int[]> HeroBasePostion = new HashMap<>();

   private HashMap<String, int[]> MonsterBasePostion = new HashMap<>();

   private List<Monster> MonsterLivePool =new ArrayList<>();

   private List<Hero> HeroLivePool =new ArrayList<>();

   private HashMap<String, Integer> HeroDeadPool =new HashMap<>();

   private HashMap<String, Integer> MonsterDeadPool =new HashMap<>();

   private BattleEvent battleEvent =new BattleEvent();




    // Helper method to calculate column based on lane
    private int getLaneCol(int index) {
        if (index == 0) {
            return 0; // Top lane
        } else if (index == 1) {
            return 3; // Mid lane
        } else if (index == 2) {
            return 6; // Bottom lane
        } else {
            throw new IllegalArgumentException("Invalid index for lane calculation: " + index);
        }
    }


    public void Start() {
        musicPlayer = new MusicPlayer();  // Initialize the MusicPlayer
        musicPlayer.playMusic("DataBase/background-music.wav");
        System.out.println("Welcome to Legends Of Valor!");

        // Step 1: Create hero team
        heroTeam = Utils.createHeroes();
        initializeHeroPositions();

        // Step 2: Create monster team
        monsterTeam = Utils.createMonsterTeam(heroTeam);
        initializeMonsterPositions();

        // Display initial board setup
        board.print();

        // Main game loop
        while (!heroWin && !monsterWin) {
            for (Hero hero : HeroLivePool) {
                System.out.println("\nIt's " + hero.getName() + "'s turn.");
                heroTurn(hero);
                if (heroWin) {
                    System.out.println("Heroes have reached the monsters' Nexus! Heroes win!");
                    return; // End the game
                }
            }

            for (Monster monster : MonsterLivePool) {
                System.out.println("\nIt's " + monster.getName() + "'s turn.");
                monsterBehavior(monster);
                if (monsterWin) {
                    System.out.println("Monsters have reached the heroes' Nexus! Monsters win!");
                    return; // End the game
                }
            }

            // Optionally update the board after each round
            board.print();
        }
        musicPlayer.stopMusic();
    }

    // Initialize hero positions on the board
    private void initializeHeroPositions() {
        for (int i = 0; i < heroTeam.getTeams().size(); i++) {
            Hero hero = heroTeam.getTeams().get(i);
            HeroLivePool.add(hero);

            int heroRow = 7; // Heroes start at the bottom row
            int heroCol = getLaneCol(i); // Distribute heroes across lanes
            hero.setRow(heroRow);
            hero.setCol(heroCol);

            // Store hero's base position
            HeroBasePostion.put(hero.getName(), new int[]{heroRow, heroCol});

            // Place hero on the board
            HeroAndMonsterContainer container =
                    (HeroAndMonsterContainer) board.getCell(heroRow, heroCol).getPiece().getEvent();
            container.setHero(hero);
        }
    }

    // Initialize monster positions on the board
    private void initializeMonsterPositions() {
        for (int i = 0; i < monsterTeam.getTeams().size(); i++) {
            Monster monster = monsterTeam.getTeams().get(i);
            MonsterLivePool.add(monster);

            int monsterRow = 0; // Monsters start at the top row
            int monsterCol = getLaneCol(i); // Distribute monsters across lanes
            monster.setRow(monsterRow);
            monster.setCol(monsterCol);

            // Store monster's base position
            MonsterBasePostion.put(monster.getName(), new int[]{monsterRow, monsterCol});

            // Place monster on the board
            HeroAndMonsterContainer container =
                    (HeroAndMonsterContainer) board.getCell(monsterRow, monsterCol).getPiece().getEvent();
            container.setMonster(monster);
        }
    }


    @Override
    public void displayInstructions() {
        System.out.println("Hero Actions in Legends of Valor:");
        System.out.println("1. Change Weapon");
        System.out.println("2. Use a Potion");
        System.out.println("3. Attack");
        System.out.println("4. Cast a Spell");
        System.out.println("5. Move");
        System.out.println("6. Teleport");
        System.out.println("7. Recall");
        System.out.println("8. Change Armor");
        System.out.println("9. Enter Market");
    }



    @Override
    public void moveAndCheckForEncounter(int newRow, int newCol, Hero hero) {
        // Validate the move
        if (!isValidMove(newRow, newCol, hero)) {
            System.out.println("Invalid move. Please try again.");
            return;
        }

        // Update the hero's position on the board
        updateHeroPosition(newRow, newCol, hero);

        State curState = board.getCell(newRow, newCol).getState();

        if(curState == State.BUSH){
            hero.setDexterity((int) (hero.getDexterity() * 0.1+ hero.getDexterity()));
        }
        else if(curState == State.CAVE){
            hero.setAgility((int) (hero.getAgility() * 1.1));

        }
        else if(curState == State.Koulou){
            hero.setStrength((int) (hero.getStrength() * 1.1));

        }

        if (newRow == 0){
            heroWin = true;
        }

    }

    /**
     * Validates if the move is legal based on the game rules.
     */
    private boolean isValidMove(int newRow, int newCol, Hero hero) {
        int currentRow = hero.getRow();
        int currentCol = hero.getCol();

        System.out.println("Checking move for Hero " + hero.getName() + ": from (" + currentRow + "," + currentCol + ") to (" + newRow + "," + newCol + ")");

        // Check if the target cell is within bounds
        if (!board.isWithinBounds(newRow, newCol)) {
            System.out.println("Move is out of bounds.");
            return false;
        }

        // Check if the cell is inaccessible
        if (board.getCell(newRow, newCol).getState() == State.INACCESSIBLE) {
            System.out.println("This space is inaccessible.");
            return false;
        }

        // Check if the cell is occupied by another hero
        if (isCellOccupiedByHero(newRow, newCol)) {
            System.out.println("Another hero is already in this space. Move not allowed.");
            return false;
        }



        // Check if the hero is trying to move behind a monster
        if (isMovingBehindMonster(newRow, newCol, hero)) {
            System.out.println("Cannot move behind a monster without killing it.");
            return false;
        }



        return true;
    }


    /**
     * Checks if a target cell is occupied by another hero.
     */
    private boolean isCellOccupiedByHero(int row, int col) {
        // Ensure the cell is within bounds
        if (!board.isWithinBounds(row, col)) {
            return false; // Out of bounds, no hero
        }

        Boardcells cell = board.getCell(row, col);
        if (cell == null || cell.getPiece() == null) {
            return false;
        }

        // Retrieve the container at the cell
        HeroAndMonsterContainer container =
                (HeroAndMonsterContainer) board.getCell(row, col).getPiece().getEvent();



        // Check if a hero exists in the container
        return container != null && container.getHero() != null;
    }


    /**
     * Checks if the hero is trying to move behind a monster.
     */
    private boolean isMovingBehindMonster(int newRow, int newCol, Hero hero) {
        int curRow = hero.getRow();
        int curCol = hero.getCol();

      if(newRow < curRow){
          return isMonsterInCell(curRow, curCol) || // Current cell
                  isMonsterInCell(curRow, curCol - 1) || // Left cell
                  isMonsterInCell(curRow, curCol + 1);  // Right cell
      }
      return false;
    }

    /**
     * Checks if a specific cell is occupied by a monster.
     */
    private boolean isMonsterInCell(int row, int col) {
        if (!board.isWithinBounds(row, col)) {
            System.out.println("Cell (" + row + "," + col + ") is out of bounds.");
            return false;
        }

        Boardcells cell = board.getCell(row, col);
        if (cell == null || cell.getPiece() == null) {
            System.out.println("Cell (" + row + "," + col + ") is empty.");
            return false;
        }

        HeroAndMonsterContainer container =
                (HeroAndMonsterContainer) cell.getPiece().getEvent();
        boolean hasMonster = container != null && container.getMonster() != null;

        System.out.println("Cell (" + row + "," + col + ") has monster: " + hasMonster);
        return hasMonster;
    }




    @Override
    public void updateHeroPosition(int newRow, int newCol, Hero hero) {
        // Get the current position of the hero
        int curX = hero.getRow();
        int curY = hero.getCol();

        // Remove the hero from the current cell
        HeroAndMonsterContainer oldContainer =
                (HeroAndMonsterContainer) board.getCell(curX, curY).getPiece().getEvent();
        oldContainer.setHero(null);

        // Update the hero's position
        hero.setRow(newRow);
        hero.setCol(newCol);

        // Add the hero to the new cell
        HeroAndMonsterContainer newContainer =
                (HeroAndMonsterContainer) board.getCell(newRow, newCol).getPiece().getEvent();
        newContainer.setHero(hero);
    }

    public void move(Hero hero) {
        while (true) { // Loop until a valid move is made or the hero chooses to return
            // Display move options
            board.print();
            System.out.println();
            System.out.println("Choose a direction to move:");
            System.out.println("1. North");
            System.out.println("2. West");
            System.out.println("3. South");
            System.out.println("4. East");
            System.out.println("5. Return to the previous menu");


            int direction = Utils.getIntInRange("Enter direction (1-5): ", 1, 5);

            // Handle "Return" option
            if (direction == 5) {
                System.out.println("Returning to the previous menu.");
                return; // End move without ending the hero's turn
            }

            // Determine new position based on direction
            int currentRow = hero.getRow();
            int currentCol = hero.getCol();
            int newRow = currentRow;
            int newCol = currentCol;

            switch (direction) {
                case 1: // Move North
                    newRow = currentRow - 1;
                    break;
                case 2: // Move West
                    newCol = currentCol - 1;
                    break;
                case 3: // Move South
                    newRow = currentRow + 1;
                    break;
                case 4: // Move East
                    newCol = currentCol + 1;
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    continue;
            }



            // Check for state changes or encounters after moving
            moveAndCheckForEncounter(newRow, newCol, hero);

            if((hero.getRow() == newRow) && (hero.getCol() == newCol)){
                endTurn = true;
                break;
            }



        }
    }


    private List<Monster> getNeighborMonsters(int row, int col) {
        List<Monster> res = new ArrayList<>();

        // Define the four possible directions (north, west, south, east)
        int[][] directions = {
                {-1, 0}, // North
                {1, 0},  // South
                {0, -1}, // West
                {0, 1}   // East
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if the neighboring cell is within bounds
            if (board.isWithinBounds(newRow, newCol)) {
                Boardcells cell = board.getCell(newRow, newCol);

                // Ensure the cell is not inaccessible
                if (cell.getState() != State.INACCESSIBLE) {
                    HeroAndMonsterContainer piece = (HeroAndMonsterContainer) cell.getPiece().getEvent();
                    if (piece != null && piece.getMonster() != null) {
                        res.add(piece.getMonster());
                    }
                }
            }
        }

        return res;
    }

    private void monsterBehavior(Monster monster) {
        // Get the current position of the monster
        int currentRow = monster.getRow();
        int currentCol = monster.getCol();

        // Check for nearby heroes
        List<Hero> nearbyHeroes = getNeighborHeroes(currentRow, currentCol);
        if (!nearbyHeroes.isEmpty()) {
            // Attack the first hero in the list
            Hero target = nearbyHeroes.get(0);
            System.out.println(monster.getName() + " attacks " + target.getName() + "!");
            monsterEvent.attack(target, monster);

            // Check if the hero is defeated
            if (!target.isAlive()) {
                System.out.println(target.getName() + " is defeated!");
                removeHero(target);
            }
            return; // End the monster's turn after attacking
        }

        // If no heroes are nearby, attempt to move forward
        int newRow = currentRow + 1; // Monsters move south (toward the heroes' Nexus)

        // Validate move
        if (board.isWithinBounds(newRow, currentCol) &&
                board.getCell(newRow, currentCol).getState() != State.INACCESSIBLE) {

            // Check if the path is blocked
            boolean isBlockedByHero = isCellOccupiedByHero(currentRow, currentCol) ||
                    isCellOccupiedByHero(currentRow, currentCol - 1) ||
                    isCellOccupiedByHero(currentRow, currentCol + 1);

            // Check if the cell already contains another monster
            HeroAndMonsterContainer container = (HeroAndMonsterContainer) board.getCell(newRow, currentCol).getPiece().getEvent();

            boolean isOccupiedByMonster = container != null && container.getMonster() != null;

            if (!isBlockedByHero && !isOccupiedByMonster) {
                System.out.println(monster.getName() + " moves forward.");
                updateMonsterPosition(newRow, currentCol, monster);

                // Check if the monster reached the Nexus
                if (newRow == 7) {
                    System.out.println(monster.getName() + " has reached the heroes' Nexus! Monsters win!");
                    monsterWin = true; // Flag the game as over
                }
            } else {
                System.out.println(monster.getName() + " cannot move forward as the path is blocked.");
            }
        } else {
            System.out.println(monster.getName() + " cannot move forward due to an obstacle.");
        }
    }




    // Handle a hero's turn
    private void heroTurn(Hero hero) {
        while (!endTurn) {
            displayInstructions();
            int choice = Utils.getIntInRange("Choose an action (1-9): ", 1, 9);
            switch (choice) {
                case 1:
                    battleEvent.useWeapon(hero);
                    break;
                case 2:
                    endTurn = battleEvent.usePotion(hero);
                    break;
                case 3:
                    endTurn = attack(hero);
                    break;
                case 4:
                    endTurn = castSpell(hero);
                    break;
                case 5:
                    move(hero);
                    break;
                case 6:
                    // Teleport
                    break;
                case 7:
                    recallToNexus(hero);
                    endTurn = true;
                    break;
                case 8:
                    battleEvent.useArmor(hero);
                    break;
                case 9:
                    State curState = board.getCell(hero.getRow(), hero.getCol()).getState();
                    if(curState != State.NEXUS){
                        System.out.println("There is not Market Here, Go back to nexus");
                        break;
                    }
                    Market market = board.getCell(hero.getRow(), hero.getCol()).getMarket();
                    Team temTeam =new Team();
                    temTeam.addMember(hero);
                    marketEvent.action(market,temTeam);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        endTurn = false; // Reset for the next hero
    }



    private boolean attack(Hero hero) {
        List<Monster> nearbyMonsters = getNeighborMonsters(hero.getRow(), hero.getCol());
        if (nearbyMonsters.isEmpty()) {
            System.out.println("No monsters nearby to attack.");
            return false;
        }

        System.out.println("Choose a monster to attack:");
        for (int i = 0; i < nearbyMonsters.size(); i++) {
            System.out.println((i + 1) + ". " + nearbyMonsters.get(i).getName());
        }

        int choice = Utils.getIntInRange("Enter monster number: ", 1, nearbyMonsters.size());
        Monster target = nearbyMonsters.get(choice - 1);

        heroEvent.attack(target,hero);



        if (!target.isAlive()) {
            System.out.println(target.getName() + " is defeated!");
            removeMonster(target);
        }
        return true;
    }

    private boolean castSpell(Hero hero) {
        List<Monster> nearbyMonsters = getNeighborMonsters(hero.getRow(), hero.getCol());
        if (nearbyMonsters.isEmpty()) {
            System.out.println("No monsters nearby to cast Spell.");
            return false;
        }

        System.out.println("Choose a monster to cast spell:");
        for (int i = 0; i < nearbyMonsters.size(); i++) {
            System.out.println((i + 1) + ". " + nearbyMonsters.get(i).getName());
        }

        int choice = Utils.getIntInRange("Enter monster number: ", 1, nearbyMonsters.size());
        Monster target = nearbyMonsters.get(choice - 1);

      boolean res =  battleEvent.useSpell(hero,target);



        if (!target.isAlive()) {
            System.out.println(target.getName() + " is defeated!");
            removeMonster(target);
        }

        return res;
    }



    private void recallToNexus(Hero hero) {
        int[] basePos = HeroBasePostion.get(hero.getName());
        updateHeroPosition(basePos[0], basePos[1], hero);
        System.out.println(hero.getName() + " has recalled to their Nexus.");
    }

    private void removeMonster(Monster monster) {
        MonsterLivePool.remove(monster);
        MonsterDeadPool.put(monster.getName(), 6);
        HeroAndMonsterContainer container =
                (HeroAndMonsterContainer) board.getCell(monster.getRow(), monster.getCol()).getPiece().getEvent();
        container.setMonster(null);
    }

    private void removeHero(Hero hero) {
        HeroLivePool.remove(hero);
        HeroDeadPool.put(hero.getName(), 6);
        HeroAndMonsterContainer container =
                (HeroAndMonsterContainer) board.getCell(hero.getRow(), hero.getCol()).getPiece().getEvent();
        container.setHero(null);
    }



    private List<Hero> getNeighborHeroes(int row, int col) {
        List<Hero> heroes = new ArrayList<>();
        int[][] directions = {
                {-1, 0}, // North
                {1, 0},  // South
                {0, -1}, // West
                {0, 1}   // East
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            // Check bounds
            if (board.isWithinBounds(newRow, newCol)) {
                Boardcells cell = board.getCell(newRow, newCol);
                if (cell != null && cell.getPiece() != null) {
                    HeroAndMonsterContainer container =
                            (HeroAndMonsterContainer) cell.getPiece().getEvent();
                    if (container != null && container.getHero() != null) {
                        heroes.add(container.getHero());
                    }
                }
            }
        }
        return heroes;
    }


    private void updateMonsterPosition(int newRow, int newCol, Monster monster) {
        // Get the current position of the monster
        int curRow = monster.getRow();
        int curCol = monster.getCol();

        // Remove the monster from the current cell
        HeroAndMonsterContainer oldContainer =
                (HeroAndMonsterContainer) board.getCell(curRow, curCol).getPiece().getEvent();
        if (oldContainer != null) {
            oldContainer.setMonster(null);
        }

        // Update the monster's position
        monster.setRow(newRow);
        monster.setCol(newCol);

        // Add the monster to the new cell
        HeroAndMonsterContainer newContainer =
                (HeroAndMonsterContainer) board.getCell(newRow, newCol).getPiece().getEvent();
        if (newContainer != null) {
            newContainer.setMonster(monster);
        }
    }






}
