package com.wayrier.bank;

import com.wayrier.bank.model.Account;
import com.wayrier.bank.service.BankService;
import com.wayrier.bank.io.JsonStore;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    private static final BankService bank = new BankService();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Java Banking App ===");
        while (true) {
            System.out.println("\n1) Create account  2) Deposit  3) Withdraw  4) Transfer");
            System.out.println("5) List accounts   6) Save     7) Load      0) Exit");
            System.out.print("> ");
            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> create();
                    case "2" -> deposit();
                    case "3" -> withdraw();
                    case "4" -> transfer();
                    case "5" -> list();
                    case "6" -> save();
                    case "7" -> load();
                    case "0" -> { System.out.println("Bye!"); return; }
                    default -> System.out.println("Unknown choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void create(){
        System.out.print("Owner name: ");
        Account a = bank.createAccount(in.nextLine().trim());
        System.out.println("Created account " + a.getId());
    }
    private static void deposit(){
        System.out.print("Account ID: "); var id = in.nextLine().trim();
        System.out.print("Amount: "); var amt = new BigDecimal(in.nextLine().trim());
        bank.find(id).ifPresentOrElse(a -> { a.deposit(amt); System.out.println("New balance: " + a.getBalance()); },
                () -> System.out.println("Account not found"));
    }
    private static void withdraw(){
        System.out.print("Account ID: "); var id = in.nextLine().trim();
        System.out.print("Amount: "); var amt = new BigDecimal(in.nextLine().trim());
        bank.find(id).ifPresentOrElse(a -> { a.withdraw(amt); System.out.println("New balance: " + a.getBalance()); },
                () -> System.out.println("Account not found"));
    }
    private static void transfer(){
        System.out.print("From ID: "); var from = in.nextLine().trim();
        System.out.print("To ID: "); var to = in.nextLine().trim();
        System.out.print("Amount: "); var amt = new BigDecimal(in.nextLine().trim());
        bank.transfer(from, to, amt);
        System.out.println("Transfer OK");
    }
    private static void list(){
        Collection<Account> accs = bank.listAccounts();
        if (accs.isEmpty()) { System.out.println("(no accounts)"); return; }
        accs.forEach(a -> System.out.println(a.getId()+" | "+a.getOwner()+" | balance="+a.getBalance()+" | tx="+a.getHistory().size()));
    }
    private static void save() {
        try { JsonStore.save(bank.listAccounts(), "bank.json"); System.out.println("Saved to bank.json"); }
        catch (Exception e){ System.out.println("Save failed: " + e.getMessage()); }
    }
    private static void load() {
        try { bank.replaceAll(JsonStore.load("bank.json")); System.out.println("Loaded accounts from bank.json"); }
        catch (Exception e){ System.out.println("Load failed: " + e.getMessage()); }
    }
}
