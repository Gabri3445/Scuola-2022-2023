package com.gabri3445.inheritance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>() {
            {
                add(new Employee("Nicolo", "Martiradonna",
                        "26/01/2006", BigDecimal.valueOf(50)));
                add(new Employee("Luca", "Rossi",
                        "28/02/2006", BigDecimal.valueOf(2)));
                add(new Employee("Gabriele", "Lopez",
                        "29/03/2006", BigDecimal.valueOf(621)));
            }
        };
        employees.sort();
    }

}
