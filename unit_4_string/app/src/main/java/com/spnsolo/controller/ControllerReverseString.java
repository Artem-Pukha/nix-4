package com.spnsolo.controller;

import com.spnsolo.reversestring.ReverseString;
import com.spnsolo.reversestring.exept.InvalidIndex;

import java.util.Scanner;

public class ControllerReverseString {
    private Scanner scanner = new Scanner(System.in);
    public void read(){
        System.out.println("/////////////Reverse words into a string/////////////");
        System.out.println();

        boolean endProgram = false;
        while (endProgram == false){
            System.out.println("----------------------------------");
            System.out.print("Pleas, enter your string: ");
            String userString = scanner.nextLine();
            System.out.println();

            selectOperation(userString);

            System.out.print("Do you want to repeat (Yes - y/ No - n): ");
            String YorN = scanner.nextLine();
            if(YorN.equals("y")){
                System.out.println("----------------------------------");
            }else{
                System.out.println("Thank you for using!");
                endProgram = true;
            }
        }
        scanner.close();
    }
    public void selectOperation(String userString) {

        System.out.print("Revers all string (1)\n"+
                "Revers substring (2)\n"+
                "Revers by indexes (3)\n"+
                "What do you want ?(enter one of :1, 2, 3) :");

        String operation = scanner.nextLine();
        System.out.println();
        switch (operation){
            case "1":
                System.out.println("Your revers: " + ReverseString.reverse(userString));
                break;
            case "2":
                System.out.println("Enter your substring: ");
                String substring = scanner.nextLine();
                System.out.println("Your reverse: " + ReverseString.reverse(userString,substring));
                break;
            case "3":
                System.out.print("Enter first index: ");
                int firstIndex = scanner.nextInt();
                System.out.print("Enter second index: ");
                int secondIndex = scanner.nextInt();
                try {
                    System.out.println("Your reverse: " + ReverseString.reverse(userString,firstIndex,secondIndex));
                } catch (InvalidIndex invalidIndex) {
                    invalidIndex.printStackTrace();
                }
                break;
            default:
                System.out.println("There are not such operation");
        }
        System.out.println();

    }
}
