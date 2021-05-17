package com.spnsolo.library.exception;

public class NonexistentIdException extends Exception{
    private String massage;

    public NonexistentIdException(){massage = "Nonexistent id";}
    public NonexistentIdException(String massage){this.massage = massage;}
    @Override
    public String toString() {
        return massage;
    }
}
