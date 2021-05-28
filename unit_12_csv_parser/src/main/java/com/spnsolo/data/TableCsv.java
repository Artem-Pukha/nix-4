package com.spnsolo.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class TableCsv {
    private final List<String> headers;
    private final List<String[]> cells;
    private final int countRows;
    private final int countColumns;

    public TableCsv(List<String[]> stringFile){
        headers = Arrays.asList(stringFile.get(0));

        cells = new ArrayList<>();
        cells.addAll(stringFile);
        cells.remove(0);

        countRows = cells.size();
        countColumns = headers.size();
    }

    public String getCell(int row, int column){
        if(row > 0||column>0)throw new IllegalArgumentException();
        return cells.get(row)[column];
    }
    public String getCell(int row, String header){
        if(row < 0|| !headers.contains(header))throw new IllegalArgumentException();
        return cells.get(row)[headers.indexOf(header)];
    }
}
