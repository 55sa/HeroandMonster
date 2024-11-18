import Controller.MainEvent;
import Entity.Board.Board;
import Entity.Board.HeroAndMonsterContainer;
import Entity.Board.LegendBoard;
import Entity.Board.Piece;
import Entity.Human.Hero;
import Entity.Human.Warrior;
import Entity.Items.Item;
import Entity.Items.Type;
import Entity.Monster.Monster;
import Entity.Monster.Spirit;
import Repository.*;

import java.util.HashMap;
import java.util.List;

// Main access
public class Main {


public static void main(String[] args){
//    MainEvent game =new MainEvent();
//    game.Start();

    Board b=new LegendBoard();
   b.getCell(7,0).setPiece(new Piece( new HeroAndMonsterContainer(new Spirit("m1",1), new Warrior("h1"))));

    b.print();
   List<Item> t = b.getCell(7,0).getMarket().getProducts();
   for (Item t1: t){
       System.out.println(t1.getName());
   }
}

}
