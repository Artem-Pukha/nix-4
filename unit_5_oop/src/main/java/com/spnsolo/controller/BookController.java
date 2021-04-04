package com.spnsolo.controller;

import com.spnsolo.entity.Book;
import com.spnsolo.exception.EmptyLibraryException;
import com.spnsolo.exception.NonexistentIdException;
import com.spnsolo.service.BookService;

import java.util.Scanner;

public class BookController {
    private static final Scanner INNER = new Scanner(System.in);
    private static final BookService SERVICE = new BookService();
    public static void run(){
        System.out.println();
        boolean endController = false;
        while(endController == false) {
            System.out.print("Create book(1) \nDelete book by id(2)\nAll books(3)\nUpdate book(4)\n" +
                    "Book by id(5)\nBook's authors(6)\nWhat do you want?:");
            String entered = INNER.nextLine();
            operation(entered);
            System.out.println("To the main menu (Yes - y/No- n)?");
            String YorNo = INNER.nextLine();
            if(YorNo.equals("y")){
                endController = true;
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
        try {
            SERVICE.allBooks();
            Book book = new Book();
            System.out.print("Enter book's id: ");
            String bookId = INNER.nextLine();
            int id = Integer.parseInt(bookId);
            book.setId(id);
            if(SERVICE.containsBook(id)){
                System.out.print("Enter new book's name: ");
                String bookName = INNER.nextLine();
                book.setName(bookName);
                SERVICE.updateBook(book);
            }else {
                System.out.println("Nonexistent id!");
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }catch (EmptyLibraryException e){
            System.out.println(e);
        }
    }
    private static void readById(){
        try {
            SERVICE.allBooks();
            System.out.print("Enter book's id: ");
            String bookId = INNER.nextLine();
            int id = Integer.parseInt(bookId);
            System.out.println(SERVICE.readBookById(id));
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }catch (EmptyLibraryException e){
            System.out.println(e);
        }catch (NonexistentIdException e){
            System.out.println(e);
        }
    }
    private static void allAuthorsByBook(){
        try {
            SERVICE.allBooks();
            System.out.print("Enter author's id: ");
            String bookId = INNER.nextLine();
            int id = Integer.parseInt(bookId);
            if(SERVICE.containsBook(id)){
                SERVICE.allAuthorsByBookId(id);
            }else {
                System.out.println("Nonexistent id!");
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }catch (EmptyLibraryException e){
            System.out.println(e);
        }
    }
    private static void create(){
        Book book = new Book();
        System.out.print("Enter book's name: ");
        String name = INNER.nextLine();
        book.setName(name);
        SERVICE.createBook(book);
    }
    private static void delete(){
        try {
            SERVICE.allBooks();
            System.out.println("Enter book's id: ");
            String authorId = INNER.nextLine();
            int id = Integer.parseInt(authorId);
            SERVICE.deleteBookById(id);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }

    }
    private static void allBooks(){
        try {
            SERVICE.allBooks();
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }
}
