package com.spnsolo.service;


import com.spnsolo.exception.EmptyLibraryException;
import org.apache.log4j.Logger;

import java.util.HashSet;

public class AssociateService {

    private final Logger logger = Logger.getLogger(AssociateService.class);
    private final AuthorService AUTHOR_SERVICE = new AuthorService();
    private final BookService BOOK_SERVICE = new BookService();

    public void booksWithAuthors(int bookId, String authorsId){
        authorsId = authorsId.trim();
        String[] ids = authorsId.split(",");
        HashSet<Integer> authors = new HashSet<>();
        for(String s:ids){
            authors.add(Integer.parseInt(s));
        }
        BOOK_SERVICE.associateAuthorWithBooks(bookId,authors);
    }
    public void authorWithBooks(int authorId, String booksId){
        booksId = booksId.trim();
        String[] ids = booksId.split(",");
        HashSet<Integer> books = new HashSet<>();
        for(String s:ids){
            books.add(Integer.parseInt(s));
        }
        AUTHOR_SERVICE.associateAuthorWithBooks(authorId,books);
    }
    public void allAuthors() throws EmptyLibraryException {
        AUTHOR_SERVICE.allAuthors();
    }
    public void allBooks() throws EmptyLibraryException {
        BOOK_SERVICE.allBooks();
    }


}
