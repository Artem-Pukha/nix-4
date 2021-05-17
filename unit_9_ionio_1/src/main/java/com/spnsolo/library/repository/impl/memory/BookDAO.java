package com.spnsolo.library.repository.impl.memory;

import com.spnsolo.library.db.impl.Books;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.BaseDAO;

import java.util.List;

public class BookDAO implements BaseDAO<Book> {
    private final Books DB = Books.getInstance();
    @Override
    public boolean create(Book book) { return DB.create(book); }

    @Override
    public boolean update(Book book) { return DB.update(book); }

    @Override
    public boolean deleteById(Integer id) { return DB.deleteById(id); }

    @Override
    public Book readById(Integer id) throws NonexistentIdException { return DB.readById(id); }

    @Override
    public List<Book> readAll() { return DB.readAll(); }

    public boolean containsById(Integer id){
        return DB.containsById(id);
    }

    public boolean isEmpty(){
        return DB.isEmpty();
    }
}
