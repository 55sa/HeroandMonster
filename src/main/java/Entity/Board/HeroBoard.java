package Entity.Board;


import DataBase.Gamedatabase;
import Entity.Items.Item;
import Entity.Market.Market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HeroBoard extends Board {

    public HeroBoard(int n) {
        super(n);
        initializeBoard();
        populateMarketsWithUniqueItems();
    }

    @Override
    protected void initializeBoard() {
        Random random = new Random();
        int totalCells = n * n;
        int inaccessibleCells = (int) (totalCells * 0.2);
        int marketCells = (int) (totalCells * 0.3);
        int commonCells = totalCells - inaccessibleCells - marketCells;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (inaccessibleCells > 0) {
                    board[i][j] = new Boardcells(i, j, State.INACCESSIBLE, null);
                    inaccessibleCells--;
                } else if (marketCells > 0) {
                    board[i][j] = new Boardcells(i, j, State.MARKET, null);
                    marketCells--;
                } else if (commonCells > 0) {
                    board[i][j] = new Boardcells(i, j, State.COMMON, null);
                    commonCells--;
                }
            }
        }
        shuffleBoard();
    }

    // Populate each market cell with a unique Market event and items
    // Populate each market cell with a unique Market event and items
    private void populateMarketsWithUniqueItems() {
        List<Item> allItems = new ArrayList<>(Gamedatabase.ITEMS);
        Collections.shuffle(allItems);
        int itemIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j].getState() == State.MARKET) {
                    List<Item> marketItems = new ArrayList<>();
                    for (int k = 0; k < Math.min(3, allItems.size() - itemIndex); k++) {
                        marketItems.add(allItems.get(itemIndex++));
                    }
                    Market market = new Market(marketItems);
                    board[i][j].setPiece(new Piece(market));

//                     Debugging check
//                    System.out.println("Market created at (" + i + ", " + j + ") with items:");
//                    for (Item item : marketItems) {
//                        System.out.println(" - " + item.getName());
//                    }
                }
            }
        }
    }


    private void shuffleBoard() {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = random.nextInt(n);
                int y = random.nextInt(n);
                Boardcells temp = board[i][j];
                board[i][j] = board[x][y];
                board[x][y] = temp;
            }
        }
    }

    @Override
    public void print() {
        System.out.print("   ");
        for (int col = 0; col < n; col++) {
            System.out.print(" " + col + "  ");
        }
        System.out.println();

        System.out.print("   ");
        for (int col = 0; col < n; col++) {
            System.out.print("----");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(" " + i + " |");
            for (int j = 0; j < n; j++) {
                switch (board[i][j].getState()) {
                    case INACCESSIBLE:
                        System.out.print(" X |");
                        break;
                    case MARKET:
                        System.out.print(" M |");
                        break;
                    case COMMON:
                        System.out.print(" C |");
                        break;
                    case HERO:
                        System.out.print(" * |");
                        break;

                }
            }
            System.out.println();

            System.out.print("   ");
            for (int col = 0; col < n; col++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }
}
