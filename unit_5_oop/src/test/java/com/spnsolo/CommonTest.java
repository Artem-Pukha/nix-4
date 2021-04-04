package com.spnsolo;

import com.spnsolo.entity.Author;
import com.spnsolo.entity.Book;
import com.spnsolo.exception.NonexistentIdException;
import com.spnsolo.service.AuthorService;
import com.spnsolo.service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.util.HashSet;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonTest {
    private static final AuthorService AUTHOR_SERVICE = new AuthorService();
    private static final BookService BOOK_SERVICE = new BookService();
    private static int bookID;
    private static int authorID;

    @BeforeAll
    public static void init(){
        int i = 0;
        for( i = 0; i < 5; ++i){
            String name = "testName " + i;
            String firstName = "testFirstName " + i;
            String secondName = "testSecondName " + i;

            Author author = new Author();
            author.setId(i);
            author.setFirstName(firstName);
            author.setSecondName(secondName);

            Book book = new Book();
            book.setName(name);
            book.setId(i);

            AUTHOR_SERVICE.createAuthor(author);
            BOOK_SERVICE.createBook(book);
        }
        Assert.assertTrue(AUTHOR_SERVICE.containsAuthor(i)&&BOOK_SERVICE.containsBook(i));
    }

    @Test
    @Order(1)
    public void associateAuthorsAndBooks() {
        for(int i = 0; i < 5; ++i){
            String name = "testName " + i;
            String firstName = "testFirstName " + i;
            String secondName = "testSecondName " + i;

            Author author = new Author();
            author.setId(i);
            author.setFirstName(firstName);
            author.setSecondName(secondName);

            Book book = new Book();
            book.setName(name);
            book.setId(i);

            AUTHOR_SERVICE.createAuthor(author);
            BOOK_SERVICE.createBook(book);
            authorID++;
            bookID++;
        }
        HashSet<Integer> books = new HashSet<>();
        books.add(bookID - 1);
        books.add(bookID - 2);
        AUTHOR_SERVICE.associateAuthorWithBooks(authorID,books);
        try {
            Assert.assertTrue(!AUTHOR_SERVICE.readAuthorById(authorID).getBooks().isEmpty());
        } catch (NonexistentIdException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Order(2)
    public void deleteAuthor(){
        Assert.assertTrue(AUTHOR_SERVICE.deleteAuthorById(authorID));
    }

    @Test
    @Order(3)
    public void deleteBook(){
        Assert.assertTrue(BOOK_SERVICE.deleteBookById(bookID));
    }

    @Test
    @Order(4)
    public void createAuthor(){
        ++authorID;
        Author author = new Author();
        author.setId(authorID);
        author.setFirstName("testFirstName " + authorID);
        author.setSecondName("testSecondName " + authorID);

        AUTHOR_SERVICE.createAuthor(author);
        Assert.assertTrue(AUTHOR_SERVICE.containsAuthor(authorID));
    }

    @Test
    @Order(5)
    public void createBook(){
        ++bookID;
        Book book = new Book();
        book.setId(bookID);
        book.setName("testName " + bookID);

        BOOK_SERVICE.createBook(book);
        Assert.assertTrue(BOOK_SERVICE.containsBook(bookID));
    }

    @Test
    @Order(6)
    public void updateBook(){
        Book book = new Book();
        book.setId(bookID);
        book.setName("testName updated");
        BOOK_SERVICE.updateBook(book);
        try {
            Assert.assertTrue(BOOK_SERVICE.readBookById(bookID).getName().equals("testName updated"));
        } catch (NonexistentIdException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(7)
    public void updateAuthor(){
        Author author = new Author();
        author.setId(authorID);
        author.setFirstName("testFirstName updated");
        author.setSecondName("testSecondName updated");
        AUTHOR_SERVICE.updateAuthor(author);
        try {
            Assert.assertTrue(AUTHOR_SERVICE.readAuthorById(authorID).getFirstName().equals("testFirstName updated")
                    &&AUTHOR_SERVICE.readAuthorById(authorID).getSecondName().equals("testSecondName updated"));
        } catch (NonexistentIdException e) {
            e.printStackTrace();
        }
    }
}
