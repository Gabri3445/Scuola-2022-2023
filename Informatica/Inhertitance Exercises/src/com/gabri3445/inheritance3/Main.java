package com.gabri3445.inheritance3;

import com.gabri3445.inheritance2.Bike;
import com.gabri3445.inheritance2.Car;
import com.gabri3445.inheritance2.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Garage garage = new Garage();
        List<Vehicle> vehicleList = new ArrayList<>() {
            {
                add(new Car("GI202GL", "Ferrari", "Enzo Ferrari", true, "Sports car"));
                add(new Bike("BA567RI", "Ducati", "Panigale V4", false, 150));
                add(new Car("NI621CO", "Devel", "Devel Sixteen", true, "Super Car"));
                add(new Bike("LU789CA", "Aprilia", "RS 660", true, 125));
            }
        };
        int choice;
        do {
            System.out.println("Choose which car to repair");
            for (int i = 0; i < vehicleList.size(); i++) {
                System.out.println("[" + i + "] " + (vehicleList.get(i).isBroken() ? "Broken" : "Not broken") + " " + vehicleList.get(i).getLicensePlate() + " " + vehicleList.get(i).getModel());
            }
            choice = scanner.nextInt();
            if (choice < vehicleList.size()) {
                System.out.println("Total to pay: " + garage.repair(vehicleList.get(choice)));
            } else {
                System.out.println("Out of range");
            }
        }while (choice != vehicleList.size());
    }
}
