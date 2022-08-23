package com.caliber.flatbudget.models;

import lombok.Data;

@Data
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

        int dollars = this.getDollar() + money.getDollar();
        int cents = this.getCents() + money.getCents();

        if (cents >= 100) {
            tempDollars = (int) cents / 100;
            cents = cents % 100;
        }

        setDollar(dollars + tempDollars);
        setCents(cents);
    }

    public void subtractMoney(Money money) {
        int tempDollars = this.getDollar() - money.getDollar();
        int tempCents = this.getCents() - money.getCents();


        if (money.cents > this.cents) {
            tempCents = 100 - this.getCents() + money.getCents();
            tempDollars -= 1;
        }
    }

}
