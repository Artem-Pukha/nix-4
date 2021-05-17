package com.spnsolo.library.controller;

import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.exception.EmptyLibraryException;
import com.spnsolo.library.service.impl.AssociateService;
import com.spnsolo.library.service.impl.AuthorService;
import com.spnsolo.library.service.impl.BookService;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AssociateController {
    private static final Scanner INNER = new Scanner(System.in);
    private static final AssociateService service = new AssociateService();
    private static final AuthorService authorService = AuthorService.getInstance();
    private static final BookService bookService = BookService.getInstance();
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
        boolean endLoop = false;
        try {
            bookService.readAll().forEach(System.out::println);
            int id = 0;
            while (!endLoop) {
                try {
                    System.out.print("Enter book's id: ");
                    String bookId = INNER.nextLine();
                    id = Integer.parseInt(bookId);
                    endLoop = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid index");
                }
            }
            authorService.readAll().forEach(System.out::println);
            System.out.print("Enter authors' ids(divide id with \',\'): ");
            String authorsId = INNER.nextLine();
            authorsId = authorsId.trim();
            String[] ids = authorsId.split(",");
            Set<Integer> authors = new HashSet<>();
            for(String s:ids){
                authors.add(Integer.parseInt(s));
            }
            AuthorAndBook authorAndBook;
            for(Integer i : authors){
                authorAndBook = new AuthorAndBook();
                authorAndBook.setAuthorId(i);
                authorAndBook.setBookId(id);
                service.create(authorAndBook);

            }
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }
    }
    private static void authorWithBooks(){
        boolean endLoop = false;
        try {
            authorService.readAll().forEach(System.out::println);
            int id = 0;
            while (!endLoop) {
                try {
                    System.out.print("Enter author's id: ");
                    String authorId = INNER.nextLine();
                    id = Integer.parseInt(authorId);
                    endLoop = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid index");
                }
            }
            endLoop = false;
            bookService.readAll().forEach(System.out::println);
            Set<Integer> books = new HashSet<>();
            while (!endLoop) {
                try {
                    System.out.print("Enter books' ids(divide id with \',\'): ");
                    String authorsId = INNER.nextLine();
                    authorsId = authorsId.trim();
                    String[] ids = authorsId.split(",");
                    books = new HashSet<>();
                    for (String s : ids) {
                        books.add(Integer.parseInt(s));
                    }
                    endLoop = true;
                }catch (NumberFormatException e){
                    System.out.println("Invalid index");
                }
            }
            AuthorAndBook authorAndBook;
            for(Integer i : books){
                authorAndBook = new AuthorAndBook();
                authorAndBook.setBookId(i);
                authorAndBook.setAuthorId(id);
                service.create(authorAndBook);

            }
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }
    }
}
