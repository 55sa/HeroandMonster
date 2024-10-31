package Repository;

import Entity.Human.Hero;
import Entity.Human.Paladin;
import Entity.Human.Sorcerer;
import Entity.Human.Warrior;
import Entity.Monster.Dragon;
import Entity.Monster.Exoskeleton;
import Entity.Monster.Monster;
import Entity.Monster.Spirit;

//implementation for gameCharacterfactory
public class CharacterFactoryImp implements CharacterFactory {



    @Override
    public Hero createHero(String type, String name) {
        switch (type.toLowerCase()) {
            case "warrior":
                return new Warrior(name);
            case "sorcerer":
                return new Sorcerer(name);
            case "paladin":
                return new Paladin(name);
            default:
                return null;
        }
    }

    @Override
    public Monster createMonster(String type, String name, int level) {
        switch (type.toLowerCase()) {
            case "dragon":
                return new Dragon(name, level);
            case "exoskeleton":
                return new Exoskeleton(name, level);
            case "spirit":
                return new Spirit(name, level);
            default:
                return null;
        }
    }
}

