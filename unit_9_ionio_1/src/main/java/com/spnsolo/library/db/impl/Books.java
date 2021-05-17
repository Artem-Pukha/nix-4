package com.spnsolo.library.db.impl;

import com.spnsolo.library.db.BaseDB;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.impl.file.BookFileDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Books implements BaseDB<Book> {
    private final BookFileDAO DAO_FILE = new BookFileDAO();
    private static Books instance;
    private final Set<Book> books = new HashSet<>();

    private Books(){books.addAll(DAO_FILE.readAll());}
    public static Books getInstance(){
        if(instance == null){
            instance = new Books();
        }
        return instance;
    }
    @Override
    public boolean create(Book book) {
        int size = books.size();
        int id = size + 1;
        book.setAvailable(true);
        book.setId(id);
        if(books.add(book)) {
            return DAO_FILE.create(book);
        }
        return false;
    }

    @Override
    public boolean update(Book book) {
        for(Book o : books){
            if(o.getId().equals(book.getId())){
                o.setName(book.getName());
                o.setCountOfPage(book.getCountOfPage());
                return DAO_FILE.update(o);
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        for(Book o: books){
            if(o.getId().equals(id) && o.isAvailable()){
                o.setAvailable(false);
                return DAO_FILE.deleteById(id);
            }
        }
        return false;
    }

    @Override
    public Book readById(Integer id) throws NonexistentIdException {
        for(Book o: books){
            if(o.getId().equals(id) && o.isAvailable())return o;
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
    public boolean containsById(Integer id){
        for(Book o : books){
            if(o.getId().equals(id)&&o.isAvailable())return true;
        }
        return false;
    }
    public boolean isEmpty(){
        return books.isEmpty();
    }
}
