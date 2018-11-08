package com.yonny;

import com.yonny.data.Card;
import com.yonny.data.Charge;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        System.out.println("Automated Parking System");

        List<Charge> charges = new ArrayList<>();
        charges.add(new Charge(0, 14, 30d));
        charges.add(new Charge(15, 30, 50d));
        charges.add(new Charge(31, 60, 70d));
        charges.add(new Charge(61, 120, 100d));
        charges.add(new Charge(121, 240, 200d));
        charges.add(new Charge(241, 480, 500d));
        charges.add(new Charge(481, Long.MAX_VALUE, 1000d));

        TreeSet<Double> acceptedDenominations = new TreeSet<>();
        acceptedDenominations.add(10d);
        acceptedDenominations.add(20d);
        acceptedDenominations.add(50d);
        acceptedDenominations.add(100d);
        acceptedDenominations.add(200d);

        TreeSet<Double> changeDenominations = new TreeSet<>();
        changeDenominations.add(10d);
        changeDenominations.add(20d);
        changeDenominations.add(50d);
        changeDenominations.add(100d);

        Machine machine = new Machine(charges, acceptedDenominations, changeDenominations);

        Card card = new Card();

        machine.exitParkingRequest(card);

        System.out.println("Change: " + machine.parkingPayment(card, 200));
    }
}
