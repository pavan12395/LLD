package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class PaymentGraph {
    private Map<String,BalanceMap> graph;

    public Map<String, BalanceMap> getGraph() {
        return graph;
    }

    public void setGraph(Map<String, BalanceMap> graph) {
        this.graph = graph;
    }

    public PaymentGraph(){
        graph = new HashMap<>();
    }

    public PaymentGraph(Map<String, BalanceMap> graph) {
        this.graph = graph;
    }

    public void addPayment(String src,String dest,double amount) {
        graph.putIfAbsent(src,new BalanceMap());
        graph.get(src).addAmount(dest,amount);
    }
}