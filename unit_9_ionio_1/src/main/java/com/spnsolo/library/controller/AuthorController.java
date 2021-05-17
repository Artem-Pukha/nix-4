package com.spnsolo.library.controller;

import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.EmptyLibraryException;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.service.impl.AssociateService;
import com.spnsolo.library.service.impl.AuthorService;

import java.util.Scanner;

public class AuthorController {
    private static final Scanner INNER = new Scanner(System.in);
    private static final AuthorService SERVICE = AuthorService.getInstance();
    private static final AssociateService ASSOCIATE_SERVICE = new AssociateService();

    public static void run(){
        System.out.println();
        boolean endController = false;
        while(endController == false) {
            System.out.print("Create author(1) \nDelete author by id(2)\nAll authors(3)\n" +
                    "Update author(4)\nAuthor dy id(5)\nAuthor's books(6)\nWhat do you want?:");
            String entered = INNER.nextLine();
            operation(entered);
            while(true) {
                System.out.println("To the main menu (Yes - y/No- n)?");
                String YorNo = INNER.nextLine();
                if (YorNo.equals("y")) {
                    endController = true;
                    break;
                }
                if(YorNo.equals("n")){break;}
            }
        }
    }
    private static void operation(String operation){
        switch (operation){
            case "1":
                create();
                break;
            case "2":
                delete();
                break;
            case "3":
                allAuthors();
                break;
            case "4":
                update();
                break;
            case "5":
                readById();
                break;
            case "6":
                allBooksByAuthor();
                break;
            default:
                System.out.println("Incorrect operation!");
        }
    }
    private static void update(){
        boolean endLoop = false;
            try {
                SERVICE.readAll().forEach(System.out::println);
                while (!endLoop) {
                    try {
                        Author author = new Author();
                        System.out.print("Enter author's id: ");
                        String bookId = INNER.nextLine();
                        int id = Integer.parseInt(bookId);
                        author.setId(id);
                        if (SERVICE.containsById(id)) {
                            System.out.print("Enter new author's first name: ");
                            String bookFirstName = INNER.nextLine();
                            author.setFirstName(bookFirstName);
                            System.out.print("The second name: ");
                            String bookSecondName = INNER.nextLine();
                            author.setFirstName(bookSecondName);
                            SERVICE.update(author);
                            endLoop = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid id");
                    } catch (NonexistentIdException e) {
                        System.out.println(e);
                    }
                }
            } catch (EmptyLibraryException e) {
                System.out.println(e);
            }
    }
    private static void readById(){
        boolean endLoop = false;
            try {
                SERVICE.readAll().forEach(System.out::println);
                while (!endLoop) {
                    try {
                        System.out.print("Enter author's id: ");
                        String bookId = INNER.nextLine();
                        int id = Integer.parseInt(bookId);
                        System.out.println(SERVICE.readById(id));
                        endLoop = true;
                    } catch (NonexistentIdException e) {
                        System.out.println(e);
                    }catch (NumberFormatException e){
                        System.out.println("Invalid id");
                    }
                }
            }catch (EmptyLibraryException e){
                System.out.println(e);
            }
    }
    private static void allBooksByAuthor(){
        boolean endLoop = false;
        try {
            SERVICE.readAll().forEach(System.out::println);
            while (!endLoop) {
                try {
                    System.out.print("Enter author's id: ");
                    String bookId = INNER.nextLine();
                    int id = Integer.parseInt(bookId);
                    if (SERVICE.containsById(id)) {
                        ASSOCIATE_SERVICE.readAllBooksByAuthorId(id).forEach(System.out::println);
                        endLoop = true;
                    }
                } catch (NonexistentIdException | NumberFormatException e) {
                    System.out.println(e);
                }
            }
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }
    private static void create(){
        boolean endLoop = false;
        while (!endLoop) {
            try {
                Author author = new Author();
                System.out.print("Enter author's first name: ");
                String firstName = INNER.nextLine();
                System.out.print("The Second name : ");
                String secondName = INNER.nextLine();
                author.setFirstName(firstName);
                author.setSecondName(secondName);
                SERVICE.create(author);
                endLoop = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid entered count ou pages!");
            }
        }
    }
    private static void delete(){
        boolean endLoop = false;
        try {
            SERVICE.readAll().forEach(System.out::println);
            while (!endLoop) {
                try {
                    System.out.println("Enter author's id: ");
                    String authorId = INNER.nextLine();
                    int id = Integer.parseInt(authorId);
                    SERVICE.deleteById(id);
                    endLoop = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id");
                } catch (NonexistentIdException e) {
                    System.out.println(e);
                }
            }
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }

    }
    private static void allAuthors(){
        try {
            SERVICE.readAll().forEach(System.out::println);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }


}
