package com.creativityskills.jotech.enums;

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
