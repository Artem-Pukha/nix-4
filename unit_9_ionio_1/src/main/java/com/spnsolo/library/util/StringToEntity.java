package com.spnsolo.library.util;

import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.entity.Book;


public class StringToEntity {
    public static Author stringToAuthor(String[] stringAuthor) {
        Author author = new Author();

        author.setFirstName(stringAuthor[0]);
        author.setSecondName(stringAuthor[1]);
        author.setId(Integer.parseInt(stringAuthor[2]));
        author.setAvailable(Boolean.parseBoolean(stringAuthor[3]));

        return author;
    }
    public static Book stringToBook(String[] stringBook){
        Book book = new Book();

        book.setName(stringBook[0]);
        book.setCountOfPage(Integer.parseInt(stringBook[1]));
        book.setId(Integer.parseInt(stringBook[2]));
        book.setAvailable(Boolean.parseBoolean(stringBook[3]));

        return book;
    }
    public static AuthorAndBook stringToAuthorAndBook(String[] stringAuthorAndBook){
        AuthorAndBook authorAndBook = new AuthorAndBook();

        authorAndBook.setAuthorId(Integer.parseInt(stringAuthorAndBook[0]));
        authorAndBook.setBookId(Integer.parseInt(stringAuthorAndBook[1]));
        authorAndBook.setId(Integer.parseInt(stringAuthorAndBook[2]));
        authorAndBook.setAvailable(Boolean.parseBoolean(stringAuthorAndBook[3]));

        return authorAndBook;
    }

}