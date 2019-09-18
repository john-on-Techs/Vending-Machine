package com.creativityskills.jotech.exception;

public class InsuficientAmountException extends Exception {

    public InsuficientAmountException() {
       super("Money is less than required amount");
    }
}
