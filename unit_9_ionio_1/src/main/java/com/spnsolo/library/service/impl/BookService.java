package com.spnsolo.library.service.impl;

import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.EmptyLibraryException;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.impl.memory.AuthorsDAO;
import com.spnsolo.library.repository.impl.memory.BookDAO;
import com.spnsolo.library.service.BaseService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BookService implements BaseService<Book> {
    private final Logger logger = Logger.getLogger(BookService.class);
    private final BookDAO DAO = new BookDAO();
    private static BookService instance;

    private BookService(){}
    public static BookService getInstance(){
        if(instance == null){
            instance = new BookService();
        }
        return instance;
    }

    @Override
    public boolean create(Book book) {
        logger.info("Creating book");
        return DAO.create(book);
    }

    @Override
    public boolean update(Book book) throws EmptyLibraryException {
        if(isEmpty()) throw new EmptyLibraryException();
        logger.info("Updating book");
        return DAO.update(book);
    }

    @Override
    public boolean deleteById(Integer id) throws NonexistentIdException, EmptyLibraryException {
        if(isEmpty()) throw new EmptyLibraryException();
        logger.info("Deleting book");
        if(id < 0) throw new NonexistentIdException("Negative id!");
        return DAO.deleteById(id);
    }

    @Override
    public Book readById(Integer id) throws NonexistentIdException, EmptyLibraryException {
        if(isEmpty()) throw new EmptyLibraryException();
        logger.info("Reading book");
        if(id < 0) throw new NonexistentIdException();
        return DAO.readById(id);
    }

    @Override
    public List<Book> readAll() throws EmptyLibraryException {
        if(isEmpty()) throw new EmptyLibraryException();
        logger.info("Reading all books");
        return  DAO.readAll();
    }

    /*public List<Author> allAuthorsByBookId(Integer id) throws NonexistentIdException, EmptyLibraryException {
        if(serviceAuthor.isEmpty())throw new EmptyLibraryException("No one authors");
        List<Author> authors = new ArrayList<>();
        for( AuthorAndBook o : serviceAssociate.readAllByBookId(id)){
            authors.add(serviceAuthor.readById(o.getId()));
        }
        if(authors.isEmpty())throw new EmptyLibraryException("No one associated authors!");
        return authors;
    }*/

    public boolean containsById(Integer id) throws NonexistentIdException, EmptyLibraryException {
        if(isEmpty()) throw new EmptyLibraryException();
        logger.info("Checking if there is book in db");
        if(id < 0) throw new NonexistentIdException("Negative ID");
        return DAO.containsById(id);
    }

    public boolean isEmpty(){
        logger.info("Checking if there are books in db");
        return DAO.isEmpty();
    }
}
