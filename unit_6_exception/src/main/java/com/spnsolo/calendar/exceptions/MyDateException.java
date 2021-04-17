package com.spnsolo.calendar.exceptions;

public class MyDateException extends Exception{
    private String message = "Incorrect entered ";

    public MyDateException(){
        
    }
    public MyDateException(String str){
        message += str;
    }

    @Override
    public String toString() {
        return message;
    }
}
