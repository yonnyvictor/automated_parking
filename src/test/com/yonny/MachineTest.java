package com.yonny;

import com.yonny.data.Card;
import com.yonny.data.Charge;
import com.yonny.data.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

class MachineTest{

    private static Machine machine;

    @BeforeAll
    public static void setup() {

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

        machine = new Machine(charges, acceptedDenominations, changeDenominations);
    }

    @Test
    public void usedCard() {
        Card card = new Card();
        card.setExitTime(card.getEntryTime().plusMinutes(15));
        card.setPayment(new Payment(card.getUid(), 50, 20));
        Assertions.assertFalse(machine.isCardValid(card));
    }

    @Test
    public void receiveUnknownDenominations() {
        double amount = machine.receivePayment(35, 25);
        Assertions.assertEquals(0d, amount);
    }

    @Test
    public void changeWithAcceptedDenominations() {
        List<Double> changes = Arrays.asList(50d, 20d);
        Assertions.assertEquals(changes, machine.returnChange(70d));
    }

    @Test
    public void parkingCharges() {
        Card card = new Card();
        card.setExitTime(card.getEntryTime().plusMinutes(115));
        Assertions.assertEquals(100d, machine.calculateCharges(card));
    }

}