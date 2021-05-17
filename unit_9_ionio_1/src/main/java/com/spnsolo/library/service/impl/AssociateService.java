package com.spnsolo.library.service.impl;

import com.spnsolo.library.db.impl.AuthorsAndBooks;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.EmptyLibraryException;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.impl.memory.AuthorsAndBooksDAO;
import com.spnsolo.library.service.BaseService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AssociateService implements BaseService<AuthorAndBook> {
    private final Logger logger = Logger.getLogger(AssociateService.class);
    private final AuthorsAndBooksDAO DAO = new AuthorsAndBooksDAO();
    private final AuthorService authorService = AuthorService.getInstance();
    private final BookService bookService = BookService.getInstance();

    @Override
    public boolean create(AuthorAndBook authorAndBook) {
        logger.info("Creating association");
        return DAO.create(authorAndBook);
    }

    @Deprecated
    @Override
    public boolean update(AuthorAndBook authorAndBook) {
        return false;
    }

    @Deprecated
    @Override
    public boolean deleteById(Integer id) throws NonexistentIdException {
        return false;
    }

    @Deprecated
    @Override
    public AuthorAndBook readById(Integer id) throws NonexistentIdException {
        return null;
    }

    @Deprecated
    @Override
    public List<AuthorAndBook> readAll() {
        return null;
    }

    public List<Author> readAllAuthorsByBookId(Integer id) throws EmptyLibraryException, NonexistentIdException {
        logger.info("Reading all associated books with author");
        List<Author> authors = new ArrayList<>();
        for( AuthorAndBook o : DAO.readAllByAuthorId(id)){
            authors.add(authorService.readById(o.getAuthorId()));
        }
        if(authors.isEmpty())throw new EmptyLibraryException("No one associated authors!");
        return authors;
    }

    public List<Book> readAllBooksByAuthorId(Integer id) throws EmptyLibraryException, NonexistentIdException {
        logger.info("Reading all associated authors with book");
        List<Book> books = new ArrayList<>();
        for( AuthorAndBook o : DAO.readAllByAuthorId(id)){
            books.add(bookService.readById(o.getAuthorId()));
        }
        if(books.isEmpty())throw new EmptyLibraryException("No one associated books!");
        return books;
    }


}
