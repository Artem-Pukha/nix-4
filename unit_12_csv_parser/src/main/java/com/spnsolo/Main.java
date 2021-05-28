package com.spnsolo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.spnsolo.data.TableCsv;
import com.spnsolo.data.User;
import com.spnsolo.en.Role;
import com.spnsolo.io.InputCsv;
import com.spnsolo.parser.CsvParser;
import com.spnsolo.until.FileResourcesUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("//////////Unit 12//////////");
        String path = FileResourcesUtil.getPathToResourcesFile("csv/input.csv");
        try (CSVReader reader = new CSVReader(new FileReader(path))){
            InputCsv inputCsv = new InputCsv(reader);
            TableCsv tableCsv = new TableCsv(inputCsv.getCsv());
            CsvParser parser = new CsvParser();
            List<User> users = parser.getCsvAsListObjects(tableCsv,User.class);
            users.forEach(System.out::println);
            System.out.println("///////////////////////////");

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

    }
}
