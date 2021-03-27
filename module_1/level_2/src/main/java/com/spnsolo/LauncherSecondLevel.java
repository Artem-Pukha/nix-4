package com.spnsolo;

import com.spnsolo.exception.InvalidSymbol;

import java.util.Scanner;

public class LauncherSecondLevel {
    private static final Scanner inner = new Scanner(System.in);
    public static void run(){
        System.out.println("--------------Validated string(\"(),[],{}\")--------------");
        System.out.println();
        boolean endProgram = false;
        while (endProgram == false){
            System.out.print("Enter your string:");
            String entered = inner.nextLine();

            try {
                ValidString.valid(entered);
            } catch (InvalidSymbol invalidSymbol) {
                invalidSymbol.printStackTrace();
            }
            System.out.println();
            System.out.print("Do you want repeat(Yes - y/No - n)?:");
            String YorN = inner.nextLine();
            if (YorN.equals("n")) {
                System.out.println("Thank you for using!");
                endProgram = true;
            }
        }
    }
}
