package org.example.operation;

import lombok.AllArgsConstructor;
import org.example.Account;
import org.example.interfaces.Operation;
import org.example.Transaction;
import org.example.exceptions.NegativeAmountException;

@AllArgsConstructor
public class DepositOperation implements Operation {
    private double amount;

    @Override
    public void execute(Account account) {

        assert account != null : "Аккаунт не инициализирован!";

        if (amount < 0) {
            throw new NegativeAmountException("Сумма пополнения была отрицательной");
        }
        account.setBalance(account.getBalance() + amount);
        account.addTransaction(new Transaction("Пополнение", amount));
    }
}
