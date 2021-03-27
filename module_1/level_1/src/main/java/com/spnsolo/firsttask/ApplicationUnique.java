package com.spnsolo.firsttask;

import java.util.Scanner;

public class ApplicationUnique {
    private static final Scanner inner = new Scanner(System.in);
    public static void run(){
        System.out.println("--------------Unique number--------------");
        System.out.println();
        boolean endProgram = false;
        while (endProgram == false){

            try {
                System.out.print("Enter string of numbers: ");
                String string = inner.nextLine();
                Integer.parseInt(string);
                Integer[] numbers = new Integer[string.length()];
                for (int i = 0; i < string.length(); ++i) {
                    numbers[i] = string.charAt(i) - 47;
                }
                System.out.println(UniqueNumbers.unique(numbers));
                System.out.println();
            }catch (NumberFormatException e){
                System.out.println("Invalid string!");
            }
                System.out.print("Do you want repeat(Yes - y/No - n)?:");
                String YorN = inner.nextLine();
                if (YorN.equals("n")) {
                    System.out.println("End of task!");
                    endProgram = true;
                }
        }
    }
}
