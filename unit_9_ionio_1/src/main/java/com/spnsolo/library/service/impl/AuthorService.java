package com.spnsolo.library.service.impl;

import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.EmptyLibraryException;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.impl.file.AuthorsFileDAO;
import com.spnsolo.library.repository.impl.memory.AuthorsDAO;
import com.spnsolo.library.service.BaseService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AuthorService implements BaseService<Author> {
    private final Logger logger = Logger.getLogger(AuthorService.class);
    private final AuthorsDAO DAO = new AuthorsDAO();
    private static AuthorService instance;

    private AuthorService(){}
    public static AuthorService getInstance(){
        if(instance == null){
            instance = new AuthorService();
        }
        return instance;
    }
    @Override
    public boolean create(Author author) {
        logger.info("Creating author");
        return DAO.create(author);
    }

    @Override
    public boolean update(Author author) throws EmptyLibraryException {
        if(isEmpty()) throw new EmptyLibraryException();
        logger.info("Updating author");
        return DAO.update(author);
    }

    @Override
    public boolean deleteById(Integer id) throws NonexistentIdException, EmptyLibraryException {
        if(isEmpty()) throw new EmptyLibraryException();
        logger.info("Deleting author");
        if(id <= 0) throw new NonexistentIdException();
        return DAO.deleteById(id);
    }

    @Override
    public Author readById(Integer id) throws NonexistentIdException, EmptyLibraryException {
        if(isEmpty()) throw new EmptyLibraryException();
        logger.info("Reading author");
        if(id <= 0) throw new NonexistentIdException();
        return DAO.readById(id);
    }

    @Override
    public List<Author> readAll() throws EmptyLibraryException {
        if(isEmpty()) throw new EmptyLibraryException();
        logger.info("Reading all authors");
        return  DAO.readAll();
    }

    /*public List<Book> allBooksByAuthorId(Integer id) throws NonexistentIdException, EmptyLibraryException {
        List<Book> books = new ArrayList<>();
        for( AuthorAndBook o : serviceAssociate.readAllByAuthorId(id)){
            books.add(serviceBook.readById(o.getId()));
        }
        if(books.isEmpty())throw new EmptyLibraryException("No one associated authors!");
        return books;
    }*/
    public boolean containsById(Integer id) throws EmptyLibraryException, NonexistentIdException {
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
