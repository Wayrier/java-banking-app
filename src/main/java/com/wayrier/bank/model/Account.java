package com.wayrier.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
    private final String id = UUID.randomUUID().toString();
    private final String owner;
    private BigDecimal balance = BigDecimal.ZERO;
    private final List<Transaction> history = new ArrayList<>();

    public Account(String owner) { this.owner = owner; }

    public String getId() { return id; }
    public String getOwner() { return owner; }
    public BigDecimal getBalance() { return balance; }
    public List<Transaction> getHistory() { return history; }

    public void deposit(BigDecimal amount) {
        requirePositive(amount);
        balance = balance.add(amount);
        history.add(Transaction.deposit(amount));
    }

    public void withdraw(BigDecimal amount) {
        requirePositive(amount);
        if (balance.compareTo(amount) < 0) throw new IllegalArgumentException("Insufficient funds");
        balance = balance.subtract(amount);
        history.add(Transaction.withdraw(amount));
    }

    public void applyTransferOut(java.math.BigDecimal amount) { balance = balance.subtract(amount); }
    public void applyTransferIn(java.math.BigDecimal amount)  { balance = balance.add(amount);  }

    private void requirePositive(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be positive");
    }
}
