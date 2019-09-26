package com.creativityskills.jotech.exception;

public class NoEnoughChangeException extends Throwable {
    public NoEnoughChangeException() {
        super("Machine is not to give back the required balance.\n  Try again later.Sorry or the inconvenience caused");
    }
}
