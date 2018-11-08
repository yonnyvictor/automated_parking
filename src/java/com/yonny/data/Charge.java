package com.yonny.data;

public class Charge {

    private long min, max;
    private double amount;

    public Charge(long min, long max, double amount) {
        this.min = min;
        this.max = max;
        this.amount = amount;
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public double getAmount() {
        return amount;
    }
}
