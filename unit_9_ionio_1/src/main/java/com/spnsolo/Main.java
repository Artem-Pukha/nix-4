package com.spnsolo;

import com.opencsv.exceptions.CsvException;
import com.spnsolo.library.app.LibraryApplication;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.impl.file.AuthorsFileDAO;
import com.spnsolo.library.service.impl.AssociateService;
import com.spnsolo.library.service.impl.AuthorService;
import com.spnsolo.library.service.impl.BookService;
import com.spnsolo.library.util.EntityToString;
import com.spnsolo.library.util.StringToEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        LibraryApplication.run();
    }
}
