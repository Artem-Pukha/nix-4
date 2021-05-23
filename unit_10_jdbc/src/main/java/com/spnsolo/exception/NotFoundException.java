package com.spnsolo.exception;

public class NotFoundException extends Exception{
    private final String defaultMass = "Not found";
    private final String massage;
    public NotFoundException(){
        massage = defaultMass;
    }
    public NotFoundException(String massage){
        this.massage = defaultMass + massage;
    }
    @Override
    public String toString() {
        return massage;
    }
}
