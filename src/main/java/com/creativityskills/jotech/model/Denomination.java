package com.creativityskills.jotech.model;

public enum Denomination {
    THOUSAND_NOTE(1000),
    FIVE_HUNDRED_NOTE(500),
    TWO_HUNDRED_NOTE(200),
    ONE_HUNDRED_NOTE(100),
    FIFTY_NOTE(50),
    TWENTY_COIN(20),
    TEN_COIN(10),
    FIVE_COIN(5),
    ONE_COIN(1);
    int value;

    Denomination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
