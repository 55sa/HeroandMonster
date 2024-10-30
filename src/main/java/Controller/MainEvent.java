package Controller;


import Entity.Human.Hero;
import Entity.Monster.Monster;
import Repository.*;

public class MainEvent {
    CharacterFactory characterFactory=new CharacterFactoryImp();
    ItemFactory itemFactory = new ItemFactoryImp();

    Event<Hero, Monster> heroEvent = new HeroEventImp();

    Event<Monster, Hero> monsterEvent = new MonsterEventImp();


}
