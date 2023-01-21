package com.gabri3445.garage;

import java.math.BigDecimal;

public class Bike extends Vehicle {
    public Bike(String name, String brand, String licensePlate, int entranceHour) {
        super(name, brand, licensePlate, entranceHour);
        setCostToEnter(BigDecimal.valueOf(1));
    }
}
