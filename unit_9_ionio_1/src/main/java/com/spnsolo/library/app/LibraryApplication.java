package com.spnsolo.library.app;

import com.spnsolo.library.controller.AssociateController;
import com.spnsolo.library.controller.AuthorController;
import com.spnsolo.library.controller.BookController;

import java.util.Scanner;

public class LibraryApplication {
    private static final Scanner inner = new Scanner(System.in);
    public static void run(){
        System.out.println("------------Book library------------");
        System.out.println();
        boolean endApp = false;
        while(!endApp) {
            System.out.print("What do you want to work with? Books(1), Authors(2), Make a association between a book and an author(3):");
            String index = inner.nextLine();
            selectSection(index);
            while(true) {
                System.out.println("Finish the program (Yes - y/No- n)?");
                String YorNo = inner.nextLine();
                if (YorNo.equals("y")) {
                    System.out.println("Thank you for using!");
                    endApp = true;
                    break;
                }
                if(YorNo.equals("n")){break;}
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
                AssociateController.run();
                break;
            default:
                System.out.println("Incorrect index!");
        }
    }
}
