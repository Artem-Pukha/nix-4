package com.spnsolo.service.impl;

import com.spnsolo.dto.AccountDto;
import com.spnsolo.repository.AccountRepository;
import com.spnsolo.repository.impl.MainRepository;
import com.spnsolo.service.AccountService;
import org.hibernate.Session;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    AccountRepository repository;

    public AccountServiceImpl(Session session){
        repository = new MainRepository(session);
    }
    @Override
    public List<AccountDto> readAllByUserId(Long id) {
        if(id <= 0)throw new IllegalArgumentException("Invalid id");
        return repository.readAllByUserId(id);
    }
}
