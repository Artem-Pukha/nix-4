package com.spnsolo.service;

import com.spnsolo.dto.CheckedUserDto;
import com.spnsolo.exception.NonExistedUserException;

public interface UserService {
    boolean checkUser(CheckedUserDto checkedUserDto) throws NonExistedUserException;
}
