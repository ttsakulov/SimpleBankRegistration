package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.interfaces.User;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Account {
    @Setter
    private User owner;
    @Setter
    private double balance;
    private final TransactionHistory transactionHistory;

    public Account(User owner) {
        this.owner = owner;
        this.balance = 0;
        transactionHistory = new TransactionHistory();
    }

    @Override
    public String toString() {
        return "Банковский счёт\n" +
                "\tпользователь: " + owner.getName() + "\n" +
                "\tбаланс: " + balance;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.addTransaction(transaction);
    }

    static class TransactionHistory {
        private final List<Transaction> transactionsList = new ArrayList<>();

        void addTransaction(Transaction transaction) {
            transactionsList.add(transaction);
        }

        List<Transaction> getTransactions() {
            return transactionsList;
        }
    }
}
