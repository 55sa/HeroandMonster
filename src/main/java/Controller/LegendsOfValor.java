package Controller;

import Entity.Board.*;
import Entity.Human.Hero;
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

    Event monsterEvent = new MonsterEventImp();
    private Team<Hero> heroTeam = new Team<>();

    private Team<Monster> monsterTeam =new Team<>();

   private   MainEvent HeroAndMonster =  new MainEvent();

   private Board board = new LegendBoard();

   private boolean endTurn = false;

   private boolean heroWin = false;

   private HashMap<String, int[]> HeroBasePostion = new HashMap<>();

   private HashMap<String, int[]> MonsterBasePostion = new HashMap<>();

   private List<Monster> MonsterLivePool =new ArrayList<>();

   private List<Hero> HeroLivePool =new ArrayList<>();

   private HashMap<String, Integer> HeroDeadPool =new HashMap<>();

   private HashMap<String, Integer> MonsterDeadPool =new HashMap<>();




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


    @Override
    public void Start() {
        System.out.println("Welcome to Legends Of Valor!");

        // Step 1: Create hero team
        heroTeam = Utils.createHeroes();

        // Initialize Hero Base Positions
        for (int i = 0; i < heroTeam.getTeams().size(); i++) {
            Hero hero = heroTeam.getTeams().get(i);
            HeroLivePool.add(hero);

            // Distribute heroes evenly across the first row of each lane
            int heroRow = 7; // Heroes start at the bottom row
            int heroCol = getLaneCol(i);; // Ensure heroes are placed in different lanes
            hero.setRow(heroRow);
            hero.setCol(heroCol);

            // Store hero's base position in HeroBasePostion
            HeroBasePostion.put(hero.getName(), new int[]{heroRow, heroCol});

            // Set hero into the board's HeroAndMonsterContainer
            HeroAndMonsterContainer container =
                    (HeroAndMonsterContainer) board.getCell(heroRow, heroCol).getPiece().getEvent();
            container.setHero(hero);
        }

        // Step 2: Create monster team
        monsterTeam = Utils.createMonsterTeam(heroTeam);

        // Initialize Monster Base Positions
        for (int i = 0; i < monsterTeam.getTeams().size(); i++) {
            Monster monster = monsterTeam.getTeams().get(i);
            MonsterLivePool.add(monster);

            // Distribute monsters evenly across the last row of each lane
            int monsterRow = 0; // Monsters start at the top row
            int monsterCol = getLaneCol(i); // Ensure monsters are placed in different lanes
            monster.setRow(monsterRow);
            monster.setCol(monsterCol);

            // Store monster's base position in MonsterBasePostion
            MonsterBasePostion.put(monster.getName(), new int[]{monsterRow, monsterCol});

            // Set monster into the board's HeroAndMonsterContainer
            HeroAndMonsterContainer container =
                    (HeroAndMonsterContainer) board.getCell(monsterRow, monsterCol).getPiece().getEvent();
            container.setMonster(monster);
        }

        // Display initial board setup
        board.print();

        for (int i=0; i<HeroLivePool.size();i++){

        }
    }


    @Override
    public void displayInstructions() {
        System.out.println("Hero Actions in Legends of Valor:");
        System.out.println("1. Change Weapon or Armor");
        System.out.println("2. Use a Potion");
        System.out.println("3. Attack");
        System.out.println("4. Cast a Spell");
        System.out.println("5. Move");
        System.out.println("6. Teleport");
        System.out.println("7. Recall");
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
        HeroAndMonsterContainer container =
                (HeroAndMonsterContainer) board.getCell(row, col).getPiece().getEvent();
        return container != null && container.getHero() != null;
    }

    /**
     * Checks if the hero is trying to move behind a monster.
     */
    private boolean isMovingBehindMonster(int newRow, int newCol, Hero hero) {
        int curRow = hero.getRow();
        int curCol = hero.getCol();

      if(newRow > curRow){
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
        // Ensure the row and column are within the board bounds
        if (!board.isWithinBounds(row, col)) {
            return false; // Out of bounds, no monster
        }

        // Retrieve the cell at the specified position
        Boardcells cell = board.getCell(row, col);

        // Check if the cell is inaccessible
        if (cell.getState() == State.INACCESSIBLE) {
            return false; // Inaccessible cells cannot have monsters
        }

        // Retrieve the container in the cell
        HeroAndMonsterContainer container =
                (HeroAndMonsterContainer) cell.getPiece().getEvent();

        // Check if the container has a monster
        return container != null && container.getMonster() != null;
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
        while (true) { // Loop until a valid move is made
            // Display move options
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
                return;
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

            // Validate the move and handle encounters
            moveAndCheckForEncounter(newRow, newCol, hero);

            // If the move is valid, exit the loop
            if (isValidMove(newRow, newCol, hero)) {
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

    private void MonsterBehavior(){

    }

    private void attack(Hero hero) {
        List<Monster> nearbyMonsters = getNeighborMonsters(hero.getRow(), hero.getCol());
        if (nearbyMonsters.isEmpty()) {
            System.out.println("No monsters nearby to attack.");
            return;
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






}
