package com.spnsolo.service;

import com.spnsolo.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> readAllByUserId(Long id);
}
