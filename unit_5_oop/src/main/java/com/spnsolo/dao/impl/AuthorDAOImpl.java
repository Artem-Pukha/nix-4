package com.spnsolo.dao.impl;

import com.spnsolo.db.MyBD;
import com.spnsolo.entity.Author;
import com.spnsolo.exception.EmptyLibraryException;
import com.spnsolo.exception.NonexistentIdException;

import java.util.HashSet;

public class AuthorDAOImpl{
    private final MyBD bd = MyBD.getInstance();

    public boolean createAuthor(Author author) { return bd.createAuthor(author); }

    public boolean updateAuthor(Author author){return bd.updateAuthor(author);}

    public boolean deleteAuthorById(int id){return bd.deleteAuthorById(id); }

    public void associateAuthorWithBooks(int authorId, HashSet<Integer>booksID){
        bd.associateAuthorWithBooks(authorId,booksID);
    }
    public Author readAuthorById(int id) throws NonexistentIdException {
        return bd.readAuthorById(id);
    }
    public void allAuthors() throws EmptyLibraryException{
        bd.allAuthors();
    }
    public void allBooksByAuthorId(int authorId){
        bd.allBooksByAuthorId(authorId);
    }
    public boolean containsAuthor(int id){return bd.containsAuthor(id);}

}
