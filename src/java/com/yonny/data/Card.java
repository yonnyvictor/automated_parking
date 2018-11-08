package com.yonny.data;

import java.time.LocalDateTime;

public class Card {

    private long uid;
    private double charge;
    private LocalDateTime entryTime, exitTime;
    private Payment payment;

    public Card() {
        uid = System.currentTimeMillis();
        entryTime = LocalDateTime.now();
    }

    public long getUid() {
        return uid;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
