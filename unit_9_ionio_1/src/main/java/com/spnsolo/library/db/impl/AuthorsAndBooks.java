package com.spnsolo.library.db.impl;

import com.spnsolo.library.db.BaseDB;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.impl.file.AuthorsAndBooksFileDAO;
import com.spnsolo.library.repository.impl.file.AuthorsFileDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthorsAndBooks implements BaseDB<AuthorAndBook> {
    private final AuthorsAndBooksFileDAO DAO_FILE = new AuthorsAndBooksFileDAO();
    private static AuthorsAndBooks instance;
    private final Set<AuthorAndBook> authorsAndBooks = new HashSet<>();
    private List<AuthorAndBook> authorAndBookList;

    private AuthorsAndBooks(){ authorsAndBooks.addAll(DAO_FILE.readAll());}
    public static AuthorsAndBooks getInstance(){
        if(instance == null){
            return instance = new AuthorsAndBooks();
        }
        return instance;
    }

    @Override
    public boolean create(AuthorAndBook authorAndBook) {
        int size = authorsAndBooks.size();
        int id = size + 1;
        authorAndBook.setAvailable(true);
        authorAndBook.setId(id);
        if(authorsAndBooks.add(authorAndBook)) {
            return DAO_FILE.create(authorAndBook);
        }
        return false;
    }

    @Deprecated
    @Override
    public boolean update(AuthorAndBook authorAndBook) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        for(AuthorAndBook o : authorsAndBooks){
            if(o.getId().equals(id)){
                o.setAvailable(false);
                return DAO_FILE.deleteById(id);
            }
        }
        return false;
    }

    @Deprecated
    @Override
    public AuthorAndBook readById(Integer id) {
        return null;
    }

    @Deprecated
    @Override
    public List<AuthorAndBook> readAll() {
        return null;
    }

    public List<AuthorAndBook> readAllByAuthorId(Integer id){
        authorAndBookList = new ArrayList<>();
        for(AuthorAndBook o : authorsAndBooks){
            if(o.getAuthorId().equals(id))authorAndBookList.add(o);
        }
        return authorAndBookList;
    }

    public List<AuthorAndBook> readAllByBookId(Integer id){
        authorAndBookList = new ArrayList<>();
        for(AuthorAndBook o : authorsAndBooks){
            if(o.getBookId().equals(id))authorAndBookList.add(o);
        }
        return authorAndBookList;
    }

    public boolean isEmpty(){
        return authorsAndBooks.isEmpty();
    }
}
