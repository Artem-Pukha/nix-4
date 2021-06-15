package com.spnsolo.repository.impl;

import com.spnsolo.dto.AccountDto;
import com.spnsolo.dto.CategoryDto;
import com.spnsolo.dto.TransactionOutput;
import com.spnsolo.entity.Account;
import com.spnsolo.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportDAO {

    private static final String GET_ALL_FOR_PERIOD = "SELECT * FROM transactions WHERE account_id = ?";
    private final Connection connection;

    public enum Period{
        ALL(0),
        YEAR(360),
        MONTH(31),
        WEEK(7),
        DAY(1);

        private int value;
        Period(int value) {
            this.value = value;
        }
        public int getValue(){return value;}
    }

    public ReportDAO(Connection connection){
        this.connection = connection;
    }

    public List<TransactionOutput> getAllTransactionsForPeriod(AccountDto account, Period period){
        List<TransactionOutput> transactionOutputs = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_FOR_PERIOD)){
            preparedStatement.setLong(1,account.getId());
            ResultSet set = preparedStatement.executeQuery();

            TransactionOutput transactionOutput;
            while (set.next()){
                transactionOutput = new TransactionOutput();
                transactionOutput.setValue(set.getDouble("value"));

                LocalDate date = set.getDate("date").toLocalDate();
                transactionOutput.setDate(date.atStartOfDay(ZoneOffset.UTC).toInstant());

                transactionOutputs.add(transactionOutput);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        checkPeriod(transactionOutputs,period);
        return transactionOutputs;
    }
    private void checkPeriod(List<TransactionOutput> transactionOutputs,Period period){
        if(period.value > 0) {
            Instant required = Instant.now();
            required = required.minusMillis(period.value * 84600L);
            for (int i = 0; i < transactionOutputs.size(); i++) {
                if(transactionOutputs.get(i).getDate().compareTo(required)<0) {
                    transactionOutputs.remove(transactionOutputs.get(i));
                }
            }
        }
    }
}
