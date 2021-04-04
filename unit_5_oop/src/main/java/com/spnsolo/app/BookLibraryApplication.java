package com.spnsolo.app;

import com.spnsolo.controller.AssociationController;
import com.spnsolo.controller.AuthorController;
import com.spnsolo.controller.BookController;

import java.util.Scanner;

public class BookLibraryApplication {
    private static final Scanner inner = new Scanner(System.in);
    public static void run(){
        System.out.println("------------Book library------------");
        System.out.println();
        boolean endApp = false;
        while(endApp == false) {
            System.out.print("What do you want? Books(1), Authors(2), Make a association between a book and an author(3):");
            String index = inner.nextLine();
            selectSection(index);
            System.out.println("Finish the program (Yes - y/No- n)?");
            String YorNo = inner.nextLine();
            if(YorNo.equals("y")){
                System.out.println("Thank you for using!");
                endApp = true;
            }
        }
    }
    public static void selectSection(String index){
        switch (index){
            case "1":
                BookController.run();
                break;
            case "2":
                AuthorController.run();
                break;
            case "3":
                AssociationController.run();
                break;
            default:
                System.out.println("Incorrect index!");
        }
    }
}
