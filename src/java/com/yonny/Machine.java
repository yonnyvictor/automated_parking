package com.yonny;

import com.yonny.data.Card;
import com.yonny.data.Charge;
import com.yonny.data.Payment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Machine {

    private final List<Charge> charges;
    private final TreeSet<Double> acceptedDenominations, changeDenominations;

    public Machine(List<Charge> charges, TreeSet<Double> acceptedDenominations, TreeSet<Double> changeDenominations) {
        this.charges = charges;
        this.acceptedDenominations = acceptedDenominations;
        this.changeDenominations = changeDenominations;
    }

    public Card exitParkingRequest(Card card){
        if(isCardValid(card)){
            //set card exit time
            card.setExitTime(LocalDateTime.now());

            //calculate and set charges
            card.setCharge(calculateCharges(card));
        }

        return card;
    }

    public List<Double> parkingPayment(Card card, double ...payments){
        //receive payment
        double tendered = receivePayment(payments);

        //add payment to card
        if(tendered >= card.getCharge()){
            double change = tendered - card.getCharge();

            card.setPayment(new Payment(card.getUid(), tendered, change));

            if(change > 0)
                return returnChange(change);
        }


        return null;
    }

    protected double receivePayment(double ...amounts){
        double total = 0d;
        //check received amounts for valid denominations and reject accordingly
        for (double amount: amounts) {
            if(acceptedDenominations.contains(amount))
                total += amount;
        }
        return total;
    }

    protected List<Double> returnChange(double balance){
        List<Double> list = new ArrayList<>();

        //assemble change using greedy algorithm;
        int index = 0;

        double[] amounts = new double[changeDenominations.size()];

        for (Double amount : changeDenominations) {
            amounts[index] = amount;
            index++;
        }

        while (balance != 0){
            for (int i = amounts.length - 1; i >= 0 ; i--) {
                if (amounts[i] <= balance) {
                    balance -= amounts[i];
                    list.add(amounts[i]);
                    i++;
                }
            }
        }

        return list;
    }

    protected double calculateCharges(Card card){
        long minutes = card.getEntryTime().until(card.getExitTime(), ChronoUnit.MINUTES);

        for (Charge charge : charges) {
            if(minutes >= charge.getMin() && minutes <= charge.getMax())
                return charge.getAmount();
        }

        return 0d;
    }

    protected boolean isCardValid(Card card){
        return card.getPayment() == null;
    }
}
