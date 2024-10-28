package Repository;

import Entity.Human.Hero;
import Entity.Monster.Monster;

// Factory interface for characters
public interface CharacterFactory {
    Hero createHero(String type, String name);
    Monster createMonster(String type, String name, int level);
}

