package org.example;

import org.example.models.Expense;
import org.example.models.Group;
import org.example.models.PaymentGraph;
import org.example.models.User;
import org.example.service.ExpenseService;
import org.example.service.GroupService;
import org.example.service.UserService;

import java.util.List;

public class SplitWiseEngine {

    public void addGroup(Group group) throws  Exception {
        GroupService.getInstance().addGroup(group);
    }

    public void addUser(User user) throws   Exception {
        UserService.getInstance().addUser(user);
    }

    public void addExpense(Expense expense) throws  Exception {
        ExpenseService.getInstance().addExpense(expense);
    }

    public List<Expense> getGroupExpenses(String groupId) throws  Exception {
        return ExpenseService.getInstance().getGroupExpenses(groupId);
    }

    public PaymentGraph getSimplifiedPayments(String groupId,String userId) throws  Exception {
        if(GroupService.getInstance().checkUserIdForGroup(groupId,userId)){
            return ExpenseService.getInstance().getPaymentGraph(groupId);
        }
        else {
            throw new Exception("User not part of the Group");
        }
    }


    // writes previously existing object with the newObject , Immutable objects maintained no Inconsistent data encountered.
    public void editExpense(Expense expense,String userId) throws Exception {
        ExpenseService.getInstance().editExpense(expense,userId);
    }
}


/*
 */