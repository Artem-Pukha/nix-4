package com.spnsolo.io;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.spnsolo.dto.AccountDto;
import com.spnsolo.dto.TransactionOutput;
import com.spnsolo.entity.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputCsv {
    private final CSVWriter writer;
    private final String[] header = {"Date","value","income","balance"};
    private final List<String[]> csvData = new ArrayList<>();

    public OutputCsv(CSVWriter writer){ this.writer = writer; }

    public void setCsv(List<TransactionOutput>transactions , AccountDto account)  {
        csvData.add(header);
        if(!transactions.isEmpty()) {
            Double income = 0D;
            for (TransactionOutput t : transactions) {
                income += t.getValue();
            }
            TransactionOutput output = transactions.get(0);
            String[] row = {output.getDate().toString(), output.getValue().toString(), income.toString()
                    , account.getBalance().toString()};
            csvData.add(row);
            for (int i = 1; i < transactions.size(); i++) {
                output = transactions.get(i);
                row = new String[]{output.getDate().toString(), output.getValue().toString()};
                csvData.add(row);
            }
        }
        writer.writeAll(csvData);
    }
}
