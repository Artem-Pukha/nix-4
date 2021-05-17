package com.spnsolo.library.repository.impl.memory;

import com.spnsolo.library.db.impl.AuthorsAndBooks;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.repository.BaseDAO;

import java.util.ArrayList;
import java.util.List;

public class AuthorsAndBooksDAO implements BaseDAO<AuthorAndBook> {
    private final AuthorsAndBooks DB = AuthorsAndBooks.getInstance();

    @Override
    public boolean create(AuthorAndBook authorAndBook) {
        return DB.create(authorAndBook);
    }

    @Deprecated
    @Override
    public boolean update(AuthorAndBook authorAndBook) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) { return DB.deleteById(id); }

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

    public List<AuthorAndBook> readAllByAuthorId(Integer id){ return DB.readAllByAuthorId(id); }

    public List<AuthorAndBook> readAllByBookId(Integer id){ return DB.readAllByBookId(id); }

}
