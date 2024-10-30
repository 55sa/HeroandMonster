import Entity.Human.Hero;
import Entity.Items.Item;
import Entity.Items.Type;
import Entity.Monster.Monster;
import Repository.*;

import java.util.HashMap;

public class Main {






public static void main(String[] args){
    Event e=new HeroEventImp();
    CharacterFactory characterFactory=new CharacterFactoryImp();
    ItemFactory itemFactory =new ItemFactoryImp();

    Item w=itemFactory.createItem("AXE" , 20 ,1, Type.WEAPON, new HashMap<>());

    Hero a = characterFactory.createHero("Warrior","zs");

    Monster b = characterFactory.createMonster("Dragon", "mn", 1);
   a.equipWeapon(w,'R');

   e.attack(b,a);

}

}
