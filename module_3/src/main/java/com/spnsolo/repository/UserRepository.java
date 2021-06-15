package com.spnsolo.repository;

import com.spnsolo.dto.CheckedUserDto;
import com.spnsolo.exception.NonExistedUserException;
import org.hibernate.Session;

public interface UserRepository {
    boolean checkUser(CheckedUserDto checkedUserDto) throws NonExistedUserException;
}
