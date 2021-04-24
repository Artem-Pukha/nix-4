package com.spnsolo;

import com.spnsolo.collection.MathSat;

import java.util.Scanner;

public class ApplicationMathSat {
    private static final MathSat<Integer> integers = new MathSat<>();
    private static final Scanner INNER = new Scanner(System.in);
    public static void run(){
        try {
            System.out.println("------------Collection \"Math Sat\"------------");
            System.out.println();
            boolean endApp = false;
            while (!endApp) {
                System.out.println("Filling of your sat:");
                fillSet();
                selectOperation();
                System.out.println("Finish the program (Yes - y/No- n)?");
                String YorNo = INNER.nextLine();
                if (YorNo.equals("y")) {
                    System.out.println("Thank you for using!");
                    endApp = true;
                }
            }
            INNER.close();
        }catch (NumberFormatException e){
            System.out.println("You enter invalid data");
        }
    }
    private static void fillSet(){
        boolean endLoop = false;
        while (!endLoop) {
            try {
                System.out.println("Enter your integer and press the key \"enter\". If you wanna end the " +
                        " entering , type \"e\" and press the key \"enter\"");
                while (true) {
                    String str = INNER.nextLine();
                    if (str.equals("e")) {
                        if (integers.isEmpty()) {
                            System.out.println("Your set is empty, you must enter something");
                        } else {
                            endLoop = true;
                            break;
                        }
                    } else {
                        Integer entered = Integer.parseInt(str);
                        integers.add(entered);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid data");
            }
        }
    }

    private static void selectOperation(){
        boolean endLoop = false;
        do{
            System.out.println();
            System.out.println("""
                    Select the operation below:
                    Print whole list(1)
                    Print minimal value(2)
                    Print maximum value(3)
                    Print average of sat(4)
                    Print median of sat(5)
                    Sort set ascendingly(6)
                    Sort set descendingly(7)
                    Squash your sat(8)
                    Remove element from sat(9)
                    End operations(e)
                    """);
            System.out.print("The operation: ");
            String index = INNER.nextLine();
            switch (index) {
                case "1":
                    printWholeList();
                    break;
                case "2":
                    printMinValue();
                    break;
                case "3":
                    printMaxValue();
                    break;
                case "4":
                    printAverage();
                    break;
                case "5":
                    printMedian();
                    break;
                case "6":
                    sortAsc();
                    break;
                case "7":
                    sortDesc();
                    break;
                case "8":
                    squash();
                    break;
                case "9":
                    removeElement();
                    break;
                case "e":
                    endLoop = true;
                    break;
                default:
                    System.out.println("Incorrect symbol!");
            }
        }while (!endLoop);
    }
    private static void printWholeList(){
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(i+": "+ integers.get(i));
        }
    }
    private static void printMinValue(){
        System.out.println("Minimal: "+integers.getMin());
    }
    private static void printMaxValue(){
        System.out.println("Maximal: "+integers.getMax());
    }
    private static void printAverage(){
        System.out.println("Average: "+integers.getAverage());
    }
    private static void printMedian(){
        integers.sortAsc();
        System.out.println("Median: "+integers.getMedian());
    }
    private static void sortDesc(){
        System.out.println("Before sorting:");
        printWholeList();
        integers.sortDesc();
        System.out.println("After sorting:");
        printWholeList();
    }
    private static void sortAsc(){
        System.out.println("Before sorting:");
        printWholeList();
        integers.sortAsc();
        System.out.println("After sorting:");
        printWholeList();
    }
    private static void squash(){
        while (true) {
            try {
                printWholeList();
                System.out.println("You need to set a range of squashing.");
                System.out.print("Enter the first index(number before a value of your list) from " + 0 + "-" + (integers.size() - 1) + ": ");
                String firstIndex = INNER.nextLine();
                System.out.print("Enter the last one: ");
                String lastIndex = INNER.nextLine();
                System.out.println("Squashed sat:");
                MathSat squashed = integers.squash(Integer.parseInt(firstIndex), Integer.parseInt(lastIndex));
                for (int i = 0; i < squashed.size(); i++) {
                    System.out.println(squashed.get(i));
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("You entered incorrect value!");
            }
        }
    }
    private static void removeElement(){
        printWholeList();
        while (true) {
            try {
                System.out.println("Enter your integer and press the key \"enter\".");
                String entered = INNER.nextLine();
                integers.remove(Integer.parseInt(entered));
                break;
            } catch (NumberFormatException e) {
                System.out.println("You entered invalid data!");
            }
        }
    }


}
