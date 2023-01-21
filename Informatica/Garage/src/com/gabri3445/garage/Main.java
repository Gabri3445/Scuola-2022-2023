package com.gabri3445.garage;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Enter the current hour");
            choice = scanner.nextInt();
        } while (choice < 0);
        Garage garage = new Garage(choice);
        garageMenu(garage);
    }

    private static void garageMenu(Garage garage) {
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
                    [6] Exit
                    """);
            choice = scanner.nextInt();
            switch (choice) {
                case 0 -> {
                    enterVehicle(garage);
                }
                case 1 -> {
                    exitVehicle(garage);
                }
                case 2 -> {
                    try {
                        printVehicles(garage.getVehicles());
                    } catch (Exception e) {
                        System.out.println("No more vehicles");
                    }
                    do {
                        System.out.println("Enter the index of the vehicle to view");
                        choice = scanner.nextInt();
                    } while (choice < 0 || choice > 24);
                    printVehicles(garage.getVehicles()[choice], Arrays.asList(garage.getVehicles()).indexOf(garage.getVehicles()[choice]));
                }
                case 3 -> {
                    try {
                        printVehicles(garage.getVehicles());
                    } catch (Exception e) {
                        System.out.println("No more vehicles");
                    }
                }
                case 4 -> {
                    try {
                        printBrands(garage.getVehicles());
                    } catch (Exception e) {
                        System.out.println("No more vehicles");
                    }
                }
                case 5 -> {
                    garage.advanceHour();
                    System.out.println("Current hour: " + garage.getCurrentHour());
                }
            }
        } while (choice != 6);
    }

    private static void enterVehicle(Garage garage) {
        Vehicle vehicle = createVehicle(garage);
        switch (garage.enterVehicle(vehicle)) {
            case Success -> System.out.println("Vehicle added");
            case NotEnoughSpaces -> System.out.println("Not enough spaces");
            case GeneralFailure -> {
                throw new RuntimeException();
            }
        }
    }

    private static void exitVehicle(Garage garage) {
        int choice;
        printVehicles(garage.getVehicles());
        Garage.ExitState exitState = null;
        do {
            System.out.println("Choose the index of the vehicle to remove -- Enter -1 to exit");
            choice = scanner.nextInt();
            if (choice >= 0 && choice < garage.getVehicles().length) {
                exitState = garage.exitVehicle(choice, garage.getVehicles()[choice]);
                switch (exitState) {
                    case Success -> System.out.println("Vehicle removed");
                    case OutOfRange -> System.out.println("Out of range"); // This SHOULD not happen
                    case GeneralFailure -> {
                        System.out.println("No vehicle found at specified index");
                    }
                }
            } else if (choice != -1) {
                exitState = Garage.ExitState.GeneralFailure;
            }
        } while (exitState != Garage.ExitState.Success || choice != -1);
    }

    private static void printVehicles(Vehicle[] vehicles) {
        int i = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                printVehicle(vehicle, Arrays.asList(vehicles).indexOf(vehicle));
            }
            i++;
        }
    }

    private static void printVehicles(Vehicle vehicle, int index) {
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

    private static void printBrands(Vehicle[] vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println("Brand: " + vehicle.getBrand());
        }
    }

    private static Vehicle createVehicle(Garage garage) {
        System.out.println("Enter 0 for a Car or 1 for a bike");
        int choice = scanner.nextInt();
        if (choice == 0) {
            System.out.println("Enter the name");
            String name = scanner.next();
            System.out.println("Enter the brand");
            String brand = scanner.next();
            System.out.println("Enter the license plate");
            String licensePlate = scanner.next();
            int entranceHour = garage.getCurrentHour();
            return new Car(name, brand, licensePlate, entranceHour);
        } else if (choice == 1) {
            System.out.println("Enter the name");
            String name = scanner.next();
            System.out.println("Enter the brand");
            String brand = scanner.next();
            System.out.println("Enter the license plate");
            String licensePlate = scanner.next();
            int entranceHour = garage.getCurrentHour();
            return new Bike(name, brand, licensePlate, entranceHour);
        }
        throw new RuntimeException();
    }
}
