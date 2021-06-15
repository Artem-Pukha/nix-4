package com.spnsolo.exception;

public class ZeroTransactionException extends Exception{
    @Override
    public String toString() {
        return "Zero value of transaction";
    }
}
