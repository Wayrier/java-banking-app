package com.wayrier.bank.service;

import com.wayrier.bank.model.Account;
import com.wayrier.bank.model.Transaction;

import java.math.BigDecimal;
import java.util.*;

public class BankService {
    private final Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String owner){
        Account acc = new Account(owner);
        accounts.put(acc.getId(), acc);
        return acc;
    }

    public Collection<Account> listAccounts(){ return accounts.values(); }
    public Optional<Account> find(String id){ return Optional.ofNullable(accounts.get(id)); }

    public void transfer(String fromId, String toId, BigDecimal amount){
        if (fromId.equals(toId)) throw new IllegalArgumentException("Same account");
        Account from = accounts.get(fromId);
        Account to   = accounts.get(toId);
        if (from == null || to == null) throw new IllegalArgumentException("Account not found");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (from.getBalance().compareTo(amount) < 0) throw new IllegalArgumentException("Insufficient funds");

        from.applyTransferOut(amount);
        to.applyTransferIn(amount);
        from.getHistory().add(Transaction.transferOut(amount, to.getId()));
        to.getHistory().add(Transaction.transferIn(amount, from.getId()));
    }

    /** Replace in-memory accounts with a loaded collection (for JSON load). */
    public void replaceAll(Collection<Account> loaded){
        accounts.clear();
        for (Account a : loaded) accounts.put(a.getId(), a);
    }
}
