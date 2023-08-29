package com.caliber.flatbudget.models.internal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Data
@Getter
@Setter
public class Money {

    private Integer dollar;

    private Integer cents;

    public Money() {
        dollar = 0;
        cents = 0;
    }

    public Money(Integer dollar, Integer cents) {
        this.dollar = dollar;
        this.cents = cents;
    }

    public void addMoney(Money money) {
        int tempDollars = 0;

        int dollars = getDollar() + money.getDollar();
        int cents = getCents() + money.getCents();

        if (cents >= 100) {
            tempDollars = cents / 100;
            cents = cents % 100;
        }

        setDollar(dollars + tempDollars);
        setCents(cents);
    }

    public void subtractMoney(Money money) {
        int tempCents = getCents() - money.getCents();

        if (tempCents < 0 && getDollar() > 0) {
            setDollar(getDollar() - 1);
            tempCents += 100;
        }

        int tempDollars = getDollar() - money.getDollar();

        if (tempDollars < 0 && tempCents > 0) {
            tempDollars++;
            tempCents -= 100;
        }

        setDollar(tempDollars);
        setCents(tempCents);
    }

    public double moneyToDouble() {
        int dollar = getDollar();
        int cents = getCents();

        return Double.parseDouble(dollar + "." + cents);
    }

}
