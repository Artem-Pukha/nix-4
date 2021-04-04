package com.spnsolo.controller;

import com.spnsolo.exception.EmptyLibraryException;
import com.spnsolo.service.AssociateService;

import java.util.Scanner;

public class AssociationController {
    private static final Scanner INNER = new Scanner(System.in);
    private static final AssociateService service = new AssociateService();
    public static void run(){
        System.out.println();
        boolean endController = false;
        while(endController == false) {
            System.out.print("Book with authors(1) \nAuthor with books(2):");
            String entered = INNER.nextLine();
            operation(entered);

            System.out.println("To the main menu (Yes - y/No- n)?");
            String YorNo = INNER.nextLine();
            if(YorNo.equals("y")){
                endController = true;
            }
        }
    }
    public static void operation(String operation){
        switch (operation){
            case "1":
                bookWithAuthors();
                break;
            case "2":
                authorWithBooks();
                break;
            default:
                System.out.println("Incorrect symbol!");

        }
    }
    private static void bookWithAuthors(){
        try {
            service.allBooks();
            System.out.print("Enter book's id: ");
            String bookId = INNER.nextLine();
            int id = Integer.parseInt(bookId);
            service.allAuthors();
            System.out.print("Enter authors' ids(divide id with \',\'): ");
            String authorId = INNER.nextLine();
            service.booksWithAuthors(id,authorId);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }
    }
    private static void authorWithBooks(){
        try {
            service.allBooks();
            service.allAuthors();
            System.out.print("Enter author's id: ");
            String authorId = INNER.nextLine();
            int id = Integer.parseInt(authorId);
            service.allBooks();
            System.out.print("Enter books' ids(divide id with \',\'): ");
            String bookId = INNER.nextLine();
            service.authorWithBooks(id,bookId);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }
    }
}
