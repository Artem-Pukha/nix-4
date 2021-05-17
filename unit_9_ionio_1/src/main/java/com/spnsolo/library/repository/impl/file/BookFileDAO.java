package com.spnsolo.library.repository.impl.file;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.Book;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.BaseDAO;
import com.spnsolo.library.util.EntityToString;
import com.spnsolo.library.util.StringToEntity;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookFileDAO implements BaseDAO<Book> {
    private final String file = "db_in_files/books.csv";
    private final String[]header = {"Name","Count of pages","Id","Availability"};
    private List<String[]> csvData = new ArrayList<>();

    public BookFileDAO(){
        csvData.add(header);
    }
    @Override
    public boolean create(Book book) {
        csvData.add(EntityToString.bookToString(book));
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Book book) {
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                if(csvData.get(i)[2].equals(Integer.toString(book.getId()))){
                    csvData.set(i,EntityToString.bookToString(book));
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

    @Override
    public Book readById(Integer id) throws NonexistentIdException {
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                if(csvData.get(i)[2].equals(Integer.toString(id))){
                    return StringToEntity.stringToBook(csvData.get(i));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        throw  new NonexistentIdException();
    }

    @Override
    public List<Book> readAll() {
        List<Book> allBooks = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            csvData = reader.readAll();
            csvData.set(0,header);
            for (int i = 1; i < csvData.size(); i++) {
                allBooks.add(StringToEntity.stringToBook(csvData.get(i)));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return allBooks;
    }
}
