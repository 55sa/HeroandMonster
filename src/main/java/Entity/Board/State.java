package Entity.Board;

//pieces in cell implemention


public enum State {
    INACCESSIBLE,  // Heroes cannot enter
    MARKET,        // Market space where heroes can buy or sell items
    COMMON,        // Common space where battles can occur
    HERO,          // Hero’s current position
    MONSTER        // Monster’s current position
}

