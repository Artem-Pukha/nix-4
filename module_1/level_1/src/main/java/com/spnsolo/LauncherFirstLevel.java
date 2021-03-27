package com.spnsolo;

import com.spnsolo.excpt.IncorrectSymbol;
import com.spnsolo.firsttask.ApplicationUnique;
import com.spnsolo.secondtask.ApplicationChessPlay;
import com.spnsolo.thirdtask.ApplicationTriangle;

import java.util.Scanner;

public class LauncherFirstLevel {
    private static final Scanner inner = new Scanner(System.in);
    public static void run() {
        System.out.println("----------First level----------");
        System.out.println();

        boolean endLevel = false;
        while (endLevel == false){
            try {
                System.out.print("Unique numbers(u), Chess knight(c), Square triangle(s):");
                String task = inner.nextLine();
                switch (task) {
                    case "u":
                        ApplicationUnique.run();
                        break;
                    case "c":
                        ApplicationChessPlay.play();
                        break;
                    case "s":
                        ApplicationTriangle.run();
                        break;
                    default:throw  new IncorrectSymbol();
                }
            }catch (IncorrectSymbol e){
                System.out.println(e);
            }
            System.out.println();
            System.out.print("Do you want repeat(Yes - y/No - n)?:");
            String YorN = inner.nextLine();
            if (YorN.equals("n")) {
                endLevel = true;
            }
        }
    }
}
