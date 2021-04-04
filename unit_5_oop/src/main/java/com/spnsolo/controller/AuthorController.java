package com.spnsolo.controller;

import com.spnsolo.entity.Author;
import com.spnsolo.entity.Book;
import com.spnsolo.exception.EmptyLibraryException;
import com.spnsolo.exception.NonexistentIdException;
import com.spnsolo.service.AuthorService;

import java.util.Scanner;

public class AuthorController {
    private static final Scanner INNER = new Scanner(System.in);
    private static final AuthorService SERVICE = new AuthorService();

    public static void run(){
        System.out.println();
        boolean endController = false;
        while(endController == false) {
            System.out.print("Create author(1) \nDelete author by id(2)\nAll authors(3)\n" +
                    "Update author(4)\nAuthor dy id(5)\nAuthor's books(6)\nWhat do you want?:");
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
                System.out.println("Incorrect index!");
        }
    }
    private static void update(){
        try {
            SERVICE.allAuthors();
            Author author = new Author();
            System.out.print("Enter author's id: ");
            String bookId = INNER.nextLine();
            int id = Integer.parseInt(bookId);
            author.setId(id);
            if(SERVICE.containsAuthor(id)){
                System.out.print("Enter author's first name: ");
                String firstName = INNER.nextLine();
                author.setFirstName(firstName);
                System.out.print("Enter author's second name: ");
                String secondName = INNER.nextLine();
                author.setSecondName(secondName);
                SERVICE.updateAuthor(author);
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
            SERVICE.allAuthors();
            System.out.print("Enter author's id: ");
            String authorId = INNER.nextLine();
            int id = Integer.parseInt(authorId);
            System.out.println(SERVICE.readAuthorById(id));
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }catch (EmptyLibraryException e){
            System.out.println(e);
        }catch (NonexistentIdException e){
            System.out.println(e);
        }

    }
    private static void allBooksByAuthor(){
        try {
            SERVICE.allAuthors();
            System.out.print("Enter author's id: ");
            String authorId = INNER.nextLine();
            int id = Integer.parseInt(authorId);
            if(SERVICE.containsAuthor(id)){
                SERVICE.allBooksByAuthorId(id);
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
        Author author = new Author();
        System.out.print("Enter author's first name: ");
        String firstName = INNER.nextLine();
        author.setFirstName(firstName);

        System.out.print("Enter author's first name: ");
        String secondName = INNER.nextLine();
        author.setSecondName(secondName);

        SERVICE.createAuthor(author);
    }
    private static void delete(){
        try {
            SERVICE.allAuthors();
            System.out.println("Enter author's id: ");
            String authorId = INNER.nextLine();
            int id = Integer.parseInt(authorId);
            SERVICE.deleteAuthorById(id);
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }catch (NumberFormatException e){
            System.out.println("Invalid id!");
        }

    }
    private static void allAuthors(){
        try {
            SERVICE.allAuthors();
        } catch (EmptyLibraryException e) {
            System.out.println(e);
        }
    }


}
