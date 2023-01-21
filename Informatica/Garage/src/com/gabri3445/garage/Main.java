package com.gabri3445.garage;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Enter the current hour");
            choice = scanner.nextInt();
        } while (choice < 0);
        Garage garage = new Garage(choice);
        garageMenu(garage);
    }

    static void garageMenu(Garage garage) {
        int choice;
        do {
            // Why the car brands?
            System.out.println("""
                    [0] Enter a vehicle
                    [1] Exit a vehicle
                    [2] View Vehicle
                    [3] Print occupied spaces
                    [4] Print car brands
                    [5] Advance current hour
                    """);
            choice = scanner.nextInt();
            // Good luck understanding anything
            // TODO Make functions for each case?
            switch (choice) {
                case 0 -> {
                    Vehicle vehicle = createVehicle();
                    switch (garage.enterVehicle(vehicle)) {
                        case Success -> System.out.println("Vehicle added");
                        case NotEnoughSpaces -> System.out.println("Not enough spaces");
                        case GeneralFailure -> {
                            throw new RuntimeException();
                        }
                    }
                }
                case 1 -> {
                    // Might now work
                    printVehicles(garage.getVehicles());
                    Garage.ExitState exitState = null;
                    do {
                        System.out.println("Choose the index of the vehicle to remove -- Enter -1 to exit");
                        choice = scanner.nextInt();
                        if (choice > 0 && choice < 25) {
                            exitState = garage.exitVehicle(choice, garage.getVehicles()[choice]);
                            switch (exitState) {
                                case Success -> System.out.println("Vehicle removed");
                                case OutOfRange -> System.out.println("Out of range"); // This SHOULD not happen
                                case GeneralFailure -> {
                                    throw new RuntimeException();
                                }
                            }
                        } else if (choice != -1) {
                            exitState = Garage.ExitState.GeneralFailure;
                        }
                    } while (exitState != Garage.ExitState.Success || choice != -1);
                }
                case 2 -> {
                    printVehicles(garage.getVehicles());
                    do {
                        System.out.println("Enter the index of the vehicle to view");
                        choice = scanner.nextInt();
                    } while (choice < 0 || choice > 24);
                    printVehicles(garage.getVehicles()[choice], choice);
                }

            }
        } while (choice != 6);
    }

    static void printVehicles(Vehicle[] vehicles) {
        int i = 0;
        for (Vehicle vehicle : vehicles) {
            printVehicle(vehicle, i);
            i++;
        }
    }

    //Method overloading is cool right?
    static void printVehicles(Vehicle vehicle, int index) {
        printVehicle(vehicle, index);
    }

    /* ONLY use this for the print functions
     * DO NOT FOR ANY REASON use this anywhere else
     */
    private static void printVehicle(Vehicle vehicle, int index) {
        System.out.printf("%n%n");
        System.out.println("------------------");
        System.out.println("IDX: " + index);
        System.out.println("Name:" + vehicle.getName());
        System.out.println();
        System.out.println("Brand: " + vehicle.getBrand());
        System.out.println("License plate: " + vehicle.getLicensePlate());
        System.out.println("Entrance hour: " + vehicle.getEntranceHour());
        System.out.println("Hours passed: " + vehicle.getHoursPassed());
    }

    static Vehicle createVehicle() {
        return null;
    }
}
