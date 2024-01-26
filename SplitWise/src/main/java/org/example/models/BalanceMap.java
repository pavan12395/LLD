package org.example.models;


import java.util.*;
public class BalanceMap {
    private Map<String,Balance> balances;

    public BalanceMap(){
        this.balances = new HashMap<>();
    }

    public BalanceMap(Map<String, Balance> balances) {
        this.balances = balances;
    }

    public Map<String, Balance> getBalances() {
        return balances;
    }

    public void setBalances(Map<String, Balance> balances) {
        this.balances = balances;
    }

    public BalanceMap add(BalanceMap balanceMap) {
        Map<String,Balance> result = new HashMap<>();
        Map<String,Balance> balances = balanceMap.getBalances();
        for(String key : this.balances.keySet()){
            if (balances.containsKey(key)) {
                result.put(key,this.balances.get(key).add(balances.get(key)));
            }
        }
        return new BalanceMap(result);
    }

    public void addAmount(String dest,double amount){
        balances.putIfAbsent(dest,new Balance(0.0));
        Balance addedBalance = balances.get(dest).add(new Balance(amount));
        balances.put(dest,addedBalance);
    }
}