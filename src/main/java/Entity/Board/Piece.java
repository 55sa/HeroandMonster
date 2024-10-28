package Entity.Board;

//pieces in cell implemention
public class Piece<T> {
   private T symbol;

    public T getSymbol() {
        return symbol;
    }

    public void setSymbol(T symbol) {
        this.symbol = symbol;
    }

    public Piece(T symbol) {
        this.symbol = symbol;
    }
}
