package com.creativityskills.jotech.exception;

public class InsufficientProductQuantityException extends Exception {
    public InsufficientProductQuantityException() {
        super("Product out of stock");
    }
}
