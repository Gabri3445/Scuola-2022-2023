package com.gabri3445.inheritance;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public class Employee extends Person {
    private final String hireDate;
    private final BigDecimal salary;

    public Employee(String name, String surname,
                    String hireDate, BigDecimal salary) {
        super(name, surname);
        this.hireDate = hireDate;
        this.salary = salary;
    }

    @Override
    public String getPersonalData() {
        return super.getPersonalData() + "\n" +
                "Hired on" + hireDate + "\n" +
                "Salary: " + salary.toString();
    }

    public boolean earnsMoreThan(@NotNull Employee employee) {
        return salary.compareTo(employee.salary) > 0;
    }
}
