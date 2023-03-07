package com.gabri3445.inheritance;

import com.gabri3445.menu.Arguments;
import com.gabri3445.menu.Menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        Menu menu = new Menu();
        Map<Integer, Arguments> actions = Map.of(
                0, new Arguments(() -> {
                    List<Employee> orderedList = new ArrayList<Employee>(employees);
                    for (int i = 0; i < orderedList.size() - 1; i++) {
                        for (int j = 0; j < orderedList.size() - i - 1; j++) {
                            if (orderedList.get(j).earnsMoreThan(orderedList.get(j + 1))) {
                                Collections.swap(orderedList, j, j + 1);
                            }
                        }
                    }
                    Collections.reverse(orderedList);
                    for (Employee employee :
                            orderedList) {
                        System.out.println(employee.getPersonalData());
                    }
                }, "Print employees")
        );
        menu.executeMenu(actions);
    }
}
