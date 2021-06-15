package com.spnsolo.service.impl;

import com.spnsolo.dto.TransactionDto;
import com.spnsolo.exception.IllegalTransactionException;
import com.spnsolo.repository.TransactionRepository;
import com.spnsolo.repository.impl.MainRepository;
import com.spnsolo.service.TransactionService;
import org.hibernate.Session;


public class TransactionServiceImpl implements TransactionService {

    TransactionRepository repository;

    public TransactionServiceImpl(Session session){repository = new MainRepository(session);}
    @Override
    public void addTransaction(TransactionDto transactionDto) throws IllegalTransactionException {
        double balance = transactionDto.getAccountDto().getBalance() + transactionDto.getValue();
        if(balance < 0) throw new IllegalTransactionException();
        transactionDto.getAccountDto().setBalance(balance);
        repository.addTransaction(transactionDto);
    }
}
