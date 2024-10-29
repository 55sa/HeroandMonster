package Entity.Board;
// implementation of board cells




public class Boardcells {
    private int row;            // Row position of the cell
    private int col;            // Column position of the cell
    private State state;        // Type of state in the cell

    private Piece piece;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Constructor to initialize position and piece type
    public Boardcells(int row, int col, State state, Piece piece) {
        this.row = row;
        this.col = col;
        this.state = state;
        this.piece = piece;
    }

    // Getters and Setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public State getPiece() {
        return state;
    }

    public void setPiece(State state) {
        this.state = state;
    }

    // Override toString for easy debugging and printing cell details
    @Override
    public String toString() {
        return "BoardCell{" +
                "row=" + row +
                ", col=" + col +
                ", piece=" + state +
                '}';
    }
}
