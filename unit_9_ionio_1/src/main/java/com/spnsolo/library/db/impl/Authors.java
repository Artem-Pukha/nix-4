package com.spnsolo.library.db.impl;

import com.spnsolo.library.db.BaseDB;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.impl.file.AuthorsFileDAO;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Authors implements BaseDB<Author> {
    private final AuthorsFileDAO DAO_FILE = new AuthorsFileDAO();
    private static Authors instance;
    private final Set<Author> authors = new HashSet<>();

    private Authors(){
        authors.addAll(DAO_FILE.readAll());
    }
    public static Authors getInstance(){
        if(instance == null){
            instance = new Authors();
        }
        return instance;
    }
    @Override
    public boolean create(Author author) {
        int size = authors.size();
        int id = size + 1;
        author.setAvailable(true);
        author.setId(id);
        if(authors.add(author)) {
            return DAO_FILE.create(author);
        }
        return false;
    }

    @Override
    public boolean update(Author author) {
        for(Author o : authors){
            if(o.getId().equals(author.getId())){
                o.setFirstName(author.getFirstName());
                o.setSecondName(author.getSecondName());
                return DAO_FILE.update(o);
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        for(Author o : authors){
            if(o.getId().equals(id)){
                o.setAvailable(false);
                return DAO_FILE.deleteById(id);
            }
        }
        return false;
    }

    @Override
    public Author readById(Integer id) throws NonexistentIdException {
        for(Author o: authors){
            if(o.getId().equals(id) && o.isAvailable())return o;
        }
        throw new NonexistentIdException();
    }

    @Override
    public List<Author> readAll() {
        ArrayList<Author> allAvailable = new ArrayList<>();
        for (Author o:authors){
            if(o.isAvailable())allAvailable.add(o);
        }
        if(!allAvailable.isEmpty())return allAvailable;
        else throw new NullPointerException("The base is empty");
    }

    public boolean containsById(Integer id){
        for(Author o : authors){
            if(o.getId().equals(id) && o.isAvailable())return true;
        }
        return false;
    }

    public boolean isEmpty(){
        return authors.isEmpty();
    }
}
