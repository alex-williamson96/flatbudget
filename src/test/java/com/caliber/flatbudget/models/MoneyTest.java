package com.caliber.flatbudget.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void addMoneyTest() {
        Money startingMoney = new Money(145, 65);
        Money inflow = new Money();
        inflow.addMoney(new Money(25, 37));
        startingMoney.addMoney(inflow);

        Assertions.assertEquals(171, startingMoney.getDollar());
        Assertions.assertEquals(2, startingMoney.getCents());

        startingMoney.addMoney(new Money(0, 99));

        Assertions.assertEquals(172, startingMoney.getDollar());
        Assertions.assertEquals(1, startingMoney.getCents());
    }

    @Test
    void subtractMoneyTest() {
        Money startingMoney1 = new Money(157, 77);
        Money transaction1 = new Money(58, 78);
        startingMoney1.subtractMoney(transaction1);

        Money startingMoney2 = new Money(12, 34);
        Money transaction2 = new Money(13, 0);
        startingMoney2.subtractMoney(transaction2);

        Assertions.assertEquals(98, startingMoney1.getDollar(), "Subtraction failed (dollars).");
        Assertions.assertEquals(99, startingMoney1.getCents(), "Subtraction failed (cents).");

        Assertions.assertEquals(0, startingMoney2.getDollar());
        Assertions.assertEquals(-66, startingMoney2.getCents());

        startingMoney1.subtractMoney(new Money(98, 99));

        Assertions.assertEquals(new Money(), startingMoney1);

        startingMoney1.subtractMoney(new Money(1, 12));

        Assertions.assertEquals(new Money(-1, -12), startingMoney1);
    }
}