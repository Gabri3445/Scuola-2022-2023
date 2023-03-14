package com.gabri3445.inheritance3;

import com.gabri3445.inheritance2.Bike;
import com.gabri3445.inheritance2.Car;
import com.gabri3445.inheritance2.Vehicle;

import java.math.BigDecimal;

public class Garage {
    public BigDecimal repair(Vehicle vehicle) {
        if (!vehicle.isBroken()) {
            return BigDecimal.valueOf(20);
        }
        if (vehicle instanceof Car) {
            vehicle.setBroken(false);
            return BigDecimal.valueOf(150);
        } else if (vehicle instanceof Bike) {
            vehicle.setBroken(false);
            return BigDecimal.valueOf(120);
        }
        throw new RuntimeException();
    }
}
