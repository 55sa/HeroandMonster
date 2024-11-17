package Entity.Board;

import Entity.Human.Hero;
import Entity.Monster.Monster;

import java.util.Random;

// Specific board for Legends of Valor
public class LegendBoard extends Board {

    public LegendBoard() {
        super(8); // Fixed 8x8 grid
        initializeBoard();
    }

    @Override
    protected void initializeBoard() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row == 0 || row == n - 1) {
                    // Nexus row
                    if (col == 2 || col == 5) {
                        board[row][col] = new Boardcells(row, col, State.INACCESSIBLE, null);
                    } else {
                        Piece piece = new Piece(new HeroAndMonsterContainer());
                        board[row][col] = new Boardcells(row, col, State.NEXUS, piece);
                    }
                } else if (col == 2 || col == 5) {
                    // Walls separating the lanes
                    board[row][col] = new Boardcells(row, col, State.INACCESSIBLE, null);
                } else {
                    // Randomly assign other space types
                    Piece piece = new Piece(new HeroAndMonsterContainer());
                    board[row][col] = new Boardcells(row, col, getRandomState(), piece);
                }
            }
        }
    }

    private State getRandomState() {
        State[] possibleStates = {State.COMMON, State.BUSH, State.CAVE, State.Koulou};
        return possibleStates[new Random().nextInt(possibleStates.length)];
    }

    @Override
    public void print() {
        // Print column headers

        for (int col = 0; col < n; col++) {
            System.out.print("      " + col + "   ");
        }
        System.out.println();

        // Print board
        for (int i = 0; i < n; i++) {
            // First row of symbols
            System.out.print("   ");
            for (int j = 0; j < n; j++) {
                System.out.print("---------");
            }
            System.out.println("-----");

            // Second row (for state)
            System.out.print(" " + i + " ");
            for (int j = 0; j < n; j++) {
                Boardcells cell = board[i][j];
                if (cell.getState() == State.INACCESSIBLE) {
                    System.out.print("|   I   ");
                } else {
                    System.out.print("|    " + getStateSymbol(cell.getState()) + "    ");
                }
            }
            System.out.println("|");

            // Third row (for Hero and Monster)
            System.out.print("   ");
            for (int j = 0; j < n; j++) {
                Boardcells cell = board[i][j];
                if (cell.getState() == State.INACCESSIBLE) {
                    System.out.print("|X  X  X");
                } else {
                    HeroAndMonsterContainer piece = cell.getPiece() != null ? (HeroAndMonsterContainer) cell.getPiece().getEvent() : null;
                    String hero = piece != null && piece.getHero() != null ? piece.getHero().getName().substring(0, 2) : "  ";
                    String monster = piece != null && piece.getMonster() != null ? piece.getMonster().getName().substring(0, 2) : "  ";
                    System.out.print("|  " + hero + " " + monster + "  ");
                }
            }
            System.out.println("|");
        }

        // Bottom row of symbols
        System.out.print("   ");
        for (int j = 0; j < n; j++) {
            System.out.print("----------");
        }

    }

    // Helper method to get the symbol for a specific state
    private String getStateSymbol(State state) {
        switch (state) {
            case NEXUS:
                return "N";
            case BUSH:
                return "B";
            case CAVE:
                return "C";
            case Koulou:
                return "K";
            case COMMON:
                return "P";
            case MARKET:
                return "M";
            case INACCESSIBLE:
                return "X";
            default:
                return " ";
        }
    }


}
