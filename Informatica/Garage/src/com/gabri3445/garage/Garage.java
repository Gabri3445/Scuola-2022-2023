package com.gabri3445.garage;

import java.math.BigDecimal;
import java.util.Scanner;

public class Garage {
    private final int MAXPLACES = 25;
    private final int MAXCARPLACES = 15;
    private final int MAXBIKEPLACES = 10;
    /**
     * Cars get places from 0 to 14 <br>
     * Bikes get places from 15 to 24
     */
    private final Vehicle[] vehicles = new Vehicle[MAXPLACES];
    private int currentCarIndex = 0;
    private int currentBikeIndex = 15;
    private int currentHour;
    private final Scanner scanner = new Scanner(System.in);

    public Garage(int currentHour) {
        this.currentHour = currentHour;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    /**
     * Handles entering the vehicles <br>
     * Cars get places from 0 to 14 <br>
     * Bikes get places from 15 to 24
     *
     * @param vehicle Vehicle to be inserted
     * @return {@link InsertState}
     */
    public InsertState enterVehicle(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            // Handle Car
            if (currentCarIndex == MAXCARPLACES - 1) return InsertState.NotEnoughSpaces;
            vehicles[currentCarIndex] = vehicle;
            currentCarIndex++;
            return InsertState.Success;
        } else if (vehicle instanceof Bike) {
            // Handle Bike
            if (currentBikeIndex == MAXPLACES - 1) return InsertState.NotEnoughSpaces;
            vehicles[currentBikeIndex] = vehicle;
            currentBikeIndex++;
            return InsertState.Success;
        }
        return InsertState.GeneralFailure;
    }

    /**
     * Handles exiting the vehicles
     *
     * @param index   index of the vehicle to be removed
     * @param vehicle vehicle to be removed (used for type checking)
     * @return {@link ExitState}
     */
    public ExitState exitVehicle(int index, Vehicle vehicle) {
        if (index > MAXPLACES - 1 || index < 0) return ExitState.OutOfRange;
        if (vehicle instanceof Car) {
            // Handle Car
            if (index > MAXCARPLACES - 1) return ExitState.OutOfRange;
            Car car = (Car) vehicle;
            payment(car.getCostToEnter());
            currentCarIndex--;
            vehicles[index] = null;
            return ExitState.Success;
        } else if (vehicle instanceof Bike) {
            // Handle Bike
            if (index > MAXCARPLACES && index < MAXPLACES - 1) return ExitState.OutOfRange;
            Bike bike = (Bike) vehicle;
            payment(bike.getCostToEnter());
            currentBikeIndex--;
            vehicles[index] = null;
            return ExitState.Success;
        }
        return ExitState.GeneralFailure;
    }

    private void payment(BigDecimal required) {
        boolean exit = false;
        BigDecimal money;
        do {
            System.out.println("Enter " + required + " or more");
            money = scanner.nextBigDecimal();
            if (money.compareTo(required) >= 0) exit = true;
        } while (!exit);
    }

    public void advanceHour() {
        if (currentHour == 24) {
            currentHour = 0;
        } else {
            currentHour++;
        }
        for (Vehicle vehicle : vehicles) {
            vehicle.setHoursPassed(vehicle.getHoursPassed() + 1);
        }
    }

    enum InsertState {
        Success, NotEnoughSpaces, GeneralFailure
    }

    enum ExitState {
        Success, OutOfRange, GeneralFailure
    }
}
