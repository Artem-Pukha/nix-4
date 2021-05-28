package com.spnsolo.io;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

public class InputCsv {
    private final CSVReader reader;

    public InputCsv(CSVReader reader){ this.reader = reader; }

    public List<String[]> getCsv() throws IOException, CsvException { return reader.readAll(); }
}
