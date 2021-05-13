package com.spnsolo.library.db.impl;

import com.spnsolo.library.db.BaseDB;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.exception.NonexistentIdException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Authors implements BaseDB<Author> {
    Set<Author> authors = new HashSet<>();
    @Override
    public boolean create(Author author) {
        int size = authors.size();
        int id = size + 1;
        author.setAvailable(true);
        author.setId(id);
        return authors.add(author);
    }

    @Override
    public boolean update(Author author) {
        for(Author o : authors){
            if(o.getId().equals(author.getId())){
                o.setFirstName(author.getFirstName());
                o.setSecondName(author.getSecondName());
                o.setUpdated(OffsetDateTime.now());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        for(Author o : authors){
            if(o.getId().equals(id)){
                o.setAvailable(false);
                return true;
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
}
