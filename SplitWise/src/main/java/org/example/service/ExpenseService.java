package org.example.service;


import org.example.models.*;

import java.util.*;

public class ExpenseService {

    private Map<String,Expense> expensesIdMap;

    private Map<String, List<Expense>> groupIdMap;

    private static ExpenseService instance;

    public static ExpenseService getInstance(){
        synchronized (ExpenseService.class){
            if(instance == null){
                synchronized (ExpenseService.class){
                    instance = new ExpenseService();
                }
            }
        }
        return instance;
    }

    public static void setInstance(ExpenseService expenseService){
        synchronized (ExpenseService.class){
            instance = expenseService;
        }
    }
    public ExpenseService() {
        expensesIdMap = new HashMap<>();
        groupIdMap = new HashMap<>();
    }

    public boolean contains(String id){
        return expensesIdMap.containsKey(id);
    }

    public void addExpense(Expense expense) throws  Exception {
        if(this.contains(expense.getId())){
            throw new Exception("Expense Already Exists");
        }
        else if(!expense.validate()){
            throw new Exception("Please add a valid expense");
        }
        else {
            this.expensesIdMap.put(expense.getId(),expense);
            if(!expense.getGroupId().equals("") && GroupService.getInstance().contains(expense.getGroupId()))
            {
                this.groupIdMap.putIfAbsent(expense.getGroupId(),new ArrayList<>());
                this.groupIdMap.get(expense.getGroupId()).add(expense);
            }
        }
    }

    public List<Expense> getGroupExpenses(String groupId){
        return groupIdMap.containsKey(groupId) ? groupIdMap.get(groupId) : new ArrayList<>();
    }

    public void editExpense(Expense expense,String userId) throws  Exception {
        if(!this.contains(expense.getId())){
            throw new Exception("Expense doesnt exist");
        }
        else if(!expense.validate()){
            throw new Exception("Please add a valid expense");
        }
        else if(!expense.getOwnerId().equals(userId)){
            throw new Exception("User cant edit the expense");
        }
        else {
            this.expensesIdMap.put(expense.getId(),expense);
            if(!expense.getGroupId().equals(""))
            {
                List<Expense> groupExpenses = this.getGroupExpenses(expense.getGroupId());
                for(int i=0;i<groupExpenses.size();i++){
                    Expense groupExpense = groupExpenses.get(i);
                    if(groupExpense.getId().equals(expense.getId())){
                        groupExpenses.set(i,expense);
                    }
                }
            }
        }
    }

    private BalanceMap calculateSum(List<Expense> expenses) {
       if(expenses.size()==0){return null;}
       else if(expenses.size()==1){return expenses.get(0).getBalance();}
       else {
           BalanceMap result = expenses.get(0).getBalance();
           for(int i=1;i<expenses.size();i++){
               result = result.add(expenses.get(i).getBalance());
           }
           return result;
       }
    }

    private PaymentGraph calculatePaymentGraphFromBalances(Map<String,Balance> balances){
        PaymentGraph paymentGraph = new PaymentGraph();
        PriorityQueue<UserBalance> positives = new PriorityQueue<UserBalance>(Comparator.comparing(b -> b.getAmount()));
        PriorityQueue<UserBalance> negatives = new PriorityQueue<UserBalance>(Comparator.comparing(b -> -b.getAmount()));
        for(String key : balances.keySet()){
            if(balances.get(key).getAmount() > 0){
                positives.add(new UserBalance(key,balances.get(key).getAmount()));
            }
            else if(balances.get(key).getAmount() < 0){
                negatives.add(new UserBalance(key,-balances.get(key).getAmount()));
            }
        }
        while(negatives.size()!=0 && positives.size()!=0) {
            UserBalance pos = positives.poll();
            UserBalance neg = negatives.poll();
            String source = neg.getUserId();
            String destination = pos.getUserId();
            if(pos.getAmount() > neg.getAmount()){
                pos.setAmount(pos.getAmount()-neg.getAmount());
                double amountTransferred = neg.getAmount();
                paymentGraph.addPayment(source,destination,amountTransferred);
                positives.add(pos);
            }
            else if(pos.getAmount() == neg.getAmount()){
                double amountTransferred = neg.getAmount();
                paymentGraph.addPayment(source,destination,amountTransferred);
            }
            else {
                neg.setAmount(neg.getAmount()-pos.getAmount());
                double amountTransferred = pos.getAmount();
                paymentGraph.addPayment(source,destination,amountTransferred);
                negatives.add(neg);
            }
        }
        return paymentGraph;
    }


    private PaymentGraph calculatePaymentGraph(List<Expense> expenses){
        BalanceMap balanceMap = calculateSum(expenses);
        Map<String, Balance> balances = balanceMap.getBalances();
        return  calculatePaymentGraphFromBalances(balances);
    }

    public PaymentGraph getPaymentGraph(String groupId) throws  Exception {
        if(this.contains(groupId)){
            List<Expense> expenses  = this.groupIdMap.get(groupId);
            return calculatePaymentGraph(expenses);
        }
        else {
            throw new Exception("Group doesnt exist");
        }
    }


}