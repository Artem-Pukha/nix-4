package com.spnsolo.service;

import com.spnsolo.dto.TransactionDto;
import com.spnsolo.exception.IllegalTransactionException;

public interface TransactionService {
    void addTransaction(TransactionDto transactionDto) throws IllegalTransactionException;
}
