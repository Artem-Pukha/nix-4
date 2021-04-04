package com.spnsolo.dao.impl;

import com.spnsolo.db.MyBD;
import com.spnsolo.entity.Book;
import com.spnsolo.exception.EmptyLibraryException;
import com.spnsolo.exception.NonexistentIdException;

import java.util.HashSet;

public class BookDAOImpl{
    private final MyBD bd = MyBD.getInstance();


    public boolean createBook(Book book) { return bd.createBook(book); }

    public boolean updateBook(Book book){return bd.updateBook(book);}

    public boolean deleteBookById(int id) { return bd.deleteBookById(id); }

    public void associateBookWithAuthors(int bookId, HashSet<Integer>authorId){
        bd.associateBookWithAuthors(bookId,authorId);
    }
    public void allBooks() throws EmptyLibraryException{
        bd.allBooks();
    }
    public Book readBookById(int id) throws NonexistentIdException{
        return bd.readBookById(id);
    }
    public void allAuthorsByBookId(int bookId){
        bd.allAuthorsByBookId(bookId);
    }
    public boolean containsBook(int id){return bd.containsBook(id);}
}
