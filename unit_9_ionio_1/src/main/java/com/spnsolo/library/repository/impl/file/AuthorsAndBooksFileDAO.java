package com.spnsolo.library.repository.impl.file;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.BaseDAO;
import com.spnsolo.library.util.EntityToString;
import com.spnsolo.library.util.StringToEntity;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorsAndBooksFileDAO implements BaseDAO<AuthorAndBook> {
    private final String file = "db_in_files/authorWithBook.csv";
    private final String[] header = {"Author ID", "Book ID", "id", "Availability"};
    private List<String[]> csvData = new ArrayList<>();
    List<AuthorAndBook> allAuthorsAndBooks;

    public AuthorsAndBooksFileDAO() {
        csvData.add(header);
    }

    @Override
    public boolean create(AuthorAndBook authorAndBook) {
        csvData.add(EntityToString.authorAndBookToString(authorAndBook));
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Deprecated
    @Override
    public boolean update(AuthorAndBook authorAndBook) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                if(csvData.get(i)[2].equals(Integer.toString(id))){
                    csvData.remove(i);
                    try(CSVWriter writer = new CSVWriter(new FileWriter(file))){
                        writer.writeAll(csvData);
                    }
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Deprecated
    @Override
    public AuthorAndBook readById(Integer id) throws NonexistentIdException {
        return null;
    }

    @Override
    public List<AuthorAndBook> readAll() {
        allAuthorsAndBooks = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            csvData = reader.readAll();
            csvData.set(0,header);
            for (int i = 1; i < csvData.size(); i++) {
                allAuthorsAndBooks.add(StringToEntity.stringToAuthorAndBook(csvData.get(i)));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return allAuthorsAndBooks;
    }

    public List<AuthorAndBook> readAllByAuthorId(Integer id) {
        allAuthorsAndBooks = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                if (csvData.get(i)[0].equals(String.valueOf(id))) {
                    allAuthorsAndBooks.add(StringToEntity.stringToAuthorAndBook(csvData.get(i)));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return allAuthorsAndBooks;
    }

    public List<AuthorAndBook> readAllByBookId(Integer id) {
        allAuthorsAndBooks = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                if (csvData.get(i)[1].equals(String.valueOf(id))) {
                    allAuthorsAndBooks.add(StringToEntity.stringToAuthorAndBook(csvData.get(i)));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return allAuthorsAndBooks;
    }
}
