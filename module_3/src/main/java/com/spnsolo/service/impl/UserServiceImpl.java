package com.spnsolo.service.impl;

import com.spnsolo.dto.CheckedUserDto;
import com.spnsolo.exception.NonExistedUserException;
import com.spnsolo.repository.UserRepository;
import com.spnsolo.repository.impl.MainRepository;
import com.spnsolo.service.UserService;
import org.hibernate.Session;

public class UserServiceImpl implements UserService {
    UserRepository repository;

    public UserServiceImpl(Session session){
        repository = new MainRepository(session);
    }
    @Override
    public boolean checkUser(CheckedUserDto checkedUserDto) throws NonExistedUserException {
        if(checkedUserDto.getId() <= 0)return false;
        return repository.checkUser(checkedUserDto);
    }
}
