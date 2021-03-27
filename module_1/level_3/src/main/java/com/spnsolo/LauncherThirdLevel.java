package com.spnsolo;

import java.util.Scanner;

public class LauncherThirdLevel {
    private static final Scanner inner = new Scanner(System.in);
    private static GameOfLife game = new GameOfLife();
    public static void run(){
        System.out.println("--------------Game of life--------------");
        System.out.println();
        boolean endGame = false;
        while (endGame == false){
            System.out.println("----Board----");
            game.printBoard();
            System.out.println();

            boolean gameLoop =true;
            while(gameLoop == true){
                gameLoop = true;
                game.gameOfLife();
                System.out.println();
                System.out.print("Next step(Yes(+)/No(-))?:");
                String next = inner.nextLine();
                if(next.equals("-")){
                    gameLoop = false;
                }
            }
            System.out.println();
            System.out.print("Do you want repeat(Yes - y/No - n)?:");
            String YorN = inner.nextLine();
            if (YorN.equals("n")) {
                System.out.println("Thank you for playing!");
                endGame = true;
            }
        }
    }
}
