package com.spnsolo;

import java.util.Scanner;

public class Main {
    private static final Scanner inner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("//////////Module 1//////////");
        System.out.println();

        boolean endModule = false;
        while (endModule == false){
            try {
                System.out.print("First level(1); \nSecond level(2); \nThird level(3); \nWhich one?:");
                String level = inner.nextLine();
                int indexLevel = Integer.parseInt(level);
                switch (indexLevel){
                    case 1:
                        LauncherFirstLevel.run();
                        break;
                    case 2:
                        LauncherSecondLevel.run();
                        break;
                    case 3:
                        LauncherThirdLevel.run();
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("Incorrect level!");
            }

            System.out.println();
            System.out.print("Do you want change level(Yes - y/No - n)?:");
            String YorN = inner.nextLine();
            if (YorN.equals("n")) {
                System.out.println("Thank you for checking module!");
                endModule = true;
            }
        }
        inner.close();
    }
}
