package org.example.test;


import org.example.SplitWiseEngine;
import org.example.models.*;
import org.example.service.ExpenseService;
import org.example.service.GroupService;
import org.example.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSplitWise {

    @After
    public void AfterTestCase(){
        ExpenseService.setInstance(null);
        GroupService.setInstance(null);
        UserService.setInstance(null);
    }
    @Test
    public void TestGetGroupExpenses(){
        try {
            SplitWiseEngine server = new SplitWiseEngine();
            User user = new User("1", "hello");
            User user1 = new User("2", "hello1");
            User user2 = new User("3", "hello1");
            List<String> userIds = new ArrayList<>();
            userIds.add(user.getId());
            userIds.add(user1.getId());
            userIds.add(user2.getId());
            Group group = new Group("1", "hello_world", userIds);
            server.addUser(user);
            server.addUser(user1);
            server.addUser(user2);
            server.addGroup(group);
            Map<String, Balance> map1 = new HashMap<>();
            map1.put("1",new Balance(-10.0));
            map1.put("2",new Balance(-20.0));
            map1.put("3",new Balance(30.0));
            BalanceMap balanceMap = new BalanceMap(map1);
            Expense expense = new Expense("1","eat","1",userIds,balanceMap,"1");
            server.addExpense(expense);
            Expense groupExpense = server.getGroupExpenses("1").get(0);
            Map<String,Balance> map = groupExpense.getBalance().getBalances();
            Assert.assertTrue(map.get("1").getAmount()==-10.0 && map.get("2").getAmount()==-20.0 && map.get("3").getAmount()==30.0);
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    public Map<String,Balance> getFirstBalanceMap(){
        Map<String,Balance> ans = new HashMap<>();
        ans.put("1",new Balance(-40.0));
        ans.put("2",new Balance(20.0));
        ans.put("3",new Balance(10.0));
        ans.put("4",new Balance(10.0));
        return ans;
    }

    public Map<String,Balance> getSecondBalanceMap(){
        Map<String,Balance> ans = new HashMap<>();
        ans.put("1",new Balance(-20.0));
        ans.put("2",new Balance(100.0));
        ans.put("3",new Balance(-80.0));
        ans.put("4",new Balance(0.0));
        return ans;
    }

    public Map<String,Balance> getThirdBalanceMap(){
        Map<String,Balance> ans = new HashMap<>();
        ans.put("1",new Balance(50.0));
        ans.put("2",new Balance(50.0));
        ans.put("3",new Balance(20.0));
        ans.put("4",new Balance(-120.0));
        return ans;
    }

    public List<Expense> getExpenses(String groupId,List<String> userIds){
        List<Expense> expenses = new ArrayList<>();
        Map<String,Balance> balanceMap = getFirstBalanceMap();
        expenses.add(new Expense("1","eat",groupId,userIds,new BalanceMap(balanceMap),"1"));
        balanceMap = getSecondBalanceMap();
        expenses.add(new Expense("2","eat",groupId,userIds,new BalanceMap(balanceMap),"1"));
        balanceMap = getThirdBalanceMap();
        expenses.add(new Expense("3","eat",groupId,userIds,new BalanceMap(balanceMap),"1"));
        return expenses;
    }

    @Test
    public void TestGetPaymentGraph(){
        try {
            SplitWiseEngine server = new SplitWiseEngine();
            User user = new User("1", "hello");
            User user1 = new User("2", "hello1");
            User user2 = new User("3", "hello1");
            User user3 = new User("4","hello1");
            List<String> userIds = new ArrayList<>();
            userIds.add(user.getId());
            userIds.add(user1.getId());
            userIds.add(user2.getId());
            userIds.add(user3.getId());
            Group group = new Group("1", "hello_world", userIds);
            server.addUser(user);
            server.addUser(user1);
            server.addUser(user2);
            server.addUser(user3);
            server.addGroup(group);
            List<Expense> expenses = getExpenses("1",userIds);
            for(Expense expense : expenses){
                server.addExpense(expense);
            }
            List<Expense> groupExpenses = server.getGroupExpenses("1");
            Assert.assertTrue(groupExpenses.size()==3);
            PaymentGraph paymentGraph = server.getSimplifiedPayments("1","1");
            Map<String,BalanceMap> balances = paymentGraph.getGraph();
            Assert.assertTrue(balances.get("1").getBalances().get("2").getAmount()==10.0);
            Assert.assertTrue(balances.get("3").getBalances().get("2").getAmount()==50.0);
            Assert.assertTrue(balances.get("4").getBalances().get("2").getAmount()==110.0);
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }
}