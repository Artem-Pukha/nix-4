package com.spnsolo.service;

import com.spnsolo.dao.impl.BookDAOImpl;
import com.spnsolo.entity.Author;
import com.spnsolo.entity.Book;
import com.spnsolo.exception.EmptyLibraryException;
import com.spnsolo.exception.NonexistentIdException;
import org.apache.log4j.Logger;

import java.util.HashSet;

public class BookService {

    private final Logger logger = Logger.getLogger(BookService.class);
    private final BookDAOImpl DAO = new BookDAOImpl();

    public void createBook(Book book){
        logger.info("Start of creating book");
        DAO.createBook(book);
        logger.info("Finish of creating book");
    }

    public boolean updateBook(Book book){
        logger.info("Updating book");
        return DAO.updateBook(book);
    }

    public boolean deleteBookById(int id) {
        logger.info("Deleting book");
        return DAO.deleteBookById(id);
    }

    public void associateAuthorWithBooks(int bookId, HashSet<Integer> authors){
        logger.info("Start of associating book with authors");
        DAO.associateBookWithAuthors(bookId,authors);
        logger.info("Finish of associating book with authors");
    }
    public void allBooks() throws EmptyLibraryException {
        logger.info("Printing all books");
        DAO.allBooks();
        logger.info("Finish of printing all books");
    }
    public Book readBookById(int id) throws NonexistentIdException {
        logger.info("Reading book");
        return DAO.readBookById(id);
    }
    public void allAuthorsByBookId(int bookId){
        logger.info("Printing all book's authors");
        DAO.allAuthorsByBookId(bookId);
        logger.info("Finish of printing all book's authors");
    }
    public boolean containsBook(int id){
        logger.info("Checking book");
        return DAO.containsBook(id);
    }
}
