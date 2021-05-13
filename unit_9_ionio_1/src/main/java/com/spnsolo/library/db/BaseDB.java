package com.spnsolo.library.db;

import com.spnsolo.library.entity.AbstractEntity;
import com.spnsolo.library.exception.NonexistentIdException;

import java.util.List;

public interface BaseDB<T extends AbstractEntity>{
    boolean create(T t);
    boolean update(T t);
    boolean deleteById(Integer id);
    T readById(Integer id) throws NonexistentIdException;
    List<T> readAll();
}
