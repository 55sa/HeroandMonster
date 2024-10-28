package Entity.Board;
//Father class of Board
public abstract class Board<T> {
 protected int n; //size of board
 protected Boardcells[][] board;

    public Boardcells[][] getBoard() {
        return board;
    }

    public void setBoard(Boardcells[][] board) {
        this.board = board;
    }

    public int getN() {
        return n;
    }



    public void setN(int n) {
        this.n = n;
    }





    public Board(int n) {
        this.n = n;

    }

    public abstract void print();


}
