package com.spnsolo.library.db.impl;

import com.spnsolo.library.db.BaseDB;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.NonexistentIdException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Books implements BaseDB<Book> {
    private static Books DB;
    Set<Book> books = new HashSet<>();

    private Books(){}
    public Books getInstance(){
        
    }
    @Override
    public boolean create(Book book) {
        int size = books.size();
        int id = size + 1;
        book.setAvailable(true);
        book.setId(id);
        return books.add(book);
    }

    @Override
    public boolean update(Book book) {
        AtomicBoolean updated = new AtomicBoolean(false);
        books.forEach(o -> {
            if(o.getId().equals(book.getId())){
                o.setName(book.getName());
                o.setCountOfPage(book.getCountOfPage());
                updated.set(true);
            }
        });
        return updated.get();
    }

    @Override
    public boolean deleteById(Integer id) {
        for(Book o: books){
            if(o.getId().equals(id) && o.isAvailable()){
                o.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public Book readById(Integer id) throws NonexistentIdException {
        for(Book o: books){
            if(o.getId()==id && o.isAvailable())return o;
        }
        throw new NonexistentIdException();
    }

    @Override
    public List<Book> readAll() {
        ArrayList<Book> allAvailable = new ArrayList<>();
        for (Book o:books){
            if(o.isAvailable())allAvailable.add(o);
        }

        if(!allAvailable.isEmpty())return allAvailable;
        else throw new NullPointerException("The base is empty");
    }
}
