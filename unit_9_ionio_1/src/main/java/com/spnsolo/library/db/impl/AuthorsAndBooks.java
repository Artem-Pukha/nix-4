package com.spnsolo.library.db.impl;

import com.spnsolo.library.db.BaseDB;
import com.spnsolo.library.entity.AuthorAndBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthorsAndBooks implements BaseDB<AuthorAndBook> {
    Set<AuthorAndBook> authorsAndBooks = new HashSet<>();
    @Override
    public boolean create(AuthorAndBook authorAndBook) {
        authorsAndBooks.add(authorAndBook);
        return authorsAndBooks.contains(authorAndBook);
    }

    @Deprecated
    @Override
    public boolean update(AuthorAndBook authorAndBook) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public AuthorAndBook readById(Integer id) {
        return null;
    }

    @Override
    public List<AuthorAndBook> readAll() {
        return null;
    }
}
