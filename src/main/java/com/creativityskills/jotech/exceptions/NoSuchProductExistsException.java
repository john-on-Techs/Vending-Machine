package com.creativityskills.jotech.exceptions;

public class NoSuchProductExistsException extends Throwable {
    String message;

    public NoSuchProductExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
