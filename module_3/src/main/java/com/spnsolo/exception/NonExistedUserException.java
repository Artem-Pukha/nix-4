package com.spnsolo.exception;

public class NonExistedUserException extends Exception{
    private String massage;

    public NonExistedUserException(){ massage = "User not found"; }
    public NonExistedUserException(Long id,String email){ massage = "User with id = " + id +", email: "+email+ " not found"; }

    @Override
    public String toString() { return massage; }
}
