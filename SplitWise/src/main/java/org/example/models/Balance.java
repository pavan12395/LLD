package org.example.models;

public class Balance {
    double amount;

    public Balance(double amount){
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Balance add(Balance balance){
        return new Balance(this.amount + balance.getAmount());
    }
}