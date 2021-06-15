package com.spnsolo.exception;

public class IllegalTransactionException extends Exception{
    private String massage;

    public IllegalTransactionException(){ massage = "Invalid value of transaction"; }
    public IllegalTransactionException(String massage){ this.massage = massage; }

    @Override
    public String toString() { return massage; }
}
