package com.yonny.data;

public class Payment {

    private long cardId;
    private double tendered, change;


    public Payment(long cardId, double tendered, double change) {
        this.cardId = cardId;
        this.tendered = tendered;
        this.change = change;
    }

    public long getCardId() {
        return cardId;
    }

    public double getTendered() {
        return tendered;
    }

    public double getChange() {
        return change;
    }
}
