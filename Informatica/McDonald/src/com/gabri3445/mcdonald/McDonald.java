package com.gabri3445.mcdonald;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class McDonald {
    private final static Scanner scanner = new Scanner(System.in);
    /**
     * List of available burgers
     * {@link Burger}
     */
    private final ArrayList<Burger> Burgers;

    public McDonald(ArrayList<Burger> burgers) {
        this.Burgers = burgers;
    }

    public ArrayList<Burger> getBurgers() {
        return Burgers;
    }

    /**
     * Prints the menu
     *
     * @param burgers which burgers to print
     */
    public void printMenu(ArrayList<Burger> burgers) {
        System.out.println("Burgers available");
        printBurgers(burgers, true);
    }

    /**
     * Prints the burgers
     *
     * @param burgers      burgers to print
     * @param showQuantity If set to true shows the quantity, otherwise shows the bought quantity
     */
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

    /**
     * Shows the receipt
     *
     * @param burgers which burgers to print
     */
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
        System.out.println("Price to pay = " + required);
        System.out.println("Remainder = " + (money.subtract(required)));
    }

    /**
     * Handles the buying process
     *
     * @param burgers burgers available
     */
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
                            boolean found = false;
                            for (Burger burger1 : receipt) {
                                if (burger1.equals(burger)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                receipt.add(burger);
                            }
                        } else {
                            System.out.println("Not enough burgers");
                        }
                    } catch (RuntimeException rE) {
                        System.out.println("Burger not found");
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

    /**
     * Gets a burger with the specified ID
     *
     * @param id      ID of the requested burger
     * @param burgers burgers to search through
     * @return The burger matching the ID
     * @throws RuntimeException Gets thrown if there is no burger
     */
    private @NotNull Burger getBurger(int id, @NotNull ArrayList<Burger> burgers) throws RuntimeException {
        for (Burger burger : burgers) {

            if (burger.getId() == id) {
                return burger;
            }
        }
        throw new RuntimeException();
    }

}