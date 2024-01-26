package org.example.models;


import java.util.*;

public class Expense  {
    private String id;

    private String title;

    private String groupId;

    private List<String> userIds;

    private BalanceMap balance;

    private String ownerId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public BalanceMap getBalance() {
        return balance;
    }

    public void setBalance(BalanceMap balance) {
        this.balance = balance;
    }

    public Expense(String id, String title, String groupId, List<String> userIds, BalanceMap balance,String ownerId) {
        this.id = id;
        this.title = title;
        this.groupId = groupId;
        this.userIds = userIds;
        this.balance = balance;
        this.ownerId = ownerId;
    }

    public boolean validate(){
        double totalAmount = 0.0;
        Map<String,Balance> balanceMap = this.getBalance().getBalances();
        for(Balance currentBalance : balanceMap.values()){
            totalAmount += currentBalance.getAmount();
        }
        return totalAmount==0.0;
    }

}