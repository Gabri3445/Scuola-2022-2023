package com.gabri3445.garage;

import java.math.BigDecimal;
import java.util.Scanner;

public class Garage {
    final int MAXPLACES = 25;
    final int MAXCARPLACES = 15;
    final int MAXBIKEPLACES = 10;
    Vehicle[] vehicles = new Vehicle[MAXPLACES];
    int currentCarIndex = 0;
    int currentBikeIndex = 15;

    int CurrentHour = 0;

    Scanner scanner = new Scanner(System.in);

    /**
     * Handles entering the vehicles
     * Cars get places from 0 to 14
     * Bikes get places from 15 to 24
     *
     * @param vehicle Vehicle to be inserted
     * @return Success if success, NotEnoughSpaces if there are not enough spaces, GeneralFailure if the vehicle is not of type Car or Bike
     */
    public InsertState enterVehicle(Vehicle vehicle) {
        //TODO enter more system.outs
        if (vehicle instanceof Car) {
            // Handle Car
            if (currentCarIndex == 14) return InsertState.NotEnoughSpaces;
            vehicles[currentCarIndex] = vehicle;
            return InsertState.Success;
        } else if (vehicle instanceof Bike) {
            // Handle Bike
            if (currentBikeIndex == 24) return InsertState.NotEnoughSpaces;
            vehicles[currentBikeIndex] = vehicle;
            return InsertState.NotEnoughSpaces;
        }
        return InsertState.GeneralFailure;
    }

    public ExitState exitVehicle(int index, Vehicle vehicle) {
        //TODO enter more system.outs
        if (index > 24 || index < 0) return ExitState.OutOfRange;
        if (vehicle instanceof Car) {
            // Handle Car
            if (index > 14) return ExitState.OutOfRange;
            Car car = (Car) vehicle;
            payment(car.getCostToEnter());
            vehicles[index] = null;
            return ExitState.Success;
        } else if (vehicle instanceof Bike) {
            // Handle Bike
            if (index > 15 && index < 24) return ExitState.OutOfRange;
            Bike bike = (Bike) vehicle;
            payment(bike.getCostToEnter());
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

    enum InsertState {
        Success, NotEnoughSpaces, GeneralFailure
    }

    enum ExitState {
        Success, OutOfRange, GeneralFailure
    }
}
