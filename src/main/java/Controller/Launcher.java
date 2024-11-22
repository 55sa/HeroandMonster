package Controller;

import Util.Utils;

public class Launcher {
   public static Game game;

   //Game launcher
    public static void launch(){
        System.out.println("Welcome to game center");
        System.out.println("Please Select game you want to play");
        System.out.println("1. Hero and Monster");
        System.out.println("2. Legends of valor");
        int choice = Utils.getIntInRange("Type 1 - 2:",1,2);
        switch (choice){
            case 1:
                game = new MainEvent();
                game.Start();
                break;
            case 2:
                game = new LegendsOfValor();
                game.Start();
                break;
        }
    }
}
