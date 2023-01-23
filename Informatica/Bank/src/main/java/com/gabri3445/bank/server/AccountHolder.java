package com.gabri3445.bank.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountHolder {
    private final String password;
    private final String name;
    private final String surname;
    private BigDecimal balance;
    private List<String> movements = new ArrayList<>();
    public AccountHolder(String password, String name, String surname, BigDecimal balance) {
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

    public List<String> getMovements() {
        return movements;
    }

    public void setMovements(List<String> movements) {
        this.movements = movements;
    }

    public Result withdraw(BigDecimal withdrawBalance) {
        if (balance.compareTo(withdrawBalance) >= 0) return Result.InsufficientBalance;
        balance = balance.subtract(withdrawBalance);
        movements.add("Withdrawn: " + withdrawBalance + "Euro");
        return Result.Success;
    }

    public Result deposit(BigDecimal depositBalance) {
        balance = balance.add(depositBalance);
        movements.add("Deposited: " + depositBalance + "€");
        return Result.Success;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public enum Result {
        Success, InsufficientBalance, Error
    }
}
