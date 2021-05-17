package com.spnsolo.library.exception;

public class EmptyLibraryException extends Exception{
    private String massage;

    public EmptyLibraryException(){massage = "Library is empty";}
    public EmptyLibraryException(String massage){this.massage = massage;}

    @Override
    public String toString() {
        return massage;
    }
}
