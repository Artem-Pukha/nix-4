package com.spnsolo.library.service;

import com.spnsolo.library.entity.AbstractEntity;
import com.spnsolo.library.exception.EmptyLibraryException;
import com.spnsolo.library.exception.NonexistentIdException;

import java.util.List;

public interface BaseService<T extends AbstractEntity> {
    boolean create(T t);
    boolean update(T t) throws EmptyLibraryException;
    boolean deleteById(Integer id) throws NonexistentIdException, EmptyLibraryException;
    T readById(Integer id) throws NonexistentIdException, EmptyLibraryException;
    List<T> readAll() throws EmptyLibraryException;
}
