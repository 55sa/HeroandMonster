package Entity.Board;
// implementation of board cells
public class Boardcells {
    private int x;
    private int y;
    private Piece value;

    // Constructor
    public Boardcells(int x, int y, Piece value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    // Getter for x
    public int getX() {
        return x;
    }

    // Setter for x
    public void setX(int x) {
        this.x = x;
    }

    // Getter for y
    public int getY() {
        return y;
    }

    // Setter for y
    public void setY(int y) {
        this.y = y;
    }

    // Getter for value
    public Piece getValue() {
        return value;
    }

    // Setter for value
    public void setValue(Piece value) {
        this.value = value;
    }
}
