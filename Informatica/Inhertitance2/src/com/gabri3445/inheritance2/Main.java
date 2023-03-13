package com.gabri3445.inheritance2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicleList = new ArrayList<>() {
            {
                add(new Car("GI202GL", "Ferrari", "Enzo Ferrari", true, "Sports car"));
                add(new Bike("BA567RI", "Ducati", "Panigale V4", false, 150));
                add(new Car("NI621CO", "Devel", "Devel Sixteen", true, "Super Car"));
                add(new Bike("LU789CA", "Aprilia", "RS 660", true, 125));
            }
        };

        for (Vehicle vehicle:
                vehicleList) {
            if (vehicle.isBroken()) {
                System.out.println(vehicle.getLicensePlate());
            }
        }
    }
}
