package com.spnsolo.repository;

import com.spnsolo.dto.AccountDto;
import org.hibernate.Session;

import java.util.List;

public interface AccountRepository {
    List<AccountDto> readAllByUserId(Long id);
}
