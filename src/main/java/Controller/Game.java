package Controller;

import Entity.Human.Hero;

public interface Game {
    public void Start();

    public void displayInstructions();

    public void moveAndCheckForEncounter(int newRow, int newCol, Hero hero);

    public void updateHeroPosition(int newRow, int newCol, Hero hero);


}