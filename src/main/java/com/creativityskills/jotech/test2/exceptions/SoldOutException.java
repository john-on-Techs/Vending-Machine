package com.creativityskills.jotech.test2.exceptions;

public class SoldOutException extends Throwable {
    private String message;

    public SoldOutException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
