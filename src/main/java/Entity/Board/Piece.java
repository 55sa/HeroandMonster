package Entity.Board;

//Stuff that the cell represent Market Block Empty
public class Piece {
    private Object event;

    public Object getEvent() {
        return event;
    }

    public void setEvent(Object event) {
        this.event = event;
    }

    public Piece(Object event) {
        this.event = event;
    }


}