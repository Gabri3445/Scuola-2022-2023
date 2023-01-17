package com.gabri3445.mcdonald;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class McDonald {
    private final static Scanner scanner = new Scanner(System.in);

    public ArrayList<Burger> getBurgers() {
        return Burgers;
    }

    private final ArrayList<Burger> Burgers;

    public McDonald(ArrayList<Burger> burgers) {
        Burgers = burgers;
    }

    public void printMenu(ArrayList<Burger> burgers) {
        System.out.println("Burgers available");
        printBurgers(burgers, true);
    }

     private void printBurgers(@NotNull ArrayList<Burger> burgers, boolean showQuantity) {
        System.out.println();
        for (Burger burger : burgers) {
            System.out.println("------------------");
            System.out.println(burger.getDescription());
            if (showQuantity) {
                System.out.println("Quantity Available: " + burger.getQuantity());
            } else {
                System.out.println("Quantity bought: " + burger.getBoughtQuantity());
            }
            System.out.println("Price: " + burger.getPrice());
            System.out.println("ID: " + burger.getId());
            System.out.println();
        }
    }

    private void showReceipt(@NotNull ArrayList<Burger> burgers) {
        BigDecimal money;
        BigDecimal required = new BigDecimal(0);
        for (Burger burger : burgers) {
            required = required.add(burger.getPrice());
        }
        boolean temp = false;
        do {
            System.out.println("Enter " + required + " or more");
            money = scanner.nextBigDecimal();
            if (money.compareTo(required) >= 0) {
                temp = true;
            }
        } while (!temp);
        System.out.println("Receipt");
        printBurgers(burgers, false);
        for (Burger burger : burgers) {
            burger.setBoughtQuantity(0);
        }
        System.out.println("Remainder = " + (money.subtract(required)));
    }

    public void buyItems(ArrayList<Burger> burgers) {
        ArrayList<Burger> receipt = new ArrayList<>();
        int input;
        do {
            boolean exit = false;
            System.out.println("""
                    [0] Buy burgers
                    [1] Finalize
                    [2] Exit back to menu
                    """);
            input = scanner.nextInt();
            switch (input) {
                case 0 -> {
                    System.out.println("Enter id of the burger you wish to buy");
                    printMenu(burgers);
                    input = scanner.nextInt();
                    try {
                        Burger burger = getBurger(input, burgers);
                        if (burger.getQuantity() - burger.getBoughtQuantity() >= 0) {
                            burger.setBoughtQuantity(burger.getBoughtQuantity() + 1);
                            burger.setQuantity(burger.getQuantity() - 1);
                            receipt.add(burger);
                        } else {
                            System.out.println("Not enough burgers");
                        }
                    } catch (RuntimeException rE) {
                        System.out.println("com.gabri3445.mcdonald.Burger not found");
                    }
                }
                case 1 -> {
                    showReceipt(receipt);
                    exit = true;
                }
            }
            if (exit) {
                input = 2;
            }
        } while (input != 2);
    }

    private @NotNull Burger getBurger(int id, @NotNull ArrayList<Burger> burgers) throws RuntimeException {
        for (Burger burger : burgers) {

            if (burger.getId() == id) {
                return burger;
            }
        }
        throw new RuntimeException();
    }

}