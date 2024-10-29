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
    private void populateMarketsWithUniqueItems() {
        List<Item> allItems = new ArrayList<>(Gamedatabase.ITEMS);
        Collections.shuffle(allItems);
        int itemIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j].getState() == State.MARKET ) {
                    int cur = 0;
                    List<Item> marketItems = new ArrayList<>();
                    while (cur < allItems.size() / (n * n * 0.3)){
                    // Create a new Market with unique items for each market cell

                    if (itemIndex < allItems.size()) {
                        marketItems.add(allItems.get(itemIndex++)); // Add a unique item

                    }
                    cur++;
                    }

                    Market market = new Market(marketItems); // Create a Market event
                    board[i][j].setPiece(new Piece(market)); // Assign Market to the Piece
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
