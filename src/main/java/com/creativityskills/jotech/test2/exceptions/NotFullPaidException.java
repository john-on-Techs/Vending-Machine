package com.creativityskills.jotech.test2.exceptions;

public class NotFullPaidException extends Throwable {
    private String message;
    private long remaining;

    public NotFullPaidException(String message, long remaining) {
        this.message = message;
        this.remaining = remaining;
    }

    public long getRemaining(){
        return remaining;
    }

    @Override
    public String getMessage(){
        return message + remaining;
    }
}
