package com.wayrier.bank.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
    public enum Type { DEPOSIT, WITHDRAW, TRANSFER_IN, TRANSFER_OUT }

    private final Type type;
    private final BigDecimal amount;
    private final Instant at = Instant.now();
    private final String note;

    private Transaction(Type type, BigDecimal amount, String note) {
        this.type = type; this.amount = amount; this.note = note;
    }

    public static Transaction deposit(BigDecimal a){ return new Transaction(Type.DEPOSIT,a,null); }
    public static Transaction withdraw(BigDecimal a){ return new Transaction(Type.WITHDRAW,a,null); }
    public static Transaction transferIn(BigDecimal a, String from){ return new Transaction(Type.TRANSFER_IN,a,"from:"+from); }
    public static Transaction transferOut(BigDecimal a, String to){ return new Transaction(Type.TRANSFER_OUT,a,"to:"+to); }

    public Type getType(){ return type; }
    public BigDecimal getAmount(){ return amount; }
    public Instant getAt(){ return at; }
    public String getNote(){ return note; }
}
