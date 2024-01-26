package org.example.models;

public class UserBalance {
    String userId;

    double amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UserBalance(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }
}