package com.spnsolo;

import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.EmptyLibraryException;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.impl.memory.AuthorsAndBooksDAO;
import com.spnsolo.library.service.impl.AssociateService;
import com.spnsolo.library.service.impl.AuthorService;
import com.spnsolo.library.service.impl.BookService;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonTest {
    private static final BookService bookService = BookService.getInstance();
    private static final AuthorService authorService = AuthorService.getInstance();
    private static final AssociateService associateService = new AssociateService();

    private static final AuthorsAndBooksDAO AUTHORS_AND_BOOKS_DAO = new AuthorsAndBooksDAO();

    private static final List<Integer> createdBooks = new ArrayList<>();
    private static final List<Integer> createdAuthors = new ArrayList<>();
    private static Integer createdAssociation;

    @BeforeAll
    public static void init(){
        String name = "testName ";
        String firstName = "testFirstName ";
        String secondName = "testSecondName ";
        int countOfPages = 23;
        int i = 0;
        for( i = 0; i < 5; ++i){

            Author author = new Author();
            author.setFirstName(firstName + i);
            author.setSecondName(secondName + i);

            Book book = new Book();
            book.setName(name + i);
            book.setCountOfPage(countOfPages + i);

            bookService.create(book);
            authorService.create(author);

            createdAuthors.add(author.getId());
            createdBooks.add(book.getId());
        }
        Assertions.assertTrue(!bookService.isEmpty() && !authorService.isEmpty());
    }

    @Test
    @Order(1)
    public void createAuthor(){
        Author author = new Author();
        author.setFirstName("createFirstName");
        author.setSecondName("createSecondName");

        Assertions.assertTrue(authorService.create(author));
        createdAuthors.add(author.getId());
    }

    @Test
    @Order(2)
    public void createBook(){
        Book book = new Book();
        book.setName("createName");
        book.setCountOfPage(98);

        Assertions.assertTrue(bookService.create(book));
        createdBooks.add(book.getId());
    }

    @Test
    @Order(3)
    public void associateBookAndAuthor(){
        AuthorAndBook authorAndBook = new AuthorAndBook();
        authorAndBook.setAuthorId(1);
        authorAndBook.setBookId(1);

        Assertions.assertTrue(associateService.create(authorAndBook));
        createdAssociation = authorAndBook.getId();
    }

    @Test
    @Order(4)
    public void updateAuthor(){
        try {
            Author author = authorService.readById(createdAuthors.get(1));
            author.setFirstName("Vasya");
            Assertions.assertTrue(authorService.update(author));
        } catch (NonexistentIdException | EmptyLibraryException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    public void updateBook(){
        try {
            Book book = bookService.readById(createdBooks.get(1));
            book.setName("Vasev");
            Assertions.assertTrue(bookService.update(book));
        } catch (NonexistentIdException | EmptyLibraryException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    public void deleteAuthor(){
        try {
            Assertions.assertTrue(authorService.deleteById(createdAuthors.get(1)));
            createdAuthors.remove(1);
        } catch (NonexistentIdException | EmptyLibraryException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(7)
    public void deleteBook(){
        try {
            Assertions.assertTrue(bookService.deleteById(createdBooks.get(1)));
            createdBooks.remove(1);
        } catch (NonexistentIdException | EmptyLibraryException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void clearCreated(){
        createdAuthors.forEach(id -> {
            try {
                authorService.deleteById(id);
            } catch (NonexistentIdException | EmptyLibraryException e) {
                e.printStackTrace();
            }
        });

        createdBooks.forEach(id-> {
            try {
                bookService.deleteById(id);
            } catch (NonexistentIdException | EmptyLibraryException e) {
                e.printStackTrace();
            }
        });

        AUTHORS_AND_BOOKS_DAO.deleteById(createdAssociation);
    }
}
