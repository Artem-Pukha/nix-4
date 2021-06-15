package com.spnsolo.repository;

import com.spnsolo.dto.TransactionDto;
import org.hibernate.Session;

import java.sql.SQLException;

public interface TransactionRepository {
    void addTransaction(TransactionDto transactionDto);
}
