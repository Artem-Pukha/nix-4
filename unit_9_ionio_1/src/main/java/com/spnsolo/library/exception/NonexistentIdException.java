package com.spnsolo.library.exception;

public class NonexistentIdException extends RuntimeException{
    @Override
    public String toString() {
        return "Nonexistent id";
    }
}
