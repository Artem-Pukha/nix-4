package com.spnsolo.secondtask;

import com.spnsolo.excpt.IncorrectPosition;

import java.awt.*;
import java.util.Scanner;

public class ApplicationChessPlay {

    private static final Scanner inner = new Scanner(System.in);
    public static void play(){
        System.out.println("--------------Chess play--------------");
        System.out.println();
        boolean endGame = false;
        while (endGame == false){
            System.out.println("Chess board: ");
            System.out.println("________________________");
            ChessKnightPosition.printBoard();
            System.out.println("________________________");
            System.out.println();
            try{
                System.out.print("Enter position of knight(for example \"A1\"):");
                String currentPos = inner.nextLine();
                ChessKnightPosition.availablePosition(currentPos);
                System.out.print("Enter next position of knight(for example \"C2\"):");
                String nextPos = inner.nextLine();
                ChessKnightPosition.availablePosition(nextPos);

                System.out.println("________________");
                ChessKnightPosition.stepKnight(currentPos,nextPos);
                System.out.println();

            }catch (IncorrectPosition e){
                System.out.println(e.toString());
            }
            System.out.print("Do you want repeat(Yes - y/No - n)?:");
            String YorN = inner.nextLine();
            if(YorN.equals("n")){
                System.out.println("End of task!");
                endGame = true;
            }
        }
    }
}
