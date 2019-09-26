package com.creativityskills.jotech.model;

public class CashDrawer {
    private int id;
    private Denomination denomination;
    private long count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CashDrawer{" +
                "id=" + id +
                ", denomination=" + denomination +
                ", count=" + count +
                '}';
    }
}
