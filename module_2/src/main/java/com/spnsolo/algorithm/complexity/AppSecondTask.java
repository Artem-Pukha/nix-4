package com.spnsolo.algorithm.complexity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class AppSecondTask {
    private static final Scanner INNER = new Scanner(System.in);
    public static void run(){
        System.out.println("--------------Second Task(unique name)--------------");
        boolean endApp = false;
        while(!endApp) {
            selectOperation();
            while(true) {
                System.out.println("Finish the task (Yes - y/No- n)?");
                String YorNo = INNER.nextLine();
                if (YorNo.equals("y")) {
                    endApp = true;
                    break;
                }
                if(YorNo.equals("n")){break;}
            }
        }
        INNER.close();
        System.out.println("---------------------------------------");
    }

    private static void selectOperation(){
        boolean endLoop;
        do {
            endLoop = true;
            System.out.print("What do you want?:\n" +
            "Test application(1)\n" +
            "Enter names by yourself(2): ");
            switch (INNER.nextLine()) {
                case "1" -> TestOfSecondTask.run();
                case "2" -> inputNames();
                default -> {
                    System.out.println("Incorrect operation!");
                    endLoop = false;
                }
            }
        }while (!endLoop);
    }

    private static void inputNames(){
        System.out.println();
        System.out.println("Enter names by splitting with help of key \"space\"(Vanya Ira Vasya Dada Ya)");
        String[] names = INNER.nextLine().split(" ");
        ArrayList<String> listOfNames = new ArrayList<>(Arrays.asList(names));

        System.out.println("First unique name from list: ");
        int index = DefinerOfFirstUniqueName.defineFirstUnique(listOfNames);
        System.out.println(listOfNames.get(index) + " (with an index " + index +")");
    }
}
