package com.spnsolo.library.repository.impl.file;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.exception.NonexistentIdException;
import com.spnsolo.library.repository.BaseDAO;
import com.spnsolo.library.util.EntityToString;
import com.spnsolo.library.util.StringToEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorsFileDAO implements BaseDAO<Author> {
    private final String file = "db_in_files/authors.csv";
    private final String[]header = {"First name","Second name","Id","Availability"};
    private List<String[]> csvData = new ArrayList<>();

    public AuthorsFileDAO(){
        csvData.add(header);
    }

    @Override
    public boolean create(Author author) {
        csvData.add(EntityToString.authorToString(author));
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Author author) {
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                if(csvData.get(i)[2].equals(Integer.toString(author.getId()))){
                    csvData.set(i,EntityToString.authorToString(author));
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
    public Author readById(Integer id) throws NonexistentIdException {
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                if(csvData.get(i)[2].equals(Integer.toString(id))){
                    return StringToEntity.stringToAuthor(csvData.get(i));
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        throw  new NonexistentIdException();
    }

    @Override
    public List<Author> readAll() {
        List<Author> allAuthors = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            csvData = reader.readAll();
            csvData.set(0,header);
            for (int i = 1; i < csvData.size(); i++) {
                allAuthors.add(StringToEntity.stringToAuthor(csvData.get(i)));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return allAuthors;
    }
}
