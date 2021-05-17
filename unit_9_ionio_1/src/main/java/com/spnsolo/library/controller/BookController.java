package com.spnsolo.library.controller;

import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.EmptyLibraryException;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.service.impl.AssociateService;
import com.spnsolo.library.service.impl.BookService;

import java.util.Scanner;

public class BookController {
    private static final Scanner INNER = new Scanner(System.in);
    private static final BookService SERVICE = BookService.getInstance();
    private static final AssociateService ASSOCIATE_SERVICE = new AssociateService();
    public static void run(){
        System.out.println();
        boolean endController = false;
        while(endController == false) {
            System.out.print("Create book(1) \nDelete book by id(2)\nAll books(3)\nUpdate book(4)\n" +
                    "Book by id(5)\nBook's authors(6)\nWhat do you want?:");
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
                allBooks();
                break;
            case "4":
                update();
                break;
            case "5":
                readById();
                break;
            case "6":
                allAuthorsByBook();
                break;
            default:
                System.out.println("Incorrect index!");
        }
    }
    private static void update(){
        boolean endLoop = false;
            try {
                SERVICE.readAll().forEach(System.out::println);
                while (!endLoop) {
                    try {
                        Book book = new Book();
                        System.out.print("Enter book's id: ");
                        String bookId = INNER.nextLine();
                        int id = Integer.parseInt(bookId);
                        book.setId(id);
                        if (SERVICE.containsById(id)) {
                            System.out.print("Enter new book's name: ");
                            String bookName = INNER.nextLine();
                            book.setName(bookName);
                            SERVICE.update(book);
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
                try {
                    while (!endLoop) {
                        System.out.print("Enter book's id: ");
                        String bookId = INNER.nextLine();
                        int id = Integer.parseInt(bookId);
                        System.out.println(SERVICE.readById(id));
                        endLoop = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id");
                } catch (NonexistentIdException e) {
                    System.out.println(e);
                }
            } catch (EmptyLibraryException e){
                System.out.println(e);
            }
    }
    private static void allAuthorsByBook(){
        boolean endLoop = false;
            try {
                SERVICE.readAll().forEach(System.out::println);
                while (!endLoop) {
                    try {
                        System.out.print("Enter book's id: ");
                        String bookId = INNER.nextLine();
                        int id = Integer.parseInt(bookId);
                        if (SERVICE.containsById(id)) {
                            ASSOCIATE_SERVICE.readAllAuthorsByBookId(id).forEach(System.out::println);
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
                Book book = new Book();
                System.out.print("Enter book's name: ");
                String name = INNER.nextLine();
                System.out.print("Enter count of pages : ");
                String countOfPage = INNER.nextLine();
                book.setName(name);
                book.setCountOfPage(Integer.parseInt(countOfPage));
                SERVICE.create(book);
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
                        System.out.println("Enter book's id: ");
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
    private static void allBooks(){
        try {
            SERVICE.readAll().forEach(System.out::println);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }
}
