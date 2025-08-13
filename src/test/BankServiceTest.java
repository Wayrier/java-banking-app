package com.wayrier.bank;

import com.wayrier.bank.model.Account;
import com.wayrier.bank.service.BankService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {

    @Test
    void createDepositTransfer() {
        BankService bank = new BankService();
        Account a = bank.createAccount("Alice");
        Account b = bank.createAccount("Bob");

        a.deposit(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), a.getBalance());

        bank.transfer(a.getId(), b.getId(), new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"), a.getBalance());
        assertEquals(new BigDecimal("40.00"), b.getBalance());
    }
}
