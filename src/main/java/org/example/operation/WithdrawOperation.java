package org.example.operation;

import lombok.AllArgsConstructor;
import org.example.Account;
import org.example.interfaces.Operation;
import org.example.exceptions.InsufficientFundsException;
import org.example.Transaction;

@AllArgsConstructor
public class WithdrawOperation implements Operation {
    private double amount;

    @Override
    public void execute(Account account) {
        assert account != null : "Аккаунт не инициализирован!";

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Недостаточно средств на счету");
        }
        account.setBalance(account.getBalance() - amount);
        account.addTransaction(new Transaction("Снятие", amount));
    }
}
