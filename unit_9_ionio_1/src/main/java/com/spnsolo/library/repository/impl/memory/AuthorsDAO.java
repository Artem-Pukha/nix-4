package com.spnsolo.library.repository.impl.memory;

import com.spnsolo.library.db.impl.Authors;
import com.spnsolo.library.entity.AbstractEntity;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.BaseDAO;

import java.util.List;

public class AuthorsDAO implements BaseDAO<Author> {
    private final Authors DB = Authors.getInstance();

    @Override
    public boolean create(Author author) { return DB.create(author); }

    @Override
    public boolean update(Author author) { return DB.update(author); }

    @Override
    public boolean deleteById(Integer id) { return DB.deleteById(id); }

    @Override
    public Author readById(Integer id) throws NonexistentIdException { return DB.readById(id); }

    @Override
    public List<Author> readAll() { return DB.readAll(); }

    public boolean containsById(Integer id){
        return DB.containsById(id);
    }

    public boolean isEmpty(){
        return DB.isEmpty();
    }
}
