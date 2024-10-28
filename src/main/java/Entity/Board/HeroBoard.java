package Entity.Board;

public class HeroBoard extends Board{
    public HeroBoard(int n) {
        super(n);
        super.board=new Boardcells[n][n];

    }

    @Override
    public void print() {

    }
}
