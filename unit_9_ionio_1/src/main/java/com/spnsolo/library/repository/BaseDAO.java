package com.spnsolo.library.repository;

import com.spnsolo.library.entity.AbstractEntity;
import com.spnsolo.library.exception.NonexistentIdException;

import java.util.List;

public interface BaseDAO<T extends AbstractEntity> {
    boolean create(T t);
    boolean update(T t);
    boolean deleteById(Integer id);
    T readById(Integer id) throws NonexistentIdException;
    List<T> readAll();
}
