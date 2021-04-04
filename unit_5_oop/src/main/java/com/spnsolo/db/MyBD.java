package com.spnsolo.db;


import com.spnsolo.entity.Book;
import com.spnsolo.entity.Author;
import com.spnsolo.exception.EmptyLibraryException;
import com.spnsolo.exception.NonexistentIdException;

import java.util.HashSet;
import java.util.Set;

public class MyBD {
    private static MyBD instance;

    private final Set<Author> authors = new HashSet<Author>();
    private final Set<Book> books = new HashSet<Book>();

    private MyBD(){}
    public static  MyBD getInstance(){
        if(instance == null){
            instance = new MyBD();
        }
        return instance;
    }

    public boolean createAuthor(Author author){
        int size = authors.size();
        int id = size + 1;
        author.setAvailable(true);
        author.setId(id);
        return authors.add(author);
    }
    public boolean createBook(Book book){
        int size = books.size();
        int id = size + 1;
        book.setAvailable(true);
        book.setId(id);
        return books.add(book);
    }
    public boolean updateAuthor(Author author){
        for(Author o: authors){
            if(o.getId() == author.getId()){
                o.setFirstName(author.getFirstName());
                o.setSecondName(author.getSecondName());
                return true;
            }
        }
        return false;
    }
    public boolean updateBook(Book book){
        for(Book o: books){
            if(o.getId() == book.getId()){
                o.setName(book.getName());
                return true;
            }
        }
        return false;
    }
    public boolean deleteAuthorById(int id){
        for(Author o:authors){
            if(o.getId() == id && o.isAvailable()){
                o.setAvailable(false);
                return true;
            }
        }
        return false;
    }
    public boolean deleteBookById(int id){
        for(Book o: books){
            if(o.getId() == id && o.isAvailable()){
                o.setAvailable(false);
                return true;
            }
        }
        return false;
    }
    public Author readAuthorById(int id) throws NonexistentIdException {
        for(Author o: authors){
            if(o.getId()==id && o.isAvailable())return o;
        }
        throw new NonexistentIdException();
    }
    public Book readBookById(int id) throws NonexistentIdException {
        for(Book o: books){
            if(o.getId()==id && o.isAvailable())return o;
        }
        throw new NonexistentIdException();
    }
    public void allAuthors() throws EmptyLibraryException {
        if(!authors.isEmpty()) {
            for (Author o : authors) {
                if(o.isAvailable())System.out.println("Id: " + o.getId() +" First name: "+ o.getFirstName()+
                        "Second name: " + o.getSecondName());
            }
        }else{
            throw new EmptyLibraryException();
        }
    }
    public void allBooks() throws EmptyLibraryException {
        if(!books.isEmpty()) {
            for (Book o : books) {
                if(o.isAvailable())System.out.println("Id: " + o.getId() + " Name: " + o.getName());
            }
        }else{
            throw new EmptyLibraryException();
        }
    }
    public void associateAuthorWithBooks(int authorId, HashSet<Integer>booksId){
        for(Author o: authors){
            if(o.getId()==authorId)o.setBooks(booksId);
        }
        for(Book o: books){
            if(booksId.contains(o.getId()))o.getAuthors().add(authorId);
        }
    }
    public void associateBookWithAuthors(int bookId, HashSet<Integer>authorId){
        for(Book o: books){
            if(o.getId()==bookId)o.setAuthors(authorId);
        }
        for(Author o: authors){
            if(authorId.contains(o.getId()))o.getBooks().add(bookId);
        }
    }
    public void allBooksByAuthorId(int authorId){
        boolean nonexistent = true;
        for(Book o: books){
            if(o.getAuthors().contains(authorId)) {
                System.out.println("Name: " + o.getName());
                nonexistent = false;
            }
        }
        if(nonexistent == true)System.out.println("Books aren't known");
    }
    public void allAuthorsByBookId(int bookId){
        boolean nonexistent = true;
        for(Author o: authors){
            if(o.getBooks().contains(bookId)) {
                System.out.println("Name: " + o.getFirstName() + " " + o.getSecondName());
                nonexistent = false;
            }
        }
        if(nonexistent == true)System.out.println("Authors aren't known");
    }
    public boolean containsAuthor(int id){
        for(Author o: authors){
            if(o.getId()==id&&o.isAvailable())return true;
        }
        return false;
    }
    public boolean containsBook(int id){
        for(Book o: books){
            if(o.getId()==id&&o.isAvailable())return true;
        }
        return false;
    }
}
