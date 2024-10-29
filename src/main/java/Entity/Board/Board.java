package Entity.Board;


public abstract class Board {
    protected int n; // Size of the board (n x n)
    protected Boardcells[][] board; // 2D array representing the board grid

    public Board(int n) {
        this.n = n;
        this.board = new Boardcells[n][n];
    }

    // Get the entire board
    public Boardcells[][] getBoard() {
        return board;
    }

    // Get a specific cell on the board
    public Boardcells getCell(int row, int col) {
        if (isWithinBounds(row, col)) {
            return board[row][col];
        }
        throw new IndexOutOfBoundsException("Cell position out of bounds");
    }

    // Set a specific cell on the board
    public void setCell(int row, int col, Boardcells cell) {
        if (isWithinBounds(row, col)) {
            board[row][col] = cell;
        } else {
            throw new IndexOutOfBoundsException("Cell position out of bounds");
        }
    }

    // Check if a position is within board bounds
    public boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    // Abstract method to initialize the board (to be implemented in subclasses)
    protected abstract void initializeBoard();

    // Abstract method to print the board layout (to be implemented in subclasses)
    public abstract void print();
}
